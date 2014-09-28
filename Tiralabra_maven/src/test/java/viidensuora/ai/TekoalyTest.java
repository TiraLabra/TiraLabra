package viidensuora.ai;

import org.junit.Before;
import org.junit.Test;
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
    }
}
