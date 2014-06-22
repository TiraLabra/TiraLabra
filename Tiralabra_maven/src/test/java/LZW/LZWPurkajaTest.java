package LZW;

import Apuvalineet.BinaariMuuntaja;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LZWPurkajaTest {
    private BinaariMuuntaja muuntaja;
    private LZWPurkaja purkaja;
    private final String n = (char) 0 + "";
    private final String y = (char) 1 + "";
    
    
    @Before
    public void setUp() {
        this.purkaja = new LZWPurkaja();
        this.muuntaja = new BinaariMuuntaja();
    }
    
    @Test
    public void pituusAlussa9() {
        assertEquals(9, purkaja.getPituus());
    }
    
    @Test
    public void merkkijono() {
        purkaja = new LZWPurkaja();
        String bittijono = n+y+n+y+n+n+y+n+y;
        
        assertEquals((char) 165 + "", purkaja.merkkijono(bittijono));
        
        bittijono += y;
        assertEquals((char) 75 + "", purkaja.merkkijono(bittijono));
        
        purkaja.lisaaKoodistoon("AB");
        assertEquals("AB", purkaja.merkkijono(y+n+n+n+n+n+n+n+n));
    }
    
    @Test
    public void seuraavaBittijono() {
        purkaja = new LZWPurkaja();
        String binaarina = n+y+y+y+n+n+y+n+n+y+n+n+y+y+n+n+y+n+n+y+y+n;
        
        assertEquals(n+y+y+y+n+n+y+n+n ,purkaja.seuraavaBittijono(binaarina, 0));
        assertEquals(n+y+n+n+y+n+n+y+y, purkaja.seuraavaBittijono(binaarina, 5));
        
        purkaja.setPituus(11);
        assertEquals(n+y+y+y+n+n+y+n+n+y+n, purkaja.seuraavaBittijono(binaarina, 0));
        assertEquals(n+y+y+n+n+y+n+n+y+y+n, purkaja.seuraavaBittijono(binaarina, 11));
    }
    
    @Test
    public void seuraavaMerkkijono() {
        purkaja = new LZWPurkaja();
        String binaarina = n+y+y+y+n+n+y+n+n+y+n+n+y+y+n+n+y+n+n+y+y+n;
        
        assertEquals((char) 228 + "", purkaja.seuraavaMerkkiJono(binaarina, 0));
        purkaja.setPituus(10);
        
        assertEquals((char) 147 + "", purkaja.seuraavaMerkkiJono(binaarina, 4));
        
        purkaja.lisaaKoodistoon("8i");
        binaarina = y+n+y+n+n+muuntaja.binaariEsitys(256);
        
        assertEquals("8i", purkaja.seuraavaMerkkiJono(binaarina, 5));
    }
    
    @Test
    public void lisaaKoodistoon() {
        purkaja = new LZWPurkaja();
        String avain = "TO";
        String arvo = muuntaja.binaariEsitys(256);
        
        purkaja.lisaaKoodistoon(avain);
        
        assertTrue(purkaja.getLaaj().sisaltaaAvaimen(avain));
        assertTrue(purkaja.getKaannettyLaaj().sisaltaaAvaimen(arvo));
        
        assertEquals(arvo, purkaja.getLaaj().getArvo(avain));
        assertEquals(avain, purkaja.getKaannettyLaaj().getArvo(arvo));
        assertEquals(9, purkaja.getPituus());
    }
    
    @Test
    public void lisaaMerkki() {
        purkaja = new LZWPurkaja();
        
        merkkiaEiLisataNykyinenTyhja();
        merkkiaEiLisataSeuraavaLoytyyLaajKoodistosta();
        merkkiJonoLisataanKoodistoon();
    }
    
    private void merkkiaEiLisataNykyinenTyhja() {
        String teksti = purkaja.getTeksti();

        String nykyinen = purkaja.kasitteleMerkki("", "A");
        assertEquals(teksti, purkaja.getTeksti());
        assertEquals("A", nykyinen);
    }
    
    private void merkkiaEiLisataSeuraavaLoytyyLaajKoodistosta() {
        purkaja.lisaaKoodistoon("AB");
        String nykyinen = purkaja.kasitteleMerkki("A", "B");
        assertEquals("AB", nykyinen);
    }
    
    private void merkkiJonoLisataanKoodistoon() {
        purkaja = new LZWPurkaja();
        String teksti = purkaja.getTeksti();
        
        purkaja.lisaaKoodistoon("98");
        String nykyinen = purkaja.kasitteleMerkki("98", "7");
        
        assertTrue(purkaja.getLaaj().sisaltaaAvaimen("987"));
        assertEquals(teksti + "98", purkaja.getTeksti());
        assertEquals("7", nykyinen);
    }
    
    @Test
    public void lisaaMerkkijono() {
        purkaja = new LZWPurkaja();
        String teksti = purkaja.getTeksti();
        
        purkaja.lisaaKoodistoon("EO");
        assertEquals("OB", purkaja.kasitteleMerkkijono("OB", ""));
        assertEquals(teksti, purkaja.getTeksti());
        
        assertEquals("EO", purkaja.kasitteleMerkkijono("OB", "EO"));
        assertEquals(teksti + "OB", purkaja.getTeksti());
    }
}
