package lib.compressors;

import java.io.IOException;
import java.util.LinkedList;
import lib.datastructures.LinkedQueue;
import lib.datastructures.SlidingWindow;
import lib.io.*;
import lib.utils.ArrayUtils;
import lib.utils.ByteAsBits;

/** Pakkaa tiedoston käyttäen Lempel-Ziv 77 -algoritmia.
 */
public class CompressorLZ77 {
    private final IO io;
    private final int windowSize = 4096;  
    private final int bufferSize = 15;
    private final SlidingWindow window; //Liukuva ikkuna, jo käsiteltyjä, osoitinta edeltäviä tavuja.
    private final LinkedQueue<Byte> buffer; //Osoittimen jälkeen tulevia tavuja. 
    private final OutputBuffer outputBuffer;
    
    /**
     * 
     * @param input lähdetiedosto
     * @param output kohdetiedosto, luodaan suorituksen aluksi.
     * @throws IOException 
     */
    public CompressorLZ77(String input, String output) throws IOException{
        io = new IO(input, output);
        window = new SlidingWindow(windowSize);
        buffer = new LinkedQueue<Byte>();;
        outputBuffer = new OutputBuffer(io);
        
    }
    /**
     * Pakkaa tiedoston.
     * @throws IOException 
     */
    public void compress() throws IOException{
        while(buffer.size() < bufferSize){
            ByteAsBits b = io.read();
            if(b == null){
                break;
            }
            buffer.enqueue(b.getByte());
        }
        while(!buffer.isEmpty()){
            int[] pair = window.findBestMatch(buffer);
            if(pair == null){
                output(buffer.getFirst());
                movePointer();
            } else {
                output(pair[0], pair[1]);
                movePointer(pair[1]);
            }            
        }
        outputBuffer.finalWrite();
        io.close();
    }
   
    /**
     * Siirtää osoitinta tavun verran eteenpäin. Puskuriin lisätään tiedostosta luettu uusi tavu, puskurin ensimmäinen tavu siirretään ikkunan viimeiseksi.
     * Ikkunan viimeinen putoaa pois.
     * @throws IOException 
     */
    private void movePointer() throws IOException{
        ByteAsBits newByte = io.read();
        if(newByte != null){
            buffer.enqueue(newByte.getByte());;
        }        
        window.add(buffer.dequeue());
        
        
    }
    /**
     * Siirtää osoitinta n kertaa.
     * @param n
     * @throws IOException 
     */
    private void movePointer(int n) throws IOException{
        for(int i = 0; i < n; i++){
            movePointer();
        }
    }
    
    /**
     * Kooda etäisyys-pituus -parin ja lisää sen outputStreamiin.
     * @param distance
     * @param length
     * @throws IOException 
     */
    private void output(int distance, int length) throws IOException{
        outputBuffer.addBit(true);
        
        boolean[] bits = ArrayUtils.intToBooleanArray(distance, 12);
        for(boolean b : bits){
            outputBuffer.addBit(b);
        }
        
        boolean[] bits2 = ArrayUtils.intToBooleanArray(length, 4);
        for(boolean b : bits2){
            outputBuffer.addBit(b);
        }
        
        outputBuffer.write();
    }
    /**
     * Lisää tavun sellaisenaan outputStreamiin.
     * @param b kirjoitettava tavu.
     * @throws IOException 
     */
    private void output(byte b) throws IOException{
        outputBuffer.addBit(false);
        outputBuffer.addByte(b);
        outputBuffer.write();
    }


}
