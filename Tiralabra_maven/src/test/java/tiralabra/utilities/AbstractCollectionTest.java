/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.utilities;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author atte
 */
public class AbstractCollectionTest {

    private ArrayList<Integer> collection;

    @Before
    public void setUp() {
        collection = new ArrayList<>();
    }
    
    @Test
    public void removingObjectsRemoveTheObjectAtTheSpecifiedIndex() {
        for (int i = 0; i < 10; i++) {
            assertTrue(collection.add(i));  
        }
                
        assertTrue(collection.remove(2));
        
        assertEquals(9, collection.size());
        assertFalse(collection.contains(2));
    }
    
    @Test
    public void removingObjectsPullsLaterObjectsBackByOne() {
        for (int i = 0; i < 10; i++) {
            assertTrue(collection.add(i));  
        }
        
        collection.remove(0);
        
        for (int i = 0; i < 9; i++) {
            assertEquals(i + 1, (int) collection.get(i));
        }
    }
    
    @Test
    public void collectionGrowsCapacityWhenTheArrayIsFull() {
        for (int i = 0; i < 100; i++) {
            assertTrue(collection.add(i));  
        }
        
        assertEquals(100, collection.size());
        
        for (int i = 0; i < 100; i++) {
            assertEquals(i, (int) collection.get(i));
        }
    }
    
    @Test
    public void clearingTheCollectionEmptiesIt() {
        for (int i = 0; i < 100; i++) {
            collection.add(i);  
        }
        
        collection.clear();
        
        assertTrue(collection.isEmpty());
        
        for (int i = 0; i < 100; i++) {
            assertFalse(collection.contains(i));
        }
    }
    
    @Test
    public void startsEmpty() {
        assertTrue(collection.isEmpty());
    }
}
