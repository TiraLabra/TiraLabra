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
        byte[] result = null;
        byte[] test = null;
        byte[][] testKey = new byte[3][56];
        
        try {
            keygen = KeyGenerator.getInstance("DESede");
            secKey = keygen.generateKey();
            for (int i = 0; i < 3; i++)
                testKey[i] = secKey.getEncoded();
            desCipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            desCipher.init(Cipher.ENCRYPT_MODE, secKey);
            result = desCipher.doFinal();
        } catch (NoSuchAlgorithmException nsae)  { fail("No such algorithm available");
        } catch (NoSuchPaddingException nspe)    { fail("No such padding available");
        } catch (InvalidKeyException ike)        { fail("Invalid key");
        } catch (IllegalBlockSizeException ibse) { fail("Illegal Block Size");
        } catch (BadPaddingException bpe)        { fail("Bad padding");
        }
        
        test = enc.encryptTripleDES("test string".getBytes(), testKey);
        
        assertArrayEquals(result, test);
    }
    
    /**
     * Test of encryptSingleDES method, of class Encryption.
     */
    @Test
    public void testEncryptSingleDES() {
        byte[] result = null;
        byte[] test = null;
        try {                
            keygen = KeyGenerator.getInstance("DES");
            secKey = keygen.generateKey();
            result = secKey.getEncoded();
            desCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            desCipher.init(Cipher.ENCRYPT_MODE, secKey);
            result = desCipher.doFinal("test string".getBytes());
        } catch (NoSuchAlgorithmException nsae)  { fail("No such algorithm available");
        } catch (NoSuchPaddingException nspe)    { fail("No such padding available");
        } catch (InvalidKeyException ike)        { fail("Invalid key");
        } catch (IllegalBlockSizeException ibse) { fail("Illegal Block Size");
        } catch (BadPaddingException bpe)        { fail("Bad padding");
        }
        
        test = enc.encryptSingleDES("test string".getBytes(), test);

        assertArrayEquals(result, test);
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
        byte[] test = {0, 1, 2, 3};
        byte[] result = {0, 1, 2, 3, 4, 4, 4, 4};
        
        test = enc.insertPadding(test);
        assertArrayEquals(result, test);
    }
    
    /**
     * Test of insertPadding method, of class Encryption.
     */
    @Test
    public void testInsertPaddingInsertsCorrectlyToFullBlock() {
        byte[] test = {0, 1, 2, 3, 4, 5, 6, 7};
        byte[] result = {0, 1, 2, 3, 4, 5, 6, 7, 8, 8, 8, 8, 8, 8, 8, 8};
                
        test = enc.insertPadding(test);
        assertArrayEquals(result, test);
    }
}
