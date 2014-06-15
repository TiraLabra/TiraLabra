
package ui;

import java.io.IOException;
import lib.compressors.*;
import lib.decompressors.*;

public class Main {
    public static void main(String[] args) throws IOException{
            
        String input = "isolorem.txt";
        String output = "out.comp";
        String unpacked = "decomp.txt";
        
        CompressorLZ77 compressor = new CompressorLZ77(input, output);         
        compressor.compress();    
        
        DecompressorLZ77 decompressor = new DecompressorLZ77(output, unpacked );
        decompressor.decompress();
        
    }
        
}

