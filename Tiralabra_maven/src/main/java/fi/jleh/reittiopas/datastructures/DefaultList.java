package fi.jleh.reittiopas.datastructures;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DefaultList<E> implements List<E> {

	private E[] array;
	private int position;
	
	@SuppressWarnings("unchecked")
	public DefaultList (int length) {
		this.array = (E[]) new Object[length];
		position = 0;
	}
	
	public DefaultList () {
		this(100);
	}
	
	@Override
	public boolean add(E e) {
		if (position >= array.length)
			resize();
		
		array[position++] = e;
		
		return true;
	}

	@Override
	public void add(int index, E element) {
		throw new UnsupportedOperationException("Method not implemented");
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		for (E element : c) {
			add(element);
		}
		
		return true;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		throw new UnsupportedOperationException("Method not implemented");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		this.array = (E[]) new Object[array.length];
		position = 0;
	}

	@Override
	public boolean contains(Object o) {
		for (E e : array) {
			if (e != null && e.equals(o))
				return true;
		}
		
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException("Method not implemented");
	}

	@Override
	public E get(int index) {
		return array[index];
	}

	@Override
	public int indexOf(Object o) {
		for (int i = 0; i < position; i++)
			if (array[i].equals(o))
				return i;
		
		return -1;
	}

	@Override
	public boolean isEmpty() {
		return position == 0;
	}

	@Override
	public Iterator<E> iterator() {
		return new DefaultListIterator<>(array);
	}

	@Override
	public int lastIndexOf(Object o) {
		throw new UnsupportedOperationException("Method not implemented");
	}

	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException("Method not implemented");
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		throw new UnsupportedOperationException("Method not implemented");
	}

	@Override
	public boolean remove(Object o) {
		remove(indexOf(o));
		
		return true;
	}

	@Override
	public E remove(int index) {
		E object = get(index);
		array[index] = null;
		
		for (int i = position - 1; i >= index; i--) {
			array[i - 1] = array[i];
		}
		
		position--;
		
		return object;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException("Method not implemented");
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException("Method not implemented");
	}

	@Override
	public E set(int index, E element) {
		throw new UnsupportedOperationException("Method not implemented");
	}

	@Override
	public int size() {
		return position;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException("Method not implemented");
	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException("Method not implemented");
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException("Method not implemented");
	}

	private void resize() {
		@SuppressWarnings("unchecked")
		E[] extendedArray = (E[]) new Object[array.length * 2];
		
		// Copy array contents
		for (int i = 0; i < array.length; i++) {
			extendedArray[i] = array[i];
		}
		
		array = extendedArray;
	}
}
