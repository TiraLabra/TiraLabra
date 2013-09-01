/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven;

import java.io.File;
import java.io.FileNotFoundException;
import junit.framework.TestCase;

/**
 *
 * @author jonu
 */
public class StatisticsTest extends TestCase{
    private Statistics statistics;
    private File file;
    
    public StatisticsTest() {
    }
    
    @Override
    public void setUp() throws FileNotFoundException {
        this.file = new File("profiles/forTests");
        this.statistics = new Statistics(file);
    }
    

    public void testStatistics1(){
        statistics.win();
        assertEquals(100.0 , statistics.getSessionWinPerCent());
        assertEquals(1, statistics.getSessionWins());
    }
    
    public void testStatistics2(){
        assertTrue(statistics.getTotalDraws() != statistics.getSessionDraws());
    }
}
