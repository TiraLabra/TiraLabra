
package ui;

import java.io.IOException;
import lib.compressors.*;
import lib.decompressors.*;

public class Main {
    public static void main(String[] args) throws IOException{
            
        String input = "loremipsum.txt";
        String output = "out.comp";
        String unpacked = "decomp.txt";
        
        CompressorLZW compressor = new CompressorLZW(input, output);         
        compressor.compress();    
        
        DecompressorLZW decompressor = new DecompressorLZW(output, unpacked );
        decompressor.decompress();
        
    }
        
}

