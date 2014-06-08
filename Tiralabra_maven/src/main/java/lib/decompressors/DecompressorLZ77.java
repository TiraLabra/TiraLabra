
package lib.decompressors;

import lib.utils.ByteAsBits;
import lib.utils.ArrayUtils;
import java.io.IOException;
import java.util.LinkedList;
import lib.datastructures.SlidingTable;
import lib.io.*;

/**
 * LZ77-purkaja.
 * @author Iiro
 */
public class DecompressorLZ77 {
    private final IO io;
    private final int windowSize = 4096;
    private final SlidingTable window;
    private final InputBuffer inputStream;
    
    public DecompressorLZ77(String input, String output) throws IOException{
        io = new IO(input, output);
        window = new SlidingTable(windowSize);
        inputStream = new InputBuffer(io);
    }
    /**
     * Purkaa tiedoston.
     * @throws IOException 
     */
    public void decompress() throws IOException{
        boolean run = true;
        
        inputStream.read();
        while(run){
            run = decode();
            inputStream.read();
        }
        
        byte[] rest = window.pollAll();
        for(byte b : rest){
            io.write(new ByteAsBits(b));
        }
        
        io.close();
    }
    /**
     * Lukee seuraavan merkin sisääntulovirrasta. Ensimmäinen bitti määrää, luetaanko seuraava tavu sellaisenaan vai koodattuna.
     * @return false, jos tiedosto on luettu loppuun.
     * @throws IOException 
     */
    private boolean decode() throws IOException{
        if(inputStream.nextBits(1)[0]){
            return outputEncoded();
        } 
        return outputUncoded();
    }
    
    /** Tulkitsee koodatun merkkijonon ja kirjoittaa aukikirjoitetun tavujonon tiedostoon. 
     * @return 
     * @throws IOException 
     */
    private boolean outputEncoded() throws IOException {
        boolean[] osbits = inputStream.nextBits(12);
        boolean[] lenbits = inputStream.nextBits(4);
        if(osbits != null && lenbits != null){            
            int offset = ArrayUtils.booleanArrayToInt(osbits);
            int length = ArrayUtils.booleanArrayToInt(lenbits)+1;
            ByteAsBits[] bytes = new ByteAsBits[length];
            for(int i = 0; i < length; i++){
                byte b = window.get(offset + i);
                bytes[i] = new ByteAsBits(b);
            }
            for(ByteAsBits b : bytes){
                ByteAsBits bt = window.add(b.getByte());
                if(bt != null){
                    io.write(bt);
                }
            }
            return true;
        } 
        return false;
    }
    /**
     * Kirjoittaa seuraavan tavun sisääntulovirrasta sellaisenaan ulostulotiedostoon.
     * @return
     * @throws IOException 
     */
    private boolean outputUncoded() throws IOException {
        boolean[] bits = inputStream.nextBits(8);
        if(bits != null){
            window.add(new ByteAsBits(bits).getByte());
            return true;
        } else return false;
    }
    
}
