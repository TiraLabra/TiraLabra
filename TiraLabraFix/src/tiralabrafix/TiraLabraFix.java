/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabrafix;

import Algoritmit.Atahtialgoritmi;
import Algoritmit.Janaleikkaus;
import Kayttoliittyma.TekstiUI;
import Luola.Luola;
import Suorituskyky.ATahtiSuorituskykytestaus;
import Tietorakenteet.Abstraktisolmu;
import Tietorakenteet.DiskreettiSolmu;
import Tietorakenteet.DiskreettiVerkko;
import Tietorakenteet.JatkuvaSolmu;
import Tietorakenteet.JatkuvaVerkko;
import Tietorakenteet.Jatkuvamonikulmio;
import Tietorakenteet.Jono.Jono;
import Tietorakenteet.Jono.Jonoiteroitava;
import Tietorakenteet.Keko;
import Tietorakenteet.Kordinaatti;
import Tietorakenteet.Naivimonikulmio;
import Tietorakenteet.Verkko;
import java.util.ArrayList;
import java.util.Arrays;
import logiikka.Jatkuvaverkkorakennus;

/**
 *
 * @author Serafim
 */
public class TiraLabraFix {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //   leikkaajfinaldebug();
        TekstiUI teksti = new TekstiUI();
       teksti.run();
        //     rakentajadebuggaus();
        //  sisalladebuggaus();
       //    luolatest();
    }

    public static void AtahtiTestaus() {
        DiskreettiVerkko verkko = new DiskreettiVerkko(1);
        DiskreettiSolmu[] solmuvektori = new DiskreettiSolmu[4];
        solmuvektori[0] = new DiskreettiSolmu(0, 0);
        solmuvektori[1] = new DiskreettiSolmu(0, 1);
        solmuvektori[2] = new DiskreettiSolmu(1, 0);
        solmuvektori[3] = new DiskreettiSolmu(1, 1);
        Atahtialgoritmi alg = new Atahtialgoritmi(verkko, 100);
        alg.asetaPisteet(solmuvektori[0], solmuvektori[3]);
        verkko.Lisaa(solmuvektori);
        alg.laske();
        for (int i = 0; i < 4; i++) {

            System.out.println(solmuvektori[i].palautaSolmuMuisti().palautaEdellinen());

            DiskreettiSolmu solmu = (DiskreettiSolmu) solmuvektori[i].palautaSolmuMuisti().palautaEdellinen();
            if (solmu != null) {
                System.out.println(solmu.palautaX() + solmu.palautaY());
                solmuvektori[i].palautaSolmuMuisti().palautaEdellinen();
            }

        }
    }

    public static void ATahtiTestausOsa2() {
        DiskreettiSolmu solmuvektori[] = new DiskreettiSolmu[9];
        solmuvektori[0] = new DiskreettiSolmu(0, 0);
        solmuvektori[1] = new DiskreettiSolmu(0, 1);
        solmuvektori[2] = new DiskreettiSolmu(1, 0);
        solmuvektori[3] = new DiskreettiSolmu(1, 1);
        solmuvektori[4] = new DiskreettiSolmu(0, 2);
        solmuvektori[5] = new DiskreettiSolmu(2, 0);
        solmuvektori[6] = new DiskreettiSolmu(1, 2);
        solmuvektori[7] = new DiskreettiSolmu(2, 1);
        solmuvektori[8] = new DiskreettiSolmu(2, 2);
        DiskreettiVerkko verkko = new DiskreettiVerkko(1);
        verkko.Lisaa(solmuvektori);
        Atahtialgoritmi alg = new Atahtialgoritmi(verkko, 100);
        alg.asetaPisteet(solmuvektori[0], solmuvektori[8]);
        alg.laske();
        for (int i = 0; i < 9; i++) {

            DiskreettiSolmu d = (DiskreettiSolmu) solmuvektori[i].palautaSolmuMuisti().palautaEdellinen();
            if (d != null) {
                System.out.println(i + " : " + d.palautaX() + "," + d.palautaY());

            }

        }

    }

    public static void polkurakennusdebuggaus() {
        DiskreettiVerkko verkko = new DiskreettiVerkko(1);
        DiskreettiSolmu[] solmuvektori = new DiskreettiSolmu[9];
        solmuvektori[0] = new DiskreettiSolmu(0, 0);
        solmuvektori[1] = new DiskreettiSolmu(0, 1);
        solmuvektori[2] = new DiskreettiSolmu(1, 0);
        solmuvektori[3] = new DiskreettiSolmu(1, 1);
        solmuvektori[4] = new DiskreettiSolmu(0, 2);
        solmuvektori[5] = new DiskreettiSolmu(2, 0);
        solmuvektori[6] = new DiskreettiSolmu(1, 2);
        solmuvektori[7] = new DiskreettiSolmu(2, 1);
        solmuvektori[8] = new DiskreettiSolmu(2, 2);
        Atahtialgoritmi alg = new Atahtialgoritmi(verkko, 100);

        verkko.Lisaa(solmuvektori);
        alg.asetaPisteet(solmuvektori[0], solmuvektori[8]);
        alg.laske();

        Jono solmut = alg.palautapolku();
        Jonoiteroitava iter = solmut.palautaEnsimmainen();
        while (iter != null) {
            DiskreettiSolmu a = (DiskreettiSolmu) iter.palautaObjekti();
            System.out.println(a.palautaKordinaatti().palautaX() + "," + a.palautaKordinaatti().palautaY());
            iter = iter.palautaSeuraava();
        }

    }

    public static void leikkausdebuggaus() {

        Kordinaatti k1 = new Kordinaatti(0, 1);
        Kordinaatti k2 = new Kordinaatti(2, 1);
        Kordinaatti k3 = new Kordinaatti(1, 0);
        Kordinaatti k4 = new Kordinaatti(1, 2);
        Janaleikkaus leikkaaja = new Janaleikkaus();
        System.out.println(leikkaaja.leikkaako(k1, k2, k3, k4));
    }

    public static void suorituskyky() {
        ATahtiSuorituskykytestaus testaus = new ATahtiSuorituskykytestaus();
        testaus.suorita(4);

    }

    public static void matricetest() {
        int[][] matrice = new int[10][2];
        System.out.println(matrice.length);
        System.out.println(matrice[0].length);
    }

    public static void aTahtiDebuggaus() {
        DiskreettiVerkko verkko = new DiskreettiVerkko(1);
        DiskreettiSolmu[] solmuvektori = new DiskreettiSolmu[4];
        solmuvektori[0] = new DiskreettiSolmu(0, 0);
        solmuvektori[1] = new DiskreettiSolmu(0, 1);
        solmuvektori[2] = new DiskreettiSolmu(1, 0);
        solmuvektori[3] = new DiskreettiSolmu(1, 1);
        Atahtialgoritmi alg = new Atahtialgoritmi(verkko, 100);
        alg.asetaPisteet(solmuvektori[0], solmuvektori[3]);
        verkko.Lisaa(solmuvektori);
        alg.laske();
    }

    public static void monikulmiodebuggaus() {
        Jono j = new Jono();
        j.lisaa(new Kordinaatti(0, 0));
        j.lisaa(new Kordinaatti(0, 1));
        j.lisaa(new Kordinaatti(1, 1));
        j.lisaa(new Kordinaatti(1, 0));
        Naivimonikulmio n = new Naivimonikulmio(j);
        Kordinaatti[][] kartta = n.PalautaJanat();
        boolean k = false;
        boolean d = false;
        for (int i = 0; i < kartta.length; i++) {
            Kordinaatti k1 = kartta[i][0];
            Kordinaatti k2 = kartta[i][1];
            Kordinaatti b1 = new Kordinaatti(0, 0);
            Kordinaatti b2 = new Kordinaatti(0, 1);

            if ((k1.palautaX() == b1.palautaX()) && (k2.palautaX() == b2.palautaX()) && (k2.palautaY() == b2.palautaY()) && (k1.palautaY() == b1.palautaY())) {
                k = true;

            }
            if ((k2 == b2)) {
                d = true;

            }

        }
        System.out.println(k);
        System.out.println(d);

    }

    public static void monikulmionakemisdebuggaus() {

        Kordinaatti k1 = new Kordinaatti(0, 0);
        Kordinaatti k2 = new Kordinaatti(1, 0);
        Kordinaatti k3 = new Kordinaatti(1, 1);
        Kordinaatti k4 = new Kordinaatti(0, 1);
        Jono kordinaattijono = new Jono();
        kordinaattijono.lisaa(k1);
        kordinaattijono.lisaa(k2);
        kordinaattijono.lisaa(k3);
        kordinaattijono.lisaa(k4);
        Jatkuvamonikulmio kulmio = new Jatkuvamonikulmio(kordinaattijono);
        Janaleikkaus leikkaaja = new Janaleikkaus();
        System.out.println(leikkaaja.nakeeko(k1, k2, kulmio));
    }

    public static void rakentajadebuggaus() {
        Kordinaatti k1 = new Kordinaatti(1, 1);
        Kordinaatti k2 = new Kordinaatti(2, 1);
        Kordinaatti k3 = new Kordinaatti(2, 2);
        Kordinaatti k4 = new Kordinaatti(1, 2);
        Kordinaatti alku = new Kordinaatti(0, 0);
        Kordinaatti loppu = new Kordinaatti(3, 3);
        Jono kordinaattijono = new Jono();
        kordinaattijono.lisaa(k1);
        kordinaattijono.lisaa(k2);
        kordinaattijono.lisaa(k3);
        kordinaattijono.lisaa(k4);
        Jatkuvamonikulmio monikulmio = new Jatkuvamonikulmio(kordinaattijono);
        Jono monikulmiot = new Jono();
        monikulmiot.lisaa(monikulmio);
        Jatkuvaverkkorakennus rakentaja = new Jatkuvaverkkorakennus(monikulmiot);
        rakentaja.asetaAlkujaLoppu(alku, loppu);
        JatkuvaVerkko xD = rakentaja.laskeVerkko();
        boolean tarkistus = (xD == null);
        System.out.println(tarkistus);
        JatkuvaSolmu alkus = (JatkuvaSolmu) rakentaja.palautaAlku();
        JatkuvaSolmu loppus = (JatkuvaSolmu) rakentaja.palautaLoppu();
        Atahtialgoritmi algoritmi = new Atahtialgoritmi(xD, 20);
        algoritmi.asetaPisteet(alkus, loppus);
        System.out.println("Reitti löytyykö:" + algoritmi.laske());
        System.out.println("Rakennetaan polku:");
        Jono polku = algoritmi.palautapolku();
        Jonoiteroitava it = polku.palautaEnsimmainen();
        while (it != null) {
            Kordinaatti k = (Kordinaatti) it.palautaObjekti();
            System.out.println(k.tulosta());
            it = it.palautaSeuraava();
        }

    }

    public static void leikkaajfinaldebug() {

        Janaleikkaus testaaja = new Janaleikkaus();
        Kordinaatti[] k = new Kordinaatti[8];
        k[0] = new Kordinaatti(0, 0);
        k[1] = new Kordinaatti(0, 3);
        k[2] = new Kordinaatti(2, 2);
        k[3] = new Kordinaatti(1, 1);
        k[4] = new Kordinaatti(4, 1);
        k[5] = new Kordinaatti(3, 2);
        k[6] = new Kordinaatti(5, 3);
        k[7] = new Kordinaatti(5, 0);

        Jono kordinaattijono = new Jono();
        for (int i = 0; i < k.length; i++) {
            kordinaattijono.lisaa(k[i]);
        }

        Jatkuvamonikulmio kulmio = new Jatkuvamonikulmio(kordinaattijono);
        boolean f = testaaja.nakeeko(k[2], k[5], kulmio);
        System.out.println(f);

    }

    public static void sisalladebuggaus() {
        Janaleikkaus testaaja = new Janaleikkaus();
        Kordinaatti k1 = new Kordinaatti(0, 0);
        Kordinaatti k2 = new Kordinaatti(10, 0);
        Kordinaatti k3 = new Kordinaatti(10, 10);
        Kordinaatti k4 = new Kordinaatti(0, 10);
        Kordinaatti m1 = new Kordinaatti(2, 2);
        Kordinaatti m2 = new Kordinaatti(2, 3);
        Kordinaatti m3 = new Kordinaatti(3, 3);
        Kordinaatti m4 = new Kordinaatti(3, 2);
        Jono kjono2 = new Jono();
        Jono kordinaattijono = new Jono();
        kordinaattijono.lisaa(k1);
        kordinaattijono.lisaa(k2);
        kordinaattijono.lisaa(k3);
        kordinaattijono.lisaa(k4);
        kjono2.lisaa(m1);
        kjono2.lisaa(m2);
        kjono2.lisaa(m3);
        kjono2.lisaa(m4);

        Jatkuvamonikulmio kulmio = new Jatkuvamonikulmio(kordinaattijono);
        Jatkuvamonikulmio kulmio2 = new Jatkuvamonikulmio(kjono2);

        Jono m = new Jono();
        m.lisaa(kulmio);

        boolean d = testaaja.kenenkaansisalla(m1, m);
        System.out.println(d);

    }

    public static void luolatest() {
        Luola luola = new Luola();
        luola.konstruoi(10, 4);
        System.out.println(luola.palautaloppu().tulosta());

    }

}
