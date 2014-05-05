package api.ann.activation_functions;

/**
 * The BoundedReLU function.
 * @author Omer
 *
 */
public class BoundedReLU implements IActivationFunction
{
    
    // the class' variable
    private double a;
    
    
    
    // the class' constructor
    public BoundedReLU(double a)
    {
    
        super();
        this.a = a;
    }

    @Override
    public double call(double x)
    {

        return Math.min(a, Math.max(0, x));
    }

    @Override
    public double derivative(double x)
    {

        return ((x > 0) ? 1 : 0);
    }
}
