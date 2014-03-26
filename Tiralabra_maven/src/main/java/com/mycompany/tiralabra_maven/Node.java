/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven;

/**
 *
 * @author Tiina
 */
public class Node {
 
    private Node next = null;
    static int value, kor, lev;

    /**
     *
     * @param value
     * @param next
     */
    public Node(int value, Node next) {
        this.next=next;
        this.value=value;        
    }
 
    /**
     *
     * @param value
     */
    public Node(int value) {
        this.value=value;
    }
    
    /**
     *Pitää mielessään hiiren sijaintia (koordinaatteja) kartalla. 
     */
    public Node(){
        this.kor = Hiiri.getXcoord();
        this.lev = Hiiri.getYcoord();
    }
 
    /**
     *
     * @return
     */
    public int getValue() {
        return value;
    }
 
    /**
     *
     * @return
     */
    public Node getNext() {
        return next;
    }
 
    /**
     *
     * @param next
     */
    public void setNext(Node next) {
        this.next = next;
    }
 
    /**
     *
     * @param value
     */
    public void setValue(int value) {
        this.value = value;
    }
  
    /**
     *
     * @return
     */
    public String toString() {
        String n="null";
 
        if (next != null)
            n = next.toString();
 
        return "Node["+value+", "+n+"]";
    }
}