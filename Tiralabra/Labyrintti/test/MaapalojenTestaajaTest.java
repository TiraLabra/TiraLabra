/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import labyrintti.logiikka.Maapala;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class MaapalojenTestaajaTest {

    public MaapalojenTestaajaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    @Test
    public void maapalallaOikeaHeuristinenArvo() {
        Maapala maapala = new Maapala(1, 1, 2);

        assertTrue(maapala.getHValue() == 2);
    }

    @Test
    public void maapalallaOikeaHeuristinenArvo1() {
        Maapala maapala = new Maapala(1, 1, 0);
        maapala.setHValue(1000);

        assertTrue(maapala.getHValue() == 1000);
    }
    @Test
    public void maapalaMuuttuuSeinaksi(){
        Maapala maapala = new Maapala(0, 0, 0);
        maapala.asetaSeinaksi();
        
        assertTrue(maapala.onkoSeina());
    }
    
    @Test
    public void maapalaMuuttuuTakaisinNormaaliksi(){
        Maapala maapala = new Maapala (0, 0, 0);
        
        maapala.asetaSeinaksi();
        assertTrue(maapala.onkoSeina());
        
        maapala.asetaNormaaliksi();
        assertFalse(maapala.onkoSeina());
    }
    
    @Test
    public void maapalallaOikeaXKoordinaatti(){
        Maapala maapala = new Maapala (0, 2, 0);
        
        assertTrue(maapala.getX() == 0);
    }
    
    @Test
    public void maapalallaOikeaYKoordinaatti(){
         Maapala maapala = new Maapala (1, 3, 0);
        
         assertTrue(maapala.getY() == 3);
    } 
}
