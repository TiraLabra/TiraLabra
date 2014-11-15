package superpakkaussofta;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Main class that contains the compression algorithms.
 *
 * @author Jouko
 */
public class HuffmanCompressor {
    
    public byte[] compress(byte[] data){
        
        HuffmanNode tree = createHuffmanTree(data);
        
        String codes[] = countNewCodes(tree);
        
        System.out.println("koodit: ");
        for(int i = 0; i < 256; i++){
            if(!codes[i].equals(""))
                System.out.println(i-128 + ": " + codes[i]);
        }
        
        StringBuilder bits = new StringBuilder();
        
        for(int i = 0; i < data.length; i++){
            bits.append(codes[data[i]+128]);
        }
        
        int dummybits = bits.length() % 8;
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
     * Generates a Huffman (binary) tree from given byte array.
     * 
     * @param data data as byte array
     * @return root HuffmanNode
     */
    public HuffmanNode createHuffmanTree(byte[] data){
        
        PriorityQueue<HuffmanNode> pque = createNodeHeap(data);
        
        while(pque.size() > 1){
            HuffmanNode n1 = pque.poll();
            HuffmanNode n2 = pque.poll();
            pque.add(new HuffmanNode(n1.getFreq()+n2.getFreq(), n1, n2));
            System.out.println(n1.getByte() + ": " + n1.getFreq() + ", " + n2.getByte() + ": " + n2.getFreq());
        }
        
        if(pque.peek() != null){
            System.out.println("Ei tyhjä: " + pque.peek());
        }else{
            System.out.println("Tyhjä");
        }
        
        
        return pque.poll();
    }
    
    /**
     * Returns a min heap (PriorityQueue) of HuffmanNodes for each different
     * byte found in data.
     * 
     * @param data
     * @return PriorityQueue<HuffmanNode>
     */
    private PriorityQueue<HuffmanNode> createNodeHeap(byte[] data){
        
        PriorityQueue<HuffmanNode> pque = new PriorityQueue<HuffmanNode>(20, new NodeComparator());
        int[] counts = countBytes(data);
        
        for(int i = 0; i < counts.length; i++){
            if(counts[i] > 0){
                pque.add(new HuffmanNode((byte) (i - 128), counts[i]));
            }
        }
        
        
        return pque;
    }
    /**
     * Counts and returns the amount of each different byte found in data.
     * 
     * @param data
     * @return Amount of different bytes in int array (256 size)
     */
    private int[] countBytes(byte[] data){
        
        int[] bytes = new int[256];
        
        for(int i = 0; i < data.length; i++){
            for(int a = 0; a < 256; a++){
                if(data[i] == a - 128){
                    bytes[a]++;
                    break;
                }
            }
        }
        
        return bytes;
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
     * Comparator for PriorityQueue.
     * TO BE REMOVED at some point.
     * 
     */
    public class NodeComparator implements Comparator<HuffmanNode>{
        
        @Override
        public int compare(HuffmanNode a, HuffmanNode b){
            return a.getFreq() - b.getFreq();
        }
    }
    
    public static void main(String[] args) {
        
        //pääasiassa testijuttuja vielä tässä vaiheessa
        FileIO fio = new FileIO();
        HuffmanCompressor compressor = new HuffmanCompressor();
        
        byte[] data = null;
        try {
            data = fio.read("testifilu.txt");
        } catch (Exception e) {
            System.out.println("luku feilas");
        }
        
        System.out.println("Alkuperäinen:");
        for(int i = 0; i < data.length; i++){
            System.out.println(data[i]);
        }
        /*
        byte[] sort = compressor.copyAndSortByteArray(data);
        System.out.println("Sortattu:");
        for(int i = 0; i < sort.length; i++){
            System.out.println(sort[i]);
        }
        */
        
        byte[] compr = compressor.compress(data);
        try {
            fio.write(compr);
        } catch (Exception e) {
            System.out.println("Tallentamienn feilas:");
            System.out.println(e);
        }
        
    }
}
