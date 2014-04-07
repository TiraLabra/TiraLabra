/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tira;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author samsalon
 */
public class Main {
    
    public static void main(String[] args) throws UnsupportedAudioFileException {
        try {
            Reader r = new Reader();
            Writer w = new Writer();
            Compressor c = new Compressor();
            
            byte[] b = r.readFile();
            System.out.println(r.getFileFormat().toString());
            
            byte[] bb = c.compress(b);
            w.write(bb, r.getFileFormat());
            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
    }      
}
