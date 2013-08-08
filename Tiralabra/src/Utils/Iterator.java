/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Structures.LinkedList.LinkedList;
import Structures.LinkedList.Node;

/**
* Can be used for iterating through a list
*/

public class Iterator<T> {
    private LinkedList<T> list;
    private Node iterator;
    private Node prev;
    public Iterator(LinkedList<T> list){
        this.list=list;
        this.iterator=this.list.getTail();
    }
    /**
    * Clears the iterator so that it points back in the beginning of the list
    */
    public void clear(){
        this.iterator=this.list.getTail();
    }
    /**
    * Points to the next object on the list
    */
    public T getNext(){
        if(this.iterator!=null){
            this.prev=this.iterator;
            Object data = this.iterator.getData();
            this.iterator=this.iterator.getNext();
            return (T)data;
        }
        return null;
    }
    /**
    * Checks if the iterator has reached the end of the list
    */
    public boolean hasNext(){
        return this.iterator!=null;
    }
    /**
    * Removes the object from the list which iterator is currently pointing at
    */
    public void remove(){ 
        if(this.prev!=null){
            Node before = null;
            Node current = this.list.getHead();
            if(this.list.getHead()==null){
                return;
            }
            while(!current.equals(this.prev)){
                before=current;
                current=current.getNext();
            }
            if(before==null && current.getNext()!=null){
                this.list.setHead(current.getNext());
                current.setNext(null);
                return;
            }
            if(before==null && current.getNext()==null){
                this.list.setHead(null);
                this.list.setTail(null);
                return;
            }
            if(before!=null && current.getNext()!=null){
                before.setNext(current.getNext());
                current.setNext(null);
                return;
            }
            if(before!=null && current.getNext()==null){
                before.setNext(null);
                current.setNext(null);
                return;
            }
        }
    }
}
