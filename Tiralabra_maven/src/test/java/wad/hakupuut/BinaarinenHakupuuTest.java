package wad.hakupuut;

import wad.solmu.Solmu;
import junit.framework.TestCase;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class BinaarinenHakupuuTest {
    BinaarinenHakupuu bst, puu;
    
    public BinaarinenHakupuuTest(){
    }

    @Before
    public void setUp() {
        puu = new BinaarinenHakupuu();
        bst = new BinaarinenHakupuu();
        bst.lisaa(4);//Alustettu puu:
        bst.lisaa(3);//      4
        bst.lisaa(2);//  3       5 
        bst.lisaa(5);//2
    }
    
    @Test
    public void tyhjaPuu() {
        assertNull(new BinaarinenHakupuu().hae(""));
    }
    
    @Test
    public void tyhjaanPuuhunLisäys() {
        puu.lisaa("olen juuri");
        assertEquals("olen juuri", puu.toString());
    }
    
    @Test
    public void tyhjaanPuuhunLisätäänJaPoistetaan() {
        puu.lisaa(1234);
        assertTrue(puu.poista(1234));
        assertNull(puu.hae(1234));
    }
    
    @Test
    public void tyhjaanPuuhunLisätäänJaPoistetaanOutoaDataa() {
        BinaarinenHakupuu puu = new BinaarinenHakupuu();
        Solmu juuri = new Solmu("moi");
        puu.lisaa(juuri); // juuri: Solmu( Solmu( "moi" ) )
        assertTrue(puu.poista(juuri)); // tarkoitus osoittaa, että puuhun ei lisätä staattista dataa
        assertNull(puu.hae(juuri)); // vaan käyttäjä voi itse valita datatyypin
    }
    
    @Test
    public void pieninArvoAlustetustaPuusta() {
        assertEquals(2, bst.min(bst.hae(4)).getArvo());
    }
    
    @Test
    public void pienimmänArvonLisäysJaEtsintä() {      //        4
        bst.lisaa(1);                                  //    3       5
        assertEquals(1, bst.min(bst.hae(4)).getArvo());//  2
    }                                                  //1
    
    @Test
    public void pieninArvoJuurenOikeastaAlipuusta() {
        assertEquals(5, bst.min(bst.hae(5)).getArvo());
    }
    
    @Test
    public void puunTulostus() {
        assertEquals("4{3{2,[]},5}", bst.toString());
    }
    
    @Test
    public void puussaOlemmattomanDatanPoisto() {
        assertFalse(bst.poista("en ole täällä"));
    }
    
    @Test
    public void lehdenPoistoOikealtaJaTulostus() {
        bst.poista(5);
        assertEquals("4{3{2,[]},[]}", bst.toString());
    }
    
    @Test
    public void lehdenPoistoVasemmaltaJaTulostus() {
        bst.poista(2);
        assertEquals("4{3,5}", bst.toString());
    }
    
    @Test
    public void yksilapsisenPoistoJaTulostus() {
        bst.poista(3);
        assertEquals("4{2,5}", bst.toString());
    }
    
    @Test
    public void juurenPoistoJaTulostus() {
        bst.poista(4);
        assertEquals("5{3{2,[]},[]}", bst.toString());
    }
    
    @Test
    public void poistetaanMuutama() {
        bst.poista(4);
        bst.poista(5);
        bst.poista(2);
        assertEquals("3", bst.toString());
    }
}