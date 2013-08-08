/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures.Stack;

/**
 * Stack structure
 */
public class Stack<T> {
    private Node top;
    public Stack(){
        this.top=null;
    }
    /**
     * Adds an object to the stack
     */
    public void push(T data){
        if(this.top==null){
            Node n=new Node(null,data);
            this.top=n;
        }else{
            Node n=new Node(this.top,data);
            this.top=n;
        }
    }
    /**
     * Removes an object from the stack
     */
    public T pop(){
        if(this.top!=null){
            T data=(T)this.top.getData();
            this.top=this.top.getNext();
            return data;
        }
        return null;
    }
    /**
     * Checks if the stack is empty
     */
    public boolean isEmpty(){
        return this.top==null;
    }
}
