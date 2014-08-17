/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ilkkaporna
 */
public class TayttoTest {
    
    public TayttoTest() {
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
    public void hello() {}

    @Test
    public void palauttaaViidenKombinaatiotOikein() {
        int[] koot = {17,19,21,23,25};
        int KONTINVETOISUUS = 99;
        double KONTINTAYTTOASTETAVOITE = 0.95;

        Paketti[] paketit = new Paketti[koot.length];
        int summa=0;
        for (int i=0; i<koot.length; i++) {
            paketit[i]=new Paketti(koot[i]);
            summa+=koot[i];
        }
        Kontti kontti = new Kontti(KONTINVETOISUUS, KONTINTAYTTOASTETAVOITE);
        Taytto taytto = new Taytto(paketit, summa, kontti);
        taytto.etsiSummatKombinaatioistaRek(new ArrayList(), 0, 0);
      
        String vastaus; 
        vastaus = taytto.annaSummatJaJonotMerkkijonona();

        assertEquals("Summa: 105, paketit: [0, 1, 2, 3, 4]\n" +
"Summa: 80, paketit: [0, 1, 2, 3]\n" +
"Summa: 82, paketit: [0, 1, 2, 4]\n" +
"Summa: 57, paketit: [0, 1, 2]\n" +
"Summa: 84, paketit: [0, 1, 3, 4]\n" +
"Summa: 59, paketit: [0, 1, 3]\n" +
"Summa: 61, paketit: [0, 1, 4]\n" +
"Summa: 36, paketit: [0, 1]\n" +
"Summa: 86, paketit: [0, 2, 3, 4]\n" +
"Summa: 61, paketit: [0, 2, 3]\n" +
"Summa: 63, paketit: [0, 2, 4]\n" +
"Summa: 38, paketit: [0, 2]\n" +
"Summa: 65, paketit: [0, 3, 4]\n" +
"Summa: 40, paketit: [0, 3]\n" +
"Summa: 42, paketit: [0, 4]\n" +
"Summa: 17, paketit: [0]\n" +
"Summa: 88, paketit: [1, 2, 3, 4]\n" +
"Summa: 63, paketit: [1, 2, 3]\n" +
"Summa: 65, paketit: [1, 2, 4]\n" +
"Summa: 40, paketit: [1, 2]\n" +
"Summa: 67, paketit: [1, 3, 4]\n" +
"Summa: 42, paketit: [1, 3]\n" +
"Summa: 44, paketit: [1, 4]\n" +
"Summa: 19, paketit: [1]\n" +
"Summa: 69, paketit: [2, 3, 4]\n" +
"Summa: 44, paketit: [2, 3]\n" +
"Summa: 46, paketit: [2, 4]\n" +
"Summa: 21, paketit: [2]\n" +
"Summa: 48, paketit: [3, 4]\n" +
"Summa: 23, paketit: [3]\n" +
"Summa: 25, paketit: [4]\n" +
"Summa: 0, paketit: []\n" +
"", vastaus);
  }
     
}
