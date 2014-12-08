/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package superpakkaussofta;

/**
 * Operations for Huffman trees.
 *
 * @author Jouko
 */
public class TreeOperator {
    
    public TreeOperator(){
    
    }
    /**
     * Generates a Huffman (binary) tree from given byte array.
     * 
     * @param data data as byte array
     * @return root HuffmanNode
     */
    public HuffmanNode constructTree(byte[] data){
        NodeHeap pque = new NodeHeap(data);
        
        HuffmanNode tree = createTreeFromHeap(pque);
        
        return tree;
    }
    /**
     * Generates a Huffman tree from a String (well formed)
     * 
     * @param st well formed String that contains the bytes and frequencies
     * @return root HuffmanNode
     */
    public HuffmanNode constructTree(String st){
        
        NodeHeap pque = new NodeHeap(st);
        
        HuffmanNode tree = createTreeFromHeap(pque);
        
        return tree;
    }
    /**
     * Constructs a Huffman tree from a NodeHeap
     * 
     * @param heap
     * @return root HuffmanNode
     */
    private HuffmanNode createTreeFromHeap(NodeHeap heap){
        System.out.println("Rakennetaan Huffmanin puu..");
        while(heap.size() > 1){
            HuffmanNode n1 = heap.poll();
            HuffmanNode n2 = heap.poll();
            heap.add(new HuffmanNode(n1, n2));
            //System.out.println(n1.getByte() + ": " + n1.getFreq() + ", " + n2.getByte() + ": " + n2.getFreq());
        }
        
        //TODO
        if(heap.peek() == null){
            System.out.println("Virhe: tyhjä puu!");
        }
        
        
        return heap.poll();
    }
    
}
