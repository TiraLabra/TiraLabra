/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package superpakkaussofta;

/**
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
        
        while(pque.size() > 1){
            HuffmanNode n1 = pque.poll();
            HuffmanNode n2 = pque.poll();
            pque.add(new HuffmanNode(n1, n2));
            System.out.println(n1.getByte() + ": " + n1.getFreq() + ", " + n2.getByte() + ": " + n2.getFreq());
        }
        
        if(pque.peek() != null){
            System.out.println("Ei tyhjä: " + pque.peek());
        }else{
            System.out.println("Tyhjä");
        }
        
        
        return pque.poll();
    }
    
}
