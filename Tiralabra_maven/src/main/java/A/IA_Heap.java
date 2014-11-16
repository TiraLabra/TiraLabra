package A;

/**
 * Interface with operations necessary for A* algorithm.
 * As the name states this interface should be implemented by a heap data structure
 */
public interface IA_Heap <E> {

    /**
     * inserts a element to the heap
     * @param e 
     */
    public void insert(E e);

    /**
     * returns the element with minium key in this heap
     * @return 
     */
    public E peakMin();

    /**
     * returns and removes the element with minium key in this heap
     * @return 
     */
    public E pollMin();
    
    /**
     * removes given element from this heap
     * @param e 
     */
    public void remove(E e);
    
    /**
     * returns true if there are no elements in this heap.
     * otherwise returns false.
     * @return 
     */
    public boolean isEmpty();
    /**
     * returns the size of this heap
     * @return 
     */
    public int size();
}
