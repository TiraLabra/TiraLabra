package juhor.tiralabra.ui;

import java.util.Arrays;
import java.util.Random;
import juhor.tiralabra.data_structures.TrainingData;
import juhor.tiralabra.data_structures.Vector;
import juhor.tiralabra.network_classes.MLPLayer;
import juhor.tiralabra.network_classes.MLPNetwork;
import juhor.tiralabra.network_classes.MultiClassPerceptron;
import juhor.tiralabra.network_classes.Perceptron;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {

        //Testataan perseptronin toimintaa oppimalla AND-operaatio:
        double[][] inputVectors = {{1, 0, 0}, {1, 1, 0}, {1, 0, 1}, {1, 1, 1}};
        double[][] outputs = {{0}, {0}, {0}, {1}};
        TrainingData data = new TrainingData(inputVectors, outputs);
        int[] numofneurons = {2, 1};
        MLPNetwork nw = new MLPNetwork(2, numofneurons, 0.9, 1000, 0.1, data);
        nw.learn();

        double[] vec = {1, 0, 0};
        Vector v = new Vector(vec);
        double troo = nw.BinaryClassify(vec);

        System.out.println(troo); //tulostetaan tulos

    }
}
