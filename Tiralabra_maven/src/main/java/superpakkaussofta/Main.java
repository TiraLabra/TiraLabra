/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package superpakkaussofta;

import java.io.IOException;

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
        //long aikaAlussa = System.currentTimeMillis();
        
        FileIO fio = new FileIO();
        HuffmanCompressor compressor = new HuffmanCompressor();
        
        
        if(args.length != 2 || !(args[0].equals("pakkaa") || args[0].equals("pura"))){
            System.out.println("Kayta ohjelmaa parametrein:\n\npakkaa *pakattava tiedosto*\npura *purettava tiedosto*\n");
        }else if(args[0].equals("pakkaa")){
            compress(fio, compressor, args[1]);
        }else if(args[0].equals("pura")){
            uncompress(fio, compressor, args[1]);
        }
        
        //compress(fio, compressor, "lorem1M.txt");
        //uncompress(fio, compressor, "lorem1M.txt.huf");
        
        
        //long aikaLopussa = System.currentTimeMillis();
        //System.out.println("AIKA: " + (aikaLopussa - aikaAlussa) + " ms.");
        
    }
    /**
     * Compresses given file.
     * 
     * @param fio FileIO
     * @param compressor HuffmanCompressor
     * @param path file path
     */
    private static void compress(FileIO fio, HuffmanCompressor compressor, String path){
        
        byte[] data = null;
        System.out.println("Luetaan tiedostoa..");
        try {
            data = fio.read(path);
        } catch (IOException e) {
            System.out.println("Virhe tiedostoa luettaessa! Onko se olemassa?");
            return;
        }
        
        byte[] compr = compressor.compress(data);
        
        System.out.println("Tallennetaan pakattu tiedosto levylle..");
        try {
            fio.write(compr, path + ".huf");
            System.out.println("Pakkaus valmis!");
        } catch (IOException e) {
            System.out.println("Levylle tallennus epäonnistui!");
        }
    }
    /**
     * Uncompresses given file.
     * 
     * @param fio FileIO
     * @param compressor HuffmanCompressor
     * @param path file path
     */
    private static void uncompress(FileIO fio, HuffmanCompressor compressor, String path){
        
        byte[] data = null;
        System.out.println("Luetaan tiedostoa..");
        try {
            data = fio.read(path);
        } catch (IOException e) {
            System.out.println("Virhe tiedostoa luettaessa! Onko se olemassa?");
            return;
        }
        
        byte[] uncompr = null;
        
        try {
            uncompr = compressor.decompress(data);
        } catch (NumberFormatException e) {
            System.out.println("Virheellinen tiedostotyyppi! Purku keskeytetty.");
            return;
        } catch (NullPointerException e) {
            System.out.println("Virheellinen tiedostotyyppi! Purku keskeytetty.");
            return;
        }
        
        
        int cut = path.lastIndexOf(".huf");
        if(cut >= 0){
            path = path.substring(0, cut);
        }
        int dot = path.lastIndexOf(".");
        int lastdir = path.lastIndexOf("/");
        if(dot > lastdir){
            path = path.substring(0, dot) + "_purettu" + path.substring(dot);
        }else{
            path = path + "_purettu";
        }
        
        try {
            fio.write(uncompr, path);
            System.out.println("Tiedoston purkaminen valmis!");
        } catch (IOException e) {
            System.out.println("Tiedoston tallennus epäonnistui!");
        }
    }
    
}
