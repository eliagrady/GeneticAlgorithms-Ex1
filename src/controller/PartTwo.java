package controller;

import api.Pair;
import api.ann.ArtificialNeuronNetwork;
import api.ann.activation_functions.IActivationFunction;
import model.StopWatch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * The PartTwo class.
 * @author Omer
 *
 */
public class PartTwo
{
    
    // the class' constant
    private final int BIAS = 1;
    
    // the class' variables
    private String trainExamples_FilePath; 
    private String validateOne_FilePath;
    private String validateTwo_FilePath;
    //private String testFilePath;
        
    private int numOf_TrainExamples;
    private int numOf_ValidationExamples;
    private int numOf_Attributes;
        
    private ArtificialNeuronNetwork ann;

    
    
    /**
     * the class' constructor
     * @param trainExamples_FilePath - the train file path
     * @param validateOne_FilePath - the validate one file path
     * @param validateTwo_FilePath - the validate two file path
     * @param testExamples_FilePath - the test file path
     * @param activationFunction - the activation function
     * @param learningRate - the learning rate
     * @param momentum - the momentum
     * @param numOf_TrainExamples - the number of train examples
     * @param numOf_ValidationExamples - the number of validation examples
     * @param numOf_Attributes - the number of attributes
     * @param numOf_ClassValues - the number of class values
     * @param numOf_Epochs - the number of epochs
     */
    public PartTwo(String trainExamples_FilePath,
                   String validateOne_FilePath,
                   String validateTwo_FilePath,
                   String testExamples_FilePath,
                   ArrayList<Integer> sizesOf_Layers,
                   IActivationFunction activationFunction,
                   double learningRate,
                   double momentum,
                   int numOf_Epochs,
                   int numOf_TrainExamples,
                   int numOf_ValidationExamples,
                   int numOf_Attributes,
                   int numOf_ClassValues)
    {
        
        super();
        this.trainExamples_FilePath = trainExamples_FilePath;
        this.validateOne_FilePath = validateOne_FilePath;
        this.validateTwo_FilePath = validateTwo_FilePath;
        //this.testExamples_FilePath = testExamples_FilePath;
        this.numOf_TrainExamples = numOf_TrainExamples;
        this.numOf_ValidationExamples = numOf_ValidationExamples;
        this.numOf_Attributes = numOf_Attributes;
        
        // creates the ANN
        this.ann = new ArtificialNeuronNetwork(sizesOf_Layers, activationFunction, learningRate, momentum, numOf_Epochs, numOf_ClassValues);
    }

    /**trainFilePathtrainFilePath
     * loads and returns examples from a file.
     * @param filePath - the examples file path
     * @param numOf_ClassValues - the number of class values
     * @param beginIndex - the starting index of the range of examples to load
     * @param endIndex - the ending index of the range of examples to load
     * @return the examples
     */
    private ArrayList<Pair<double[], Integer>> loadExamples(String filePath, int numOf_Examples)
    {
      
        // the method's variables
        BufferedReader bufferedReader;
        String line;
        ArrayList<Pair<double[], Integer>> examples = new ArrayList<Pair<double[], Integer>>(numOf_Examples);
        
        // tries the following block
        try
        {
         
            // creates a buffered reader for the file
            bufferedReader = new BufferedReader(new FileReader(filePath));

            // reads and loads the examples line by line
            for (int i = 0; i < numOf_Examples; ++i)
            {
                
                
                if (null == (line = bufferedReader.readLine())) { break; }
                if (line.equals("")) { continue; }
                
                
                // splits the current example's values
                String[] exampleValues = line.split(",");

                // gets the example values
                double[] currentExample = new double[numOf_Attributes + BIAS];
                for (int j = 1; j < exampleValues.length; ++j)
                {
                    
                    currentExample[j - 1] = Double.parseDouble(exampleValues[j]);
                }
                currentExample[numOf_Attributes] = BIAS;
                
                examples.add(new Pair<double[], Integer>(currentExample, Integer.parseInt(exampleValues[0])));
            }

            // closes the buffered reader
            bufferedReader.close();
        }
        
        // catches Exception
        catch (Exception e) { e.printStackTrace(); } // does nothing
        
        // returns the examples
        return examples;
    }
    
    /**
     * basic ANN.
     */
    public void ann()
    {
        
        // starts a stop watch
        StopWatch sw = new StopWatch();
        
        // trains the ANN
        ann.train(loadExamples(trainExamples_FilePath, numOf_TrainExamples));

        // classifies validate one and two and prints success percentages
        System.out.printf("Validate 1 : %.3f\n", ann.validate(loadExamples(validateOne_FilePath, numOf_ValidationExamples)));
        System.out.printf("Validate 2 : %.3f\n", ann.validate(loadExamples(validateTwo_FilePath, numOf_ValidationExamples)));
        
        // prints the time taken
        System.out.println("\nTotal Run Time: " + sw.getTime() + "\n");
    }
}
