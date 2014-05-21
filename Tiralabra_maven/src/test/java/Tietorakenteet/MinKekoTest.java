package Tietorakenteet;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MinKekoTest {
    private MinKeko keko;
    
    @Before
    public void setUp() {
        this.keko = new MinKeko(10);
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
        keko.lisaa(new Solmu("a", 7));
        keko.lisaa(new Solmu("b", 8));
        keko.lisaa(new Solmu("c", 5));
        keko.lisaa(new Solmu("d", 4));
        keko.lisaa(new Solmu("e", 7));
        assertEquals("d", keko.getKeko()[0].getAvain());
    }
}
