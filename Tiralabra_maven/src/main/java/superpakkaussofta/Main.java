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
        TreeOperator toperator = new TreeOperator();
        
        String path = "testifilu2.txt";
        
        byte[] data = null;
        try {
            data = fio.read(path);
        } catch (Exception e) {
            System.out.println("luku feilas");
        }
        
        System.out.println("Alkuperäinen:");
        for(int i = 0; i < data.length; i++){
            System.out.println(data[i]);
        }
        
        HuffmanNode tree = toperator.constructTree(data);
        
        
        byte[] compr = compressor.compress(data, tree);
        byte[] finalComprData = compressor.concatTreeWithByteArray(compr, tree);
        
        try {
            fio.write(finalComprData, path);
        } catch (Exception e) {
            System.out.println("Tallentaminen feilas:");
            System.out.println(e);
        }
    }
    
}
