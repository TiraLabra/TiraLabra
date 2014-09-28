/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven;

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
public class AstarTest {
    
    private final int AARETONPAINO = 1000000;
    
    public AstarTest() {
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
    public void konstruktoriJaAlustus() {
    
        /*
        000
        08#
        000
        */
        
        //Luo verkko
        Verkko verkko2 = new Verkko();
        
        //Luo solmut
        verkko2.lisaaSolmu( new Solmu(0, 0, 0) );
        verkko2.lisaaSolmu( new Solmu(1, 0, 0) );
        verkko2.lisaaSolmu( new Solmu(2, 0, 0) );
        verkko2.lisaaSolmu( new Solmu(0, 1, 0) );
        verkko2.lisaaSolmu( new Solmu(1, 1, 8) );
        verkko2.lisaaSolmu( new Solmu(2, 1, -1) );
        verkko2.lisaaSolmu( new Solmu(0, 2, 0) );
        verkko2.lisaaSolmu( new Solmu(1, 2, 0) );
        verkko2.lisaaSolmu( new Solmu(2, 2, 0) );
        
        verkko2.luoVieruslistat();

        Astar astar = new Astar(verkko2, verkko2.getSolmu(2, 0), verkko2.getSolmu(2, 2));
        
        int alkuunsumma = 0;
        
        Listasolmu pinosolmu = verkko2.getSolmut().getYlin();
        
        while (pinosolmu != null) {
            Solmu solmu = pinosolmu.getSisalto();
            
            alkuunsumma += solmu.getAlkuun();
            
            pinosolmu = pinosolmu.getSeuraava();
        }
        
        assertEquals(0, verkko2.getSolmu(2, 0).getAlkuun()); //aloitussolmun alkuun
        assertEquals(AARETONPAINO, verkko2.getSolmu(2, 2).getAlkuun()); //kohdesolmun alkuun
        assertEquals( (AARETONPAINO * 8), alkuunsumma); //äärettömäksi alustettuja solmuja n-1 kpl
        assertEquals(2, verkko2.getSolmu(2, 0).getLoppuun()); //kohdesolmu on kahden siirron päässä aloitussolmusta
        assertEquals(1, verkko2.getSolmu(2, 1).getLoppuun());
        assertEquals(0, verkko2.getSolmu(2, 2).getLoppuun());
        assertEquals(4, verkko2.getSolmu(0, 0).getLoppuun());
    }
    
    @Test
    public void lyhinPolku1() {
    
        /*    
        kartta:    oletus:
        00A        ..A
        08#        .8#
        00B        ..B
        */
        
        //Luo verkko
        Verkko verkko2 = new Verkko();
        
        //Luo solmut
        verkko2.lisaaSolmu( new Solmu(0, 0, 0) );
        verkko2.lisaaSolmu( new Solmu(1, 0, 0) );
        verkko2.lisaaSolmu( new Solmu(2, 0, 0) );
        verkko2.lisaaSolmu( new Solmu(0, 1, 0) );
        verkko2.lisaaSolmu( new Solmu(1, 1, 8) );
        verkko2.lisaaSolmu( new Solmu(2, 1, -1) );
        verkko2.lisaaSolmu( new Solmu(0, 2, 0) );
        verkko2.lisaaSolmu( new Solmu(1, 2, 0) );
        verkko2.lisaaSolmu( new Solmu(2, 2, 0) );
        
        verkko2.luoVieruslistat();

        Astar astar = new Astar(verkko2, verkko2.getSolmu(2, 0), verkko2.getSolmu(2, 2));

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
    
    public void lyhinPolku2() {
    
        /*    
        kartta:    oletus:
        121        ...
        A81        A8.
        08B        08B
        */
        
        //Luo verkko
        Verkko verkko2 = new Verkko();
        
        //Luo solmut
        verkko2.lisaaSolmu( new Solmu(0, 0, 1) );
        verkko2.lisaaSolmu( new Solmu(1, 0, 2) );
        verkko2.lisaaSolmu( new Solmu(2, 0, 1) );
        verkko2.lisaaSolmu( new Solmu(0, 1, 0) );
        verkko2.lisaaSolmu( new Solmu(1, 1, 8) );
        verkko2.lisaaSolmu( new Solmu(2, 1, 1) );
        verkko2.lisaaSolmu( new Solmu(0, 2, 1) );
        verkko2.lisaaSolmu( new Solmu(1, 2, 8) );
        verkko2.lisaaSolmu( new Solmu(2, 2, 0) );
        
        verkko2.luoVieruslistat();

        Astar astar = new Astar(verkko2, verkko2.getSolmu(0, 1), verkko2.getSolmu(2, 2));

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
    
    public void lyhinPolku3() {
    
        /*    
        kartta:    oletus:
        000B       000B
        ###0       ###.
        00#0       00#.
        A0#0       A.#.
        0000       0...
        */
        
        //Luo verkko
        Verkko verkko2 = new Verkko();
        
        //Luo solmut
        verkko2.lisaaSolmu( new Solmu(0, 0, 0) );
        verkko2.lisaaSolmu( new Solmu(1, 0, 0) );
        verkko2.lisaaSolmu( new Solmu(2, 0, 0) );
        verkko2.lisaaSolmu( new Solmu(3, 0, 0) );
        verkko2.lisaaSolmu( new Solmu(0, 1, -1) );
        verkko2.lisaaSolmu( new Solmu(1, 1, -1) );
        verkko2.lisaaSolmu( new Solmu(2, 1, -1) );
        verkko2.lisaaSolmu( new Solmu(3, 1, 0) );
        verkko2.lisaaSolmu( new Solmu(0, 2, 0) );
        verkko2.lisaaSolmu( new Solmu(1, 2, 0) );
        verkko2.lisaaSolmu( new Solmu(2, 2, -1) );
        verkko2.lisaaSolmu( new Solmu(3, 2, 0) );
        verkko2.lisaaSolmu( new Solmu(0, 3, 0) );
        verkko2.lisaaSolmu( new Solmu(1, 3, 0) );
        verkko2.lisaaSolmu( new Solmu(2, 3, -1) );
        verkko2.lisaaSolmu( new Solmu(3, 3, 0) );
        verkko2.lisaaSolmu( new Solmu(0, 4, 0) );
        verkko2.lisaaSolmu( new Solmu(1, 4, 0) );
        verkko2.lisaaSolmu( new Solmu(2, 4, 0) );
        verkko2.lisaaSolmu( new Solmu(3, 4, 0) );
        
        verkko2.luoVieruslistat();

        Astar astar = new Astar(verkko2, verkko2.getSolmu(0, 3), verkko2.getSolmu(3, 0));

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
        assertEquals("x1y3", "x" + solmu.getX() + 'y' + solmu.getY()); 
        solmu = solmu.getPolku();
        assertEquals("x0y3", "x" + solmu.getX() + 'y' + solmu.getY()); //aloitussolmu (A)
    }
}
