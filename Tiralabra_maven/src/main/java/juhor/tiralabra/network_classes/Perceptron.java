/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package juhor.tiralabra.network_classes;

import juhor.tiralabra.data_structures.TrainingData;
import juhor.tiralabra.data_structures.Vector;

/**
 * Single layer binary perceptron. Classifies data into two sets. 
 * @author juhorim
 */
public class Perceptron {
    
    Vector weights;
    TrainingData trainingData;
    double threshold;
    
    /**
     * The main constructor 
     * @param data TrainingData used to train the perceptron
     * @param th 
     */
    public Perceptron(TrainingData data, double th){
        trainingData = data;
        weights = new Vector(trainingData.getVectorDimensions());
        threshold = th;
    }
    
    /**
     * Learning; We calculate the dot product for each datapoint with the weight vector, and 
     * determine if data point belongs to the set of true answers. 
     * The perceptron shuffles through the training data until it successfully classifies
     * all the datapoints to correct classes. If the training data isn't linearly separable this method will
     * run endlessly.
     */
    public void learn(){
        boolean ready = false;
        
        //repeat until every datapoint is classified correctly
        while(!ready){
            int errors = 0;
            
            for(int i = 0; i < trainingData.getNumOfVectors(); i++){
                Vector vec = new Vector(trainingData.getVector(i));
                //for every vector in training data, calculate the scalar product, and check if get "true" or "false" value
                double z = weights.dotProduct(vec);
                boolean out = classify(z);
                
                if(out && trainingData.getOutput(i)[0] == 0){
                    weights.substract(vec);
                    errors++;
                }else if(!out && trainingData.getOutput(i)[1] == 1){
                    weights.add(vec);
                    errors++;
                }
            }
            //No errors -> we're done
            if(errors == 0){
                ready = true;
            }
        }
    }
    
    public Vector getWeights(){
        return weights;
    }
    
    /**
     * After learning we can give vectors to the perceptron to classify them.
     * 
     * @param input
     * @return 
     */
    public boolean test(double[] input){
        return classify(weights.dotProduct(new Vector(input)));
    }
    
    /**
     * Method is used to check if the value of the dot product higher than the threshold, if it is,
     * input is classified into "true" category.
     * 
     * @param d scalar product of the vector being classified and weight vector.
     * @return boolean value which determines if the vector under our inspection belongs to the set of "true" or "false" values.
     */
    public boolean classify(double d){
        if(d > threshold){
            return true;
        }else{
            return false;
        }
    }
}
