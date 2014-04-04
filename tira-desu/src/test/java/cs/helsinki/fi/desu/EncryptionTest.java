
package cs.helsinki.fi.desu;

import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Juha Lindqvist <juha.lindqvist@cs.helsinki.fi>
 */

public class EncryptionTest {
    
    private Encryption enc;
    
    public EncryptionTest() {
        enc = new Encryption();
    }
    
    /**
     * Test of encryptTripleDES method, of class Encryption.
     */
    @Test
    public void testEncryptTripleDES() {
        
    }
    
    /**
     * Test of encryptSingleDES method, of class Encryption.
     */
    @Test
    public void testEncryptSingleDES() {
        
    }
    
    /**
     * Test of encrypt64Block method, of class Encryption.
     */
    @Test
    public void testEncrypt64Block() {
        
    }

    /**
     * Test of insertPadding method, of class Encryption.
     */
    @Test
    public void testInsertPadding() {
        byte[] test = enc.insertPadding(64);
        byte[] result = new byte[64];
        result[0] = -128;
        for (int i = 1; i < 64; i++)
            result[i] = 0;
        
        boolean success = true;
        for (int i = 0; i < 64; i++)
            if (result[i] != test[i])
                success = false;
        
        assertTrue(success);
    }
}
