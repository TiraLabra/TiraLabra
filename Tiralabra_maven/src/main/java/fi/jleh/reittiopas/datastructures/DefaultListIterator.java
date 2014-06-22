package fi.jleh.reittiopas.datastructures;

import java.util.Iterator;

public class DefaultListIterator<E> implements Iterator<E> {

	private E[] array;
	private int size;
	private int index;
	
	public DefaultListIterator (E[] array) {
		this.array = array;
		this.size = array.length;
		this.index = 0;
	}
	
	@Override
	public boolean hasNext() {
		return index < size && array[index] != null;
	}

	@Override
	public E next() {
		return array[index++];
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("Remove not supported");
	}

}
