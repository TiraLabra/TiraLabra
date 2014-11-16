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
