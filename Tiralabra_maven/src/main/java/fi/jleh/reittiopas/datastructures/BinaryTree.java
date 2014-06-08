package fi.jleh.reittiopas.datastructures;

public class BinaryTree<O> {

	private Integer key;
	private O object;
	private BinaryTree<O> parent;
	private BinaryTree<O> left;
	private BinaryTree<O> right;
	private BinaryTree<O> root;
	
	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public O getObject() {
		return object;
	}

	public void setObject(O object) {
		this.object = object;
	}

	public BinaryTree<O> getParent() {
		return parent;
	}

	public void setParent(BinaryTree<O> parent) {
		this.parent = parent;
	}

	public BinaryTree<O> getLeft() {
		return left;
	}

	public void setLeft(BinaryTree<O> left) {
		this.left = left;
	}

	public BinaryTree<O> getRight() {
		return right;
	}

	public void setRight(BinaryTree<O> right) {
		this.right = right;
	}
	
	/**
	 * Insert new object to tree.
	 * @param key
	 * @param object
	 */
	public void insert(Integer key, O object) {
		BinaryTree<O> node = new BinaryTree<O>();
		BinaryTree<O> x = getRoot();
		BinaryTree<O> parent = x;

		node.setKey(key);
		node.setObject(object);
		
		if (getRoot() == null) {
			this.root = node;
			return;
		}
		
		while (x != null) {
			parent = x;
			
			if (node.getKey() < x.getKey())
				x = x.getLeft();
			else
				x = x.right;
		}
		
		node.setParent(parent);
		
		if (node.getKey() < parent.getKey())
			parent.setLeft(node);
		else
			parent.setRight(node);
	}
	
	/**
	 * Search object with given key.
	 * @param k
	 * @return
	 */
	public O serach(Integer k) {
		BinaryTree<O> searchNode = searchNode(k);
		
		if (searchNode == null)
			return null;
		else
			return searchNode.getObject();
	}

	public BinaryTree<O> searchNode(int k) {
		BinaryTree<O> x = getRoot();
		
		while (x != null && x.getKey() != k) {
			if (k < x.getKey())
				x = x.getLeft();
			else
				x = x.getRight();
		}
		
		if (x == null)
			return null;
		
		return x;
	}
	
	public BinaryTree<O> getRoot() {
		return root;
	}

	public void setRoot(BinaryTree<O> root) {
		this.root = root;
	}
	
	/**
	 * Delete object with given key from tree.
	 * Returns removed object.
	 * @param key
	 * @return
	 */
	public O delete(int key) {
		BinaryTree<O> nodeToRemove = searchNode(key);
		
		if (nodeToRemove.getLeft() == null && nodeToRemove.getRight() == null) {
			BinaryTree<O> parent = nodeToRemove.getParent();
			
			if (parent == null) {
				root = null;
				
				return nodeToRemove.getObject();
			}
			if (nodeToRemove == parent.getLeft()) {
				parent.setLeft(null);
			}
			else {
				parent.setRight(null);
			}
			
			return nodeToRemove.getObject();
		}
		
		if (nodeToRemove.getLeft() == null || nodeToRemove.getRight() == null) {
			BinaryTree<O> child;
			BinaryTree<O> parent;
			
			if (nodeToRemove.getLeft() != null) {
				child = nodeToRemove.getLeft();
			}
			else {
				child = nodeToRemove.getRight();
			}
			
			parent = nodeToRemove.getParent();
			child.setParent(parent);
			
			if (parent == null) {
				root = child;
				return nodeToRemove.getObject();
			}
			
			if (nodeToRemove == parent.getLeft())
				parent.setLeft(child);
			else
				parent.setRight(child);
			
			return nodeToRemove.getObject();
		}
		
		BinaryTree<O> next = getMin(nodeToRemove.getRight());
		BinaryTree<O> child = next.getRight();
		BinaryTree<O> parent = next.getParent();
		O object = nodeToRemove.getObject();
		
		nodeToRemove.setKey(next.getKey());
		nodeToRemove.setObject(next.getObject());
		
		if (parent.getLeft() == next)
			parent.setLeft(child);
		else
			parent.setRight(child);
		
		if (child != null)
			child.setParent(parent);
		
		
		return object;
	}
	
	/**
	 * Get node with lowest key for given subtree.
	 * @param tree
	 * @return
	 */
	public BinaryTree<O> getMin(BinaryTree<O> tree) {
		while (tree.getLeft() != null) {
			tree = tree.getLeft();
		}
		
		return tree;
	}
}
