/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.logiikka.algoritmi;

import com.mycompany.tiralabra_maven.Koordinaatit;
import com.mycompany.tiralabra_maven.gui.RuudunTila;
import com.mycompany.tiralabra_maven.gui.Ruutu;
import com.mycompany.tiralabra_maven.logiikka.tietorakenteet.PrioriteettiKeko;

/**
 *
 * @author mikko
 */
public class DijkstraAlgoritmi extends Algoritmi {

    private PrioriteettiKeko<Solmu> tutkittavat;
    private int[][] parhaatReitit;
    private Solmu tutkittavaSolmu;

    public DijkstraAlgoritmi(Ruutu[][] maailma, int hidaste, Koordinaatit alku, Koordinaatit maali, boolean vinottain) {
        super(maailma, hidaste, alku, maali, vinottain);
        this.parhaatReitit = new int[korkeus][leveys];
        this.tutkittavat = new PrioriteettiKeko<>();
        alustaParhaatReitit();
    }

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

                //Jos tähän ruutuun on jo päästy vähintään yhtä hyvää reittiä pitkin, ei käsitellä tätä enää
                if (parhaatReitit[s.getKoord().getY()][s.getKoord().getX()] != -1 && parhaatReitit[s.getKoord().getY()][s.getKoord().getX()] <= s.getKuljettuMatka()) {
                    continue;
                }
                //Muussa tapauksessa päivitetään paras reitti ja lisätään tämä tutkittaviin
                parhaatReitit[s.getKoord().getY()][s.getKoord().getX()] = s.getKuljettuMatka();

                ruutujenTilat[s.getKoord().getY()][s.getKoord().getX()] = RuudunTila.TUTKITTAVA;
                //tutkittavat.lisaa(new Solmu(s.getKoord(), tutkittavaSolmu.getKuljettuMatka() + maailma[s.getKoord().getY()][s.getKoord().getX()].getHinta(), tutkittavaSolmu));
                tutkittavat.lisaa(s);

            }

            //Lopuksi merkitään tämä solmu tutkituksi
            ruutujenTilat[tutkittavaSolmu.getKoord().getY()][tutkittavaSolmu.getKoord().getX()] = RuudunTila.TUTKITTU;
        }

    }

    private void alustaParhaatReitit() {
        for (int x = 0; x < this.leveys; x++) {
            for (int y = 0; y < this.korkeus; y++) {
                parhaatReitit[y][x] = -1;
            }
        }
    }

}
