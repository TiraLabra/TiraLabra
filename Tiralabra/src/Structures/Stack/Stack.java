/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures.Stack;

/**
 *
 * @author Kalle
 */
public class Stack<T> {
    private Node top;
    public Stack(){
        this.top=null;
    }
    public void push(T data){
        if(this.top==null){
            Node n=new Node(null,data);
            this.top=n;
        }else{
            Node n=new Node(this.top,data);
            this.top=n;
        }
    }
    public T pop(){
        if(this.top!=null){
            T data=(T)this.top.getData();
            this.top=this.top.getNext();
            return data;
        }
        return null;
    }
    public boolean isEmpty(){
        return this.top==null;
    }
}
