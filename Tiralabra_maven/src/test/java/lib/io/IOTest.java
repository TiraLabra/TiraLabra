
package lib.io;

import lib.utils.ByteAsBits;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class IOTest {
    
    public IOTest() {
    }
    
    private IO io;
    FileInputStream readOutput;
    FileOutputStream writeInput;
    
    @Before
    public void setUp() throws IOException {
        File out = new File("out.txt");        
        File in = new File("in.txt");
        in.createNewFile();
        io = new IO("in.txt", "out.txt");
        readOutput = new FileInputStream(out);
        writeInput = new FileOutputStream(in);
    }
    
    @After
    public void tearDown() {
        File out = new File("out.txt");
        out.delete();
        File in = new File("in.txt");
        in.delete();
    }

    @Test
    public void testWrite() throws Exception {
        int b = 0;
        io.write(new ByteAsBits(b));        
        int result = readOutput.read();
        assertTrue(result == b);
    }

    @Test
    public void testRead() throws Exception {
        byte b = -128;
        writeInput.write(b+128);
        ByteAsBits result = io.read();
        assertTrue(result.getByte() == b);
        assertTrue(io.read() == null);
    }
    
}
