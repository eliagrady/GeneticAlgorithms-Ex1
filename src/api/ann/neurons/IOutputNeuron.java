package api.ann.neurons;
/**
 * The IOutputNeuron interface.
 * @author Omer
 *
 */
public interface IOutputNeuron
{

    /**
     * calculates and returns the delta of an output layer neuron
     * @param expectedOutput the real result which the neuron should have fired
     * @return the delta 
     */
    public double calculateDelta(double expectedOutput);
}
