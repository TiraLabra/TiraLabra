/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tiralabra.game;

/**
 *
 * @author atte
 */
public enum Player {
    NONE(0), BLACK(1), WHITE(2);
    
    private int value;

    private Player(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
    
    /**
     * Returns the player with a given value.
     * @param value
     * @return 
     */
    public static Player player(int value) {
        for (Player player : Player.values()) {
            if (value == player.value) {
                return player;
            }
        }
        return null;
    }
    
    
    /**
     * Returns the opposing player.
     * @param player
     * @return player
     */
    public static Player opposing(Player player) {
        if (player == BLACK) return WHITE;
        else if (player == WHITE) return BLACK;
        else return NONE;
    }
}
