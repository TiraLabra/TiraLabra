/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TiralabraTest;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.tiedostonkasittely.TiedostonLukija;


/**
 *
 * @author Joonas
 */
public class TiedostonLukijaTest {
    
    private TiedostonLukija lukija;
    
    public TiedostonLukijaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.lukija = new TiedostonLukija("test.txt");
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void lukeeOikein(){
        assertEquals(97, lukija.lue());
        assertEquals(97, lukija.lue());
        assertEquals(97, lukija.lue());
        assertEquals(97, lukija.lue());
        assertEquals(98, lukija.lue());

    }
    @Test
    public void oikeaMaaraVapaana(){
        assertEquals(30, lukija.vapaana());
        lukija.lue();
        assertEquals(29, lukija.vapaana());
    }
}
