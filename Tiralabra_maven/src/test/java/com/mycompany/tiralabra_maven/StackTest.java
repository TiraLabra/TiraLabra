/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.structures.Stack;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author szetk
 */
public class StackTest {

    public StackTest() {
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

    @Test
    public void testPushJaPopToimii() {
        Stack<Integer> list = new Stack<Integer>();
        list.push(2);
        int i = list.pop();
        assertEquals(2, i);
        list.push(1);
        list.push(2);
        list.push(3);
        i = list.pop();
        assertEquals(3, i);
        list.push(5);
        i = list.pop();
        assertEquals(5, i);
        i = list.pop();
        assertEquals(2, i);
        i = list.pop();
        assertEquals(1, i);
    }

    @Test
    public void testSize() {
        Stack<Integer> list = new Stack<Integer>();
        list.push(1);
        assertEquals(1, list.size());
        list.push(112);
        assertEquals(2, list.size());
    }

    @Test
    public void testEmpty() {
        Stack<Integer> list = new Stack<Integer>();
        assertTrue(list.empty());
        list.push(1);
        assertFalse(list.empty());
    }
}
