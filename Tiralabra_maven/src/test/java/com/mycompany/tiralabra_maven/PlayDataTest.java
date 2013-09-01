package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.player.PlayData;
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author Joel Nummelin
 */
public class PlayDataTest extends TestCase{
    private PlayData playData;
    
    public PlayDataTest() {
    }
        
    @Override
    public void setUp() {
        this.playData = new PlayData();
    }

    
    @Test
    public void testPlayData(){
        assertEquals(playData.counterAntiStrategy(), false);
        int[] is = {1, 1, 0};
        playData.setUsedData(is);
        for (int i = 0; i < 5; i++) {
            playData.update(2);
        }
        assertTrue(playData.counterAntiStrategy());
    }
}