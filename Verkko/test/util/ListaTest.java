/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Arvoitusmies
 */
public class ListaTest {

    public ListaTest() {
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

    /**
     * Test of koko method, of class Lista.
     */
    @Test
    public void testKoko() {
        System.out.println("koko");
        Lista<Integer> instance = new Lista();
        int expResult = 0;
        int result = instance.koko();
        assertEquals(expResult, result);
        for (int i = 0; i < 3; i++) {
            instance.lisaa(1);
        }
        assertEquals(3, instance.koko());

    }

    /**
     * Test of get method, of class Lista.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        Lista<Integer> instance = new Lista();
        Integer[] a = {1, 5, 9, 7, 3, 0};
        for (Integer integer : a) {
            instance.lisaa(integer);
        }
        for (int i = 0; i < a.length; i++) {
            assertEquals(a[i], instance.get(i));
        }
    }

    /**
     * Test of muuta method, of class Lista.
     */
    @Test
    public void testMuuta() {
        System.out.println("muuta");
        int indeksi = 0;
        Integer uusiArvo = 123;
        Lista<Integer> instance = new Lista();
        instance.lisaa(79798);
        instance.muuta(indeksi, uusiArvo);
        assertEquals(instance.get(0), uusiArvo);
    }

    /**
     * Test of contains method, of class Lista.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        Object o = null;
        Lista<Integer> instance = new Lista();
        instance.lisaa(123);
        assertTrue(instance.contains(123));
        assertFalse(instance.contains(1234));

    }

}
