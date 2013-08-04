package com.mycompany.tiralabra_maven.player;

import com.mycompany.tiralabra_maven.data_structures.GameTreeNode;
import com.mycompany.tiralabra_maven.data_structures.Stack;
import com.mycompany.tiralabra_maven.data_structures.StackNode;

/**
 *
 * @author Joel Nummelin
 */
public class Ai {
    private GameTreeNode tree;
    private Stack stack;
    private int lastMove;
    private int depth;
    
    public Ai(){
        this.tree = new GameTreeNode(-2, -2);
        this.stack = new Stack();
        this.lastMove = -2;
        this.depth = 3;
    }

    public int determineMove(){
        int x = 0;
        lastMove = x;
        stack.put(x);
        return x;
    }
    
    public void update(int result){
        stack.setTopResult(result);
        if (stack.size() < depth){
            
        } else {
            StackNode sn = stack.peek();
            for (int i = 0; i < depth; i++) {
                
            }
        }
    }
    
}
