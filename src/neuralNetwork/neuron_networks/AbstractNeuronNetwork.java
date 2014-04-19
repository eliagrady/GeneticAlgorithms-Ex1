package artificial_neuron_network.neuron_networks;
import java.util.ArrayList;

import artificial_neuron_network.activation_functions.IActivationFunction;
import artificial_neuron_network.neuron_layers.AbstractNeuronLayer;

/**
 * The AbstractNeuronNetwork class,
 * an abstraction that represents a neuron network learning model.
 * @author Omer
 *
 */
public abstract class AbstractNeuronNetwork
{

    // the class' variables
    private ArrayList<ArrayList<ArrayList<Double>>> networkWeights;
    private ArrayList<AbstractNeuronLayer> neuronLayers;
    private ArrayList<Integer> layersSizes;
    private IActivationFunction activationFunction;
        
    
    
    /**
     * the class' constructor.
     * @param layersSizes - contains the size of each layer
     * @param initialWeights - contains the weights for the network
     * @param activationFunction - the activation function
     */
    public AbstractNeuronNetwork(ArrayList<Integer> layersSizes,
                                 IActivationFunction activationFunction)
    {
        
        super();
        this.neuronLayers = new ArrayList<AbstractNeuronLayer>();
        this.layersSizes = layersSizes;
        this.activationFunction = activationFunction;
    }

    /**
     * sets the weights for the network
     * @param networkWeights - the weights for the network
     */
    protected void setNetworkWeights(ArrayList<ArrayList<ArrayList<Double>>> networkWeights)
    {

        this.networkWeights = networkWeights;
    }
    
    /**
     * @return the weights of the entire network
     */
    public ArrayList<ArrayList<ArrayList<Double>>> getNetworkWeights()
    {

        return this.networkWeights;
    }
    
    /**
     * @return the neuron layers
     */
    protected ArrayList<AbstractNeuronLayer> getNeuronLayers()
    {
        return this.neuronLayers;
    }

    /**
     * @return layers sizes
     */
    protected ArrayList<Integer> getLayersSizes()
    {
        
        return this.layersSizes;
    }

    /**
     * @return the activation function
     */
    protected IActivationFunction getActivationFunction()
    {
        
        return this.activationFunction;
    }

    /**
     * initializes the neuron network.
     */
    public abstract void initializeNetwork();
    
    /**
     * gets inputs and returns the result (classification).
     * @param input - the input for the network
     * @return the result of the network for the input
     */
    public ArrayList<Double> getResult(ArrayList<Double> input)
    {
        
        ArrayList<Double> tempInput = new ArrayList<Double>();
        tempInput.addAll(input);
        tempInput.add(new Double(1));
        for (int i = 0; i < this.neuronLayers.size(); ++i)
        {
            
            tempInput = neuronLayers.get(i).getOutputs(tempInput);
            if (i < (this.neuronLayers.size() - 1))
            {
             
                tempInput.add(new Double(1));
            }
        }
        
        return tempInput;
    }
}
