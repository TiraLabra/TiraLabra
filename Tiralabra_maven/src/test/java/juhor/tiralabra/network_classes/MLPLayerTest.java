/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package juhor.tiralabra.network_classes;

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
public class MLPLayerTest {
    
    public MLPLayerTest() {
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
    public void testLayerOutputs(){
        MLPLayer layer = new MLPLayer(3, 2);
        
        double[] i1 = {1,1};
        double[] i2 = {0, 0};
        Vector in1 = new Vector(i1);
        Vector in2 = new Vector(i2);
        
        double[] out1 = layer.layerOutputs(in1).getAsArray();
        System.out.println(out1.length);
        double[] out2 = layer.layerOutputs(in2).getAsArray();
        
        for(int i = 0; i < out1.length; i++){
            MLPNeuron n = layer.getNeuron(i);
            double[] weights = n.getWeights();
            double out = 1/(1+Math.exp(-(weights[0]+weights[1])));

            assertTrue(out1[i] == out);
            assertTrue(out2[i] == 0.5);
        }
        
    }
}
