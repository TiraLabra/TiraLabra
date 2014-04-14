/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juhor.tiralabra.network_classes;

import juhor.tiralabra.data_structures.TrainingData;
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
public class MLPNetworkTest {

    MLPNetwork net;

    public MLPNetworkTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        double[][] inputVectors = {{1, 0, 0}, {1, 0, 1}, {1, 1, 0}, {1, 1, 1}};
        double[][] outputs = {{0}, {0}, {0}, {1}};
        TrainingData data = new TrainingData(inputVectors, outputs);
        int[] neurons = {2, 1};

        net = new MLPNetwork(2, neurons, 0.4, 1, 0.5, data);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testErrorEvaluation() {
        double[][] expected = {{0, 0}, {0, 0}, {0, 0}, {0, 0}};
        double[][] out = {{1, 2}, {3, 4}, {5, 6}, {7, 8}};

        double error = net.calculateError(expected, out);

        assertTrue(error == 102); // 1/2*(1^2+2^2+...+8^2)

        double[][] expected2 = {{1, 2}, {3, 4}};
        double[][] out2 = {{4, 3}, {2, 1}};

        error = net.calculateError(expected2, out2); // 1/2*((1-4)^2 + (2-3)^2 + (3-2)^2 + (4-1)^2)
        assertTrue(error == 10);

    }

    @Test
    public void testFeedforwarding() {
        for (int i = 0; i < 2; i++) {
            MLPLayer l = net.getLayer(i);
            for (int j = 0; j < l.getSize(); j++) {
                MLPNeuron n = l.getNeuron(j);
                for (int k = 0; k < n.getWeights().length; k++) {
                    n.setWeight(k, 1);
                }
            }
        }

        double[] in = {2, 3, 4};
        
        Vector input = new Vector(in);
        
        Vector out = net.feedForward(input);
        
        assertTrue(out.getAsArray()[0] == 0.8807711642648273);
    }
}
