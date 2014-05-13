
package Chess.game;

import chess.game.Menu;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class MenuTest {
    private Menu menu;  
    private int [][] cb;      
    
    public MenuTest() {
        menu = new Menu();        

    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.cb = new int[][] {{4,0,8,10,12,0,6,4},
                               {2,0,2,2,2,2,2,2},
                               {0,0,6,0,0,0,0,0},
                               {0,2,0,0,3,0,0,0},
                               {1,0,0,0,0,5,0,8},
                               {0,0,0,0,0,9,0,0},
                               {0,1,1,1,1,1,1,1},
                               {0,5,7,0,11,7,0,3}};           
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void LoadTurns() throws IOException {
        menu.save(cb, 4, 1); 
        int turns = menu.loadTurns();
        assertEquals(turns, 4);         
      
    } 
    
    @Test    
    public void LoadInfo() throws IOException {
        menu.save(cb, 4, 3); 
        int info = menu.loadInfo();
        assertEquals(info, 3);         
      
    } 
    
    @Test   
    public void LoadBoard() throws IOException {
        menu.save(cb, 4, 3); 
        int[][] board = menu.loadBoard();
        int piece = board[5][5];
        assertEquals(piece, 9);         
      
    } 
    
    @Test   
    public void LoadBoardEmpty() throws IOException {
        menu.save(cb, 4, 3); 
        int[][] board = menu.loadBoard();
        int piece = board[1][1];
        assertEquals(piece, 0);         
      
    }     

}