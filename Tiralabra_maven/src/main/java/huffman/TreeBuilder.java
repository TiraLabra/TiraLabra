
package huffman;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * @author alpa
 * 
 * Class that is used to analyze the input file and build
 * the Huffman tree used in encoding of the file.
 */
public class TreeBuilder {

    private final FileInputStream file;
    private final Byte[] bs;

    /**
     * 
     * @param file the input file
     * @throws Exception 
     */
    public TreeBuilder(FileInputStream file) throws Exception {
        this.file = file;
        bs = new Byte[file.available()];
    }

    /**
     * 
     * @return an Array of singular Nodes
     * @throws Exception 
     */
    public ArrayList<Node> countFrequencies() throws Exception {
        ArrayList<Node> freqs = new ArrayList<Node>();
        int k;
        while ((k = file.read()) != -1) {
        }
        return null;
    }

    /**
     * 
     * @param freqs an Array of singular Nodes
     * @return a PriorityQueue where the lowest weight Node is first
     */
    public PriorityQueue<Node> createQueue(ArrayList<Node> freqs) {
        return null;
    }

    /**
     * 
     * @return the completed Huffman tree
     * @throws Exception
     */
    public Node buildTree() throws Exception {
        ArrayList<Node> freqs = countFrequencies();
        return null;
    }
}
