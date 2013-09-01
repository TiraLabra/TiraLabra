
package com.mycompany.tiralabra_maven.logiikka;

import com.mycompany.tiralabra_maven.tietorakenteet.Jono;
import com.mycompany.tiralabra_maven.tietorakenteet.JonoTest;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class RegextulkkiTest {
    
    public RegextulkkiTest() {
    }
    
    private Regextulkki rt;
    
    @Before
    public void setUp() {
        this.rt = new Regextulkki();
    }

    @Test
    public void testMerkkiOnLyhenne() {
        rt.merkki = '[';
        assertTrue(rt.merkkiOnLyhenne());
    }

    @Test
    public void testMerkkiOnOperaattori() {
        rt.merkki = '|';
        assertTrue(rt.merkkiOnOperaattori());
        rt.merkki = '.';
        assertTrue(rt.merkkiOnOperaattori());
        rt.merkki = '?';
        assertTrue(rt.merkkiOnOperaattori());
        rt.merkki = '*';
        assertTrue(rt.merkkiOnOperaattori());
        rt.merkki = '+';
        assertTrue(rt.merkkiOnOperaattori());
    }

    @Test
    public void testMerkkiOnOperandi() {
        rt.merkki = 'g';
        assertTrue(rt.merkkiOnOperandi());
    }

    @Test
    public void testKasitteleLyhenne1() {
        Jono<String> oikeaVastaus = new Jono("a", "b", "c", "|");
        Jono<String> saatuVastaus = rt.tulkitseMerkkijono("[a-c]");
        JonoTest.vertaaJonoja(oikeaVastaus, saatuVastaus);
        oikeaVastaus = new Jono("a", "b", "c", "|", "*");
        saatuVastaus = rt.tulkitseMerkkijono("[a-c]*");
        JonoTest.vertaaJonoja(oikeaVastaus, saatuVastaus);
        oikeaVastaus = new Jono("a", "b", "c", "|", "a", "b", "c", "|");
        saatuVastaus = rt.tulkitseMerkkijono("[a-c]{2}");
        JonoTest.vertaaJonoja(oikeaVastaus, saatuVastaus);
        oikeaVastaus =new Jono("a", "g", "7", "|", "a", "g", "7", "|", "a", "g",
                "7", "|", "a", "g", "7", "|", "a", "g", "7", "|");
        saatuVastaus = rt.tulkitseMerkkijono("[ag7]{5}");
        JonoTest.vertaaJonoja(oikeaVastaus, saatuVastaus);
    }
    
    @Test
    public void testKasitteleLyhenne2() {
        Jono<String> oikeaVastaus = new Jono("a", "b", "c", "|", "a", "b", "c",
                "|", "a", "b", "c", "|");
        rt.merkkijono       = "[a-c]{3}.d";
        rt.syotteenMerkit   = rt.merkkijono.toCharArray();
        rt.PINO.tyhjenna();
        rt.JONO.tyhjenna();
        rt.indeksi      = 0;
        rt.merkki       = '[';
        rt.kasitteleLyhenne();
        assertEquals(8, rt.indeksi);
        JonoTest.vertaaJonoja(oikeaVastaus, rt.JONO);
    }
    
    @Test
    public void testKasitteleOperandi1() {
        Jono<String> oikeaVastaus   = new Jono("a");
        rt.merkkijono               = "a.b|c";
        rt.syotteenMerkit           = rt.merkkijono.toCharArray();
        rt.PINO.tyhjenna();
        rt.JONO.tyhjenna();
        rt.indeksi  = 0;
        rt.merkki   = 'a';
        rt.kasitteleOperandi();
        JonoTest.vertaaJonoja(oikeaVastaus, rt.JONO);
    }

    @Test
    public void testKasitteleOperandi2() {
        Jono<String> oikeaVastaus   = new Jono("abc");
        rt.merkkijono               = ".abc*";
        rt.syotteenMerkit           = rt.merkkijono.toCharArray();
        rt.PINO.tyhjenna();
        rt.JONO.tyhjenna();
        rt.indeksi  = 1;
        rt.merkki   = 'a';
        rt.kasitteleOperandi();
        JonoTest.vertaaJonoja(oikeaVastaus, rt.JONO);
    }
}