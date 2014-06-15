/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lib.datastructures;

/**
 * Binääripuun solmu.
 * @author Iiro
 */
public class BinaryTreeNode {
    
    private BinaryTreeNode left;
    private BinaryTreeNode right;
    private int key;
    
    public BinaryTreeNode(int key){
        left = null;
        right = null;
        this.key = key;
    }
    
    public BinaryTreeNode getLeft(){
        return left;
    }
    
    public void setLeft(BinaryTreeNode node){
        left = node;
    }
    
    public BinaryTreeNode getRight(){
        return right;
    }
    
    public void setRight(BinaryTreeNode node){
        right = node;
    }
    
    public int getKey(){
        return key;
    }
    
    
}
