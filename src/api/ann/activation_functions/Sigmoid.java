package api.ann.activation_functions;

/**
 * The Sigmoid function.
 * @author Omer
 *
 */
public class Sigmoid implements IActivationFunction
{

    @Override
    public double call(double x)
    {

        return (1 / (1 + Math.pow(Math.E, (-1) * x)));
    }

    @Override
    public double derivative(double x)
    {

        double fx = this.call(x);
        return (fx * (1 - fx));
    }
}
