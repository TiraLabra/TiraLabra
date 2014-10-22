package wad.hakupuut;

import wad.solmu.Solmu;
import junit.framework.TestCase;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PunaMustaPuuTest {
    PunaMustaPuu puu;
    
    public PunaMustaPuuTest() {
    }
    
    @Before
    public void setUp() {
        puu = new PunaMustaPuu();
    }
    
    @Test
    public void puuhunLisaysOnnistuu() {
        puu.lisaa("jotakin");
        assertEquals("jotakin",puu.hae("jotakin").getArvo());
    }
    
    @Test
    public void sisarTesti() {
        puu.lisaa(1);
        puu.lisaa(2);
        puu.lisaa(3);
        assertEquals(puu.hae(1), puu.getSisar(puu.hae(3), puu.hae(2)));
    }
    
    @Test
    public void kysytäänVanhempaaJaVäärääLasta() {
        puu.lisaa(1);
        puu.lisaa(2);
        assertNull(puu.getSisar(puu.hae(2), puu.hae(1)));
    }
    
    @Test
    public void poistoOnnistuu() {
        puu.lisaa(1);
        puu.poista(1);
        assertNull(puu.hae(1));
    }
    
    @Test
    public void kahdenLisays() {
        puu.lisaa(1);
        puu.lisaa(2);
        assertEquals(puu.hae(2), puu.hae(1).getOikea());
        assertEquals(2, puu.hae(2).getArvo());
    }
    
    @Test
    public void vasenKaannos() {
        for(int i = 0; i<3;i++) puu.lisaa(i);
        assertEquals("1{0,2}", puu.tulosta(puu.getJuuri()));
    }
    
    @Test
    public void oikeaKaannos() {
    for(int i = 3; i>0;i--) puu.lisaa(i);
        assertEquals("2{1,3}", puu.tulosta(puu.getJuuri()));
    }
    
    @Test
    public void lisataanPaljon() {
        for(int i = 0; i<16; i++) puu.lisaa(i);
        assertEquals("3{1{0,2},7{5{4,6},11{9{8,10},13{12,14{[],15}}}}}", puu.tulosta(puu.getJuuri()));
    }
    
    @Test
    public void poistetaanPaljon() {
        for(int i = 0; i<16; i++) puu.lisaa(i);
        for(int i = 0; i<15; i++) puu.poista(i);
        assertEquals("15", puu.tulosta(puu.getJuuri()));
    }
}
