/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package util;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Arvoitusmies
 */
public class TaulukkoTest {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    public TaulukkoTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of poistaNullit method, of class Taulukko.
     */
    @Test
    public void testPoistaNullit() {
        Object a = 123;
        Object b = 456;
        Object c = 789;
        Object[] ks = {null, a, null, b, null, c, null, null, null};
        Object[] expResult = {a, b, c};
        Object[] result = Taulukko.poistaNullit(ks);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of swap method, of class Taulukko.
     */
    @Test
    public void testSwap() {
        Object a = 123;
        Object b = 456;
        Object c = 789;
        Object[] taulukko = {a, b, c};
        Taulukko.swap(taulukko, 0, 1);
        Taulukko.swap(taulukko, 0, 2);
        Object[] exp = {c, a, b};
        assertArrayEquals(exp, taulukko);
    }

    /**
     * Test of sekoita method, of class Taulukko.
     */
    @Test
    public void testSekoita() {
        Integer a = 123;
        Integer b = 456;
        Integer c = 789;
        System.out.println("sekoita");
        Integer[] taulukko = {a, b, c};
        Taulukko.sekoita(taulukko);
        int aFreq = 0, bFreq = 0, cFreq = 0;
        for (Integer i : taulukko) {
            if (i.equals(a)) {
                aFreq++;
            } else if(i.equals(b)){
                bFreq++;
            } else if(i.equals(c)){
                cFreq++;
            }
        }
        assertEquals(aFreq, 1);
        assertEquals(bFreq, 1);
        assertEquals(cFreq, 1);
        assertEquals(taulukko, 3);
    }

}
