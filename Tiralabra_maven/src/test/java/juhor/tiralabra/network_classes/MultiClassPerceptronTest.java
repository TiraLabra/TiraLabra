/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juhor.tiralabra.network_classes;

import java.util.Random;
import juhor.tiralabra.data_structures.TrainingData;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juhorim
 */
public class MultiClassPerceptronTest {

    public MultiClassPerceptronTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testWithAnd() { //Making sure that MC-perceptron works also with binary classes
        double[][] inputVectors = {{1, 0, 0}, {1, 0, 1}, {1, 1, 0}, {1, 1, 1}};
        double[][] outputs = {{0}, {0}, {0}, {1}};
        TrainingData data = new TrainingData(inputVectors, outputs);

        MultiClassPerceptron tron = new MultiClassPerceptron(data, 2);
        tron.learn();

        double[] in1 = {1, 0, 0};
        double[] in2 = {1, 1, 0};
        double[] in3 = {1, 0, 1};
        double[] in4 = {1, 1, 1};

        assertTrue(tron.classify(in1) == 0);
        assertTrue(tron.classify(in2) == 0);
        assertTrue(tron.classify(in3) == 0);
        assertTrue(tron.classify(in4) == 1);

    }

    @Test
    public void testWithFourClassesAndRandomTrainingSets() {
        Random rand = new Random();
        double[][] outputs = new double[1000][1];

        //class 0, 100 random points around (1,1)
        double[][] class0 = new double[250][3];
        for (int i = 0; i < class0.length; i++) {
            double x = 0.001 + 2 * rand.nextDouble();
            double y = 0.001 + 2 * rand.nextDouble();
            double[] xy = {1, x, y};
            class0[i] = xy;
            outputs[i][0] = 0;
        }

        //class 1, 100 random points around (1,-1}
        double[][] class1 = new double[250][3];
        for (int i = 0; i < class1.length; i++) {
            double x = 0.001 + 2 * rand.nextDouble();
            double y = -0.001 - 2 * rand.nextDouble();
            double[] xy = {1, x, y};
            class1[i] = xy;
            outputs[250 + i][0] = 1;
        }

        //class 2, 100 random points around (-1, 1);
        double[][] class2 = new double[250][3];
        for (int i = 0; i < class2.length; i++) {
            double x = -0.001 - 2 * rand.nextDouble();
            double y = 0.001 + 2 * rand.nextDouble();
            double[] xy = {1, x, y};
            class2[i] = xy;
            outputs[500 + i][0] = 2;
        }

        //class 3, 100 random points around (-1,-1);
        double[][] class3 = new double[250][3];
        for (int i = 0; i < class3.length; i++) {
            double x = -0.001 - 2 * rand.nextDouble();
            double y = -0.001 - 2 * rand.nextDouble();
            double[] xy = {1, x, y};
            class3[i] = xy;
            outputs[750 + i][0] = 3;
        }

        double[][] inputs = new double[1000][3];

        System.arraycopy(class0, 0, inputs, 0, class0.length);
        System.arraycopy(class1, 0, inputs, class0.length, class1.length);
        System.arraycopy(class2, 0, inputs, class0.length + class1.length, class2.length);
        System.arraycopy(class3, 0, inputs, class0.length + class1.length + class2.length, class3.length);

        TrainingData data = new TrainingData(inputs, outputs);

        MultiClassPerceptron tron = new MultiClassPerceptron(data, 4);
        tron.learn();
        
        for (int i = 0; i < 100; i++) { //let's test with 100 random input vectors
            //pick a random point from R^2 plane
            double x = 2 * rand.nextDouble() - 2;
            double y = 2 * rand.nextDouble() - 2;
            double[] in = {1, x, y};

            if (x > 0.01 && y > 0.01) {
                assertTrue(tron.classify(in) == 0);
            } else if (x > 0.01 && y < -0.01) {
                assertTrue(tron.classify(in) == 1);
            } else if (x < -0.01 && y > 0.01) {
                assertTrue(tron.classify(in) == 2);
            } else if(x < -0.01 && y < -0.01){
                assertTrue(tron.classify(in) == 3);
            }
        }

        double[] in1 = {1, 0.5, 0.5};
        double[] in2 = {1, 0.5, -0.5};
        double[] in3 = {1, -0.5, 0.5};
        double[] in4 = {1, -0.5, -0.5};

        assertTrue(tron.classify(in1) == 0);
        assertTrue(tron.classify(in2) == 1);
        assertTrue(tron.classify(in3) == 2);
        assertTrue(tron.classify(in4) == 3);
    }

}
