package viidensuora.ai;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import viidensuora.peli.Peli;

public class TekoalyTest {

    private Peli p;
    private Tekoaly ai;

    @Before
    public void setUp() {
        p = new Peli(3, 3, 3);
        ai = new Tekoaly(p, new MinMax(), 5);
    }

    @Test
    public void test1() {
        p.getPelilauta().asetaRisti(0, 0);
        p.getPelilauta().asetaNolla(1, 0);
        p.getPelilauta().asetaRisti(0, 1);
        p.getPelilauta().asetaNolla(1, 1);
        // assertEquals(Integer.MAX_VALUE, ai.minmax(3, true, null));
    }
}
