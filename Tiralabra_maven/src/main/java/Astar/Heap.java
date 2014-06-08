package Astar;

/**
 * A priority queue to hold the neighbor nodes (the ones to be checked if necessary)
 * @author Arto
 * 
 * 
 */
public class Heap {
    public Node[] t;
    private int size;
    
    Heap() {
        t = new Node[10];
        size = 0;
    }
    
    /**
     * When heap gets full, double the make a new one of double size and copy content
     * @return 
     */
    public Node[] increaseHeapSize() {
           Node[] tnew = new Node[2*t.length];     
           for(int i=1; i<t.length; i++) {
               tnew[i] = t[i];                     
           }
           return tnew;
    }
    
    /**
     * Adds a new node to priority queue
     * @param n The node to be added 
     */
    public void insertNode(Node n) {
        if(size<t.length-1) {     //table must not be full
                size++;
                int i = size;
                while(i>1 && t[i/2].getPrio()>n.getPrio()) {
                        Node tmp =t[i];
                        t[i] = t[i/2];
                        t[i/2]=tmp;
                        i=i/2;
                }
                t[i] = n;
        }
        else {
            t = increaseHeapSize();
            insertNode(n);                          //redo insert
        }
    }
    /**
     * Returns Node with highest priority
     * 
     * @return the Node with highest priority
     */
    public Node getHighest() {
        return t[1];
    }
    /**
     *  Remove node with highest priority from the heap
     */
    public void removeNode() {
        t[1] = t[size];         //last to first
        size--;
        sortHeap(1);        //re-organize
    }
    
    /**
     * Organize nodes in given table to meet min-heap requirements
     * 
     * @param i the index of node currently being evaluated with its possible children
     */
    public void sortHeap(int i){
        int high =i;
        int left = 2*i;
        int right = 2*i+1;                       
        if(size>=2*i) {
           if(t[left].getPrio()<t[i].getPrio())
                high = left;
        }
        if(size>=2*i+1){
            if(t[right].getPrio()< t[high].getPrio()) 
                high = right;
        }
        if(t[high].getPrio()<t[i].getPrio()) {
            Node tmp = t[high];
            t[high] = t[i];
            t[i] = tmp;
            sortHeap(high);
        } 
    }
   
    /**
     * Returns boolean value based on Node n's existence in heap
     * 
     * @param n the Node to be searched for
     * @return was, or was n not in the heap
     */
    public boolean hasNode(Node n) {
        for(int i=1;i<=size;i++){        //go through heap
            if(t[i] == n) {
                return true;            //found
            }
        }
            return false;               //not found
    }
    /**
     * Returns boolean value describing is the heap empty 
     * @return empty or not?
     */
    public boolean isEmpty() {
        if(size==0)
            return true;
        else
            return false;
    }
    
    public int getSize(){
        return size;
    }
    public Node get(int i){
        return t[i];
    }
   

}