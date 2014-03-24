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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testVectorAddition() {
        double[][] vectors = {{1, 1, 1}, {0, 0, 0}};
        boolean[] outs = {true, true};
        TrainingData td = new TrainingData(vectors, outs);
        Perceptron p = new Perceptron(td, 0, 0);

        double[] vec1 = {1, 1, 1};
        double[] vec2 = {0.5, 2, 3};

        p.vecAddition(vec1);

        double[] weights = p.getWeights();

        assertTrue(weights[0] == 1);
        assertTrue(weights[1] == 1);
        assertTrue(weights[2] == 1);

        p.vecAddition(vec2);
        weights = p.getWeights();
        
        assertTrue(weights[0] == 1.5);
        assertTrue(weights[1] == 3);
        assertTrue(weights[2] == 4);

    }

    @Test
    public void testVectorSubstraction() {
        double[][] vectors = {{1, 1, 1}, {0, 0, 0}};
        boolean[] outs = {true, true};
        TrainingData td = new TrainingData(vectors, outs);
        Perceptron p = new Perceptron(td, 0, 0);
        
        double[] vec1 = {1, 2, 3};
        double[] vec2 = {-3, -4, -5};
        
        p.vecSubstraction(vec1);
        double[] weights = p.getWeights();
        
        assertTrue(weights[0] == -1);
        assertTrue(weights[1] == -2);
        assertTrue(weights[2] == -3);
        
        p.vecSubstraction(vec2);
        weights = p.getWeights();
        
        assertTrue(weights[0] == 2);
        assertTrue(weights[1] == 2);
        assertTrue(weights[2] == 2);

    }

    @Test
    public void testDotProduct() {
        double[][] vectors = {{1, 1, 1}, {0, 0, 0}};
        boolean[] outs = {true, true};
        TrainingData td = new TrainingData(vectors, outs);
        Perceptron p = new Perceptron(td, 0, 0);

        double[] vec1 = {2, 3, 1};
        double d = p.dotProduct(vec1);

        assertTrue(d == 0);

        p.vecAddition(vec1);

        double[] vec2 = {1, 2, 3};

        d = p.dotProduct(vec2);

        assertTrue(d == 11);
    }

}
