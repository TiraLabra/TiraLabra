
package ui;

import java.io.IOException;
import lib.compressors.*;
import lib.decompressors.*;

public class Main {
    public static void main(String[] args) throws IOException{
            
        String input = "isolorem.txt";
        String output = "test.lzw";
        String unpacked = "decomp.txt";
        
        CompressorLZW compressor = new CompressorLZW(input, output);         
        compressor.compress();    
        
        DecompressorLZW decompressor = new DecompressorLZW(output, unpacked );
        decompressor.decompress();
        
    }
        
}

