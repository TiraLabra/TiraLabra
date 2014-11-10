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
        
        //byte[] sorted = copyAndSortByteArray(data);
        PriorityQueue<HuffmanNode> pque = createNodeHeap(data);
        
        /*
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
        */
        
        while(pque.peek() != null){
            HuffmanNode n = pque.poll();
            System.out.println(n.getByte() + ": " + n.getFreq());
        }
        
        
        while(pque.size() > 1){
            HuffmanNode n1 = pque.poll();
            HuffmanNode n2 = pque.poll();
            pque.add(new HuffmanNode(n1.getFreq()+n2.getFreq(), n1, n2));
            //System.out.println(n.getByte() + ": " + n.getFreq());
        }
        
        
        
        
        return pque.poll();
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
