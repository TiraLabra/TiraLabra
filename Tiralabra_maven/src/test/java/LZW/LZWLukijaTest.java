package LZW;

import Apuvalineet.BinaariMuuntaja;
import Tietorakenteet.HajautusTaulu;
import java.io.IOException;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LZWLukijaTest {
    private LZWLukija lukija;
    private YleisMetodeja yleis;
    private BinaariMuuntaja muuntaja;
    private final String polku = "LZWLukijaTest.txt";
    
    @Before
    public void setUp() {
        this.lukija = new LZWLukija();
        this.yleis = new YleisMetodeja();
        this.muuntaja = new BinaariMuuntaja();
    }
    
    @Test
    public void tekstiAlussaTyhja() {
        assertTrue(lukija.getTeksti().isEmpty());
    }
    

    
//    
//    @Test
//    public void lisaaArvojenEteenEtuNolla() {
//        lukija = new LZWLukija();
//        String[] arvot = new String[lukija.getEsitykset().getKoko()];
//        System.arraycopy(lukija.getEsitykset().getArvot(), 0, arvot, 0, arvot.length);
//        
//        lukija.lisaaArvojenEteenEtuNolla();
//        String[] uudetArvot = lukija.getEsitykset().getArvot();
//        
//        for (int i = 0; i < arvot.length; i++) {
//            assertEquals((char) 0 + arvot[i], uudetArvot[i]);
//        }
//    }
    
    @Test
    public void lisaaAvain() {
        lukija = new LZWLukija();
        lukija.lisaaKoodistoon("ab");
        
        assertEquals(257, yleis.arvoja(lukija.getAsciiKoodisto(), lukija.getLaajennettuKoodisto()));
        assertTrue(lukija.getLaajennettuKoodisto().sisaltaaAvaimen("ab"));
    }
    
    @Test
    public void lisaaBittijonoTekstiin() throws IOException {
        lukija = new LZWLukija();
        lukija.lue(polku);
        
        String teksti = lukija.getTeksti();
        lukija.lisaaBittijonoTekstiin("m");
        
        String m = (char) 0 + muuntaja.binaariEsitys8Bit(109);
        assertEquals(teksti + m, lukija.getTeksti());
        
        lukija.lisaaBittijonoTekstiin("EOR");
        assertEquals(teksti + m + muuntaja.binaariEsitys(269), lukija.getTeksti());
    }
    
    @Test
    public void lisaaBittijono() throws IOException {
        lukija = new LZWLukija();
        
        HajautusTaulu ascii = lukija.getAsciiKoodisto();
        HajautusTaulu laaj = lukija.getLaajennettuKoodisto();
        
        lukija.lisaaBittijono(ascii, "c");
        assertEquals((char) 0 + muuntaja.binaariEsitys8Bit(99), lukija.getTeksti());
        
        lukija.lue(polku);
        String teksti = lukija.getTeksti();
        
        lukija.lisaaBittijono(laaj, "NO");
        
        assertEquals(teksti +  muuntaja.binaariEsitys(262), lukija.getTeksti());
    }
    
    @Test
    public void lisaaMerkki() throws IOException {
        lukija = new LZWLukija();
        lukija.lue(polku);

        builderOnTyhja(new StringBuilder(), lukija.getTeksti());
        laajennettuKoodistoSisaltaaAvaimen(new StringBuilder(), lukija.getTeksti());
        avainEiOleKummassakaanKoodistossa(new StringBuilder(), lukija.getTeksti());
    }
    
    private void builderOnTyhja(StringBuilder builder, String teksti) {
        builder = lukija.lisaaMerkki('*', builder);
        assertEquals("*", builder.toString());
        assertEquals(teksti, lukija.getTeksti());
    }
    
    private void laajennettuKoodistoSisaltaaAvaimen(StringBuilder builder, String teksti) {
        builder.append("R");
        builder = lukija.lisaaMerkki('N', builder);
        
        assertEquals("RN", builder.toString());
        assertEquals(teksti, lukija.getTeksti());
    }
    
    private void avainEiOleKummassakaanKoodistossa(StringBuilder builder, String teksti) {
        builder.append("T");
        builder = lukija.lisaaMerkki('R', builder);
        
        HajautusTaulu laaj = lukija.getLaajennettuKoodisto();
        assertTrue(laaj.sisaltaaAvaimen("TR"));
        assertEquals(muuntaja.binaariEsitys(271), laaj.getArvo("TR"));
        
        assertEquals("R", builder.toString());
        assertEquals(teksti + (char) 0 + muuntaja.binaariEsitys8Bit('T'), lukija.getTeksti());
    }
    
    @Test
    public void lue() throws IOException {
        lukija = new LZWLukija();
        lukija.lue(polku);
        
        HajautusTaulu laajKoodisto = lukija.getLaajennettuKoodisto();
        
        String[] avaimet = {"TO", "OB", "BE", "EO", "OR", "RN", "NO", "OT", "TT", "TOB", "BEO", "ORT", "TOBE", "EOR", "RNO"};
        assertEquals(avaimet.length, laajKoodisto.getAvaimet().length);
        
        for (int i = 0; i < avaimet.length; i++) {
            String avain = avaimet[i];
            
            assertTrue(laajKoodisto.sisaltaaAvaimen(avain));
            assertEquals(muuntaja.binaariEsitys(i + 256), laajKoodisto.getArvo(avain));
        }
    }
}
