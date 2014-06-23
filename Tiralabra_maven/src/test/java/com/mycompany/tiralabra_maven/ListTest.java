package com.mycompany.tiralabra_maven;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mycompany.tiralabra_maven.structures.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sami
 */
public class ListTest {

    public ListTest() {
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
    public void testLisaysToimii() {
        List<Integer> list = new List<Integer>();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        int i = list.get(4);
        assertEquals(4, i);
        i = list.get(2);
        assertEquals(2, i);
        i = list.get(3);
        assertEquals(3, i);
    }

    @Test
    public void testLisaysYliAlotusKoonToimii() {
        List<Integer> list = new List<Integer>();
        for (int i = 0; i < 106; i++) {
            list.add(i);
        }
        int i = list.get(4);
        assertEquals(4, i);
        i = list.get(50);
        assertEquals(50, i);
        i = list.get(51);
        assertEquals(51, i);
        i = list.get(105);
        assertEquals(105, i);
    }

    @Test
    public void testSize() {
        List<Integer> list = new List<Integer>();
        list.add(1);
        assertEquals(1, list.size());
        list.add(112);
        assertEquals(2, list.size());
    }

    @Test
    public void testSizeToimiiYliAlotusKoon() {
        List<Integer> list = new List<Integer>();
        for (int i = 0; i < 106; i++) {
            list.add(i);
        }
        assertEquals(106, list.size());

    }

    @Test
    public void testEmpty() {
        List<Integer> list = new List<Integer>();
        assertTrue(list.empty());
        list.add(1);
        assertFalse(list.empty());
    }

    @Test
    public void testPoistoToimii() {
        List<Integer> list = new List<Integer>();
        list.add(1);
        int i = list.get(0);
        assertEquals(1, i);
        list.remove(0);
        assertEquals(null, list.get(0));
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        i = list.get(3);
        assertEquals(3, i);
        list.remove(3);
        assertEquals(null, list.get(3));
        
        i = list.get(1);
        assertEquals(1, i);
        list.remove(1);
        i = list.get(1);
        assertEquals(2, i);
        

    }

}
