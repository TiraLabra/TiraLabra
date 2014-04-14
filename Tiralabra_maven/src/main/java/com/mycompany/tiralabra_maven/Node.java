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
    //astar muuttujat.
    private int f, h, g;

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
    public int getH() {
        return h;
    }
 
    /**
     *
     * @param next
     */
    public void setH(int h) {
        this.h = h;
    }
 
    /**
     *
     * @return
     */
    public int getG() {
        return g;
    }
 
    /**
     *
     * @param next
     */
    public void setG(int g) {
        this.g = g;
    }    
    
    /**
     *
     * @return
     */
    public int getF() {
        return f;
    }
 
    /**
     *
     * @param next
     */
    public void setF(int f) {
        this.f = f;
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