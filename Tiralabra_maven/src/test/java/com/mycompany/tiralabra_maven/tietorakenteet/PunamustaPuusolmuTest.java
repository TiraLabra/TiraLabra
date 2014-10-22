package com.mycompany.tiralabra_maven.tietorakenteet;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Markus
 */
public class PunamustaPuusolmuTest {

    PunamustaPuusolmu s;

    public PunamustaPuusolmuTest() {
    }

    @Before
    public void setUp() {
        s = new PunamustaPuusolmu(0);
    }

    @Test
    public void testOikea() {
        assertEquals(null, s.getOikea());
        PunamustaPuusolmu t = new PunamustaPuusolmu(0);
        s.setOikea(t);
        assertEquals(t, s.getOikea());
    }

    @Test
    public void testVasen() {
        assertEquals(null, s.getVasen());
        PunamustaPuusolmu t = new PunamustaPuusolmu(0);
        s.setVasen(t);
        assertEquals(t, s.getVasen());
    }

    @Test
    public void testVanhempi() {
        assertEquals(null, s.getVanhempi());
        PunamustaPuusolmu t = new PunamustaPuusolmu(1);
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
    public void testGetVari() {
        assertEquals(true, s.getVari() instanceof Vari);
        assertEquals(Vari.PUNAINEN, s.getVari());
    }

    @Test
    public void testSetVari() {
        assertEquals(Vari.PUNAINEN, s.getVari());
        s.setVari(Vari.MUSTA);
        assertEquals(Vari.MUSTA, s.getVari());
    }

    @Test
    public void testGetIsovanhempi() {
        PunamustaPuusolmu a = new PunamustaPuusolmu(1);
        PunamustaPuusolmu b = new PunamustaPuusolmu(2);
        s.setVanhempi(a);
        a.setVanhempi(b);
        assertEquals(b, s.getIsovanhempi());
        assertEquals(null, a.getIsovanhempi());

    }

    @Test
    public void testGetSisarus() {
        PunamustaPuusolmu a = new PunamustaPuusolmu(1);
        PunamustaPuusolmu b = new PunamustaPuusolmu(2);
        a.setVanhempi(s);
        b.setVanhempi(s);
        s.setVasen(a);
        s.setOikea(b);
        assertEquals(a, b.getSisarus());
        assertEquals(b, a.getSisarus());
        assertEquals(null, s.getSisarus());
    }

    @Test
    public void testGetSeta() {
        PunamustaPuusolmu a = new PunamustaPuusolmu(1);
        PunamustaPuusolmu b = new PunamustaPuusolmu(2);
        PunamustaPuusolmu c = new PunamustaPuusolmu(3);
        PunamustaPuusolmu d = new PunamustaPuusolmu(4);
        a.setVanhempi(s);
        b.setVanhempi(a);
        c.setVanhempi(s);
        d.setVanhempi(c);
        s.setVasen(a);
        a.setVasen(b);
        s.setOikea(c);
        c.setOikea(d);
        assertEquals(a, d.getSeta());
        assertEquals(c, b.getSeta());
        assertEquals(null, s.getSeta());

    }

}
