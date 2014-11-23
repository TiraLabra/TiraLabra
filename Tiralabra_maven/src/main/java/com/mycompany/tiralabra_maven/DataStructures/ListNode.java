package com.mycompany.tiralabra_maven.DataStructures;

import com.mycompany.tiralabra_maven.Node;

/**
 * A node in the linked list; used in the MyList-class
 */
public class ListNode {
    /** Node that is the key */
    private Node node;
    /** Next node in the list */
    private ListNode next;
    /** Previous node in the list */
    private ListNode prev;

    public ListNode(Node node) {
        this.node = node;
    }

    /** Gets the next listnode in the list */
    public ListNode getNext() {
        return next;
    }

    /** Sets the next listnode in the list */
    public void setNext(ListNode next) {
        this.next = next;
    }
    /** Gets the previous listnode in the list */
    public ListNode getPrev() {
        return prev;
    }
    /** Sets the previous listnode in the list */
    public void setPrev(ListNode prev) {
        this.prev = prev;
    }
    /** Returns the map node in the list */
    public Node getNode() {
        return node;
    }
}
