package api.ann.activation_functions;

/**
 * The TanH class.
 * @author Omer
 *
 */
public class TanH implements IActivationFunction
{

    @Override
    public double call(double x)
    {

        return Math.tanh(x);
    }

    @Override
    public double derivative(double x)
    {

        double y = Math.tanh(x);
        return (1 - (y * y)); 
    }
}
