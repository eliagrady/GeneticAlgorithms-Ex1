package model.artificial_neuron_network.neurons;
import java.util.ArrayList;

import model.artificial_neuron_network.activation_functions.IActivationFunction;

/**
 * The BasicNeuron class,
 * represents a single neuron.
 * @author Omer
 *
 */
public class BasicNeuron
{
    
    // the class' variables
    private ArrayList<Double> lastInput;
    private double lastOutput;
    private IActivationFunction activationFunction;
    
    
    
    /**
     * the class' constructor.
     * @param activationFunction - an activation function
     */
    public BasicNeuron(IActivationFunction activationFunction)
    {
        
        super();
        this.lastInput = null;
        this.lastOutput = 0;
        this.activationFunction = activationFunction;
    }
    
    /**
     * @return the last input
     */
    public ArrayList<Double> getLastInput()
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
     * sums up xi * wi.
     * @param values - inputs from the previous neuron layer
     * @param weights - weights of the interconnections with the previous neuron layer
     * @return the sum
     */
    private double sumUp(ArrayList<Double> values, ArrayList<Double> weights)
    {
        
        // invalid input
        if (values.size() != weights.size())
        {
            
            return 0;
        }
        
        // sums the multiplications
        Double sum = new Double(0);
        for (int i = 0; i < weights.size(); ++i)
        {
            
            sum += (values.get(i) * weights.get(i));
        }
        
        // returns the sum
        return sum.doubleValue();
    }
       
    /**
     * "fires".
     * @param values - inputs from the previous neuron layer
     * @param weights - weights of the interconnections with the previous neuron layer
     * @return the result
     */
    public double activate(ArrayList<Double> values, ArrayList<Double> weights)
    {
        
        // saves the input values
        this.lastInput = values;
        
        // calculates the sum of xi * wi
        double sum = this.sumUp(values, weights);
        
        // returns the activation of the sigmoid function with the sum as input
        if (null != this.activationFunction)
        {
            
            this.lastOutput = this.activationFunction.call(sum);
        }
        return this.lastOutput;
    }
}
