package Apuvalineet;

import java.io.File;
import java.io.IOException;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class KirjoittajaTest {
    private Kirjoittaja kirjoittaja;
    
    @Before
    public void luoTiedosto() throws IOException {
        this.kirjoittaja = new Kirjoittaja("KirjoittajaTest.txt");
    }
    
    @Test
    public void testaaKirjoittaminen() throws IOException {
        kirjoittaja.kirjoita("asdhoashdoas");
        
        TekstinLukija lukija = new TekstinLukija();
        lukija.lueTiedosto("KirjoittajaTest.txt");
        assertEquals("asdhoashdoas", lukija.getTeksti());
    }
            
    @After
    public void tuhoaKirjoitettuTiedosto() {
        new File("KirjoittajaTest.txt").delete();
    }
}
