/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.algoritmi;

import com.mycompany.tiralabra_maven.Koordinaatit;
import com.mycompany.tiralabra_maven.gui.RuudunTila;
import com.mycompany.tiralabra_maven.gui.Ruutu;
import com.mycompany.tiralabra_maven.tietorakenteet.PrioriteettiKeko;

/**
 *
 * @author mikko
 */
public class GreedyBestFirstAlgoritmi extends Algoritmi {

    private Heuristiikka heuristiikka;
    private PrioriteettiKeko<Solmu> tutkittavat;
    private Solmu tutkittavaSolmu;

    public GreedyBestFirstAlgoritmi(Ruutu[][] maailma, int hidaste, Koordinaatit alku, Koordinaatit maali, boolean vinottain, Heuristiikka heuristiikka) {
        super(maailma, hidaste, alku, maali, vinottain);
        this.heuristiikka = heuristiikka;
        GreedyVertailija vertailija = new GreedyVertailija(this.heuristiikka, maali);
        this.tutkittavat = new PrioriteettiKeko<>(vertailija);
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
                return;
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
                tutkittavat.lisaa(new Solmu(s.getKoord(), tutkittavaSolmu.getKuljettuMatka() + maailma[s.getKoord().getY()][s.getKoord().getX()].getHinta(), tutkittavaSolmu));
            }

            //Lopuksi merkitään tämä solmu tutkituksi
            ruutujenTilat[tutkittavaSolmu.getKoord().getY()][tutkittavaSolmu.getKoord().getX()] = RuudunTila.TUTKITTU;
        }

    }

}
