/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package superpakkaussofta;

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
        
        if(Files.exists(Paths.get("testwrite.huf")))
            Files.delete(Paths.get("testwrite.huf"));
    }

    /**
     * Test for reading
     */
    public void testRead() throws Exception {
        byte[] expResult = {10, 20};
        byte[] result = fio.read(filepath);
        Assert.assertArrayEquals(expResult, result);
        
    }
    /**
     *  Test for writing
     */
    public void testWrite() throws Exception{
        byte[] data = {97, 98};
        fio.write(data, "testwrite.huf");
        
        byte[] expData = fio.read("testwrite.huf");

        Assert.assertArrayEquals(data, expData);
    }
    
}
