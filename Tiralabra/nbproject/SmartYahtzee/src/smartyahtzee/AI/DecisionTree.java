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
        return ev;
    }
    
    private void countEV(TreeNode node)
    {
        if (node.getValue().length == 5)
        {
            do {
                evs += Scores.calculateBestScore(node.getValue(), marked);
                leavesVisited++;
                node = node.getSibling();
            } while (node != null);
            
            
        } else {
            countEV(node.getChild());
        }
    }
}
