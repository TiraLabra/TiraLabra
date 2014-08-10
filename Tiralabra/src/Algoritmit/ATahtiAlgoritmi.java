package Algoritmit;

import Tietorakenteet.Keko.Iteroitava;
import Tietorakenteet.Keko.Keko;
import Tietorakenteet.Solmu;
import Tietorakenteet.Verkko;
import Tietorakenteet.abstraktiSolmu;

/*
 * 
 * ATahtiAlgoritmi joka loytaa lyhimman polun kahden solmun välillä.
 * 
 */
class ATahtiAlgoritmi {

    private Verkko verkko;
    private abstraktiSolmu alku;
    private abstraktiSolmu loppu;
    private Keko laskentaJoukko;
    private int maksimi;

    /*
     * 
     * Konstruktori joka asettaa Verkon jota käytetään.
     * 
     */
    public ATahtiAlgoritmi(Verkko verkko, int maksimi) {
        this.verkko = verkko;
        this.maksimi = maksimi;
    }
    /*
     * 
     * Palauttaa verkon
     * 
     */

    public Verkko palautaVerkko() {
        return this.verkko;
    }

    /*
     * 
     * Asettaa toisen verkon
     * 
     */
    public void asetaVerkko(Verkko verkko) {
        this.verkko = verkko;
    }
    /*
     * 
     * Asettaa alku ja loppu pisteet.
     * 
     */

    public void asetaPisteet(abstraktiSolmu alku, abstraktiSolmu loppu) {
        this.alku = alku;
        this.loppu = loppu;
        initKeko();
    }

    /*
     * 
     * Laskee lyhimman polun
     * 
     */
    public void Laske() {
        this.laskentaJoukko.Lisaa(alku);

    }

    public void initKeko() {
        this.laskentaJoukko = new Keko();
        Iteroitava[] taulukko = new Iteroitava[this.maksimi];
        this.laskentaJoukko.asetaTaulukko(taulukko, 0);

    }

}
