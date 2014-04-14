/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juhor.tiralabra.network_classes;

import java.util.Random;
import juhor.tiralabra.data_structures.Vector;
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
public class MLPNeuronTest {

    public MLPNeuronTest() {
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
    public void testOutputWithZeros() {
        MLPNeuron n = new MLPNeuron(5, new Random());

        double[] in = {0, 0, 0, 0, 0};
        Vector v = new Vector(in);

        assertTrue(n.calculateOutput(v) == 0.5);
    }

    @Test
    public void testOutputWithWeightsOne() {
        MLPNeuron n = new MLPNeuron(5, new Random());

        for (int i = 0; i < 5; i++) {
            n.setWeight(i, 1);
        }

        double[] in = {1, 2, 3, 4, 5};
        Vector v = new Vector(in);

        assertTrue(n.calculateOutput(v) == 0.999999694097773);
    }

    @Test
    public void testOutputValueGreaterThanZero() {
        Random rand = new Random();

        for (int i = 0; i < 100; i++) {
            int size = rand.nextInt(1000);
            MLPNeuron n = new MLPNeuron(size, rand);
            double[] r = new double[size];
            for (int j = 0; j < size; j++) {
                r[j] = 100 * rand.nextDouble() - 100;
            }
            Vector v = new Vector(r);
            assertTrue(n.calculateOutput(v) >= 0);
        }

    }

    @Test
    public void testOutputValueLessThanOne() {
        Random rand = new Random();

        for (int i = 0; i < 100; i++) {
            int size = rand.nextInt(1000);
            MLPNeuron n = new MLPNeuron(size, rand);
            double[] r = new double[size];
            for (int j = 0; j < size; j++) {
                r[j] = 100 * rand.nextDouble() - 100;
            }
            Vector v = new Vector(r);
            assertTrue(n.calculateOutput(v) <= 1);
        }
    }
}
