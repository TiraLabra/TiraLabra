/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juhor.tiralabra.network_classes;

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
        double[][] inputVectors = {{1,0}, {1,1}};
        double[][] outputs = {{1}, {0}};
        TrainingData data = new TrainingData(inputVectors, outputs);
        Perceptron tron = new Perceptron(data, 0.5);

        tron.learn();
        double[] input1 = {1,1};
        double[] input2 = {1,0};
        assertFalse(tron.test(input1));
        assertTrue(tron.test(input2));
    }
    

}
