package artificial_neuron_network.neurons;
import java.util.ArrayList;

/**
 * The IHiddenNeuron interface.
 * @author Omer
 *
 */
public interface IHiddenNeuron
{

    /**
     * calculates and returns the delta of a hidden layer neuron.
     * @param weights - weights of the interconnections to the neurons in the next layer
     * @param - deltas - deltas of the neurons in the next layer
     * @return the delta 
     */
    public double calculateDelta(ArrayList<Double> weights, ArrayList<Double> deltas);
}
