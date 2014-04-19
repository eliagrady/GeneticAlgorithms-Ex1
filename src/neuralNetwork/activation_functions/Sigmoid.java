package artificial_neuron_network.activation_functions;
/**
 * The Sigmoid function.
 * @author Omer
 *
 */
public class Sigmoid implements IActivationFunction
{

    /**
     * the class' constructor.
     */
    public Sigmoid()
    {
        
        super();
    }
    
    /**
     * the sigmoid activation function : 1/(1+e^(-x)).
     * @param input for the function
     * @return the output of the sigmoid function
     */
    public double call(double input)
    {
        
        // returns the output of the sigmoid function
        double result = (1 / (1 + Math.pow(Math.E, (-1) * input)));
        return result;
    }
}
