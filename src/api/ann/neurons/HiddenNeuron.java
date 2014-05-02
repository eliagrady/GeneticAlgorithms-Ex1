package api.ann.neurons;
import api.ann.activation_functions.IActivationFunction;

/**
 * The HiddenNeuron class,
 * extends the BasicNeuron class and implements the IHiddenNeuron interface.
 * @author Omer
 *
 */
public class HiddenNeuron extends AbstractNeuron implements IHiddenNeuron
{

    /**
     * the class' constructor.
     * @param activationFunction - an activation function
     */
    public HiddenNeuron(IActivationFunction activationFunction)
    {
        
        super(activationFunction);
    }

    /**
     * sums up xi * di.
     * @param weights - weights of the interconnections with the next neuron layer
     * @param deltas - deltas of the neurons in the next layer
     * @return the sum
     */
    private double sumUpDeltas(double[] weights, double[] deltas)
    {
        
        // invalid input
        if (weights.length != deltas.length)
        {
            
            return 0;
        }
        
        // sums the multiplications
        int numOf_Multiplications = weights.length;
        double sum = 0;
        for (int i = 0; i < numOf_Multiplications; ++i)
        {
            
            sum += (weights[i] * deltas[i]);
        }
        
        // returns the sum
        return sum;
    }

    @Override
    public double calculateDelta(double[] weights, double[] deltas)
    {
        
        // gets the last derivative
        double derivative = this.getLastOutputDerivative();

        // optimization : skips sum up deltas if 0
        if (0 == derivative)
        {
            
            return 0;
        }
        
        // calculates the sum of wi * di
        double sum = this.sumUpDeltas(weights, deltas);
        
        // calculates and returns the delta
        return (derivative * sum);
    }
}
