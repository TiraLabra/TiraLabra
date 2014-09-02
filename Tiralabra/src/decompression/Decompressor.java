/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package decompression;

import datastructures.Tree;
import datastructures.Node;

/**
 * Purkaa sille annetun paketin ja palauttaa sen merkkijonona.
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
    
    /**
     * Purkaa paketin kutsumalla nextchar metodia.
     * @return purettu lause
     */
    public String unzip() {
        
        while (paketti.length() -1 > i) {
            nextChar(tree.getRoot(),paketti.charAt(i));
           // System.out.println(paketti.charAt(i));
        }        
        return lause;
    }
    

    /**
     * menee puussa eteenpäin kutsumalla itseään rekursiivisesti.
     * Kun solmulla ei ole enää lapsia, lisätään sen sisältämä merkki lauseeseen.
     * @param root puun juuri
     * @param c 
     */
    private void nextChar(Node root, Character c) {
//        Node nod = root;
        if (root == null) {
            return;
        }
        else if (c.equals('0') && root.getLeft() != null) {
                 nextChar(root.getLeft(), i >= paketti.length() - 1 ? 'c':paketti.charAt(++i));
            }
        
        else if (c.equals('1') && root.getRight() != null) {
                nextChar(root.getRight(),  i >= paketti.length() - 1 ? 'c':paketti.charAt(++i));
            
        }else {
            lause += root.getChar();
            
        }

    }
}
