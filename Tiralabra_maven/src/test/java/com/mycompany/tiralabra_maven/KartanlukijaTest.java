/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven;

import java.io.File;
import java.io.FileNotFoundException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author iivo
 */
public class KartanlukijaTest {
    
    public KartanlukijaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
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

    @Test
    public void getteritJaSetterit() {
        Kartanlukija kartanlukija = new Kartanlukija();
        Solmu solmu = new Solmu(0,0,0);
        Solmu solmu2 = new Solmu(0,0,0);
        kartanlukija.setLahtosolmu(solmu);
        kartanlukija.setKohdesolmu(solmu2);
        
        assertSame(solmu, kartanlukija.getLahtosolmu());
        assertSame(solmu2, kartanlukija.getKohdesolmu());
    }
    
    @Test //Kopioitu AstarTest:istä
    public void lyhinPolku1() throws FileNotFoundException {
    
        /*    
        kartta:    oletus:
        00A        ..A
        08#        .8#
        00B        ..B
        */
        
        File kartta = new File("kartanlukijaTesti1.txt");
        Kartanlukija kartanlukija = new Kartanlukija();
        Verkko verkko2 = kartanlukija.luoVerkko(kartta);

        Astar astar = new Astar(verkko2, kartanlukija.getLahtosolmu(), kartanlukija.getKohdesolmu());

        Solmu solmu = astar.haeLyhinPolku();
        
        assertEquals("x2y2", "x" + solmu.getX() + 'y' + solmu.getY()); //kohdesolmu (B)
        solmu = solmu.getPolku();
        assertEquals("x1y2", "x" + solmu.getX() + 'y' + solmu.getY()); 
        solmu = solmu.getPolku();
        assertEquals("x0y2", "x" + solmu.getX() + 'y' + solmu.getY()); 
        solmu = solmu.getPolku();
        assertEquals("x0y1", "x" + solmu.getX() + 'y' + solmu.getY()); 
        solmu = solmu.getPolku();
        assertEquals("x0y0", "x" + solmu.getX() + 'y' + solmu.getY()); 
        solmu = solmu.getPolku();
        assertEquals("x1y0", "x" + solmu.getX() + 'y' + solmu.getY()); 
        solmu = solmu.getPolku();
        assertEquals("x2y0", "x" + solmu.getX() + 'y' + solmu.getY()); //aloitussolmu (A)
    }
    
    @Test //Kopioitu AstarTest:istä
    public void lyhinPolku2() throws FileNotFoundException {
    
        /*    
        kartta:    oletus:
        121        ...
        A81        A8.
        08B        08B
        */
        
        File kartta = new File("kartanlukijaTesti2.txt");
        Kartanlukija kartanlukija = new Kartanlukija();
        Verkko verkko2 = kartanlukija.luoVerkko(kartta);

        Astar astar = new Astar(verkko2, kartanlukija.getLahtosolmu(), kartanlukija.getKohdesolmu());

        Solmu solmu = astar.haeLyhinPolku();
        
        assertEquals("x2y2", "x" + solmu.getX() + 'y' + solmu.getY()); //kohdesolmu (B)
        solmu = solmu.getPolku();
        assertEquals("x2y1", "x" + solmu.getX() + 'y' + solmu.getY()); 
        solmu = solmu.getPolku();
        assertEquals("x2y0", "x" + solmu.getX() + 'y' + solmu.getY()); 
        solmu = solmu.getPolku();
        assertEquals("x1y0", "x" + solmu.getX() + 'y' + solmu.getY()); 
        solmu = solmu.getPolku();
        assertEquals("x0y0", "x" + solmu.getX() + 'y' + solmu.getY()); 
        solmu = solmu.getPolku();
        assertEquals("x0y1", "x" + solmu.getX() + 'y' + solmu.getY()); //aloitussolmu (A)
    }
    
    @Test //Kopioitu AstarTest:istä
    public void lyhinPolku3() throws FileNotFoundException {
    
        /*    
        kartta:    oletus:
        000B       000B
        ###0       ###.
        00#0       00#.
        A0#0       A0#.
        0000       ....
        */
        
        File kartta = new File("kartanlukijaTesti3.txt");
        Kartanlukija kartanlukija = new Kartanlukija();
        Verkko verkko2 = kartanlukija.luoVerkko(kartta);

        Astar astar = new Astar(verkko2, kartanlukija.getLahtosolmu(), kartanlukija.getKohdesolmu());

        Solmu solmu = astar.haeLyhinPolku();
        
        assertEquals("x3y0", "x" + solmu.getX() + 'y' + solmu.getY()); //kohdesolmu (B)
        solmu = solmu.getPolku();
        assertEquals("x3y1", "x" + solmu.getX() + 'y' + solmu.getY()); 
        solmu = solmu.getPolku();
        assertEquals("x3y2", "x" + solmu.getX() + 'y' + solmu.getY()); 
        solmu = solmu.getPolku();
        assertEquals("x3y3", "x" + solmu.getX() + 'y' + solmu.getY()); 
        solmu = solmu.getPolku();
        assertEquals("x3y4", "x" + solmu.getX() + 'y' + solmu.getY()); 
        solmu = solmu.getPolku();
        assertEquals("x2y4", "x" + solmu.getX() + 'y' + solmu.getY()); 
        solmu = solmu.getPolku();
        assertEquals("x1y4", "x" + solmu.getX() + 'y' + solmu.getY()); 
        solmu = solmu.getPolku();
        assertEquals("x0y4", "x" + solmu.getX() + 'y' + solmu.getY()); 
        solmu = solmu.getPolku();
        assertEquals("x0y3", "x" + solmu.getX() + 'y' + solmu.getY()); //aloitussolmu (A)
    }
    
    @Test
    public void lyhinPolku4() throws FileNotFoundException {
    
        /*    
        kartta:    oletus:
        A1245      A..45
        34334      34.34
        67899      67.99
        98765      98.65
        4321B      43..B
        */
        
        File kartta = new File("kartanlukijaTesti4.txt");
        Kartanlukija kartanlukija = new Kartanlukija();
        Verkko verkko2 = kartanlukija.luoVerkko(kartta);

        Astar astar = new Astar(verkko2, kartanlukija.getLahtosolmu(), kartanlukija.getKohdesolmu());

        Solmu solmu = astar.haeLyhinPolku();
        
        assertEquals("x4y4", "x" + solmu.getX() + 'y' + solmu.getY()); //kohdesolmu (B)
        solmu = solmu.getPolku();
        assertEquals("x3y4", "x" + solmu.getX() + 'y' + solmu.getY()); 
        solmu = solmu.getPolku();
        assertEquals("x2y4", "x" + solmu.getX() + 'y' + solmu.getY()); 
        solmu = solmu.getPolku();
        assertEquals("x2y3", "x" + solmu.getX() + 'y' + solmu.getY()); 
        solmu = solmu.getPolku();
        assertEquals("x2y2", "x" + solmu.getX() + 'y' + solmu.getY()); 
        solmu = solmu.getPolku();
        assertEquals("x2y1", "x" + solmu.getX() + 'y' + solmu.getY()); 
        solmu = solmu.getPolku();
        assertEquals("x2y0", "x" + solmu.getX() + 'y' + solmu.getY()); 
        solmu = solmu.getPolku();
        assertEquals("x1y0", "x" + solmu.getX() + 'y' + solmu.getY()); 
        solmu = solmu.getPolku();
        assertEquals("x0y0", "x" + solmu.getX() + 'y' + solmu.getY()); //aloitussolmu (A)
    }
}
