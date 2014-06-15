
package ui;

import java.io.IOException;
import lib.compressors.*;
import lib.decompressors.*;

public class Main {
    public static void main(String[] args) throws IOException{
            
        String input = "isolorem.txt";
        String output = "out.comp";
        String unpacked = "decomp.txt";
        int offsetBits = 12;
        int lengthBits = 4;
        int entryBits = 12;
        
        CompressorLZ77 compressor = new CompressorLZ77(input, output,offsetBits, lengthBits);         
        compressor.compress();    
        
        DecompressorLZ77 decompressor = new DecompressorLZ77(output, unpacked,offsetBits, lengthBits);
        decompressor.decompress();
        
    }
        
}

