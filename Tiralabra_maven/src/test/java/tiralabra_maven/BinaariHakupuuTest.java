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
public class BinaariHakupuuTest {
    
    public BinaariHakupuuTest() {
    }
    
    /**
     * Test of lisaaSolmu method, of class BinaariHakupuu.
     */
    @Test
    public void testLisaaSolmu() {
        System.out.println("lisaaSolmu");
        Solmu u1 = new Solmu(15);
        Solmu u2 = new Solmu(2);
        Solmu u3 = new Solmu(-100);
        BinaariHakupuu instance = new BinaariHakupuu();
        instance.lisaaSolmu(u1);
        instance.lisaaSolmu(u2);
        instance.lisaaSolmu(u3);
        String expResult = "15{2{-100,[]},[]}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of poistaSolmu method, of class BinaariHakupuu.
     */
    @Test
    public void testPoistaSolmu() {
        System.out.println("poistaSolmu");
        int i = 12;
        Solmu u1 = new Solmu(15);
        Solmu u2 = new Solmu(i);
        Solmu u3 = new Solmu(20);
        BinaariHakupuu instance = new BinaariHakupuu();
        instance.lisaaSolmu(u1);
        instance.lisaaSolmu(u2);
        instance.lisaaSolmu(u3);
        boolean expResult = true;
        boolean result = instance.poistaSolmu(i);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of hae method, of class BinaariHakupuu.
     */
    @Test
    public void testHae() {
        System.out.println("hae");
        int i = 28;
        Solmu u1 = new Solmu(31);
        Solmu u2 = new Solmu(12);
        Solmu u3 = new Solmu(i);
        BinaariHakupuu instance = new BinaariHakupuu();
        instance.lisaaSolmu(u1);
        instance.lisaaSolmu(u2);
        instance.lisaaSolmu(u3);
        Solmu expResult = u3;
        Solmu result = instance.hae(i);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class BinaariHakupuu.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        BinaariHakupuu instance = new BinaariHakupuu();
        Solmu u1 = new Solmu(1);
        Solmu u2 = new Solmu(2);
        instance.lisaaSolmu(u1);
        instance.lisaaSolmu(u2);
        String expResult = "1{[],2}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}