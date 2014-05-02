package api.ann.neuron_layers;

/**
 * The IOutputNeuronLayer interface.
 * @author Omer
 *
 */
public interface IOutputNeuronLayer
{

    /**
     * updates the weights of the layer through the back propagation algorithm.
     * @param exceptedOutputs - the real outputs that should have been accepted
     */
    public void updateWeights(double[] exceptedOutputs);
}
