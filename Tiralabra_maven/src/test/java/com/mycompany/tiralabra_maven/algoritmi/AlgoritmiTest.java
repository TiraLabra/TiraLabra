/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.algoritmi;

import com.mycompany.tiralabra_maven.Koordinaatit;
import com.mycompany.tiralabra_maven.gui.RuudunTila;
import com.mycompany.tiralabra_maven.gui.Ruutu;
import static junit.framework.Assert.*;
import org.junit.*;

/**
 *
 * @author mikko
 */
public class AlgoritmiTest {

    @Before
    public void setUp() {

    }

    @Test
    public void algoritmiLoytaaSuoranReitinPerille() {
        Ruutu[][] maailma = alustaMaailma();
        Koordinaatit alku = new Koordinaatit(1, 4);
        Koordinaatit maali = new Koordinaatit(8, 4);
        Algoritmi algoritmi = new Algoritmi(maailma, false, alku, maali, false);
        suoritaAlgoritmi(algoritmi);
        assertEquals(7, algoritmi.getReitti().getKuljettuMatka());

    }

    @Test
    public void algoritmiOsaaKiertaaSeinan() {
        Ruutu[][] maailma = alustaMaailma();
        teeSeina(maailma);
        Koordinaatit alku = new Koordinaatit(0, 0);
        Koordinaatit maali = new Koordinaatit(9, 5);
        Algoritmi algoritmi = new Algoritmi(maailma, false, alku, maali, false);
        suoritaAlgoritmi(algoritmi);
        assertEquals(20, algoritmi.getReitti().getKuljettuMatka());

    }

    @Test
    public void algoritmiEiTutkiVaaraanSuuntaan() {
        Ruutu[][] maailma = alustaMaailma();
        maailma[3][3] = Ruutu.SEINA;
        maailma[3][4] = Ruutu.SEINA;
        maailma[3][5] = Ruutu.SEINA;
        Koordinaatit alku = new Koordinaatit(3, 5);
        Koordinaatit maali = new Koordinaatit(4, 0);
        Algoritmi algoritmi = new Algoritmi(maailma, false, alku, maali, false);
        suoritaAlgoritmi(algoritmi);
        assertEquals(8, algoritmi.getReitti().getKuljettuMatka());
        assertNull(algoritmi.getRuudunTila(0, 6));
        assertNull(algoritmi.getRuudunTila(3, 8));
        assertEquals(RuudunTila.REITTI, algoritmi.getRuudunTila(2, 3));
    }

    private Ruutu[][] alustaMaailma() {
        int leveys = 10;
        int korkeus = 10;
        Ruutu[][] maailma = new Ruutu[korkeus][leveys];
        for (int y = 0; y < korkeus; y++) {
            for (int x = 0; x < leveys; x++) {
                maailma[y][x] = Ruutu.LATTIA;
            }
        }
        return maailma;
    }

    private void teeSeina(Ruutu[][] maailma) {
        for (int y = 0; y < 8; y++) {
            maailma[y][4] = Ruutu.SEINA;
        }
    }

    private void suoritaAlgoritmi(Algoritmi algoritmi) {
        algoritmi.start();
        int laskuri = 0;
        while (!algoritmi.onkoValmis()) {
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