package Algoritmit;

import Tietorakenteet.Abstraktisolmu;
import Tietorakenteet.DiskreettiSolmu;
import Tietorakenteet.Keko.Iteroitava;
import Tietorakenteet.Keko.Keko;
import Tietorakenteet.Verkko;
import java.util.ArrayList;

/*
 * 
 * ATahtiAlgoritmi joka loytaa lyhimman polun kahden solmun välillä.
 * 
 */
class ATahtiAlgoritmi {

    private Verkko verkko;
    private Abstraktisolmu alku;
    private Abstraktisolmu loppu;
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

    public void asetaPisteet(Abstraktisolmu alku, Abstraktisolmu loppu) {
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
        alku.palautaSolmuMuisti().asetaKekoon(true);
        this.alku.palautaSolmuMuisti().asetaGScore(0);
        this.alku.palautaSolmuMuisti().asetaFScore(this.verkko.Etaisyys(alku, loppu));
        while (this.laskentaJoukko.palautaNykyinenKoko() > 0) {
            Abstraktisolmu current = (Abstraktisolmu) this.laskentaJoukko.PoistaMinimi();
            current.palautaSolmuMuisti().asetaKekoon(false);
            if (current == loppu) {
                this.loppu.palautaSolmuMuisti().asetaEdellinen(alku);
                return;
            }
            current.palautaSolmuMuisti().Varita(1);
            ArrayList<Abstraktisolmu> naapurit = this.verkko.Naapurit(current);
            for (Abstraktisolmu naapuri : naapurit) {
                if (naapuri.palautaSolmuMuisti().palautaVari() != 1) {
                    double gscore = current.palautaSolmuMuisti().palautaGScore() + this.verkko.Etaisyys(current, naapuri);
                    if ((naapuri.palautaSolmuMuisti().Keossa() == false) || (gscore < naapuri.palautaSolmuMuisti().palautaGScore())) {
                        
                    }
                }
            }

        }

    }

    public void initKeko() {
        this.laskentaJoukko = new Keko();
        Iteroitava[] taulukko = new Iteroitava[this.maksimi];
        this.laskentaJoukko.asetaTaulukko(taulukko, 0);

    }

}
