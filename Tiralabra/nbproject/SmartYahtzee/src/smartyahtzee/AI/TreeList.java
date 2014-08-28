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
    
    public int getLength()
    {
        return trees.length;
    }
    
    /**
     * Etsii puun, joka rakentuu annetulle juurelle.
     * 
     * @param root haettavan puun juuri
     */
    
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
            if (tree != null)
            {
                double treeEV = tree.getEV();
                System.out.println("TreeEV: " +treeEV);
                if (treeEV > ev)
                {
                    ev = treeEV;
                    biggestTree = tree;
                }
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
        System.out.println("Combinations: ");
            for (int j = 0; j<dice.length; j++)
            {
                System.out.print(dice[j]);
            }
        int[][] combinations = new int[11][];
        for (int j = 4; j > 0; j--)         
        {
            int[] combination = new int[j];
            for (int k = 0; k < j; k++)
            {
                combination[k] = dice[k];
            }
            
            combinations[4-j] = combination;
        }
        
        for (int j = 4; j > 0; j--) //backward combinations
        {
            int[] combination = new int[j];
            for (int k = 0; k < j; k++)
            {
                combination[k] = dice[4-k];
            }
            
            if (!duplicate(combinations, combination))
            {
                combinations[8-j] = combination;
            }
            
        }
        
        for (int i = 1; i < 4; i++) //single dice
        {
            int[] combination = new int[1];
            combination[0] = dice[i];
            if (!duplicate(combinations, combination))
            {
                combinations[11-i] = combination;
            }
        }
        
        
        trees = new DecisionTree[notNulls(combinations)];
        
        for (int i = 0; i < trees.length; i++)
        {   
            if (combinations[i] != null) 
            {
                DecisionTree tree = new DecisionTree(combinations[i], marked);
                trees[i] = tree;
            }
        }
        
        
    }
    
    /**
     * Tarkistaa sisältääkö taulukko jo arvon.
     * 
     * 
     * @param arrayOfArrays
     * @param array
     * @return 
     */
    private boolean duplicate(int[][] arrayOfArrays, int [] array)
    {
        for (int i = 0; i < arrayOfArrays.length; i++)
        {
            if (Arrays.equals(arrayOfArrays[i], array))
            {
                return true;
            }
        }
        
        return false;
    }
    
    /** Laskee taulukon ei-tyhjät paikat.
     * 
     */
    
    private int notNulls(int[][] array)
    {
        int count = 0;
        for (int i = 0; i < array.length; i++)
        {
            if (array[i] != null)
            {
                count++;
            }
        }
        //System.out.println("Count: "+count);
        return count;
    }
    
    
}
