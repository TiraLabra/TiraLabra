package com.mycompany.tiralabra_maven.datastructures;

import com.mycompany.tiralabra_maven.datastructures.List.Node;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ListTest {

    List<Integer> list;

    @Before
    public void setUp() {
        list = new List<>();
    }

    @Test
    public void testInsert() {
        assertTrue(list.isEmpty());
        list.insertLast(1);
        assertTrue(list.contains(1));
        assertFalse(list.contains(2));
        assertFalse(list.isEmpty());

        list.insertLast(2);
        assertTrue(list.contains(2));
    }

    @Test
    public void testSearch() {
        list.insertLast(1);
        list.insertLast(2);
        Node p = list.head;
        list.insertLast(3);
        assertEquals(p, list.search(2));
    }

    @Test
    public void testDeleteNode() {
        list.insertLast(1);
        assertTrue(list.contains(1));
        list.delete(list.head);
        assertFalse(list.contains(1));

        list.insertLast(2);
        assertTrue(list.contains(2));
        list.delete(list.head);
        assertFalse(list.contains(2));

        list.insertLast(3);
        assertTrue(list.contains(3));
        list.delete(list.head);
        assertFalse(list.contains(3));
    }

    @Test
    public void testDelete() {
        list.insertLast(1);
        list.insertLast(2);
        Node p = list.head;
        list.insertLast(3);
        assertTrue(list.contains(2));
        list.delete(2);
        assertFalse(list.contains(2));
        assertFalse(list.contains(4));
        list.delete(4);
        assertTrue(list.contains(1));
        assertTrue(list.contains(3));
        assertFalse(list.contains(4));
    }

}
