package fi.jleh.reittiopas.datastructures;

import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryHeapTest {

	@Test
	public void insertTest() {
		Heap<Integer> heap = new BinaryHeap<Integer>(2);
		
		heap.insert(4, 4);
		heap.insert(3, 3);
		heap.insert(2, 2);
		heap.insert(7, 7);
		heap.insert(1, 1);
		
		int minValue =  heap.getMin();
		
		assertEquals(1, minValue);
	}
	
	@Test
	public void testRemove() {
		Heap<Integer> heap = new BinaryHeap<Integer>();
		
		heap.insert(4, 4);
		heap.insert(3, 3);
		heap.insert(15, 15);
		heap.insert(23, 23);
		heap.insert(7, 7);
		heap.insert(2, 2);
		heap.insert(1, 1);
		
		heap.getAndRemoveMin();
		
		int minValue = heap.getMin();
		
		assertEquals(2, minValue);
	}
	
	@Test
	public void testIsEmpty() {
		Heap<Integer> heap = new BinaryHeap<Integer>(2);
		
		heap.insert(4, 4);
		heap.insert(3, 3);
		
		assertFalse("Heap should not be empty", heap.isEmpty());
		
		heap.getAndRemoveMin();
		heap.getAndRemoveMin();
		
		assertTrue("Heap should be empty", heap.isEmpty());
	}
	
	@Test
	public void testContains() {
		Heap<Integer> heap = new BinaryHeap<Integer>(2);
		
		heap.insert(4, 4);
		heap.insert(3, 3);
		heap.insert(2, 2);
		heap.getAndRemoveMin();
		
		assertTrue(heap.contains(4));
		assertFalse(heap.contains(5));
		assertFalse(heap.contains(2));
	}
}
