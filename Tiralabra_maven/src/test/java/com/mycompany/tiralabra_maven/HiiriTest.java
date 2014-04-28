/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven;

import java.awt.Image;
import java.awt.event.KeyEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tiina
 */
public class HiiriTest {
    
    public HiiriTest() {
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
     * Test of move method, of class Hiiri.
     */
    @Test
    public void testMove() {
        System.out.println("move");
        Hiiri instance = null;
        instance.move();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getX method, of class Hiiri.
     */
    @Test
    public void testGetX() {
        System.out.println("getX");
        Hiiri instance = null;
        int expResult = 0;
        int result = instance.getX();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getY method, of class Hiiri.
     */
    @Test
    public void testGetY() {
        System.out.println("getY");
        Hiiri instance = null;
        int expResult = 0;
        int result = instance.getY();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXcoord method, of class Hiiri.
     */
    @Test
    public void testGetXcoord() {
        System.out.println("getXcoord");
        Hiiri instance = null;
        int expResult = 0;
        int result = instance.getXcoord();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getYcoord method, of class Hiiri.
     */
    @Test
    public void testGetYcoord() {
        System.out.println("getYcoord");
        Hiiri instance = null;
        int expResult = 0;
        int result = instance.getYcoord();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXcoord method, of class Hiiri.
     */
    @Test
    public void testSetXcoord() {
        System.out.println("setXcoord");
        int x_coord = 0;
        Hiiri instance = null;
        instance.setXcoord(x_coord);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setYcoord method, of class Hiiri.
     */
    @Test
    public void testSetYcoord() {
        System.out.println("setYcoord");
        int y_coord = 0;
        Hiiri instance = null;
        instance.setYcoord(y_coord);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCoord method, of class Hiiri.
     */
    @Test
    public void testSetCoord() {
        System.out.println("setCoord");
        int x_coord = 0;
        int y_coord = 0;
        Hiiri instance = null;
        instance.setCoord(x_coord, y_coord);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getImage method, of class Hiiri.
     */
    @Test
    public void testGetImage() {
        System.out.println("getImage");
        Hiiri instance = null;
        Image expResult = null;
        Image result = instance.getImage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of keyPressed method, of class Hiiri.
     */
    @Test
    public void testKeyPressed() {
        System.out.println("keyPressed");
        KeyEvent e = null;
        Hiiri instance = null;
        instance.keyPressed(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of keyReleased method, of class Hiiri.
     */
    @Test
    public void testKeyReleased() {
        System.out.println("keyReleased");
        KeyEvent e = null;
        Hiiri instance = null;
        instance.keyReleased(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
