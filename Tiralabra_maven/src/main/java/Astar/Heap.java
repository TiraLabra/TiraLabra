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
     * Adds a new node to priority queue
     * @param n The node to be added 
     */
    public void insertNode(Node n) {
        if(size<t.length-1) {     //table must not be full
                size++;
                int i = size;
                while(i>1 && t[i/2].getPrio()>n.getPrio()) { //not yet top of heap and the heap isn't valid
                        t[i] = t[i/2];
                        i=i/2;
                }
                t[i] = n;
        }
        else {                                      //the table was full so:
            Node[] tnew = new Node[2*t.length];     //double the length of previous table
            for(int i=1; i<=t.length; i++) {
                tnew[i] = t[i];                     //copy from old to new
            }
            t = tnew;                               //"discard" old t
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
        Node left = null;
        Node right = null;
        if(size>=2*i)   
            left = t[2*i];                          //left child
        if(size>=2*i+1) 
            right = t[2*i+1];                       //right child
        if ((2*i+1)<=size) {                        //there is a right child
            if(left.getPrio()>right.getPrio()){     //compare children  
                if(t[i].getPrio()>left.getPrio()){  //left child bigger than parent
                    t[2*i] = t[i];                  //swapt child and parent
                    t[i] = left;  
                    sortHeap(2*i);
                }
            }
            else {
                if(t[i].getPrio()>right.getPrio()){ //right child bigger than parent
                    t[2*i+1] = t[i];                //swap child and parent
                    t[i] = right;
                    sortHeap(2*i+1);
                }
            }
        }  
        if(size==2*i && t[i].getPrio()>left.getPrio()){ //there is a left child and it as a bigger prio than parent
                t[2*i] = t[i];                              //swap child and parent
                t[i] = left;
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