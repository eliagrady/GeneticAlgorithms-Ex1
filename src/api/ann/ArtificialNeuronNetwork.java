package api.ann;

import api.Pair;
import api.ann.activation_functions.IActivationFunction;
import api.ann.neuron_layers.AbstractNeuronLayer;
import api.ann.neuron_layers.HiddenNeuronLayer;
import api.ann.neuron_layers.OutputNeuronLayer;
import api.classifiers.IClassifier;
import com.google.common.primitives.Doubles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * The ArtificialNeuronNetwork class,
 * represents artificial neuron network learning model.
 * @author Omer
 *
 */
public class ArtificialNeuronNetwork implements IClassifier
{

    // the class' constant
    private final double STARTING_WEIGHT = 0.00001;
    private final Random RANDOM = new Random();
    private final int BIAS = 1;

    // the class' variables
    private ArrayList<AbstractNeuronLayer> neuronLayers;
    private ArrayList<Integer> sizesOf_Layers;
    private IActivationFunction activationFunction;
    private double learningRate;
    private double momentum;
    private int numOf_Epochs;
    
    private int numOf_ClassValues;

    private ArrayList<double[]> classValues;
    
    
    
    /**
     * the class' constructor.
     * @param sizesOf_Layers - contains the size of each layer
     * @param weights - contains the weights for the network
     * @param activationFunction - the activation function
     * @param learningRate - the learning rate of the network
     * @param momentum - the momentum of the network
     */
    public ArtificialNeuronNetwork(ArrayList<Integer> sizesOf_Layers,
                                 IActivationFunction activationFunction,
                                 double learningRate,
                                 double momentum,
                                 int numOf_Epochs,
                                 int numOf_ClassValues)
    {
        
        super();
        this.neuronLayers = new ArrayList<AbstractNeuronLayer>();
        this.sizesOf_Layers = sizesOf_Layers;
        this.activationFunction = activationFunction;
        this.learningRate = learningRate;
        this.momentum = momentum;
        this.numOf_Epochs = numOf_Epochs;
        
        this.numOf_ClassValues = numOf_ClassValues;
        this.classValues = this.classValuesToArrays();
        
        // initializes the network
        this.initializeNetwork();
    }

    /**
     * builds array lists that represents the class values.
     * @return the array lists
     */
    private ArrayList<double[]> classValuesToArrays()
    {
        
        // builds the array lists
        ArrayList<double[]> values = new ArrayList<double[]>(this.numOf_ClassValues);
        for (int i = 0; i < this.numOf_ClassValues; ++i)
        {
            
            double[] value = new double[this.numOf_ClassValues];
            for (int j = 0; j < this.numOf_ClassValues; ++j)
            {
                
                if (j == i)
                {
                    
                    value[j] = 1;
                }
                
                else
                {
                    
                    value[j] = 0;
                }
            }
            
            values.add(value);
        }
        
        // returns the values
        return values;
    }
    
    /**
     * returns a randomized weight.
     * @return the weight
     */
    private double createRandomizedWeight()
    {
        
        // gets a random double between 0.00001 and 0.00001 + (0.999.../2) 
        double weight = (STARTING_WEIGHT + (Math.random() / 2));
        
        // decides whether the weight should be negative or not
        int randomizedInt = this.RANDOM.nextInt(2);
        if (0 == randomizedInt)
        {
            
            weight *= (-1);
        }
        
        // returns the randomized weight
        return weight;
    }    
    
    /**
     * initializes the neuron network.
     */
    private void initializeNetwork()
    {

        // creates the layers
        int sizeOfNetwork = this.sizesOf_Layers.size();
        for (int i = 1; i < sizeOfNetwork; ++i)
        {
            
            // gets this and previous layers sizes
            int sizeOf_Layer = this.sizesOf_Layers.get(i);
            int sizeOf_PreviousLayer = (this.sizesOf_Layers.get(i-1) + BIAS);
            
            // builds randomized weights
            ArrayList<double[]> allWeights = new ArrayList<double[]>(sizeOf_Layer);          
            for (int j = 0; j < sizeOf_Layer; ++j)
            {
                
                double[] currentWeights = new double[sizeOf_PreviousLayer];
                for (int k = 0; k < sizeOf_PreviousLayer; ++k)
                {
                    
                    currentWeights[k] = this.createRandomizedWeight();
                }
                allWeights.add(currentWeights);
            }
            
            // creates and adds the layers
            if (i < (sizeOfNetwork - 1))
            {
                
                this.neuronLayers.add(new HiddenNeuronLayer(sizeOf_Layer, sizeOf_PreviousLayer, allWeights, this.activationFunction, this.learningRate, this.momentum));
            }
            
            else
            {
                
                this.neuronLayers.add(new OutputNeuronLayer(sizeOf_Layer, sizeOf_PreviousLayer, allWeights, this.activationFunction, this.learningRate, this.momentum));
            }
        }
    }
        
    /**
     * returns the class value that an array list represents.
     * @param array - the array list representation of a class value.
     * @return the class value
     */
    private int arrayToInteger(double[] array)
    {

        ArrayList<Double> arrayList = new ArrayList<Double>(Doubles.asList(array));
        return arrayList.indexOf(Collections.max(arrayList));
    }
    
    /**
     * gets inputs and returns the result (classification).
     * @param input - the input for the network
     * @return the classification
     */
    private int feedForward(double[] input)
    {

        // feeds the network
        double[] tempInput = input;
        int sizeOf_Network = this.neuronLayers.size();
        for (int i = 0; i < sizeOf_Network; ++i)
        {
            
            tempInput = neuronLayers.get(i).getLayerOutput(tempInput);
        }
        
        // returns the classification
        int classification = arrayToInteger(tempInput);
        return classification;
    }
    
    /**
     * updates the weights of the neuron network through the back propagation algorithm.
     * @param expectedOutput - the real outputs that should have been accepted
     */
    private void backPropagate(double[] expectedOutput)
    {

        // updates the output layer
        OutputNeuronLayer outputLayer = (OutputNeuronLayer) this.neuronLayers.get(this.neuronLayers.size() - 1);
        outputLayer.updateWeights(expectedOutput);
        
        // updates the rest of the network
        for (int i = (this.neuronLayers.size() - 2); i >= 0; --i)
        {
            
            HiddenNeuronLayer hiddenLayer = (HiddenNeuronLayer) this.neuronLayers.get(i);
            hiddenLayer.updateWeights(this.neuronLayers.get(i + 1));
        }
    }

    @ Override
    public void train(ArrayList<Pair<double[], Integer>> examples)
    {
        
        // the method's variable
        int numOf_Examples = examples.size();
        
        // trains the neuron network
        for (int i = 0; i < this.numOf_Epochs; ++i)
        {

            
            int mistakes = 0; /////////////////////
            
            
            // for every example in the train data
            for (int j = 0; j < numOf_Examples; ++j)
            {

                // feeds the network and updates the weights
                int observedOutput = this.feedForward(examples.get(j).getLeft());
                int expectedOutput = examples.get(j).getRight().intValue();
                if (observedOutput != expectedOutput)
                {
                    

                    
                    
                    ++mistakes; /////////////
                }
                
                this.backPropagate(this.classValues.get(expectedOutput));
            }
            
            
            System.out.println("Epoch #" + (i+1) + ": " + (((double) (numOf_Examples - mistakes)) * (100 / (double) numOf_Examples)) + ".");
        }
        
        
        System.out.println("");
    }
    
    @Override
    public double validate(ArrayList<Pair<double[], Integer>> examples)
    {
        
        // the class' variables
        int numOf_Examples = examples.size();
        int mistakes = 0;
    
        // validates
        for (int i = 0; i < numOf_Examples; ++i)
        {
            
            // classifies each example
            int observedOutput = this.feedForward(examples.get(i).getLeft());
            int expectedOutput = examples.get(i).getRight().intValue();
            
            // wrong classification
            if (observedOutput != expectedOutput)
            {
                
                ++mistakes;
            }
        }
    
        // returns the percentage of success
        return (((double) (numOf_Examples - mistakes)) * (100 / (double) numOf_Examples));
    }
    
    @Override
    public int[] classify(ArrayList<double[]> examples)
    {

        // classifies
        int numOf_Examples = examples.size();
        int[] classifications = new int[numOf_Examples];
        for (int i = 0; i < numOf_Examples; ++i)
        {

            // appends the classification
            classifications[i] = this.feedForward(examples.get(i));
        }

        // returns the classifications
        return classifications;
    }
}
