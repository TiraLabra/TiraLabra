package juhor.tiralabra.network_classes;

import java.util.Random;
import juhor.tiralabra.data_structures.Vector;

/**
 * This is the basic component for our Multilayer Perceptron (MLP) neural
 * network.
 *
 * @author juhorim
 */
public class MLPNeuron {

    private double[] weights;
    private double lastActivation;

    /**
     * Main constructor. Initializes weight vector with small random values (between -0.5 and 0.5)
     *
     * @param numOfInput number of inputs
     * @param rand Random for generating initial weights
     */
    public MLPNeuron(int numOfInput, Random rand) {
        weights = new double[numOfInput];

        for (int i = 0; i < numOfInput; ++i) {
            weights[i] = rand.nextDouble() - 0.5;
        }
    }

    public double[] getWeights() {
        return weights;
    }

    public double getWeight(int i) {
        return weights[i];
    }

    public void setWeight(int i, double d) {
        weights[i] = d;
    }

    /**
     * Output for input vector will be dot product with weight vector fed into
     * logistic function
     *
     * @param inputs the input vector
     * @return f(sum(x_1*y_1,..., x_n*y_n)) where f is logistic function
     */
    public double calculateOutput(double[] inputs) {
        lastActivation = 0.0;

        for (int i = 0; i < inputs.length; ++i) {
            System.out.println(weights[i]);
            System.out.println(inputs[i]);
            lastActivation += inputs[i] * weights[i];
        }
        return sigmoid(lastActivation);
    }
    
    /**
     * Activation function: f(d) = 1/(1+e^(-d))
     * @param d
     * @return 
     */
    private double sigmoid(double d) {
        return 1 / (1 + Math.exp(-d));
    }
    
    /**
     * Derivative of sigmoid function at last activation value.
     * @return 
     */
    public double derivative() {
        double exp = Math.exp(lastActivation);
        
        return exp / (Math.pow(1 + exp,2));
    }

}
