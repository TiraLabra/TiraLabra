package superpakkaussofta;

import java.math.BigInteger;

/**
 * Compressor class that uses Huffman coding.
 * 
 *
 * @author Jouko
 */
public class HuffmanCompressor {
    
    
    /**
     * Compresses given byte array using Huffman compression with given huffman tree.
     * Compression may result an amount of bits that isn't divisible by eight.
     * 0-7 dummybits (and a byte to tell the amount) are inserted in front of the bit String to fix that.
     * 
     * @param data
     * @param tree
     * @return 
     */
    public byte[] compress(byte[] data, HuffmanNode tree){
        
        String codes[] = countNewCodes(tree);
        /*
        System.out.println("koodit: ");
        for(int i = 0; i < 256; i++){
            if(!codes[i].equals(""))
                System.out.println(i-128 + ": " + codes[i]);
        }
        */
        
        StringBuilder bits = new StringBuilder();
        
        for(int i = 0; i < data.length; i++){
            bits.append(codes[data[i]+128]);
        }
        /*
        System.out.println("data: " + bits.toString());
        System.out.println("datan pituus: " + bits.length());
        System.out.println("lisättävät nollat: " + (8 - (bits.length() % 8)));
        */
        int dummybits = 8 - (bits.length() % 8);
        
        for(int i = 0; i < dummybits; i++){
            bits.insert(0, '0');
        }
        
        System.out.println("Pakattu data: " + bits.toString());
        System.out.println("Pakattu biginttinä: " +  new BigInteger(bits.toString(), 2));
        
        byte[] compressed = new BigInteger(bits.toString(), 2).toByteArray();
        byte[] comprWithDummyNumber = new byte[compressed.length + 1];
        
        for(int i = 1; i < comprWithDummyNumber.length; i++){
            comprWithDummyNumber[i] = compressed[i - 1];
        }
        comprWithDummyNumber[0] = (byte) dummybits;
        
        
        return comprWithDummyNumber;
    }
    /**
     * Decompresses data array (which includes a generated huffman tree)
     * and passes it as a new data array
     * 
     * @param data byte array (huffman tree included)
     * @return decompressed data as byte array
     */
    public byte[] decompress(byte[] data, TreeOperator oper){
        
        System.out.println("Alkuperäinen data stringinä: " + new String(data));
        
        int cp = getTreeCutPoint(data);
        String stree = parseTreeString(data, cp);
        System.out.println("Puu stringinä: " + stree);
        byte[] pureData = parseOnlyData(data, cp);
        System.out.println("Vain data stringinä: " + new String(pureData));
        
        HuffmanNode tree = oper.constructTree(stree);
        
        System.out.println("Uudelleen koottu puu: " + tree);
        
        return null;
    }
    /**
     * Parses and converts to String a Huffman tree information from
     * given byte array.
     * 
     * @param data byte array
     * @param cutPoint index that separates compressed data and tree
     * @return Huffman tree information as String
     */
    private String parseTreeString(byte[] data, int cutPoint){
        
        byte[] treeBytes = new byte[cutPoint];
        System.arraycopy(data, 0, treeBytes, 0, cutPoint);
        
        return new String(treeBytes);
    }
    /**
     * Parses compressed data from byte array that includes both data and
     * a Huffman tree
     * 
     * @param data byte array
     * @param cutPoint index that separates compressed data and tree
     * @return compressed data as byte array
     */
    private byte[] parseOnlyData(byte[] data, int cutPoint){
        
        int dataLength = data.length - (cutPoint + 1);
        byte[] onlyData = new byte[dataLength];
        
        System.arraycopy(data, cutPoint + 1, onlyData, 0, dataLength);
        //System.out.println("Vain data stringinä" + new String(onlyData));
        
        return onlyData;
    }
    /**
     * Searches and returns an index that separates Huffman tree and compressed
     * data from given byte array.
     * 
     * @param data byte array
     * @return index separating tree and data as int
     */
    private int getTreeCutPoint(byte[] data){
        
        int cp = 0;
        
        while(cp < data.length && data[cp] != 99){  //99 = c
            cp++;
        }
        
        return cp;
    }
    
    /**
     * Uses a Huffman tree to calculate a new code for each
     * byte found in the tree.
     * 
     * @param tree root HuffmanNode
     * @return String array with binary code for each byte
     */
    private String[] countNewCodes(HuffmanNode tree){
        
        String[] codes = new String[256];
        for(int i = 0; i < codes.length; i++){
            codes[i] = "";
        }
        countCodesRecursively(codes, "", tree);
        
        return codes;
    }
    /**
     * Calculates new binary codes recursively
     * 
     * @param codes a String array
     * @param code starting binary code as String
     * @param t root HuffmanNode
     */
    private void countCodesRecursively(String[] codes, String code, HuffmanNode t){
        
        if(t.getLeft() == null && t.getRight() == null){
            codes[t.getByte()+128] = code;
        }else{
            countCodesRecursively(codes, code + "0", t.getLeft());
            countCodesRecursively(codes, code + "1", t.getRight());
        }
        
    }
    /**
     * Concatenates Huffman tree with byte array.
     * 
     * @param cdata
     * @param tree
     * @return 
     */
    public byte[] concatTreeWithByteArray(byte[] cdata, HuffmanNode tree){
        
        String treeString = tree.toString() + "c";  //lopetusmerkki
        byte[] bytesTree = treeString.getBytes();
        int treeLength = bytesTree.length;
        int dataLength = cdata.length;
        byte[] compWithTree = new byte[dataLength + treeLength];
        
        System.out.println("puu: " + treeString);
        System.out.println("puun pituus tavuina: " + treeLength);
        
        System.arraycopy(bytesTree, 0, compWithTree, 0, treeLength);
        System.arraycopy(cdata, 0, compWithTree, treeLength, dataLength);
        
        System.out.println("lopullinen data stringinä: " + new String(compWithTree));
        
        return compWithTree;
        
    }

}
