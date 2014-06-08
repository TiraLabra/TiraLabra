package fi.jleh.reittiopas.datastructures;

import java.util.Set;

import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultSetTest {

	@Test
	public void testInsert() {
		Set<Integer> set = new DefaultSet<Integer>();
		
		assertTrue(set.isEmpty());
		
		set.add(5);
		set.add(8);
		set.add(25);
		set.add(3);
		set.add(1);
		
		assertFalse(set.isEmpty());
	}
	
	@Test
	public void testContains() {
		Set<Integer> set = new DefaultSet<Integer>();
		
		set.add(5);
		set.add(8);
		set.add(25);
		set.add(3);
		set.add(1);
		
		assertTrue(set.contains(25));
		assertFalse(set.contains(18));
	}
	
	@Test
	public void testDelete() {
		Set<Integer> set = new DefaultSet<Integer>();
		
		set.add(5);
		set.add(8);
		set.add(25);
		set.add(3);
		set.add(1);
		
		set.remove(8);
		set.remove(3);
		
		assertFalse(set.contains(8));
		assertFalse(set.contains(3));
	}
}
