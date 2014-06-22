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

    /**
     *
     */
    private Labyrintitin l;

    /**
     *
     */
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

    /**
     *
     */
    private final Solmu[][] solmut;

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

        Solmu nyt = new Solmu(new Double[1]);
        Solmu naapuri = new Solmu(new Double[1]);
        Labyrintitin.luoNaapuruudet(nyt, naapuri);

        assertTrue(nyt.onkoNaapuri(naapuri));
        assertTrue(naapuri.onkoNaapuri(nyt));
    }

    /**
     *
     */
    public class LabyrintitinImpl extends Labyrintitin {

        /**
         *
         */
        public LabyrintitinImpl() {
            super(null);
        }

        /**
         *
         * @param solmut
         */
        public LabyrintitinImpl(Solmu[][] solmut) {
            super(solmut);
        }

        public void labyrintita() {
        }

    }

}
