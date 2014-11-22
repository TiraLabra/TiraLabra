/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package superpakkaussofta;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * A min heap for HuffmanNodes. At the moment using PriorityQueue, but will
 * be later implemented form scratch.
 * 
 *
 * @author Jouko
 */
public class NodeHeap {
    
    PriorityQueue<HuffmanNode> pque;
    
    /**
     * Constructs a min heap based on frequancy of bytes in data.
     * 
     * @param data 
     */
    public NodeHeap(byte[] data){
        pque = new PriorityQueue<HuffmanNode>(20, new NodeComparator());
        int[] counts = countBytes(data);
        
        for(int i = 0; i < counts.length; i++){
            if(counts[i] > 0){
                pque.add(new HuffmanNode((byte) (i - 128), counts[i]));
            }
        }
    }
    /**
     * Constructs a min heap from a Huffman tree String representation.
     * 
     * @param tree 
     */
    public NodeHeap(String tree){
        pque = new PriorityQueue<HuffmanNode>(20, new NodeComparator());
        
        int ia = 0;
        int ib = -1;
        byte b;
        int counts;
        
        while(ib + 1 < tree.length()){
            tree = tree.substring(ib + 1);
            ia = tree.indexOf('a');
            ib = tree.indexOf('b');
            b = (byte) Integer.parseInt(tree.substring(0, ia));
            counts = Integer.parseInt(tree.substring(ia + 1, ib));
            
            pque.add(new HuffmanNode(b, counts));
        }
        
    }
    /**
     * Counts and returns the amount of each different byte found in data.
     * 
     * @param data
     * @return Amount of different bytes as int array (256 size)
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
     * 
     * @param n 
     */
    public void add(HuffmanNode n){
        pque.add(n);
    }
    /**
     * 
     * @return 
     */
    public HuffmanNode poll(){ 
        return pque.poll();
    }
    /**
     * 
     * @return 
     */
    public HuffmanNode peek(){
        return pque.peek();
    }
    /**
     * 
     * @return 
     */
    public int size(){
        return pque.size();
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
    @Override
    public boolean equals(Object o){
        //TODO
        return false;
    }
}
