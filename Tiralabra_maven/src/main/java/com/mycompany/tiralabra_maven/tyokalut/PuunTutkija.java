package com.mycompany.tiralabra_maven.tyokalut;

import com.mycompany.tiralabra_maven.tietorakenteet.*;
import java.util.Random;

/**
 * Puiden suorituskyvyn analysointiin liittyviä toimintoja sisältävä luokka.
 * Luokka saa luomisen yhteydessä Hakupuu rajapinnan toteuttavia olioita. Luokka
 * sisältää metodeja, jotka saavat parametrina taulukon alkioita, joiden avulla
 * sitten tutkitaan kunkin puun toimintaa jossakin tehtävässä.
 * Tuloksetpalautetaan mittaustulos-olioina.
 *
 * @author Markus
 */
public class PuunTutkija {

    private final Sekuntikello kello;
    private final Hakupuu[] puut;
    private int toistoja;

    /**
     * Alustetaan luokka asettamalla saadut puut taulukkoon, luomalla uusi
     * sekuntikello aikojen mittaamiseen ja asettamalla mittausten
     * toistomäärälle alkuarvo (1).
     *
     * @param puut Puut joihin luokan suorittamat mittaukset kohdistuvat.
     */
    public PuunTutkija(Hakupuu... puut) {
        this.puut = puut;
        this.kello = new Sekuntikello();
        toistoja = 1;
    }

    /**
     * Palauttaa suoritettavien toistojen määrän.
     *
     * @return
     */
    public int getToistoja() {
        return toistoja;
    }

    /**
     * Asettaa suoritettavien toistojen määrän
     *
     * @param toistoja Uusi toistojen lukumäärä.
     */
    public void setToistoja(int toistoja) {
        this.toistoja = toistoja;
    }

    /**
     * Palauttaa puun lisäysoperaatioon kuluvan ajan.
     *
     * @param puu Puu josta alkiota haetaan
     * @param lisattavat Lisättävät alkiot sisältävä taulukko
     * @return Lisäykseen kulunut aika
     */
    private long lisaysaika(Hakupuu puu, int[] lisattavat) {
        kello.aloita();
        puu.lisaaKaikki(lisattavat);
        return kello.lopeta();
    }

    /**
     * Mittaa lisäysajan niin monta kertaa kuin toistoja-muuttuja ilmoittaa ja
     * palauttaa kaikki nämä mittaukset sisältävän mittaustulos-olion.
     *
     * @param puu Puu johon arvot lisätään
     * @param lisattavat Lisattavat arvot sisältävä taulukko
     * @return Mittaustulos-olio, joka sisältää tietoa mitatuista ajoista.
     */
    private Mittaustulos lisaysaikaTulokset(Hakupuu puu, int[] lisattavat) {
        Mittaustulos tulokset = new Mittaustulos(puu);
        for (int i = 0; i < toistoja; i++) {
            tulokset.lisaaAika(lisaysaika(puu, lisattavat));
            puu.tyhjenna();
        }
        return tulokset;
    }

    /**
     * Vertaa puuntutkijalle annettujen hakupuiden lisäysaikoja annetulla
     * tietojoukolla ja palauttaa taulukon joka sisältä kutakin puuta vastaavan
     * Mittaustulos-olion. Kukin Mittaustulos sisältää "toistoja" verran
     * mitattuja aikoja.
     *
     *
     * @param data Tietojoukko jolla mittaukset suoritetaan.
     * @return Taulukko mittaustuloksia joka sisältää kutakin puuta vastaavan
     * Mittaustulos-olion.
     */
    public Mittaustulos[] lisaysVertailu(int[] data) {
        Mittaustulos[] tulokset = new Mittaustulos[puut.length];
        for (int i = 0; i < puut.length; i++) {
            tulokset[i] = lisaysaikaTulokset(puut[i], data);
        }
        return tulokset;
    }

    /**
     * Vertaa puuntutkijalle annettujen hakupuiden lisäysaikoja sekoitetulla
     * versiolla annetusta tietojoukosta ja palauttaa taulukon joka sisältä
     * kutakin puuta vastaavan Mittaustulos-olion. Kukin Mittaustulos sisältää
     * "toistoja" verran mitattuja aikoja.
     *
     * @param data Tietojoukko jonka sekoitetulla versiolla mittaukset
     * suoritetaan.
     * @return Taulukko mittaustuloksia joka sisältää kutakin puuta vastaavan
     * Mittaustulos-olion.
     */
    public Mittaustulos[] lisaysVertailuRand(int[] data) {
        Mittaustulos[] tulokset = new Mittaustulos[puut.length];
        data = sekoitaTaulukko(data);
        for (int i = 0; i < puut.length; i++) {
            tulokset[i] = lisaysaikaTulokset(puut[i], data);
        }
        return tulokset;
    }

    /**
     * Palauttaa puun hakuoperaatioihin kuluvan ajan
     *
     * @param puu Puu josta alkioita haetaan
     * @param haettavat Haettavat alkioiden arvot
     * @return Hakuun kulunut aika
     */
    private long hakuaika(Hakupuu puu, int[] haettavat) {
        kello.aloita();
        for (int i : haettavat) {
            puu.hae(i);
        }
        return kello.lopeta();
    }

    /**
     * Mittaa hakuajan niin monta kertaa kuin toistoja-muuttuja ilmoittaa ja
     * palauttaa kaikki nämä mittaukset sisältävän mittaustulos-olion.
     *
     * @param puu Puu josta arvoja haetaan
     * @param haettavat Taulukko joka sisältää puusta haettavat arvot
     * @return Mittaustulos-olio, joka sisältää tietoa mitatuista ajoista
     */
    private Mittaustulos hakuaikaTulokset(Hakupuu puu, int[] haettavat) {
        Mittaustulos tulokset = new Mittaustulos(puu);
        for (int i = 0; i < toistoja; i++) {
            tulokset.lisaaAika(hakuaika(puu, haettavat));
        }
        return tulokset;
    }

    /**
     * Vertaa puuntutkijalle annettujen hakupuiden hakuaikoja annetulla
     * tietojoukolla ja palauttaa saadun taulukon Mittaustuloksia. Kukin
     * Mittaustulos sisältää "toistoja" verran mitattuja aikoja.
     *
     * @param data Arvot jolla mittaukset suoritetaan
     * @return Taulukko Mittaustulos-olioita joka sisältää kutakin puuta
     * vastaavan Mittaustulos-olion.
     */
    public Mittaustulos[] hakuVertailu(int[] data) {
        Mittaustulos[] tulokset = new Mittaustulos[puut.length];
        rakennaPuut(data);
        for (int i = 0; i < puut.length; i++) {
            tulokset[i] = hakuaikaTulokset(puut[i], data);
        }
        tyhjennaPuut();
        return tulokset;
    }

    /**
     * Vertaa puuntutkijalle annettujen hakupuiden hakuaikoja annetun
     * tietojoukon sekoitetulla versiolla ja palauttaa saadun vertailu-olion.
     * Puut rakennetaan ensin järjestyksessä olevalla joukolla ja tämän jälkeen
     * arvoja etsitään sekoitetulla versiolla. Kukin Mittaustulos sisältää
     * "toistoja" verran mitattuja aikoja.
     *
     * @param data Arvot jolla mittaukset suoritetaan
     * @return Taulukko Mittaustulos-olioita joka sisältää kutakin puuta
     * vastaavan Mittaustulos-olion.
     */
    public Mittaustulos[] hakuVertailuRand(int[] data) {
        Mittaustulos[] tulokset = new Mittaustulos[puut.length];
        rakennaPuut(data);
        data = sekoitaTaulukko(data);
        for (int i = 0; i < puut.length; i++) {
            tulokset[i] = hakuaikaTulokset(puut[i], data);
        }
        tyhjennaPuut();
        return tulokset;
    }

    /**
     * Vertaa puuntutkijalle annettujen hakupuiden hakuaikoja annetun
     * tietojoukon käänteisellä versiolla ja palauttaa saadun vertailu-olion.
     * Puut rakennetaan ensin järjestyksessä olevalla joukolla ja tämän jälkeen
     * arvoja etsitään käänteisellä versiolla. Kukin Mittaustulos sisältää
     * "toistoja" verran mitattuja aikoja.
     *
     * @param data Arvot jolla mittaukset suoritetaan
     * @return Taulukko Mittaustulos-olioita joka sisältää kutakin puuta
     * vastaavan Mittaustulos-olion.
     */
    public Mittaustulos[] hakuVertailuKaanteinen(int[] data) {
        Mittaustulos[] tulokset = new Mittaustulos[puut.length];
        rakennaPuut(data);
        data = kaannaTaulukko(data);
        for (int i = 0; i < puut.length; i++) {
            tulokset[i] = hakuaikaTulokset(puut[i], data);
        }
        tyhjennaPuut();
        return tulokset;
    }

    /**
     * Palauttaa puun poisto-operaatioihin kuluvan ajan
     *
     * @param puu Puu josta arvot poistetaan
     * @param poistettavat Poistettavat alkiot sisältävä taulukko.
     * @return Poistoon kulunut aika
     */
    private long poistoaika(Hakupuu puu, int[] poistettavat) {
        kello.aloita();
        for (int i : poistettavat) {
            puu.poista(i);
        }
        return kello.lopeta();
    }

    /**
     * Mittaa poistoajan niin monta kertaa kuin toistoja-muuttuja ilmoittaa ja
     * palauttaa kaikki nämä mittaukset sisältävän mittaustulos-olion.
     *
     * @param puu Puu josta arvot poistetaan
     * @param alkiot Alkiot jotka puun tulee sisältää ennen poisto-operaatioita.
     * @param poistettavat Alkiot mitkä poistetaan puusta.
     * @return Mittaustulos -olio, joka sisältää tietoa mitatuista ajoista
     */
    private Mittaustulos poistoaikaTulokset(Hakupuu puu, int[] alkiot, int[] poistettavat) {
        Mittaustulos tulokset = new Mittaustulos(puu);
        for (int i = 0; i < toistoja; i++) {
            puu.lisaaKaikki(alkiot);
            tulokset.paivitaPuunTiedot();
            tulokset.lisaaAika(poistoaika(puu, poistettavat));
            puu.tyhjenna();
        }

        return tulokset;
    }

    /**
     * Vertaa puuntutkijalle annettujen hakupuiden hakuaikoja annetulla
     * tietojoukolla ja palauttaa saadun taulukon. Arvot lisätään ja poistetaan
     * puusta samassa järjestyksessä.
     *
     * @param data Tietojoukko jolla mittaukset suoritetaan.
     * @return Taulukko mittaustuloksia, joka sisältää kutakin puuta vastaavan.
     * Mittaustulos-olion. Kukin Mittaustulos sisältää "toistoja" verran
     * mitattuja aikoja.
     */
    public Mittaustulos[] poistoVertailu(int[] data) {
        Mittaustulos[] tulokset = new Mittaustulos[puut.length];
        for (int i = 0; i < puut.length; i++) {
            tulokset[i] = poistoaikaTulokset(puut[i], data, data);
        }
        return tulokset;
    }

    /**
     * Vertaa puuntutkijalle annettujen hakupuiden hakuaikoja annetulla
     * tietojoukolla ja palauttaa saadun taulukon. Arvot lisätään järjestyksessä
     * ja poistetaan arvotussa järjestyksessä. Kukin Mittaustulos sisältää
     * "toistoja" verran mitattuja aikoja.
     *
     * @param data Tietojoukko jolla mittaukset suoritetaan
     * @return Taulukko mittaustuloksia, joka sisältää kutakin puuta vastaavan.
     * Mittaustulos-olion.
     */
    public Mittaustulos[] poistoVertailuRand(int[] data) {
        Mittaustulos[] tulokset = new Mittaustulos[puut.length];
        for (int i = 0; i < puut.length; i++) {
            int[] randData = sekoitaTaulukko(data.clone());
            tulokset[i] = poistoaikaTulokset(puut[i], data, randData);
        }
        return tulokset;
    }

    /**
     * Vertaa puuntutkijalle annettujen hakupuiden hakuaikoja annetulla
     * tietojoukolla ja palauttaa saadun taulukon. Arvot lisätään järjestyksessä
     * ja poistetaan käänteisessä järjestyksessä. Kukin Mittaustulos sisältää
     * "toistoja" verran mitattuja aikoja.
     *
     * @param data Tietojoukko jolla mittaukset suoritetaan
     * @return Taulukko mittaustuloksia, joka sisältää kutakin puuta vastaavan.
     * Mittaustulos-olion.
     */
    public Mittaustulos[] poistoVertailuKaanteinen(int[] data) {
        Mittaustulos[] tulokset = new Mittaustulos[puut.length];
        int[] kaantData = kaannaTaulukko(data.clone());
        for (int i = 0; i < puut.length; i++) {
            tulokset[i] = poistoaikaTulokset(puut[i], data, kaantData);
        }
        return tulokset;
    }

    /**
     * Rakentaa kaikki tutkittavat puut annetulla tietojoukolla.
     *
     * @param arvot Puihin sijoitettavat arvot
     */
    private void rakennaPuut(int[] arvot) {
        for (Hakupuu hakupuu : puut) {
            hakupuu.lisaaKaikki(arvot);
        }
    }

    /**
     * Tyhjentää kaikki tutkittavat puut
     */
    private void tyhjennaPuut() {
        for (Hakupuu hakupuu : puut) {
            hakupuu.tyhjenna();
        }
    }

    /**
     * Sekoittaa parametrina saadun taulukon kokonaislukuja ja palauttaa uuden
     * taulukon.
     *
     * @param data Sekoitettava taulukko.
     * @return Sekoitettu taulukko.
     */
    private int[] sekoitaTaulukko(int[] data) {
        Random rnd = new Random();
        for (int i = 0; i < data.length - 1; i++) {
            int index = rnd.nextInt(data.length - i - 1);
            int a = data[i];
            data[i] = data[i + index];
            data[i + index] = a;
        }
        return data;
    }

    /**
     * Kääntää parametrina saadun taulukon arvot ja palauttaa uuden taulukon.
     *
     * @param data Käännettävä taulukko.
     * @return Käännetty taulukko.
     */
    private int[] kaannaTaulukko(int[] data) {
        int index = data.length - 1;
        for (int i = 0; i < data.length / 2; i++) {
            int a = data[i];
            data[i] = data[index - i];
            data[index - i] = a;
        }
        return data;
    }
}
