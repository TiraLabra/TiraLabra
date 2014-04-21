package juhor.tiralabra.network_classes;

import java.util.Random;
import juhor.tiralabra.data_structures.TrainingData;
import juhor.tiralabra.data_structures.Vector;

/**
 * This class will take care of actual backpropagation based learning
 *
 * @author juhorim
 */
public class MLPNetwork {

    private MLPLayer[] layers;
    private double[][][] dWeights;
    private double[][] gradients;

    public MLPNetwork(int[] numOfNeurons) {
        Random rand = new Random();
        
        layers = new MLPLayer[numOfNeurons.length];
        for (int i = 0; i < numOfNeurons.length; ++i) {
            layers[i] = new MLPLayer(i == 0 ? numOfNeurons[i] : numOfNeurons[i - 1], numOfNeurons[i], rand);

        }

        dWeights = new double[numOfNeurons.length][][];
        for (int i = 0; i < numOfNeurons.length; ++i) {
            dWeights[i] = new double[layers[i].getSize()][layers[i].getWeights(0).length];
        }

        gradients = new double[numOfNeurons.length][];
        for (int i = 0; i < numOfNeurons.length; ++i) {
            gradients[i] = new double[layers[i].getSize()];
        }
    }

    public MLPLayer getLayer(int i) {
        return layers[i];
    }

    /**
     * This method takes output vector of each layer, and feeds it into next
     * one.
     *
     * @param inputs
     * @return Returns the output of the last layer.
     */
    public double[] feedForward(double[] inputs) {
        double outputs[] = new double[inputs.length];

        for (int i = 0; i < layers.length; ++i) {
            outputs = layers[i].calculateLayerOutput(inputs);
            inputs = outputs;
        }

        return outputs;
    }

    /**
     * Calculates error signals for each node of the network. Signals for output
     * layer and hidden layers are evaluated separately.
     *
     * @param expected An array of expected outputs
     */
    public void SignalErrors(double[] expected) {

        for (int c = layers.length - 1; c >= 0; --c) {
            for (int i = 0; i < layers[c].getSize(); ++i) {
                
                //Output layer
                if (c == layers.length - 1) { 
                    gradients[c][i] = 2 * (layers[c].getOutput(i) - expected[0]) * layers[c].getDerivative(i);
                } else { //Hidden layers
                    double sum = 0;
                    for (int k = 1; k < layers[c + 1].getSize(); ++k) {
                        sum += layers[c + 1].getWeight(k, i) * gradients[c + 1][k];
                    }
                    gradients[c][i] = layers[c].getDerivative(i) * sum;
                }
            }
        }
    }

    /**
     * Squared error for certain training sample
     *
     * @param realOutput
     * @param expected
     * @return
     */
    private double error(double[] realOutput, double[] expected) {
        double d[];
        
        if (expected.length != realOutput.length) {
            d = new double[expected.length+1];
            for(int i = 0; i < expected.length; i++){
                d[i+1] = expected[i];
            }
            d[0] = 1;
        } else {
            d = expected;
        }


        double e = 0;
        for (int i = 0; i < realOutput.length; ++i) {
            e += Math.pow(realOutput[i] - d[i], 2);
        }

        return e;
    }

    /**
     * Evaluates overall error of the output. Algorithm tries to minimize the
     * output of this method.
     *
     * @param samples array of training samples
     * @param expected expected outputs for samples
     * @return returns mean squared error of the output
     */
    public double calculateError(double[][] samples, double[][] expected) {

        double e = 0;

        for (int i = 0; i < samples.length; ++i) {
            e += error(feedForward(samples[i]), expected[i]);
        }
        return e;
    }

    /**
     * Sets delta values to zero
     */
    private void resetDeltas() {
        for (int i = 0; i < layers.length; ++i) {
            for (int j = 0; j < layers[i].getSize(); ++j) {
                double weights[] = layers[i].getWeights(j);
                for (int k = 0; k < weights.length; ++k) {
                    dWeights[i][j][k] = 0;
                }
            }
        }
    }

    /**
     * Calculates delta (change) values for weights of each neuron.
     */
    private void evaluateDeltas() {

        for (int i = 1; i < layers.length; ++i) {
            for (int j = 0; j < layers[i].getSize(); ++j) {
                double weights[] = layers[i].getWeights(j);
                for (int k = 0; k < weights.length; ++k) {
                    dWeights[i][j][k] += gradients[i][j] * layers[i - 1].getOutput(k);
                }
            }
        }
    }

    /**
     * Updates weights of each node in the network. newWeigth = oldWeight -
     * learnRate*deltaWeight
     *
     * @param learnRate
     */
    private void updateWeights(double learnRate) {
        for (int i = 0; i < layers.length; ++i) {
            for (int j = 0; j < layers[i].getSize(); ++j) {
                double weights[] = layers[i].getWeights(j);
                for (int k = 0; k < weights.length; ++k) {
                    layers[i].setWeight(j, k, layers[i].getWeight(j, k) - (learnRate * dWeights[i][j][k]));
                }
            }
        }
    }

    /**
     * The actual backpropagation algorithm. For all training samples: 1.
     * calculate outputs 2. evaluate errors for all nodes 3. evaluate weight
     * changes
     *
     * Then update weights
     *
     * This is batched backpropagation -> it updates weigths after going through
     * ALL samples.
     *
     * @param samples set of sample inputs
     * @param expected set of expected outputs
     * @param learnRate
     */
    private void backpropagation(double[][] samples, double[][] expected, double learnRate) {
        resetDeltas();

        for (int i = 0; i < samples.length; ++i) {
            feedForward(samples[i]);
            SignalErrors(expected[i]);
            evaluateDeltas();
        }

        updateWeights(learnRate);
    }

    /**
     * Attempts to learn training samples with backpropagation based learning
     *
     * @param samples set of training samples
     * @param expected set of expected outputs
     * @param learnRate 
     * @param maxError Maximum acceptable value of error function
     * @param maxIterations
     */
    public void train(double[][] samples, double[][] expected, double learnRate, double maxError, int maxIterations) {

        double e = Double.POSITIVE_INFINITY;
        int numOfIterations = 0;

        while (e > maxError && numOfIterations < maxIterations) {

            backpropagation(samples, expected, learnRate);

            e = calculateError(samples, expected);
            numOfIterations++;
        }
    }

}
