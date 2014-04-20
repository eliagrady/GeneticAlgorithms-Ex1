package model.artificial_neuron_network.neuron_layers;
import java.util.ArrayList;

import model.artificial_neuron_network.activation_functions.IActivationFunction;

/**
 * The BasicNeuronLayer class,
 * represents a basic neuron layer.
 * @author Omer
 *
 */
public abstract class LearningNeuronLayer extends AbstractNeuronLayer
{

    // the class' variables
    private double learningRate;
    private double momentum;
    private ArrayList<Double> lastDeltas;
    private ArrayList<ArrayList<Double>> lastCapitalDeltas;
    
    
    
    /**
     * the class' constructor.
     * @param layerSize - the size of this layer
     * @param previousLayerSize - the size of the previous neuron layer
     * @param allWeights - the weights of the interconnections with the previous layer
     * @param activationFunction - an activation function
     * @param learningRate - the learning rate of the layer
     * @param momentum - the momentum of the layer
     */
    public LearningNeuronLayer(int layerSize,
                               int previousLayerSize,
                               ArrayList<ArrayList<Double>> allWeights,
                               IActivationFunction activationFunction,
                               double learningRate,
                               double momentum)
    {
        
        // initializes the super class
        super(layerSize, previousLayerSize, allWeights, activationFunction);
        
        // sets the learning rate and momentum
        this.learningRate = learningRate;
        this.momentum = momentum;
        
        // initializes the last deltas (small and capital)
        this.lastDeltas = new ArrayList<Double>();
        this.lastCapitalDeltas = new ArrayList<ArrayList<Double>>();
        for (int i = 0; i < layerSize; ++i)
        {
            
            this.lastDeltas.add(new Double(0));
            
            ArrayList<Double> currentCapitalDeltas = new ArrayList<Double>();
            for (int j = 0; j < previousLayerSize; ++j)
            {
                
                currentCapitalDeltas.add(new Double(0));
            }
            this.lastCapitalDeltas.add(currentCapitalDeltas);
        }
    }
    
    /**
     * @return the last deltas
     */
    protected ArrayList<Double> getLastDeltas()
    {
    
        return this.lastDeltas;
    }
    
    /**
     * @return the last capital deltas
     */
    protected ArrayList<ArrayList<Double>> getLastCapitalDeltas()
    {
    
        return this.lastCapitalDeltas;
    }
    
    /**
     * commits weight update for interconnections that connect a specific neuron.
     * @param weights - the current weights
     * @param lastInput - the last values that were fired by each interconnection
     * @param delta - the delta of the neuron
     */
    protected void commitWeightsUpdate(ArrayList<Double> weights, ArrayList<Double> lastInput, double delta, ArrayList<Double> capitalDeltas)
    {
        
        // updates the weights
        for (int i = 0; i < weights.size(); ++i)
        {
            
            // calculates the capital delta
            double capitalDelta = (this.learningRate * delta * lastInput.get(i)) + (this.momentum * capitalDeltas.get(i));
            capitalDeltas.set(i, capitalDelta);
            
            // updates the weights
            double oldWeight = weights.get(i);
            weights.set(i, oldWeight + capitalDelta);
        }
    }
}
