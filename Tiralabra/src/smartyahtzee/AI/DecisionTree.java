/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates   
 * and open the template in the editor.
 */

package smartyahtzee.AI;

import smartyahtzee.scoring.Scores;

/**
 *
 * @author Essi
 */
public class DecisionTree {
    
    private final TreeNode root;
    private double evs;
    private int leavesVisited;
    private boolean[] marked;
    
    public DecisionTree(int[] dice, boolean[] markedScores)
    {
        root = new TreeNode(dice);
        this.marked = markedScores;
    }
    
    public int[] getRoot()
    {
        return root.getValue();
    }
    
    public TreeNode findNode(int number)
    {
        TreeNode child = root.getChild();
        for (int i = 0; i < number-1; i++)
        {
            child = child.getSibling();
        }
        return child;
    }
    
    /**
     * Laskee puun odotusarvon.
     * 
     * Käy läpi puun lehdet ja laskee odotusarvojen keskiarvon.
     * @return pisteodotusarvo
     */
    
    public double getEV()
    {
        TreeNode node = root.getChild();
        evs = 0.0;
        leavesVisited = 0;
        
        countEV(node);
        
        if (leavesVisited == 0)
        {
            return 0;
        }

        double ev = evs / leavesVisited;
        //System.out.println("TreeEV: " +ev);
        return ev;
    }
    
    
    /**
     * Käy läpi puun.
     * 
     */
    
    public double getEV(TreeNode node)
    {
        evs = 0.0;
        leavesVisited = 0;
        
        countEV(node);
        
        if (leavesVisited == 0)
        {
            return 0;
        }

        double ev = evs / leavesVisited;
        return ev;
    }
    
    /**
     * Apumetodi puun läpikäyntiin.
     * 
     * 
     * @param node node, josta alkaen käydään läpi
     */
    
    private void countEV(TreeNode node)
    {        
        do {
            if (node.getValue().length == 5)
            {
                evs += Scores.calculateBestScore(node.getValue(), marked);
                leavesVisited++;
            } else {
                countEV(node.getChild());
            }
            node = node.getSibling();
        } while (node != null);
            
    }
}
