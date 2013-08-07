/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.data_structures;

/**
 *
 * @author Joel Nummelin
 */
public class GameTreeNode {

    private GameTreeNode[] children;
    private int move;
    private int result;
    private int timesPlayed;

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
    
    public GameTreeNode getChild(StackNode sn){
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
    
    public void addChild(StackNode sn){
        if (getChild(sn) == null){
            children[3 * sn.getMove() + (sn.getResult() + 1)] = new GameTreeNode(sn.getMove(), sn.getResult());
        } else {
            getChild(sn).timesPlayed++;
        }
    }
}
