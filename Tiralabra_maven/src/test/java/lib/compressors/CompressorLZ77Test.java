/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lib.compressors;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CompressorLZ77Test {
    
    public CompressorLZ77Test() {
    }
    
    CompressorLZ77 compressor;
    
    @Before
    public void setUp() throws FileNotFoundException, IOException {
        File file = new File("in.txt");
        file.createNewFile();
        FileOutputStream writeInput = new FileOutputStream(file);
        for(int i = 0; i < 50; i++){
            writeInput.write(i);
        }        
        writeInput.close();
        compressor = new CompressorLZ77("in.txt","out.txt");        
    }
    
    @After
    public void tearDown() {
        File in = new File("in.txt");
        in.delete();
        File out = new File("out.txt");
        out.delete();
    }

    @Test
    public void testCompress() throws Exception {
        compressor.compress();
    }
    
}
