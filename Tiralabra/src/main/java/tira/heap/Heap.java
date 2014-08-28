package tira.heap;

import tira.common.Node;

/**
 *
 * @author joonaslaakkonen
 */
public class Heap <T> {
    
    private Node[] table;
    private int size;
    
    public Heap (int size) {
        this.table = new Node[size];
        this.size = 0;
    }
    
    public void insert (Node o) {
        if (this.size==0) {
            this.table[0] = o;
            this.size++;
        } else {
            this.heapInsert(o);
        }
    }
    
    public void delete() {
        
    }

    private void heapInsert(Node n) {

    }
    
}
