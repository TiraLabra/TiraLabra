package juhor.tiralabra.network_classes;

import juhor.tiralabra.data_structures.TrainingData;
import juhor.tiralabra.data_structures.Vector;

/**
 * This class will take care of actual backpropagation based learning
 *
 * @author juhorim
 */
public class MLPNetwork {

    private MLPLayer[] layers;
    private int numOfIterations;
    private TrainingData trainingData;
    private double learningRate;
    private double minError;

    public MLPNetwork(int numOfLayers, int[] numOfNeurons, double learnrate, int iterations, double minimum, TrainingData data) {
        layers = new MLPLayer[numOfLayers];
        numOfIterations = iterations;
        learningRate = learnrate;
        minError = minimum;
        trainingData = data;
        layers[0] = new MLPLayer(numOfNeurons[0], data.getVectorDimensions());
        for (int i = 1; i < numOfLayers; i++) {
            
            layers[i] = new MLPLayer(numOfNeurons[i], numOfNeurons[i - 1]);
        }
    }
    
    public MLPLayer getLayer(int i){
        return layers[i];
    }

    /**
     * This method takes output vector of each layer, and feeds it into next
     * one.
     *
     * @param input
     * @return Returns the output of the last layer.
     */
    public Vector feedForward(Vector input) {
        Vector last = input;
        for (int i = 0; i < layers.length; i++) {

            last = layers[i].layerOutputs(last);
        }
        return last;
    }

    /**
     * Calculates error signals for each node of the network. Signals for output layer and hidden layers
     * are evaluated separately. 
     * 
     * @param expected An array of expected outputs
     */
    public void SignalErrors(double[] expected) {

        //output layer
        for (int i = 0; i < layers[layers.length-1].errorSignals.length; i++) {
            double out = layers[layers.length-1].outputs.getValue(i);
            layers[layers.length-1].errorSignals[i] = (expected[i] - out) * out * (1 - out);
        }

        //hidden layers
        for (int i = layers.length - 2; i >= 0; i--) {
            for (int j = 0; j < layers[i].getSize(); j++) {
                double sum = 0;
                for (int k = 0; k < layers[i + 1].getSize(); k++) {
                    sum = sum + layers[i + 1].getNeuron(k).getWeight(j) * layers[i + 1].errorSignals[k];
                }
                layers[i].errorSignals[j] = layers[i].outputs.getValue(j) * (1 - layers[i].outputs.getValue(j) * sum);
            }
        }
    }
    
    /**
     * Evaluates overall error of the output. Algorithm tries to minimize the output of this method.
     * @param expected 2d-array of expected outputs for the set of datapoints
     * @param out 2d-array of outputs calculated by the algorithm
     * @return returns mean squared error of the output
     */
    public double calculateError(double[][] expected, double[][] out) {
        
        
        double error = 0;
        for (int j = 0; j < expected.length; j++) {
            double sum = 0;
            for (int i = 0; i < expected[0].length; i++) {
                sum = sum+ Math.pow(expected[j][i] - out[j][i], 2);
                
            }
            error = error+ 0.5*sum;
        }
        return error;
    }

    /**
     * Updates the weights of each node in the network.
     */
    public void BackPropagation() {
        for (int i = layers.length - 1; i >= 0; i--) {
            
        double[] weightDeltas = new double[layers[i].numberOfInputs];
            for (int j = 0; j < layers[i].getSize(); j++) {

                for (int k = 0; k < layers[i].numberOfInputs; k++) {
                    
                    weightDeltas[k] = learningRate * layers[i].errorSignals[j] * layers[i].inputs.getValue(k)+ 0.01*layers[i].getNeuron(j).lastDeltas.getValue(k);

                }
                Vector deltas = new Vector(weightDeltas);
                layers[i].getNeuron(j).lastDeltas = deltas;
                layers[i].getNeuron(j).addToWeigths(deltas);
            }
        }

    }
    /**
     * Attempts to learn the training data using backpropagation based learning.
     */
    public void learn() {
        int i = 0;
        double error = Double.POSITIVE_INFINITY;
        while (error > minError && i < numOfIterations) {
            
            double[][] allOutputs = new double[trainingData.getNumOfVectors()][trainingData.getOutput(0).length];
            for (int sampNum = 0; sampNum < trainingData.getNumOfVectors(); sampNum++) {
                Vector v = new Vector(trainingData.getVector(sampNum));
                feedForward(v);
                allOutputs[sampNum] = layers[layers.length-1].outputs.getAsArray();
                SignalErrors(trainingData.getOutput(sampNum));
                BackPropagation();
                
            }
            i++;
            error = calculateError(trainingData.getAllOutputs(), allOutputs);
            
        }
    }
    /**
     * This method tries to classify the input vector into either "true" or "false" group.
     * @param input
     * @return 
     */
    public double BinaryClassify(double[] input){
        Vector in = new Vector(input);
        Vector v = feedForward(in);
        return (double) v.getValue(0);
    }

}
