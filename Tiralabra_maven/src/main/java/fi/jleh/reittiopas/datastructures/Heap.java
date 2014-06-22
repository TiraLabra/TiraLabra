package fi.jleh.reittiopas.datastructures;

/**
 * (Minimum) heap data structure.
 *
 * @param <T>
 */
public interface Heap<T> {

	/**
	 * Insert object with given key/score to the heap.
	 * @param key
	 * @param object
	 * @return
	 */
	boolean insert(double key, T object);
	
	/**
	 * Get object with lowest score/key from the heap.
	 * @return
	 */
	T getMin();
	
	/**
	 * Get object with lowest score/key and remove it from the heap.
	 * @return
	 */
	T getAndRemoveMin();
	
	/**
	 * Check is heap empty.
	 * @return
	 */
	boolean isEmpty();
	
	/**
	 * Check is object in heap.
	 * @param object
	 * @return
	 */
	boolean contains(T object);
	
	/**
	 * Returns value of top node.
	 * @return
	 */
	double getMinValue();
}
