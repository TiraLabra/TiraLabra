package lib.io;

import static org.junit.Assert.*;
import org.junit.Test;

public class ByteAsBitsTest {
    
    public ByteAsBitsTest() {
    }

    @Test
    public void testGetBitAt() {      
        int b = 1;
        ByteAsBits btb = new ByteAsBits(b);
        if(!btb.getBitAt(7)){
            fail();
        }
        for(int i = 6; i >= 0; i--){
            if(btb.getBitAt(i)){
                fail();
            }
        }
    }

    @Test
    public void testConstructors(){
        int k = 0;
        byte b = -128;
        boolean[] a = new boolean[]{false, false , false, false, false, false, false, false};
        ByteAsBits b1 = new ByteAsBits(k);
        ByteAsBits b2 = new ByteAsBits(b);
        ByteAsBits b3 = new ByteAsBits(a);
        assertTrue(b1.getByte() == b2.getByte() && b2.getByte() == b3.getByte() );
    }
    
    @Test
    public void testGetAllBits() {
        int b = 3;
        ByteAsBits btb = new ByteAsBits(b);
        boolean[] bools = btb.getAllBits();
        for(int i = 0; i < 6; i++){
            if(bools[i]) fail();
        } 
        if(!(bools[6] && bools[7]) ) fail();
        
    }
    
    @Test
    public void testToString(){
        int b = 8;
        ByteAsBits btb = new ByteAsBits(b);
        assertTrue(btb.toString().equals("00001000"));
        
        b = 17;
        btb = new ByteAsBits(b);
        assertTrue(btb.toString().equals("00010001"));
    }
    
    @Test
    public void testGetByte(){
        int b = 0;
        ByteAsBits btb = new ByteAsBits(b);
        assertTrue(btb.getByte() == -128);
    }
    
}
