/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures.Queue;


/**
 *
 * @author Kalle
 */
public class Queue<T> {
    private Node tail;
    private Node head;
    public Queue(){
        this.tail=null;
        this.head=null;
    }
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
    public T dequeue(){
        if(this.head!=null){
            T data=(T)this.head.getData();
            this.head=this.head.getNext();
            return data;
        }
        return null;
    }
    public boolean isEmpty(){
        return this.head==null;
    }
}
