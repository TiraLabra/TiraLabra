/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures.Queue;


/**
 *
 * @author Kalle
 */
public class Node {
    private Node next;
    private Object data;
    protected Node(Node next, Object data){
        this.next=next;
        this.data=data;
    }
    public Node getNext(){
        return this.next;
    }
    public Object getData(){
        return this.data;
    }
    public void setNext(Node n){
        this.next=n;
    }
}
