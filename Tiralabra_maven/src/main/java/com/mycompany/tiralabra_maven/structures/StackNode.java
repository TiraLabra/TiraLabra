package com.mycompany.tiralabra_maven.structures;
/**
 * Tämä on yksi solmu pinossa
 * @author szetk
 * @param <E> Tallennettavien alkioiden tyyppi
 */
public class StackNode<E> {

    private Object value;
    private StackNode<E> next;

    public StackNode() {
    }
/**
 * Konstruktori
 * @param value Tallennettava alkio
 */
    public StackNode(E value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public StackNode<E> getNext() {
        return next;
    }

    public void setNext(StackNode<E> next) {
        this.next = next;
    }

}
