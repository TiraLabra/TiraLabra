/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tiralabra.utilities;


import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author atte
 */
public class CollectionsTest {
    
    @Test
    public void sortSortsList() {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        
        for (int i = 0; i < 100; i++) {
            list.add(random.nextInt());
        }
        Collections.sort(list);
    
        int previous = list.get(0);
        for (Integer i : list) {
            assertTrue(previous <= i);
        }
    }
}
