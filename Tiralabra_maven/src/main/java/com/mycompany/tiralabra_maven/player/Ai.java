package com.mycompany.tiralabra_maven.player;

import com.mycompany.tiralabra_maven.data_structures.Stack;

/**
 *
 * @author Joel Nummelin
 */
public interface Ai {
    public int determineMove();
    
    public int update(int result);
    public void loadProfile(Stack s);
}
