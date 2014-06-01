/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lib.datastructures;

import lib.utils.ByteAsBits;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
public class SlidingTableTest {
    
    public SlidingTableTest() {
    }

    @Test
    public void testAdd() {
        SlidingTable table = new SlidingTable(3);
        byte a = 1;
        table.add(a);
        table.add((byte)2);
        table.add((byte)3);
        ByteAsBits b = table.add((byte)4);
        assertTrue(b.getByte() == a);
        
    }

    @Test
    public void testGet() {
        SlidingTable table = new SlidingTable(5);
        byte a = 2;
        table.add((byte)1);
        table.add(a);
        assertTrue(table.get(1) == a);
        table.add((byte)3);
        assertTrue(table.get(1) == a);
        table.add((byte) 4);
        table.add((byte) 3);
        table.add((byte) 5);
        assertTrue(table.get(0) == a);
        table.add((byte) 5);
        assertTrue(table.get(0) == (byte)3);
    }
    
    @Test
    public void testPollAll(){
        SlidingTable table = new SlidingTable(3);
        table.add((byte)1);
        table.add((byte)2);
        table.add((byte)3);
        byte[] bytes = table.pollAll();
        assertTrue(bytes[0] == 1 && bytes[1] == 2 && bytes[2] == 3);
    }
    
}
