

package juhor.tiralabra.network_classes;

import java.util.Random;
import juhor.tiralabra.data_structures.Vector;

/**
 * This is the basic component for our Multilayer Perceptron (MLP) neural network.
 * 
 * @author juhorim
 */
public class MLPNeuron {
    private Vector weights;
    Vector lastDeltas;
    
    /**
     * Main constructor. Initializes weight vector with small random values.
     * @param size
     * @param rand 
     */
    public MLPNeuron(int size, Random rand){
        double[] values = new double[size];
        lastDeltas = new Vector(size);
        for(int i = 0; i < values.length; i++){
            values[i] = 2*rand.nextDouble()-1;
        }
        weights = new Vector(values);
    }
    
    public double[] getWeights(){
        return weights.getAsArray();
    }
    
    public double getWeight(int i){
        return weights.getValue(i);
    }
    
    public void addToWeigths(Vector v){
        weights.add(v);
    }
    
    public void setWeight(int i, double d){
        weights.setValue(i, d);
    }
    
    /**
     * Output for input vector will be dot product with weight vector fed into logistic function
     * 
     * @param input the input vector
     * @return f(sum(x_1*y_1,..., x_n*y_n)) where f is logistic function
     */
    public double calculateOutput(Vector input){
        double dproduct = weights.dotProduct(input);
        double activation =sigmoid(dproduct);
        return activation;
    }
    
    private double sigmoid(double d){
        return 1/(1+Math.exp(-d));
    }
    
    
    
    
    
}
