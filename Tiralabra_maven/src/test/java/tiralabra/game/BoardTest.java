/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.game;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author atte
 */
public class BoardTest {

    Board board = new Board();
    int[][] verticalTestBoard;
    int[][] horizontalTestBoard;
    int[][] diagonalTestBoard;

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
        verticalTestBoard = new int[][]{{0, 1, 2, 0, 0}};
        horizontalTestBoard = new int[][]{{0}, {1}, {2}, {0}, {0},};
        diagonalTestBoard = new int[][]{{0, 0, 0, 0},
        {0, 1, 0, 0},
        {0, 0, 2, 0},
        {0, 0, 0, 0}};
    }

    @After
    public void tearDown() {
    }

    @Test
    public void puttingAWhitePiecePutsAPieceAtTheRightSpot() {
        board.put(5, 3, 1);
        assertEquals(1, board.getBoard()[3][5]);
    }

    @Test
    public void puttingABlackPiecePutsAPieceAtTheRightSpot() {
        board.put(2, 3, 2);
        assertEquals(2, board.getBoard()[3][2]);
    }

    @Test
    public void puttingAtInvalidPositionDoesNothing() {
        board.setBoard(verticalTestBoard.clone());
        board.put(4, 0, 1);
        assertArrayEquals(verticalTestBoard, board.getBoard());
    }

    @Test
    public void verticalFlippingToWhiteWorks() {
        board.setBoard(verticalTestBoard.clone());
        board.put(3, 0, 1);
        assertEquals(1, board.getBoard()[0][3]);
    }
    
    @Test
    public void verticalFlippingToBlackWorks() {
        board.setBoard(verticalTestBoard.clone());
        board.put(0, 0, 2);
        assertEquals(2, board.getBoard()[0][0]);
    }
}
