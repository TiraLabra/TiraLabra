/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package superpakkaussofta;

import java.math.BigInteger;

/**
 * Main class.
 *
 * @author Jouko
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        long aikaAlussa = System.currentTimeMillis();
        
        FileIO fio = new FileIO();
        HuffmanCompressor compressor = new HuffmanCompressor();
        
        
        if(args.length != 2 || !(args[0].equals("pakkaa") || args[0].equals("pura"))){
            System.out.println("Kayta ohjelmaa parametrein:\n\npakkaa *pakattava tiedosto*\npura *purettava tiedosto*\n");
        }else if(args[0].equals("pakkaa")){
            compress(fio, compressor, args[1]);
        }else if(args[0].equals("pura")){
            uncompress(fio, compressor, args[1]);
        }
        
        //compress(fio, compressor, "kuvajpeg.jpg");
        //uncompress(fio, compressor, "kuvapieni.bmp.huf");
        
        
        long aikaLopussa = System.currentTimeMillis();
        System.out.println("AIKA: " + (aikaLopussa - aikaAlussa) + " ms.");
        
    }
    private static void compress(FileIO fio, HuffmanCompressor compressor, String path){
        
        byte[] data = null;
        System.out.println("Luetaan tiedostoa..");
        try {
            data = fio.read(path);
        } catch (Exception e) {
            System.out.println("Virhe tiedostoa luettaessa!");
        }
        /*
        System.out.println("Alkuperäinen:");
        for(int i = 0; i < data.length; i++){
            System.out.println(data[i]);
        }
        */
        byte[] compr = compressor.compress(data);
        
        System.out.println("Tallennetaan pakattu tiedosto levylle..");
        try {
            fio.write(compr, path + ".huf");
            System.out.println("Pakkaus valmis!");
        } catch (Exception e) {
            System.out.println("Levylle tallennus epäonnistui!");
            //System.out.println(e);
        }
    }
    private static void uncompress(FileIO fio, HuffmanCompressor compressor, String path){
        
        byte[] data = null;
        System.out.println("Luetaan tiedostoa..");
        try {
            data = fio.read(path);
        } catch (Exception e) {
            System.out.println("Virhe tiedostoa luettaessa!");
        }
        
        byte[] uncompr = compressor.decompress(data);
        
        try {
            fio.write(uncompr, path + ".ava");
            System.out.println("Tiedoston purkaminen valmis!");
        } catch (Exception e) {
            System.out.println("Tiedoston tallennus epäonnistui!");
            System.out.println(e);
        }
    }
    
}
