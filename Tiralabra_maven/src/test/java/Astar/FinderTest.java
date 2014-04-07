package Astar;

import junit.framework.TestCase;

/**
 *
 * @author Arto
 */
public class FinderTest extends TestCase {
    
    /**
     * Test creation of a Finder
     */
    public void testFinderCreation(){
        Finder f = new Finder();
        assertTrue(f!=null);
    }
}
