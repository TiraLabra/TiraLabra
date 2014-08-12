/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smartyahtzee.AI;

/**
 *
 * @author Essi
 */
public class TreeNode {
    private TreeNode child;
    private TreeNode nextSibling = null;
    private int[] value;
    
    /**
    * Konstruktori. Luo myös lapsisolmut.
    * 
    * Luo lapsisolmut ja linkittää ne toisiinsa.
    */
    
    
    public TreeNode(int[] dice)
    {
        value = dice;
        if (value.length < 5)
        {
            TreeNode last = null;
            for (int i = 0; i < 6; i++)
            {
                int[] childDice = new int[value.length+1];
                for (int j = 0; j < dice.length; j++)
                {
                    childDice[j] = dice[j];
                }
                childDice[value.length] = i+1;
                if (i == 0)
                {
                    child = new TreeNode(childDice);
                    last = child;
                } else {
                    TreeNode newSibling = new TreeNode(childDice);
                    last.nextSibling = newSibling;
                    last = newSibling;
                }
                
            }
        }
    }
    
    public int[] getValue()
    {
        return value;
    }
    
    public TreeNode getChild()
    {
        return child;
    }
    
    public TreeNode getSibling()
    {
        return nextSibling;
    }
}
