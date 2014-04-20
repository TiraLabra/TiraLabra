
package cs.helsinki.fi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Julppu
 */
public class OperatorTest {
    
    private Operator op;
    private File keyfile;
    private File tripleKeyfile;
    private File writeTest;
    private FileWriter fw;
    private Scanner fr;
    private Scanner tripleFr;
    
    public OperatorTest() throws IOException {
        String args[] = {"", "", "testkey.txt", "", "", "", ""};
        op = new Operator(args);
        keyfile = new File("testkey.txt");
        tripleKeyfile = new File("tripletestkey.txt");
        writeTest = new File("writetest.txt");
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
     * Test of readFile method, of class Operator.
     */
    @Test
    public void testReadFile() throws FileNotFoundException {
        fr = new Scanner(keyfile);
        byte[] result = fr.nextLine().getBytes();
        byte[] test = op.readFile(keyfile);
        
        assertTrue(Arrays.equals(result, test));
    }

    /**
     * Test of readTripleKey method, of class Operator.
     */
    @Test
    public void testReadTripleKey() {

    }

    /**
     * Test of writeFile method, of class Operator.
     */
    @Test
    public void testWriteFile() {
        
    }
}
