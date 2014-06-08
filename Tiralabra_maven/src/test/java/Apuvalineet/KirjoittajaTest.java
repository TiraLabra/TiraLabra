package Apuvalineet;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
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
        
        Scanner lukija = new Scanner(new File("KirjoittajaTest.txt"));
        assertEquals("asdhoashdoas", lukija.nextLine());
        lukija.close();
    }
            
    @After
    public void tuhoaKirjoitettuTiedosto() {
        new File("KirjoittajaTest.txt").delete();
    }
}
