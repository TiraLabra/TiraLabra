package lib.utils;

import lib.utils.ArrayUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class ArrayUtilsTest {
    
    public ArrayUtilsTest() {
    }

    @Test
    public void testIntToBooleanArray() {
        int k = 1;
        int l = 2;
        boolean[] bits = ArrayUtils.intToBooleanArray(k, l);
        assertTrue(bits[0] == false && bits[1] == true);
        
        k = 2;
        l = 4;
        bits = ArrayUtils.intToBooleanArray(k, l);
        assertTrue(bits[0] == false && bits[1] == false && bits[2] == true && bits[3] == false);
    }

    @Test
    public void testBooleanArrayToInt() {
        boolean[] bits = new boolean[]{false, false, true};
        assertTrue(ArrayUtils.booleanArrayToInt(bits) == 1);
        
        bits = new boolean[]{false, false, false, true, false, true};
        assertTrue(ArrayUtils.booleanArrayToInt(bits) == 5);
        
    }

    @Test
    public void testCombine() {
        byte[] a = new byte[]{(byte)1,(byte)2};
        byte[] b = new byte[]{(byte)3,(byte)4};
        byte[] ab = ArrayUtils.combine(a, b);
        assertTrue(ab.length == 4 && ab[0] == 1 && ab[1] == 2 && ab[2] == 3 && ab[3] == 4);
    }

    @Test
    public void testByteArrayToString() {
        char c = (char)((byte) 1);
        String str = "";
        str += c;
        assertTrue(ArrayUtils.byteArrayToString(new byte[]{(byte)1}).equals(str));
    }

    @Test
    public void testStringToByteArray() {
        char c = (char)((byte) 1);
        String str = "";
        str += c;
        assertTrue(ArrayUtils.stringToByteArray(str)[0] == (byte) 1);       
    }
    
}
