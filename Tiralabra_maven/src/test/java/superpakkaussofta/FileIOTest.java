/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package superpakkaussofta;

import com.sun.net.httpserver.Authenticator;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import junit.framework.TestCase;
import org.junit.Assert;

/**
 *
 * @author Jouko
 */
public class FileIOTest extends TestCase {
    
    FileIO fio;
    String filepath;
    
    
    @Override
    protected void setUp() throws Exception {
        fio = new FileIO();
        filepath = "testfile";
        byte[] d = {10, 20};
        FileOutputStream out = new FileOutputStream(filepath);
        out.write(d);
        out.close();
    }
    
    @Override
    protected void tearDown() throws Exception {
        Files.delete(Paths.get(filepath));
    }

    /**
     * Test of read method, of class FileIO.
     */
    public void testRead() throws Exception {
        byte[] expResult = {10, 20};
        byte[] result = fio.read(filepath);
        Assert.assertArrayEquals(expResult, result);
        
    }

    
}
