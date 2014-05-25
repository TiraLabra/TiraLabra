package huffman;

import java.io.FileInputStream;

/**
 * @author alpa
 * 
 * Implementation of Huffman coding
 */
public class Huffman {

    /**
     * @param filename the name and path of the file that is processed
     * @throws Exception Either FileNotFoundException or IOException
     */
    public void run(String filename) throws Exception {
        Node n = new TreeBuilder(new FileInputStream(filename)).buildTree();
    }
}
