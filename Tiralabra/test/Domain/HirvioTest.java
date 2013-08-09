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
public class HirvioTest {
    Hirvio h1;
    Hirvio h2;
    
    public HirvioTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        h1 = new Hirvio(new Koordinaatit(1,0));
        h2 = new Hirvio(new Koordinaatit(5,6));
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void konstruktoriTest() {
        assertEquals(h1.getKoordinaatit().getX(), 1);
        assertEquals(h1.getKoordinaatit().getY(), 0);
    }
    
    // Luokassa tällä hetkellä vain yksinkertaisia gettereitä ja settereitä,
    // ei järkeä testata suuremmin erikseen...
}
