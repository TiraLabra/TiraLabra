/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package juhor.tiralabra.data_structures;

/**
 * Class for easier training processes.
 * @author juhorim
 */
public class TrainingData {
    private double[][] vectors;
    private int[] outputs; 
    
    public TrainingData(double[][] datamatrix, int[] outputvalues){
        vectors = datamatrix;
        outputs = outputvalues;
    }
    
    public int getVectorDimensions(){
        return vectors[0].length;
    }
    
    public int getNumOfVectors(){
        return vectors.length;
    }
    
    public int getOutput(int index){
        return outputs[index];
    }
    
    public double[] getVector(int index){
        return vectors[index];
    }
}
