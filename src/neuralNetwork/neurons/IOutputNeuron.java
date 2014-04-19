package artificial_neuron_network.neurons;
/**
 * The IOutputNeuron interface.
 * @author Omer
 *
 */
public interface IOutputNeuron
{

    /**
     * calculates and returns the delta of an output layer neuron
     * @param realOutput the real result which the neuron should have fired
     * @return the delta 
     */
    public double calculateDelta(double realOutput);
}
