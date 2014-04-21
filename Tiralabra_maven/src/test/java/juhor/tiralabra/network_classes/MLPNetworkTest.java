/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juhor.tiralabra.network_classes;

import java.util.Random;
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
        int[] neurons = {3, 2, 1};

        net = new MLPNetwork(neurons);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testWithXor() {
        double[][] trainDat = {{0, 0}, {1, 0}, {0, 1}, {1, 1}};
        double[][] expected = {{0}, {1}, {1}, {0}};

        net.train(trainDat, expected, 0.3, 0.01, 100000);
        double[] i1 = {0, 0};
        double[] i2 = {1, 0};
        double[] i3 = {0, 1};
        double[] i4 = {1, 1};
        System.out.println();
        assertTrue(Math.round(net.feedForward(i1)[1]) == 0);
        assertTrue(Math.round(net.feedForward(i2)[1]) == 1);
        assertTrue(Math.round(net.feedForward(i3)[1]) == 1);
        assertTrue(Math.round(net.feedForward(i4)[1]) == 0);
    }

    @Test
    public void testWithOr() {
        double[][] trainDat = {{0, 0}, {1, 0}, {0, 1}, {1, 1}};
        double[][] expected = {{0}, {1}, {1}, {1}};

        net.train(trainDat, expected, 0.3, 0.01, 100000);
        double[] i1 = {0, 0};
        double[] i2 = {1, 0};
        double[] i3 = {0, 1};
        double[] i4 = {1, 1};
        System.out.println();
        assertTrue(Math.round(net.feedForward(i1)[1]) == 0);
        assertTrue(Math.round(net.feedForward(i2)[1]) == 1);
        assertTrue(Math.round(net.feedForward(i3)[1]) == 1);
        assertTrue(Math.round(net.feedForward(i4)[1]) == 1);
    }
    
    @Test
    public void testWithLargeRandomLinearlySeparableInput(){
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
        
        int[] nodes = {1, 3,1};
        MLPNetwork tron = new MLPNetwork(nodes);

        tron.train(inputs, outputs, 0.3, 0.01, 40000);
        for (int i = 0; i < 100; i++) {
            double r = 2 * rand.nextDouble() - 2; //random double from interval [-2, 2[
            double[] in = {1, r};
            if (r > 0.01) {
                assertFalse(tron.feedForward(in)[1] > 0.5);
            } else if (r < -0.01) { 
                assertTrue(tron.feedForward(in)[1] < 0.5);

            }
        }

    }
    
    
    @Test
    public void testWithLargeRandomNonSeparableInput(){
        
    }
    /**
    @Test
    public void testErrorEvaluation() {
        double[][] expected = {{0, 0}, {0, 0}, {0, 0}, {0, 0}};
        double[][] out = {{1, 2}, {3, 4}, {5, 6}, {7, 8}};

        double error = net.calculateError(expected, out);
        System.out.println(error);
        assertTrue(error == 159.01150024548167); // 1/2*(1^2+2^2+...+8^2)

        double[][] expected2 = {{1, 2}, {3, 4}};
        double[][] out2 = {{4, 3}, {2, 1}};
        
        error = net.calculateError(expected2, out2); // 1/2*((1-4)^2 + (2-3)^2 + (3-2)^2 + (4-1)^2)
        System.out.println(error);
        assertTrue(error == 10);

    }*/
    /**
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

        double[] out = net.feedForward(in);
        System.out.println(out[1]);
        assertTrue(out[1] == 0.8807711642648273);
    }*/
}
