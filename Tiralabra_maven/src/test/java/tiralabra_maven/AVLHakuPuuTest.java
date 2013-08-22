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
public class AVLHakuPuuTest {
    
    public AVLHakuPuuTest() {
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
        String expResult = "13{1,100}";
        instance.lisaaSolmu(u1);
        instance.lisaaSolmu(u2);
        instance.lisaaSolmu(u3);
        String result = instance.toString();
        
        assertEquals(expResult, result);
    }
    
    @Test
    public void testTasapainoisuus(){
        AVLHakuPuu avl = new AVLHakuPuu();
        for (int i = 0; i < 7; i++) {
            avl.lisaaSolmu(new Solmu((int)Math.random()*100));
        }
        boolean b = (avl.juuri.getKorkeus()<3);
        assertEquals(true, b);
    }

    /**
     * Test of poistaSolmu method, of class AVLHakuPuu.
     */
    @Test
    public void testPoistaSolmu() {
        System.out.println("poistaSolmu");
        int arvo = 5;
        AVLHakuPuu instance = new AVLHakuPuu();
        instance.lisaaSolmu(new Solmu(arvo));
        instance.lisaaSolmu(new Solmu(65));
        instance.lisaaSolmu(new Solmu(3));
        boolean expResult = true;
        boolean result = instance.poistaSolmu(arvo);
        assertEquals(expResult, result);   
    }

}