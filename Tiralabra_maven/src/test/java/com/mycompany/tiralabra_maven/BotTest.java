/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.player.AdvancedAi;
import com.mycompany.tiralabra_maven.player.Bot;
import junit.framework.TestCase;

/**
 *
 * @author Joel Nummelin
 */
public class BotTest extends TestCase{
    private AdvancedAi ai;
    private Bot bot;
    
    public BotTest() {
    }
    
    
    @Override
    public void setUp() {
        ai = new AdvancedAi();
        bot = new Bot(0);
    }
    

    public void testAiBehavior(){
        int x = ai.determineMove();
        
        for (int i = 0; i < 500; i++) {
            ai.update(1);
        }
        
        int y = 0;
        
        for (int i = 0; i < 10; i++) {
            if (ai.determineMove() == x){
                y++;
            }
        }
        
        assertTrue(y > 7);
    }
    

    public void testBot() {
        for (int i = 0; i < 1000; i++) {
            int x = bot.makeAMove();
            if (x == 0){
                bot.updateAi(1);
            } else {
                bot.updateAi(-1);
            }
        }
        int x = 0;
        for (int i = 0; i < 10; i++) {
            if (bot.makeAMove() == 0){
                x++;
            }
        }
        assertTrue(x > 6);
    }
}
