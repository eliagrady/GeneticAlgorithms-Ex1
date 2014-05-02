package api.ann.neuron_layers;
import java.util.ArrayList;

import api.Pair;
import api.ann.activation_functions.IActivationFunction;
import api.ann.neurons.AbstractNeuron;
import api.ann.neurons.HiddenNeuron;

/**
 * The HiddenNeuronLayer class,
 * represents a hidden neuron layer.
 * @author Omer
 *
 */
public class HiddenNeuronLayer extends AbstractNeuronLayer implements IHiddenNeuronLayer
{

    // the class' constant
    private final int BIAS = 1;
    
    
    
    /**
     * the class' constructor.
     * @param sizeOf_Layer - the size of this layer
     * @param sizeOf_PreviousLayer - the size of the previous neuron layer
     * @param allWeights - the weights of the interconnections with the previous layer
     * @param activationFunction - an activation function
     * @param learningRate - the learning rate of the layer
     * @param momentum - the momentum of the layer
     */
    public HiddenNeuronLayer(int sizeOf_Layer,
                             int sizeOf_PreviousLayer,
                             ArrayList<double[]> allWeights,
                             IActivationFunction activationFunction,
                             double learningRate,
                             double momentum)
    {
    
        // initializes the super class
        super(sizeOf_Layer, sizeOf_PreviousLayer, allWeights, activationFunction, learningRate, momentum);
    }

    @Override
    protected AbstractNeuron createNeuron()
    {

        // creates and returns a hidden layer neuron
        return new HiddenNeuron(this.getActivationFunction());
    }

    @Override
    public double[] getLayerOutput(double[] input)
    {
        
        // the method's variables
        int sizeOf_Layer = this.getSizeOfLayer();
        ArrayList<Pair<double[], AbstractNeuron>> layer = this.getLayer();
        double[] output = new double[sizeOf_Layer + BIAS];
        
        // for every neuron in the layer
        for (int i = 0; i < sizeOf_Layer; ++i)
        {
            
            Pair<double[], AbstractNeuron> pair = layer.get(i);
            output[i] = pair.getRight().activate(input, pair.getLeft());
        }
        output[sizeOf_Layer] = BIAS;
        
        // returns the output
        return output;
    }
    
    @Override
    public void updateWeights(AbstractNeuronLayer nextNeuronLayer)
    {

        // the method's variables
        ArrayList<Pair<double[], AbstractNeuron>> thisLayer = this.getLayer();
        ArrayList<Pair<double[], AbstractNeuron>> nextLayer = nextNeuronLayer.getLayer();
        
        int sizeOf_ThisLayer = this.getSizeOfLayer();
        int sizeOf_NextLayer = nextNeuronLayer.getSizeOfLayer();
        
        double[] lastDeltas = this.getLastDeltas();
        double[] nextLayerDeltas = nextNeuronLayer.getLastDeltas();
        
        // for each neuron in the layer
        for (int i = 0; i < sizeOf_ThisLayer; ++i)
        {
            
            // gets the current neuron
            HiddenNeuron currentNeuron = (HiddenNeuron) thisLayer.get(i).getRight();

            // calculates the deltas (small and capital) of the neuron
            double[] nextLayerWeights = new double[sizeOf_NextLayer];
            for (int j = 0; j < sizeOf_NextLayer; ++j)
            {
                
                nextLayerWeights[j] = nextLayer.get(j).getLeft()[i];
            }
            double delta = currentNeuron.calculateDelta(nextLayerWeights, nextLayerDeltas);
            lastDeltas[i] = delta;
            
            // updates the weights of the interconnections that connect to the current neuron
            double[] currentWeights = thisLayer.get(i).getLeft();
            double[] currentLastInput = currentNeuron.getLastInput();
            double[] currentLastCapitalDeltas = this.getLastCapitalDeltas().get(i);
            this.commitWeightsUpdate(currentWeights, currentLastInput, delta, currentLastCapitalDeltas);
        }
    }
}
