package model.artificial_neuron_network.neuron_layers;
import api.Pair;
import model.artificial_neuron_network.activation_functions.IActivationFunction;
import model.artificial_neuron_network.neurons.BasicNeuron;

import java.util.ArrayList;

/**
 * The AbstractNeuronLayer class,
 * an abstraction that represents a neuron layer.
 * @author Omer
 *
 */
public abstract class AbstractNeuronLayer
{
    
    // the class' variables
    private ArrayList<Pair<ArrayList<Double>, BasicNeuron>> layer;
    private IActivationFunction activationFunction;
    
    
    
    /**
     * the class' constructor.
     * @param layerSize - the size of this layer
     * @param previousLayerSize - the size of the previous neuron layer
     * @param allWeights - the weights of the interconnections with the previous layer
     * @param activationFunction - an activation function
     */
    public AbstractNeuronLayer(int layerSize,
                               int previousLayerSize,
                               ArrayList<ArrayList<Double>> allWeights,
                               IActivationFunction activationFunction)
    {

        super();
        this.activationFunction = activationFunction;

        // creates the layer
        this.layer = new ArrayList<Pair<ArrayList<Double>, BasicNeuron>>();
        for (int i = 0; i < layerSize; ++i)
        {
            
            // gets the current weights
            ArrayList<Double> weights = allWeights.get(i);
            
            // creates the current neuron
            BasicNeuron neuron = this.createNeuron();
            
            // adds the pair to the layer
            this.layer.add(new Pair<ArrayList<Double>, BasicNeuron>(weights, neuron));
        }
    }

    /**
     * @return the layer
     */
    public ArrayList<Pair<ArrayList<Double>, BasicNeuron>> getLayer()
    {
        
        return this.layer;
    }
    
    /**
     * @return the activation function
     */
    protected IActivationFunction getActivationFunction()
    {
        
        return this.activationFunction;
    }
    
    /**
     * creates a neuron.
     * @return the neuron
     */
    protected abstract BasicNeuron createNeuron();
    
    /**
     * calculates and returns all of the outputs of the layer.
     * @param inputs
     * @return
     */
    public ArrayList<Double> getOutputs(ArrayList<Double> inputs)
    {
        
        // the method's variables
        ArrayList<Double> outputs = new ArrayList<Double>(); 
        
        // for every neuron in the layer
        for (int i = 0; i < this.layer.size(); ++i)
        {
            
            Pair<ArrayList<Double>, BasicNeuron> pair = this.layer.get(i);
            outputs.add(pair.getRight().activate(inputs, pair.getLeft()));
        }
        
        // returns the outputs
        return outputs;
    }
}
