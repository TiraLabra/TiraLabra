package fi.jleh.reittiopas.datastructures;

import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryTreeTest {

	@Test
	public void testInsert() {
		BinaryTree<Integer> tree = new BinaryTree<Integer>();
		
		tree.insert(5, 5);
		tree.insert(25, 25);
		tree.insert(75, 75);
		tree.insert(15, 15);
	}
	
	@Test
	public void testSearch() {
		BinaryTree<Integer> tree = new BinaryTree<Integer>();
		
		tree.insert(5, 5);
		tree.insert(25, 25);
		tree.insert(75, 75);
		tree.insert(15, 15);
		
		Integer serach = tree.serach(75);
		
		assertNotNull(serach);
		
		serach = tree.serach(55);
		
		assertNull(serach);
	}
	
	@Test
	public void testDelete() {
		BinaryTree<Integer> tree = new BinaryTree<Integer>();
		
		tree.insert(5, 5);
		tree.insert(25, 25);
		tree.insert(75, 75);
		tree.insert(15, 15);
		
		tree.delete(25);
		
		assertNull(tree.serach(25));
		
		tree.delete(5);
		tree.delete(75);
		tree.delete(15);
		
		assertNull(tree.getRoot());
	}
}
