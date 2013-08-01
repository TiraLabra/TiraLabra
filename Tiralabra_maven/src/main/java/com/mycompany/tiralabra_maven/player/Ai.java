package com.mycompany.tiralabra_maven.player;

import com.mycompany.tiralabra_maven.data_structures.GameTreeNode;
import com.mycompany.tiralabra_maven.data_structures.StackNode;

/**
 *
 * @author Joel Nummelin
 */
public class Ai {
    private GameTreeNode tree;
    private StackNode stack;
    
    public Ai(){
        this.tree = new GameTreeNode(-2, -2);
        this.stack = null;
    }

    public int determineMove(){
        return 0;
    }
    
    public void update(int result){
        
    }
    
}
