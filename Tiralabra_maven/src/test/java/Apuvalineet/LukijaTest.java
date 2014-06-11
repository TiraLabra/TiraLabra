package Apuvalineet;

import Tietorakenteet.HajautusTaulu;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LukijaTest {
    private Lukija lukija;
    private Lukija lukija2;
    private final String n = (char) 0 + "";
    private final String y = (char) 1 + "";    
    
    @Before
    public void setUp() throws UnsupportedEncodingException, IOException {
        String polku = "LukijaTest.txt";
        kirjoitaTiedostonSisalto(polku);
        this.lukija = new Lukija(polku);
    }
    
    private void kirjoitaTiedostonSisalto(String polku) throws IOException {
        Kirjoittaja kirjoittaja = new Kirjoittaja(polku, true);
        kirjoittaja.kirjoita("#"+n+n + "å"+n+y + ";"+y+n + "ö"+y+y + (char) 127 + (char) 127 + n + (char) 135);
    }
    
    @Test
    public void alussaTekstiOnTyhja() {
        assertTrue(lukija.getTeksti().isEmpty());
    }    
    
    @Test (expected = IOException.class) 
    public void lukeminenHeittaaPoikkeuksenJosTiedostoaEiLoydy() throws UnsupportedEncodingException, IOException, Exception {
        lukija2 = new Lukija("");
        lukija2.lue();
    }
    
    @Test
    public void lueLukeeSisallon() throws IOException, Exception {
        lukija.lue();
        assertEquals("#"+n+n + "å"+n+y + ";"+y+n + "ö"+y+y + (char) 127 + (char) 127 + n + (char) 135, lukija.getTeksti());
        
        lukija2 = new Lukija("LukijaTest2.txt", true);
        lukija2.lue();
//        
//        lopussaTekstiOnTiedostonSisaltamaTeksti();
        merkkienEsiintymienMaaraTasmaa();
    }


    private void lopussaTekstiOnTiedostonSisaltamaTeksti() {
        String teksti = lukija2.getTeksti();
        assertTrue(teksti.equals("Tämä on testi -teksti, mikä luetaan\n" +
                                              "LukijaTestin\n" +
                                              "kautta."));
    }
    
    private void merkkienEsiintymienMaaraTasmaa() throws Exception {
        HajautusTaulu esiintymat = lukija2.getEsiintymat();
        
        String avain = esiintymat.getArvo("T");
        assertEquals(2, Integer.parseInt(avain));
        
        avain = esiintymat.getArvo(" ");
        assertEquals(5, Integer.parseInt(avain));        
        
//        luku = esiintymat.get("ä");
//        assertEquals(3, luku);
        
        avain = esiintymat.getArvo("\n");
        assertEquals(2, Integer.parseInt(avain));
        
        avain = esiintymat.getArvo("n");
        assertEquals(3, Integer.parseInt(avain)); 
    }
    
    @Test
    public void merkinLisaaminenKasvattaaSenEsiintymia() throws UnsupportedEncodingException, IOException, Exception {
        lukija2 = new Lukija("LukijaTest2.txt", true);

        lukija2.lisaaMerkki('a');
        String maara = lukija2.getEsiintymat().getArvo("a");
        assertEquals(1, Integer.parseInt(maara));
        
        lukija2.lisaaMerkki('a');
        maara = lukija2.getEsiintymat().getArvo("a");
        assertEquals(2, Integer.parseInt(maara));
    }
}
