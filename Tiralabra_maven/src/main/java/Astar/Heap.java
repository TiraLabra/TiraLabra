package Astar;

/**
 * A priority queue to hold the neighbour nodes (the ones to be checked if necessary)
 * @author Arto
 * 
 * 
 */
public class Heap {
    public Node[] t;
    private int size;
    private Node temp;
    
    Heap() {
        t = new Node[10];
        size = 0;
        temp = null;
    }

    /**
     * Adds a new node to priority queue
     * @param n The node to be added 
     */
    public void insertNode(Node n) {
        if(size<t.length) {     //table must not be full
            if(size == 0){      //add first node
                size++;
                t[size] = n;
            }
            if(size>0) {        //insert when not empty
                size++;
                int i = size;
                while(i>=0 && t[i-1].getPrio()>n.getPrio()) {
                    t[i] = t[i-1];
                    i--;
                }
                t[i] = n;
            }
        }
        else {                                      //the table was full so:
            Node[] tnew = new Node[2*t.length];     //double the length of previous table
            for(int i=0; i<t.length; i++) {
                tnew[i] = t[i];                     //copy from old to new
            }
            t = tnew;                               //"discard" old t
            insertNode(n);                          //redo insert
        }
    }
    /**
     *  Remove a node from the heap
     */
    public void removeNode(Node n) {
        
        
    }

}