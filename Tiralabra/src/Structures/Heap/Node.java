
package Structures.Heap;


public class Node {
    private int key;
    private Object data;
    protected Node(int key, Object data){
        this.key=key;
        this.data=data;
    }
    protected int getKey(){
        return this.key;
    }
    protected Object getData(){
        return this.data;
    }
    protected void setKey(int key){
        this.key=key;
    }
}
