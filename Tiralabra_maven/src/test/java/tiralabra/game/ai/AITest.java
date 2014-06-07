/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.game.ai;

import java.math.BigInteger;
import tiralabra.utilities.ArrayList;
import tiralabra.game.Board;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.game.Player;
import tiralabra.game.ai.AI.Move;
import tiralabra.utilities.Utilities;
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
        hasher = new ZobristHash();
    }

    @Test
    public void searchDoesntPlaceOrRemoveAnyPiecesOnTheBoard() {
        for (int i = 0; i < 5; i++) {
            long originalHash = hasher.hash(board.getBoard());
            Utilities.printBoard(board.getBoard());
            
            long move = ai.move();
            long newHash = hasher.hash(board.getBoard());
            Utilities.printBoard(board.getBoard());
            assertEquals("i: " + i, originalHash, newHash);

            board.placeTile(move);
        }
    }
    
    @Test
    public void findingLegalMovesFindsMoves() {
        assertFalse(ai.getAllPossibleMovesInOrder().isEmpty());
    }

    @Test
    public void allFoundLegalMovesAreLegal() {
        ArrayList<Move> moves = ai.getAllPossibleMovesInOrder();
        
        for (Move move : moves) {
            assertTrue(board.canPlace(move.x, move.y, board.getPlayerInTurn()));
        }
    }
    
}
