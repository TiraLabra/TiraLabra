package com.mycompany.logiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KasiTest {
    
    public KasiTest() {
    }
    
    @Before
    public void SetUp() {
        
    }
    
    @Test
    public void kadenNimiOnOikein(){
        Kasi k = new Kasi("asdf");
        assertEquals("asdf", k.getNimi());
    }
    
    @Test
    public void kasiHaviaaOikein() {
        Kasi k = new Kasi("KIVI");
        Kasi p = new Kasi("PAPERI");
        k.lisaaVoittavaKasiYksi(p);
        assertEquals(false, k.voittaako(p));
    }
    
    @Test
    public void kasiVoittaaOikein() {
        Kasi k = new Kasi("KIVI");
        Kasi p = new Kasi("PAPERI");
        k.lisaaVoittavaKasiYksi(p);
        assertEquals(true, p.voittaako(k));
    }
}
