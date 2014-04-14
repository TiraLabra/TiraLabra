/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman.peli.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pacman.peli.Highscore;

/**
 *
 * @author Hanna
 */
public class HighscoreTest {
    
    private Highscore highscore;
    private File tempFile;
    
    public HighscoreTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
        tempFile = File.createTempFile("testi", "tiedosto");
        highscore = new Highscore(tempFile);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void katsooOikeinOnkoUusiEnnatys1() throws IOException {
        assertTrue(highscore.tarkistaOnkoEnnatys(200));       
    }
    
    @Test
    public void katsooOikeinOnkoUusiEnnatys2() throws IOException {
        highscore.kirjaaEnnatys(51);
        assertTrue(highscore.tarkistaOnkoEnnatys(200)); 
    }
    
    @Test
    public void kirjataankoTulosOikein() throws IOException {
        highscore.kirjaaEnnatys(195); 
        Scanner lukija = new Scanner(tempFile);
        assertEquals(195, lukija.nextInt());
    }
    
    @Test
    public void katsooOikeinEttaEiEnnatys() throws IOException {
        highscore.kirjaaEnnatys(200);
        assertFalse(highscore.tarkistaOnkoEnnatys(50));
    }
    
    @Test
    public void toimiiOikeinJosTiedostoaEiOlemassa1() throws FileNotFoundException {
        highscore = new Highscore(new File("enOleOlemassa"));        
        assertFalse(highscore.tarkistaOnkoEnnatys(-53));
    }
    
    @Test
    public void toimiiOikeinJosTiedostoaEiOlemassa2() throws FileNotFoundException {
        highscore = new Highscore(new File("enOleOlemassa"));        
        assertTrue(highscore.tarkistaOnkoEnnatys(10));        
    }
    
    @Test
    public void onkoTiedostoOlemassa() throws IOException {;
        highscore = new Highscore(tempFile);
        highscore.kirjaaEnnatys(50);
        assertTrue(tempFile.exists());

    }
}
