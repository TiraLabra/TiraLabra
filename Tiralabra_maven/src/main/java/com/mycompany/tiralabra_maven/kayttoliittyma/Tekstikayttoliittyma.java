package com.mycompany.tiralabra_maven.kayttoliittyma;

import com.mycompany.tiralabra_maven.tyokalut.*;

/**
 * Ohjelman käyttöliittymä ja tekstin tulostuksesta vastaavat metodit.
 * Käyttöliittymä kutsuu saamansa Puuntutkijan metodeja ja tulostaa saatuja
 * mittaustulos-olioita.
 *
 * @author Markus
 */
public class Tekstikayttoliittyma {

    /**
     * Puuntutkija, jolle on annettu tutkittavat puut.
     */
    private final PuunTutkija tutkija;

    /**
     * Lista, joka sisältää puuntutkijalle annettavat kokonaislukutaulukot.
     */
    LinkitettyLista datajoukot;

    /**
     * Alustaa Listan ja asettaa parametrina saadun puuntutkijan.
     *
     * @param tutkija Asetettava Puuntutkija.
     */
    public Tekstikayttoliittyma(PuunTutkija tutkija) {
        this.tutkija = tutkija;
        datajoukot = new LinkitettyLista();
    }

    /**
     * Käynnistää tekstipohjaisen käyttöliittymän.
     */
    public void kaynnista() {
        /*
         Parametrina kokonaislukuja, jotka ovat luotavien tietojoukkojen alkioiden määrät.
         Jokaisen täytyy olla unsigned Integer. Koodi ei tarkista parametrien oikeellisuutta.
         Muuta näitä arvoja jos haluat tulosteen sisältävän mittauksia muun suuruisilla tietojoukoilla.
         */
        alustaTietojoukot(200, 400, 800, 1600, 3200);

        /*
         Kertoo kuinka monta kertaa kukin mittaus ajetaan.
         Puuntutkijan palauttamat mittaustulos-oliot sisältävät kerkiarvon näistä mitatuista ajoista.
         Täytyy olla positiivinen kokonaisluku. (0 < x <Integer.MAX_VALUE).
         Ohjelman suoritusaika suurilla arvoilla kasvaa merkittävästi.
         */
        tutkija.setToistoja(100);

        //Ajaa kaikki testit ja tulostaa suoritusajat luoduilla tietojoukoilla.
        tulostaVertailut();
    }

    /**
     * Suorittaa ja tulostaa kaikki mittaukset kaikilla tietojoukoilla.
     */
    private void tulostaVertailut() {
        tulostaLisaysVertailut();
        tulostaHakuvertailut();
        tulostaPoistovertailut();
    }

    /**
     * Tulostaa kaikki lisäys-operaatioihin liityvät mittaustulokset taulukoina.
     */
    private void tulostaLisaysVertailut() {
        System.out.println("LISAÄYSAJAT:\n");

        System.out.println("Lisätään kaikki alkiot puuhun kasvavassa järjestyksessä:");
        tulostaTaulukonAvain();
        for (Solmu solmu : datajoukot) {
            tulostaVertailu(tutkija.lisaysVertailu(solmu.getData().clone()));
        }
        tulostaViivarivi(240);

        System.out.println("Lisätään kaikki alkiot puuhun sattumanvaraisessa järjestyksessä:");
        tulostaTaulukonAvain();
        for (Solmu solmu : datajoukot) {
            tulostaVertailu(tutkija.lisaysVertailuRand(solmu.getData().clone()));
        }
        tulostaViivarivi(240);

        tulostaViivarivi(240);

    }

    /**
     * Tulostaa kaikki haku-operaatioihin liityvät mittaustulokset taulukkona.
     */
    private void tulostaHakuvertailut() {
        System.out.println("HAKUAJAT:\n");

        System.out.println("Haetaan kukin puussa esiintyvä alkio lisäysjärjestyksessä");
        tulostaTaulukonAvain();
        for (Solmu solmu : datajoukot) {
            tulostaVertailu(tutkija.hakuVertailu(solmu.getData().clone()));
        }
        tulostaViivarivi(240);

        System.out.println("Haetaan kukin puussa esiintyvä alkio sattumanvaraisessa järjestyksessä");
        tulostaTaulukonAvain();
        for (Solmu solmu : datajoukot) {
            tulostaVertailu(tutkija.hakuVertailuRand(solmu.getData().clone()));
        }
        tulostaViivarivi(240);

        System.out.println("Haetaan kukin puussa esiintyvä alkio käänteisessä järjestyksessä");
        tulostaTaulukonAvain();
        for (Solmu solmu : datajoukot) {
            tulostaVertailu(tutkija.hakuVertailuKaanteinen(solmu.getData().clone()));
        }
        tulostaViivarivi(240);

        tulostaViivarivi(240);

    }

    /**
     * Tulostaa kaikki poisto-operaatioihin liityvät mittaustulokset taulukkona.
     */
    private void tulostaPoistovertailut() {
        System.out.println("POISTOAJAT:\n");

        System.out.println("Poistetaan kukin alkio lisäysjärjestyksessä");
        tulostaTaulukonAvain();
        for (Solmu solmu : datajoukot) {
            tulostaVertailu(tutkija.poistoVertailu(solmu.getData().clone()));
        }
        tulostaViivarivi(240);

        System.out.println("Poistetaan kukin alkio sattumanvaraisessa järjestyksessä");
        tulostaTaulukonAvain();
        for (Solmu solmu : datajoukot) {
            tulostaVertailu(tutkija.poistoVertailuRand(solmu.getData().clone()));
        }
        tulostaViivarivi(240);

        System.out.println("Poistetaan kukin alkio käänteisessä järjestyksessä");
        tulostaTaulukonAvain();
        for (Solmu solmu : datajoukot) {
            tulostaVertailu(tutkija.poistoVertailuKaanteinen(solmu.getData().clone()));
        }
        tulostaViivarivi(240);

        tulostaViivarivi(240);

    }

    /**
     * Tulostaa kunkin saadun Mittaustulostaulukon mittaustuloksen arvot.
     * Muodostaa täten yhden taulukon rivin.
     *
     * @param tulokset Tulostettavat mittaustulokset sisältävä taulukko.
     */
    private void tulostaVertailu(Mittaustulos[] tulokset) {
        for (Mittaustulos mittaustulos : tulokset) {
            System.out.printf("%20s%7d%12d%12d%7d |", mittaustulos.getNimi(), mittaustulos.getKoko(), (mittaustulos.getKeskiarvo() / mittaustulos.getKoko()), mittaustulos.getKeskiarvo(), mittaustulos.getKorkeus());
        }
        System.out.println("");
    }

    /**
     * Tulostaa parametrina saadun luvun verra '-' merkkejä. Käytetään taulukon
     * selkeyttämiseen.
     *
     * @param pituus Piirrettävien '-' merkkien lukumäärä.
     */
    private void tulostaViivarivi(int pituus) {
        for (int j = 0; j < pituus; j++) {
            System.out.print("-");
        }
        System.out.println("\n");
    }

    /**
     * Alustaa Parametrinä saadun kokoiset taulukot alkioita ja lisää ne
     * listaan. Taulukot alustetaan kokonaisluvuilla 0..(koko-1) kasvavassa
     * järjestyksessä.
     *
     * @param koot Rakennettavien taulukoiden koot.
     */
    private void alustaTietojoukot(int... koot) {
        int[] data;
        for (int i : koot) {
            data = new int[i];
            for (int j = 0; j < i; j++) {
                data[j] = j;
            }
            datajoukot.lisaa(data);
        }
    }

    /**
     * Tulostaa selityksen taulukon tietojen muotoilusta.
     */
    private void tulostaTaulukonAvain() {
        System.out.printf("%-20s%-7s%-12s%-12s%-7s\n", "Puu:", "koko:", "Keskiarvo:", "Yhteensä:", "Korkeus:");
    }
}
