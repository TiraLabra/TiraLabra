/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package search;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 */
public class AStarSearchTest {
    
    AStarSearch search;
    int [][] validMap = {{1, 1, 2, 3, 2},
                         {2, 6, 2, 1, 1},
                         {1, 2, 9, 2, 2},
                         {2, 1, 1, 2, 1}};
    
    @Before
    public void setUp() {
        search = new AStarSearch(validMap);
    }
    
    @Test
    public void badSearchTermDoesNotWork() {
        assertEquals("Search value(s) out of map range", search.search(0, 0, 10, 10));
    }
    
    @Test
    public void searchWorks() {
        assertEquals("(0, 0: time: 0) -> (0, 1: time: 2) -> (0, 2: time: 3) -> (1, 2: time: 5) -> (1, 3: time: 6) -> (2, 3: time: 7) -> (3, 3: time: 9)", search.search(0, 0, 3, 3));
    }
    
    @Test
    public void mapPrintingWorks() {
        assertEquals("   0 1 2 3 4\n" + 
                     " 0 1 1 2 3 2\n" + 
                     " 1 2 6 2 1 1\n" + 
                     " 2 1 2 9 2 2\n" + 
                     " 3 2 1 1 2 1\n", search.printMap());
    }
    
    
}
