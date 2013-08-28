/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra_maven;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author esaaksvu
 */
public class PunMusPuuTest {
    
    public PunMusPuuTest() {
    }

    @Test
    public void testLisaaSolmu() {
        System.out.println("lisaaSolmu");
        Solmu uusi = new Solmu(4);
        PunMusPuu instance = new PunMusPuu();
        instance.lisaaSolmu(uusi);
        assertEquals(instance.toString(), "4(M){,}");
    }

    @Test
    public void testPoistaSolmu() {
        System.out.println("poistaSolmu");
        int i = 5;
        PunMusPuu instance = new PunMusPuu();
        instance.lisaaSolmu(new Solmu(i));
        boolean expResult = true;
        boolean result = instance.poistaSolmu(instance.hae(i));
        assertEquals(expResult, result);
    }

    @Test
    public void testToString() {
        System.out.println("toString");
        PunMusPuu instance = new PunMusPuu();
        instance.lisaaSolmu(new Solmu(4));
        instance.lisaaSolmu(new Solmu(8));
        String expResult = "4(M){,8(P){,}}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    @Test
    public void testHae() {
        System.out.println("hae");
        int i = 12;
        PunMusPuu instance = new PunMusPuu();
        Solmu s = new Solmu(i);
        instance.lisaaSolmu(s);
        for (int j = 0; j < 10; j++) {
            instance.lisaaSolmu(new Solmu(j));
        }
        Solmu expResult = s;
        Solmu result = instance.hae(i);
        assertEquals(expResult, result);
    }
}