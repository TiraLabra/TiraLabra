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
    
    private TreeNode root;
    
    public DecisionTree(int[] dice)
    {
        root = new TreeNode(dice);
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
        int evs = 0;
        int leavesVisited = 0;
        
        while (node.getSibling() != null)
        {
            node = node.getSibling();
            if (node.getValue().length == 5)
            {
                evs += Scores.calculateBestScore(node.getValue());
                leavesVisited++;
            }
        }
        
        if (leavesVisited == 0)
        {
            return 0;
        }
        
        double ev = evs / leavesVisited;
                
        return ev;
    }
}
