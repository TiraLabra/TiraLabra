
package cs.helsinki.fi.desu;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Juha Lindqvist <juha.lindqvist@cs.helsinki.fi>
 */

public class DecryptionTest {
    
    private Decryption dec;
    private DES des;
    private KeyGenerator keygen;
    private SecretKey secKey;
    private Cipher desCipher;
    
    public DecryptionTest() {
        dec = new Decryption();
        des = new DES();
    }
    
    /**
     * Test of decryptTripleDES method, of class Decryption.
     */
    @Test
    public void testDecryptTripleDES() {
        byte[] result = null;
        byte[][] testKey = new byte[3][56];
                
        try {
            keygen = KeyGenerator.getInstance("DESede");
            for (int i = 0; i < 3; i++) {
                secKey = keygen.generateKey();
                testKey[i] = secKey.getEncoded();
            }
            desCipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            desCipher.init(Cipher.DECRYPT_MODE, secKey);
            result = desCipher.doFinal();
        } catch (NoSuchAlgorithmException nsae)  { fail("No such algorithm available");
        } catch (NoSuchPaddingException nspe)    { fail("No such padding available");
        } catch (InvalidKeyException ike)        { fail("Invalid key");
        } catch (IllegalBlockSizeException ibse) { fail("Illegal Block Size");
        } catch (BadPaddingException bpe)        { fail("Bad padding");
        }

        byte[] test = new Encryption().encryptTripleDES("test string".getBytes(), testKey);
        assertArrayEquals(result, test);
    }
        
    /**
     * Test of decryptSingleDES method, of class Decryption.
     */
    @Test
    public void testDecryptSingleDES() {
        byte[] test;
        byte[] result = null;
        
        try {
            keygen = KeyGenerator.getInstance("DES");
            desCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            secKey = keygen.generateKey();
            desCipher.init(Cipher.ENCRYPT_MODE, secKey);
            result = desCipher.doFinal("test string".getBytes());
        } catch (NoSuchAlgorithmException nsae)  { fail("No such algorithm available");
        } catch (NoSuchPaddingException nspe)    { fail("No such padding available");
        } catch (InvalidKeyException ike)        { fail("Invalid key");
        } catch (IllegalBlockSizeException ibse) { fail("Illegal Block Size");
        } catch (BadPaddingException bpe)        { fail("Bad padding");
        }
               
        test = new Encryption().encryptSingleDES("test string".getBytes(), result);
        assertArrayEquals(result, test);
    }
    
    /**
     * Test of decrypt64Block method, of class Decryption.
     */
    @Test
    public void testDecrypt64Block() {
        fail("Not Implemented Yet.");
    }

    /**
     * Test of deletePadding method, of class Decryption.
     */
    @Test
    public void testDeletePadding() {
        byte[] test = {2, 7, 4, 8, 4, 4, 4, 4};
        byte[] result = {2, 7, 4, 8};
        
        test = dec.deletePadding(test);
        assertArrayEquals(result, test);
    }
    
    /**
     * Test of deletePadding method, of class Decryption.
     */
    @Test
    public void testDeletePaddingReturnsNullIfFullyPadded() {
        byte[] test = {8,8,8,8,8,8,8,8};
        byte[] result = {};

        test = dec.deletePadding(test);
        assertArrayEquals(result, test);
    }
}
