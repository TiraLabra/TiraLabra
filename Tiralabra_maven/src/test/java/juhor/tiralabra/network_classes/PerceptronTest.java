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
public class PerceptronTest {

    public PerceptronTest() {
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
    public void testWithOr() {
        double[][] inputVectors = {{1, 0, 0}, {1, 1, 0}, {1, 0, 1}, {1, 1, 1}};
        double[][] outputs = {{0}, {1}, {1}, {1}};
        TrainingData data = new TrainingData(inputVectors, outputs);
        Perceptron tron = new Perceptron(data, 0.5);

        tron.learn();
        double[] input1 = {1, 0, 0};
        double[] input2 = {1, 1, 0};
        double[] input3 = {1, 0, 1};
        double[] input4 = {1, 1, 1};

        assertFalse(tron.test(input1));
        assertTrue(tron.test(input2));
        assertTrue(tron.test(input3));
        assertTrue(tron.test(input4));
    }

    @Test
    public void testWithAnd() {
        double[][] inputVectors = {{1, 0, 0}, {1, 1, 0}, {1, 0, 1}, {1, 1, 1}};
        double[][] outputs = {{0}, {0}, {0}, {1}};
        TrainingData data = new TrainingData(inputVectors, outputs);
        Perceptron tron = new Perceptron(data, 0.5);

        tron.learn();
        double[] input1 = {1, 0, 0};
        double[] input2 = {1, 1, 0};
        double[] input3 = {1, 0, 1};
        double[] input4 = {1, 1, 1};

        assertFalse(tron.test(input1));
        assertFalse(tron.test(input2));
        assertFalse(tron.test(input3));
        assertTrue(tron.test(input4));
    }

    @Test
    public void testWithNot() {
        double[][] inputVectors = {{1, 0}, {1, 1}};
        double[][] outputs = {{1}, {0}};
        TrainingData data = new TrainingData(inputVectors, outputs);
        Perceptron tron = new Perceptron(data, 0.5);

        tron.learn();
        double[] input1 = {1, 1};
        double[] input2 = {1, 0};
        assertFalse(tron.test(input1));
        assertTrue(tron.test(input2));
    }

    @Test
    public void testWithLargeRandomDataSets() {
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

        tron.learn();
        for (int i = 0; i < 100; i++) {
            double r = 2 * rand.nextDouble() - 2; //random double from interval [-2, 2[
            double[] in = {1, r};
            if (r > 0.01) {
                assertFalse(tron.test(in));
            } else if (r < -0.01) { 
                assertTrue(tron.test(in));

            }
        }

    }

}
