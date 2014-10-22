package com.mycompany.tiralabra_maven.tyokalut;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Markus
 */
public class SolmuTest {

    Solmu s;
    int[] a;

    public SolmuTest() {
    }

    @Before
    public void setUp() {
        a = new int[1];
        a[0] = 0;
        s = new Solmu(a);
    }

    @Test
    public void testGetData() {
        assertArrayEquals(a, s.getData());
    }

    @Test
    public void testSeuraava() {
        assertEquals(null, s.getSeuraava());
        Solmu s2 = new Solmu(a);
        s.setSeuraava(s2);
        assertEquals(s2, s.getSeuraava());
        assertEquals(null, s2.getSeuraava());
    }
}
