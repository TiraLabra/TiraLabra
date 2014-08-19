/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smartyahtzee.AI;

import java.util.Arrays;

/**
 * Hassu nimi: ei siis ole puulista vaan lista puista
 * @author essalmen
 */
class TreeList {
    
    private DecisionTree[] trees;
    private boolean[] marked;
    
    public TreeList(int length, int[] dice, boolean[] marked)
    {
        this.trees = new DecisionTree[length];
        this.marked = marked;
        createTrees(dice);
    }
    
    public DecisionTree getTree(int[] root)
    {
        DecisionTree treeWithRoot = null;
        for (DecisionTree tree : trees)
        {
            if (Arrays.equals(tree.getRoot(), root))
            {
                return tree;
            }
        }
        return treeWithRoot;
    }
    
        
    public DecisionTree getBiggestEVtree()
    {
        DecisionTree biggestTree = null;
        double ev = 0.0;
        
        for (DecisionTree tree : trees)
        {
            double treeEV = tree.getEV();
            if (treeEV > ev)
            {
                ev = treeEV;
            }
        }
        
        return biggestTree;
        
    }
    
    /**
     * Luo puut.
     * 
     * @param dice 
     */
    private void createTrees(int[] dice)
    {
        int[][] combinations = new int[4][];
        for (int j = 4; j > 0; j--)
        {
            int[] combination = new int[j];
            for (int k = 0; k < j; k++)
            {
                combination[k] = dice[k];
            }
            
            combinations[4-j] = combination;
        }
        for (int i = 0; i < combinations.length; i++)
        {
            DecisionTree tree = new DecisionTree(combinations[i], marked);
            trees[i] = tree;
        }
        
        
    }
    
    
    
    
}
