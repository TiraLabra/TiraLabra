/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
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
    public void puttingAWhitePiecePutsAPieceAtTheRightSpot() {
        assertTrue(board.put(5, 3, Board.WHITE));
        assertEquals(Board.WHITE, board.getBoard()[3][5]);
    }

    @Test
    public void puttingABlackPiecePutsAPieceAtTheRightSpot() {
        assertTrue(board.put(2, 3, Board.BLACK));
        assertEquals(Board.BLACK, board.getBoard()[3][2]);
    }

    @Test
    public void puttingAtInvalidPositionDoesNothing() {
        board.setBoard(verticalTestBoard.clone());
        assertFalse(board.put(0, 0, Board.WHITE));
        assertArrayEquals(verticalTestBoard, board.getBoard());
    }

    @Test
    public void verticalFlippingToWhiteWorksRight() {
        board.setBoard(verticalTestBoard.clone());
        board.put(3, 0, Board.WHITE);
        assertEquals(Board.WHITE, board.getBoard()[0][2]);
    }

    @Test
    public void verticalFlippingToWhiteWorksLeft() {
        board.setBoard(verticalTestBoard.clone());
        board.put(4, 0, Board.WHITE);
        assertEquals(Board.WHITE, board.getBoard()[0][5]);
    }

    @Test
    public void verticalFlippingToBlackWorksRight() {
        board.setBoard(verticalTestBoard.clone());
        board.put(0, 0, Board.BLACK);
        assertEquals(Board.BLACK, board.getBoard()[0][0]);
    }

    @Test
    public void verticalFlippingToBlackWorksLeft() {
        board.setBoard(verticalTestBoard.clone());
        board.put(7, 0, Board.BLACK);
        assertEquals(Board.BLACK, board.getBoard()[0][6]);
    }

    @Test
    public void horizontalFlippingToWhiteWorksUp() {
        board.setBoard(horizontalTestBoard.clone());
        board.put(0, 3, Board.WHITE);
        assertEquals(Board.WHITE, board.getBoard()[2][0]);
    }

    @Test
    public void horizontalFlippingToWhiteWorksDown() {
        board.setBoard(horizontalTestBoard.clone());
        board.put(0, 4, Board.WHITE);
        assertEquals(Board.WHITE, board.getBoard()[5][0]);
    }

    @Test
    public void horizontalFlippingToBlackWorksUp() {
        board.setBoard(horizontalTestBoard.clone());
        board.put(0, 7, Board.BLACK);
        assertEquals(Board.BLACK, board.getBoard()[6][0]);
    }

    @Test
    public void horizontalFlippingToBlackWorksDown() {
        board.setBoard(horizontalTestBoard.clone());
        board.put(0, 0, Board.BLACK);
        assertEquals(Board.BLACK, board.getBoard()[1][0]);
    }

    @Test
    public void diagonalFlippingToWhiteWorksDown() {
        board.setBoard(diagonalTestBoard1.clone());
        board.put(0, 3, Board.WHITE);
        board.put(3, 3, Board.WHITE);
        assertEquals(Board.WHITE, board.getBoard()[2][1]);
        assertEquals(Board.WHITE, board.getBoard()[2][2]);
    }

    @Test
    public void diagonalFlippingToWhiteWorksUp() {
        board.setBoard(diagonalTestBoard2.clone());
        board.put(0, 0, Board.WHITE);
        board.put(3, 0, Board.WHITE);
        assertEquals(Board.WHITE, board.getBoard()[1][1]);
        assertEquals(Board.WHITE, board.getBoard()[1][2]);
    }

    @Test
    public void diagonalFlippingToBlackWorksDown() {
        board.setBoard(diagonalTestBoard2.clone());
        board.put(0, 3, Board.BLACK);
        board.put(3, 3, Board.BLACK);
        assertEquals(Board.BLACK, board.getBoard()[2][1]);
        assertEquals(Board.BLACK, board.getBoard()[2][2]);
    }

    @Test
    public void diagonalFlippingToBlackWorksUp() {
        board.setBoard(diagonalTestBoard1.clone());
        board.put(0, 0, Board.BLACK);
        board.put(3, 0, Board.BLACK);
        assertEquals(Board.BLACK, board.getBoard()[1][1]);
        assertEquals(Board.BLACK, board.getBoard()[1][2]);
    }

    private ArrayList<Long> buildLegalMovesAssertionList(long... moves) {
        ArrayList<Long> legalMoves = new ArrayList<>();
        for (long l : moves) {
            legalMoves.add(l);
        }
        return legalMoves;
    }

    @Test
    public void legalMovesFoundCorrectlyWhiteVertical() {
        board.setBoard(verticalTestBoard.clone());
        ArrayList<Long> toCompare = board.findLegalMoves(Board.WHITE);

        ArrayList<Long> assertion
                = buildLegalMovesAssertionList(Board.point(3, 0), Board.point(4, 0));
        assertEquals(assertion, toCompare);
    }

    @Test
    public void legalMovesFoundCorrectlyBlackVertical() {
        board.setBoard(verticalTestBoard.clone());
        ArrayList<Long> toCompare = board.findLegalMoves(Board.BLACK);

        ArrayList<Long> assertion
                = buildLegalMovesAssertionList(Board.point(0, 0), Board.point(7, 0));
        assertEquals(assertion, toCompare);
    }

    @Test
    public void legalMovesFoundCorrectlyWhiteHorizontal() {
        board.setBoard(horizontalTestBoard.clone());
        ArrayList<Long> toCompare = board.findLegalMoves(Board.WHITE);

        ArrayList<Long> assertion
                = buildLegalMovesAssertionList(Board.point(0, 3), Board.point(0, 4));
        assertEquals(assertion, toCompare);
    }

    @Test
    public void legalMovesFoundCorrectlyBlackHorizontal() {
        board.setBoard(horizontalTestBoard.clone());
        ArrayList<Long> toCompare = board.findLegalMoves(Board.BLACK);

        ArrayList<Long> assertion
                = buildLegalMovesAssertionList(Board.point(0, 0), Board.point(0, 7));
        assertEquals(assertion, toCompare);
    }

    @Test
    public void legalMovesFoundCorrectlyWhiteDiagonalUp() {
        board.setBoard(diagonalTestBoard1.clone());
        ArrayList<Long> toCompare = board.findLegalMoves(Board.WHITE);

        assertTrue(toCompare.contains(Board.point(0, 3)));
        assertTrue(toCompare.contains(Board.point(3, 3)));
    }

    @Test
    public void legalMovesFoundCorrectlyWhiteDiagonalDown() {
        board.setBoard(diagonalTestBoard2.clone());
        ArrayList<Long> toCompare = board.findLegalMoves(Board.WHITE);

        assertTrue(toCompare.contains(Board.point(0, 0)));
        assertTrue(toCompare.contains(Board.point(3, 0)));
    }
    
    @Test
    public void legalMovesFoundCorrectlyBlackDiagonalUp() {
        board.setBoard(diagonalTestBoard2.clone());
        ArrayList<Long> toCompare = board.findLegalMoves(Board.BLACK);

        assertTrue(toCompare.contains(Board.point(0, 3)));
        assertTrue(toCompare.contains(Board.point(3, 3)));
    }

    @Test
    public void legalMovesFoundCorrectlyBlackDiagonalDown() {
        board.setBoard(diagonalTestBoard1.clone());
        ArrayList<Long> toCompare = board.findLegalMoves(Board.BLACK);

        assertTrue(toCompare.contains(Board.point(0, 0)));
        assertTrue(toCompare.contains(Board.point(3, 0)));
    }
    
    @Test
    public void noLegalMovesFoundOnAFullBoard() {
        int[][] test = new int[8][8];
        for (int y = 0; y < test.length; y++) {
            for (int x = 0; x < test[0].length; x++) {
                test[y][x] = new Random().nextInt(2) + 1;
            }
        }
        assertTrue(board.findLegalMoves(Board.WHITE).isEmpty());
        assertTrue(board.findLegalMoves(Board.BLACK).isEmpty());
    }
}
