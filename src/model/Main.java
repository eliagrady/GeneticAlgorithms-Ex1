package model;
import api.Pair;
import eightQueens.EightQueensVisualizer;
import eightQueens.RandomEightQueensSolverOld;
import model.artificial_neuron_network.activation_functions.Sigmoid;
import model.artificial_neuron_network.neuron_networks.BasicNeuronNetwork;
import model.artificial_neuron_network.neuron_networks.LearningNeuronNetwork;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The Main class,
 * used for testing.
 * @author Omer
 * //TODO make this main an entry point for testing purposes.
 */
public class Main
{

    // the class' variable
    private static final String trainFilePath = "C:/Users/Omer/Desktop/train.txt"; 
    private static final String testFilePath = "C:/Users/Omer/Desktop/test.txt";
    
    
    
    /**
     * the main method of the class.
     * @param args - ignored.
     */
    public static void main(String[] args)
    {
        
        // tests the random eight queens solver
        //testRandomEightQueensSolver();
        
        // tests basic network
        //testBasicNeuronNetwork();
        
        // tests learning network
        testLearningNetwork();
    }
    
    /**
     * tests the random eight queens solver.
     */
    public static void testRandomEightQueensSolver()
    {

        // finds an eight queens puzzle solution
        RandomEightQueensSolverOld solver = new RandomEightQueensSolverOld();
        ArrayList<Integer> queensPositions = solver.getSoluion();
        
        // prints the solution
        System.out.println(EightQueensVisualizer.eightQueensPuzzleToString(queensPositions));
        System.out.println(queensPositions);
    }
    
    /**
     * tests the basic neuron network.
     */
    public static void testBasicNeuronNetwork()
    {
        
        ArrayList<Integer> layersSizes = new ArrayList<Integer>();
        layersSizes.add(new Integer(2));
        layersSizes.add(new Integer(2));
        layersSizes.add(new Integer(2));
        
        ArrayList<ArrayList<ArrayList<Double>>> allLayersweights = new ArrayList<ArrayList<ArrayList<Double>>>();
        ArrayList<ArrayList<Double>> layerOneWeights = new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> layerTwoWeights = new ArrayList<ArrayList<Double>>();
        ArrayList<Double> hiddenOneWeights = new ArrayList<Double>();
        ArrayList<Double> hiddenTwoWeights = new ArrayList<Double>();
        ArrayList<Double> OuputOneWeights = new ArrayList<Double>();
        ArrayList<Double> OuputTwoWeights = new ArrayList<Double>();
        
        
        hiddenOneWeights.add(new Double(3.0386875552907737));
        hiddenOneWeights.add(new Double(3.037844012511573));
        hiddenOneWeights.add(new Double(2.221302229290372));
        
        hiddenTwoWeights.add(new Double(3.096441485353698));
        hiddenTwoWeights.add(new Double(3.0970419796867206));
        hiddenTwoWeights.add(new Double(2.2871876168362144));
        
        OuputOneWeights.add(new Double(-4.4601199573386054));
        OuputOneWeights.add(new Double(-4.665500338052224));
        OuputOneWeights.add(new Double(3.852795454987013));
        
        OuputTwoWeights.add(new Double(4.511704255952126));
        OuputTwoWeights.add(new Double(4.613836251265302));
        OuputTwoWeights.add(new Double(-3.852648351219511));
        
        
        layerOneWeights.add(hiddenOneWeights);
        layerOneWeights.add(hiddenTwoWeights);
        
        layerTwoWeights.add(OuputOneWeights);
        layerTwoWeights.add(OuputTwoWeights);
        
        allLayersweights.add(layerOneWeights);
        allLayersweights.add(layerTwoWeights);
        
        // creates and initializes the basic network
        BasicNeuronNetwork basicNeuronNetwork = new BasicNeuronNetwork(layersSizes, new Sigmoid(), allLayersweights);
        basicNeuronNetwork.initializeNetwork();
        ///
        
        
        System.out.println("Weights:");
        System.out.println(basicNeuronNetwork.getNetworkWeights());
        System.out.println("");
        
        // input
        ArrayList<Double> inputs = new ArrayList<Double>();
        inputs.add(new Double(0.1));
        inputs.add(new Double(0.1));
        
        // gets the result
        System.out.println(basicNeuronNetwork.getResult(inputs));
        
        
        // validates
        Sigmoid s = new Sigmoid();
        double hiddenOneOutput = s.call((inputs.get(0) * hiddenOneWeights.get(0)) + (inputs.get(1) * hiddenOneWeights.get(1)) + hiddenOneWeights.get(2));
        double hiddenTwoOutput = s.call((inputs.get(0) * hiddenTwoWeights.get(0)) + (inputs.get(1) * hiddenTwoWeights.get(1)) + hiddenTwoWeights.get(2));
        double outputOneOutput = s.call((hiddenOneOutput * OuputOneWeights.get(0)) + (hiddenTwoOutput * OuputOneWeights.get(1)) + OuputOneWeights.get(2));
        double outputTwoOutput = s.call((hiddenOneOutput * OuputTwoWeights.get(0)) + (hiddenTwoOutput * OuputTwoWeights.get(1)) + OuputTwoWeights.get(2));
        
        System.out.println(outputOneOutput);
        System.out.println(outputTwoOutput);
    }
    
    /**
     * returns array lists that represents class values.
     * @param classValuesNumber - the class values number
     * @return the array lists
     */
    private static ArrayList<ArrayList<Double>> classValuesToArrayRepresentation(int classValuesNumber)
    {
        
        // builds the array lists
        ArrayList<ArrayList<Double>> values = new ArrayList<ArrayList<Double>>();
        for (int i = 0; i < classValuesNumber; ++i)
        {
            
            ArrayList<Double> value = new ArrayList<Double>();
            for (int j = 0; j < classValuesNumber; ++j)
            {
                
                if (j == i)
                {
                    
                    value.add(new Double(0.9));
                }
                
                else
                {
                    
                    value.add(new Double(0.1));
                }
            }
            
            values.add(value);
        }
        
        // returns the values
        return values;
    }
    
    /**
     * returns the class value that an array list represents.
     * @param array - the array list representation of a class value.
     * @return the class value
     */
    private static int arrayRepresentationToClassValue(ArrayList<Double> array)
    {
        
        // finds the max value
        double max = Collections.max(array);
        
        // returns the class value
        return array.indexOf(max);
    }
    
    /**
     * loads and returns examples from a file.
     * @param filePath - the examples file path
     * @return the examples
     */
    private static ArrayList<Pair<ArrayList<Double>, ArrayList<Double>>> loadExamples(String filePath)
    {
      
        // the method's variables
        BufferedReader bufferedReader;
        String line;
        int classValuesNumber = 0;
        ArrayList<Pair<ArrayList<Double>, ArrayList<Double>>> examples = new ArrayList<Pair<ArrayList<Double>, ArrayList<Double>>>();
        
        // tries the following block
        try
        {
         
            // creates a buffered reader for the file
            bufferedReader = new BufferedReader(new FileReader(filePath));
            
            // reads the class' values number
            if ((line = bufferedReader.readLine()) != null)
            {
                
                classValuesNumber = (new Integer(line)).intValue();
            }
            
            // builds array list representations of the class values
            ArrayList<ArrayList<Double>> classValues = classValuesToArrayRepresentation(classValuesNumber);
            
            // reads the examples line by line
            while ((line = bufferedReader.readLine()) != null)
            {

                if (!line.equals(""))
                {
                    
                    // splits
                    String[] exampleValues = line.split(",");
                    
                    // gets the class value
                    ArrayList<Double> realOutput = classValues.get(new Integer(exampleValues[0]));
                    
                    // gets the attributes values
                    ArrayList<Double> attributesValues = new ArrayList<Double>();
                    for (int i = 1; i < exampleValues.length; ++i)
                    {
                        
                        attributesValues.add(new Double(exampleValues[i]));
                    }
                    
                    // adds the example
                    examples.add(new Pair<ArrayList<Double>, ArrayList<Double>>(attributesValues, realOutput));
                }
            }

            // closes the buffered reader
            bufferedReader.close();
        }
        
        // catches Exception
        catch (Exception e) {} // does nothing
        
        // returns the examples
        return examples;
    }
    
    /**
     * tests the learning neuron network.
     */
    public static void testLearningNetwork()
    {
        
        // loads the examples
        ArrayList<Pair<ArrayList<Double>, ArrayList<Double>>> trainExamples = loadExamples(trainFilePath);
        ArrayList<Pair<ArrayList<Double>, ArrayList<Double>>> testExamples = loadExamples(testFilePath);
        
        // layers sizes
        ArrayList<Integer> layersSizes = new ArrayList<Integer>(); 
        layersSizes.add(2);
        layersSizes.add(2);
        layersSizes.add(2);

        // creates, initializes and trains a neuron network
        LearningNeuronNetwork learningNeuronNetwork = new LearningNeuronNetwork(layersSizes, new Sigmoid(), 0.1, 0.2);
        learningNeuronNetwork.initializeNetwork();
        learningNeuronNetwork.trainNetwork(trainExamples, 10000);

        // classifies
        int mistakes = 0;
        for (int i = 0; i < testExamples.size(); ++i)
        {
            
            ArrayList<Double> outputArray = learningNeuronNetwork.getResult(testExamples.get(i).getLeft());
            ArrayList<Double> realOutputArray = testExamples.get(i).getRight();
            
            int output = arrayRepresentationToClassValue(outputArray);
            int realOutput = arrayRepresentationToClassValue(realOutputArray);
            
            System.out.println("Example " + i + ": " + "Real Output: " + realOutput + " Actual Output: " + output);
            
            if (output != realOutput)
            {
                
                ++mistakes;
            }
        }
        
        // percentage of success
        double percentage = ((double) (testExamples.size() - mistakes)) * (100 / (double) testExamples.size());
        System.out.println(percentage);
    }
}
