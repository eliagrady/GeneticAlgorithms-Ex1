package artificial_neuron_network.neuron_layers;
import java.util.ArrayList;

/**
 * The IOutputNeuronLayer interface.
 * @author Omer
 *
 */
public interface IOutputNeuronLayer
{

    /**
     * updates the weights of the layer through the back propagation algorithm.
     * @param realOutputs - the real outputs that should have been accepted
     */
    public void updateWeights(ArrayList<Double> realOutputs);
}
