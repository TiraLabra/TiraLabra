/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TiralabraTest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.tiivistys.ToistojenLaskija;

/**
 *
 * @author Joonas
 */
public class LaskijaTest {
    
    private ToistojenLaskija laskija;
    
    public LaskijaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.laskija = new ToistojenLaskija();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void laskeeOikein(){
        laskija.laske("test.txt");
        int taulukko[] = laskija.getToistot();
        assertEquals(4, taulukko[97]);
        assertEquals(2, taulukko[98]);
        assertEquals(3, taulukko[99]);
        assertEquals(2, taulukko[100]);
        assertEquals(8, taulukko[101]);
        assertEquals(9, taulukko[102]);
        assertEquals(2, taulukko[103]);
    }
    
    @Test
    public void eiLaskeKuulumattomiaKirjaimia(){
        laskija.laske("test.txt");
        int taulukko[] = laskija.getToistot();
        assertEquals(0, taulukko[96]);
        assertEquals(0, taulukko[0]);
        assertEquals(0, taulukko[44]);
        assertEquals(0, taulukko[255]);
        assertEquals(0, taulukko[104]);

    }
}
