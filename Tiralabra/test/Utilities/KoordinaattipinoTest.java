/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Domain.Koordinaatit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Emleri
 */
public class KoordinaattipinoTest {

    Koordinaattipino p;

    public KoordinaattipinoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        p = new Koordinaattipino();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void koordinaattipinoKonstruktoriTest() {
        assertTrue(p.isEmpty());
    }

    @Test
    public void pushTest() {
        p.push(new Koordinaatit(0, 0));
        assertTrue(!p.isEmpty());
        assertEquals(p.getKoko(), 1);
        p.push(new Koordinaatit(5, 5));
        assertEquals(p.getKoko(), 2);
    }

    @Test
    public void popTest() {
        Koordinaatit k = new Koordinaatit(0, 0);
        p.push(k);
        Koordinaatit k2 = p.pop();
        assertTrue(p.isEmpty());
        assertEquals(k, k2);
        p.push(k);
        p.push(new Koordinaatit(5, 5));
        k = p.pop();
        assertEquals(k.getX(), 5);
        assertEquals(k.getY(), 5);
        assertEquals(p.getKoko(), 1);
        assertTrue(!p.isEmpty());
    }
    
    @Test
    public void isEmptyTest() {
        assertTrue(p.isEmpty());
        p.push(new Koordinaatit(0,0));
        assertTrue(!p.isEmpty());
    }
}
