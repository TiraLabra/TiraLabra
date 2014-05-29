package Tietorakenteet;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MinKekoTest {
    private MinKeko keko;
    private MinKeko keko2;
    
    @Before
    public void setUp() {
        this.keko = new MinKeko(10);
        this.keko2 = new MinKeko(7);
        
        keko2.lisaa(new Solmu("a", 7));
        keko2.lisaa(new Solmu("b", 8));
        keko2.lisaa(new Solmu("c", 5));
        keko2.lisaa(new Solmu("d", 4));
        keko2.lisaa(new Solmu("e", 7));
    }
    
    @Test
    public void kokoAlussa0() {
        assertEquals(0, keko.getKoko());
    }
    
    @Test
    public void testVanhempi() {
        assertEquals(2, keko.vanh(5));
        assertEquals(0, keko.vanh(2));
    }
    
    @Test
    public void testVasen() {
        assertEquals(7, keko.vasen(3));
    }
    
    @Test
    public void testOikea() {
        assertEquals(8, keko.oikea(3));
    }
    
    @Test
    public void kokoKasvaaKunSolmuLisataan() {
        keko.lisaa(new Solmu("a",0));
        assertEquals(1, keko.getKoko());
    }
    
    @Test
    public void ekaLisattavaSolmuMeneeKeonAlkuun() {
        keko.lisaa(new Solmu("a", 0));
        Solmu[] solmut = keko.getSolmut();
        assertEquals("a", solmut[0].getAvain());
    }
    
    @Test
    public void lisatytSolmutMenevatKekoonSitenEttaPieninEkana() {
        assertEquals("d", keko2.getSolmut()[0].getAvain());
    }
    
    @Test
    public void keonSolmuKokoTuplaantuuKunKekoonAsetetaanEnemmanSolmujaKuinMahtuisi() {
        MinKeko keko3 = luoKekoJossaSolmujenMaaraOnTuplattu();
        assertEquals(2, keko3.getSolmut().length);
    }
    
    @Test
    public void solmuKoonTuplaamisenJalkeenKeossaSailyvatSamatSolmutSamassaJarjestyksessa() {
        MinKeko keko3 = luoKekoJossaSolmujenMaaraOnTuplattu();
        assertEquals(1, keko3.getSolmut()[0].getEsiintymat());
        assertEquals(2, keko3.getSolmut()[1].getEsiintymat());
    }  
    
    private MinKeko luoKekoJossaSolmujenMaaraOnTuplattu() {
        MinKeko keko3 = new MinKeko(1);
        
        keko3.lisaa(new Solmu(1));
        keko3.lisaa(new Solmu(2));
        return keko3;
    }
}
