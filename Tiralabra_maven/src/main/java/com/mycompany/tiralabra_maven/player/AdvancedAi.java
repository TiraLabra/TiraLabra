package com.mycompany.tiralabra_maven.player;

import com.mycompany.tiralabra_maven.data_structures.GameTreeNode;
import com.mycompany.tiralabra_maven.data_structures.Stack;
import com.mycompany.tiralabra_maven.data_structures.StackNode;
import java.util.Random;

/**
 * This should be the most advanced ai in this project. 
 * @author Joel Nummelin
 */
public class AdvancedAi implements Ai{

    private GameTreeNode tree;
    private Stack stack;
    private int depth;
    private int lastMove;

    public AdvancedAi() {
        this.tree = new GameTreeNode(-2, -2);
        this.stack = new Stack();
        this.depth = 4;
        this.lastMove = -2;
    }

    /**
     * Determines next move. 
     * @return move
     */
    @Override
    public int determineMove() {
        if (stack.size() < depth) {
            lastMove = new Random().nextInt(3);
            return lastMove;
        }
        int[] is = treeStatistics(0);
        int counter = 0;
        while (true){
            if (is[0] != 0 || is[1] != 0 || is[2] != 0) {
                break;
            }
            counter++;
            is = treeStatistics(counter);
        }

        int x = is[0];
        is[0] = is[2];
        is[2] = is[1];
        is[1] = x;

        double[] ds = new double[3];

        for (int i = 0; i < 3; i++) {
            ds[i] = (is[i] + 3) * Math.random();
        }

        int move = 0;

        for (int i = 0; i < 3; i++) {
            if (ds[i] > ds[move]) {
                move = i;
            }
        }

        lastMove = move;
        return move;
    }

    
    /**
     * Gives ai last rounds result so ai can improve. 
     * @param result 
     */
    @Override
    public void update(int result) {
        stack.put(new StackNode(oppnentsLastMove(result), result));
        if (stack.size() < depth) {
        } else {
            Stack s = new Stack();
            for (int i = 0; i < depth; i++) {
                s.put(stack.pop());
            }
            updateTree(s);
        }
    }

    /**
     * Puts last rounds result in the game tree.
     * @param s 
     */
    private void updateTree(Stack s) {
        GameTreeNode gtn = tree;
        for (int i = 0; i < depth; i++) {
            StackNode sn = s.pop();
            stack.put(sn);
            gtn.addChild(sn);
            gtn = gtn.getChild(sn);
        }
    }

    /**
     * Searchs familiar move patterns from the tree and gives statistics about them if any. 
     * @param decreaseHeight
     * @return moves
     */
    private int[] treeStatistics(int decreaseHeight) {
        GameTreeNode gtn = tree;
        Stack s = new Stack();
        
        StackNode node = stack.peek();
        for (int i = 0; i < depth -(decreaseHeight + 1); i++) {
            s.put(new StackNode(node.getMove(), node.getResult()));
            node = node.getNext();
        }
        

        for (int i = 0; i < depth - (decreaseHeight + 1); i++) {
            StackNode sn = s.pop();
            if (gtn.getChild(sn) == null) {
                int[] is = {0, 0, 0};
                return is;
            }
            gtn = gtn.getChild(sn);
        }

        int[] is = new int[3];

        System.out.println();
        for (int i = 0; i < 9; i++) {
            if (gtn.getChildren()[i] != null) {
                if (i >= 0 && i < 3) {
                    is[0] += gtn.getTimesPlayed();
                } else if (i >= 3 && i < 6) {
                    is[1] += gtn.getTimesPlayed();
                } else if (i >= 6 && i < 9) {
                    is[2] += gtn.getTimesPlayed();
                }
            }
        }
        return is;
    }

    /**
     * Return opponent's last move. 
     * @param result
     * @return move
     */
    private int oppnentsLastMove(int result) {
        if (result == 0) {
            return lastMove;
        }
        if (lastMove < 2 && result == -1) {
            return lastMove + 1;
        } else if (lastMove > 0 && result == 1) {
            return lastMove - 1;
        } else if (lastMove == 0 && result == 1) {
            return 2;
        } else if (lastMove == 2 && result == -1) {
            return 0;
        }
        return -2;
    }


}
