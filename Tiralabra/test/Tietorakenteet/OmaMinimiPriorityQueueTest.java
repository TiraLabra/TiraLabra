/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet;

import java.util.Comparator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class OmaMinimiPriorityQueueTest {

    OmaQueue<Integer> jono;

    public OmaMinimiPriorityQueueTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        jono = new OmaMinimiPriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
    }

    @After
    public void tearDown() {
    }

    @Test
    public void uusiJonoOnTyhja() {
        assertTrue("Uusi jono ei ole tyhja", jono.isEmpty());
    }

    @Test
    public void uudenJononKokoOnNolla() {
        assertTrue("Uuden jonon koko ei ole nolla", jono.size() == 0);
    }

    @Test
    public void kokoOikeaKunLisataanAlkiota() {
        jono.push(4);
        jono.push(12);

        assertTrue("Jonon koko väärä", jono.size() == 2);
    }

    @Test
    public void kokoOikeaKunPopataanAlkiota() {
        jono.push(4);
        jono.push(12);
        jono.push(15);
        jono.pop();
        assertTrue("Jonon koko väärä", jono.size() == 2);
    }

    @Test
    public void alkiotTulevatOikeassaJarjestyksessa() {
        jono.push(4);
        jono.push(5);
        jono.push(123);
        jono.push(24);
        jono.push(19);
        jono.push(2);


        assertTrue("Jono palautti väärän alkion", jono.pop() == 2);
        assertTrue("Jono palautti väärän alkion", jono.pop() == 4);
        assertTrue("Jono palautti väärän alkion", jono.pop() == 5);
        assertTrue("Jono palautti väärän alkion", jono.pop() == 19);
        assertTrue("Jono palautti väärän alkion", jono.pop() == 24);
        assertTrue("Jono palautti väärän alkion", jono.pop() == 123);
    }

    @Test
    public void alkiotTulevatOikeassaJarjestyksessaKunLisataanKesken() {
        jono.push(4);
        jono.push(8);
        jono.push(123);
        jono.push(28);
        jono.push(19);
        jono.push(2);


        assertTrue("Jono palautti väärän alkion", jono.pop() == 2);
        assertTrue("Jono palautti väärän alkion", jono.pop() == 4);
        assertTrue("Jono palautti väärän alkion", jono.pop() == 8);

        jono.push(200);
        jono.push(1);
        assertTrue("Jono palautti väärän alkion", jono.pop() == 1);
        assertTrue("Jono palautti väärän alkion", jono.pop() == 19);
        assertTrue("Jono palautti väärän alkion", jono.pop() == 28);
        assertTrue("Jono palautti väärän alkion", jono.pop() == 123);
        assertTrue("Jono palautti väärän alkion", jono.pop() == 200);
    }
}
