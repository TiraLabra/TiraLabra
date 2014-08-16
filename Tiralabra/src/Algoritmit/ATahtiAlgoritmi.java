package Algoritmit;

import tietorakenteett.Abstraktisolmu;
import tietorakenteett.DiskreettiSolmu;
import tietorakenteet.keko.Iteroitava;
import tietorakenteet.keko.Keko;
import tietorakenteett.Verkko;
import java.util.ArrayList;

/*
 * 
 * Atahtialgoritmi joka loytaa lyhimman polun kahden solmun välillä.
 * 
 */
class Atahtialgoritmi {
    
    private Verkko verkko;
    private Abstraktisolmu alku;
    private Abstraktisolmu loppu;
    private Keko laskentaJoukko;
    private int maksimi;
    private ArrayList<Abstraktisolmu> polku;


    /*
     * 
     * Konstruktori joka asettaa Verkon jota käytetään.
     * 
     */
    public Atahtialgoritmi(Verkko verkko, int maksimi) {
        this.verkko = verkko;
        this.maksimi = maksimi;
        this.polku = new ArrayList<Abstraktisolmu>();

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
    public void laske() {
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
                        naapuri.palautaSolmuMuisti().asetaEdellinen(current);
                        naapuri.palautaSolmuMuisti().asetaGScore(gscore);
                        double d = naapuri.palautaSolmuMuisti().palautaGScore() + this.verkko.Heurestiikka(naapuri, loppu);
                        boolean kasvaa;
                        if (d > naapuri.palautaSolmuMuisti().palautaFScore()) {
                            kasvaa = true;
                        } else {
                            kasvaa = false;
                        }
                        naapuri.palautaSolmuMuisti().asetaFScore(d);
                        if (naapuri.palautaSolmuMuisti().Keossa() == false) {
                            this.laskentaJoukko.Lisaa(naapuri);
                            naapuri.palautaSolmuMuisti().asetaKekoon(true);
                        } else {
                            if (kasvaa) {
                                this.laskentaJoukko.Kasvatettu(naapuri.SijaintiKeossa());
                            } else {
                                this.laskentaJoukko.Pienennetty(naapuri.SijaintiKeossa());
                            }
                        }
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
    
    public void rakennapolku()
    {

    
    }
    
}
