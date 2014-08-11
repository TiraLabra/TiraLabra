package huffmanKoodaajaTest.kasittely;

import huffmanKoodaaja.kasittely.Tiedosto;
import junit.framework.TestCase;

public class TiedostoTest extends TestCase {
    
    public void testPakattavanTiedostonAlustus() {
        Tiedosto testi = new Tiedosto("testitiedostot/testi.txt");
        assertEquals(true, testi.isPakkaus());
        assertEquals("testitiedostot/testi.txt", testi.getSijainti());
        assertEquals("testitiedostot/testi.txt.huff", testi.getTallennus());
        
        testi = new Tiedosto("testitiedostot/testi.txt", "testitiedostot/pakattu.txt");
        assertEquals(true, testi.isPakkaus());
        assertEquals("testitiedostot/testi.txt", testi.getSijainti());
        assertEquals("testitiedostot/pakattu.txt.huff", testi.getTallennus());
    }
    
    public void testPurettavanTiedostonAlustus() {
        Tiedosto testi = new Tiedosto("testitiedostot/testi.txt.huff");
        assertEquals(false, testi.isPakkaus());
        assertEquals("testitiedostot/testi.txt.huff", testi.getSijainti());
        assertEquals("testitiedostot/testi.txt", testi.getTallennus());
        
//        testi = new Tiedosto("testitiedostot/testi.txt.huff", "testitiedostot/purettu.txt");
//        assertEquals(false, testi.isPakkaus());
//        assertEquals("testitiedostot/testi.txt.huff", testi.getSijainti());
//        assertEquals("testitiedostot/purettu.txt", testi.getTallennus());
    }
}
