/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.game.ai;

import java.math.BigInteger;
import java.util.ArrayList;
import tiralabra.game.Board;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.utilities.Utilities;
import tiralabra.utilities.ZobristHashMap;

/**
 *
 * @author atte
 */
public class AITest {

    private AI ai;
    private Board board;
    private ZobristHashMap hasher;

    @Before
    public void setUp() {
        board = new Board();
        ai = new AI(board);
        hasher = new ZobristHashMap();
    }

    @Test
    public void searchDoesntPlaceOrRemoveAnyPiecesOnTheBoard() {
        for (int i = 0; i < 5; i++) {
            long originalHash = hasher.hash(board.getBoard());
            Utilities.printBoard(board.getBoard());
            
            long move = ai.search();
            long newHash = hasher.hash(board.getBoard());
            Utilities.printBoard(board.getBoard());
            assertEquals("i: " + i, originalHash, newHash);

            board.placeTile(move);
        }
    }
    
    @Test
    public void findingLegalMovesFindsMoves() {
        ArrayList<Long> moves = ai.getAllPossibleMovesInOrder();
        assertFalse(moves.isEmpty());
    }

}
