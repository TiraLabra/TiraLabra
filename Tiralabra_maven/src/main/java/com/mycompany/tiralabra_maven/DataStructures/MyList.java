package com.mycompany.tiralabra_maven.DataStructures;

import com.mycompany.tiralabra_maven.Node;

/**
 * Implementation of a linked list
 *
 */

public class MyList {
    private ListNode head;

    public MyList() {
    }

    /** Removes the ListNode from the list
     *
     * @param node item to be removed
     */
    public void remove(Node node) {
        ListNode previous = search(node).getPrev();
        ListNode next = search(node).getNext();
        if (previous != null) {
            previous.setNext(next);
        } else {
            head = next;
        }
        if (next != null) {
            next.setPrev(previous);
        }

    }

    /** Adds the node in the list: the new insert is always in the beginning of the list
     *
     * @param node node to be added
     */
    public void add(Node node) {
        ListNode x = new ListNode(node);
        x.setNext(head);
        x.setPrev(null);

        if (head != null) {
            ListNode next = x.getNext();
            next.setPrev(x);
        }
        head = x;
    }


    /**
     * Checks if the list has the item
     * @param node node to be checked
     * @return if the list contains the node
     */
    public boolean contains(Node node) {
        ListNode p = head;
        while (p != null) {
            if (node.isSameNode(p.getNode())) {
                return true;
            }
            p = p.getNext();

            }
        return false;
    }

    /**
     * Searches for the list node which contains the map node
     * @param node Map node
     * @return List node associated with the map node
     */
    public ListNode search(Node node) {
        ListNode p = head;
        while (p != null) {
            if (node.isSameNode(p.getNode())) {
                return p;
            }
            p = p.getNext();

        }
        return null;
    }

}
