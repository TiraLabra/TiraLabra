package com.mycompany.tiralabra_maven.player;

import com.mycompany.tiralabra_maven.data_structures.Stack;

/**
 * Interface for ais
 * @author Joel Nummelin
 */
public interface Ai {
    public int determineMove();
    
    /**
     * Updates ai. 
     * @param result
     */
    public void update(int result);
    /**
     * Loads ai with given stack. 
     * @param stack 
     */
    public void loadProfile(Stack s);
    /**
     * Is for showing data about ai's decisions. 
     */
    public void showData();
}
