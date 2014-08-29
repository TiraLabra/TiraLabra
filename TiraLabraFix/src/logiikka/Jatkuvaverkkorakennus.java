/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import Algoritmit.Janaleikkaus;
import Tietorakenteet.JatkuvaVerkko;
import Tietorakenteet.Jono.Jono;
import Tietorakenteet.Jono.Jonoiteroitava;
import Tietorakenteet.Kordinaatti;
import Tietorakenteet.Monikulmio;

/**
 *
 *
 */
public class Jatkuvaverkkorakennus {

    private Jono monikulmiot;
    private Kordinaatti alku;
    private Kordinaatti loppu;
    private Janaleikkaus leikkaaja;

    public Jatkuvaverkkorakennus(Jono kordinaatit) {
        this.monikulmiot = kordinaatit;
        this.leikkaaja = new Janaleikkaus();

    }

    public void asetaKordinaatit(Jono kordinaatit) {
        this.monikulmiot = kordinaatit;
    }

    public Jono palautaMonikulmiot() {
        return this.monikulmiot;
    }

    public void asetaAlkujaLoppu(Kordinaatti alku, Kordinaatti loppu) {
        this.alku = alku;
        this.loppu = loppu;

    }

    public JatkuvaVerkko laskeVerkko() {
        JatkuvaVerkko rakennus = new JatkuvaVerkko();

        //iteroimonikulmioidensuhteen 
        Jonoiteroitava iter = this.monikulmiot.palautaEnsimmainen();
        while (iter != null) {
            Monikulmio d = (Monikulmio) iter.palautaObjekti();
            Jono kulmat = d.palautaKulmat();
            Jonoiteroitava kulmaiteraattori = kulmat.palautaEnsimmainen();
            while (kulmaiteraattori != null) {
                Kordinaatti k = (Kordinaatti) kulmaiteraattori.palautaObjekti();
                toinenkulma(k, d);
                kulmaiteraattori = kulmaiteraattori.palauataSeuraava();
            }
            iter = iter.palauataSeuraava();
        }

        return rakennus;
    }

    public void toinenkulma(Kordinaatti k, Monikulmio d) {
        Jonoiteroitava iter = this.monikulmiot.palautaEnsimmainen();
        while (iter != null) {
            Monikulmio d2 = (Monikulmio) iter.palautaObjekti();
            Jono kulmat = d.palautaKulmat();

            //Tapaus 1: Kulmat ovat samassa fg 
            Jonoiteroitava kulmaiteraattori = kulmat.palautaEnsimmainen();
            while (kulmaiteraattori != null) {
                Kordinaatti k2 = (Kordinaatti) kulmaiteraattori.palautaObjekti();
                kulmaiteraattori = kulmaiteraattori.palauataSeuraava();
            }
            iter = iter.palauataSeuraava();
        }

    }

    public void samaMonikulmio(Kordinaatti k, Kordinaatti k2, Monikulmio a) {
        if (leikkaaja.nakeeko(k, k2, a))
        {
        
        }
        
    }

    public void eriMonikulmio() {

    }

}
