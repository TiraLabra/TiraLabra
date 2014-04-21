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

        Random rand = new Random();

        double[][] outputs = new double[1000][1];

        //class 0: 500 random doubles from interval [0.001,2[ 
        double[][] class0 = new double[500][2];
        for (int i = 0; i < class0.length; i++) {
            double x = 0.001 + 2 * rand.nextDouble();
            double[] xset = {1, x};
            class0[i] = xset;
            outputs[i][0] = 0;
        }

        //class 1: 500 random doubles from interval ]-2, -0.001]
        double[][] class1 = new double[500][2];
        for (int i = 0; i < class1.length; i++) {
            double x = -0.001 - 2 * rand.nextDouble();
            double[] xset = {1, x};
            class1[i] = xset;
            outputs[500 + i][0] = 1;
        }

        double[][] inputs = new double[1000][2];
        System.arraycopy(class0, 0, inputs, 0, class0.length);
        System.arraycopy(class1, 0, inputs, class0.length, class1.length);

        TrainingData data = new TrainingData(inputs, outputs);

        Perceptron tron = new Perceptron(data, 0.5);
        long start = System.currentTimeMillis();
        tron.learn();
        long stop = System.currentTimeMillis();

        System.out.println(stop - start);

    }
}
