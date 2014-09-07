/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.logiikka.algoritmi;

import com.mycompany.tiralabra_maven.logiikka.algoritmi.heuristiikka.Heuristiikka;
import com.mycompany.tiralabra_maven.logiikka.algoritmi.heuristiikka.ManhattanHeuristiikka;
import com.mycompany.tiralabra_maven.AlgoritmiTyyppi;
import com.mycompany.tiralabra_maven.Koordinaatit;
import com.mycompany.tiralabra_maven.gui.RuudunTila;
import com.mycompany.tiralabra_maven.gui.Ruutu;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.fail;

/**
 *
 * @author mikko
 */
public class AlgoritmiTest {

    private final AlgoritmiTyyppi algoritmiTyyppi;
    private final AlgoritmiTehdas algoritmiTehdas;

    public AlgoritmiTest(AlgoritmiTyyppi algoritmiTyyppi) {
        this.algoritmiTyyppi = algoritmiTyyppi;
        this.algoritmiTehdas = new AlgoritmiTehdas();
    }

    public void algoritmiLoytaaSuoranReitinPerille() {
        Ruutu[][] maailma = alustaMaailma();
        Koordinaatit alku = new Koordinaatit(1, 4);
        Koordinaatit maali = new Koordinaatit(8, 4);
        Algoritmi algoritmi = algoritmiTehdas.luoAlgoritmi(algoritmiTyyppi, maailma, 0, alku, maali, false, new ManhattanHeuristiikka());
        suoritaAlgoritmi(algoritmi);
        assertEquals(7, algoritmi.getReitti().getKuljettuMatka());

    }

    public void algoritmiLoytaaReitinPerilleNopeasti(int alarajaMs, int ylarajaMs) {
        Ruutu[][] maailma = alustaMaailma(40, 40);
        Koordinaatit alku = new Koordinaatit(38, 0);
        Koordinaatit maali = new Koordinaatit(0, 38);
        //Annetaan algoritmille 1 ms hidaste suorituskykytestausta varten
        Algoritmi algoritmi = algoritmiTehdas.luoAlgoritmi(algoritmiTyyppi, maailma, 1, alku, maali, false, new ManhattanHeuristiikka());
        long aikaAlussa = System.currentTimeMillis();
        suoritaAlgoritmi(algoritmi);
        long kulunutAika = System.currentTimeMillis() - aikaAlussa;
        assertEquals(76, algoritmi.getReitti().getKuljettuMatka());
        //alaraja 210
        if (kulunutAika < alarajaMs) {
            fail("Suoritukseen kului epäilyttävän vähän aikaa (" + kulunutAika + " ms), 1 ms hidaste ei toimi?");
        }
        //ylaraja 1500
        if (kulunutAika > ylarajaMs) {
            fail("Aikaa kului yli " + ylarajaMs + "ms. aikaa kului " + kulunutAika + "ms");
        }
        
    }

    public void algoritmiOsaaKiertaaSeinan() {
        Ruutu[][] maailma = alustaMaailma();
        teeSeina(maailma);
        Koordinaatit alku = new Koordinaatit(0, 0);
        Koordinaatit maali = new Koordinaatit(9, 5);
        Algoritmi algoritmi = algoritmiTehdas.luoAlgoritmi(algoritmiTyyppi, maailma, 0, alku, maali, false, new ManhattanHeuristiikka());

        suoritaAlgoritmi(algoritmi);
        assertEquals(20, algoritmi.getReitti().getKuljettuMatka());

    }

    private void teeSeina(Ruutu[][] maailma) {
        for (int y = 0; y < 8; y++) {
            maailma[y][4] = Ruutu.SEINA;
        }
    }

    public void algoritmiLoytaaLyhimmanReitinEikaTutkiVaaraanSuuntaan() {
        Ruutu[][] maailma = alustaMaailma();
        maailma[3][3] = Ruutu.SEINA;
        maailma[3][4] = Ruutu.SEINA;
        maailma[3][5] = Ruutu.SEINA;
        Koordinaatit alku = new Koordinaatit(3, 5);
        Koordinaatit maali = new Koordinaatit(4, 0);
        Algoritmi algoritmi = algoritmiTehdas.luoAlgoritmi(algoritmiTyyppi, maailma, 0, alku, maali, false, new ManhattanHeuristiikka());
        suoritaAlgoritmi(algoritmi);
        assertEquals(8, algoritmi.getReitti().getKuljettuMatka());
        assertNull(algoritmi.getRuudunTila(0, 6));
        assertNull(algoritmi.getRuudunTila(3, 8));
        assertEquals(RuudunTila.REITTI, algoritmi.getRuudunTila(2, 3));
    }

    public Ruutu[][] alustaMaailma() {
        return alustaMaailma(10, 10);
    }

    public Ruutu[][] alustaMaailma(int leveys, int korkeus) {
        Ruutu[][] maailma = new Ruutu[korkeus][leveys];
        for (int y = 0; y < korkeus; y++) {
            for (int x = 0; x < leveys; x++) {
                maailma[y][x] = Ruutu.LATTIA;
            }
        }
        return maailma;
    }

    public void suoritaAlgoritmi(Algoritmi algoritmi) {
        new Thread(algoritmi).start();
        //Koodi tarkkailee jääkö algoritmi jumiin
        int laskuri = 0;
        while (!algoritmi.onkoValmis()) {
            if (laskuri > 60) {
                fail("Kesti liian kauan, jäikö algoritmi jumiin?");
            }
            laskuri++;
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
            }

        }
    }

}
