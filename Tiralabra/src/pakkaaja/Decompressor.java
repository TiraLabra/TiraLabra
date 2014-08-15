/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pakkaaja;

/**
 *
 * @author joonaskylliainen
 */
public class Decompressor {
    private Tree tree;
    private String paketti;
    private String lause;
    private int i;
    
    public Decompressor(Tree tree, String paketti) {
        this.paketti = paketti;
        this.tree = tree;
        this.lause = "";
        this.i = 0;
    }
    
    public String unzip() {
        
        while (paketti.length() > i) {
            nextChar(tree.getRoot(),paketti.charAt(i));
        }        
        return lause;
    }
    
    /**
     *
     * @param root
     * @param c
     * @param i
     * @return
     */
    
    
    public void nextChar(Node root, Character c) {
        i++;
        if (root == null) {
            return;
        }
        if (c.equals('0') && root.getLeft() != null) {
            nextChar(root.getLeft(), paketti.charAt(i));
        }
        if (c.equals('1') && root.getRight() != null) {
            nextChar(root.getRight(), paketti.charAt(i));
        }
        lause += root.getChar();

    }
}
