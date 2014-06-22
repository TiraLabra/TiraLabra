package fi.jleh.reittiopas.datastructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class PerformanceTests {

	@Test
	public void listTest() {	
		List<Integer> list = new ArrayList<Integer>();
		List<Integer> list2 = new DefaultList<Integer>(10);
		
		long time1 = listOperations(list);
		long time2 = listOperations(list2);
		
		System.out.println("ArrayList " + time1 + " ms");
		System.out.println("DefaultList " + time2 + " ms");
	}
	
	private long listOperations(List<Integer> list) {
		long start = System.currentTimeMillis();
		
		for (int i = 0; i < 50000; i++) {
			list.add(i);
		}
		
		return System.currentTimeMillis() - start;
	}
	
	@Test
	public void hashMapTest() {
		Map<Integer, Integer> map1 = new HashMap<Integer, Integer>();
		Map<Integer, Integer> map2 = new DefaultHashMap<Integer, Integer>(16);
		
		long time1 = hashMapOperations(map1);
		long time2 = hashMapOperations(map2);
		
		System.out.println("HashMap " + time1 + " ms");
		System.out.println("DefaultHashMap " + time2 + " ms");
		
		long time3 = hashMapOperations2(map1);
		long time4 = hashMapOperations2(map2);
		
		System.out.println("HashMap " + time3 + " ms");
		System.out.println("DefaultHashMap " + time4 + " ms");
	}
	
	private long hashMapOperations(Map<Integer, Integer> map) {
		long start = System.currentTimeMillis();
		
		for (int i = 0; i < 50000; i++) {
			map.put(i, i);
		}
		
		return System.currentTimeMillis() - start;
	}
	
	private long hashMapOperations2(Map<Integer, Integer> map) {
		long start = System.currentTimeMillis();
		
		for (int i = 0; i < 100; i++) {
			map.get(i * 15);
		}
		
		return System.currentTimeMillis() - start;
	}
	
	@Test
	public void setTest() {
		Set<Integer> set1 = new TreeSet<Integer>();
		Set<Integer> set2 = new DefaultSet<Integer>();
		
		long time1 = setOperations(set1);
		long time2 = setOperations(set2);
		
		System.out.println("TreeSet " + time1 + " ms");
		System.out.println("DefaultSet " + time2 + " ms");
	}
	
	private long setOperations(Set<Integer> set) {
		long start = System.currentTimeMillis();
		
		for (int i = 0; i < 25000; i++) {
			set.add(i);
		}
		
		for (int i = 0; i < 25000; i++) {
			set.add(i);
		}
		
		return System.currentTimeMillis() - start;
	}
}
