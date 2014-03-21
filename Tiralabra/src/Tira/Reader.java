/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tira;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author samsalon
 */
public class Reader {

    String filepath = "bang.wav";
    File wavToRead = new File(filepath);
    
    public byte[] readFile() throws FileNotFoundException, IOException {
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(wavToRead));

        int read;
        byte[] buff = new byte[1024];

        while ((read = in.read(buff)) > 0)
        {
            out.write(buff, 0, read);
        }
        out.flush();
        byte[] audioBytes = out.toByteArray();

        return audioBytes;
        
    }
}
