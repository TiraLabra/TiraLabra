package LZW;

import Apuvalineet.BinaariMuuntaja;
import Tietorakenteet.HajautusTaulu;
import java.io.IOException;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class YleisMetodejaTest {
    private LZWLukija lukija;
    private YleisMetodeja yleis;
    
    @Before
    public void setUp() {
        this.yleis = new YleisMetodeja();
    }
    
    @Test
    public void merkkienPituus() throws IOException {
        lukija = new LZWLukija();
        assertEquals(9, yleis.merkkienPituus(lukija.getAsciiKoodisto(), lukija.getLaajennettuKoodisto()));
    }
    
    @Test
    public void asciiKoodistonAlustusToimii() {
        lukija = new LZWLukija();
        BinaariMuuntaja muuntaja = new BinaariMuuntaja();
        
        HajautusTaulu esitykset = lukija.getAsciiKoodisto();
        String[] avaimet = esitykset.getAvaimet();
        
        for (int i = 0; i < avaimet.length; i++) {
            String avain = avaimet[i];
            
            assertEquals((char) i + "", avain);
            assertEquals(muuntaja.binaariEsitys8Bit(i), esitykset.getArvo(avain));
        }
        assertEquals(256, yleis.arvoja(lukija.getAsciiKoodisto(), lukija.getLaajennettuKoodisto()));
    }
}
