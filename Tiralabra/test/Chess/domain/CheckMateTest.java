
package Chess.domain;

import chess.domain.CheckMate;
import chess.domain.Moves;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class CheckMateTest {
    private int [][] board; 
    private CheckMate cm;
      
    
    public CheckMateTest() {
        this.board = new int[][] {{0,0,8,10,0,2,6,4},
                                  {2,2,2,2,0,2,0,2},
                                  {3,0,0,0,0,8,0,2},
                                  {9,0,0,12,0,0,0,3},
                                  {4,0,0,11,1,0,0,0},
                                  {0,5,0,0,0,0,0,5},
                                  {1,1,1,1,6,1,1,1},
                                  {0,0,7,0,0,7,0,0}};  
              
        this.cm = new CheckMate();         
    }
    
    @BeforeClass
    public static void setUpClass() {        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        cm.addPieces(board);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void canAttacBlackKing() {
        
        assertEquals(cm.canAttackKing(board, 9, 0, 3), true);   
        assertEquals(cm.canAttackKing(board, 3, 7, 3), true);  
        assertEquals(cm.canAttackKing(board, 1, 2, 6), false); 
        assertEquals(cm.canAttackKing(board, 5, 1, 5), false);                       
       
        
    }
    
    @Test
    public void canAttacWhiteKing() {
        
        assertEquals(cm.canAttackKing(board, 4, 0, 4), true);  
        assertEquals(cm.canAttackKing(board, 8, 2, 5), true); 
        assertEquals(cm.canAttackKing(board, 6, 4, 6), true);
        assertEquals(cm.canAttackKing(board, -5, 4, 6), false);          
    }   
    
    @Test
    public void boardOK() {
        assertEquals(board[3][3], 12);
        assertEquals(board[4][3], 11);    
        assertEquals(board[6][4], 6);         
    }
    
    @Test
    public void KingLocation() {
        cm.findKing(board, 3);
        assertEquals(cm.getX(), 3);
        assertEquals(cm.getY(), 3);
        cm.findKing(board, 10); 
        assertEquals(cm.getX(), 3);
        assertEquals(cm.getY(), 4);        
    }
    
    @Test
    public void PiecesLocations() {
        assertEquals(cm.getBlack(0), 20);
        assertEquals(cm.getBlack(15), 46);   
        assertEquals(cm.getBlack(8), 31);
        assertEquals(cm.getBlack(11), 52);    
        assertEquals(cm.getWhite(6), 75);
        assertEquals(cm.getWhite(12), 66);   
        assertEquals(cm.getWhite(4), 44);
        assertEquals(cm.getWhite(1), 3);          
    }
    
    @Test
    public void canEliminateAttackerFalse() {
        assertEquals(cm.canTakeDownAttacker(1, 6), false);
    }

    @Test
    public void canEliminateAttackerTrue() {
        assertEquals(cm.canTakeDownAttacker(4, 6), true);
    }
    
    @Test
    public void canTakeDownAttackerQueen() {
        assertEquals(cm.canTakeDownAttacker(0, 3), true);
    } 
    
    @Test
    public void canBlockAttackerQueen() {
        assertEquals(cm.canTakeDownAttacker(3, 0), false);
    }     

    @Test
    public void canEliminateAttackerEmpty() {
        assertEquals(cm.canTakeDownAttacker(1, 2), true);        
    }
    
    @Test
    public void blockAttackerRookTrue() {
        assertEquals(cm.canTakeDownAttacker(0, 4), true);         
    }
    
    @Test
    public void blockAttackerRookFalse() {
        assertEquals(cm.canTakeDownAttacker(7, 3), false);         
    }
    
    @Test
    public void blockAttackerBishopTrue() {
        assertEquals(cm.canTakeDownAttacker(5, 2), true);         
    }
    
    @Test
    public void blockAttackerBishopFalse() {
        assertEquals(cm.canTakeDownAttacker(2, 0), false);         
    }    
    
    @Test
    public void canKingMoveFalse() {
        cm.findKing(board, 9);
        cm.addSquares();
        assertEquals(cm.canKingMove(), false);
    }

    @Test
    public void canKingMoveTrue() {
        cm.findKing(board, 8);
        cm.addSquares();
        assertEquals(cm.canKingMove(), true);
    } 
    
    
    
}
