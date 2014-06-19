package LZW;

import Apuvalineet.BinaariMuuntaja;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LZWPurkajaTest {
    private BinaariMuuntaja muuntaja;
    private LZWPurkaja purkaja;
    private YleisMetodeja yleis;
    private final String n = (char) 0 + "";
    private final String y = (char) 1 + "";
    
    
    @Before
    public void setUp() {
        this.purkaja = new LZWPurkaja();
        this.yleis = new YleisMetodeja();
        this.muuntaja = new BinaariMuuntaja();
    }
    
    @Test
    public void pituusAlussa9() {
        assertEquals(9, purkaja.getPituus());
    }
    
    @Test
    public void paivitaPituusJosPienempi() {
        purkaja.paivitaPituusJosPienempi(8);
        assertEquals(9, purkaja.getPituus());        
        
        purkaja.paivitaPituusJosPienempi(9);
        assertEquals(9, purkaja.getPituus());
        
        purkaja.paivitaPituusJosPienempi(10);
        assertEquals(10, purkaja.getPituus());
    }
    
    @Test
    public void asciiMerkki() {
        String bittijono = n+y+n+y+n+n+y+n+y;
        assertEquals((char) 165 + "", purkaja.asciiMerkki(bittijono));
        
        bittijono += y;
        assertEquals((char) 75 + "", purkaja.asciiMerkki(bittijono));
        
        bittijono += n;
        assertEquals((char) 150 + "", purkaja.asciiMerkki(bittijono));
    }
    
    @Test
    public void seuraavaBittijono() {
        purkaja = new LZWPurkaja();
        String binaarina = n+y+y+y+n+n+y+n+n+y+n+n+y+y+n+n+y+n+n+y+y+n;
        
        assertEquals(n+y+y+y+n+n+y+n+n ,purkaja.seuraavaBittijono(binaarina, 0));
        assertEquals(n+y+n+n+y+n+n+y+y, purkaja.seuraavaBittijono(binaarina, 5));
        
        purkaja.paivitaPituusJosPienempi(11);
        assertEquals(n+y+y+y+n+n+y+n+n+y+n, purkaja.seuraavaBittijono(binaarina, 0));
        assertEquals(n+y+y+n+n+y+n+n+y+y+n, purkaja.seuraavaBittijono(binaarina, 11));
    }
    
    @Test
    public void seuraavaMerkki() {
        purkaja = new LZWPurkaja();
        String binaarina = n+y+y+y+n+n+y+n+n+y+n+n+y+y+n+n+y+n+n+y+y+n;
        
        assertEquals((char) 228 + "", purkaja.seuraavaMerkkiJono(binaarina, 0));
        purkaja.paivitaPituusJosPienempi(10);
        
        assertEquals((char) 147 + "", purkaja.seuraavaMerkkiJono(binaarina, 4));
    }
    
    @Test
    public void lisaaAvainKoodistoon() {
        purkaja = new LZWPurkaja();
        String avain = "TO";
        purkaja.lisaaKoodistoon(avain);
        
        assertTrue(purkaja.getLaaj().sisaltaaAvaimen(avain));
        assertEquals(muuntaja.binaariEsitys(256), purkaja.getLaaj().getArvo(avain));
        assertEquals(9, purkaja.getPituus());
    }
}
