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
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author samsalon
 */
public class Reader {

    String filepath = "bang.wav";
    File wavToRead = new File(filepath);
    AudioFileFormat format;
    
    public byte[] readFile() throws FileNotFoundException, IOException, UnsupportedAudioFileException {
        
        format = AudioSystem.getAudioFileFormat(wavToRead);
              
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
    
    public AudioFileFormat getFileFormat() {
        return format;
    }
}
