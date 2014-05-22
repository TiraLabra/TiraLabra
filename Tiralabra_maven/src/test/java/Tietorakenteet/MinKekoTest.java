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
        Solmu[] solmut = keko.getKeko();
        assertEquals("a", solmut[0].getAvain());
    }
    
    @Test
    public void lisatytSolmutMenevatKekoonSitenEttaPieninEkana() {
        assertEquals("d", keko2.getKeko()[0].getAvain());
    }
    
    @Test
    public void alkioidenPoistoKeosta() {
        Solmu poistettu = keko2.poistaHuippuSolmu();
        assertEquals("d", poistettu.getAvain());
        poistettu = keko2.poistaHuippuSolmu();
        assertEquals("c", poistettu.getAvain());
        
        assertEquals(3, keko2.getKoko());
        
        keko2.poistaHuippuSolmu();
        keko2.poistaHuippuSolmu();
        poistettu = keko2.poistaHuippuSolmu();
        assertEquals("b", poistettu.getAvain());
    }
}
