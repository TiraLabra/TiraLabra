package fi.jleh.reittiopas.datastructures;

import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

public class ListTest {

	@Test
	public void createListTest() {
		List<Integer> list = new DefaultList<Integer>();
		assertNotNull(list);
	}
	
	@Test
	public void addToListTest() {
		List<Integer> list = new DefaultList<Integer>();
		
		list.add(1);
		list.add(2);
		list.add(3);
		
		int size = list.size();
		
		assertEquals(3, size);
	}
	
	@Test
	public void addToListTest2() {
		List<Integer> list = new DefaultList<Integer>(2);
		
		list.add(1);
		list.add(2);
		list.add(3);
		
		int size = list.size();
		
		assertEquals(3, size);
	}
	
	@Test
	public void containsTest() {
		List<Integer> list = new DefaultList<Integer>();
		
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		
		assertTrue(list.contains(4));
		assertFalse(list.contains(15));
	}
	
	@Test
	public void getObjectTest() {
		List<Integer> list = new DefaultList<Integer>();
		
		list.add(1);
		list.add(25);
		list.add(3);
		
		int object = list.get(1);
		
		assertEquals(25, object);
	}
	
	@Test
	public void isEmptyTest() {
		List<Integer> list = new DefaultList<Integer>();
		
		assertTrue(list.isEmpty());
		
		list.add(1);
		
		assertFalse(list.isEmpty());
		
		list.remove(1);
		
		assertTrue(list.isEmpty());
	}
	
	@Test
	public void indexOfTest() {
		List<Integer> list = new DefaultList<Integer>();
		
		list.add(1);
		list.add(25);
		list.add(3);
		list.add(37);
		list.add(5);
		
		assertEquals(2, list.indexOf(3));
		assertEquals(-1, list.indexOf(13));
		assertEquals(4, list.indexOf(5));
	}
	
	@Test
	public void removeTest() {
		List<Integer> list = new DefaultList<Integer>();
		
		list.add(1);
		list.add(25);
		list.add(3);
		list.add(37);
		list.add(5);
		
		assertEquals(2, list.indexOf(3));
		list.remove(2);
		
		assertEquals(-1, list.indexOf(3));
		assertEquals(4, list.size());
	}
	
	@Test
	public void removeTest2() {
		List<Integer> list = new DefaultList<Integer>();
		
		list.add(1);
		list.add(25);
		list.add(3);
		list.add(37);
		list.add(5);
		
		list.remove(new Integer(25));
		
		assertEquals(-1, list.indexOf(25));
		assertEquals(4, list.size());
	}
	
	@Test
	public void iteratorTest() {
		List<Integer> list = new DefaultList<Integer>();
		
		list.add(1);
		list.add(25);
		list.add(3);
		list.add(37);
		list.add(5);
		
		for (int i : list) {
			System.out.println(i);
		}
	}
}
