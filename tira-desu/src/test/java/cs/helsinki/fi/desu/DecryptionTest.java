
package cs.helsinki.fi.desu;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
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
    private byte[] encryptedTest;
    
    public DecryptionTest() {
        dec = new Decryption();
        des = new DES();
        encryptedTest = null;
    }
    
    /**
     * Test of decryptTripleDES method, of class Decryption.
     */
    @Test
    public void testDecryptTripleDES() {
        
        fail("Not Implemented Yet.");
        
        byte[] test = null;
        byte[][] testKey = null;
                
        try {
            keygen = KeyGenerator.getInstance("DESede");
            secKey = keygen.generateKey();
            for (int i = 0; i < 3; i++) {
                testKey[i] = secKey.getEncoded();
            }
            encryptedTest = new Encryption().encryptTripleDES("test string".getBytes(), testKey);
            desCipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            desCipher.init(Cipher.DECRYPT_MODE, secKey);
            test = desCipher.doFinal();
        } catch (NoSuchAlgorithmException nsae)  { fail("No such algorithm available");
        } catch (NoSuchPaddingException nspe)    { fail("No such padding available");
        } catch (InvalidKeyException ike)        { fail("Invalid key");
        } catch (IllegalBlockSizeException ibse) { fail("Illegal Block Size");
        } catch (BadPaddingException bpe)        { fail("Bad padding");
        }
                
        assertTrue(Arrays.equals("test string".getBytes(), test));
    }
        
    /**
     * Test of decryptSingleDES method, of class Decryption.
     */
    @Test
    public void testDecryptSingleDES() {
        byte[] test = null;
        secKey = keygen.generateKey();
        byte[] testKey = secKey.getEncoded();
        encryptedTest = new Encryption().encryptSingleDES("test string".getBytes(), testKey);
        
        try {
            keygen = KeyGenerator.getInstance("DES");
            desCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            desCipher.init(Cipher.DECRYPT_MODE, secKey);
            test = desCipher.doFinal(encryptedTest);
        } catch (NoSuchAlgorithmException nsae)  { fail("No such algorithm available");
        } catch (NoSuchPaddingException nspe)    { fail("No such padding available");
        } catch (InvalidKeyException ike)        { fail("Invalid key");
        } catch (IllegalBlockSizeException ibse) { fail("Illegal Block Size");
        } catch (BadPaddingException bpe)        { fail("Bad padding");
        }
        
        assertTrue(Arrays.equals("test string".getBytes(), test));
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
        byte[] test = {2, 7, 4, 8, 5, -128, 0, 0, 0, 0};
        byte[] result = {2, 7, 4, 8, 5};
        
        test = dec.deletePadding(test);
        assertTrue(Arrays.equals(test, result));
    }
    
}
