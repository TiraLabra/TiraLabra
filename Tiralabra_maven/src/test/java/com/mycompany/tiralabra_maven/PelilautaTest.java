package com.mycompany.tiralabra_maven;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author noora
 */
public class PelilautaTest {
    private Pelilauta lauta;
    
    public PelilautaTest() {
    }
    
    @Before
    public void setUp() {
        lauta = new Pelilauta();
    }

    @Test
    public void asetaNappulatAlkuasetelmaanToimii() {
        lauta.asetaNappulatAlkuasetelmaan();
        assertEquals(Nappula.MUSTA, lauta.getNappula(1, 0));
        assertEquals(Nappula.VALKOINEN, lauta.getNappula(5, 2));
    }
    
    @Test
    public void getSallitutSiirrotPalauttaaOikeanMaaranSiirtojaAlkuasetelmasta() {
        lauta.asetaNappulatAlkuasetelmaan();
        Siirto[] siirrot = lauta.getSallitutSiirrot(true);
        assertEquals(7, siirrot.length);
    }
    
    @Test
    public void teeSiirtoTekeeSiirron() {
        lauta.asetaNappulatAlkuasetelmaan();
        lauta.teeSiirto(new Siirto(5, 0, 4, 1));
        assertEquals(Nappula.VALKOINEN, lauta.getNappula(4, 1));
        assertEquals(null, lauta.getNappula(5, 0));
    }
    
    @Test
    public void getSallitutHypytPalauttaaOikeanMaaranHyppyjaAlkuasetelmasta() {
        lauta.asetaNappulatAlkuasetelmaan();
        Siirto[] hypyt = lauta.getSallitutHypyt(true, 5, 0);
        assertEquals(true, null == hypyt);
    }
    
    @Test
    public void teeKopiotekeeKopionPelilaudasta() {
        lauta.asetaNappulatAlkuasetelmaan();
        Pelilauta uusi = lauta.teeKopio();
        assertEquals(Nappula.VALKOINEN, uusi.getNappula(5, 0));
    }
    
    @Test
    public void voikoNappulaHypataToimiiAlkuasetelmasta() {
        lauta.asetaNappulatAlkuasetelmaan();
        assertEquals(false, lauta.voikoNappulaHypata(true, 5, 0, 4, 1, 3, 2));
    }
    
    @Test
    public void voikoNappulaLiikkuaToimiiJosVoiLiikkua() {
        lauta.asetaNappulatAlkuasetelmaan();
        assertEquals(true, lauta.voikoNappulaLiikkua(true, 5, 0, 4, 1));
    }
    
    @Test
    public void voikoNappulaLiikkuaEiToimiJosToisenPelaajanVuoro() {
        lauta.asetaNappulatAlkuasetelmaan();
        assertEquals(false, lauta.voikoNappulaLiikkua(true, 1, 1, 2, 0));
    }
    
    @Test
    public void voikoNappulaLiikkuaEiToimiJosEiNappulaa() {
        lauta.asetaNappulatAlkuasetelmaan();
        assertEquals(false, lauta.voikoNappulaLiikkua(true, 5, 1, 4, 1));
    }
    
}
