package com.mycompany.tiralabra_maven;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class MyMapTest extends TestCase
{
    /** Test that the same point returns true
     */
    public void testMapCanFindSameCoordinate()
    {
        Node node = new Node(1,0);
        MyMap myMap = new MyMap(node, node, 5, 5);
        myMap.createMap();
        assertTrue(myMap.isSamePointOnMap(1, 0, node));
    }

    /** Test that the different points return false
     */
    public void testMapCanFindDifferentCoordinate()
    {
        Node node = new Node(1,0);
        MyMap myMap = new MyMap(node, node, 5, 5);
        myMap.createMap();
        assertFalse(myMap.isSamePointOnMap(0, 0, node));
    }

}
