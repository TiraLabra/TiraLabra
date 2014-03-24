
package pacman.peli.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pacman.peli.Pistelaskuri;

public class PistelaskuriTest {
    private Pistelaskuri laskuri;
    
    public PistelaskuriTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        laskuri = new Pistelaskuri();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void kasvattaaPisteitaOikein() {
        laskuri.kasvata(20);
        assertEquals(20, laskuri.getPisteet());        
    }
}
