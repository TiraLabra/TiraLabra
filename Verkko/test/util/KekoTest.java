/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package util;

import java.util.ArrayList;
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

    public KekoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }
    Keko<Integer> keko;

    @Before
    public void setUp() {
        comparator = new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 == o2) {
                    return 0;
                }
                if (o1 < o2) {
                    return -1;
                }
                return 1;
            }
        };
        keko = new Keko<>(comparator);

    }
    private Comparator<Integer> comparator;

    @After
    public void tearDown() {
    }

    /**
     * Test of lisaa method, of class Keko.
     */
    @Test
    public void testLisaaJaPoista() {

        final int N = 123;

        ArrayList<Integer> lisattavat = new ArrayList<Integer>();
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

}
