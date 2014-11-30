package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.DataStructures.ListNode;
import com.mycompany.tiralabra_maven.DataStructures.MyList;
import junit.framework.TestCase;

/**
 * Unit tests for MyList class
 * Also tests some items from ListNode-class
 */
public class MyListTest extends TestCase {

    /** Test that an item can be added to the list
     */
    public void testListItemsCanBeAdded()
    {
        Node node = new Node(0,0);
        MyList list = new MyList();
        list.add(node);
        assertTrue(list.contains(node));
    }

    /** Test that an item is removed properly from the list
     */
    public void testListItemsCanBeDeleted()
    {
        Node node = new Node(1,0);
        MyList list = new MyList();
        list.add(node);
        assertTrue(list.contains(node));
        list.remove(node);
        assertFalse(list.contains(node));
    }

    /** Test that previous and next nodes are properly set
     *      Test list should go as follows:
     *  previous(null) - HEAD: node2 - node - next(null)
     */
    public void testNodesHavePreviousAndNextCorrectlySet() {
        Node node = new Node(1,0);
        MyList list = new MyList();
        Node node2 = new Node(0,0);
        list.add(node);
        list.add(node2);
        ListNode test = list.search(node).getPrev();
        assertTrue(test.getNode().equals(node2));
        assertNull(list.search(node).getNext());
        test = list.search(node2).getNext();
        assertTrue(test.getNode().equals(node));
        assertNull(list.search(node2).getPrev());
    }

}
