package lib.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Hallitsee lähdetiedostosta lukua ja kohdetiedostoon kirjoittamista.
 */
public class IO {
    
    private FileOutputStream output;
    private FileInputStream input;
    
    /**
     * @param inputPath Polku lähdetiedostoon.
     * @param outputPath Polku kohdetiedostoon-
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public IO(String inputPath, String outputPath) throws FileNotFoundException, IOException{
        File inputFile = new File(inputPath);
        input = new FileInputStream(inputFile);        
        
        File outputFile = new File(outputPath);
        if(!outputFile.exists()){
            outputFile.createNewFile();
        }        
        output  = new FileOutputStream(outputFile);                
    }
    /**
     * Kirjoittaa tavun kohdetiedostoon.
     * @param b Kirjoitettava tavu ByteAsBits-oliona.
     * @throws IOException 
     */
    public void write(ByteAsBits b) throws IOException{
        output.write(b.getByte()+128);
    }
    /**
     * Lukee tavun lähdetiedostosta.
     * @return Luettu tavu ByteAsBits-oliona.
     * @throws IOException 
     */
    public ByteAsBits read() throws IOException{
        int b = input.read();
        if(b != -1){
            ByteAsBits ret = new ByteAsBits(b);
            return ret;
        }
        return null;
    }
}
