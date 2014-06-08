package lib.compressors;

import lib.utils.ByteAsBits;
import lib.utils.ArrayUtils;
import java.io.IOException;
import lib.datastructures.Dictionary;
import lib.io.*;

/**
 * Pakkaa tiedoston käyttäen Lempel-Ziv-Welch -algoritmia.
 * @author Iiro
 */
public class CompressorLZW {
    private final IO io;
    private final Dictionary dictionary; 
    private final OutputBuffer outputBuffer;
    
    /**
     * @param input lähdetiedosto.
     * @param output kohdetiedosto.
     * @throws IOException 
     */
    public CompressorLZW(String input, String output) throws IOException{
        io = new IO(input, output);
        dictionary = new Dictionary(true);
        outputBuffer = new OutputBuffer(io);
    }
    
    public void compress() throws IOException{
        byte[] w = null;
        
        while(true){
            ByteAsBits next = io.read();
            if(next == null){
                output(w);
                break;
            }            
            byte[] k = new byte[]{next.getByte()};
            byte[] wk = ArrayUtils.combine(w, k);            
            
            if(dictionary.contains(wk)){
                w = wk;
            } else{
                output(w);
                dictionary.addToDictionary(wk);      
                w = k;
            } 
        }
        outputBuffer.finalWrite();
        io.close();
    }
    /**
     * Kirjoittaa tiedostoon tavujonoa vastaavan hakemistoindeksin.
     * @param w
     * @throws IOException 
     */
    private void output(byte[] w) throws IOException{
        if(w != null){
            int code = dictionary.get(w);
            outputBuffer.addBits(ArrayUtils.intToBooleanArray(code, 12));            
            outputBuffer.write();
        }
    }

}
