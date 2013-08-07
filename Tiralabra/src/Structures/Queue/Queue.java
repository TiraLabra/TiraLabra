/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures.Queue;


/**
 * Queue structure
 */
public class Queue<T> {
    private Node tail;
    private Node head;
    public Queue(){
        this.tail=null;
        this.head=null;
    }
    /**
     * Adds an object to the queue
     */
    public void enqueue(T data){
        if(this.head==null){
            Node n=new Node(null,data);
            this.head=n;
            this.tail=n;
        }else{
            Node n=new Node(null,data);
            this.tail.setNext(n);
            this.tail=n;
        }
    }
    /**
     * Removes an object from the queue
     */
    public T dequeue(){
        if(this.head!=null){
            T data=(T)this.head.getData();
            this.head=this.head.getNext();
            return data;
        }
        return null;
    }
    /**
     * Checks if the queue is empty
     */
    public boolean isEmpty(){
        return this.head==null;
    }
}
