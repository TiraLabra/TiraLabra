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
    public void etsiRatkaisutViidesta() {
        int[] koot = {17,19,21,23,25};
        int KONTINVETOISUUS = 99;
        double KONTINTAYTTOASTETAVOITE = 0.50;

        Paketti[] paketit = new Paketti[koot.length];
        int summa=0;
        for (int i=0; i<koot.length; i++) {
            paketit[i]=new Paketti(koot[i]);
            summa+=koot[i];
        }
        Kontti kontti = new Kontti(KONTINVETOISUUS, KONTINTAYTTOASTETAVOITE);
        Taytto taytto = new Taytto(paketit, summa, kontti);
        taytto.etsiMahtuvaSummaKombinaatioistaRek(new ArrayList(), 0, 0, 0);
      
        String vastaus; 
        vastaus = taytto.annaRatkaisutMerkkijonona();
        
        assertEquals("Vetoisuus: 99 Täyttötavoite: 49 Summa: 80 LisaysJono: [0, 1, 2, 3] PoistoJono: null" +
"", vastaus);
    }
    public void etsiRatkaisutViidestaToisinpain() {
        int[] koot = {25,23,21,19,17};
        int KONTINVETOISUUS = 99;
        double KONTINTAYTTOASTETAVOITE = 0.50;

        Paketti[] paketit = new Paketti[koot.length];
        int summa=0;
        for (int i=0; i<koot.length; i++) {
            paketit[i]=new Paketti(koot[i]);
            summa+=koot[i];
        }
        Kontti kontti = new Kontti(KONTINVETOISUUS, KONTINTAYTTOASTETAVOITE);
        Taytto taytto = new Taytto(paketit, summa, kontti);
        taytto.etsiMahtuvaSummaKombinaatioistaRek(new ArrayList(), 0, 0, 0);
      
        String vastaus; 
        vastaus = taytto.annaRatkaisutMerkkijonona();
        
        assertEquals("Vetoisuus: 99 Täyttötavoite: 49 Summa: 80 LisaysJono: null PoistoJono: [0, 1, 2, 3]" +
"", vastaus);
    }
     
}
