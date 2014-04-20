package model.artificial_neuron_network.neuron_layers;
import java.util.ArrayList;

import model.artificial_neuron_network.activation_functions.IActivationFunction;
import model.artificial_neuron_network.neurons.BasicNeuron;

/**
 * The BasicNeuronLayer class,
 * represents a basic neuron layer.
 * @author Omer
 *
 */
public class BasicNeuronLayer extends AbstractNeuronLayer
{
    
    /**
     * the class' constructor.
     * @param layerSize - the size of this layer
     * @param previousLayerSize - the size of the previous neuron layer
     * @param allWeights - the weights of the interconnections with the previous layer
     * @param activationFunction - an activation function
     */
    public BasicNeuronLayer(int layerSize,
                            int previousLayerSize,
                            ArrayList<ArrayList<Double>> allWeights,
                            IActivationFunction activationFunction)
    {
        
        // initializes the super class
        super(layerSize, previousLayerSize, allWeights, activationFunction);
    }
    
    /**
     * creates a neuron.
     * @return the neuron
     */
    @Override
    protected BasicNeuron createNeuron()
    {

        // creates and returns a basic neuron
        return new BasicNeuron(this.getActivationFunction());
    }
}
