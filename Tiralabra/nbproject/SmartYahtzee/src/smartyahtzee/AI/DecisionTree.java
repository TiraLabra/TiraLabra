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
        double evs = 0;
        int leavesVisited = 0;
        
        while (node.getSibling() != null)
        {
            
            TreeNode childnode = node.getChild();
            while (childnode != null && childnode.getSibling() != null)
            {
                if (childnode.getValue().length == 5)
                {
                    evs += Scores.calculateBestScore(childnode.getValue());
                    leavesVisited++;
                }
                childnode = childnode.getSibling();
                
            }
            
            //node = node.getSibling();
            
        }
        
        
        if (leavesVisited == 0)
        {
            return 0;
        }
        
        double ev = evs / leavesVisited;
                
        return ev;
    }
}
