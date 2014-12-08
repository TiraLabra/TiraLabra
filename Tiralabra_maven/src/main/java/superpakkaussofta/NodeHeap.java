/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package superpakkaussofta;


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
    
    /**
     * Creates an empty NodeHeap
     * 
     */
    public NodeHeap(){
        nodes = new HuffmanNode[10];
    }
    
    /**
     * Constructs a min heap based on frequancy of bytes in data.
     * 
     * @param data 
     */
    public NodeHeap(byte[] data){
        nodes = new HuffmanNode[10];
        
        int[] counts = countBytes(data);
        
        constructHeapFromByteCounts(counts);
    }
    /**
     * Constructs a min heap from a Huffman tree String representation.
     * 
     * @param tree 
     */
    public NodeHeap(String tree){
        nodes = new HuffmanNode[10];
        
        System.out.println("Parsitaan tavumääriä..");
        
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
     * Construct a heap based on bytes' counts.
     * 
     * @param counts 
     */
    private void constructHeapFromByteCounts(int[] counts){
        System.out.println("Kootaan kekoa..");
        for(int i = 0; i < counts.length; i++){
            if(counts[i] > 0){
                this.add(new HuffmanNode((byte) (i - 128), counts[i]));
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
        System.out.println("Lasketaan tavujen määriä..");
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
     * Adds given HuffmanNode to this heap.
     * 
     * @param n HuffmanNode
     */
    public void add(HuffmanNode n){
        
        size++;
        if(size >= nodes.length){
            HuffmanNode[] longer = new HuffmanNode[(size + 1)*2];
            System.arraycopy(nodes, 1, longer, 1, size - 1);
            nodes = longer;
        }
        
        int i = size;
        while(i > 1 && nodes[i/2].getFreq() > n.getFreq()){
            nodes[i] = nodes[i/2];
            i = i/2;
        }
        nodes[i] = n;
    }
    /**
     * Deletes (from this heap) and returns the HuffmanNode with least
     * frequency.
     * 
     * @return HuffmanNode with least frequency
     */
    public HuffmanNode poll(){ 
        HuffmanNode n = null;
        
        if(size > 0){
            n = nodes[1];

            nodes[1] = nodes[size];
            size--;
            if(size > 0){
                heapify(1);
            }
        }
        
        
        return n;
    }
    /**
     * Returns HuffmanNode with least frequency (or null if empty).
     * 
     * @return HuffmanNode with least frequency
     */
    public HuffmanNode peek(){
        if(size > 0){
            return nodes[1];
        }else{
            return null;
        }
    }
    /**
     * 
     * @return Amount of HuffmanNodes in this heap. 
     */
    public int size(){
        return size;
    }
    /**
     * Heapify operation
     * 
     * @param n 
     */
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
    @Override
    public boolean equals(Object o){
        
        if(!(o instanceof NodeHeap)){
            return false;
        }
        NodeHeap n = (NodeHeap) o;
        
        if(this.size() != n.size()){
            return false;
        }
        
        int l = this.size();
        for(int i = 1; i <= l; i++){
            if(!this.nodes[i].equals(n.nodes[i])){
                return false;
            }
        }
        
        return true;
    }
}
