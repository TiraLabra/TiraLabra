
package Chess.domain;

import chess.domain.Moves;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class MovesTest {
    private Moves move;
    private int [] whites;
    private int [] blacks;
    private int [][] board;     
    
    public MovesTest() {
        move = new Moves();
        this.board = new int[][] {{4,6,8,10,12,8,6,4},
                                  {2,2,2,2,2,2,2,2},
                                  {0,0,0,0,0,0,0,0},
                                  {0,0,0,0,0,0,0,0},
                                  {0,0,0,0,0,0,0,0},
                                  {0,0,0,0,0,0,0,0},
                                  {1,1,1,1,1,1,1,1},
                                  {3,5,7,9,11,7,5,3}};   
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
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
      
    @Test 
    public void moveKingOnEmptyE() {    
        assertEquals(move.moveKing(blacks, 2, 3, 3, 3), true);
    }

    @Test 
    public void moveKingOnEmptySE() {    
        assertEquals(move.moveKing(blacks, 2, 3, 3, 4), true);
    } 
    
    @Test 
    public void moveKingOnEmptyS() {    
        assertEquals(move.moveKing(blacks, 2, 3, 2, 4), true);
    } 
    
    @Test 
    public void moveKingOnEmptySW() {    
        assertEquals(move.moveKing(blacks, 2, 3, 1, 4), true);
    }

    @Test 
    public void moveKingOnEmptyW() {    
        assertEquals(move.moveKing(blacks, 2, 3, 1, 3), true);
    } 
    
    @Test 
    public void moveKingOnEmptyNW() {    
        assertEquals(move.moveKing(blacks, 2, 3, 1, 2), true);
    } 
    
    @Test 
    public void moveKingOnEmptyN() {    
        assertEquals(move.moveKing(blacks, 2, 3, 2, 2), true);
    } 
    
    @Test 
    public void moveKingOnEmptyNE() {    
        assertEquals(move.moveKing(blacks, 2, 3, 3, 2), true);
    }     

    @Test 
    public void moveBlackKingOnWhitePieceE() {    
        assertEquals(move.moveKing(blacks, 5, 6, 6, 6), true);
    }
    
    @Test 
    public void moveBlackKingOnBlackPieceNW() {
        assertEquals(move.moveKing(blacks, 2, 2, 1, 1), false);         
    }
    
    @Test 
    public void moveBlackKingOnBlackPieceN() {
        assertEquals(move.moveKing(blacks, 2, 2, 2, 1), false);         
    }
    
    @Test 
    public void moveBlackKingOnBlackPieceNE() {
        assertEquals(move.moveKing(blacks, 2, 2, 3, 1), false);         
    }       

    @Test 
    public void moveWhitesKingOnWhitePieceE() {    
        assertEquals(move.moveKing(whites, 5, 6, 6, 6), false);
    }
    
    @Test 
    public void moveWhitesKingOnWhitePieceSE() {    
        assertEquals(move.moveKing(whites, 5, 6, 6, 7), false);
    } 
    
    @Test 
    public void moveWhitesKingOnWhitePieceS() {    
        assertEquals(move.moveKing(whites, 5, 6, 5, 7), false);
    }
    
    @Test 
    public void moveWhitesKingOnWhitePieceW() {    
        assertEquals(move.moveKing(whites, 5, 6, 4, 6), false);
    }    
    
    @Test 
    public void moveWhitesKingOnWhitePieceSW() {    
        assertEquals(move.moveKing(whites, 5, 6, 4, 7), false);
    }    
    
    @Test
    public void moveWhiteKingonBlackPiece() {
        assertEquals(move.moveKing(whites, 2, 2, 2, 1), true);
    }
    
    @Test
    public void moveKingOffBoard() {
        assertEquals(move.moveKing(blacks, 4, 0, 4, -1), false);        
    }
    
    @Test
    public void moveKingWrong() {
        assertEquals(move.moveKing(blacks, 4, 4, 2, 5), false);        
    }       
 
    @Test
    public void moveBlackPawnOnBlack() {
        assertEquals(move.moveBlackPawn(blacks, whites, 5, 0, 5, 1), false);       
    } 
    
    @Test
    public void moveBlackPawnOnWhiteFront() {
        assertEquals(move.moveBlackPawn(blacks, whites, 5, 5, 5, 6), false);       
    } 
    
    @Test
    public void moveBlackPawnOnWhiteRightSideAttack() {
        assertEquals(move.moveBlackPawn(blacks, whites, 3, 5, 4, 6), true);       
    }
    
    @Test
    public void moveBlackPawnOnWhiteLeftSideAttack() {
        assertEquals(move.moveBlackPawn(blacks, whites, 3, 5, 2, 6), true);       
    }     
    
    @Test
    public void moveBlackPawnTwoStepsStart() {
        assertEquals(move.moveBlackPawn(blacks, whites, 1, 1, 1, 3), true);       
    } 
    
    @Test
    public void moveBlackPawnTwoStepsNotStart() {
        assertEquals(move.moveBlackPawn(blacks, whites, 3, 3, 3, 5), false);       
    }
    
    @Test
    public void moveBlackPawnTwoStepsOneStepFront() {
        assertEquals(move.moveBlackPawn(blacks, whites, 3, 3, 3, 4), true);       
    } 
    
    @Test
    public void moveBlackPawnTwoStepsOneStepSideEmpty() {
        assertEquals(move.moveBlackPawn(blacks, whites, 3, 3, 4, 4), false);       
    } 
    
    @Test
    public void moveBlackPawnOutOfBoard() {
        assertEquals(move.moveBlackPawn(blacks, whites, 6, 7, 6, 8), false);       
    }  
    
    @Test
    public void moveBlackPawnBack() {
        assertEquals(move.moveBlackPawn(blacks, whites, 2, 4, 2, 3), false);       
    } 
    
    @Test
    public void moveBlackPawnLeft() {
        assertEquals(move.moveBlackPawn(blacks, whites, 2, 4, 1, 4), false);       
    } 
    
    @Test
    public void moveBlackPawnRight() {
        assertEquals(move.moveBlackPawn(blacks, whites, 2, 4, 3, 4), false);       
    }     
   
    @Test
    public void knightMoveUpLeftTwoOne() {
        assertEquals(move.moveKnight(blacks, 5, 4, 4, 2), true);        
    }
    
    @Test
    public void knightMoveUpLeftOneTwo() {
        assertEquals(move.moveKnight(blacks, 5, 4, 3, 3), true);          
    } 
    
    @Test
    public void knightMoveUpRighttTwoOne() {
        assertEquals(move.moveKnight(blacks, 5, 4, 7, 3), true);              
    }
    
    @Test
    public void knightMoveUpRightOneTwo() {
        assertEquals(move.moveKnight(blacks, 5, 4, 6, 2), true);             
    } 
    
    @Test
    public void knightMoveDownLeftTwoOne() {
        assertEquals(move.moveKnight(blacks, 4, 3, 2, 4), true);         
    }
    
    @Test
    public void knightMoveDownLeftOneTwo() {
        assertEquals(move.moveKnight(blacks, 4, 3, 3, 5), true);          
    } 
    
    @Test
    public void knighMovetDownRighttTwoOne() {
        assertEquals(move.moveKnight(blacks, 4, 3, 6, 4), true);          
    }
    
    @Test
    public void knighMovetDownRightOneTwo() {
        assertEquals(move.moveKnight(blacks, 4, 3, 5, 5), true);          
    }    
    
    @Test
    public void moveKnightTotallyWrong() {   
        assertEquals(move.moveKnight(blacks, 0, 0, 9, 9), false);       
    } 
    
    @Test
    public void moveKnightOffBoard() {
        assertEquals(move.moveKnight(blacks, 6, 2, 8, 1), false);        
    }
    
    @Test
    public void moveBlackKnightBlackNE() {
        assertEquals(move.moveKnight(blacks, 5, 2, 7, 1), false);        
    } 
    
    @Test
    public void moveBlackKnightBlackNW() {
        assertEquals(move.moveKnight(blacks, 5, 2, 3, 1), false);        
    } 
    
    @Test
    public void moveBlackKnightBlackNE2() {
        assertEquals(move.moveKnight(blacks, 5, 2, 6, 0), false);        
    } 
    
    @Test
    public void moveBlackKnightBlackNW2() {
        assertEquals(move.moveKnight(blacks, 5, 2, 4, 0), false);        
    }  
    
    @Test
    public void moveWhiteKnightWhiteSW2() {
        assertEquals(move.moveKnight(whites, 3, 5, 1, 6), false);        
    } 
    
    @Test
    public void moveWhiteKnightWhiteSW() {
        assertEquals(move.moveKnight(whites, 3, 5, 2, 7), false);        
    } 
    
    @Test
    public void moveWhiteKnightWhiteSE2() {
        assertEquals(move.moveKnight(whites, 3, 5, 4, 7), false);        
    } 
    
    @Test
    public void moveWhiteKnightWhiteSE() {
        assertEquals(move.moveKnight(whites, 3, 5, 5, 6), false);        
    }     
    
    @Test
    public void moveWhiteKnightBlack() {
        assertEquals(move.moveKnight(whites, 5, 2, 7, 1), true);        
    } 
    
    @Test
    public void moveRookOffBoardHorizontally() {
        assertEquals(move.moveRook(board, blacks, 3, 2, -3, 2), false);        
    }
    
    @Test
    public void moveRookOffBoardVertically() {
        assertEquals(move.moveRook(board, blacks, 3, 0, 3, -2), false);        
    }    
    
    @Test
    public void moveRookTotallyWrong() {
        assertEquals(move.moveRook(board, blacks, 3, 2, 8, -2), false);        
    }
    
    @Test
    public void moveBlackRookWhite() {
        assertEquals(move.moveRook(board, blacks, 3, 2, 3, 6), true);        
    } 
    
    @Test
    public void moveBlackRookBlack() {
        assertEquals(move.moveRook(board, blacks, 3, 2, 3, 1), false);        
    } 
    
    @Test
    public void moveWhiteRookBlack() {
        assertEquals(move.moveRook(board, whites, 3, 2, 3, 1), true);        
    }
    
    @Test
    public void moveRookWrong() {
        assertEquals(move.moveRook(board, blacks, 3, 2, 5, 3), false);        
    } 
    
    @Test
    public void moveRookOverOwnPiece() {
        assertEquals(move.moveRook(board, blacks, 3, 2, 3, 0), false);        
    }
    
    @Test
    public void moveRookOverOwnEnemy() {
        assertEquals(move.moveRook(board, blacks, 3, 2, 3, 7), false);        
    } 
    
    @Test
    public void moveRookVerticalEmpty() {
        assertEquals(move.moveRook(board, blacks, 3, 2, 5, 2), true);        
    }  
    
    @Test
    public void moveRookHorizontalEmpty() {
        assertEquals(move.moveRook(board, blacks, 3, 5, 3, 2), true);        
    }     
 
    @Test
    public void moveBishopOffBoard() {
        assertEquals(move.moveBishop(board, blacks, 7, 5, 8, 6), false);        
    }
    
    @Test
    public void whiteBishopOnBlack() {
        assertEquals(move.moveBishop(board, whites, 3, 3, 1, 1), true);        
    }
    
    @Test
    public void blackBishopOnBlack() {
        assertEquals(move.moveBishop(board, blacks, 3, 3, 1, 1), false);        
    }  
    
    @Test
    public void blackBishopOnWhite() {
        assertEquals(move.moveBishop(board, blacks, 3, 3, 6, 6), true);        
    } 
    
    @Test
    public void moveBishopTotallyWrong() {
        assertEquals(move.moveBishop(board, blacks, 3, 3, -8, 15), false);
    }
    
    @Test
    public void moveBlackBishopTest() {
        assertEquals(move.moveBishop(board, blacks, 0, 0, 1, 1), false);
        assertEquals(move.moveBishop(board, blacks, 3, 3, 5, 5), true);
        assertEquals(move.moveBishop(board, blacks, 3, 3, 7, 7), false);
        assertEquals(move.moveBishop(board, blacks, 3, 3, 2, 2), true);   
        assertEquals(move.moveBishop(board, blacks, 3, 3, 0, 6), true);         
    }
    
    @Test
    public void moveBlackQueenTest() {
        assertEquals(move.moveQueen(board, blacks, 2, 0, 6, 0), false);
        assertEquals(move.moveQueen(board, blacks, 2, 0, 2, 6), false);
        assertEquals(move.moveQueen(board, blacks, 2, 0, 1, 1), false);
        assertEquals(move.moveQueen(board, blacks, 3, 3, 3, 6), true); 
        assertEquals(move.moveQueen(board, blacks, 3, 3, 6, 6), true);  
        assertEquals(move.moveQueen(board, blacks, 4, 4, 2, 2), true);
        assertEquals(move.moveQueen(board, blacks, 4, 4, 2, 6), true);  
        assertEquals(move.moveQueen(board, blacks, 4, 4, 6, 2), true);         
        assertEquals(move.moveQueen(board, blacks, 4, 4, 0, 0), false);         
    }      
    
    @Test
    public void moveWhiteBishopTest() {
        assertEquals(move.moveBishop(board, whites, 7, 7, 0, 0), false);
        assertEquals(move.moveBishop(board, whites, 4, 5, 6, 5), false);
        assertEquals(move.moveBishop(board, whites, 2, 2, 5, 7), false);
        assertEquals(move.moveBishop(board, whites, 3, 4, 4, 5), true);   
        assertEquals(move.moveBishop(board, whites, 3, 4, 6, 1), true); 
        assertEquals(move.moveBishop(board, whites, 3, 4, 0, 1), true);         
    }
    
    @Test
    public void moveWhiteQueenTest() {
        assertEquals(move.moveQueen(board, whites, 0, 0, 7, 7), false);
        assertEquals(move.moveQueen(board, whites, 0, 0, 7, 0), false);
        assertEquals(move.moveQueen(board, whites, 0, 0, 0, 7), false);
        assertEquals(move.moveQueen(board, whites, 3, 2, 5, 6), false); 
        assertEquals(move.moveQueen(board, whites, 3, 2, 5, 2), true); 
        assertEquals(move.moveQueen(board, whites, 3, 2, 5, 4), true);  
        assertEquals(move.moveQueen(board, whites, 3, 2, 2, 1), true); 
        assertEquals(move.moveQueen(board, whites, 3, 2, 3, 1), true);  
        assertEquals(move.moveQueen(board, whites, 3, 2, 3, 5), true); 
        
    }
     
    
    @Test
    public void moveWhitePawnOnWhite() {
        assertEquals(move.moveWhitePawn(whites, blacks, 5, 7, 5, 6), false);       
    } 
    
    @Test
    public void moveWhitePawnOnBlackFront() {
        assertEquals(move.moveWhitePawn(whites, blacks, 5, 2, 5, 1), false);       
    } 
    
    @Test
    public void moveWhitePawnOnBlackRightSideAttack() {
        assertEquals(move.moveWhitePawn(whites, blacks, 3, 2, 4, 1), true);       
    }
    
    @Test
    public void moveWhitePawnOnBlackLeftSideAttack() {
        assertEquals(move.moveWhitePawn(whites, blacks, 3, 2, 2, 1), true);       
    }     
    
    @Test
    public void moveWhitePawnTwoStepsStart() {
        assertEquals(move.moveWhitePawn(whites, blacks, 1, 6, 1, 4), true);       
    } 
    
    @Test
    public void moveWhitePawnTwoStepsNotStart() {
        assertEquals(move.moveWhitePawn(whites, blacks, 3, 4, 3, 2), false);       
    }
    
    @Test
    public void moveWhitePawnTwoStepsOneStepFront() {
        assertEquals(move.moveWhitePawn(whites, blacks, 3, 4, 3, 3), true);       
    } 
    
    @Test
    public void movWhitePawnSideEmpty() {
        assertEquals(move.moveWhitePawn(whites, blacks, 4, 4, 3, 3), false);       
    } 
    
    @Test
    public void moveWhitePawnOutOfBoard() {
        assertEquals(move.moveWhitePawn(whites, blacks, 6, 0, 6, -1), false);       
    }  
    
    @Test
    public void moveWhitePawnBack() {
        assertEquals(move.moveWhitePawn(whites, blacks, 2, 3, 2, 4), false);       
    } 
    
    @Test
    public void moveWhiteLeft() {
        assertEquals(move.moveWhitePawn(whites, blacks, 2, 3, 1, 3), false);       
    } 
    
    @Test
    public void moveWhiteRight() {
        assertEquals(move.moveWhitePawn(whites, blacks, 2, 3, 3, 3), false);       
    }     
       
}
