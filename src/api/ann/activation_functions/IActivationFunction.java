package api.ann.activation_functions;

/**
 * The ActivationFunction interface.
 * @author Omer
 *
 */
public interface IActivationFunction
{

    /**
     * executes the activation function.
     * @param x - input for the function
     * @return the output of the activation function
     */
    public double call(double x);
    
    /**
     * calculates the derivative of the activation function.
     * @param x - input for the derivative
     * @return the output of the derivative
     */
    public double derivative(double x);
}
