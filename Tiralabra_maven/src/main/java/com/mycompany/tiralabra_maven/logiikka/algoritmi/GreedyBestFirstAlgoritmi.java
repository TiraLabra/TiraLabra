/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.logiikka.algoritmi;

import com.mycompany.tiralabra_maven.logiikka.algoritmi.heuristiikka.Heuristiikka;
import com.mycompany.tiralabra_maven.Koordinaatit;
import com.mycompany.tiralabra_maven.gui.RuudunTila;
import com.mycompany.tiralabra_maven.gui.Ruutu;
import com.mycompany.tiralabra_maven.logiikka.tietorakenteet.PrioriteettiKeko;
import java.util.Comparator;

/**
 * Luokka, joka toteuttaa ahneen "paras ensin" -haun.
 *
 * @author mikko
 */
public class GreedyBestFirstAlgoritmi extends Algoritmi {

    private Heuristiikka heuristiikka;
    private final PrioriteettiKeko<Solmu> tutkittavat;
    private Solmu tutkittavaSolmu;

    /**
     * Luo uuden algoritmin instanssin. Yksi luotu algotitmiolio voidaan
     * suorittaa vain kerran.
     *
     * @param maailma algoritmin toimintaympäristö, joka sisältää tiedon
     * ruutujen kustannuksista
     * @param hidaste odotetaan näin monta millisekuntia jokaisen algoritmin
     * suoritusaskeleen välillä.
     * @param alkuKoord alkupisteen koordinaatit
     * @param maaliKoord maalipisteen koordinaatit
     * @param vinottain sallitaanko liikkuminen vinottain
     * @param h käytettävä heuristiikka
     */
    public GreedyBestFirstAlgoritmi(Ruutu[][] maailma, int hidaste, Koordinaatit alkuKoord, Koordinaatit maaliKoord, boolean vinottain, Heuristiikka h) {
        super(maailma, hidaste, alkuKoord, maaliKoord, vinottain);
        this.heuristiikka = h;

        Comparator<Solmu> vertailija = new Comparator<Solmu>() {
            @Override
            public int compare(Solmu s1, Solmu s2) {
                if (heuristiikka.arvioiMatkaMaaliin(s1.getKoord(), maali) < heuristiikka.arvioiMatkaMaaliin(s2.getKoord(), maali)) {
                    return -1;
                } else if (heuristiikka.arvioiMatkaMaaliin(s1.getKoord(), maali) == heuristiikka.arvioiMatkaMaaliin(s2.getKoord(), maali)) {
                    return 0;
                }
                return 1;
                //heuristiikka.arvioiMatkaMaaliin(s1.getKoord(), maali) - heuristiikka.arvioiMatkaMaaliin(s2.getKoord(), maali);
            }
        };

        this.tutkittavat = new PrioriteettiKeko<>(vertailija);
    }

    /**
     * Käynnistää hakualgoritmin suorituksen.
     */
    @Override
    public void run() {
        tutkittavat.lisaa(new Solmu(alku, 0, null));

        while (!tutkittavat.tyhja() && jatketaanko) {

            //Otetaan jonosta solmu
            tutkittavaSolmu = tutkittavat.seuraava();

            //Jos ollaan maalissa, lopetetaan tähän
            if (tutkittavaSolmu.getKoord().equals(maali)) {
                maaliLoytyi(tutkittavaSolmu);
                break;
            }

            //Muussa tapauksessa merkitään solmu nyt käsittelyssä olevaksi
            ruutujenTilat[tutkittavaSolmu.getKoord().getY()][tutkittavaSolmu.getKoord().getX()] = RuudunTila.KASITTELYSSA;

            //Odotetaan mahdollisen viiveen verran aikaa ennen jatkamista
            super.odota();

            //Käydään läpi solmun naapurit
            for (Solmu s : solmunNaapurit(tutkittavaSolmu)) {

                //Jos tässä ruudussa on jo käyty, ei käsitellä tätä enää
                if (ruutujenTilat[s.getKoord().getY()][s.getKoord().getX()] != null) {
                    continue;
                }

                //Muussa tapauksessa lisätään solmun naapuri tutkittaviin
                ruutujenTilat[s.getKoord().getY()][s.getKoord().getX()] = RuudunTila.TUTKITTAVA;
                //tutkittavat.lisaa(new Solmu(s.getKoord(), tutkittavaSolmu.getKuljettuMatka() + maailma[s.getKoord().getY()][s.getKoord().getX()].getHinta(), tutkittavaSolmu));
                tutkittavat.lisaa(s);
            }

            //Lopuksi merkitään tämä solmu tutkituksi
            ruutujenTilat[tutkittavaSolmu.getKoord().getY()][tutkittavaSolmu.getKoord().getX()] = RuudunTila.TUTKITTU;
        }

    }

}
