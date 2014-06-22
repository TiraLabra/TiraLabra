/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.game.ai;

import java.math.BigInteger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import tiralabra.game.Board;
import tiralabra.game.ai.AI.Move;
import tiralabra.game.ai.AI.Strategy;
import tiralabra.utilities.ArrayList;
import tiralabra.utilities.ZobristHash;

/**
 *
 * @author atte
 */
public class AITest {

    private AI ai;
    private Board board;
    private ZobristHash hasher;

    @Before
    public void setUp() {
        board = new Board();
        ai = new AI(board);
        hasher = new ZobristHash(8, 8);
    }

    @Test
    public void searchDoesntPlaceOrRemoveAnyPiecesOnTheBoard() {
        for (int i = 0; i < 10; i++) {
            BigInteger originalHash = board.getHash();
            
            long move = ai.move();

            assertEquals("Searching for a move should leave the board unchanged!", originalHash, board.getHash());

            board.placeTile(move);
        }
    }
    
    @Test
    public void findingLegalMovesFindsMoves() {
        assertFalse(ai.getLegalMovesInOrder(Strategy.MAXIMIZEPIECES, false, board.playerInTurn()).isEmpty());
    }

    @Test
    public void allFoundLegalMovesAreLegal() {
        ArrayList<Move> moves = ai.getLegalMovesInOrder(Strategy.MAXIMIZEPIECES, false, board.playerInTurn());
        
        for (Move move : moves) {
            assertTrue(board.canPlace(move.x, move.y, board.playerInTurn()));
        }
    }
    
}
