package com.mycompany.tiralabra_maven.tyokalut;

import com.mycompany.tiralabra_maven.tietorakenteet.*;

/**
 * Puiden suorituskyvyn analysointiin liittyviä toimintoja sisältävä luokka
 *
 * @author Markus
 */
public class PuunTutkija {

    final private Sekuntikello kello;
    private Hakupuu[] puut;

    public PuunTutkija(Hakupuu... puut) {
        this.puut = puut;
        this.kello = new Sekuntikello();
    }

    /**
     * Palauttaa puun lisäysoperaatioon kuluvan ajan
     *
     * @param puu Puu josta alkiota haetaan
     * @param lisattava Lisättävän alkion arvo
     * @return Lisäykseen kulunut aika
     */
    public long lisaysaika(Hakupuu puu, int lisattava) {
        kello.aloita();
        puu.lisaa(lisattava);
        return kello.lopeta();
    }

    /**
     * Mittaa kaikkien lisättävien arvojen lisäysajat ja palauttaa näistä
     * tilastoja sisältävän olion
     *
     * @param puu Puu johon arvot lisätään
     * @param lisattavat Lisattavat arvot sisältävä taulukko
     * @return Mittaustulos -olio, joka sisältää tietoa mitatuista ajoista
     */
    public Mittaustulos lisaysaikaTulokset(Hakupuu puu, int[] lisattavat) {
        Mittaustulos tulokset = new Mittaustulos("Lisäys", puu);
        for (int i : lisattavat) {
            tulokset.lisaaAika(lisaysaika(puu, i));
        }
        return tulokset;
    }
    
    /**
     * Palauttaa puun hakuoperaatioon kuluvan ajan
     *
     * @param puu Puu josta alkiota haetaan
     * @param haettava Haettavan alkion arvo
     * @return Hakuun kulunut aika
     */
    public long hakuaika(Hakupuu puu, int haettava) {
        kello.aloita();
        puu.hae(haettava);
        return kello.lopeta();
    }

    /**
     * Mittaa kaikkien haettaviaen arvojen hakuajat ja palauttaa näistä
     * tilastoja sisältävän olion
     *
     * @param puu Puu josta arvoja haetaan
     * @param haettavat Haettavat arvot sisältävä taulukko
     * @return Mittaustulos -olio, joka sisältää tietoa mitatuista ajoista
     */
    public Mittaustulos hakuaikaTulokset(Hakupuu puu, int[] haettavat) {
        Mittaustulos tulokset = new Mittaustulos(puu);
        for (int i : haettavat) {
            tulokset.lisaaAika(hakuaika(puu, i));
        }
        return tulokset;
    }

    /**
     * Palauttaa puun poisto-operaatioon kuluvan ajan
     *
     * @param puu Puu josta arvo poistetaan
     * @param poistettava Poistettavan alkion arvo
     * @return Poistoon kulunut aika
     */
    public long poistoaika(Hakupuu puu, int poistettava) {
        kello.aloita();
        puu.poista(poistettava);
        return kello.lopeta();
    }

    /**
     * Mittaa kaikkien poistettavien arvojen poistoajat ja palauttaa näistä
     * tilastoja sisältävän olion
     *
     * @param puu Puu josta arvot poistetaan
     * @param poistettavat Poistettavat arvot sisältävä taulukko
     * @return Mittaustulos -olio, joka sisältää tietoa mitatuista ajoista
     */
    public Mittaustulos poistoaikaTulokset(Hakupuu puu, int[] poistettavat) {
        Mittaustulos tulokset = new Mittaustulos(puu);
        for (int i : poistettavat) {
            tulokset.lisaaAika(poistoaika(puu, i));
        }
        return tulokset;
    }

    /**
     * Vertaa puuntutkijalle annettujen hakupuiden lisäysaikoja annetulla
     * tietojoukolla ja palauttaa saadun vertailu-olion.
     *
     * @param kuvaus Kuvaus, annetaan vertailulle
     * @param data Tietojoukko jolla mittaukset suoritetaan
     * @return Vertailu olio, joka sisältää kutakin puuta vastaavan
     * Mittaustulos-olion.
     */
    public Vertailu lisaysVertailu(String kuvaus, int[] data) {
        Mittaustulos[] tulokset = new Mittaustulos[puut.length];
        for (int i = 0; i < puut.length; i++) {
            tulokset[i] = lisaysaikaTulokset(puut[i], data);
        }
        return new Vertailu(kuvaus, tulokset);
    }

    /**
     * Vertaa puuntutkijalle annettujen hakupuiden hakuaikoja annetulla
     * tietojoukolla ja palauttaa saadun vertailu-olion.
     *
     * @param kuvaus Kuvaus, annetaan vertailulle
     * @param data Tietojoukko jolla mittaukset suoritetaan
     * @return Vertailu olio, joka sisältää kutakin puuta vastaavan
     * Mittaustulos-olion.
     */
    public Vertailu hakuVertailu(String kuvaus, int[] data) {
        Mittaustulos[] tulokset = new Mittaustulos[puut.length];
        for (int i = 0; i < puut.length; i++) {
            tulokset[i] = hakuaikaTulokset(puut[i], data);
        }
        return new Vertailu(kuvaus, tulokset);
    }

    /**
     * Vertaa puuntutkijalle annettujen hakupuiden hakuaikoja annetulla
     * tietojoukolla ja palauttaa saadun vertailu-olion.
     *
     * @param kuvaus Kuvaus, annetaan vertailulle
     * @param data Tietojoukko jolla mittaukset suoritetaan
     * @return Vertailu olio, joka sisältää kutakin puuta vastaavan
     * Mittaustulos-olion.
     */
    public Vertailu poistoVertailu(String kuvaus, int[] data) {
        Mittaustulos[] tulokset = new Mittaustulos[puut.length];
        for (int i = 0; i < puut.length; i++) {
            tulokset[i] = poistoaikaTulokset(puut[i], data);
        }
        return new Vertailu(kuvaus, tulokset);
    }

    /**
     * Laskee ja palauttaa keskimääräisen ajan mittaamisessa tapahtuvan virheen.
     *
     * @return Keskimääräinen virhe nanosekunteina.
     */
    public long virhemarginaali() {
        long sum = 0;
        long errsum = 0;
        long lkm = 0;
        long aika;
        for (int i = 0; i < 1000; i++) {
            kello.aloita();
            aika = kello.lopeta();
            lkm++;
            sum += aika;
            errsum += Math.abs(sum / lkm - aika);
        }
        return errsum / lkm;
    }

    
  /**
   * Rakentaa kaikki tutkittavat puut annetulla tietojoukolla.
   * @param arvot Puihin sijoitettavat arvot
   */
    public void rakennaPuut(int[] arvot) {
        for (Hakupuu hakupuu : puut) {
            hakupuu.lisaaKaikki(arvot);
        }
    }
    
    /**
     * Tyhjentää kaikki tutkittavat puut
     */
    public void tyhjennaPuut(){
        for (Hakupuu hakupuu : puut) {
            hakupuu.tyhjenna();
        }
    }
}

/*
 //Pohjat
 //ajan mittaus:
 kello.aloita();
 //Suoritettava operaatio
 return kello.lopeta();
    
 //Mittaustulos useasta operaatiosta:
 Mittaustulos tulokset = new Mittaustulos("nimi", Hakupuu);
 for (int i : lisattavat) {
 tulokset.lisaaAika(lisaysaika(puu, i));
 }
 return tulokset;
    
 //Vertailu:
 Hakupuu[] puut = {...};
 int[] avaimet ={...};
 for(Hakupuu puu : puut){
 //tee jokin Mittaustulos olio kaikista puista
 }
 Vertailu vertailu = new Vertailu("mitä vertailtiin", Mittaustulos[];
 return vertailu;
 */
