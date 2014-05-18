
package Chess.game;

import chess.game.Chessboard;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class ChessboardTest {
    private Chessboard board;    
   
    
    public ChessboardTest() {
        board = new Chessboard();        
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
    public void newBoardTest() {
        assertEquals(board.getPiece(0, 0), 4);
        assertEquals(board.getPiece(7, 6), 1);
        assertEquals(board.getPiece(5, 5), 0); 
        assertEquals(board.getPiece(6, 7), 5);
        assertEquals(board.getPiece(4, 0), 12);                
    }
    
    @Test
    public void addPiecesWhite() {
        assertEquals(board.getWhite(12), 47);
    }
    
    @Test
    public void addPiecesBlack() {
        assertEquals(board.getBlack(8), 1);
    }

    
    @Test
    public void editBlacksTest() {
        board.editBlacks(5, 5, 5);
        assertEquals(board.getBlack(5), 55);
        board.editBlacks(6, 2, 1);
        assertEquals(board.getBlack(1), 62);   
        board.editBlacks(0, 0, 0);
        assertEquals(board.getBlack(0), 0);   
        assertEquals(board.getBlack(8), 1);         
    }
    
    @Test
    public void editWhitesTest() {
        board.editWhites(1, 1, 1);
        assertEquals(board.getWhite(1), 11);
        board.editWhites(0, 7, 3);
        assertEquals(board.getWhite(3), 7);  
        board.editWhites(3, 0, 2);
        assertEquals(board.getWhite(2), 30);  
        assertEquals(board.getWhite(5), 56);        
        
    }
        
    
    @Test
    public void eliminateWhiteTest() {
        board.eliminateWhite(0, 6);
        assertEquals(board.getWhite(0), 99);
        assertEquals(board.getWhite(8), 7);
        
    }
    
   @Test
    public void eliminateBlackTest() {
        board.eliminateBlack(0, 0);
        assertEquals(board.getBlack(0), 99);
        board.eliminateBlack(1, 7);
        assertEquals(board.getBlack(15), 71); 
        
   } 
   
   @Test
   public void movePieceTest() {
       board.movePiece(0, 0, 5, 5, 3);
       assertEquals(board.getPiece(5, 5), 3);
       board.movePiece(0, 0, 1, 1, 12);
       assertEquals(board.getPiece(1, 1), 12);       
   }
   
   @Test
   public void moveWhiteTest() {
       board.moveWhite(0, 6, 0, 4, 1);
       assertEquals(board.getPiece(0, 4), 1); 
       assertEquals(board.getPiece(0, 6), 0); 
       board.moveWhite(8, 8, 5, 5, 5);  
       assertEquals(board.getPiece(5, 5), 0); 
       
   }
   
   @Test
   public void moveBlackTest() {
       board.moveBlack(5, 1, 5, 3, 2);
       assertEquals(board.getPiece(5, 1), 0); 
       assertEquals(board.getPiece(5, 3), 2);
       board.moveBlack(1, 0, 2, 2, 6);  
       assertEquals(board.getPiece(1, 0), 0); 
       assertEquals(board.getPiece(2, 2), 6);
       board.moveBlack(-1, 0, 4, 4, 6);  
       assertEquals(board.getPiece(4, 4), 0);       
       
   }  
   
   @Test
   public void BlackTurnTest() {
       board.blackTurn(6, 0, 5, 2);
       assertEquals(board.getPiece(6, 0), 0);       
       assertEquals(board.getPiece(5, 2), 6);
       assertEquals(board.getBlack(6), 52);
       board.blackTurn(3, 1, 3, 2);
       assertEquals(board.getPiece(3, 1), 0);       
       assertEquals(board.getPiece(3, 2), 2);
       assertEquals(board.getBlack(11), 32);       
   }
   
   @Test
   public void WhiteTurnTest() {
       board.whiteTurn(0, 6, 0, 4);
       assertEquals(board.getPiece(0, 6), 0);
       assertEquals(board.getPiece(0, 4), 1);       
       assertEquals(board.getWhite(0), 4); 
       board.whiteTurn(0, 7, 0, 5); 
       assertEquals(board.getPiece(0, 7), 0);
       assertEquals(board.getPiece(0, 5), 3);       
       assertEquals(board.getWhite(8), 5);        
   }
   
   @Test
   public void setBoard() {
        int[][]cb  = new int[][] {{0,0,8,10,12,0,0,4},
                                  {2,0,2,2,2,2,2,2},
                                  {0,0,6,0,0,0,6,0},
                                  {0,2,0,4,3,0,0,0},
                                  {1,0,0,1,0,5,0,8},
                                  {0,1,0,0,0,9,0,0},
                                  {0,0,1,0,1,1,1,1},
                                  {0,5,7,0,11,7,0,3}};  
        board.setBoard(cb);
        assertEquals(board.getPiece(5, 4), 5);           

   }
   
   @Test
   public void moveBTrue() {
       board.moveBlack(0, 1, 0, 3, 2);
       assertEquals(board.getMoveB(), true);  
   }
   
   @Test
   public void moveBFalse() {
       board.moveBlack(4, 5, 0, 3, 11);
       assertEquals(board.getMoveB(), false);  
   } 
   
   @Test
   public void moveWTrue() {
       board.moveWhite(6, 7, 5, 5, 5);
       assertEquals(board.getMoveW(), true);  
   }
   
   @Test
   public void moveWFalse() {
       board.moveWhite(3, 2, 7, 5, 4);
       assertEquals(board.getMoveW(), false);  
   }    
   
   
    
}
