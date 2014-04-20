package model.artificial_neuron_network.neurons;
import model.artificial_neuron_network.activation_functions.IActivationFunction;

/**
 * The OutputNeuron class,
 * extends the BasicNeuron class and implements the IOutputNeuron interface.
 * @author Omer
 *
 */
public class OutputNeuron extends BasicNeuron implements IOutputNeuron
{

    /**
     * the class' constructor.
     * @param activationFunction - an activation function
     */
    public OutputNeuron(IActivationFunction activationFunction)
    {
        
        super(activationFunction);
    }

    /**
     * calculates and returns the delta of an output layer neuron.
     * @param realOutput the real result which the neuron should have fired
     * @return the delta 
     */
    @Override
    public double calculateDelta(double realOutput)
    {
        
        double delta = this.getLastOutput() * (1 - this.getLastOutput()) * (realOutput - this.getLastOutput());
        return delta;
    }
}
