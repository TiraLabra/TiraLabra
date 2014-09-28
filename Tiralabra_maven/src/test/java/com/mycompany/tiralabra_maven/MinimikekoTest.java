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
public class MinimikekoTest {
    
    public MinimikekoTest() {
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
    public void kekokoe1() {
        /*
        Tehdään minimikeko
        
                1
             3     6
           5   9 8  
        */
    
        Minimikeko keko = new Minimikeko(6);
        
        Solmu solmu1 = new Solmu(0, 0, 0);
        solmu1.setAlkuun(1);
        solmu1.setLoppuun(0);
        
        Solmu solmu3 = new Solmu(0, 0, 0);
        solmu3.setAlkuun(2);
        solmu3.setLoppuun(1);
        
        Solmu solmu6 = new Solmu(0, 0, 0);
        solmu6.setAlkuun(3);
        solmu6.setLoppuun(3);
        
        Solmu solmu5 = new Solmu(0, 0, 0);
        solmu5.setAlkuun(3);
        solmu5.setLoppuun(2);
        
        Solmu solmu9 = new Solmu(0, 0, 0);
        solmu9.setAlkuun(4);
        solmu9.setLoppuun(5);
        
        Solmu solmu8 = new Solmu(0, 0, 0);
        solmu8.setAlkuun(2);
        solmu8.setLoppuun(6);

        
        keko.lisaa(solmu1);
        keko.lisaa(solmu6);
        keko.lisaa(solmu3);
        keko.lisaa(solmu8);
        keko.lisaa(solmu9);
        keko.lisaa(solmu5);
        
        assertEquals(solmu1, keko.poistaPienin());
        assertEquals(solmu3, keko.poistaPienin());
        assertEquals(solmu5, keko.poistaPienin());
        assertEquals(solmu6, keko.poistaPienin());
        assertEquals(solmu8, keko.poistaPienin());
        assertEquals(solmu9, keko.poistaPienin());

    }
    
    @Test
    public void kekokoe2() {
        /*
        Tehdään minimikeko
        
                   14
              21           16
          24     31     19    68
        65  26 32  47 20
        */
    
        Minimikeko keko = new Minimikeko(12);
        
        Solmu solmu14 = new Solmu(0, 0, 0);
        solmu14.setAlkuun(14);
        solmu14.setLoppuun(0);
        
        Solmu solmu21 = new Solmu(0, 0, 0);
        solmu21.setAlkuun(21);
        solmu21.setLoppuun(0);
        
        Solmu solmu16 = new Solmu(0, 0, 0);
        solmu16.setAlkuun(16);
        solmu16.setLoppuun(0);
        
        Solmu solmu24 = new Solmu(0, 0, 0);
        solmu24.setAlkuun(24);
        solmu24.setLoppuun(0);
        
        Solmu solmu31 = new Solmu(0, 0, 0);
        solmu31.setAlkuun(31);
        solmu31.setLoppuun(0);
        
        Solmu solmu19 = new Solmu(0, 0, 0);
        solmu19.setAlkuun(19);
        solmu19.setLoppuun(0);
        
        Solmu solmu68 = new Solmu(0, 0, 0);
        solmu68.setAlkuun(86);
        solmu68.setLoppuun(0);
        
        Solmu solmu65 = new Solmu(0, 0, 0);
        solmu65.setAlkuun(65);
        solmu65.setLoppuun(0);
        
        Solmu solmu26 = new Solmu(0, 0, 0);
        solmu26.setAlkuun(26);
        solmu26.setLoppuun(0);
        
        Solmu solmu32 = new Solmu(0, 0, 0);
        solmu32.setAlkuun(32);
        solmu32.setLoppuun(0);
        
        Solmu solmu47 = new Solmu(0, 0, 0);
        solmu47.setAlkuun(47);
        solmu47.setLoppuun(0);
        
        Solmu solmu20 = new Solmu(0, 0, 0);
        solmu20.setAlkuun(20);
        solmu20.setLoppuun(0);

        
        keko.lisaa(solmu14);
        keko.lisaa(solmu21);
        keko.lisaa(solmu16);
        keko.lisaa(solmu65);
        keko.lisaa(solmu26);
        keko.lisaa(solmu32);
        keko.lisaa(solmu47);
        keko.lisaa(solmu20);
        keko.lisaa(solmu24);
        keko.lisaa(solmu31);
        keko.lisaa(solmu19);
        keko.lisaa(solmu68);
        
        
        assertEquals(solmu14, keko.poistaPienin());
        assertEquals(solmu16, keko.poistaPienin());
        assertEquals(solmu19, keko.poistaPienin());
        assertEquals(solmu20, keko.poistaPienin());
        assertEquals(solmu21, keko.poistaPienin());
        assertEquals(solmu24, keko.poistaPienin());
        assertEquals(solmu26, keko.poistaPienin());
        assertEquals(solmu31, keko.poistaPienin());
        assertEquals(solmu32, keko.poistaPienin());
        assertEquals(solmu47, keko.poistaPienin());
        assertEquals(solmu65, keko.poistaPienin());
        assertEquals(solmu68, keko.poistaPienin());
        
        //assertNull(keko.poistaPienin());
        
    }
    
    @Test
    public void samojaArvoja() {
        /*
        Tehdään minimikeko
        
               10
             10  12
        */
    
        Minimikeko keko = new Minimikeko(3);
        
        Solmu solmu10_1 = new Solmu(0, 0, 0);
        solmu10_1.setAlkuun(10);
        solmu10_1.setLoppuun(0);
        
        Solmu solmu10_2 = new Solmu(0, 0, 0);
        solmu10_2.setAlkuun(10);
        solmu10_2.setLoppuun(0);
        
        Solmu solmu12 = new Solmu(0, 0, 0);
        solmu12.setAlkuun(12);
        solmu12.setLoppuun(0);
        
        
        keko.lisaa(solmu12);
        keko.lisaa(solmu10_1);
        keko.lisaa(solmu10_2);

        assertTrue(keko.poistaPienin().getAlkuunLoppuunSumma() == 10);
        assertTrue(keko.poistaPienin().getAlkuunLoppuunSumma() == 10);
        assertEquals(solmu12, keko.poistaPienin());
        
    }
    
    @Test
    public void pienennaArvoa() {
        /*
        Tehdään minimikeko
        
                 9       ja muutetaan se lennossa      [4]
              10    14                                9   14
           [12] 15                                  10 15
        */
    
        Minimikeko keko = new Minimikeko(5);
        
        Solmu solmu10 = new Solmu(0, 0, 0);
        solmu10.setAlkuun(10);
        
        Solmu solmu9 = new Solmu(0, 0, 0);
        solmu9.setAlkuun(9);
        
        Solmu solmu14 = new Solmu(0, 0, 0);
        solmu14.setAlkuun(14);
        
        Solmu solmu15 = new Solmu(0, 0, 0);
        solmu15.setAlkuun(15);

        Solmu solmu4 = new Solmu(0, 0, 0);
        solmu4.setAlkuun(12); //alussa 12
        
        keko.lisaa(solmu4);
        keko.lisaa(solmu10);
        keko.lisaa(solmu9);
        keko.lisaa(solmu14);
        keko.lisaa(solmu15);
        
        solmu4.setAlkuun(4); //muutetaan arvoa
        
        keko.pienennaArvoa(solmu4);

        assertEquals(solmu4, keko.poistaPienin());
        assertEquals(solmu9, keko.poistaPienin());
        assertEquals(solmu10, keko.poistaPienin());
    }
    
    @Test
    public void taulukkokoe() {
        /*
        Tehdään minimikeko
        
                1
             3     5
           8   9 6  
        
        taulukkomuodossa: |1|3|5|8|9|6|
        */
    
        Minimikeko keko = new Minimikeko(6);
        
        Solmu solmu1 = new Solmu(0, 0, 0);
        solmu1.setAlkuun(1);
        solmu1.setLoppuun(0);
        
        Solmu solmu3 = new Solmu(0, 0, 0);
        solmu3.setAlkuun(2);
        solmu3.setLoppuun(1);
        
        Solmu solmu6 = new Solmu(0, 0, 0);
        solmu6.setAlkuun(3);
        solmu6.setLoppuun(3);
        
        Solmu solmu5 = new Solmu(0, 0, 0);
        solmu5.setAlkuun(3);
        solmu5.setLoppuun(2);
        
        Solmu solmu9 = new Solmu(0, 0, 0);
        solmu9.setAlkuun(4);
        solmu9.setLoppuun(5);
        
        Solmu solmu8 = new Solmu(0, 0, 0);
        solmu8.setAlkuun(2);
        solmu8.setLoppuun(6);

        
        keko.lisaa(solmu1);
        keko.lisaa(solmu6);
        keko.lisaa(solmu3);
        keko.lisaa(solmu8);
        keko.lisaa(solmu9);
        keko.lisaa(solmu5);
        
        Solmu[] solmut = keko.getSolmut();
        
        assertEquals(solmu1, solmut[0]);
        assertEquals(solmu3, solmut[1]);
        assertEquals(solmu5, solmut[2]);
        assertEquals(solmu8, solmut[3]);
        assertEquals(solmu9, solmut[4]);
        assertEquals(solmu6, solmut[5]);
    }
    
    @Test
    public void pieninIlmanPoistoa() {
        /*
        Tehdään minimikeko
        
                1
             3  
        */
    
        Minimikeko keko = new Minimikeko(2);
        
        Solmu solmu1 = new Solmu(0, 0, 0);
        solmu1.setAlkuun(1);
        solmu1.setLoppuun(0);
        
        Solmu solmu3 = new Solmu(0, 0, 0);
        solmu3.setAlkuun(2);
        solmu3.setLoppuun(1);

        keko.lisaa(solmu3);
        keko.lisaa(solmu1);
        
        assertEquals(solmu1, keko.pienin());

    }
    
    @Test
    public void solmunIndeksinEtsinta() {
        /*
        Tehdään minimikeko
        
                1
             3  
        taulukkomuodossa |1|3|
                         0. 1.
        */
    
        Minimikeko keko = new Minimikeko(2);
        
        Solmu solmu1 = new Solmu(0, 0, 0);
        solmu1.setAlkuun(1);
        solmu1.setLoppuun(0);
        
        Solmu solmu2 = new Solmu(0, 0, 0);
        solmu2.setAlkuun(2);
        solmu2.setLoppuun(0);
        
        Solmu solmu3 = new Solmu(0, 0, 0);
        solmu3.setAlkuun(2);
        solmu3.setLoppuun(1);

        keko.lisaa(solmu3);
        keko.lisaa(solmu1);
        
        assertEquals(0, solmu1.getIndeksi());
        assertEquals(1, solmu3.getIndeksi());
        assertEquals(-1, solmu2.getIndeksi()); //ei lisätty kekoon
    }
}
