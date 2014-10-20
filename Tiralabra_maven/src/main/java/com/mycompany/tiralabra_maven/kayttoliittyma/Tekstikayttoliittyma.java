package com.mycompany.tiralabra_maven.kayttoliittyma;

import com.mycompany.tiralabra_maven.tyokalut.*;
import java.util.Random;

/**
 * Ohjelman käyttöliittymä ja tekstin tulostuksesta vastaavat metodit
 *
 * @author Markus
 */
public class Tekstikayttoliittyma {

    private PuunTutkija tutkija;
    LinkitettyLista[] datajoukot;

    public Tekstikayttoliittyma(PuunTutkija tutkija) {
        this.tutkija = tutkija;
    }

    /**
     * Käynnistää tekstipohjaisen käyttöliittymän
     */
    public void kaynnista() {
        datajoukot = new LinkitettyLista[2];
        alustaTietojoukot();
        tulostaVertailut();
    }

    /**
     * Suorittaa ja tulostaa kaikki vertailut
     */
    private void tulostaVertailut() {
        tulostaLisaysVertailut();
//        tulostaHakuvertailut();
//        tulostaPoistovertailut();
    }

    private void tulostaLisaysVertailut() {
        System.out.println("LISAÄYSAJAT:\n");
        for (int i = 0; i < datajoukot.length; i++) {
            LinkitettyLista joukot = datajoukot[i];
            System.out.println(joukot.getKuvaus());
            System.out.printf("%-20s%-7s%-10s%-12s%-12s%-7s\n", "Puu:", "koko:", "suurin:", "Keskiarvo:", "Yhteensä:", "Korkeus:");
            for (Solmu solmu : joukot) {
                tulostaLisaysvertailu(tutkija.lisaysVertailu(solmu.getData()));
                System.out.println("");
                tutkija.tyhjennaPuut();
            }
            System.out.println("");
        }
    }

    /**
     * Tulostaa vertalu-olion sisällön. Tarkoitettu käytettäväksi
     * lisäysoperaatioista saatavien vertailujen kanssa.
     *
     * @param vertailu Tulostettava vertailu
     */
    private void tulostaLisaysvertailu(Vertailu vertailu) {
        for (Mittaustulos mittaustulos : vertailu.getTulokset()) {
            System.out.printf("%20s%7d%10d%12d%12d%7d", mittaustulos.getPuu().getNimi(), mittaustulos.getPuu().getKoko(), mittaustulos.getSuurin(), mittaustulos.getKeskiarvo(), mittaustulos.getKokonaisaika(), mittaustulos.getPuu().getKorkeus());
        }
    }

    private void tulostaHakuvertailut() {
        System.out.println("HAKUAJAT:\n");
        for (int i = 0; i < datajoukot.length; i++) {
            LinkitettyLista joukot = datajoukot[i];
            System.out.println(joukot.getKuvaus() + " Tietyn alkion hakuajat");
            System.out.printf("%-20s%-7s%-10s%-12s%-12s%-7s\n", "Puu:", "koko:", "suurin:", "Keskiarvo:", "Yhteensä:", "Korkeus:");
            for (Solmu solmu : joukot) {
                tutkija.rakennaPuut(solmu.getData());
                tulostaHakuvertailu(tutkija.hakuVertailu(0));
                System.out.println("");
                tutkija.tyhjennaPuut();
            }
            System.out.println("");
        }
    }

    private void tulostaHakuvertailu(Vertailu vertailu) {
        for (Mittaustulos mittaustulos : vertailu.getTulokset()) {
            System.out.printf("%20s%7d%10d%12d%12d%7d", mittaustulos.getPuu().getNimi(), mittaustulos.getPuu().getKoko(), mittaustulos.getSuurin(), mittaustulos.getKeskiarvo(), mittaustulos.getKokonaisaika(), mittaustulos.getPuu().getKorkeus());
        }
    }

    private void tulostaPoistovertailut() {
        System.out.println("POISTOAJAT:\n");
        for (int i = 0; i < datajoukot.length; i++) {
            LinkitettyLista joukot = datajoukot[i];
            System.out.println(joukot.getKuvaus() + " Joukon tyhjennys poistooperaatioilla");
            System.out.printf("%-20s%-7s%-10s%-12s%-12s%-7s\n", "Puu:", "koko:", "suurin:", "Keskiarvo:", "Yhteensä:", "Korkeus:");
            for (Solmu solmu : joukot) {
                tutkija.rakennaPuut(solmu.getData());
                tulostaPoistovertailu(tutkija.poistoVertailu(solmu.getData()));
                System.out.println("");
                tutkija.tyhjennaPuut();
            }
            System.out.println("");
        }
    }

    private void tulostaPoistovertailu(Vertailu vertailu) {
        for (Mittaustulos mittaustulos : vertailu.getTulokset()) {
            System.out.printf("%20s%7d%10d%12d%12d%7d", mittaustulos.getPuu().getNimi(), mittaustulos.getPuu().getKoko(), mittaustulos.getSuurin(), mittaustulos.getKeskiarvo(), mittaustulos.getKokonaisaika(), mittaustulos.getPuu().getKorkeus());
        }
    }

    /**
     * Alustaa kuvaukset ja vastaavat tietojoukot
     */
    private void alustaTietojoukot() {
        int[] koot = {100, 200, 400};

        datajoukot[0] = new LinkitettyLista();
        datajoukot[0].setKuvaus("Alkiota kasvavassa järjestyksessä:");
        int[] data;
        for (int i : koot) {
            data = new int[i];
            for (int j = 0; j < i; j++) {
                data[j] = j;
            }
            datajoukot[0].lisaa(data);
        }

        datajoukot[1] = new LinkitettyLista();
        datajoukot[1].setKuvaus("Sattumanvaraisia alkioita:");
        Random rnd = new Random();
        for (int i : koot) {
            data = new int[i];
            for (int j = 0; j < i; j++) {
                data[j] = j;
            }
            for (int j = 0; j < i - 1; j++) {
                int index = rnd.nextInt(i - j - 1);
                int a = data[j];
                data[j] = data[j + index];
                data[j + index] = a;
            }
            datajoukot[1].lisaa(data);
        }

        tutkija.rakennaPuut(datajoukot[0].iterator().next().getData());
        tutkija.tyhjennaPuut();
    }

}
