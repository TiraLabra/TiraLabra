package wad.hakupuut;

import wad.solmu.Solmu;
import junit.framework.TestCase;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SplayPuuTest {
    SplayPuu puu;
    
    public SplayPuuTest() {
    }
    
    @Before
    public void setUp() {
        puu = new SplayPuu();
    }
    
    @Test
    public void pieniLisays() {
        for(int i = 1; i<7; i++) puu.lisaa(i);
        assertEquals("6{5{4{3{2{1,[]},[]},[]},[]},[]}", puu.tulosta(puu.getJuuri()));
    }
    
    @Test
    public void suurempiLisays() {
        for(int i = 12; i>0; i--) puu.lisaa(i);
        assertEquals("1{[],2{[],3{[],4{[],5{[],6{[],7{[],8{[],9{[],10{[],11{[],12}}}}}}}}}}}", puu.tulosta(puu.getJuuri()));
    }
    
    @Test
    public void hakuMuuttaaJarjestysta() {
        for(int i = 1; i<7; i++) puu.lisaa(i);
        puu.hae(1);
        assertEquals("1{[],6{4{2{[],3},5},[]}}", puu.tulosta(puu.getJuuri()));
    }
    
    @Test
    public void hakuPalauttaaOikein() {
        Solmu lisatty = puu.lisaa("moi");
        assertEquals(lisatty, puu.hae("moi"));    
    }
    
    @Test
    public void eiPoistettavaa() {
        puu.lisaa("moi");
        assertNull(puu.poista("En ole täällä"));
    }
    
    @Test
    public void lehdenPoisto() {
        for(int i = 1; i<7; i++) puu.lisaa(i);
        puu.poista(1);
        assertEquals("6{4{2{[],3},5},[]}", puu.tulosta(puu.getJuuri()));
    }
    
    @Test
    public void yksilapsisenPoisto() {
        for(int i = 1; i<7; i++) puu.lisaa(i);
        puu.poista(4);
        assertEquals("5{3{2{1,[]},[]},6}", puu.tulosta(puu.getJuuri()));    
    }
    
    @Test
    public void kaksilapsisenPoisto() {
        for(int i = 1; i<7; i++) puu.lisaa(i);
        puu.poista(1);
        puu.poista(4);
        assertEquals("5{2{[],3},6}", puu.tulosta(puu.getJuuri()));
    }
    
    @Test
    public void poistetaanEnemman() {
        for(int i = 1; i<7; i++) puu.lisaa(i);
        for(int i = 1; i<7; i++) puu.hae(i);
        for(int i = 1; i<6; i++) puu.poista(i);
        assertEquals(6, puu.hae(6).getArvo());
        assertEquals("6", puu.tulosta(puu.getJuuri()));
    }
}
