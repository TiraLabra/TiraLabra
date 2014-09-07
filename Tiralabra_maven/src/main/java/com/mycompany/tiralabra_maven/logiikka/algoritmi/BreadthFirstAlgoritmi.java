package com.mycompany.tiralabra_maven.logiikka.algoritmi;

import com.mycompany.tiralabra_maven.Koordinaatit;
import com.mycompany.tiralabra_maven.gui.RuudunTila;
import com.mycompany.tiralabra_maven.gui.Ruutu;
import com.mycompany.tiralabra_maven.logiikka.tietorakenteet.Jono;

/**
 * Reittialgoritmi, joka toteuttaa leveyssuuntaisen haun.
 *
 * @author mikko
 */
public class BreadthFirstAlgoritmi extends Algoritmi {

    private final Jono<Solmu> tutkittavat;
    private Solmu tutkittavaSolmu;

    /**
     * Luo uuden algoritmin instanssin. Yksi luotu algotitmiolio voidaan
     * suorittaa vain kerran.
     *
     * @param maailma algoritmin toimintaympäristö, joka sisältää tiedon
     * ruutujen kustannuksista
     * @param hidaste odotetaan näin monta millisekuntia jokaisen algoritmin
     * suoritusaskeleen välillä.
     * @param alku alkupisteen koordinaatit
     * @param maali maalipisteen koordinaatit
     * @param vinottain sallitaanko liikkuminen vinottain
     */
    public BreadthFirstAlgoritmi(Ruutu[][] maailma, int hidaste, Koordinaatit alku, Koordinaatit maali, boolean vinottain) {
        super(maailma, hidaste, alku, maali, vinottain);
        this.tutkittavat = new Jono<>();
    }

    /**
     * Käynnistää hakualgoritmin suorituksen.
     */
    @Override
    public void run() {
        tutkittavat.lisaa(new Solmu(alku, 0, null));

        while (!tutkittavat.tyhja() && jatketaanko) {
            //Otetaan jonosta solmu
            tutkittavaSolmu = tutkittavat.otaJonosta();

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
                tutkittavat.lisaa(s);
            }

            //Lopuksi merkitään tämä solmu tutkituksi
            ruutujenTilat[tutkittavaSolmu.getKoord().getY()][tutkittavaSolmu.getKoord().getX()] = RuudunTila.TUTKITTU;
        }

    }

}
