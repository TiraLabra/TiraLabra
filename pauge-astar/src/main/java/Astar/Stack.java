package Astar;

/**
 *
 * @author Arto
 */
public class Stack {
        public Node[] t;
        private int size;
    
    Stack() {
        t = new Node[10];
        size = 0;
    }
    
    /**
     * 
     * @return 
     */
    public Node[] increaseStackSize() {
           Node[] tnew = new Node[2*t.length];     
           for(int i=1; i<t.length; i++) {
               tnew[i] = t[i];                     
           }
           return tnew;
    }
    
    /**
     * 
     * @param n 
     */
    public void insertNode(Node n) {
        if(size<t.length-1) {     //table must not be full
                size++;
                t[size] = n;
        }
        else {
            t = increaseStackSize();
            insertNode(n);                          //redo insert
        }
    }
    
    /**
     * 
     * @param n
     * @return 
     */
    public Node removeNode() {
        if(size>0) {              //table must not be empty
            size--;
            return t[size+1];
        }
        return null;
    }
    /**
     * 
     * @param n
     * @return 
     */
    public boolean hasNode(Node n) {
        boolean r = false;
        for(int i = 1; i<=size;i++) {
            if(t[i]==n) {
                r=true;
                break;
            }
        }
        return r;
    }
    
    public int size() {
        return size;
    }
}
