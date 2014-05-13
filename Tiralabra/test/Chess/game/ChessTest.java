
package Chess.game;

import chess.game.Chess;
import chess.game.Chessboard;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class ChessTest {
    private Chess chess;
    private Chessboard board;  
    
    public ChessTest() {
        this.chess = new Chess();
        this.board = new Chessboard();

    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        board.newBoard();
        board.addPieces();      
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void BlackTurns() {
        board.blackTurn(6, 6, 6, 4);
        assertEquals(board.getMoveB(), false);
        board.blackTurn(1, 0, 0, 2);
        assertEquals(board.getMoveB(), true);         
    }
    
    @Test
    public void WhiteTurns() {
        board.whiteTurn(6, 6, 6, 4);
        assertEquals(board.getMoveW(), true);     
        board.whiteTurn(1, 0, 0, 2);
        assertEquals(board.getMoveW(), false);  
    }
       
    @Test
    public void setInfoKingDead() {
        chess.setInfo(4);
        assertEquals(chess.getInfo(), 4);
    }
    
    @Test
    public void copyBoad() {
        int[][] cb = board.getBoard();
        assertEquals(cb[7][6], 5);       
    }
    
    @Test
    public void newGameTurns() {
        chess.newGame();
        int turns = chess.getTurn();
        assertEquals(turns, 1);          
    }
    
    @Test
    public void newGameInfo() {
        chess.newGame();
        int info = chess.getInfo();
        assertEquals(info, 0);          
    }  
    
    @Test
    public void newGameBoard() {
        chess.newGame();
        chess.copyBoard();
        assertEquals(chess.getBoard()[1][3], 2);          
    } 
    
    @Test
    public void newGamePieces() {
        chess.newGame();
        assertEquals(board.getBlack(5), 50);   
        assertEquals(board.getWhite(9), 17);         
    } 
       
       
}
