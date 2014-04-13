
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

public class EncryptionTest {
    
    private Encryption enc;
    private DES des;
    private KeyGenerator keygen;
    private SecretKey secKey;
    private Cipher desCipher;
    
    public EncryptionTest() {
        enc = new Encryption();
        des = new DES();
    }
    
    /**
     * Test of encryptTripleDES method, of class Encryption.
     */
    @Test
    public void testEncryptTripleDES() {
        
        fail("Not Implemented Yet.");
        
        byte[] test = null;
        byte[][] testKey = null;
                
        try {
            keygen = KeyGenerator.getInstance("DESede");
            secKey = keygen.generateKey();
            for (int i = 0; i < 3; i++) {
                testKey[i] = secKey.getEncoded();
            }
            
            desCipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            desCipher.init(Cipher.ENCRYPT_MODE, secKey);
            test = desCipher.doFinal();
        } catch (NoSuchAlgorithmException nsae)  { fail("No such algorithm available");
        } catch (NoSuchPaddingException nspe)    { fail("No such padding available");
        } catch (InvalidKeyException ike)        { fail("Invalid key");
        } catch (IllegalBlockSizeException ibse) { fail("Illegal Block Size");
        } catch (BadPaddingException bpe)        { fail("Bad padding");
        }
        
        byte[] result = enc.encryptTripleDES("test string".getBytes(), testKey);
        
        assertTrue(Arrays.equals(test, result));
    }
    
    /**
     * Test of encryptSingleDES method, of class Encryption.
     */
    @Test
    public void testEncryptSingleDES() {
        byte[] test = null;
        secKey = keygen.generateKey();
        byte[] testKey = secKey.getEncoded();
        
        try {
            keygen = KeyGenerator.getInstance("DES");
            desCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            desCipher.init(Cipher.ENCRYPT_MODE, secKey);
            test = desCipher.doFinal("test string".getBytes());
        } catch (NoSuchAlgorithmException nsae)  { fail("No such algorithm available");
        } catch (NoSuchPaddingException nspe)    { fail("No such padding available");
        } catch (InvalidKeyException ike)        { fail("Invalid key");
        } catch (IllegalBlockSizeException ibse) { fail("Illegal Block Size");
        } catch (BadPaddingException bpe)        { fail("Bad padding");
        }
        
        byte[] result = enc.encryptSingleDES("test string".getBytes(), testKey);
        
        assertTrue(Arrays.equals(test, result));
    }
    
    /**
     * Test of encrypt64Block method, of class Encryption.
     */
    @Test
    public void testEncrypt64Block() {
        fail("Not Implemented Yet.");
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
