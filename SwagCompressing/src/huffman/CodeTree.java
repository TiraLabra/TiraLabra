/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package huffman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author robertvalta
 */
public class CodeTree {
    private NodeWithFrequency root;
    private Map<Integer,List<Integer>> table;
    private int symbols;
    
    public CodeTree(NodeWithFrequency root, int symbols) {
        this.root = root;
        this.table = new HashMap<Integer,List<Integer>>();
        this.symbols = symbols;
    }
    
    public void buildBinarySymbol(Node node, List<Integer> prefix) {
        for (int i = 0; i < this.symbols; i++) {
            table.put(null, null);
        }
        
        if (node instanceof NodeWithFrequency) {
            NodeWithFrequency nwf = (NodeWithFrequency)node;
            
            prefix.add(0);
            buildBinarySymbol(nwf.getLeft(), prefix);
            prefix.remove(prefix.size()-1);
            
            prefix.add(1);
            buildBinarySymbol(nwf.getRight(), prefix);
            prefix.remove(prefix.size()-1);
            
        } else if (node instanceof Leaf) {
            Leaf leaf = (Leaf)node;
            table.put(leaf.getSymbol(), new ArrayList<Integer>(prefix));
        }
    }
    
    public List<Integer> getBinarySymbol(int symbol) {
        return this.table.get(symbol);
    }
    
}
