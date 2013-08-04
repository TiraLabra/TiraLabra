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
        this.timesPlayed = 0;
    }

    public GameTreeNode[] getChildren() {
        return children;
    }

    public GameTreeNode getChild(int move, int result) {
        for (GameTreeNode child : children) {
            if (child.move == move && child.result == result) {
                return child;
            }
        }
        return null;
    }

    public int getTimesPlayed() {
        return timesPlayed;
    }

    public void setTimesPlayed(int timesPlayed) {
        this.timesPlayed = timesPlayed;
    }
    
    public void thisPlayed(){
        timesPlayed++;
    }
    
    
    
}
