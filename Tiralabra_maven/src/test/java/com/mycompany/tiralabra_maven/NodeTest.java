/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven;

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
public class NodeTest {
    
    public NodeTest() {
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
     * Test of getH method, of class Node.
     */
    @Test
    public void testGetH() {
        System.out.println("getH");
        Node instance = null;
        int expResult = 0;
        int result = instance.getH();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setH method, of class Node.
     */
    @Test
    public void testSetH() {
        System.out.println("setH");
        int h = 0;
        Node instance = null;
        instance.setH(h);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of onkoSeina method, of class Node.
     */
    @Test
    public void testOnkoSeina() {
        System.out.println("onkoSeina");
        Node node = null;
        Node instance = null;
        boolean expResult = false;
        boolean result = instance.onkoSeina(node);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPrevNode method, of class Node.
     */
    @Test
    public void testSetPrevNode() {
        System.out.println("setPrevNode");
        Node prevNode = null;
        Node instance = null;
        instance.setPrevNode(prevNode);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrevNode method, of class Node.
     */
    @Test
    public void testGetPrevNode() {
        System.out.println("getPrevNode");
        Node instance = null;
        Node expResult = null;
        Node result = instance.getPrevNode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMatka method, of class Node.
     */
    @Test
    public void testGetMatka() {
        System.out.println("getMatka");
        Node instance = null;
        int expResult = 0;
        int result = instance.getMatka();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMatka method, of class Node.
     */
    @Test
    public void testSetMatka_0args() {
        System.out.println("setMatka");
        Node instance = null;
        instance.setMatka();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMatka method, of class Node.
     */
    @Test
    public void testSetMatka_int() {
        System.out.println("setMatka");
        int matka = 0;
        Node instance = null;
        instance.setMatka(matka);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
