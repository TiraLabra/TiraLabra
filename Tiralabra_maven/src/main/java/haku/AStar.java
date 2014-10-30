/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haku;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import verkko.Kaari;
import verkko.Pysakki;
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
     * @param laskin Vastaa sekä kustannuksen että heuristisen arvion ja uuden
     * käsiteltävän reitin luonnista tekemisestä
     * @return
     */
    public Reitti etsiReitti(Verkko verkko, Pysakki alku, Pysakki maali, ReittiLaskin laskin) {
        laskin.setVerkko(verkko);
        // 
        Reitti alkuTila = new Reitti();
        alkuTila.setKustannus(0);
        alkuTila.setSolmu(alku);
        alkuTila.setArvioituKustannus(laskin.heuristiikka(alku, maali));
        // alustetaan käsittelyjärjestystä varten jono
        /*
         PrioriteettiJono<Reitti> kasittelyJarjestys = new PrioriteettiJono();
         Vertailija<Reitti> vertailija = new Vertailija() {
         public double vertaa(Object e1, Object e2) {
         Reitti t1 = (Reitti) e1;
         Reitti t2 = (Reitti) e2;
         return t1.getArvioituKustannus() + t1.getKustannus() - t2.getArvioituKustannus() - t2.getKustannus();
         }
         };
         kasittelyJarjestys.setVertailija(vertailija);
         kasittelyJarjestys.add(alkuTila);
         */
        PriorityQueue<Reitti> kasittelyJarjestys = new PriorityQueue(new Comparator<Reitti>() {

            public int compare(Reitti t1, Reitti t2) {
                return (int) (t1.getArvioituKustannus() + t1.getKustannus() - t2.getArvioituKustannus() - t2.getKustannus());
            }
        });
        kasittelyJarjestys.add(alkuTila);
        // kuljetut kaaret talteen, ettei vahingossa käydä läpi useasti
        ArrayList<String> kasitellytKaaret = new ArrayList();

        while (!kasittelyJarjestys.isEmpty()) {           // kaivannee katkaisun johonkin kohtaan: entä jos verkko ei yhtenäinen
            System.out.println(""+kasittelyJarjestys.size());
            Reitti kasiteltava = kasittelyJarjestys.poll(); // otetaan jonon 1. pois käsiteltäväksi
            Pysakki solmu = kasiteltava.getSolmu();
            System.out.println("{ " + kasiteltava.getSolmu().getKoodi() + " t:" + kasiteltava.getAika() + " d:" + kasiteltava.getMatka() + " est:" + kasiteltava.getArvioituKustannus() + " }");
            if (solmu.equals(maali)) { // ollaan maalissa, tämä on paras ratkaisu ( jos heuristiikka toimii )
                return kasiteltava;
            }
            for (Pysakki naapuri : verkko.getNaapurit(solmu)) {
                /*
                 Kaarten käsittely: 
                 * voi olla useita mahdollisia kaaria: esim. tien yli voi mennä, tai alikulun kautta, pysäkiltä toiselle pääsee monella bussilla
                 -> Kaaret listana OK!
                 * entä jos kustannus vaihtuu sen mukaan, milloin mennään (esim. bussipysäkillä odotusaika)
                 -> Tilaan aika-muuttuja, kaareen tieto siitä miten vaihtelee ajan mukaan OK!
                 * entä jos edellinen kaari vaikuttaa kustannukseen ( esim. ollaan jo bussissa matkalla eteenpäin, esim2. autolla suoraan: ei tarvitse u-käännöstä yms)
                 -> Kaareen tieto tyypistä OK!               
                 */
                for (Kaari kaari : verkko.getKaaret(solmu, naapuri)) {
                    // samaa kaarta ei kannata kulkea kahdesti
                    if (kasitellytKaaret.contains(kaari.toString())) {
                        // System.out.println("HUPS");
                        continue;
                    }
                    kasitellytKaaret.add(kaari.toString());
                    // reittilaskin luo seuraavan
                    Reitti seuraava = laskin.laskeSeuraava(kasiteltava, kaari, naapuri, maali);
                    // jonoon:
                    kasittelyJarjestys.add(seuraava);
                }
            }
        }

        return null;
    }

}
