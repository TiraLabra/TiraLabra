package Structures.LinkedList;

import Utils.Iterator;

/**
 * Linked list structure
 */
public class LinkedList<T> {
    private Node tail;
    private Node head;
    private int size;
    public LinkedList(){
        this.tail=null;
        this.size=0;
        this.head=null;
    }
    /**
     * Adds an object in the end of the linked list
     */
    public void add(T data){
        if(this.tail==null){
            Node n = new Node(null,data);
            this.tail=n;
            this.head=n;
        }else{
            Node n = new Node(null,data);
            this.tail.setNext(n);
            this.tail=n;
        }
        this.size++;
    }
    /**
     * Removes the last object in the linked list
     * @return The last object in the linked list
     */
    public T removeTail(){
        if(this.head==null){
            return null;
        }
        if(this.head.getNext()==null){
            this.head=null;
            return null;
        }else{
            Node newTail = this.head.getNext();
            this.head=newTail;
        }
        this.size--;
        return (T)this.head.getData();
    }
    /**
     * Checks if the linked list is empty
     */
    public boolean isEmpty(){
        return this.head==null;
    }
    /**
     * Removes all the objects in the linked list
     */
    public void clear(){
        this.head=null;
        this.tail=null;
        this.size=0;
    }
    /**
     * Returns the last object in the linked list without removing it
     */
    public Node getTail(){
        return this.head;
    }
    /**
     * Returns the first object in the linked list without removing it
     */
    public Node getHead(){
        return this.head;
    }
    public void setTail(Node n){
        this.tail=n;
    }
    public void setHead(Node n){
        this.head=n;
    }
    public int size(){
        return this.size;
    }
    public void remove(Node n, Node prev){
        if(n==null){
            return;
        }
        if(n.getNext()==null){
            prev.setNext(null);
        }else{
            prev.setNext(n.getNext());
        }
    }
    @Override
    public String toString(){
        String list="[";
        Iterator<T> i = new Iterator<T>(this);
        int n = 0;
        while(i.hasNext()){
            T next = i.getNext();
            if(n==0){
                list+=next.toString();
            }else{
                list+=","+next.toString();
            }
            n++;
        }
        list+="]";
        return list;
        
    }
}
