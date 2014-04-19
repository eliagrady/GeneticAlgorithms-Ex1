package artificial_neuron_network.neuron_layers;
import general.Pair;

import java.util.ArrayList;

import artificial_neuron_network.activation_functions.IActivationFunction;
import artificial_neuron_network.neurons.BasicNeuron;
import artificial_neuron_network.neurons.HiddenNeuron;

/**
 * The HiddenNeuronLayer class,
 * represents a hidden neuron layer.
 * @author Omer
 *
 */
public class HiddenNeuronLayer extends LearningNeuronLayer implements IHiddenNeuronLayer
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
    public HiddenNeuronLayer(int layerSize,
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

        // creates and returns a hidden layer neuron
        return new HiddenNeuron(this.getActivationFunction());
    }
    
    /**
     * updates the weights of the layer through the back propagation algorithm.
     * @param LearningNeuronLayer - the next neuron layer in the network
     */
    @Override
    public void updateWeights(LearningNeuronLayer nextNeuronLayer)
    {

        // gets the layers
        ArrayList<Pair<ArrayList<Double>, BasicNeuron>> thisLayer = this.getLayer();
        ArrayList<Pair<ArrayList<Double>, BasicNeuron>> nextLayer = nextNeuronLayer.getLayer();
        
        // gets the last deltas
        ArrayList<Double> lastDeltas = this.getLastDeltas();
        ArrayList<Double> nextLayerDeltas = nextNeuronLayer.getLastDeltas();
        
        // for each neuron
        for (int i = 0; i < thisLayer.size(); ++i)
        {
            
            // gets the current neuron
            HiddenNeuron currentNeuron = (HiddenNeuron) thisLayer.get(i).getRight();

            // calculates the neuron deltas (small and capital)
            ArrayList<Double> nextLayerWeights = new ArrayList<Double>();
            for (int j = 0; j < nextLayer.size(); ++j)
            {
                
                nextLayerWeights.add(nextLayer.get(j).getLeft().get(i));
            }
            double delta = currentNeuron.calculateDelta(nextLayerWeights, nextLayerDeltas);
            lastDeltas.set(i, delta);
            
            // updates the weights of the interconnections that connect to the current neuron
            ArrayList<Double> currentWeights = thisLayer.get(i).getLeft();
            ArrayList<Double> currentLastInput = currentNeuron.getLastInput();
            ArrayList<Double> currentLastCapitalDeltas = this.getLastCapitalDeltas().get(i);
            this.commitWeightsUpdate(currentWeights, currentLastInput, delta, currentLastCapitalDeltas);
        }
    }
}
