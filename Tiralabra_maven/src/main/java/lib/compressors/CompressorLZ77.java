package lib.compressors;

import lib.utils.ByteAsBits;
import lib.utils.ArrayUtils;
import java.io.IOException;
import java.util.LinkedList;
import lib.io.*;

/** Pakkaa tiedoston käyttäen Lempel-Ziv 77 -algoritmia.
 */
public class CompressorLZ77 {
    private final IO io;
    private final int windowSize = 4096;  
    private final int bufferSize = 15;
    private final LinkedList<Byte> window; //Liukuva ikkuna, jo käsiteltyjä, osoitinta edeltäviä tavuja.
    private final LinkedList<Byte> buffer; //Osoittimen jälkeen tulevia tavuja. 
    private final OutputBuffer outputBuffer;
    
    /**
     * 
     * @param input lähdetiedosto
     * @param output kohdetiedosto, luodaan suorituksen aluksi.
     * @throws IOException 
     */
    public CompressorLZ77(String input, String output) throws IOException{
        io = new IO(input, output);
        window = new LinkedList<Byte>();
        buffer = new LinkedList<Byte>();;
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
            buffer.add(b.getByte());
        }
        while(!buffer.isEmpty()){
            int[] pair = searchWindow();
            if(pair == null){
                output(buffer.peek());
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
     * Etsii ikkunasta pisimmän puskurin kanssa täsmäävän merkkijonon.
     * @return int-parin, josta ensimmäinen ilmaisee täsmäävän merkkijonon etäisyyden(offset), toinen pituuden(length). Jos merkkijonoa ei löydy, null.
     */
    private int[] searchWindow(){
        int bestOffset = -1;
        int bestLength = -1;        
        for(int i = 0; i < window.size(); i++){
            if(buffer.peek() == window.get(i)){
                int l = 1;
                
                for(int j = 1; j < buffer.size(); j++){
                    if(i+j >= window.size()){
                        break;
                    } if(buffer.get(j) != window.get(i+j)){
                        break;
                    } 
                    l++;
                }                
                
                if (l > bestLength){
                    bestLength = l;
                    bestOffset = i;
                } if(bestLength >= 15){
                    break;
                }
                i += l;
            }
            
        }
        if(bestLength > 1){
            return new int[]{bestOffset, bestLength};
        }   
        return null;
    }
    /**
     * Siirtää osoitinta tavun verran eteenpäin. Puskuriin lisätään tiedostosta luettu uusi tavu, puskurin ensimmäinen tavu siirretään ikkunan viimeiseksi.
     * Ikkunan viimeinen putoaa pois.
     * @throws IOException 
     */
    private void movePointer() throws IOException{
        ByteAsBits newByte = io.read();
        if(newByte != null){
            buffer.add(newByte.getByte());;
        }        
        window.add(buffer.poll());
        
        if(window.size() >= windowSize){
            window.removeFirst();
        }
        
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
