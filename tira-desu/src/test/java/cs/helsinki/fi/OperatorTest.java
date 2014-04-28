
package cs.helsinki.fi;

import cs.helsinki.fi.desu.Decryption;
import cs.helsinki.fi.desu.Encryption;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Juha Lindqvist
 */
public class OperatorTest {
    
    private Operator op;
    private File keyfile;
    private File tripleKeyfile;
    private File writeTest;
    private FileWriter fw;
    private Scanner fr;
    private Scanner tripleFr;
    private Encryption enc;
    private Decryption dec;
    
    public OperatorTest() throws IOException {
        String args[] = {"", "", "src/test/resources/testkey.txt", "", "", "", ""};
        op = new Operator(args);
        keyfile = new File("src/test/resources/testkey.txt");
        tripleKeyfile = new File("src/test/resources/tripletestkey.txt");
        writeTest = new File("src/test/resources/writetest.txt");
        fr = null;
        fw = null;
    }

    /**
     * Test of encrypt method, of class Operator.
     */
    @Test
    public void testEncrypt() {
        
    }

    /**
     * Test of decrypt method, of class Operator.
     */
    @Test
    public void testDecrypt() {
    }
    
    /**
     * Test of encrypt and decrypt methods, of class Operator.
     */
    @Test
    public void testEncryptAndDecrypt() {
        byte[] key = "testkey".getBytes();
        byte[] result = "test string".getBytes();
        enc = new Encryption();
        dec = new Decryption();
        
        byte[] test = enc.encryptSingleDES(result, key);
        test = dec.decryptSingleDES(test, key);
        
        assertArrayEquals(result, test);
    }

    /**
     * Test of readFile method, of class Operator.
     */
    @Test
    public void testReadFile() {
        try {
        fr = new Scanner(keyfile);
        } catch (FileNotFoundException fnfe) {
            fail("File not found.");
            return;
        }
        byte[] result = fr.nextLine().getBytes();
        byte[] test = op.readFile(keyfile);
        
        assertArrayEquals(result, test);
    }

    /**
     * Test of readTripleKey method, of class Operator.
     */
    @Test
    public void testReadTripleKey() {
        try {
            fr = new Scanner(tripleKeyfile);
        } catch (FileNotFoundException fnfe) {
            fail("File not found.");
            return;
        }
        byte[][] tripleKey = null;
        for (int i = 0; i < 3; i++) {
            tripleKey[i] = fr.nextLine().getBytes();
        }
        byte[] result = fr.nextLine().getBytes();
        byte[] test = op.readFile(keyfile);

        assertArrayEquals(result, test);
    }

    /**
     * Test of writeFile method, of class Operator.
     */
    @Test
    public void testWriteFile() {
        String result = "this is a key test";
        String test = null;
        try {
            fw = new FileWriter(writeTest);
            fw.write(result);

            fr = new Scanner(writeTest);
            test = fr.nextLine();
        } catch (IOException ioe) {
            fail("Error.");
            return;
        }
        assertEquals(result, test);
    }
}
