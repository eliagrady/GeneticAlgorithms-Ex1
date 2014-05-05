package api.ann.activation_functions;

/**
 * The ReLU function.
 * @author Omer
 *
 */
public class ReLU implements IActivationFunction
{
    
    @Override
    public double call(double x)
    {

        return ((x > 0) ? x : 0);
    }

    @Override
    public double derivative(double x)
    {

        return ((x > 0) ? 1 : 0);
    }
}
