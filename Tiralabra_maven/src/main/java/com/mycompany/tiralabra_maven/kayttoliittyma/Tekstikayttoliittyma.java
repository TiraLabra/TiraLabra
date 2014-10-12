package com.mycompany.tiralabra_maven.kayttoliittyma;

import com.mycompany.tiralabra_maven.tietorakenteet.PuuOperaatiot;
import com.mycompany.tiralabra_maven.tyokalut.*;
import java.util.Random;

/**
 * Ohjelman käyttöliittymä ja tekstin tulostuksesta vastaavat metodit
 *
 * @author Markus
 */
public class Tekstikayttoliittyma {

    private PuunTutkija tutkija;
    String[] kuvaukset;
    int[][] datajoukot;

    public Tekstikayttoliittyma(PuunTutkija tutkija) {
        this.tutkija = tutkija;
        alustaTietojoukot();
    }

    /**
     * Käynnistää tekstipohjaisen käyttöliittymän
     */
    public void kaynnista() {
        tulostaSekuntikellonTiedot();
        tulostaVertailut();
    }

    /**
     * Alustaa kuvaukset ja vastaavat tietojoukot
     */
    private void alustaTietojoukot() {

        int joukkoja = 3;

        kuvaukset = new String[joukkoja];
        datajoukot = new int[joukkoja][];

        int nro = 0;
        int koko = 2000;

        // Kaskavat arvot
        kuvaukset[nro] = "Alkiota kasvavassa järjestyksessä";
        datajoukot[nro] = new int[koko];
        for (int i = 0; i < koko; i++) {
            datajoukot[nro][i] = i + 1;
        }

//        nro++;
//        // Vähenevät arvot
//        kuvaukset[nro] = "Alkiota laskevassa järjestyksessä";
//        datajoukot[nro] = new int[koko];
//        for (int i = 0; i < koko; i++) {
//            datajoukot[nro][i] = 100 - i;
//        }
        nro++;
        //Loittonevat arvot
        kuvaukset[nro] = "Alkiot alusta loittonevassa järjestyksessä (esim. 5,6,4,7,3...)";
        datajoukot[nro] = new int[koko];
        int alku = 1000;
        datajoukot[nro][0] = alku;
        for (int i = 1; i < koko; i++) {
            if (i % 2 == 1) {
                datajoukot[nro][i] = alku + i;
            } else {
                datajoukot[nro][i] = alku - i;
            }
        }

        nro++;
        // Alkioita sattumanvaraisessa järjestyksessä
        kuvaukset[nro] = "Sattumanvaraisia alkioita";
        datajoukot[nro] = new int[koko];
        Random rnd = new Random();
        datajoukot[nro] = datajoukot[0].clone();
        for (int i = koko - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int a = datajoukot[nro][index];
            datajoukot[nro][index] = datajoukot[nro][i];
            datajoukot[nro][i] = a;
        }
    }

    /**
     * Tulostaa yleisen mittaustarkkuuden virheen.
     */
    private void tulostaSekuntikellonTiedot() {
        System.out.println("Keskimääräinen mittausvirhe: " + tutkija.virhemarginaali());
    }

    /**
     * Suorittaa ja tulostaa kaikki vertailut
     */
    private void tulostaVertailut() {
        for (int i = 0; i < kuvaukset.length; i++) {
            System.out.println(kuvaukset[i] + "\nJoukossa " + datajoukot[i].length + " alkiota.");
            tulostaLisaysvertailu(tutkija.lisaysVertailu("Lisäys:", datajoukot[i]));
            tulostaVertailu(tutkija.hakuVertailu("Haku:", datajoukot[i]));
            tulostaVertailu(tutkija.poistoVertailu("Poisto:", datajoukot[i]));
            tutkija.tyhjennaPuut();
        }

    }

    /**
     * Tulostaa vertalu-olion sisällön. Tarkoitettu käytettäväksi
     * lisäysoperaatioista saatavien vertailujen kanssa.
     *
     * @param vertailu Tulostettava vertailu
     */
    private void tulostaLisaysvertailu(Vertailu vertailu) {
        System.out.println(vertailu.getKuvaus());
        System.out.printf("%-20s %-10s %-10s %-10s %-7s\n", "Puu:", "Pienin:", "suurin:", "Keskiarvo:", "Korkeus:");
        for (Mittaustulos mittaustulos : vertailu.getTulokset()) {
            System.out.printf("%20s %10s %10s %10s %7s\n", mittaustulos.getPuu().getNimi(), mittaustulos.getPienin(), mittaustulos.getSuurin(), mittaustulos.getKeskiarvo(), PuuOperaatiot.korkeus(mittaustulos.getPuu().getJuuri()));
        }
        System.out.println("");
    }

    /**
     * Tulostaa vertalu-olion sisällön. Tarkoitettu käytettäväksi haku- tai
     * poisto-operaatioista saatavien vertailujen kanssa.
     *
     * @param vertailu Tulostettava vertailu
     */
    private void tulostaVertailu(Vertailu vertailu) {
        System.out.println(vertailu.getKuvaus());
        System.out.printf("%-20s %-10s %-10s %-10s\n", "Puu:", "Pienin:", "suurin:", "Keskiarvo:");
        for (Mittaustulos mittaustulos : vertailu.getTulokset()) {
            System.out.printf("%20s %10s %10s %10s\n", mittaustulos.getPuu().getNimi(), mittaustulos.getPienin(), mittaustulos.getSuurin(), mittaustulos.getKeskiarvo());
        }
        System.out.println("");
    }
}
