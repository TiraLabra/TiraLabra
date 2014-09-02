/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import Algoritmit.Janaleikkaus;
import Tietorakenteet.Abstraktisolmu;
import Tietorakenteet.JatkuvaSolmu;
import Tietorakenteet.JatkuvaVerkko;
import Tietorakenteet.Jatkuvamonikulmio;
import Tietorakenteet.Jono.Jono;
import Tietorakenteet.Jono.Jonoiteroitava;
import Tietorakenteet.Kordinaatti;
import Tietorakenteet.Monikulmio;

/**
 *
 * Tämä luokka rakentaa monikulmiosta verkon
 */
public class Jatkuvaverkkorakennus {

    private Jono monikulmiot;
    private JatkuvaSolmu alku;
    private JatkuvaSolmu loppu;
    private Janaleikkaus leikkaaja;
    private JatkuvaVerkko verkko;

    public Jatkuvaverkkorakennus(Jono Naivimonikulmiot) {
        this.monikulmiot = Naivimonikulmiot;
        this.leikkaaja = new Janaleikkaus();

    }

    /**
     *
     * Asettaa monikulmiot
     */
    public void asetaKordinaatit(Jono kordinaatit) {
        this.monikulmiot = kordinaatit;
    }

    /**
     *
     * Palauttaa monikulmiot
     */
    public Jono palautaMonikulmiot() {
        return this.monikulmiot;
    }

    /**
     *
     * Asettaa alku ja loppu pisteen
     */

    public void asetaAlkujaLoppu(Kordinaatti alkur, Kordinaatti loppur) {
        this.alku = new JatkuvaSolmu(alkur);
        this.loppu = new JatkuvaSolmu(loppur);

    }

    /**
     *
     * Laskee verkon
     */
    public JatkuvaVerkko laskeVerkko() {
        this.verkko = new JatkuvaVerkko();
        solmuAlustus();

        //iteroimonikulmioidensuhteen 
        Jonoiteroitava iter = this.monikulmiot.palautaEnsimmainen();
        while (iter != null) {
            Jatkuvamonikulmio d = (Jatkuvamonikulmio) iter.palautaObjekti();
            Jono kulmat = d.palautaJatkuvasolmut();
            Jonoiteroitava kulmaiteraattori = kulmat.palautaEnsimmainen();
            while (kulmaiteraattori != null) {
                JatkuvaSolmu k = (JatkuvaSolmu) kulmaiteraattori.palautaObjekti();
                toinenkulma(k, d);
                kulmaiteraattori = kulmaiteraattori.palauataSeuraava();
            }
            //Iterointi:
            iter = iter.palauataSeuraava();
        }
        toinenkulma(this.alku, null);

        return this.verkko;
    }

    /**
     *
     * Iteroi toisen alkion suhteen
     */
    public void toinenkulma(JatkuvaSolmu k, Monikulmio d) {
        Jonoiteroitava iter = this.monikulmiot.palautaEnsimmainen();
        while (iter != null) {
            Jatkuvamonikulmio d2 = (Jatkuvamonikulmio) iter.palautaObjekti();
            Jono kulmat = d2.palautaJatkuvasolmut();

            //Tapaus 1: Kulmat ovat samassa fg 
            Jonoiteroitava kulmaiteraattori = kulmat.palautaEnsimmainen();
            while (kulmaiteraattori != null) {
                boolean kertoja = false;
                JatkuvaSolmu k2 = (JatkuvaSolmu) kulmaiteraattori.palautaObjekti();

                //Debuggaus tulostukset:
                System.out.println("It: " + k.palautaKordinaatti().tulosta() + "..:.." + k2.palautaKordinaatti().tulosta());

                if (!k.palautaKordinaatti().equals(k2.palautaKordinaatti())) {

                    if (d == d2) {
                        kertoja = samaMonikulmio(k.palautaKordinaatti(), k2.palautaKordinaatti(), d);
                    } else {
                        kertoja = eriMonikulmio(k.palautaKordinaatti(), k2.palautaKordinaatti());

                    }

                    if (kertoja == true) {
                        k.lisaaNaapuri(k2);
                    }
                }
                //Iterointi:
                kulmaiteraattori = kulmaiteraattori.palauataSeuraava();

            }
            //Iterointi:
            iter = iter.palauataSeuraava();
        }
        if (eriMonikulmio(k.palautaKordinaatti(), this.loppu.palautaKordinaatti())) {
            k.lisaaNaapuri(this.loppu);
        }

    }

    /**
     *
     * Tämä metodi castataan jos kordinaatti k ja k2 kuuluvat samaan
     * monikulmioon
     */
    public boolean samaMonikulmio(Kordinaatti k, Kordinaatti k2, Monikulmio a) {
        if (leikkaaja.nakeeko(k, k2, a)) {

            return eriMonikulmio(k, k2);

        }
        return false;

    }

    /**
     *
     * Tarkastellaan tapausta jossa k ja k2 eivät kuulu samaan monikulmioon tai
     * sillä ei ole väliä
     */
    public boolean eriMonikulmio(Kordinaatti k, Kordinaatti k2) {
        Jonoiteroitava j = this.monikulmiot.palautaEnsimmainen();
        while (j != null) {
            Jatkuvamonikulmio m = (Jatkuvamonikulmio) j.palautaObjekti();
            Kordinaatti[][] janat = m.PalautaJanat();
            for (int i = 0; i < janat.length; i++) {
                Kordinaatti p1 = janat[i][0];
                Kordinaatti p2 = janat[i][1];
                boolean kertoja = this.leikkaaja.leikkaako(k, k2, p1, p2);
                if (kertoja == true) {
                    return false;
                }

            }
            //Iterointi:
            j = j.palauataSeuraava();

        }
        return true;
    }

    /**
     *
     * Alustetaan Jatkuvasolmu alkiot
     */
    public void solmuAlustus() {
        Jonoiteroitava iter = this.monikulmiot.palautaEnsimmainen();
        while (iter != null) {
            Jatkuvamonikulmio s = (Jatkuvamonikulmio) iter.palautaObjekti();
            Jonoiteroitava b = s.palautaJatkuvasolmut().palautaEnsimmainen();
            while (b != null) {
                JatkuvaSolmu abc = (JatkuvaSolmu) b.palautaObjekti();
                this.verkko.lisaaAlkio(abc);
                b = b.palauataSeuraava();
            }
            //Iterointi:
            iter = iter.palauataSeuraava();
        }
        this.verkko.lisaaAlkio(alku);
        this.verkko.lisaaAlkio(loppu);

    }

}
