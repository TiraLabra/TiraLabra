package com.mycompany.tiralabra_maven.player;

/**
 *
 * @author Joel Nummelin
 */
public interface Ai {
    public int determineMove();
    
    public void update(int result);
}
