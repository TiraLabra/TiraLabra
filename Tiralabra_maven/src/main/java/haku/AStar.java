/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haku;

import tira.PrioriteettiJono;
import tira.Vertailija;
import verkko.Kaari;
import verkko.Solmu;
import verkko.Verkko;

/**
 * A*-haun toteutus
 * 
 * @author E
 */
public class AStar {

    /*
    Entä jos alkutila/lopputila ei ole verkossa: esim. pysäkkiverkkoon päästään kävelemällä pysäkille
    */
    
    /**
     * Etsii a*-haulla verkosta parhaan reitin alkusolmusta loppusolmuun.
     *
     * @param verkko
     * @param maali
     * @param alku
     * @param heuristiikka
     * @return
     */
    public Reitti etsiReitti(Verkko verkko, Solmu alku, Solmu maali, Heuristiikka heuristiikka) {
        // 
        Reitti alkuTila = new Reitti();
        alkuTila.setKustannus(0);
        alkuTila.setSolmu(alku);
        alkuTila.setArvioituKustannus( heuristiikka.arvio(verkko,alku,maali));
        // alustetaan käsittelyjärjestystä varten jono
        PrioriteettiJono<Reitti> kasittelyJarjestys = new PrioriteettiJono();
        Vertailija<Reitti> vertailija = new Vertailija() {
            public double vertaa(Object e1, Object e2) {
                Reitti t1 = (Reitti)e1;
                Reitti t2 = (Reitti)e2;
                return t1.getArvioituKustannus()+t1.getKustannus()-t2.getArvioituKustannus()-t2.getKustannus();
            }
        };
        kasittelyJarjestys.setVertailija( vertailija );
        kasittelyJarjestys.enqueue(alkuTila);
        
        while (!kasittelyJarjestys.isEmpty()) {           // kaivannee katkaisun johonkin kohtaan: entä jos verkko ei yhtenäinen
            Reitti kasiteltava = kasittelyJarjestys.poll(); // otetaan jonon 1. pois käsiteltäväksi
            Solmu solmu = kasiteltava.getSolmu();

            if (solmu.equals(maali)) { // ollaan maalissa, tämä on paras ratkaisu
                return kasiteltava;
            }

            for ( Solmu naapuri : verkko.getNaapurit(solmu)) {
                /*
                Kaarten käsittely: 
                * voi olla useita mahdollisia kaaria: esim. tien yli voi mennä, tai alikulun kautta, pysäkiltä toiselle pääsee monella bussilla
                -> Kaaret listana
                * entä jos kustannus vaihtuu sen mukaan, milloin mennään (esim. bussipysäkillä odotusaika)
                -> Tilaan aika-muuttuja, kaareen tieto siitä miten vaihtelee ajan mukaan
                * entä jos edellinen kaari vaikuttaa kustannukseen ( esim. ollaan jo bussissa matkalla eteenpäin, esim2. autolla suoraan: ei tarvitse u-käännöstä yms)
                -> Kaareen tieto tyypistä                
                */
                for ( Kaari kaari : verkko.getKaaret(solmu, naapuri)) {
                    Reitti seuraava = new Reitti();
                    seuraava.setKustannus(kasiteltava.getKustannus() + kaari.getKustannus( /*kasiteltava tila, edellinen kaari*/ ));
                    seuraava.setArvioituKustannus(heuristiikka.arvio(verkko, naapuri, maali));
                    seuraava.setPrevious(kasiteltava);
                    seuraava.setKuljettuKaari(kaari);
                    kasittelyJarjestys.enqueue(seuraava);
                }
            }
        }

        return null;
    }

}
