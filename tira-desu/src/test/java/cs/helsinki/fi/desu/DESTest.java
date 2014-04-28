package cs.helsinki.fi.desu;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Juha Lindqvist <juha.lindqvist@cs.helsinki.fi>
 */

public class DESTest {
    
    private DES des;
    
    public DESTest() {
        this.des = new DES();
    }

    /**
     * Test of permute method, of class DES.
     */
    @Test
    public void testPermute() {
        
    }

    /**
     * Test of feistelF method, of class DES.
     */
    @Test
    public void testFeistelF() {
        
    }

    /**
     * Test of feistelS method, of class DES.
     */
    @Test
    public void testFeistelS() {
    }

    /**
     * Test of generateKeys method, of class DES.
     */
    @Test
    public void testGenerateSubkeys() {
        byte[] test = {1, 2, 3, 4, 5, 6, 7, 8};
        byte[][] result = des.generateSubkeys(test);
    }
}
