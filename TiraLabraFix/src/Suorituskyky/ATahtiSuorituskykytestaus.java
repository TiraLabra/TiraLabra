/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Suorituskyky;

import Algoritmit.Atahtialgoritmi;
import Tietorakenteet.DiskreettiSolmu;
import Tietorakenteet.DiskreettiVerkko;
import Tietorakenteet.Kordinaatti;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * Tässä testataan ATahtialgoritmin suorituskykya
 */
public class ATahtiSuorituskykytestaus {

    private DiskreettiVerkko verkko;
    private Atahtialgoritmi algoritmi;
    private HashMap<Integer, Verkkoluokittelu> testauskartta;

    public ATahtiSuorituskykytestaus() {
        verkko = new DiskreettiVerkko(1);

    }
    /**
 *
 * Suorittaa testauksen k*k suuruisella ruudukolla
 */

    public void suorita(int k) {

        ArrayList<DiskreettiSolmu> solmut = new ArrayList<DiskreettiSolmu>();
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                if ((i == 0) && (j == 0)) {
                    continue;
                }
                if ((i == k - 1) && (j == k - 1)) {
                    continue;
                }
                solmut.add(new DiskreettiSolmu(i, j));

            }

        }

        int koko = k * k;
        HashMap<Kordinaatti, DiskreettiSolmu> solmukartta = new HashMap<Kordinaatti, DiskreettiSolmu>();
        DiskreettiSolmu alku = new DiskreettiSolmu(0, 0);
        DiskreettiSolmu loppu = new DiskreettiSolmu(k-1, k-1);
        solmukartta.put(new Kordinaatti(0, 0), alku);
        solmukartta.put(new Kordinaatti(k-1, k-1), loppu);
        for (DiskreettiSolmu solmut1 : solmut) {
            solmukartta.put(solmut1.palautaKordinaatti(), solmut1);
        }
        verkko.asetaKartta(solmukartta);
        algoritmi = new Atahtialgoritmi(verkko, koko + 10);
        this.testauskartta = new HashMap<Integer, Verkkoluokittelu>();
        for (int i = 0; i <= koko - 2; i++) {
            this.testauskartta.put(i, new Verkkoluokittelu(i, koko));
        }
        algoritmi.asetaPisteet(alku, loppu);
        iterointi(koko, 0, 0, solmut);
       
        for (int i = 0; i <= koko - 2; i++) {
            Verkkoluokittelu l = this.testauskartta.get(i);
            System.out.println(i + " mustia. Aika: " + l.palautaaikakeskiarvo() + " Onnistumistod: " + l.palautaonnistumistodennakoisyys());
        }
        
    }

    public void iterointi(int koko, int nyt, int mustia, ArrayList<DiskreettiSolmu> solmut) {
        System.out.println("Iterointi: Koko: " + koko + ", nyt: " + nyt + ", mustia: " + mustia);
        if (nyt == koko - 2) {
            long aikaAlussa = System.currentTimeMillis();
            boolean k = algoritmi.laske();
            long aikaLopussa = System.currentTimeMillis();
            long aika = aikaLopussa = aikaAlussa;
            int j = 0;
            if (k == true) {
                j = 1;
            }
            this.testauskartta.get(mustia).lisaahavainto(aika, j);
        } else {
            solmut.get(nyt).asetaKulku(false);
            iterointi(koko, nyt + 1, mustia + 1, solmut);
            solmut.get(nyt).asetaKulku(true);
            iterointi(koko, nyt + 1, mustia, solmut);

        }

    }

}
