/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import datastructures.Tree;

/**
 *
 * @author joonaskylliainen
 */
public class Paketti {
    private Tree tree;
    private String text;
    private byte[] byteTree;
    private byte[] byteText;
    private int apu;
    
    public Paketti(Tree tree, String s) {
        this.tree = tree;
        this.text = s;
        treeToByteChar();
    }
    public Paketti(byte[] byteTree, byte[] byteText) {
        this.byteTree = byteTree;
        this.byteText = byteText;
        byteTextToString();
        byteTreeToTree();
    }
    
    public Tree getTree() {
        return this.tree;
    }
    public String getText() {
        return this.text;
    }

    public byte[] getByteTree() {
        return byteTree;
    }

    public byte[] getByteText() {
        return byteText;
    }
    /**
     *puu käännettään binäärimuotoon tavutaulukoksi
     */
    public void treeToByteChar() {
        byteTree = tree.treeToBinary();
    }
    /**
     * tavuina tallennettu puu muutetaan tällä puuksi
     */
    public void byteTreeToTree() {
        int size = byteTree[0];
        Node node = new Node((char)byteTree[0], 1);
        tree = new Tree(node);
        apu = 1;
        treeWalk(node, "l");
        ++apu;
        treeWalk(node, "r");
        
    }
    /**
     * rekursio tavuina tallennetun puun saattamiseksi takaisin puuksi
     * @param root
     * @param side
     */
    public void treeWalk(Node root, String side) {
        Node node = new Node((char)byteTree[apu],1);
        
        if (side.equals("l")) {
            root.setLeft(node);
        }
        else if(side.equals("r")) {
            root.setRight(node);
        }
        if(byteTree[apu] == 42 && apu < byteTree.length) {
            ++apu;
            treeWalk(node, "l");
            ++apu;
            treeWalk(node, "r");
        }
    }
        
    /**
     * muuttaa tavuina tallennetun tekstin String muotoon
     */
    public void byteTextToString() {
        text = "";
        for (byte b : byteText) {
            String temp = Integer.toBinaryString(b);
            temp = temp.substring(1);
            text += temp;
        }
    }
    
    
}
