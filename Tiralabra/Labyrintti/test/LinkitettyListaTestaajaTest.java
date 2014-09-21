/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import labyrintti.logiikka.Maapala;
import labyrintti.tietorakenteet.LinkitettyLista;
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
public class LinkitettyListaTestaajaTest {
    
    public LinkitettyListaTestaajaTest() {
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
    public void oikeaKokoAluksi(){
        LinkitettyLista linkitettyLista = new LinkitettyLista();
        
        assertTrue(linkitettyLista.getKoko() == 0);
    }
    
    @Test
    public void oikeaKokoAlkioitaLisattaessa(){
        LinkitettyLista linkitettyLista = new LinkitettyLista();
        
        Maapala maapala = new Maapala (0,0,1);
        
        linkitettyLista.lisaaListaan(maapala);
        
        assertTrue(linkitettyLista.getKoko() == 1);
    }
    
    @Test
    public void oikeaKokoAlkioitaLisattaessa1(){
        LinkitettyLista linkitettyLista = new LinkitettyLista();
        
        Maapala maapala = new Maapala (0,0,0);
        Maapala maapala1 = new Maapala (0,0,1);
        
        linkitettyLista.lisaaListaan(maapala);
        linkitettyLista.lisaaListaan(maapala1);
        
        assertTrue(linkitettyLista.getKoko() == 2);
    }
    
    @Test
    public void oikeaKokoAlkioitaPoistettaessa(){
        LinkitettyLista linkitettyLista = new LinkitettyLista();
        
        Maapala maapala = new Maapala (0,0,0);
        
        linkitettyLista.lisaaListaan(maapala);
        
        assertTrue(linkitettyLista.getKoko() == 1);
        
        linkitettyLista.poistaListasta(maapala);
        
        assertTrue(linkitettyLista.getKoko() == 0);
    }
    
    @Test
    public void oikeaKokoAlkioitaPoistettaessa1(){
        LinkitettyLista linkitettyLista = new LinkitettyLista();
        
        Maapala maapala = new Maapala (0,0,0);
        Maapala maapala1 = new Maapala (0,0,1);
        
        linkitettyLista.lisaaListaan(maapala);
        linkitettyLista.poistaListasta(maapala1);
        
        assertTrue(linkitettyLista.getKoko() == 1);
    }
    
    @Test
    public void oikeaPaa(){
        LinkitettyLista linkitettyLista = new LinkitettyLista();
        
        Maapala maapala = new Maapala (0,0,8);
        linkitettyLista.lisaaListaan(maapala);
        
        Maapala maapala1 = (Maapala)linkitettyLista.getPaa();
        
        assertTrue(maapala1.getHArvo() == 8);
        
    }
    
    @Test
    public void oikeaPaa1(){
        LinkitettyLista linkitettyLista = new LinkitettyLista();
        
        Maapala maapala = new Maapala (0,0,8);
        linkitettyLista.lisaaListaan(maapala);
        
        Maapala maapala1 = new Maapala(3,2,5);
        
        linkitettyLista.lisaaListaan(maapala1);
        
        linkitettyLista.poistaListasta(maapala);
        Maapala maapala2 = (Maapala)linkitettyLista.getPaa();
        
        assertTrue(maapala2.getHArvo() == 5);
        
        
    }
}
