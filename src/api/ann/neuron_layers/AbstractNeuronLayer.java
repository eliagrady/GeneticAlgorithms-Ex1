package api.ann.neuron_layers;
import java.util.ArrayList;

import api.Pair;
import api.ann.activation_functions.IActivationFunction;
import api.ann.neurons.AbstractNeuron;

/**
 * The AbstractNeuronLayer class,
 * an abstraction that represents a neuron layer.
 * @author Omer
 *
 */
public abstract class AbstractNeuronLayer
{
    
    // the class' variables
    private int sizeOf_Layer;
    private IActivationFunction activationFunction;
    private double learningRate;
    private double momentum;
    
    private ArrayList<Pair<double[], AbstractNeuron>> layer; // pairs of weights and neurons
    private double[] lastDeltas;
    private ArrayList<double[]> lastCapitalDeltas;
    
    /**
     * the class' constructor.
     * @param sizeOf_Layer - the size of this layer
     * @param sizeOf_PreviousLayer - the size of the previous neuron layer
     * @param allWeights - the weights of the interconnections with the previous layer
     * @param activationFunction - an activation function
     */
    public AbstractNeuronLayer(int sizeOf_Layer,
                               int sizeOf_PreviousLayer,
                               ArrayList<double[]> allWeights,
                               IActivationFunction activationFunction,
                               double learningRate,
                               double momentum)
    {

        super();
        this.sizeOf_Layer = sizeOf_Layer;
        this.activationFunction = activationFunction;
        this.learningRate = learningRate;
        this.momentum = momentum;

        // creates the layer
        this.layer = new ArrayList<Pair<double[], AbstractNeuron>>(sizeOf_Layer);
        for (int i = 0; i < sizeOf_Layer; ++i)
        {

            this.layer.add(new Pair<double[], AbstractNeuron>(allWeights.get(i), this.createNeuron()));
        }
        
        // initializes the last deltas (small and capital)
        this.lastDeltas = new double[sizeOf_Layer];
        this.lastCapitalDeltas = new ArrayList<double[]>(sizeOf_Layer);
        for (int i = 0; i < sizeOf_Layer; ++i)
        {
            
            this.lastDeltas[i] = 0;
            
            double[] currentCapitalDeltas = new double[sizeOf_PreviousLayer];
            for (int j = 0; j < sizeOf_PreviousLayer; ++j)
            {
                
                currentCapitalDeltas[j] = 0;
            }
            this.lastCapitalDeltas.add(currentCapitalDeltas);
        }
    }
    
    /**
     * @return the size of the layer
     */
    protected int getSizeOfLayer()
    {
        
        return this.sizeOf_Layer;
    }
    
    /**
     * @return the activation function
     */
    protected IActivationFunction getActivationFunction()
    {
        
        return this.activationFunction;
    }
    
    /**
     * @return the layer
     */
    public ArrayList<Pair<double[], AbstractNeuron>> getLayer()
    {
        
        return this.layer;
    }
    
    /**
     * @return the last deltas
     */
    protected double[] getLastDeltas()
    {
    
        return this.lastDeltas;
    }
    
    /**
     * @return the last capital deltas
     */
    protected ArrayList<double[]> getLastCapitalDeltas()
    {
    
        return this.lastCapitalDeltas;
    }

    /**
     * creates a neuron.
     * @return the neuron
     */
    protected abstract AbstractNeuron createNeuron();
    
    /**
     * calculates and returns the output of this layer
     * @param input - input to the layer
     * @return the output of the layer
     */
    public abstract double[] getLayerOutput(double[] input);
    
    /**
     * commits weight update for interconnections that connect a specific neuron.
     * @param weights - the current weights
     * @param lastInput - the last values that were fired by each interconnection
     * @param delta - the delta of the neuron
     * @param capitalDeltas - the last capital deltas
     */
    protected void commitWeightsUpdate(double[] weights, double[] lastInput, double delta, double[] capitalDeltas)
    {
        
        // updates the weights
        int numOf_Updates = weights.length;
        for (int i = 0; i < numOf_Updates; ++i)
        {
            
            // calculates the capital delta
            double capitalDelta = (this.learningRate * delta * lastInput[i]) + (this.momentum * capitalDeltas[i]);
            capitalDeltas[i] = capitalDelta;
            
            // updates the weights
            double oldWeight = weights[i];
            weights[i] = (oldWeight + capitalDelta);
        }
    }
}
