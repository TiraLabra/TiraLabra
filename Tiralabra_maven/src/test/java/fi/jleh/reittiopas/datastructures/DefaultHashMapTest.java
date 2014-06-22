package fi.jleh.reittiopas.datastructures;

import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultHashMapTest {

	@Test
	public void testHashGenerator() {
		DefaultHashMap<Integer, String> map = new DefaultHashMap<Integer, String>();
		
		System.out.println(map.calculateHash(15));
		System.out.println(map.calculateHash(5));
		System.out.println(map.calculateHash(215231));
		System.out.println(map.calculateHash(252121));
		System.out.println(map.calculateHash(4));
		System.out.println(map.calculateHash(2));
	}
	
	@Test
	public void insertTest() {
		DefaultHashMap<Integer, String> map = new DefaultHashMap<Integer, String>();
		
		map.put(15, "A");
		map.put(5, "B");
		map.put(215231, "C");
		map.put(252121, "D");
	}
	
	@Test
	public void getTest() {
		DefaultHashMap<Integer, String> map = new DefaultHashMap<Integer, String>();
		
		map.put(15, "A");
		map.put(5, "B");
		map.put(215231, "C");
		map.put(252121, "D");
		
		assertEquals("B", map.get(5));
		assertEquals("C", map.get(215231));
	}
}
