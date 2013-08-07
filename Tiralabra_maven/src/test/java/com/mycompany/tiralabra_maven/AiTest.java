/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.player.Ai;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Joel Nummelin
 */
public class AiTest extends TestCase{
    private Ai ai;
    
    public AiTest() {
    }
    
    
    @Before
    @Override
    public void setUp() {
        ai = new Ai();
    }
    
    @Test
    public void testBehavior(){
        int x = ai.determineMove();
        
        for (int i = 0; i < 100; i++) {
            ai.update(1);
        }
        
        int y = 0;
        
        for (int i = 0; i < 10; i++) {
            if (ai.determineMove() == x){
                y++;
            }
        }
        
        assertTrue(y > 6);
    }
    
}
