
package com.mycompany.tiralabra_maven.kayttoliittymat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author John Lång <jllang@cs.helsinki.fi>
 */
public class TekstikayttoliittymaTest {
    
    private Tekstikayttoliittyma k;
    
    /**
     *
     */
    public TekstikayttoliittymaTest() {
    }
    
    /**
     *
     */
    @BeforeClass
    public static void setUpClass() {
    }
    
    /**
     *
     */
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     *
     */
    @Before
    public void setUp() {
    }
    
    /**
     *
     */
    @After
    public void tearDown() {
    }

    /**
     *
     */
    @Test
    public void testTulosta() {
        k = new Tekstikayttoliittyma();
        String merkkijono = "Testiteksti.";
        ByteArrayOutputStream ulostulo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(ulostulo));
        k.tulosta(merkkijono);
        assertEquals(merkkijono, ulostulo.toString().trim());
    }

    /**
     *
     */
    @Test
    public void testPyydaSyote() {
        String testisyote = "1+1";
        k = new Tekstikayttoliittyma(testisyote);
        assertEquals(testisyote, k.pyydaSyote("Tämä on testi."));
    }
}