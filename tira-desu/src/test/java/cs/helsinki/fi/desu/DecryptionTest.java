
package cs.helsinki.fi.desu;

import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Juha Lindqvist <juha.lindqvist@cs.helsinki.fi>
 */

public class DecryptionTest {
    
    private Decryption dec;
    
    public DecryptionTest() {
        dec = new Decryption();
    }
    
    /**
     * Test of decryptTripleDES method, of class Decryption.
     */
    @Test
    public void testDecryptTripleDES() {
        
    }
        
    /**
     * Test of decryptSingleDES method, of class Decryption.
     */
    @Test
    public void testDecryptSingleDES() {
        
    }
    
    /**
     * Test of decrypt64Block method, of class Decryption.
     */
    @Test
    public void testDecrypt64Block() {
        
    }

    /**
     * Test of deletePadding method, of class Decryption.
     */
    public void testDeletePadding() {
        byte[] test = {2, 7, 4, 8, 5, -128, 0, 0, 0, 0};
        byte[] result = {2, 7, 4, 8, 5};
        
        test = dec.deletePadding(test);
        assertTrue(Arrays.equals(test, result));
    }
    
}
