/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra_maven;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author esaaksvu
 */
public class AVLHakuPuuTest {
    
    public AVLHakuPuuTest() {
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

    /**
     * Test of lisaaSolmu method, of class AVLHakuPuu.
     */
    @Test
    public void testLisaaSolmu() {
        System.out.println("lisaaSolmu");
        Solmu u1 = new Solmu(1);
        Solmu u2 = new Solmu(13);
        Solmu u3 = new Solmu(100);
        AVLHakuPuu instance = new AVLHakuPuu();
        String expResult = "13{1{null,null},100{null,null}}";
        instance.lisaaSolmu(u1);
        instance.lisaaSolmu(u2);
        instance.lisaaSolmu(u3);
        String result = instance.toString();
        
        assertEquals(expResult, result);
    
    }

    /**
     * Test of poistaSolmu method, of class AVLHakuPuu.
     */
    @Test
    public void testPoistaSolmu() {
        System.out.println("poistaSolmu");
        int arvo = 0;
        AVLHakuPuu instance = new AVLHakuPuu();
        boolean expResult = false;
        boolean result = instance.poistaSolmu(arvo);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of max method, of class AVLHakuPuu.
     */
    @Test
    public void testMax() {
        System.out.println("max");
        int i = 100;
        int j = 1;
        AVLHakuPuu instance = new AVLHakuPuu();
        int expResult = 100;
        int result = instance.max(i, j);
        assertEquals(expResult, result);
        
    }
}