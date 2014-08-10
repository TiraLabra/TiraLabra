/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.algoritmi;

import com.mycompany.tiralabra_maven.Koordinaatit;
import com.mycompany.tiralabra_maven.Piirtologiikka;
import static junit.framework.Assert.*;
import org.junit.*;

/**
 *
 * @author mikko
 */
public class SimulaatioTest {

    private Simulaatio simulaatio;
    private Piirtologiikka piirtologiikka;

    public SimulaatioTest() {

    }

    @Before
    public void setUp() throws Exception {
        this.piirtologiikka = new Piirtologiikka(10, 10);
        this.simulaatio = new Simulaatio(piirtologiikka, false);
    }

    @Test
    public void AlgoritmiLoytaaSuoranReitinPerille() {
        int[][] ruudukko = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };

        Koordinaatit alku = new Koordinaatit(1, 4);
        Koordinaatit maali = new Koordinaatit(8, 4);

        simulaatio.setRuudukko(ruudukko);
        simulaatio.setAlkuPiste(alku);
        simulaatio.setMaali(maali);

        suoritaSimulaatio(simulaatio);
        assertEquals(7, simulaatio.getReitti().getKuljettuMatka());

    }

    @Test
    public void algoritmiOsaaKiertaaSeinan() {
        int[][] ruudukko = {
            {1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };

        Koordinaatit alku = new Koordinaatit(0, 0);
        Koordinaatit maali = new Koordinaatit(9, 5);

        simulaatio.setRuudukko(ruudukko);
        simulaatio.setAlkuPiste(alku);
        simulaatio.setMaali(maali);

        suoritaSimulaatio(simulaatio);
        assertEquals(20, simulaatio.getReitti().getKuljettuMatka());

    }

    private void suoritaSimulaatio(Simulaatio simulaatio) {
        simulaatio.start();
        int laskuri = 0;
        while (!simulaatio.onkoValmis()) {
            if (laskuri > 10) {
                fail("Kesti liian kauan");
            }
            laskuri++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
            }
            
        }
    }

}
