/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Käyttöliittymä;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jaakko
 */
public class KuvaTest {
    
    public KuvaTest() {
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
     * Test of haeKuva method, of class Kuva.
     */
    @Test
    public void testHaeKuva() {
        System.out.println("haeKuva");
        Kuva instance = new Kuva();
        instance.haeKuva();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of haeLahto method, of class Kuva.
     */
    @Test
    public void testHaeLahto() {
        System.out.println("haeLahto");
        Kuva instance = new Kuva();
        Point expResult = null;
        Point result = instance.haeLahto();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of haeMaali method, of class Kuva.
     */
    @Test
    public void testHaeMaali() {
        System.out.println("haeMaali");
        Kuva instance = new Kuva();
        Point expResult = null;
        Point result = instance.haeMaali();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of paintComponent method, of class Kuva.
     */
    @Test
    public void testPaintComponent() {
        System.out.println("paintComponent");
        Graphics grafiikka = null;
        Kuva instance = new Kuva();
        instance.paintComponent(grafiikka);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mousePressed method, of class Kuva.
     */
    @Test
    public void testMousePressed() {
        System.out.println("mousePressed");
        MouseEvent me = null;
        Kuva instance = new Kuva();
        instance.mousePressed(me);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mouseReleased method, of class Kuva.
     */
    @Test
    public void testMouseReleased() {
        System.out.println("mouseReleased");
        MouseEvent me = null;
        Kuva instance = new Kuva();
        instance.mouseReleased(me);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mouseClicked method, of class Kuva.
     */
    @Test
    public void testMouseClicked() {
        System.out.println("mouseClicked");
        MouseEvent me = null;
        Kuva instance = new Kuva();
        instance.mouseClicked(me);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mouseEntered method, of class Kuva.
     */
    @Test
    public void testMouseEntered() {
        System.out.println("mouseEntered");
        MouseEvent me = null;
        Kuva instance = new Kuva();
        instance.mouseEntered(me);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mouseExited method, of class Kuva.
     */
    @Test
    public void testMouseExited() {
        System.out.println("mouseExited");
        MouseEvent me = null;
        Kuva instance = new Kuva();
        instance.mouseExited(me);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mouseDragged method, of class Kuva.
     */
    @Test
    public void testMouseDragged() {
        System.out.println("mouseDragged");
        MouseEvent me = null;
        Kuva instance = new Kuva();
        instance.mouseDragged(me);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mouseMoved method, of class Kuva.
     */
    @Test
    public void testMouseMoved() {
        System.out.println("mouseMoved");
        MouseEvent me = null;
        Kuva instance = new Kuva();
        instance.mouseMoved(me);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
