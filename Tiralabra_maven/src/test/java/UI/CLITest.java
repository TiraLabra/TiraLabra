/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import search.AStarSearch;

/**
 *
 * @author ghaas
 */
public class CLITest {
    
    /**
     * Virta, johon testattavan Main-luokan komentorivitulosteet ohjataan.
     */
    private ByteArrayOutputStream out;
    
    private CLI cli;
    private int [][] validMap = {{1, 1, 2, 3, 2},
                         {2, 6, 2, 1, 1},
                         {1, 2, 9, 2, 2},
                         {2, 1, 1, 2, 1}};
    
    public CLITest() {
    }
    

    public void setupTest(final String input) {
        out = new ByteArrayOutputStream();
        cli = new CLI(new AStarSearch(validMap), new Scanner(input), new PrintStream(out));
    }

    /**
     * Uudelleenohjaa System.outin staattisessa kentässä out
     * olevaan virtaan, jotta ohjelman komentorivitulosteita voidaan
     * testata.
     */
    private void startCapture() {
        System.setOut(new PrintStream(out));
    }

    /**
     * Poistaa System.outin uudelleenohjauksen.
     */
    private void stopCapture() {
        System.setOut(null);
    }
    
    @Test
    public void invalidInputDoesNotWork() {
        setupTest("2,3,,4,2");
        startCapture();
        cli.menu();
        String[] output = out.toString().split("\n");
        System.out.println(output[0]);
        stopCapture();
        assertEquals("bad input, please use the x1,y1,x2,y2 format", output[7]);
    }
    
    @Test
    public void validInputStartsASearch() {
        setupTest("2,3,4,2");
        startCapture();
        cli.menu();
        String[] output = out.toString().split("\n");
        System.out.println(output[0]);
        stopCapture();
        assertEquals("Search result: ", output[7]);
        
    }
}
