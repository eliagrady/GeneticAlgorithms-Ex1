package model.artificial_neuron_network.neuron_layers;
import api.Pair;
import model.artificial_neuron_network.activation_functions.IActivationFunction;
import model.artificial_neuron_network.neurons.BasicNeuron;
import model.artificial_neuron_network.neurons.OutputNeuron;

import java.util.ArrayList;

/**
 * The OutputNeuronLayer class,
 * represents an output neuron layer.
 * @author Omer
 *
 */
public class OutputNeuronLayer extends LearningNeuronLayer implements IOutputNeuronLayer
{

    /**
     * the class' constructor.
     * @param layerSize - the size of this layer
     * @param previousLayerSize - the size of the previous neuron layer
     * @param allWeights - the weights of the interconnections with the previous layer
     * @param activationFunction - an activation function
     * @param learningRate - the learning rate of the layer
     * @param momentum - the momentum of the layer
     */
    public OutputNeuronLayer(int layerSize,
                             int previousLayerSize,
                             ArrayList<ArrayList<Double>> allWeights,
                             IActivationFunction activationFunction,
                             double learningRate,
                             double momentum)
    {
    
        // initializes the super class
        super(layerSize, previousLayerSize, allWeights, activationFunction, learningRate, momentum);
    }

    /**
     * creates a neuron.
     * @return the neuron
     */
    @Override
    protected BasicNeuron createNeuron()
    {

        // creates and returns an output layer neuron
        return new OutputNeuron(this.getActivationFunction());
    }

    /**
     * updates the weights of the layer through the back propagation algorithm.
     * @param realOutput - the real outputs that should have been accepted
     */
    @Override
    public void updateWeights(ArrayList<Double> realOutput)
    {

        // gets the layer
        ArrayList<Pair<ArrayList<Double>, BasicNeuron>> layer = this.getLayer();
        
        // gets the last deltas
        ArrayList<Double> lastDeltas = this.getLastDeltas();
        
        // updates the weights
        for (int i = 0; i < layer.size(); ++i)
        {
            
            // gets the current neuron
            OutputNeuron currentNeuron = (OutputNeuron) layer.get(i).getRight();
            
            // calculates the neuron's delta
            double delta = currentNeuron.calculateDelta(realOutput.get(i));
            lastDeltas.set(i, delta);
            
            // updates the weights of the interconnections that connect to the current neuron
            ArrayList<Double> currentWeights = layer.get(i).getLeft();
            ArrayList<Double> currentLastInput = currentNeuron.getLastInput();
            ArrayList<Double> currentLastCapitalDeltas = this.getLastCapitalDeltas().get(i);
            this.commitWeightsUpdate(currentWeights, currentLastInput, delta, currentLastCapitalDeltas);
        }
    }
}
