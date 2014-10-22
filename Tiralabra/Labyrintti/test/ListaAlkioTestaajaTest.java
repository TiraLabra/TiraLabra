/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import labyrintti.logiikka.Maapala;
import labyrintti.tietorakenteet.ListaAlkio;
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
public class ListaAlkioTestaajaTest {
    
    public ListaAlkioTestaajaTest() {
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
    public void seuraavaOnAluksiNull(){
        
        ListaAlkio listaAlkio = new Maapala (1, 3);
        
        assertTrue(listaAlkio.getSeuraava() == null);
    }
    
    @Test
    public void oikeaSeuraavaAlkio(){
        ListaAlkio listaAlkio = new Maapala (1, 3);
        ListaAlkio listaAlkio1 = new Maapala (2, 5);
        listaAlkio.setSeuraava(listaAlkio1);
        
        assertTrue(listaAlkio.getSeuraava().equals(listaAlkio1));
    }
}
