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








}
