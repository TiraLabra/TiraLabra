/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package juhor.tiralabra.network_classes;

import juhor.tiralabra.data_structures.TrainingData;

/**
 *
 * @author juhorim
 */
public class Perceptron {
    
    private double[] weights;
    private TrainingData trainingData;
    private double bias;
    private double threshold;
    
    public Perceptron(TrainingData data, double b, double th){
        trainingData = data;
        weights = new double[trainingData.getVectorDimensions()];
        bias = b;
        threshold = th;
    }
    
    public void learn(){
        boolean ready = false;
        while(!ready){
            int errors = 0;
            for(int i = 0; i < trainingData.getNumOfVectors(); i++){
                double[] vec = trainingData.getVector(i);
                double z = dotProduct(vec)+bias;
                boolean out = classify(z);
                
                if(out && !trainingData.getOutput(i)){
                    vecSubstraction(vec);
                    errors++;
                }else if(!out && trainingData.getOutput(i)){
                    vecAddition(vec);
                    errors++;
                }
            }
            if(errors == 0){
                ready = true;
            }
        }
    }
    
    public double[] getWeights(){
        return weights;
    }
    
    public boolean test(double[] input){
        return classify(dotProduct(input));
    }
    
    public boolean classify(double d){
        if(d > threshold){
            return true;
        }else{
            return false;
        }
    }
    
    public void vecAddition(double[] vec){
        for(int i = 0; i < weights.length; i++){
            weights[i] = weights[i] + vec[i];
        }
    }
    
    public void vecSubstraction(double[] vec){
        for(int i = 0; i< weights.length; i++){
            weights[i] = weights[i] - vec[i];
        }
    }
    
    public double dotProduct(double[] vec){
        double product = 0;
        
        for(int i = 0; i < weights.length; i++){
            product = product + weights[i]*vec[i];
        }
        return product;
    }
}
