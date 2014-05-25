
package ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import lib.compressors.CompressorLZ77;

public class Main {
    public static void main(String[] args) throws IOException{
        File file = new File("in.txt");
        file.createNewFile();
        FileOutputStream writeInput = new FileOutputStream(file);
        for(int i = 1; i < 100; i++){
            writeInput.write(i);
        }        
        writeInput.close();
        CompressorLZ77 compressor = new CompressorLZ77("in.txt","out.txt"); 
        
        
        compressor.compress();
        FileInputStream out = new FileInputStream("out.txt");
        FileInputStream in = new FileInputStream("in.txt");
        
        System.out.println("");
        System.out.println("in: ");
        int next = in.read();
        while(next != -1){
            System.out.print(next+" ");
            next = in.read();
        }
        System.out.println("");
        
        System.out.println("out: ");
        next = out.read();
        while(next != -1){
            System.out.print(next+" ");
            next = out.read();
        }
        
        System.out.println("");
        System.out.println("in: ");
        next = in.read();
        while(next != -1){
            System.out.print(next);
            next = in.read();
        }
        System.out.println("");
        
        out.close();
        in.close();
        
        File infile = new File("in.txt");
        infile.delete();
        File outfile = new File("out.txt");
        outfile.delete();
    }
}
