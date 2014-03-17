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
    private int value;
 
    public Node(int value, Node next) {
        this.next=next;
        this.value=value;
    }
 
    public Node(int value) {
        this.value=value;
    }
 
    public int getValue() {
        return value;
    }
 
    public Node getNext() {
        return next;
    }
 
    public void setNext(Node next) {
        this.next = next;
    }
 
    public void setValue(int value) {
        this.value = value;
    }
  
    public String toString() {
        String n="null";
 
        if (next != null)
            n = next.toString();
 
        return "Node["+value+", "+n+"]";
    }
}