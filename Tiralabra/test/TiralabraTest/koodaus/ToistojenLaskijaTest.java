/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TiralabraTest.koodaus;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.koodaus.ToistojenLaskija;

/**
 *
 * @author Joonas
 */
public class ToistojenLaskijaTest {
    
    private ToistojenLaskija laskija;
    
    public ToistojenLaskijaTest() {
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
    public void laskeeOikeinA(){
        laskija.laske("test.txt");
        int taulukko[] = laskija.getToistot();
        assertEquals(4, taulukko[97]);
    }
    
    @Test
    public void laskeeOikeinB(){
        laskija.laske("test.txt");
        int taulukko[] = laskija.getToistot();
        assertEquals(2, taulukko[98]);
    }
    
    @Test
    public void laskeeOikeinE(){
        laskija.laske("test.txt");
        int taulukko[] = laskija.getToistot();
        assertEquals(8, taulukko[101]);
    }
    
    @Test
    public void laskeeOikeinG(){
        laskija.laske("test.txt");
        int taulukko[] = laskija.getToistot();
        assertEquals(2, taulukko[103]);
    }
    
    @Test
    public void eiLaskeKuulumattomiaViereisiaKirjaimia(){
        laskija.laske("test.txt");
        int taulukko[] = laskija.getToistot();
        assertEquals(0, taulukko[96]);
        assertEquals(0, taulukko[104]);

    }
    
    @Test
    public void eiLaskeKuulumattomiaKirjaimiaAlusta(){
        laskija.laske("test.txt");
        int taulukko[] = laskija.getToistot();
        int tarkistus = 96;
        for(int i = 0; i < tarkistus;i++){
            assertEquals(0, taulukko[i]);
        }
    }
    
    @Test
    public void eiLaskeKuulumattomiaKirjaimiaLopusta(){
        laskija.laske("test.txt");
        int taulukko[] = laskija.getToistot();
        int tarkistus = 256;
        for(int i = 105; i < tarkistus;i++){
            assertEquals(0, taulukko[i]);
        }
    }
}
