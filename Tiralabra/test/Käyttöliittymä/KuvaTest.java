/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Käyttöliittymä;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jaakko
 */
public class KuvaTest {
    
    static Kuva testi;
    
    
    public KuvaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        testi=new Kuva();
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

    /**
     * Test of haeKuva method, of class Kuva.
     */
    @Test
    public void testHaeKuva() throws URISyntaxException, IOException {
        
        assertEquals(testi.kuva, ImageIO.read(new File(getClass().getResource("Untitled.jpg").toURI())));
    }



    /**
     * Test of paintComponent method, of class Kuva.
     */
    @Test
    public void testPaintComponent() {
        System.out.println("paintComponent");
        Graphics grafiikka = null;
        Kuva instance = new Kuva();
        instance.paintComponent(grafiikka);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mousePressed method, of class Kuva.
     */
    @Test
    public void testMousePressed() {
        System.out.println("mousePressed");
        MouseEvent me = null;
        Kuva instance = new Kuva();
        instance.mousePressed(me);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mouseReleased method, of class Kuva.
     */
    @Test
    public void testMouseReleased() {
        System.out.println("mouseReleased");
        MouseEvent me = null;
        Kuva instance = new Kuva();
        instance.mouseReleased(me);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
