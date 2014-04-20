package model.artificial_neuron_network.neuron_networks;
import java.util.ArrayList;

import model.artificial_neuron_network.activation_functions.IActivationFunction;
import model.artificial_neuron_network.neuron_layers.AbstractNeuronLayer;
import model.artificial_neuron_network.neuron_layers.BasicNeuronLayer;

/**
 * The BasicNeuronNetwork class,
 * represents a basic neuron network model.
 * @author Omer
 *
 */
public class BasicNeuronNetwork extends AbstractNeuronNetwork
{

    /**
     * the class' constructor.
     * @param layersSizes - contains the size of each layer
     * @param weights - contains the weights for the network
     * @param activationFunction - the activation function
     */
    public BasicNeuronNetwork(ArrayList<Integer> layersSizes,
                              IActivationFunction activationFunction,
                              ArrayList<ArrayList<ArrayList<Double>>> networkWeights)
    {
        
        // initializes the super class
        super(layersSizes, activationFunction);
        
        // sets the initial weights
        this.setNetworkWeights(networkWeights);
    }

    /**
     * initializes the neuron network.
     */
    @Override
    public void initializeNetwork()
    {

        ArrayList<AbstractNeuronLayer> neuronLayers = this.getNeuronLayers();
        ArrayList<Integer> layersSizes = this.getLayersSizes();
        ArrayList<ArrayList<ArrayList<Double>>> networkWeights = this.getNetworkWeights();
        IActivationFunction activationFunction = this.getActivationFunction();
        for (int i = 1; i < layersSizes.size(); ++i)
        {
            
            neuronLayers.add(new BasicNeuronLayer(layersSizes.get(i),
                                                  layersSizes.get(i-1) + 1,                                                       
                                                  networkWeights.get(i-1),
                                                  activationFunction));
        }
    }
}
