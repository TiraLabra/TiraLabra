/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OhjelmaLogiikka;

import Tietorakenteet.OmaArrayList;
import Tietorakenteet.OmaHashMap;
import Tietorakenteet.OmaList;
import Tietorakenteet.OmaMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Omistaja
 */
public class KanonisoidunKoodinMuodostajaTest {

    private KanonisoidunKoodinMuodostaja muodostaja;

    public KanonisoidunKoodinMuodostajaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        muodostaja = new KanonisoidunKoodinMuodostaja();
    }

    @After
    public void tearDown() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void heittaaIllegalArgumentExceptionKunAnnetaanKoodienPituudetVaarin() {
        muodostaja.muodostaKoodi(5);
        muodostaja.muodostaKoodi(4);
    }

    @Test
    public void testaaAntaaOikeatKoodit() {
        OmaList<Long> koodit = new OmaArrayList<Long>();

        // pitäisi luoda koodit ennen kääntöä ympäri:
        // 00, 01, 100, 101, 1100, 1101
        // käännettynä ympäri: 00, 10, 001, 101, 0011, 1011
        // eli 0, 2, 1, 5, 3, 11 integereinä

        koodit.add(muodostaja.muodostaKoodi(2));
        koodit.add(muodostaja.muodostaKoodi(2));
        koodit.add(muodostaja.muodostaKoodi(3));
        koodit.add(muodostaja.muodostaKoodi(3));
        koodit.add(muodostaja.muodostaKoodi(4));
        koodit.add(muodostaja.muodostaKoodi(4));

        assertEquals("Muodostettu koodi väärä", new Long(0), koodit.get(0));
        assertEquals("Muodostettu koodi väärä", new Long(2), koodit.get(1));
        assertEquals("Muodostettu koodi väärä", new Long(1), koodit.get(2));
        assertEquals("Muodostettu koodi väärä", new Long(5), koodit.get(3));
        assertEquals("Muodostettu koodi väärä", new Long(3), koodit.get(4));
        assertEquals("Muodostettu koodi väärä", new Long(11), koodit.get(5));
    }

    @Test
    public void testaaAntaaOikeatKoodit2() {
        OmaList<Long> koodit = new OmaArrayList<Long>();

        // pitäisi luoda koodit ennen kääntöä ympäri:
        // 000, 0010, 00110, 001110, 001111, 010000
        // käännettynä ympäri: 00, 0100, 01100, 011100, 111100 ja  000010
        // eli 0, 4, 12, 28, 60, 2 integereinä

        koodit.add(muodostaja.muodostaKoodi(3));
        koodit.add(muodostaja.muodostaKoodi(4));
        koodit.add(muodostaja.muodostaKoodi(5));
        koodit.add(muodostaja.muodostaKoodi(6));
        koodit.add(muodostaja.muodostaKoodi(6));
        koodit.add(muodostaja.muodostaKoodi(6));

        assertEquals("Muodostettu koodi väärä", new Long(0), koodit.get(0));
        assertEquals("Muodostettu koodi väärä", new Long(4), koodit.get(1));
        assertEquals("Muodostettu koodi väärä", new Long(12), koodit.get(2));
        assertEquals("Muodostettu koodi väärä", new Long(28), koodit.get(3));
        assertEquals("Muodostettu koodi väärä", new Long(60), koodit.get(4));
        assertEquals("Muodostettu koodi väärä", new Long(2), koodit.get(5));
    }

    @Test
    public void kaikkiKooditUniikkeja() {
        OmaList<Long> koodit = new OmaArrayList<Long>();

        for (int i = 4; i < 20; ++i) {
            for (int j = 0; j < 8; ++j) {
                koodit.add(muodostaja.muodostaKoodi(i));
            }
        }

        OmaMap<Long, Long> omaMap = new OmaHashMap<Long, Long>();

        for (int i = 0; i < koodit.size(); ++i) {
            assertNull("Koodi ei ole uniikki!", omaMap.get(koodit.get(i)));
            omaMap.put(koodit.get(i), koodit.get(i));
        }
    }
}
