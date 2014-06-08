package fi.jleh.reittiopas.datastructures;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class DefaultSet<E> implements Set<E> {

	private BinaryTree<E> tree;
	
	public DefaultSet() {
		this.tree = new BinaryTree<E>();
	}
	
	@Override
	public boolean add(E object) {
		if (!contains(object))
			tree.insert(object.hashCode(), object);
		
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		throw new UnsupportedOperationException("Method not implemented");
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException("Method not implemented");
	}

	@Override
	public boolean contains(Object object) {
		return tree.serach(object.hashCode()) != null;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		throw new UnsupportedOperationException("Method not implemented");
	}

	@Override
	public boolean isEmpty() {
		return tree.getRoot() == null;
	}

	@Override
	public Iterator<E> iterator() {
		throw new UnsupportedOperationException("Method not implemented");
	}

	@Override
	public boolean remove(Object object) {
		E delete = tree.delete(object.hashCode());
		
		return delete != null;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		throw new UnsupportedOperationException("Method not implemented");
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		throw new UnsupportedOperationException("Method not implemented");
	}

	@Override
	public int size() {
		throw new UnsupportedOperationException("Method not implemented");
	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException("Method not implemented");
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		throw new UnsupportedOperationException("Method not implemented");
	}

}
