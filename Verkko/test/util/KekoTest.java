/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author moversti
 */
public class KekoTest {

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
    Keko<Integer> keko;

    /**
     *
     */
    private Comparator<Integer> comparator;

    /**
     *
     */
    public KekoTest() {
        comparator = new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 < o2) {
                    return -1;
                }
                if (o1 > o2) {
                    return 1;
                }
                return 0;
            }
        };
        keko = new Keko<>(comparator);
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
     * Test of lisaa method, of class Keko.
     */
    @Test
    public void testLisaaNEnsimaistaKokonaislukuaSatunnaisestiJaPoistaOikeassaJarjestyksessa() {

        final int N = 123;

        ArrayList<Integer> lisattavat = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            lisattavat.add(i);
        }
        Collections.shuffle(lisattavat);
        for (Integer integer : lisattavat) {
            keko.lisaa(integer);
        }
        Collections.sort(lisattavat, comparator);
        for (int i = 0; i < N; i++) {
            assertEquals(lisattavat.get(i), keko.poista());
        }

    }

    /**
     *
     */
    @Test
    public void testKasvataTaulukko() {
        final Integer randomi = (int) Math.round(Math.random() * 100);

        keko.lisaa(randomi);
        assertEquals(keko.getTaulukonLenght(), Keko.ALKUKOKO);
        keko.kasvataTaulukko();
        assertEquals(keko.getTaulukonLenght(), Keko.ALKUKOKO * Keko.KASVUKERROIN);
        assertEquals(randomi, keko.poista());
        assertNull(null, keko.poista());

    }

    /**
     *
     */
    @Test
    public void testContains() {
        final Integer randomi = (int) Math.round(Math.random() * 100);

        keko.lisaa(randomi);
        assertFalse(keko.contains(-2));
        assertTrue(keko.contains(randomi));

    }

    /**
     *
     */
    @Test
    public void testIsEmptyWhenEmpty() {
        assertTrue(keko.isEmpty());
    }

    /**
     *
     */
    @Test
    public void testIsEmptyWhenNotEmpty() {
        keko.lisaa(Integer.MIN_VALUE);
        assertFalse(keko.isEmpty());
    }

    /**
     *
     */
    @Test
    public void testEttaVoidaanLaittaaMontaSamaa() {
        final Integer randomi = (int) Math.round(Math.random() * 100);
        for (int i = 0; i < 10; i++) {
            keko.lisaa(randomi);
        }
        for (int i = 0; i < 10; i++) {
            assertEquals(randomi, keko.poista());
        }
    }

    /**
     *
     */
    @Test
    public void testPoistaTietty() {
        final Integer randomi1 = (int) Math.round(Math.random() * 10);
        final Integer randomi2 = (int) Math.round(Math.random() * 10 + 100);
        keko.lisaa(randomi2);
        keko.lisaa(randomi1);
        keko.lisaa(randomi2);
        keko.lisaa(randomi2);
        keko.lisaa(randomi1);
        keko.lisaa(randomi2);
        keko.lisaa(randomi1);
        final Integer tissit = 715517;
        keko.lisaa(tissit);
        keko.lisaa(randomi2);
        assertNull(keko.poista(-55378008));
        assertEquals(tissit, keko.poista(tissit));
        assertEquals(randomi1, keko.poista(randomi1));
    }

    /**
     *
     */
    @Test
    public void testBuildHeap() {
        /*
         1d100 = 63

         1d100 = 17

         1d100 = 31

         1d100 = 45

         1d100 = 5

         1d100 = 84

         1d100 = 69


         */
        Integer[] alku = {null, 63, 17, 31, 45, 5, 84, 69};
        Integer[] exp = {null, 5, 17, 31, 45, 63, 84, 69};
        keko = new Keko<>(alku, comparator);
        assertArrayEquals(exp, keko.taulukko);
    }

    /**
     *
     */
    @Test
    public void testKostruktoriJossaNollaEiOleNull() {
        comparator = new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 < o2) {
                    return -1;
                }
                if (o1 > o2) {
                    return 1;
                }
                return 0;
            }
        };
        Integer[] taulukko = {2, 1};
        Keko<Integer> k = new Keko<>(taulukko, comparator);
        Integer[] exp = {null, 1, 2};
        final Object[] taulukko1 = k.taulukko;
        int koko = k.getKoko();
        final Integer[] saatu = Arrays.copyOf(taulukko1, koko + 1, Integer[].class);
        assertArrayEquals(exp, saatu);
    }

}
