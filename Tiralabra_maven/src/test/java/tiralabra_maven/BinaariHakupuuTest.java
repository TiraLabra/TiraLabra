package tiralabra_maven;

import org.junit.Test;
import static org.junit.Assert.*;

public class BinaariHakupuuTest {
    
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
        instance.poistaSolmu(u2);
        Solmu expResult = null;
        Solmu result = instance.hae(u2.getArvo());
        assertEquals(expResult, result);
        
    }

    @Test
    public void testPoistaSolmu2() {
        System.out.println("poistaSolmu2");
        int i = 12;
        Solmu u1 = new Solmu(15);
        Solmu u2 = new Solmu(i);
        Solmu u3 = new Solmu(20);
        BinaariHakupuu instance = new BinaariHakupuu();
        instance.lisaaSolmu(u1);
        instance.lisaaSolmu(u2);
        instance.lisaaSolmu(u3);
        instance.poistaSolmu(u2);
        String expResult = "15{[],20}";
        String result = instance.toString();
        assertEquals(expResult, result);
        
    }
    

    
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

    @Test
    public void testMin() {
        System.out.println("min");
        BinaariHakupuu instance = new BinaariHakupuu();
        Solmu s = new Solmu(13);
        instance.lisaaSolmu(s);
        for (int i = 0; i < 10; i++) {
            instance.lisaaSolmu(new Solmu(i));
        }
        Solmu expResult = instance.hae(0);
        Solmu result = instance.min(s);
        assertEquals(expResult, result);
    }
}