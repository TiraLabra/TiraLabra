/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

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
public class KoordinaatitTest {
    Koordinaatit k1;
    Koordinaatit k2;
    Koordinaatit k3;
    Koordinaatit k4;
    
    public KoordinaatitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        k1 = new Koordinaatit(0, 0);
        k2 = new Koordinaatit(5, 7);
        k3 = new Koordinaatit(-5, -3);
        k4 = new Koordinaatit(-5, 3);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void KoordinaatitTest() {
        assertEquals(k1.getX(), 0);
        assertEquals(k1.getY(), 0);
        
        assertEquals(k2.getX(), 5);
        assertEquals(k2.getY(), 7);
        
        assertEquals(k3.getX(), 0);
        assertEquals(k3.getY(), 0);
        
        assertEquals(k4.getX(), 0);
        assertEquals(k4.getY(), 3);
    }
    
    @Test
    public void setXNegatiivinenTest() {
        k1.setX(-8);
        assertEquals(k1.getX(), 0);
    }
    
    @Test
    public void setXPositiivinenTest() {
        k1.setX(8);
        assertEquals(k1.getX(), 8);
    }
    
    @Test
    public void setXNollaTest() {
        k1.setX(0);
        assertEquals(k1.getX(), 0);
    }
    
    @Test
    public void setYNegatiivinenTest() {
        k1.setY(-8);
        assertEquals(k1.getY(), 0);
    }
    
    @Test
    public void setYPositiivinenTest() {
        k1.setY(8);
        assertEquals(k1.getY(), 8);
    }
    
    @Test
    public void setYNollaTest() {
        k1.setY(0);
        assertEquals(k1.getY(), 0);
    }
}
