package juhor.tiralabra_maven;

import java.util.Arrays;
import juhor.tiralabra.data_structures.TrainingData;
import juhor.tiralabra.network_classes.Perceptron;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        double[][] inputVectors = {{0,0},{1,0},{0,1},{1,1}};
        boolean[] outputs = {false,false,false,true};
        TrainingData data = new TrainingData(inputVectors, outputs);
        Perceptron p = new Perceptron(data, 0, 1);
        p.learn();
        
        double[] w = p.getWeights();
        
        double[] vec  = {1,1};
        boolean troo = p.test(vec);
        System.out.println(Arrays.toString(w));
        System.out.println(troo);
        
    }
}
