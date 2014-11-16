package com.mycompany.tiralabra_maven;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class KarttaTest extends TestCase
{
    /** Test that the same point returns true
     */
    public void testMapCanFindSameCoordinate()
    {
        Node node = new Node(1,0);
        Kartta kartta = new Kartta(node, node, 5, 5);
        kartta.createMap();
        assertTrue(kartta.isSamePointOnMap(1, 0, node));
    }

    /** Test that the different points return false
     */
    public void testMapCanFindDifferentCoordinate()
    {
        Node node = new Node(1,0);
        Kartta kartta = new Kartta(node, node, 5, 5);
        kartta.createMap();
        assertFalse(kartta.isSamePointOnMap(0, 0, node));
    }

}
