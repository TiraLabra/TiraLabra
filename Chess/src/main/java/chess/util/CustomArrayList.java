package chess.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Oma ArrayList-toteutus.
 */
public final class CustomArrayList<T> implements List<T>
{
	/**
	 * Taulukon kapasiteetti alussa.
	 */
	private static final int DEFAULT_INITIAL_CAPACITY = 8;

	/**
	 * Listan elementit.
	 */
	private Object[] elements;

	/**
	 * Elementtien lukumäärä.
	 */
	private int size;

	/**
	 * Luo tyhjän listan käyttäen oletuskapasiteettia.
	 */
	public CustomArrayList()
	{
		elements = new Object[DEFAULT_INITIAL_CAPACITY];
		size = 0;
	}

	@Override
	public int size()
	{
		return size;
	}

	@Override
	public boolean isEmpty()
	{
		return size == 0;
	}

	@Override
	public boolean add(T e)
	{
		grow(size + 1);
		elements[size++] = e;
		return true;
	}

	@Override
	public T get(int i)
	{
		if (i >= size)
			throw new IndexOutOfBoundsException();
		return (T) elements[i];
	}

	@Override
	public boolean contains(Object o)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Iterator<T> iterator()
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Object[] toArray()
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public <T> T[] toArray(T[] ts)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean remove(Object o)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean containsAll(Collection<?> clctn)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean addAll(Collection<? extends T> clctn)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean addAll(int i, Collection<? extends T> clctn)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean removeAll(Collection<?> clctn)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean retainAll(Collection<?> clctn)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void clear()
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public T set(int i, T e)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void add(int i, T e)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public T remove(int i)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public int indexOf(Object o)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public int lastIndexOf(Object o)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public ListIterator<T> listIterator()
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public ListIterator<T> listIterator(int i)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public List<T> subList(int i, int i1)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	private void grow(int requiredSize)
	{
		requiredSize = Integer.highestOneBit(requiredSize) << 1;
		if (elements.length < requiredSize)
			elements = Arrays.copyOf(elements, requiredSize);
	}
}
