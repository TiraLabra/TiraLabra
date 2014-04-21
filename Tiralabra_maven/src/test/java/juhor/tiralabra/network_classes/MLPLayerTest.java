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
    public void testLayerOutputs() {
        MLPLayer layer = new MLPLayer(2, 2, new Random());

        double[] i1 = {1, 1};
        double[] i2 = {0, 0};
        Vector in1 = new Vector(i1);
        Vector in2 = new Vector(i2);

        double[] out1 = layer.calculateLayerOutput(i1);
        double[] out2 = layer.calculateLayerOutput(i2);
        
        assertTrue(out1[0] == 1);
        assertTrue(out2[0] ==1);
        
    }
}
