/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tiralabra.utilities;

import java.math.BigInteger;
import java.util.Arrays;
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
public class TranspositionTableTest {
    
    private TranspositionTable table;
    
    @Before
    public void setUp() {
        table = new TranspositionTable();
    }
    
    @Test
    public void transpositionTableStoresTheValues() {
        Random random = new Random();
        for (int i = 0; i < 100000; i++) {
            BoardValues values = new BoardValues();
            values.hash = new BigInteger(64, random);
            values.depth = random.nextInt(8);
            
            table.put(values);
        
            assertEquals(values, table.get(values.hash));
        }
    }
}
