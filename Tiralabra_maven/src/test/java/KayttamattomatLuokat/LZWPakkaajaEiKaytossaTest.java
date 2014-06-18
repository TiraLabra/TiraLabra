//package LZW;
//
//import Apuvalineet.BinaariMuuntaja;
//import java.io.IOException;
//import static org.junit.Assert.*;
//import org.junit.Before;
//import org.junit.Test;
//
//public class LZWPakkaajaEiKaytossaTest {
//    private BinaariMuuntaja muuntaja;
//    private LZWPakkaaja pakkaaja;
//    private LZWLukija lukija;
//    private LZWLukija tyhjaLukija;
//
//    @Before
//    public void setUp() throws IOException {
//        this.lukija = new LZWLukija();
//        lukija.lue("LZWLukijaTest.txt");
//        this.muuntaja = new BinaariMuuntaja();
//    }
//    
//    @Test (expected = Exception.class)
//    public void heittaaKeskeytyksenJosLukijanTekstiTyhja() throws IllegalStateException {
//        this.tyhjaLukija = new LZWLukija();
//        this.pakkaaja = new LZWPakkaaja(tyhjaLukija);
//    }
//    
//    @Test
//    public void bittiJono() throws IllegalStateException {
//        pakkaaja = new LZWPakkaaja(lukija);
//        
//        StringBuilder s = new StringBuilder();
//        s.append("/");
//        assertEquals(muuntaja.binaariEsitys8Bit((char) '/'), pakkaaja.bittiJono(s));
//        
//        s.append("t");
//        assertNull(pakkaaja.bittiJono(s));
//    }
//    
//    @Test
//    public void seuraavaaMerkkiJonoaEiOle() {
//        pakkaaja = new LZWPakkaaja(lukija);
//        StringBuilder s = new StringBuilder();
//        
//        assertTrue(lukija.getAsciiKoodisto().sisaltaaAvaimen("."));
//        assertFalse(pakkaaja.seuraavaaMerkkijonoaEiOle(s, '.'));
//        
//        s.append("%");
//        assertTrue(lukija.getAsciiKoodisto().sisaltaaAvaimen("%"));
//        assertTrue(pakkaaja.seuraavaaMerkkijonoaEiOle(s, '3'));
//    }
//    
//    @Test
//    public void pakattuna() {
//        pakkaaja = new LZWPakkaaja(lukija);
//        String n = (char) 0 + "";
//        String y = (char) 1 + "";
//        
//        String ilmanEtuNollia = n+y+y+n+y+n+y+y+n+y+y+n+n+y;
//        assertEquals((char) 26 + "" + (char) 217, pakkaaja.pakattuna(ilmanEtuNollia));
//    }
//    
//  LZWPakkaajaTest:    abccabcbaa
//    
//  Hajautustauluun lis‰tt‰v‰t uudet avaimet ja bin‰‰riesitykset:
//  -------------------------------------------------------------
//  256 - ab
//  257 - bc
//  258 - cc
//  259 - ca
//  260 - abc
//  261 - cb
//  262 - ba
//  263 - aa
//
//  teksti k‰‰ntyy siis:
//  260 + 259 + 257 + 262 + 97
//  
//    @Test
//    public void ilmanEtuNollia() throws IOException {
//        lukija = new LZWLukija();
//        lukija.lue("LZWPakkaajaTest.txt");
//        pakkaaja = new LZWPakkaaja(lukija);
//        
//        String pakattuna = muuntaja.binaariEsitys(260) + muuntaja.binaariEsitys(259) +
//                           muuntaja.binaariEsitys(257) + muuntaja.binaariEsitys(262) +
//                           muuntaja.binaariEsitys8Bit(97);
//        
//        assertEquals(pakattuna, pakkaaja.ilmanEtuNollia());
//    }
//}
