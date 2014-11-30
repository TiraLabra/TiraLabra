package structures;

import map.Node;

/**A min-heap designed to work with the AStarSearch class.
 * 
 * @author ghaas
 */
public class MinHeap {

    private Node[] heap;
    private int size;
    
    public MinHeap (int x, int y) {
        this.heap = new Node[x * y];
        this.size = 0;
    }
    
    /**Inserts a new node to the heap.
     * 
     * @param node 
     */
    public void add(Node node) {
        size++;
        int i = size;
        while (i > 1 && heap[i/2].getF() > node.getF()) {
            heap[i] = heap[i/2];
            i = i/2;
        }
        heap[i] = node;
    }
    
    /**Returns the smallest (first) element in heap and removes it from the heap.
     * 
     * @return 
     */
    public Node pop() {
        if (size < 1) {
            return null;
        }
        Node min = heap[1];
        heap[1] = heap[size];
        size = size - 1;
        heapify(1);
        return min;
    }
    
    /**Orders the array as an min-heap.
     * 
     * @param i where the heapification starts
     */
    public void heapify(int i) {
        int left = 2 * i;
        int right = 2 * i + 1;
        if (right <= size) {
            int smallest = right;
            if (heap[left].getF() < heap[right].getF()) {
                smallest = left;
            }
            if (heap[i].getF()> heap[smallest].getF()) {
                Node temp = heap[smallest];
                heap[smallest] = heap[i];
                heap[i] = temp;
                heapify(smallest);
            }
        }
        else if (right == size && heap[i].getF() > heap[left].getF()) {
            Node temp = heap[left];
            heap[left] = heap[i];
            heap[i] = temp;
        }
    }
    
    /**Checks if the heap contains a Node with the coordinates in the arguments.
     * 
     * @param x
     * @param y
     * @return 
     */
    public boolean contains(int x, int y) {
        if (size == 0) {
            return false;
        }
        for (int i = 1; i <= size; i++) {
            if (heap[i].getX() == x && heap[i].getY() == y) {
                return true;
            }
        }
        return false;
    }
    
    /**Returns the Node with the correct coordinates, but doesn't remove it
     * from the heap.
     * 
     * @param x
     * @param y
     * @return 
     */
    public Node get(int x, int y) {
        if (size == 0) {
            return null;
        }
        for (int i = 1; i <= size; i++) {
            if (heap[i].getX() == x && heap[i].getY() == y) {
                return heap[i];
            }
        }
        return null;
    }
    
    /**Returns true if the heap is empty.
     * 
     * @return 
     */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
    
    /**Returns a string representation of the heap. Used for testing.
     * 
     * @return 
     */
    @Override
    public String toString() {
        String output = "";
        if (size == 0) {
            return "empty heap";
        }
        for (int x = 1; x <= size; x++) {
            output += "(" + x + ", F:" + heap[x].getF() + ") -> ";
        }
        return output;
    }
}
