package Astar;

import junit.framework.TestCase;

/**
 *
 * @author Arto
 */
public class MapTest extends TestCase {
    
    /**
     * Test creation of a map
     */
    public void testMapCreation() {
        Map m = new Map();
        assertTrue(m!=null);
    }
    
    /**
     * Check that a Node in the middle of the map has correct value
     */
    public void testMapFieldValidity() {
        Map m = new Map();
        assertTrue(m.field[5][11].getValue()=='X');
    }
}
