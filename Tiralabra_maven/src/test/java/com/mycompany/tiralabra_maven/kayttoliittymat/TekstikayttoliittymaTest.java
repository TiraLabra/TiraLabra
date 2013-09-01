
package com.mycompany.tiralabra_maven.kayttoliittymat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.*;
import org.junit.Test;

public class TekstikayttoliittymaTest {
    
    private Tekstikayttoliittyma k;

    @Test
    public void testTulosta() {
        k = new Tekstikayttoliittyma();
        String merkkijono = "Testiteksti.";
        ByteArrayOutputStream ulostulo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(ulostulo));
        k.tulosta(merkkijono);
        assertEquals(merkkijono, ulostulo.toString().trim());
    }

    @Test
    public void testPyydaSyote() {
        String testisyote = "1+1";
        k = new Tekstikayttoliittyma(testisyote);
        assertEquals(testisyote, k.pyydaSyote("Tämä on testi."));
    }
}