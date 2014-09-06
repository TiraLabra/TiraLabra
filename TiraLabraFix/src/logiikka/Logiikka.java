/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import Algoritmit.Atahtialgoritmi;
import Algoritmit.Janaleikkaus;
import Luola.Luola;
import Tietorakenteet.JatkuvaVerkko;
import Tietorakenteet.Jatkuvamonikulmio;
import Tietorakenteet.Jono.Jono;
import Tietorakenteet.Jono.Jonoiteroitava;
import Tietorakenteet.Kordinaatti;

/**
 *
 * @author Serafim
 */
public class Logiikka {

    private Jono monikulmiot;
    private Janaleikkaus leikkaaja;
    private Kordinaatti alkupiste;
    private Kordinaatti loppupiste;
    private Jatkuvaverkkorakennus rakentaja;
    private JatkuvaVerkko verkko;
    private Atahtialgoritmi algoritmi;
    private Jono reitti;
    private long laskemisaika;
    private long atahtiaika;
    private int maksimikulma;
    private boolean safemode;
    private Luola luola;
    private Jatkuvaverkkorakennus ylimrakentaja;

    public Logiikka() {
        this.monikulmiot = new Jono();
        this.leikkaaja = new Janaleikkaus();
        this.rakentaja = new Jatkuvaverkkorakennus(this.monikulmiot);
        this.algoritmi = new Atahtialgoritmi(null, 200);
        this.safemode = true;
        this.luola = new Luola();
        this.ylimrakentaja = new Jatkuvaverkkorakennus(null);

    }

    public void asetaSafemode(boolean k) {
        this.safemode = k;
    }

    public int asetaAlku(Kordinaatti alku) {
        if (safemode) {
            if (loppupiste != null) {
                if (loppupiste == alku) {
                    return 1;
                }
            }
        }
        alkupiste = alku;
        return 0;
    }

    public int asetaLoppu(Kordinaatti loppu) {
        if (safemode) {
            if (alkupiste != null) {
                if (alkupiste == loppu) {
                    return 1;
                }
            }
        }
        loppupiste = loppu;
        return 0;
    }

    public int lisaaMonikulmio(Jono kordinaatti) {
        Jatkuvamonikulmio d = new Jatkuvamonikulmio(kordinaatti);
        if (safemode) {
            Kordinaatti abc = (Kordinaatti) kordinaatti.palautaEnsimmainen().palautaObjekti();
            boolean z = this.leikkaaja.kenenkaansisalla(abc, monikulmiot);
            if (z == true) {
                return 3;
            }

            if (leikkaakoitseaan(d)) {
                return 1;
            }

            if (sallitaanko(d)) {
                monikulmiot.lisaa(d);
                return 0;
            } else {
                return 2;
            }

        } else {
            monikulmiot.lisaa(d);
            return 2;
        }
    }

    public boolean leikkaakoitseaan(Jatkuvamonikulmio d) {
        Kordinaatti[][] janat = d.PalautaJanat();
        for (int i = 0; i < janat.length; i++) {
            for (int j = 0; j < i; j++) {
                boolean f = this.leikkaaja.leikkaako(janat[i][0], janat[i][1], janat[j][0], janat[j][1]);
                if (f == true) {
                    return true;
                }
            }

        }
        return false;
    }

    public boolean sallitaanko(Jatkuvamonikulmio d) {
        Kordinaatti[][] janat = d.PalautaJanat();

        for (int i = 0; i < janat.length; i++) {
            Jonoiteroitava iter = this.monikulmiot.palautaEnsimmainen();
            while (iter != null) {
                Jatkuvamonikulmio a = (Jatkuvamonikulmio) iter.palautaObjekti();
                if (leikkaako(janat[i][0], janat[i][1], a)) {
                    return false;

                }
                iter = iter.palautaSeuraava();
            }
        }
        return true;
    }

    public boolean leikkaako(Kordinaatti k1, Kordinaatti k2, Jatkuvamonikulmio a) {
        Kordinaatti[][] janat = a.PalautaJanat();
        for (int i = 0; i < janat.length; i++) {
            if ((k1.equals(janat[i][0])) || (k1.equals(janat[i][1])) || (k2.equals(janat[i][0])) || (k2.equals(janat[i][1]))) {
                return true;
            }
            boolean d = this.leikkaaja.leikkaako(k1, k2, janat[i][0], janat[i][1]);
            if (d == true) {
                return true;
            }

        }
        return false;
    }

    public void laskeVerkko() {

        this.verkko = this.rakentaja.laskeVerkko();

    }

    public int laskeReitti() {
        if (this.safemode) {
            boolean d123 = this.leikkaaja.kenenkaansisalla(alkupiste, monikulmiot);
            if (d123) {
                return 4;
            }
            boolean d256 = this.leikkaaja.kenenkaansisalla(loppupiste, monikulmiot);
            if (d256) {
                return 5;
            }
        }

        long aikaAlussa = System.currentTimeMillis();
        if (this.alkupiste == null) {
            return 1;
        }
        if (this.loppupiste == null) {
            return 2;
        }
        this.rakentaja.asetaAlkujaLoppu(this.alkupiste, this.loppupiste);
        this.verkko = this.rakentaja.laskeVerkko();
        this.algoritmi.asetaVerkko(verkko);
        this.algoritmi.asetaPisteet(this.rakentaja.palautaAlku(), this.rakentaja.palautaLoppu());
        long aikaAlussa2 = System.currentTimeMillis();
        boolean d = this.algoritmi.laske();
        long aikaLopussa2 = System.currentTimeMillis();

        if (d == false) {
            return 3;
        }
        this.reitti = this.algoritmi.palautapolku();
        long aikaLopussa = System.currentTimeMillis();
        laskemisaika = aikaLopussa - aikaAlussa;
        this.atahtiaika = aikaLopussa2 - aikaAlussa2;
        return 0;

    }

    public double palautaPituus() {
        return this.algoritmi.palautaPituus();
    }

    public boolean solmuMonikulmiossa(Kordinaatti tarkasteltava) {

        return false;

    }

    public Jono palautaMonikulmio() {
        return this.monikulmiot;
    }

    public Jono palautaReitti() {
        return this.reitti;
    }

    public void clear() {
        this.monikulmiot = new Jono();
    }

    public long palautaLaskemisaika() {
        return this.laskemisaika;
    }

    public long palautaAlgoritmiaika() {
        return this.atahtiaika;
    }

    public int poistaNs(int n) {
        Jonoiteroitava iter = this.monikulmiot.palautaNs(n);
        if (iter == null) {
            return 1;
        } else {
            this.monikulmiot.poista(iter);
            return 0;
        }

    }

    public int luolasuorituskyky(int koko, int pienin) {
        this.luola.konstruoi(koko, pienin);
        Kordinaatti alku = this.luola.palautaalku();
        Kordinaatti loppu = this.luola.palautaloppu();
        Jatkuvamonikulmio kulmio = this.luola.palautakulmio();
        Jono kulmiot = new Jono();
        kulmiot.lisaa(kulmio);
       // System.out.println(kulmio.tulosta());
        this.ylimrakentaja.asetaAlkujaLoppu(alku, loppu);
        this.ylimrakentaja.asetaKordinaatit(kulmiot);
        
        long aikaAlussa = System.currentTimeMillis();
        this.verkko = this.ylimrakentaja.laskeVerkko();
        this.algoritmi.asetaVerkko(verkko);
        this.algoritmi.asetaPisteet(this.ylimrakentaja.palautaAlku(), this.ylimrakentaja.palautaLoppu());
        long aikaAlussa2 = System.currentTimeMillis();
        boolean d = this.algoritmi.laske();
        long aikaLopussa2 = System.currentTimeMillis();

        if (d == false) {
            return 1;
        }
        this.reitti = this.algoritmi.palautapolku();
        long aikaLopussa = System.currentTimeMillis();
        laskemisaika = aikaLopussa - aikaAlussa;
        this.atahtiaika = aikaLopussa2 - aikaAlussa2;
        return 0;

    }

}
