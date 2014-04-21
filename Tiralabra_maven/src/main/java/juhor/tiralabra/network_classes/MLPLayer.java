package juhor.tiralabra.network_classes;

import java.util.Random;
import juhor.tiralabra.data_structures.Vector;

/**
 *
 * @author juhorim
 */
public class MLPLayer {

    private int numOfNeurons, prevNumOfNeurons;
    private MLPNeuron[] neurons;
    private double[] outputs;

    /**
     * The main constructor
     *
     * @param numOfInput Size of the input for this layer (number of nodes in
     * previous layer)
     * @param size number of nodes in this layer
     * @param rand Random for generating initial weights
     */
    public MLPLayer(int numOfInput, int size, Random rand) {
        numOfNeurons = size + 1;
        prevNumOfNeurons = numOfInput + 1;
        neurons = new MLPNeuron[numOfNeurons];
        outputs = new double[numOfNeurons];

        for (int i = 0; i < numOfNeurons; ++i) {
            neurons[i] = new MLPNeuron(prevNumOfNeurons, rand);
        }
    }

    public double getOutput(int i) {
        return outputs[i];
    }

    public int getSize() {
        return numOfNeurons;
    }

    public double getWeight(int i, int j) {
        return neurons[i].getWeight(j);
    }
    
    public MLPNeuron getNeuron(int i){
        return neurons[i];
    }

    public double[] getWeights(int i) {
        return neurons[i].getWeights();
    }

    public void setWeight(int i, int j, double v) {
        neurons[i].setWeight(j, v);
    }

    public double getDerivative(int i) {
        return neurons[i].derivative();
    }

    /**
     * Add bias to the input. Bias can be thought as an extra input. To make it
     * easier for the user, we add it here, so that user doesn't have to think
     * about it.
     *
     * @param in the input vector
     * @return input vector with bias
     */
    public double[] addInputBias(double[] in) {
        if (in.length < getWeights(0).length) {
            double newInput[] = new double[in.length + 1];
            for (int i = 0; i < in.length; ++i) {
                newInput[i + 1] = in[i];
            }
            newInput[0] = 1.0;
            return newInput;
        } else { //if bias was already added
            return in;
        }

    }

    /**
     * Evaluate output of this layer
     *
     * @param in
     * @return
     */
    public double[] calculateLayerOutput(double[] in) {
        double inputs[];

        inputs = addInputBias(in);

        for (int i = 1; i < numOfNeurons; ++i) {
            outputs[i] = neurons[i].calculateOutput(inputs);
        }

        outputs[0] = 1.0;

        return outputs;
    }

}
