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
import filehandling.Reader;
import java.io.File;
import java.util.Scanner;

public class Main {
    
    public static void main( String[] args ) {

        Scanner lukija = new Scanner(System.in);
        String lause = "";
        
        System.out.println("Anna pakattava: ");
        System.out.println("1 - kirjoita teksti");
        System.out.println("muu - anna tiedoston nimi");
        
//        int i = lukija.nextInt();
//        if (i == 1) {
            lause = lukija.nextLine();
 //       }
//        else {
//            Reader reader = new Reader();
//            lause = reader.readInput();
//        }    
        
        System.out.println("----------------");
        Packer packer = new Packer();
        Paketti paketti = packer.pack(lause);

        System.out.println("-----------------");

        System.out.println(paketti.getLause());

        System.out.println("----------------");

        Decompressor decompressor = new Decompressor(paketti.getTree(), paketti.getLause());
        System.out.println(decompressor.unzip());


    }
    
}
