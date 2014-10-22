/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import labyrintti.logiikka.Maapala;
import labyrintti.tietorakenteet.Keko;
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
public class KekoTest {

    public KekoTest() {
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
    public void keonKokoKasvaaAlkiotaLisattaessa() {
        Keko keko = new Keko(4);
        Maapala maapala = new Maapala(3, 2);
        maapala.setKokonaisArvo1(3);

        keko.lisaaAlkio(maapala);

        assertTrue(keko.getKoko() == 1);
    }

    @Test
    public void keonKokoKasvaaAlkiotaLisattaessa1() {
        Keko keko = new Keko(5);
        Maapala maapala = new Maapala(0, 2);
        maapala.setKokonaisArvo1(3);

        Maapala maapala1 = new Maapala(3, 4);
        maapala1.setKokonaisArvo1(4);

        keko.lisaaAlkio(maapala);
        keko.lisaaAlkio(maapala1);

        assertTrue(keko.getKoko() == 2);
    }

    @Test
    public void minKekoToteutuuOikein() {

        Keko keko = new Keko(5);
        Maapala maapala = new Maapala(0, 2);
        maapala.setKokonaisArvo1(3);

        Maapala maapala1 = new Maapala(3, 4);
        maapala1.setKokonaisArvo1(2);

        keko.lisaaAlkio(maapala);
        keko.lisaaAlkio(maapala1);

        keko.minKeko();
        Maapala mPala = (Maapala) keko.getAlkio(1);

        assertTrue(mPala.getKokonaisArvo() == 2);
    }

    @Test
    public void minKekoToteutuuOikein1() {

        Keko keko = new Keko(5);
        Maapala maapala = new Maapala(0, 2);
        maapala.setKokonaisArvo1(2);

        Maapala maapala1 = new Maapala(3, 4);
        maapala1.setKokonaisArvo1(1);

        Maapala maapala2 = new Maapala(4, 5);
        maapala2.setKokonaisArvo1(5);

        keko.lisaaAlkio(maapala);
        keko.lisaaAlkio(maapala1);
        keko.lisaaAlkio(maapala2);

        keko.minKeko();
        Maapala mPala = (Maapala) keko.getAlkio(1);

        assertTrue(mPala.getKokonaisArvo() == 1);
    }

    @Test
    public void keollaOikeaJuuri() {
        Keko keko = new Keko(4);

        Maapala maapala = new Maapala(6, 7);
        maapala.setKokonaisArvo1(4);

        Maapala maapala1 = new Maapala(4, 5);
        maapala1.setKokonaisArvo1(2);

        keko.lisaaAlkio(maapala);
        keko.lisaaAlkio(maapala1);

        keko.minKeko();

        Maapala maapala2 = (Maapala) keko.getAlkio(1);

        assertTrue(maapala2.getKokonaisArvo() == 2);

    }

    @Test
    public void oikeaAlkioHaulla() {
        Keko keko = new Keko(3);

        Maapala maapala = new Maapala(6, 7);
        maapala.setKokonaisArvo1(4);

        Maapala maapala1 = new Maapala(4, 5);
        maapala1.setKokonaisArvo1(2);
        
        Maapala maapala2 = new Maapala(5,6);
        maapala2.setKokonaisArvo1(5);

        keko.lisaaAlkio(maapala);
        keko.lisaaAlkio(maapala1);
        keko.lisaaAlkio(maapala2);
        
        keko.minKeko();
        
        
        Maapala maapala3 = (Maapala) keko.getAlkio(3);
        
        assertTrue(maapala2.getKokonaisArvo() == 5);
    }
}
