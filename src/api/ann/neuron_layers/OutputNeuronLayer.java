package api.ann.neuron_layers;
import java.util.ArrayList;

import api.Pair;
import api.ann.activation_functions.IActivationFunction;
import api.ann.neurons.AbstractNeuron;
import api.ann.neurons.OutputNeuron;

/**
 * The OutputNeuronLayer class,
 * represents an output neuron layer.
 * @author Omer
 *
 */
public class OutputNeuronLayer extends AbstractNeuronLayer implements IOutputNeuronLayer
{

    /**
     * the class' constructor.
     * @param sizeOf_Layer - the size of this layer
     * @param sizeOf_PreviousLayer - the size of the previous neuron layer
     * @param allWeights - the weights of the interconnections with the previous layer
     * @param activationFunction - an activation function
     * @param learningRate - the learning rate of the layer
     * @param momentum - the momentum of the layer
     */
    public OutputNeuronLayer(int sizeOf_Layer,
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

        // creates and returns an output layer neuron
        return new OutputNeuron(this.getActivationFunction());
    }

    @Override
    public double[] getLayerOutput(double[] input)
    {
        
        // the method's variables
        int sizeOf_Layer = this.getSizeOfLayer();
        ArrayList<Pair<double[], AbstractNeuron>> layer = this.getLayer();
        double[] output = new double[sizeOf_Layer];
        
        // for every neuron in the layer
        for (int i = 0; i < sizeOf_Layer; ++i)
        {
            
            Pair<double[], AbstractNeuron> pair = layer.get(i);
            output[i] = pair.getRight().activate(input, pair.getLeft());
        }
        
        // returns the output
        return output;
    }
    
    @Override
    public void updateWeights(double[] expectedOutput)
    {

        // the method's variables
        ArrayList<Pair<double[], AbstractNeuron>> layer = this.getLayer();
        double[] lastDeltas = this.getLastDeltas();
        
        // updates the weights
        for (int i = 0; i < layer.size(); ++i)
        {
            
            // gets the current neuron
            OutputNeuron currentNeuron = (OutputNeuron) layer.get(i).getRight();
            
            // calculates the neuron's delta
            double delta = currentNeuron.calculateDelta(expectedOutput[i]);
            lastDeltas[i] = delta;
            
            // updates the weights of the interconnections that connect to the current neuron
            double[] currentWeights = layer.get(i).getLeft();
            double[] currentLastInput = currentNeuron.getLastInput();
            double[] currentLastCapitalDeltas = this.getLastCapitalDeltas().get(i);
            this.commitWeightsUpdate(currentWeights, currentLastInput, delta, currentLastCapitalDeltas);
        }
    }
}
