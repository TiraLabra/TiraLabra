
package lib.datastructures;

import lib.utils.ByteAsBits;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SlidingWindowTest {
    
    SlidingWindow w;
    
    public SlidingWindowTest() {
    }
    
    @Before
    public void setUp() {
        w = new SlidingWindow(5);
    }
    
    @Test
    public void testAdd() {
        w.add((byte)1);
        w.add((byte)-128);
        w.add((byte)3);
        w.add((byte)4);
        w.add((byte)127);
        ByteAsBits b = w.add((byte)6);
        assertTrue(b.getByte() == (byte)1);
    }

    @Test
    public void testFindBestMatch() {
        w.add((byte)1);
        w.add((byte)2);
        w.add((byte)3);
        w.add((byte)4);
        w.add((byte)5);
        LinkedQueue<Byte> q = new LinkedQueue<Byte>();
        q.enqueue((byte)1);
        q.enqueue((byte)2);
        int[] match = w.findBestMatch(q);
        assertTrue(match[0] == 0 && match[1] == 2);
    }
    
    @Test
    public void testFindBestMatch2() {
        w.add((byte)1);
        w.add((byte)2);
        w.add((byte)3);
        w.add((byte)4);
        w.add((byte)5);
        w.add((byte)6);
        LinkedQueue<Byte> q = new LinkedQueue<Byte>();
        q.enqueue((byte)4);
        q.enqueue((byte)5);
        q.enqueue((byte)6);
        int[] match = w.findBestMatch(q);
        assertTrue(match[0]+ ", " + match[1],match[0] == 2 && match[1] == 3);
    }
    
}
