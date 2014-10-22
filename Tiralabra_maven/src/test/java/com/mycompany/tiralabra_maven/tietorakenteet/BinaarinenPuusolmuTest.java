
package com.mycompany.tiralabra_maven.tietorakenteet;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Markus
 */
public class BinaarinenPuusolmuTest {
     BinaarinenPuusolmu s;
    
    public BinaarinenPuusolmuTest() {
    }
    
    @Before
    public void setUp() {
        s = new BinaarinenPuusolmu(0);
    }

   
    @Test
    public void testOikea() {
        assertEquals(null, s.getOikea());
        BinaarinenPuusolmu t = new BinaarinenPuusolmu(0);
        s.setOikea(t);
        assertEquals(t, s.getOikea());
    }

   
    @Test
    public void testVasen() {
        assertEquals(null, s.getVasen());
        BinaarinenPuusolmu t = new BinaarinenPuusolmu(0);
        s.setVasen(t);
        assertEquals(t, s.getVasen());
    }

   
    @Test
    public void testVanhempi() {
        assertEquals(null, s.getVanhempi());
        BinaarinenPuusolmu t = new BinaarinenPuusolmu(0);
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

    
}
