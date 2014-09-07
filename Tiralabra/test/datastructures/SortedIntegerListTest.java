package datastructures;

import java.util.Random;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Sebastian Björkqvist
 */
public class SortedIntegerListTest {

    public SortedIntegerListTest() {
    }

    @Test
    public void testSizeOfEmptyList() {
        SortedIntegerList list = new SortedIntegerList();

        assertEquals(0, list.getSize());
    }

    @Test
    public void testSizeAfterOneAddedElement() {
        SortedIntegerList list = new SortedIntegerList();

        list.addIntegerToList(1, true);

        assertEquals(1, list.getSize());
    }

    @Test
    public void testSizeAfterTwoAddedElements() {
        SortedIntegerList list = new SortedIntegerList();

        list.addIntegerToList(1, true);
        list.addIntegerToList(2, true);

        assertEquals(2, list.getSize());
    }

    @Test
    public void testSizeAfterThreeSameElementsAdded() {
        SortedIntegerList list = new SortedIntegerList();

        list.addIntegerToList(1, true);
        list.addIntegerToList(1, true);
        list.addIntegerToList(1, true);

        assertEquals(3, list.getSize());
    }

    @Test
    public void testTraversal() {
        SortedIntegerList list = new SortedIntegerList();

        list.addIntegerToList(1, true);
        list.addIntegerToList(3, true);
        list.addIntegerToList(11, true);

        IntegerNode smallest = list.getSmallestNode();
        assertEquals(null, smallest.getPrev());
        assertEquals(1, (int) smallest.getValue());
        IntegerNode next = smallest.getNext();
        assertEquals(3, (int) next.getValue());
        IntegerNode next2 = next.getNext();
        assertEquals(11, (int) next2.getValue());
        assertEquals(null, next2.getNext());
    }

    @Test
    public void testTraversal2() {
        SortedIntegerList list = new SortedIntegerList();

        list.addIntegerToList(3, true);
        list.addIntegerToList(2, true);
        list.addIntegerToList(10, true);
        list.addIntegerToList(7, true);
        list.addIntegerToList(-1, true);
        list.addIntegerToList(0, true);

        IntegerNode largest = list.getLargestNode();
        assertEquals(null, largest.getNext());
        assertEquals(10, (int) largest.getValue());
        IntegerNode prev = largest.getPrev();
        assertEquals(7, (int) prev.getValue());
        prev = prev.getPrev();
        assertEquals(3, (int) prev.getValue());
        prev = prev.getPrev();
        assertEquals(2, (int) prev.getValue());
        prev = prev.getPrev();
        assertEquals(0, (int) prev.getValue());
        prev = prev.getPrev();
        assertEquals(-1, (int) prev.getValue());
        prev = prev.getPrev();
        assertEquals(null, prev);
    }

    @Test
    public void testTraversal3() {
        SortedIntegerList list = new SortedIntegerList();

        list.addIntegerToList(3, false);
        list.addIntegerToList(2, false);
        list.addIntegerToList(10, true);
        list.addIntegerToList(7, true);
        list.addIntegerToList(-1, true);
        list.addIntegerToList(0, false);

        IntegerNode largest = list.getLargestNode();
        assertEquals(null, largest.getNext());
        assertEquals(10, (int) largest.getValue());
        IntegerNode prev = largest.getPrev();
        assertEquals(7, (int) prev.getValue());
        prev = prev.getPrev();
        assertEquals(3, (int) prev.getValue());
        prev = prev.getPrev();
        assertEquals(2, (int) prev.getValue());
        prev = prev.getPrev();
        assertEquals(0, (int) prev.getValue());
        prev = prev.getPrev();
        assertEquals(-1, (int) prev.getValue());
        prev = prev.getPrev();
        assertEquals(null, prev);
    }

    @Test
    public void testTraversal4() {
        SortedIntegerList list = new SortedIntegerList();

        for (int i = 0; i < 100; i++) {
            list.addIntegerToList(i, false);
        }

        IntegerNode current = list.getSmallestNode();

        for (int i = 0; i < 100; i++) {
            assertNotNull(current);
            assertEquals(i, (int) current.getValue());
            current = current.getNext();
        }

        assertEquals(100, list.getSize());

    }

    @Test
    public void testTraversal5() {
        SortedIntegerList list = new SortedIntegerList();

        for (int i = 100; i > 0; i--) {
            list.addIntegerToList(i, true);
        }

        IntegerNode current = list.getLargestNode();

        for (int i = 100; i > 0; i--) {
            assertNotNull(current);
            assertEquals(i, (int) current.getValue());
            current = current.getPrev();
        }

        assertEquals(100, list.getSize());

    }

    @Test
    public void testTraversalPseudoRandom() {
        SortedIntegerList list = new SortedIntegerList();

        Random random = new Random(1);

        for (int i = 0; i < 100; i++) {
            list.addIntegerToList(random.nextInt(200) - 100, true);
        }

        IntegerNode smaller = list.getSmallestNode();
        IntegerNode larger = smaller.getNext();

        int howManyChecked = 1;
        while (larger != null) {
            assertTrue(smaller.getValue() <= larger.getValue());
            IntegerNode newLarger = larger.getNext();
            smaller = larger;
            larger = newLarger;
            howManyChecked++;
        }

        assertEquals(100, howManyChecked);
        assertEquals(100, list.getSize());

    }

    @Test
    public void testTraversalPseudoRandom2() {
        SortedIntegerList list = new SortedIntegerList();

        Random random = new Random(2);

        for (int i = 0; i < 200; i++) {
            list.addIntegerToList(random.nextInt(1000) - 500, false);
        }

        IntegerNode larger = list.getLargestNode();
        IntegerNode smaller = larger.getPrev();
        
        int howManyChecked = 1;

        while (smaller != null) {
            assertTrue(larger.getValue() >= smaller.getValue());
            IntegerNode newSmaller = smaller.getPrev();
            larger = smaller;
            smaller = newSmaller;
            howManyChecked++;            
        }

        assertEquals(200, list.getSize());
        assertEquals(200, howManyChecked);
    }

}
