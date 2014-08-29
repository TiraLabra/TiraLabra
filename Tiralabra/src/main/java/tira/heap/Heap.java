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
    
    public Node poll() {
        if(this.size != 0) {
            Node smallest = this.table[0];
            this.table[0] = this.table[this.size-1];
            this.size--;
            this.heapify(0);
            return smallest;
        }
        return null;     
    }
    
    public boolean empty() {
        if (this.size != 0) {
            return false;
        }
        return true;
    }

    private void heapInsert(Node n) {
        this.size++;
        int k = this.size-1;
        while (k > 0 && n.compareTo(this.table[(k-1)/2]) < 0) {
            Node parent = this.table[(k-1)/2];
            this.table[k] = parent;
            int p = (k-1)/2;
            k = p;
        }
        this.table[k] = n;
    }
    
    private void heapify(int paikka) {
        int l = 2*paikka + 1;
        int r = 2*paikka + 2;
        
        if (r < this.size) {
            Node smaller;
            int small;
            
            if (this.table[l].compareTo(this.table[r]) < 0) {
                smaller = this.table[l];
                small = l;
            } else {
                smaller = this.table[r];
                small = r;
            }
            if (this.table[paikka].compareTo(smaller) > 0) {
                Node oldRoot = this.table[paikka];
                Node root = this.table[small];
                this.table[paikka] = root;
                this.table[small] = oldRoot;
                this.heapify(small);
            }
        } else if(l==this.size-1 && this.table[paikka].compareTo(this.table[l]) > 0) {
            Node oldRoot = this.table[paikka];
            Node root = this.table[l];
            this.table[paikka] = root;
            this.table[l] = oldRoot;
        }
    }
}