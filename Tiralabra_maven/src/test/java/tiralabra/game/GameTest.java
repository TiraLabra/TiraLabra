/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tiralabra.game;

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
public class GameTest {
    
    private Game game1;
    private Game game2;
    private Game game3;
    private Board board1;
    private Board board2;
    private Board board3;
    
    @Before
    public void setUp() {
        game1 = new Game(true, true);
        game2 = new Game(false, true);
        game3 = new Game(true, false);
        board1 = new Board();
        board2 = new Board();
        board3 = new Board();
        game1.setBoard(board1);
        game2.setBoard(board2);
        game3.setBoard(board3);
    }
    
    @Test
    public void gameCorrectlyWaitsForUserInputWhenAHumanPlaysAgainstAnAI() {
        game2.nextTurn();
        assertTrue(game2.isWaiting());
        game3.nextTurn();
        game3.nextTurn();
        assertTrue(game3.isWaiting());
    } 
    
    @Test
    public void whileWaitingTheBlackAIWontMakeMoves() {
        int[][] testBoard = Utilities.copy2dArray(board2.getBoard());
        game2.nextTurn();
        game2.nextTurn();
        game2.nextTurn();
        assertArrayEquals(testBoard, game2.getBoard());
    }
    
    @Test
    public void whileWaitingTheWhiteAIWontMakeMoves() {
        game3.nextTurn();
        int[][] testBoard = Utilities.copy2dArray(board3.getBoard());
        game3.nextTurn();
        game3.nextTurn();
        assertArrayEquals(testBoard, game3.getBoard());
    }
    
    @Test
    public void theGameWillCorrectlyBeOverWhenTheBoardIsFull() {
        int[][] test = new int[8][8];
        for (int y = 0; y < test.length; y++) {
            for (int x = 0; x < test[0].length; x++) {
                test[y][x] = new Random().nextInt(2) + 1;
            }
        }
        Board testBoard = new Board();
        testBoard.setBoard(Utilities.copy2dArray(test));
        game1.setBoard(testBoard);
        
        
        game1.nextTurn();
        assertArrayEquals(test, game1.getBoard());
        assertTrue(game1.gameOver());
    }
}
