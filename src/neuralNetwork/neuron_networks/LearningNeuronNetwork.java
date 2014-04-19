package artificial_neuron_network.neuron_networks;
import general.Pair;

import java.util.ArrayList;
import java.util.Random;

import artificial_neuron_network.activation_functions.IActivationFunction;
import artificial_neuron_network.neuron_layers.AbstractNeuronLayer;
import artificial_neuron_network.neuron_layers.HiddenNeuronLayer;
import artificial_neuron_network.neuron_layers.LearningNeuronLayer;
import artificial_neuron_network.neuron_layers.OutputNeuronLayer;
import artificial_neuron_network.neurons.BasicNeuron;

/**
 * The LearningNeuronNetwork class,
 * represents a learning neuron network model.
 * (using the back propagation algorithm).
 * @author Omer
 *
 */
public class LearningNeuronNetwork extends AbstractNeuronNetwork
{

    // the class' constant
    private final double STARTING_WEIGHT = 0.00001;
    private final Random RANDOM = new Random();
    
    // the class' variables
    private double learningRate;
    private double momentum;
        
    
    
    /**
     * the class' constructor.
     * @param layersSizes - contains the size of each layer
     * @param weights - contains the weights for the network
     * @param activationFunction - the activation function
     * @param learningRate - the learning rate of the network
     * @param momentum - the momentum of the network
     */
    public LearningNeuronNetwork(ArrayList<Integer> layersSizes,
                                 IActivationFunction activationFunction,
                                 double learningRate,
                                 double momentum)
    {
        
        // initializes the super class
        super(layersSizes, activationFunction);
        
        // sets the learning rate and momentum
        this.learningRate = learningRate;
        this.momentum = momentum;
    }

    /**
     * @return the weights of the entire network
     */
    @Override
    public ArrayList<ArrayList<ArrayList<Double>>> getNetworkWeights()
    {
        
        ArrayList<AbstractNeuronLayer> neuronLayers = this.getNeuronLayers();
        ArrayList<ArrayList<ArrayList<Double>>> networkWeights = new ArrayList<ArrayList<ArrayList<Double>>>();
        
        // collects the weights of the entire network
        for (int i = 0; i < neuronLayers.size(); ++i)
        {
                        
            ArrayList<Pair<ArrayList<Double>, BasicNeuron>> currentLayer = neuronLayers.get(i).getLayer();
            ArrayList<ArrayList<Double>> currentLayerWeights = new ArrayList<ArrayList<Double>>(); 
            
            // collects the weights of the current neuron layer
            for (int j = 0; j < currentLayer.size(); ++j)
            {
                
                // adds the weights that connect to the current neuron
                currentLayerWeights.add(currentLayer.get(j).getLeft());
            }
            
            // adds the weights of the current neuron layer
            networkWeights.add(currentLayerWeights);
        }
        
        // returns the weights
        return networkWeights;
    }
    
    /**
     * returns a randomized weight.
     * @return the weight
     */
    private double createRandomizedWeight()
    {
        
        // gets a random double between 0.00001 and 0.00001 + (0.999/2) 
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
    @Override
    public void initializeNetwork()
    {
        
        ArrayList<AbstractNeuronLayer> neuronLayers = this.getNeuronLayers();
        ArrayList<Integer> layersSizes = this.getLayersSizes();
        IActivationFunction activationFunction = this.getActivationFunction();
        
        // creates the layers
        for (int i = 1; i < layersSizes.size(); ++i)
        {
            
            // gets this and previous layers sizes
            int layerSize = layersSizes.get(i);
            int previousLayerSize = layersSizes.get(i-1) + 1;
            
            // builds randomized weights
            ArrayList<ArrayList<Double>> allWeights = new ArrayList<ArrayList<Double>>();          
            for (int j = 0; j < layerSize; ++j)
            {
                
                ArrayList<Double> currentWeights = new ArrayList<Double>();
                for (int k = 0; k < previousLayerSize; ++k)
                {
                    
                    currentWeights.add(this.createRandomizedWeight());
                }
                allWeights.add(currentWeights);
            }
            
            // creates and adds the layer
            if (i < (layersSizes.size() - 1))
            {
                
                neuronLayers.add(new HiddenNeuronLayer(layerSize,
                                                       previousLayerSize,
                                                       allWeights,
                                                       activationFunction,
                                                       this.learningRate,
                                                       this.momentum));
            }
            
            else
            {
                
                neuronLayers.add(new OutputNeuronLayer(layerSize,
                                                       previousLayerSize,
                                                       allWeights,
                                                       activationFunction,
                                                       this.learningRate,
                                                       this.momentum));
            }
        }
    }
    
    /**
     * updates the weights of the neuron network through the back propagation algorithm.
     * @param realOutput - the real outputs that should have been accepted
     */
    private void updateWeights(ArrayList<Double> realOutput)
    {
        
        ArrayList<AbstractNeuronLayer> neuronLayers = this.getNeuronLayers();
        
        // updates the output layer
        OutputNeuronLayer outputLayer = (OutputNeuronLayer) neuronLayers.get(neuronLayers.size() - 1);
        outputLayer.updateWeights(realOutput);
        
        // updates the rest of the network
        for (int i = (neuronLayers.size() - 2); i >= 0; --i)
        {
            
            HiddenNeuronLayer hiddenLayer = (HiddenNeuronLayer) neuronLayers.get(i);
            hiddenLayer.updateWeights((LearningNeuronLayer) neuronLayers.get(i + 1));
        }
    }
    
    /**
     * trains the neuron network using the back propagation algorithm.
     * @param trainData - data used for the training
     * @param epochsNumber - the epochs number
     */
    public void trainNetwork(ArrayList<Pair<ArrayList<Double>, ArrayList<Double>>> trainData, int epochsNumber)
    {
        
        // trains the neuron network
        for (int i = 0; i < epochsNumber; ++i)
        {
            
            // for every example in the train data
            for (int j = 0; j < trainData.size(); ++j)
            {
             
                ArrayList<Double> example = trainData.get(j).getLeft();
                ArrayList<Double> realOutput = trainData.get(j).getRight();
                
                // calculates the output
                this.getResult(example);

                // updates the weights of the neuron network
                this.updateWeights(realOutput);
            }
        }
    }
}
