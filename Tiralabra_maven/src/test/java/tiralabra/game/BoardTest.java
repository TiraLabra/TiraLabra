/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.game;

import java.util.ArrayList;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.utilities.Utilities;

/**
 *
 * @author atte
 */
public class BoardTest {

    Board board = new Board();
    int[][] verticalTestBoard;
    int[][] horizontalTestBoard;
    int[][] diagonalTestBoard1;
    int[][] diagonalTestBoard2;

    public BoardTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        verticalTestBoard = new int[][]{{0, 1, 2, 0, 0, 2, 1, 0}};
        horizontalTestBoard = new int[][]{{0}, {1}, {2}, {0}, {0}, {2}, {1}, {0}};
        diagonalTestBoard1 = new int[][]{{0, 0, 0, 0},
        {0, 1, 1, 0},
        {0, 2, 2, 0},
        {0, 0, 0, 0}};
        diagonalTestBoard2 = new int[][]{{0, 0, 0, 0},
        {0, 2, 2, 0},
        {0, 1, 1, 0},
        {0, 0, 0, 0}};
    }

    @After
    public void tearDown() {
    }

    @Test
    public void placingABlackPiecePutsAPieceAtTheRightSpot() {
        board.setBoard(Utilities.createPlayerTable(verticalTestBoard), Player.BLACK);

        assertTrue(board.place(3, 0, Player.BLACK, true) > 0);
        assertEquals(Player.BLACK, board.getTile(3, 0));
    }

    @Test
    public void placingAWhitePiecePutsAPieceAtTheRightSpot() {
        board.setBoard(Utilities.createPlayerTable(verticalTestBoard), Player.WHITE);

        assertTrue(board.place(0, 0, Player.WHITE, true) > 0);
        assertEquals(Player.WHITE, board.getTile(0, 0));
    }

    @Test
    public void placingBlacksFlipsPiecesVertically() {
        board.setBoard(Utilities.createPlayerTable(verticalTestBoard), Player.WHITE);

        assertEquals(1, board.place(3, 0, Player.BLACK, true));
        assertEquals(1, board.place(4, 0, Player.BLACK, true));

        assertEquals(6, board.blackPieces());
        assertEquals(0, board.whitePieces());
    }

    @Test
    public void placingWhitesFlipsPiecesVertically() {
        board.setBoard(Utilities.createPlayerTable(verticalTestBoard), Player.WHITE);

        assertEquals(1, board.place(0, 0, Player.WHITE, true));
        assertEquals(1, board.place(7, 0, Player.WHITE, true));
        assertEquals(6, board.whitePieces());
        assertEquals(0, board.blackPieces());
    }

    @Test
    public void placingBlacksFlipsPiecesHorizontally() {
        board.setBoard(Utilities.createPlayerTable(horizontalTestBoard), Player.WHITE);

        assertEquals(1, board.place(0, 3, Player.BLACK, true));
        assertEquals(1, board.place(0, 4, Player.BLACK, true));
        assertEquals(6, board.blackPieces());
        assertEquals(0, board.whitePieces());
    }

    @Test
    public void placingWhitesFlipsPiecesHorizontally() {
        board.setBoard(Utilities.createPlayerTable(horizontalTestBoard), Player.WHITE);

        assertEquals(1, board.place(0, 0, Player.WHITE, true));
        assertEquals(1, board.place(0, 7, Player.WHITE, true));
        assertEquals(6, board.whitePieces());
        assertEquals(0, board.blackPieces());
    }
    
    @Test
    public void placingBlacksFlipsPiecesDiagonally1() {
        board.setBoard(Utilities.createPlayerTable(diagonalTestBoard1), Player.BLACK);

        assertEquals(1, board.place(0, 3, Player.BLACK, true));
        assertEquals(1, board.place(3, 3, Player.BLACK, true));
        assertEquals(6, board.blackPieces());
        assertEquals(0, board.whitePieces());
    } 
    
    @Test
    public void placingBlacksFlipsPiecesDiagonally2() {
        board.setBoard(Utilities.createPlayerTable(diagonalTestBoard2), Player.BLACK);

        assertEquals(1, board.place(0, 0, Player.BLACK, true));
        assertEquals(1, board.place(3, 0, Player.BLACK, true));
        assertEquals(6, board.blackPieces());
        assertEquals(0, board.whitePieces());
    } 
    
    @Test
    public void placingWhitesFlipsPiecesDiagonally1() {
        board.setBoard(Utilities.createPlayerTable(diagonalTestBoard2), Player.WHITE);

        assertEquals(1, board.place(0, 3, Player.WHITE, true));
        assertEquals(1, board.place(3, 3, Player.WHITE, true));
        assertEquals(6, board.whitePieces());
        assertEquals(0, board.blackPieces());
    } 
    
    @Test
    public void placingWhitesFlipsPiecesDiagonally2() {
        board.setBoard(Utilities.createPlayerTable(diagonalTestBoard1), Player.WHITE);

        assertEquals(1, board.place(0, 0, Player.WHITE, true));
        assertEquals(1, board.place(3, 0, Player.WHITE, true));
        assertEquals(6, board.whitePieces());
        assertEquals(0, board.blackPieces());
    } 
    
    @Test
    public void undoingFlipsWorks() {
        board.setBoard(Utilities.createPlayerTable(diagonalTestBoard1), Player.WHITE);
        
        board.place(0, 0, Player.WHITE, true);
        board.undo();
        assertArrayEquals(Utilities.createPlayerTable(diagonalTestBoard1), board.getBoard());
    }
}
