package juhor.tiralabra.ui;

import java.util.Arrays;
import juhor.tiralabra.data_structures.TrainingData;
import juhor.tiralabra.network_classes.MultiClassPerceptron;
import juhor.tiralabra.network_classes.Perceptron;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
        //Testataan perseptronin toimintaa oppimalla AND-operaatio:
        
        double[][] inputVectors = {{1,0,0},{1,1,0},{1,0,1},{1,1,1}};
        int[] outputs = {1,1,1,0};
        TrainingData data = new TrainingData(inputVectors, outputs);
        MultiClassPerceptron p = new MultiClassPerceptron(data,2);
        p.learn();
        
        
        double[] vec  = {1,1,1};
        int troo = p.classify(vec);
        System.out.println(troo); //tulostetaan tulos
        
    }
}
