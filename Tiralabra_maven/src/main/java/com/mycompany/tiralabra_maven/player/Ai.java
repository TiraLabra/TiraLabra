package com.mycompany.tiralabra_maven.player;

import com.mycompany.tiralabra_maven.data_structures.Stack;

/**
 * Interface for ais
 * @author Joel Nummelin
 */
public interface Ai {
    public int determineMove();
    
    /**
     * 
     * @param result
     */
    public void update(int result);
    public void loadProfile(Stack s);
}
