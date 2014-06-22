
package ui;

import java.io.IOException;
import java.util.Scanner;
import lib.compressors.CompressorLZ77;
import lib.compressors.CompressorLZW;
import lib.decompressors.DecompressorLZ77;
import lib.decompressors.DecompressorLZW;

public class Main {
    public static void main(String[] args) throws IOException{
        int offsetBits = 16;
        int lengthBits = 4;
        int entryBits = 14;
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Valitse toiminto: ");
        System.out.println("1. Pakkaa LZ77");
        System.out.println("2. Pakkaa LZW");
        System.out.println("3. Pura LZ77");
        System.out.println("4. Pura LZW");
        System.out.println("");       
        String input = scanner.nextLine();
        if(!(input.equals("1") || input.equals("2") || input.equals("3") ||input.equals("4"))){
            System.out.println("Valitse toiminto kijoittamalla numero 1-4.");
            return;
        }
        String[] paths = getPaths(scanner);
        System.out.println("...");
        long start = System.currentTimeMillis();
        try{
            if(input.equals("1")){
                CompressorLZ77 compressor = new CompressorLZ77(paths[0], paths[1],offsetBits,lengthBits);
                compressor.compress();
            } else if(input.equals("2")){
                CompressorLZW compressor = new CompressorLZW(paths[0], paths[1],entryBits);
                compressor.compress();
            } else if(input.equals("3")){
                DecompressorLZ77 decompressor = new DecompressorLZ77(paths[0], paths[1],offsetBits,lengthBits);
                decompressor.decompress();
            } else if(input.equals("4")){
                DecompressorLZW decompressor = new DecompressorLZW(paths[0], paths[1],entryBits);
                decompressor.decompress();
            } 
        } catch(Exception e){
            System.out.println("Hups! Tapahtui virhe. Tarkista lähdetiedoston polku!");
            return;
        }
        long end = System.currentTimeMillis();
        System.out.println("Valmis! Aikaa kului "+(end-start)+" millisekuntia. ");
    }
    
    public static String[] getPaths(Scanner scanner){
        String[] ret = new String[2];
        System.out.println("Anna lähdetiedosto: ");
        ret[0] = scanner.nextLine();
        System.out.println("Anna kohdetiedosto(luodaan): ");
        ret[1] = scanner.nextLine();
        return ret;
    }
        
}

