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
    private String s;
    
    public Paketti(Tree tree, String s) {
        this.tree = tree;
        this.s = s;
    }
    
    public Tree getTree() {
        return this.tree;
    }
    public String getLause() {
        return this.s;
    }
    
    
}
