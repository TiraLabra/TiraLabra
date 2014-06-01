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
public class PlayerTest {
    
    @Before
    public void setUp() {
        
    }
    
    
    @Test
    public void threePlayerEnumerablesWithTheseValuesExist() {
        assertEquals(Player.NONE, Player.player(0));
        assertEquals(Player.BLACK, Player.player(1));
        assertEquals(Player.WHITE, Player.player(2));
    }
    
    @Test
    public void opposingOfBlackIsWhite() {
        assertEquals(Player.WHITE, Player.opposing(Player.BLACK));
    }
    
    @Test
    public void opposingOfWhiteIsBlack() {
        assertEquals(Player.BLACK, Player.opposing(Player.WHITE));
    }
    
    @Test
    public void opposingOfNoneIsNone() {
        assertEquals(Player.NONE, Player.opposing(Player.NONE));
    }
}
