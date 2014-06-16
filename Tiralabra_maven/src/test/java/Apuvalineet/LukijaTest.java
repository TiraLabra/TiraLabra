package Apuvalineet;

import java.io.IOException;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LukijaTest {
    private Lukija lukija;
    private Lukija lukija2;
    private final String n = (char) 0 + "";
    private final String y = (char) 1 + "";   
    private final String polku = "LukijaTest.txt";
    
    @Before
    public void setUp() throws IOException {
        kirjoitaTiedostonSisalto();
        this.lukija = new Lukija();
    }
    
    private void kirjoitaTiedostonSisalto() throws IOException {
        Kirjoittaja kirjoittaja = new Kirjoittaja(polku, true);
        kirjoittaja.kirjoita("#"+n+n + "å"+n+y + ";"+y+n + "ö"+y+y + (char) 127 + (char) 127 + n + (char) 135);
    }
    
    @Test
    public void alussaTekstiOnTyhja() {
        assertTrue(lukija.getTeksti().isEmpty());
    }    
    
    @Test (expected = IOException.class) 
    public void lukeminenHeittaaPoikkeuksenJosTiedostoaEiLoydy() throws IOException {
        lukija2 = new Lukija();
        lukija2.lue("");
    }
    
    @Test
    public void lueLukeeSisallon() throws IOException {
        lukija.lue(polku);
        assertEquals("#"+n+n + "å"+n+y + ";"+y+n + "ö"+y+y + (char) 127 + (char) 127 + n + (char) 135, lukija.getTeksti());
    }
}
