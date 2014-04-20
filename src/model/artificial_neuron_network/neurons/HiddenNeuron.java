package model.artificial_neuron_network.neurons;
import java.util.ArrayList;

import model.artificial_neuron_network.activation_functions.IActivationFunction;

/**
 * The HiddenNeuron class,
 * extends the BasicNeuron class and implements the IHiddenNeuron interface.
 * @author Omer
 *
 */
public class HiddenNeuron extends BasicNeuron implements IHiddenNeuron
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
    private double sumUpDeltas(ArrayList<Double> weights, ArrayList<Double> deltas)
    {
        
        // invalid input
        if (weights.size() != deltas.size())
        {
            
            return 0;
        }
        
        // sums the multiplications
        Double sum = new Double(0);
        for (int i = 0; i < weights.size(); ++i)
        {
            
            sum += (weights.get(i) * deltas.get(i));
        }
        
        // returns the sum
        return sum.doubleValue();
    }
    
    /**
     * calculates and returns the delta of a hidden layer neuron.
     * @param weights - weights of the interconnections to the neurons in the next layer
     * @param deltas - deltas of the neurons in the next layer
     * @return the delta 
     */
    @Override
    public double calculateDelta(ArrayList<Double> weights, ArrayList<Double> deltas)
    {

        // calculates the sum of wi * di
        double sum = this.sumUpDeltas(weights, deltas);
        
        // calculates and returns the delta
        double delta = this.getLastOutput() * (1 - this.getLastOutput()) * sum;
        return delta;
    }
}
