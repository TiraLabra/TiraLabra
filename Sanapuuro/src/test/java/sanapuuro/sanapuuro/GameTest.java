/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sanapuuro.sanapuuro.grid.Grid;
import sanapuuro.sanapuuro.grid.LetterContainer;
import sanapuuro.sanapuuro.gui.TimerWrapper;
import sanapuuro.sanapuuro.letters.Letter;

/**
 *
 * @author skaipio
 */
public class GameTest {
    private Game game;
    private TimerWrapper timer = new TimerWrapper();
    
    public GameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.game = new Game();
    }
    
    @After
    public void tearDown() {
    }

     @Test
     public void gridIsClearAfterNewGame() {
         this.game.newGame(this.timer);
         Grid grid = this.game.getGrid();
         grid.setContainerAt(new LetterContainer(new Letter('c', 0, 0)), 0, 0);
         grid.setContainerAt(new LetterContainer(new Letter('c', 0, 0)), 7, 7);
         this.game.newGame(this.timer);
         Grid newGrid = this.game.getGrid();
         assertNull(newGrid.getContainerAt(0, 0));
         assertNull(newGrid.getContainerAt(7, 7));
     }
     
     @Test
     public void timerStartsAtTwoMinutesAfterNewGame() {
         this.game.newGame(this.timer);
         assertEquals(2, this.timer.getMinutes());
     }
     
     @Test
     public void playerScoreStartsAtZeroOnNewGame() {
         this.game.newGame(this.timer);
         assertEquals(0, this.game.getPlayer().getScore());
     }
     
     @Test
     public void disablesPlayerOnTimeout() {
         this.game.newGame(this.timer);
         this.game.notifyTimeOut();
         assertEquals(false, this.game.getPlayer().isEnabled());
     }
}
