/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package superpakkaussofta;

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
        
        //pääasiassa testijuttuja vielä tässä vaiheessa
        FileIO fio = new FileIO();
        HuffmanCompressor compressor = new HuffmanCompressor();
        
        uncompress(fio, compressor);
        //compress(fio, compressor);
        
    }
    private static void compress(FileIO fio, HuffmanCompressor compressor){
        String path = "testifilu3.txt";
        
        byte[] data = null;
        System.out.println("Reading file..");
        try {
            data = fio.read(path);
        } catch (Exception e) {
            System.out.println("Error: Reading failed!");
        }
        /*
        System.out.println("Alkuperäinen:");
        for(int i = 0; i < data.length; i++){
            System.out.println(data[i]);
        }
        */
        byte[] compr = compressor.compress(data);
        
        System.out.println("Saving compressed file..");
        try {
            fio.write(compr, path + ".huf");
            System.out.println("Compressing complete!");
        } catch (Exception e) {
            System.out.println("Saving to disk failed!");
            System.out.println(e);
        }
    }
    private static void uncompress(FileIO fio, HuffmanCompressor compressor){
        String path = "testifilu3.txt.huf";
        
        byte[] data = null;
        System.out.println("Reading file..");
        try {
            data = fio.read(path);
        } catch (Exception e) {
            System.out.println("Error: Reading failed!");
        }
        
        byte[] uncompr = compressor.decompress(data);
        
        try {
            fio.write(uncompr, path + ".ava");
            System.out.println("Decompressing complete!");
        } catch (Exception e) {
            System.out.println("Saving to disk failed!");
            System.out.println(e);
        }
    }
    
}
