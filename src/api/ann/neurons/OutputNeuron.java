package api.ann.neurons;
import api.ann.activation_functions.IActivationFunction;

/**
 * The OutputNeuron class,
 * extends the BasicNeuron class and implements the IOutputNeuron interface.
 * @author Omer
 *
 */
public class OutputNeuron extends AbstractNeuron implements IOutputNeuron
{

    /**
     * the class' constructor.
     * @param activationFunction - an activation function
     */
    public OutputNeuron(IActivationFunction activationFunction)
    {
        
        super(activationFunction);
    }

    @Override
    public double calculateDelta(double expectedOutput)
    {
        
        return (this.getLastOutputDerivative() * (expectedOutput - this.getLastOutput()));
    }
}
