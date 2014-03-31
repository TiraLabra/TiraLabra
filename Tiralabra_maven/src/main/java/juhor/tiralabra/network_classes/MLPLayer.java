

package juhor.tiralabra.network_classes;

import java.util.Random;
import juhor.tiralabra.data_structures.Vector;

/**
 *
 * @author juhorim
 */
public class MLPLayer {
    
    MLPNeuron[] neurons;
    
    /**
     * Main constructor
     * @param numOfNeurons number of neurons in this layer
     * @param numOfInput number of input values (number of neurons in previous layer)
     */
    public MLPLayer(int numOfNeurons, int numOfInput){
        neurons = new MLPNeuron[numOfNeurons];
        
        Random rand = new Random();
        for(int i = 0; i < neurons.length; i++){
            neurons[i] = new MLPNeuron(numOfInput, rand);
        }
    }
    /**
     * This method creates a vector from outputs of neurons. 
     * @param input
     * @return 
     */
    public Vector layerOutputs(Vector input){
        Vector output = new Vector(neurons.length);
        for(int i = 0; i < neurons.length; i++){
            double val = neurons[i].calculateOutput(input);
            output.setValue(i, val);
        }
        
        return output;
    }
    
    
    
}
