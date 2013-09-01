
package com.mycompany.tiralabra_maven.suorituskykytestit;

import static org.junit.Assert.*;
import org.junit.Test;

public class SuorituskykytyokalutTest {
    
    @Test
    public void testKeskiarvo() {
        long[] luvut = { 1, 2, 3 };
        assertEquals(2.0, Suorituskykytyokalut.keskiarvo(luvut), 0.01);
    }

    @Test
    public void testPienin() {
        long[] luvut = { 1, 2, 3 };
        assertEquals(1, Suorituskykytyokalut.pienin(luvut));
    }

    @Test
    public void testSuurin() {
        long[] luvut = { 1, 2, 3 };
        assertEquals(3, Suorituskykytyokalut.suurin(luvut));
    }
}