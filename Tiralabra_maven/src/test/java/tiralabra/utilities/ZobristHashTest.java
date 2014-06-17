/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tiralabra.utilities;

import java.util.Arrays;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.game.Player;

/**
 *
 * @author atte
 */
public class ZobristHashTest {
    
    private ZobristHash hasher;
    
    @Before
    public void setUp() {
        hasher = new ZobristHash(8, 8);
    }
    
    @Test
    public void sameBoardHashesToTheSameValue() {
        Player[][] board = initalizeARandomlyGeneratedBoard();
        Player[][] board1 = new Player[board.length][board[0].length];
        
        int y = 0;
        for (Player[] players : board) {
            board1[y] = Arrays.copyOf(players, board[0].length);
            y++;
        }
        
        assertEquals(hasher.hash(board), hasher.hash(board1));
    }
    
    public Player[][] initalizeARandomlyGeneratedBoard() {
        Player[][] table = new Player[8][8];
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                table[y][x] = Player.player(new Random().nextInt(3));
            }
        }
        return table;
    }
}
