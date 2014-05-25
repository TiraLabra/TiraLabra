package lib.compressors;

import java.io.IOException;
import java.util.LinkedList;
import lib.io.ByteAsBits;
import lib.io.IO;

/** Pakkaa tiedoston käyttäen Lempel-Ziv 77 -algoritmia.
 */
public class CompressorLZ77 {
    private IO io;
    private int windowSize = 4096;  
    private int bufferSize = 16;
    private LinkedList<Byte> window; //Liukuva ikkuna, jo käsiteltyjä, osoitinta edeltäviä tavuja.
    private LinkedList<Byte> buffer; //Osoittimen jälkeen tulevia tavuja. 
    private LinkedList<Boolean> outputStream; //Ulostulotiedostoon kirjoitettava bittivirta.
    
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
        outputStream = new LinkedList<Boolean>();
        
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
        finalWrite();
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
                int j = 1;
                while(j < buffer.size()&& i+j < window.size() && buffer.get(j) == window.get(i+j)){                    
                    l++;
                    j++;
                }
                if (l > bestLength){
                    bestLength = l;
                    bestOffset = i;
                }
            }
            
        }
        if(bestLength > 0){
            return new int[]{windowSize+1-bestOffset, bestLength};
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
        outputStream.add(true);
        
        String str = Integer.toBinaryString(distance);
        while(str.length() < 12){
            str = "0" + str;
        }
        
        for(char c : str.toCharArray()){
            if(c == '1'){
                outputStream.add(true);
            } else {outputStream.add(false);}
        }
        
        str = Integer.toBinaryString(length);
        while(str.length() < 4){
            str = "0" + str;
        }
        
        for(char c : str.toCharArray()){
            if(c == '1'){
                outputStream.add(true);
            } else {outputStream.add(false);}
        }
        
        write();
    }
    /**
     * Lisää tavun sellaisenaan outputStreamiin.
     * @param b kirjoitettava tavu.
     * @throws IOException 
     */
    private void output(byte b) throws IOException{
        outputStream.add(false);
        ByteAsBits bits = new ByteAsBits(b);
        for(boolean bit: bits.getAllBits()){
            outputStream.add(bit);
        }      
        write();
    }
    /**
     * Kirjoittaa outputStreamista tiedostoon tavuttain. 
     * @throws IOException 
     */
    private void write() throws IOException{
        while(outputStream.size() >= 8){
            boolean[] bits = new boolean[8];
            for(int i = 0; i < 8; i++){
                bits[i] = outputStream.poll();
            }
            ByteAsBits b = new ByteAsBits(bits);
            io.write(b);
        }
    }
    /**
     * Kirjoittaa "ylijäämäbitit" tiedostoon. Tavu täydennetään nollilla.
     * @throws IOException 
     */
    private void finalWrite() throws IOException{
        while(outputStream.size()<8){
            outputStream.add(false);
        }
        write();
    }
}
