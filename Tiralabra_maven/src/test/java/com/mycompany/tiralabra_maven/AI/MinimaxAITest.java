/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven.AI;

import com.mycompany.tiralabra_maven.PelaajaTest;
import com.mycompany.tiralabra_maven.Peli;
import com.mycompany.tiralabra_maven.Pelilauta;
import com.mycompany.tiralabra_maven.Siirto;
import com.mycompany.tiralabra_maven.gui.Paivitettava;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author noora
 */
public class MinimaxAITest {
    private Peli peli;
    private Pelilauta lauta;
    private Heuristiikka heuristiikka;
    private MinimaxAI AI;
    private Paivitettava paivitettava;
    
    public MinimaxAITest() {
    }
    
    @Before
    public void setUp() {
        lauta = new Pelilauta();
        heuristiikka = new Heuristiikka(lauta);
        peli = new Peli();
        paivitettava = new Paivitettava() {

            @Override
            public void paivita() {
                
            }

            @Override
            public void naytaViesti(String viesti) {
                
            }
        };
        peli.setPaivitettava(paivitettava);
        AI = new MinimaxAI(peli);
        
    }

    @Test
    public void seuraavaSiirtoPalauttaaOikeanSiirronPelinAlussa() {
        peli.uusiPeli();
        lauta.asetaNappulatAlkuasetelmaan();
        Siirto siirto = AI.seuraavaSiirto();
        assertEquals(4, siirto.getLoppuRivi());
        assertEquals(1, siirto.getLoppuSarake());
    }
    
}
