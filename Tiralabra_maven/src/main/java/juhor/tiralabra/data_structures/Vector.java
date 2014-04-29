/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package juhor.tiralabra.data_structures;

/**
 * Class containing all the necessary vector operations; addition, substraction, scalar product etc.
 * @author juhorim
 */
public class Vector {
    private double[] values;
    
    public Vector(double[] v){
        values = v;
    }
    
    public Vector(int size){
        values = new double[size];
        
    }
    
    public int getSize(){
        return values.length;
    }
    
    public double getValue(int index){
        return values[index];
    }
    
    public double[] getAsArray(){
        return values;
    }
    
    public void setValue(int i, double val){
        values[i] = val;
    }
    
    /**
     * Vector addition: [x_1, x_2,..., x_n] + [y_1,..., y_n] = [x_1 + y_1,..., x_n + y_n]
     * @param v 
     */
    public void add(Vector v){
        for(int i = 0; i < values.length; i++){
            values[i] = values[i] + v.getValue(i);
        }
    }
    /**
     * Vector substraction
     * @param v 
     */
    public void substract(Vector v){
        for(int i = 0; i < values.length; i++){
            values[i] = values[i] - v.getValue(i);
        }
    }
    
    /**
     * Scalar, or dot product of two vectors. 
     * @param v 
     * @return double value of sum(x_1*y_1 + ... + x_n*y_n);
     */
    
    public double dotProduct(Vector v){
        double product = 0;
        
        for(int i = 0; i < values.length; i++){
            product = product + values[i]*v.getValue(i);
        }
        
        return product;
    }
    
    public void multiplyByDouble(double d){
        for(int i = 0; i < values.length; i++){
            values[i] = values[i]*d;
        }
    }
}
