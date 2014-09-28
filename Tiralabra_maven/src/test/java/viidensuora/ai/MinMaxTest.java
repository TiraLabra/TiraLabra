package viidensuora.ai;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import viidensuora.peli.Peli;

public class MinMaxTest {

    private MinMax mm;

    @Before
    public void setUp() {
        mm = new MinMax();
        Peli peli = new Peli(3, 3, 3);
        Tekoaly ai = new Tekoaly(peli, mm, 9);
    }

    @Test
    public void hello() {
        assertTrue(true);
    }
}
