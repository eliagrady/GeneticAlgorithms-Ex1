package api.ann.activation_functions;

/**
 * The SoftPlus class.
 * @author Omer
 *
 */
public class SoftPlus implements IActivationFunction
{

    @Override
    public double call(double x)
    {

        return Math.log(1 + Math.pow(Math.E, x));
    }

    @Override
    public double derivative(double x)
    {

        return (1 / (1 + Math.pow(Math.E, (-1) * x)));
    }
}
