
package com.mycompany.tiralabra_maven.tietorakenteet;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Markus
 */
public class AvlPuusolmuTest {
    
    public AvlPuusolmuTest() {
    }
    AvlPuusolmu s;
    

    @Before
    public void setUp() {
        s = new AvlPuusolmu(0);
    }

   
    @Test
    public void testOikea() {
        assertEquals(null, s.getOikea());
        AvlPuusolmu t = new AvlPuusolmu(0);
        s.setOikea(t);
        assertEquals(t, s.getOikea());
    }

   
    @Test
    public void testVasen() {
        assertEquals(null, s.getVasen());
        AvlPuusolmu t = new AvlPuusolmu(0);
        s.setVasen(t);
        assertEquals(t, s.getVasen());
    }

   
    @Test
    public void testVanhempi() {
        assertEquals(null, s.getVanhempi());
        AvlPuusolmu t = new AvlPuusolmu(0);
        s.setVanhempi(t);
        assertEquals(t, s.getVanhempi());
    }

    
    @Test
    public void testAvain() {
        assertEquals(0, s.getAvain());
        int arvo = 5;
        s.setAvain(arvo);
        assertEquals(arvo, s.getAvain());
    }
    
    @Test
    public void testKorkeus(){
        assertEquals(0, s.getKorkeus());
        int arvo = 5;
        s.setKorkeus(arvo);
        assertEquals(arvo, s.getKorkeus());
    }
}
