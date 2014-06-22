package fi.jleh.reittiopas.datastructures;

import java.util.Set;

public class BinaryHeap<T> implements Heap<T> {

	private HeapNode<T>[] heapArray;
	private int length;
	private int position;
	private Set<T> objects; // Hash for checking that heap contains an object
	
	public BinaryHeap() {
		this(100);
	}
	
	@SuppressWarnings("unchecked")
	public BinaryHeap(int lenght) {
		this.length = lenght;
		this.heapArray = new HeapNode[this.length];
		this.position = 0;
		this.objects = new DefaultSet<T>();
	}

	public boolean insert(double key, T object) {
		// Create heap object for given object
		HeapNode<T> node = new HeapNode<T>(key, object);
		
		objects.add(object);
		
		if (position + 1 > heapArray.length) {
			resize();
		}
		
		heapArray[position++] = node;
		heapify(position - 1);
		
		return true;
	}
	
	private void heapify(int i) {
		int p = getParent(i);
		
		while (i > 0 && heapArray[i].compareTo(heapArray[p]) < 0) {
			swap(i, p);
			i = p;
			p = getParent(i);
		}
	}
	
	private void swap(int i, int p) {
		HeapNode<T> node1 = heapArray[i];
		HeapNode<T> node2 = heapArray[p];
		
		heapArray[i] = node2;
		heapArray[p] = node1;
	}
	
	@Override
	public T getMin() {
		return heapArray[0].getObject();
	}
	
	@Override
	public T getAndRemoveMin() {
		T min = getMin();
		removeMin();
		objects.remove(min);
		
		return min;
	}
	
	@Override
	public boolean isEmpty() {
		return position == 0;
	}
	
	@Override
	public boolean contains(T object) {
		return objects.contains(object);
	}
	
	@Override
	public double getMinValue() {
		return heapArray[0].getScore();
	}
	
	private void removeMin() {
		heapArray[0] = heapArray[--position];
		minHeapify();
	}
	
	private void minHeapify() {
		int i = 0;
		
		do {
			int j = -1;
			int right = getRight(i);
			int left = getLeft(i);
			
			if (right < position && heapArray[right].compareTo(heapArray[i]) < 0) {
				if (heapArray[left].compareTo(heapArray[right]) < 0) {
					j = left;
				}
				else {
					j = right;
				}
			}
			else {
				if (left < position && heapArray[left].compareTo(heapArray[i]) < 0)
					j = left;
			}
			
			if (j >= 0)
				swap(i, j);
			
			i = j;
		} while (i >= 0);
	}
	
	private int getLeft(int i) {
		return 2*i + 1;
	}
	
	private int getRight(int i) {
		return 2*i + 2;
	}
	
	private int getParent(int i) {
		return (i - 1) / 2;
	}
	
	@SuppressWarnings("unchecked")
	private void resize() {
		int newLength = length * 2;
		HeapNode<T>[] extendedArray = new HeapNode[newLength];
		
		// Copy array contents
		for (int i = 0; i < heapArray.length; i++) {
			extendedArray[i] = heapArray[i];
		}
		
		heapArray = extendedArray;
		length = newLength;
	}
}
