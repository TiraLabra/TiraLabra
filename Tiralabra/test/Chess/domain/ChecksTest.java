
package Chess.domain;

import chess.domain.Checks;
import chess.game.Chessboard;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class ChecksTest {
    private Checks checks;
    private int [] whites;
    private int [] blacks;
    private int [][] board; 
    
    public ChecksTest() {
        this.checks = new Checks();
        this.board = new int [8][8];
                        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.board = new int[][] {{4,0,8,10,12,0,6,4},
                                  {2,0,2,2,2,2,2,2},
                                  {0,0,6,0,0,0,0,0},
                                  {0,2,0,0,3,0,0,0},
                                  {1,0,0,0,0,5,0,8},
                                  {0,0,0,0,0,9,0,0},
                                  {0,1,1,1,1,1,1,1},
                                  {0,5,7,0,11,7,0,3}};  ;
        this.whites = new int[16];
        this.blacks = new int[16]; 
        int k = 0;
        int l = 0;
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board.length; j++) {
                if (this.board[i][j] != 0) {
                    if (this.board[i][j] % 2 == 0) {
                        this.blacks[k] = j * 10 + i;
                        k++;                        
                    } else {
                        this.whites[l] = j * 10 + i;
                        l++;
                    }
                }
                        
            }
        }        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void emptyWhiteTestTrue() {
        assertEquals(checks.empty(whites, 3, 2), true);
        assertEquals(checks.empty(whites, 5, 1), true);  
        assertEquals(checks.empty(whites, 0, 3), true);            
    }
    
    @Test
    public void emptyWhiteTestFalse() {
        assertEquals(checks.empty(whites, 7, 7), false);    
        assertEquals(checks.empty(whites, 5, 6), false);          
    }    
    
    @Test
    public void emptyBlackTestTrue() {
        assertEquals(checks.empty(blacks, 7, 7), true);
        assertEquals(checks.empty(blacks, 3, 2), true);  
        assertEquals(checks.empty(blacks, 0, 3), true);    
        assertEquals(checks.empty(blacks, 5, 6), true);          
    } 
    
    @Test
    public void emptyBlackTestFalse() {
        assertEquals(checks.empty(blacks, 5, 1), false);  
        assertEquals(checks.empty(blacks, 0, 0), false);           
    }    
    
    @Test
    public void onBoardTestXover() {    
        assertEquals(checks.onBoard(11, 7), false);          
    }
    
    @Test
    public void onBoardTestYover() {    
        assertEquals(checks.onBoard(5, 9), false);     
    } 
    
    @Test
    public void onBoardTestXunder() {      
        assertEquals(checks.onBoard(-1, 3), false);             
    }
    
    @Test
    public void onBoardTestYunder() {
        assertEquals(checks.onBoard(3, -9), false);         
    } 
    
    @Test
    public void onBoardTestBothOver() {
        assertEquals(checks.onBoard(124, 666), false);         
    }
    
    @Test
    public void onBoardTestBothUnder() {
        assertEquals(checks.onBoard(-24, -6), false);         
    } 
    
    @Test
    public void onBoardTestTrue() {
        assertEquals(checks.onBoard(0, 7), true);         
    }      

    @Test
    public void rookCheckVerticalTrue() {
        assertEquals(checks.rookCheckVertical(this.board, 0, 5, 0, 7), true);       
    }
    
    @Test
    public void rookCheckVerticalFalse() {
        assertEquals(checks.rookCheckVertical(this.board, 1, 1, 1, 5), false);       
    }
    
    @Test
    public void rookCheckVerticalYTrue() {
        assertEquals(checks.rookCheckVertical(this.board, 6, 5, 6, 3), true);       
    }
    
    @Test
    public void rookCheckVerticalYFalse() {
        assertEquals(checks.rookCheckVertical(this.board, 4, 4, 4, 2), false);       
    }    
    
    @Test
    public void rookCheckHorizontalTrue() {
        assertEquals(checks.rookCheckHorizontal(this.board, 4, 5, 4, 1), true);       
    } 
    
    @Test
    public void rookCheckHorizontalFalse() {
        assertEquals(checks.rookCheckHorizontal(this.board, 6, 4, 3, 4), false);       
    } 
    
    @Test
    public void rookCheckHorizontalXTrue() {
        assertEquals(checks.rookCheckHorizontal(this.board, 0, 0, 1, 0), true);       
    } 
    
    @Test
    public void rookCheckHorizontalXFalse() {
        assertEquals(checks.rookCheckHorizontal(this.board, 3, 5, 6, 5), false);       
    }     
   
    @Test
    public void bishopCheckNWOut() {
        assertEquals(checks.bishopCheckNW(this.board, 2, 3, 0, 2), false);       
    }
    
    @Test
    public void bishopCheckNEOut() {
        assertEquals(checks.bishopCheckNE(this.board, 3, 3, 6, 2), false);       
    } 
    
    @Test
    public void bishopCheckSWOut() {
        assertEquals(checks.bishopCheckSW(this.board, 4, 5, 0, 6), false);       
    }   

    @Test
    public void bishopCheckSEOut() {    
        assertEquals(checks.bishopCheckSE(this.board, 5, 2, 7, 3), false);       
    }     
    
    @Test
    public void bishopCheckNWTrue() {
        assertEquals(checks.bishopCheckNW(this.board, 4, 5, 2, 3), true);       
    }
    
    @Test
    public void bishopCheckNETrue() {
        assertEquals(checks.bishopCheckNE(this.board, 1, 5, 4, 2), true);       
    } 
    
    @Test
    public void bishopCheckSWTrue() {
        assertEquals(checks.bishopCheckSW(this.board, 3, 2, 0, 5), true);       
    }   

    @Test
    public void bishopCheckSETrue() {    
        assertEquals(checks.bishopCheckSE(this.board, 0, 1, 4, 5), true);       
    }
    
    @Test
    public void bishopCheckNWFalse() {
        assertEquals(checks.bishopCheckNW(this.board, 3, 3, 1, 1), false);       
    }
    
    @Test
    public void bishopCheckNEFalse() {
        assertEquals(checks.bishopCheckNE(this.board, 2, 5, 5, 2), false);       
    } 
    
    @Test
    public void bishopCheckSWFalse() {
        assertEquals(checks.bishopCheckSW(this.board, 5, 0, 2, 3), false);       
    }   

    @Test
    public void bishopCheckSEFalse() {    
        assertEquals(checks.bishopCheckSE(this.board, 0, 1, 3, 2), false);       
    }    
    
     
            
}
