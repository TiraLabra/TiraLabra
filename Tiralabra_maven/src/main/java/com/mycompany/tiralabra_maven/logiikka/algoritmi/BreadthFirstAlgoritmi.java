/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven.logiikka.algoritmi;

import com.mycompany.tiralabra_maven.Koordinaatit;
import com.mycompany.tiralabra_maven.gui.RuudunTila;
import com.mycompany.tiralabra_maven.gui.Ruutu;
import com.mycompany.tiralabra_maven.logiikka.tietorakenteet.Jono;

/**
 *
 * @author mikko
 */
public class BreadthFirstAlgoritmi extends Algoritmi{
    private Jono<Solmu> tutkittavat;
    private Solmu tutkittavaSolmu;

    public BreadthFirstAlgoritmi(Ruutu[][] maailma, int hidaste, Koordinaatit alku, Koordinaatit maali, boolean vinottain) {
        super(maailma, hidaste, alku, maali, vinottain);
        this.tutkittavat = new Jono<>();
    }

    @Override
    public void run() {
        tutkittavat.lisaa(new Solmu(alku, 0, null));
        
        while (!tutkittavat.tyhja() && jatketaanko) {
            //Otetaan jonosta solmu
            tutkittavaSolmu = tutkittavat.otaJonosta();
            
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
            for (Solmu s: solmunNaapurit(tutkittavaSolmu)) {
                //Jos tässä ruudussa on jo käyty, ei käsitellä tätä enää
                if (ruutujenTilat[s.getKoord().getY()][s.getKoord().getX()] != null) {
                    continue;
                }
                //Muussa tapauksessa lisätään solmun naapuri tutkittaviin
                ruutujenTilat[s.getKoord().getY()][s.getKoord().getX()] = RuudunTila.TUTKITTAVA;
                tutkittavat.lisaa(new Solmu(s.getKoord(), tutkittavaSolmu.getKuljettuMatka()+maailma[s.getKoord().getY()][s.getKoord().getX()].getHinta(), tutkittavaSolmu));
            }
            
            //Lopuksi merkitään tämä solmu tutkituksi
            ruutujenTilat[tutkittavaSolmu.getKoord().getY()][tutkittavaSolmu.getKoord().getX()] = RuudunTila.TUTKITTU;
        }
        
    }
    
}
