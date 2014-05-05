package api.ann.neurons;
import api.ann.activation_functions.IActivationFunction;

/**
 * The AbstractNeuron class,
 * represents a single neuron.
 * @author Omer
 *
 */
public abstract class AbstractNeuron
{
    
    // the class' variables
    private double[] lastInput;
    private double lastOutput;
    private double lastDerivative;
    private IActivationFunction activationFunction;
    
    
    
    /**
     * the class' constructor.
     * @param activationFunction - an activation function
     */
    public AbstractNeuron(IActivationFunction activationFunction)
    {
        
        super();
        this.lastInput = null;
        this.lastOutput = 0;
        this.lastDerivative = 0;
        this.activationFunction = activationFunction;
    }
    
    /**
     * @return the last input
     */
    public double[] getLastInput()
    {
        
        return this.lastInput;
    }
    
    /**
     * @return the last output
     */
    public double getLastOutput()
    {
    
        return this.lastOutput;
    }

    /**
     * @return the last output derivative
     */
    public double getLastOutputDerivative()
    {
        
        return this.lastDerivative;
    }
    
    /**
     * sums up xi * wi.
     * @param values - inputs from the previous neuron layer
     * @param weights - weights of the interconnections with the previous neuron layer
     * @return the sum
     */
    private double sumUp(double[] values, double[] weights)
    {

        // sums the multiplications
        int numOf_Multiplications = values.length;
        double sum = 0;
        for (int i = 0; i < numOf_Multiplications; ++i)
        {
            
            sum += (values[i] * weights[i]);
        }
        
        // returns the sum
        return sum;
    }
       
    /**
     * "fires".
     * @param values - inputs from the previous neuron layer
     * @param weights - weights of the interconnections with the previous neuron layer
     * @return the result
     */
    public double activate(double[] values, double[] weights)
    {
        
        // saves the input values
        this.lastInput = values;
        
        // calculates the sum of xi * wi
        double sum = this.sumUp(values, weights);
        
        // returns the activation of the sigmoid function with the sum as input
        if (null != this.activationFunction)
        {
            
            this.lastOutput = this.activationFunction.call(sum);
            this.lastDerivative = this.activationFunction.derivative(sum);
        }
        return this.lastOutput;
    }
}
