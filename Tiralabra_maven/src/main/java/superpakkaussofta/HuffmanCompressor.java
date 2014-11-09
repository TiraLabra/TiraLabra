package superpakkaussofta;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Main class that contains the compression algorithms.
 *
 * @author Jouko
 */
public class HuffmanCompressor {
    
    public void compress(byte[] data){
        
    }
    /**
     * Generates a Huffman (binary) tree from given byte array.
     * 
     * @param data as byte array
     * @return root HuffmanNode
     */
    public HuffmanNode createHuffmanTree(byte[] data){
        
        byte[] sorted = copyAndSortByteArray(data);
        PriorityQueue<HuffmanNode> pque = new PriorityQueue<HuffmanNode>(20, new NodeComparator());
        
        byte cur;
        byte prev = sorted[0];
        int amount = 1;
        
        for(int i = 1; i < sorted.length; i++){
            cur = sorted[i];
            if(cur == prev){
                amount++;
            }else{
                pque.add(new HuffmanNode(prev, amount));
                amount = 1;
            }
            prev = cur;
        }
        //vimppa
        pque.add(new HuffmanNode(prev, amount));
        
        
        while(pque.peek() != null){
            HuffmanNode n = pque.poll();
            System.out.println(n.getByte() + ": " + n.getFreq());
        }
        
        
        return null;
    }
    /**
     * Copies a byte array and sorts it.
     * 
     * @param data as byte array
     * @return sorted byte array
     */
    public byte[] copyAndSortByteArray(byte[] data){
        
        byte[] sorted = new byte[data.length];
        
        for(int i = 0; i < data.length; i++){
            sorted[i] = data[i];
        }
        
        Arrays.sort(sorted);
        
        return sorted;
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
        
        byte[] sort = compressor.copyAndSortByteArray(data);
        System.out.println("Sortattu:");
        for(int i = 0; i < sort.length; i++){
            System.out.println(sort[i]);
        }
        compressor.createHuffmanTree(data);
    }
}
