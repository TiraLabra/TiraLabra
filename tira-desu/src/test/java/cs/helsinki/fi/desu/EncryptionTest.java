
package cs.helsinki.fi.desu;

import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Juha Lindqvist <juha.lindqvist@cs.helsinki.fi>
 */

public class EncryptionTest {
    
    private Encryption enc;
    private DES des;
    
    public EncryptionTest() {
        enc = new Encryption();
        des = new DES();
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
        byte[] test = "000000000000000".getBytes();
        byte[] result = "82DCBAFBDEAB6602".getBytes();
        byte[] key = "10316E028C8F3B4A".getBytes();
        
        test = enc.encryptSingleDES(test, key);
        
        assertEquals("82DCBAFBDEAB6602", new String(test));
    }
    
    /**
     * Test of encrypt64Block method, of class Encryption.
     */
    @Test
    public void testEncrypt64Block() {
        byte[] test = {0, 0, 0, 0, 0, 0, 0, 0};
        byte[] key = {1&1, 0&1, 3&1, 1&1, 6&1, 'E'&1, 0&1, 2&1, 8&1, 'C'&1, 8&1, 'F'&1, 3&1, 'B'&1, 4&1, 'A'&1};
        byte[][] subkeys = des.generateSubkeys(key);
        enc.encrypt64Block(test, subkeys);
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
        
        assertTrue(Arrays.equals(result, test));
    }
}
