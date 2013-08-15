/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.data_structures;

/**
 * GameTreeNode is used to follow players move patterns. 
 * @author Joel Nummelin
 */
public class GameTreeNode {

    private GameTreeNode[] children;
    /**
     * Opponents move.
     */
    private int move;
    /**
     * Round result in ai's point of view. -1: loss, 0: draw, 1: win.
     */
    private int result;
    private int timesPlayed;

    /**
     * @param move
     * @param result 
     */
    public GameTreeNode(int move, int result) {
        this.children = new GameTreeNode[9];
        this.move = move;
        this.result = result;
        this.timesPlayed = 1;
    }

    public GameTreeNode[] getChildren() {
        return children;
    }

    public GameTreeNode getChild(int move, int result) {
        return children[3 * move + (result + 1)];
    }
    
    public GameTreeNode getChild(Node sn){
        return children[3 * sn.getMove() + (sn.getResult() + 1)];
    }

    public int getTimesPlayed() {
        return timesPlayed;
    }

    public void setTimesPlayed(int timesPlayed) {
        this.timesPlayed = timesPlayed;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GameTreeNode other = (GameTreeNode) obj;
        if (this.move != other.move) {
            return false;
        }
        if (this.result != other.result) {
            return false;
        }
        if (this.timesPlayed != other.timesPlayed) {
            return false;
        }
        return true;
    }
    
    public void thisPlayed(){
        timesPlayed++;
    }  
    
    /**
     * If node has no child with same move and result than sn, then creates a new child node. 
     * Onherwise adds one to the right child's TimesPlayed attribute. 
     * @param sn 
     */
    public void addChild(Node sn){
        if (getChild(sn) == null){
            children[3 * sn.getMove() + (sn.getResult() + 1)] = new GameTreeNode(sn.getMove(), sn.getResult());
        } else {
            getChild(sn).timesPlayed++;
        }
    }
}
