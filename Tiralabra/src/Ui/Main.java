/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Ui;

/**
 *
 * @author joonaskylliainen
 */
import compression.Packer;
import datastructures.Paketti;
import decompression.Decompressor;
import filehandling.MyReader;
import java.io.File;
import java.util.Scanner;

public class Main {
    
    public static void main( String[] args ) {

        Scanner lukija = new Scanner(System.in);
        
        System.out.println("Pakkaa - 1");
        System.out.println("Pura - 2");
        
        if(lukija.nextInt() == 1) {
            String text = "";

            System.out.println("Anna pakattava: ");

            MyReader reader = new MyReader();
            text = reader.readInput();


            System.out.println("----------------");
            Packer packer = new Packer();
            Paketti paketti = packer.pack(text);
            System.out.println("Done!");

        }    
        else {
            
            MyReader reader = new MyReader();
            String file = reader.readStuff();
            System.out.println("------------");
            Paketti paketti = reader.read(file);
            System.out.println("------------");
            Decompressor decompressor = new Decompressor(paketti.getTree(), paketti.getText());
            decompressor.unzip();
        }     

    }
    
}
