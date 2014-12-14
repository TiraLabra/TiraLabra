package com.mycompany.tiralabra_maven;

import junit.framework.TestCase;

/**
 * Unit tests for Node class
 */
public class NodeTest extends TestCase {

    /** Test if Node can be created and it sets coordinates right
     */
    public void testNodeCanBeCreated()
    {
        Node node = new Node(0,0);
        assertTrue(node.getX()==0 && node.getY() == 0);
    }

    /** Test that Node parent can be set
     */
    public void testNodeHasParentSet()
    {
        Node node = new Node(1,0);
        Node parent = new Node(0,0);
        node.setParent(parent);
        assertTrue(node.getParent().equals(parent));
    }

    /** Test that compareTo returns -1 if the node is cheaper
     * 1 if node is more expensive
     * 0 if the cost is same
     */
    public void testCompareToWorks()
    {
        Node cheaper = new Node(1,0);
        cheaper.setCost(1);
        Node same = new Node(1,1);
        same.setCost(1);
        Node expensive = new Node(0,0);
        expensive.setCost(10);
        assertTrue(cheaper.compareTo(expensive) == -1);
        assertTrue(expensive.compareTo(cheaper) == 1);
        assertTrue(cheaper.compareTo(same) == 0);

    }

    /** Test that two nodes with same coordinates are the same
     */
    public void testSameNode()
    {
        Node node = new Node(1,0);
        Node same = new Node(1,0);
        assertTrue(node.isSameNode(same));
    }



}
