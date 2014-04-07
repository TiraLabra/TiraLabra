

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
    
    
    public MLPNetwork(int numOfLayers, int[] numOfNeurons, double learnrate, int iterations, double minimum, TrainingData data){
        layers = new MLPLayer[numOfLayers];
        numOfIterations = iterations;
        learningRate = learnrate;
        minError = minimum;
        trainingData = data;
        
        for(int i = 0; i < numOfLayers; i++){
            layers[i] = new MLPLayer(numOfNeurons[i], numOfNeurons[i-1]);
        }
    }
    
    /**
     * This method takes output vector of each layer, and feeds it into next one.
     * 
     * @param input
     * @return Returns the output of the last layer.
     */
    public Vector feedForward(Vector input){
        Vector last = input; 
        for(int i = 0; i < layers.length; i++){
            
            last = layers[i].layerOutputs(last);    
        }
        return last;  
    }
    
    public void SignalErrors(double[] expected, double[] output){
        double sum;
        //output layer:
        for(int i = 0; i < layers[0].errorSignals.length; i++){
            layers[0].errorSignals[i] = (expected[i]-output[i])*output[i]*(1-output[i]);
        }
    }
    
}
