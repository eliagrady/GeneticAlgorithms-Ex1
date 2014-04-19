package artificial_neuron_network.activation_functions;


/**
 * The ActivationFunction interface.
 * @author Omer
 *
 */
public interface IActivationFunction
{

    /**
     * the activation function
     * @param input for the function
     * @return the output of the activation function
     */
    public double call(double input);
}
