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
    
    HuffmanNode[] nodes;
    int size;
    PriorityQueue<HuffmanNode> pque;
    
    public NodeHeap(){
        pque = new PriorityQueue<HuffmanNode>(20, new NodeComparator());
        nodes = new HuffmanNode[10];
    }
    private void heapify(int n){
        boolean done = false;
        int nfreq;
        int lfreq;
        int rfreq;
        HuffmanNode res;
        
        while(done == false){
            nfreq = nodes[n].getFreq();
            
            if(2*n + 1 <= size){
                lfreq = nodes[2*n].getFreq();
                rfreq = nodes[2*n + 1].getFreq();
                if(lfreq < nfreq || rfreq < nfreq){
                    if(lfreq < rfreq){
                        res = nodes[n];
                        nodes[n] = nodes[2*n];
                        nodes[2*n] = res;
                        n = 2*n;
                    }else{
                        res = nodes[n];
                        nodes[n] = nodes[2*n + 1];
                        nodes[2*n + 1] = res;
                        n = 2*n + 1;
                    }
                }else{
                    done = true;
                }
            }else if(2*n == size){
                lfreq = nodes[2*n].getFreq();
                if(lfreq < nfreq){
                    res = nodes[n];
                    nodes[n] = nodes[2*n];
                    nodes[2*n] = res;
                    n = 2*n;
                }else{
                    done = true;
                }
            }else{
                done = true;
            }
            
        }
    }
    /**
     * Constructs a min heap based on frequancy of bytes in data.
     * 
     * @param data 
     */
    public NodeHeap(byte[] data){
        pque = new PriorityQueue<HuffmanNode>(20, new NodeComparator());
        int[] counts = countBytes(data);
        
        constructHeapFromByteCounts(counts);
    }
    /**
     * Constructs a min heap from a Huffman tree String representation.
     * 
     * @param tree 
     */
    public NodeHeap(String tree){
        pque = new PriorityQueue<HuffmanNode>(20, new NodeComparator());
        
        int[] counts = new int[256];
        
        int ia = 0;
        int ib = -1;
        int bytee;
        int count;
        
        while(ib + 1 < tree.length()){
            tree = tree.substring(ib + 1);
            ia = tree.indexOf('a');
            ib = tree.indexOf('b');
            bytee = Integer.parseInt(tree.substring(0, ia));
            count = Integer.parseInt(tree.substring(ia + 1, ib));
            
            counts[bytee + 128] = count;
        }
        
        constructHeapFromByteCounts(counts);
        
    }
    /**
     * Construct a heap based on bytes' counts
     * 
     * @param counts 
     */
    private void constructHeapFromByteCounts(int[] counts){
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
        /*
        if(!(o instanceof NodeHeap)){
            return false;
        }
        
        NodeHeap ob = (NodeHeap) o;
        
        return pque.equals(ob.pque);
        */
        return false;
    }
}
