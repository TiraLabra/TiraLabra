package LZW;

import Apuvalineet.BinaariMuuntaja;
import Tietorakenteet.HajautusTaulu;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class YleisMetodejaTest {
    private BinaariMuuntaja muuntaja;
    private LZWLukija lukija;
    private YleisMetodeja yleis;
    
    @Before
    public void setUp() {
        this.yleis = new YleisMetodeja();
        this.muuntaja = new BinaariMuuntaja();
    }
    
    @Test
    public void merkkienPituus() {
        assertEquals(10, yleis.merkkienPituus(9, 10));
        assertEquals(10, yleis.merkkienPituus(10, 10));
    }
    
    @Test
    public void arvoja() {
        lukija = new LZWLukija();
        assertEquals(256, yleis.arvoja(lukija.getAscii(), lukija.getLaaj()));
    }
    
    @Test
    public void koodistoonLisattavaArvo() {
        lukija = new LZWLukija();
        assertEquals(muuntaja.binaariEsitys(256), yleis.koodistoonLisattavaArvo(lukija.getAscii(), lukija.getLaaj()));
    }
    
    @Test
    public void asciiKoodistonAlustusToimii() {
        lukija = new LZWLukija();

        HajautusTaulu ascii = lukija.getAscii();
        String[] avaimet = ascii.getAvaimet();
        
        for (int i = 0; i < avaimet.length; i++) {
            String avain = avaimet[i];
            
            assertEquals((char) i + "", avain);
            assertEquals(muuntaja.binaariEsitys8Bit(i), ascii.getArvo(avain));
        }
        assertEquals(256, yleis.arvoja(lukija.getAscii(), lukija.getLaaj()));
    }
}
