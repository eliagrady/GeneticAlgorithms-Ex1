package artificial_neuron_network.neuron_layers;
/**
 * The IHiddenNeuronLayer interface.
 * @author Omer
 *
 */
public interface IHiddenNeuronLayer
{

    /**
     * updates the weights of the layer through the back propagation algorithm.
     * @param nextNeuronLayer - the next neuron layer in the network
     */
    public void updateWeights(LearningNeuronLayer nextNeuronLayer);
}
