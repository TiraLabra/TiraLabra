/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package labyrintti;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import verkko.Solmu;

/**
 *
 * @author Arvoitusmies
 */
public class LabyrintitinTest {

    private Labyrintitin l;

    public LabyrintitinTest() {
        Double[] koord1 = {0.0, 0.0};
        Double[] koord2 = {0.0, 1.0};
        Double[] koord3 = {1.0, 0.0};
        Double[] koord4 = {1.0, 1.0};
        solmut = new Solmu[2][2];
        solmut[0][0] = new Solmu(koord1);
        solmut[0][1] = new Solmu(koord2);
        solmut[1][0] = new Solmu(koord3);
        solmut[1][1] = new Solmu(koord4);

        l = new LabyrintitinImpl(solmut);
    }
    private final Solmu[][] solmut;

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

    /**
     * Test of labyrintita method, of class Labyrintitin.
     */
    @Test
    public void testLabyrintita() {
        System.out.println("labyrintita");
        Labyrintitin instance = null;
        instance.labyrintita();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of naapurit method, of class Labyrintitin.
     */
    @Test
    public void testNaapurit() {
        System.out.println("naapurit");
        Solmu s = null;
        Labyrintitin instance = null;
        Solmu[] expResult = null;
        Solmu[] result = instance.naapurit(s);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of kayty method, of class Labyrintitin.
     */
    @Test
    public void testKayty() {
        Solmu[][] labyrintitinsolmut = l.solmut;
        for (Solmu[] solmut1 : labyrintitinsolmut) {
            for (int j = 0; j < labyrintitinsolmut[0].length; j++) {
                assertFalse(l.onkoKayty(solmut1[j]));
                l.kayty(solmut1[j]);
                assertTrue(l.onkoKayty(solmut1[j]));
            }
        }

    }

    /**
     * Test of luoNaapuruudet method, of class Labyrintitin.
     */
    @Test
    public void testLuoNaapuruudet() {
        System.out.println("luoNaapuruudet");
        Solmu nyt = null;
        Solmu naapuri = null;
        Labyrintitin instance = null;
        instance.luoNaapuruudet(nyt, naapuri);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class LabyrintitinImpl extends Labyrintitin {

        public LabyrintitinImpl() {
            super(null);
        }

        public LabyrintitinImpl(Solmu[][] solmut) {
            super(solmut);
        }

        public void labyrintita() {
        }

    }

}
