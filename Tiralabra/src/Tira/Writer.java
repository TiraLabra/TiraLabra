/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tira;
import ch.aplu.util.*;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
/**
 *
 * @author samsalon
 */
public class Writer {
    
    AudioFormat format;
    
    
    
    
    public void write(byte[] wavAsByteArray, AudioFileFormat format) {
        SoundRecorder sr = new SoundRecorder(format.getFormat());
        sr.writeWavFile(wavAsByteArray, "output.wav");
    }
    
}
