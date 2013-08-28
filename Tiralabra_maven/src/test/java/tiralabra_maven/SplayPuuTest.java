package tiralabra_maven;

import org.junit.Test;
import static org.junit.Assert.*;

public class SplayPuuTest {
    
    @Test
    public void testLisaaSolmu() {
        System.out.println("lisaaSolmu");
        Solmu u1 = new Solmu(21);
        Solmu u2 = new Solmu(12);
        Solmu u3 = new Solmu(23);
        SplayPuu instance = new SplayPuu();
        instance.lisaaSolmu(u1);
        instance.lisaaSolmu(u2);
        instance.lisaaSolmu(u3);
        assertEquals("23{21{12,[]},[]}", instance.toString());
    }

    @Test
    public void testPoistaSolmu() {
        System.out.println("poistaSolmu");
        Solmu u1 = new Solmu(50);
        Solmu u2 = new Solmu(43);
        Solmu u3 = new Solmu(63);
        SplayPuu instance = new SplayPuu();
        instance.lisaaSolmu(u1);
        instance.lisaaSolmu(u2);
        instance.lisaaSolmu(u3);
        boolean expResult = true;
        boolean result = instance.poistaSolmu(u2);
        assertEquals(expResult, result);
        assertEquals("50{[],63}", instance.toString());
    }

    @Test
    public void testHae() {
        System.out.println("hae");
        int i = 16;
        Solmu s = new Solmu(i);
        SplayPuu instance = new SplayPuu();
        instance.lisaaSolmu(s);
        for (int j = 0; j < 10; j++) {
            instance.lisaaSolmu(new Solmu(j));
        }
        Solmu expResult = s;
        Solmu result = instance.hae(i);
        assertEquals(expResult, result);
    }
}