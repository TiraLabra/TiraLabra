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
        Map m = new Map(2,"Line");
        assertTrue(m!=null);
    }
}
