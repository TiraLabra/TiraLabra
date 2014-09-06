/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmit;

import Tietorakenteet.Jono.Jono;
import Tietorakenteet.Jono.Jonoiteroitava;
import Tietorakenteet.Keko;
import Tietorakenteet.Kordinaatti;
import Tietorakenteet.Monikulmio;

/**
 *
 *
 *
 */
public class Janaleikkaus {

    /**
     *
     * Palauttaa true vain jos janoilla on vain yksi leikkauspiste, muuten
     * false.
     *
     *
     * @param piste1 Ensimmäisen janan päätepiste
     * @param piste2 Ensimmäisen janan toinen päätepiste
     * @param piste3 Toisen janan päätepiste
     * @param piste4 Toisen janan toinen päätepiste
     * @return boolean Palauttaa leikkaako janat jotka muodostuvat kyseista
     * pistejoukosta
     */
    public boolean leikkaako(Kordinaatti piste1, Kordinaatti piste2, Kordinaatti piste3, Kordinaatti piste4) {

        Kordinaatti testi = suoraLeikkaus(piste1, piste2, piste3, piste4);
        if (testi == null) {
            return false;
        }

        if ((testi.equals(piste1)) || (testi.equals(piste2)) || (testi.equals(piste3)) || (testi.equals(piste4))) {
            return false;

        }
        return leikkausjalki(testi, piste1, piste2, piste3, piste4);

    }

    public boolean paasekolapi(Kordinaatti piste1, Kordinaatti piste2, Kordinaatti piste3, Kordinaatti piste4) {

        Kordinaatti testi = suoraLeikkaus(piste1, piste2, piste3, piste4);
        if (testi == null) {
            return false;
        }

        if ((testi.equals(piste1)) || (testi.equals(piste2))) {
            return false;

        }
        return leikkausjalki(testi, piste1, piste2, piste3, piste4);

    }

    private boolean leikkausjalki(Kordinaatti testi, Kordinaatti piste1, Kordinaatti piste2, Kordinaatti piste3, Kordinaatti piste4) {
        double ratkaisux = testi.palautaX();
        double ratkaisuy = testi.palautaY();
        double MaxX1 = Math.max(piste1.palautaX(), piste2.palautaX());
        double MinX1 = Math.min(piste1.palautaX(), piste2.palautaX());
        double MaxY1 = Math.max(piste1.palautaY(), piste2.palautaY());
        double MinY1 = Math.min(piste1.palautaY(), piste2.palautaY());
        double MaxX2 = Math.max(piste3.palautaX(), piste4.palautaX());
        double MinX2 = Math.min(piste3.palautaX(), piste4.palautaX());
        double MaxY2 = Math.max(piste3.palautaY(), piste4.palautaY());
        double MinY2 = Math.min(piste3.palautaY(), piste4.palautaY());
        if ((ratkaisux <= MaxX1) && (ratkaisux <= MaxX2) && (ratkaisux >= MinX1) && (ratkaisux >= MinX2) && (ratkaisuy <= MaxY1) && (ratkaisuy <= MaxY2) && (ratkaisuy >= MinY1) && (ratkaisuy >= MinY2)) {
            return true;
        }
        return false;
    }

    /**
     * Löytää kahden pisteen määräämien suorien leikkauspisteen (Jos sellainen
     * on olemassa)
     *
     * @param piste1 Ensimmäisen janan päätepiste
     * @param piste2 Ensimmäisen janan toinen päätepiste
     * @param piste3 Toisen janan päätepiste
     * @param piste4 Toisen janan toinen päätepiste
     */
    public Kordinaatti suoraLeikkaus(Kordinaatti piste1, Kordinaatti piste2, Kordinaatti piste3, Kordinaatti piste4) {
        double u0x = piste1.palautaX();
        double u0y = piste1.palautaY();
        double v0x = piste1.palautaX() - piste2.palautaX();
        double v0y = piste1.palautaY() - piste2.palautaY();
        double t = 0;

        double u1x = piste3.palautaX();
        double u1y = piste3.palautaY();
        double v1x = piste3.palautaX() - piste4.palautaX();
        double v1y = piste3.palautaY() - piste4.palautaY();
        double s = 0;

        double determinantti = v1x * v0y - v0x * v1y;
        if (determinantti == 0) {
            return null;

        } else {
            s = 1 / determinantti * ((u0x - u1x) * v0y - v0x * (u0y - u1y));
            t = 1 / determinantti * -1 * ((-1) * v1y * (u0x - u1x) + (u0y - u1y) * v1x);
            double ratkaisux = u0x + v0x * t;
            double ratkaisuy = u0y + v0y * t;
            Kordinaatti palautus = new Kordinaatti(ratkaisux, ratkaisuy);
            return palautus;
        }

    }

    /**
     * Löytää suoran ja janan leikkauspisteen
     *
     * @param piste1 Janan päätepiste
     * @param piste2 Toinen Janan päätepiste
     * @param piste3 Suoran päätepiste
     * @param piste4 Suoran toinen päätepiste
     * @param paatepistecheck true jos meitakiinostaa paatepiste
     * @return Kordinaatti leikkauspiste, jos sellaista ei ole palauttaa null
     */
    public Kordinaatti suoranjaJananleikkaus(Kordinaatti piste1, Kordinaatti piste2, Kordinaatti piste3, Kordinaatti piste4, boolean paatepistecheck) {
        Kordinaatti testi = suoraLeikkaus(piste1, piste2, piste3, piste4);
        if (testi == null) {
            return null;
        }
        double ratkaisux = testi.palautaX();
        double ratkaisuy = testi.palautaY();
        if (paatepistecheck) {
            if ((testi.equals(piste1)) || (testi.equals(piste2))) {
                return null;

            }

        }

        double MaxX1 = Math.max(piste1.palautaX(), piste2.palautaX());
        double MinX1 = Math.min(piste1.palautaX(), piste2.palautaX());
        double MaxY1 = Math.max(piste1.palautaY(), piste2.palautaY());
        double MinY1 = Math.min(piste1.palautaY(), piste2.palautaY());
        if ((ratkaisux <= MaxX1) && (ratkaisux >= MinX1) && (ratkaisuy <= MaxY1) && (ratkaisuy >= MinY1)) {
            return testi;
        }
        return null;

    }

    public boolean nakeeko(Kordinaatti k, Kordinaatti k2, Monikulmio a) {

    //    System.out.println("eka: " + k.tulosta() + ", toka: " + k2.tulosta());
        Jono toistojoukko = new Jono();
        Kordinaatti[][] janat = a.PalautaJanat();
        Keko keko = new Keko(true, janat.length + 2);
        keko.Lisaa(k);
        keko.Lisaa(k2);
        toistojoukko.lisaa(k);
        toistojoukko.lisaa(k2);
        for (int i = 0; i < janat.length; i++) {
            Kordinaatti eka = janat[i][0];
            Kordinaatti toka = janat[i][1];
            Kordinaatti leikkauspiste = suoranjaJananleikkaus(eka, toka, k, k2, false);
            if (leikkauspiste != null) {
                if (!toistojoukko.etsi(leikkauspiste)) {
                    keko.Lisaa(leikkauspiste);
                    toistojoukko.lisaa(leikkauspiste);
                }

            }

        }
        int i = 1;
        boolean abc = true;
       
        while ((!keko.palautaTaulukko()[0].equals(k)) && (!keko.palautaTaulukko()[0].equals(k2))) {
            i++;
            keko.poistaMinimi();
            
           

        }
        Kordinaatti alku = null;
        Kordinaatti loppu = null;

        if (keko.palautaTaulukko()[0].equals(k)) {
            alku = k;
            loppu = k2;

        } else {
            alku = k2;
            loppu = k;

        }
        keko.poistaMinimi();

        Kordinaatti seuraava = (Kordinaatti) keko.poistaMinimi();
        Kordinaatti[] naapurit = etsivieruspisteet(alku, a);
        if (seuraava.equals(loppu)) {
            if (i % 2 == 1) {

                Kordinaatti d = suoranjaJananleikkaus(naapurit[0], naapurit[1], k, k2, true);
                if (d == null) {
                    return true;
                }
                return false;

            } else {
                //tähän:

                Kordinaatti d = suoranjaJananleikkaus(naapurit[0], naapurit[1], k, k2, true);
                if (d == null) {
                    return false;
                }
                return true;
            }
        }

        return false;
    }

    public Kordinaatti[] etsivieruspisteet(Kordinaatti k, Monikulmio a) {
        Kordinaatti[] z = new Kordinaatti[2];
        Kordinaatti[][] janat = a.PalautaJanat();
        int abc = 0;
        for (int i = 0; i < janat.length; i++) {
            Kordinaatti eka = janat[i][0];
            Kordinaatti toka = janat[i][1];
            if (eka.equals(k)) {
                z[abc] = toka;
                abc++;

            }
            if (toka.equals(k)) {
                z[abc] = eka;
                abc++;
            }
        }
        return z;
    }

    public int Suorajamonikulmioleikkaus(Kordinaatti k1, Kordinaatti k2, Monikulmio d) {
        Jono joukko = new Jono();
        int vasenleikkauksia = 0;
        int oikealeikkauksia = 0;
        Kordinaatti[][] janat = d.PalautaJanat();
        for (int i = 0; i < janat.length; i++) {

            Kordinaatti ratkaisu = suoranjaJananleikkaus(janat[i][0], janat[i][1], k1, k2, false);
            if (ratkaisu != null) {
                if ((ratkaisu.equals(janat[i][0])) || (ratkaisu.equals(janat[i][1]))) {
                    Kordinaatti[] naapurit = this.etsivieruspisteet(ratkaisu, d);
                    Kordinaatti e = suoranjaJananleikkaus(naapurit[0], naapurit[1], k1, k2, true);
                    if (e == null) {
                        continue;
                    } else {
                        if (joukko.etsi(ratkaisu)) {
                            continue;
                        }
                        if (ratkaisu.vertausoperaatio(k1) < 0) {
                            joukko.lisaa(ratkaisu);
                            vasenleikkauksia++;
                        }
                        if (ratkaisu.vertausoperaatio(k1) > 0) {
                            joukko.lisaa(ratkaisu);
                            oikealeikkauksia++;
                        }

                    }
                } else {
                    if (ratkaisu.vertausoperaatio(k1) < 0) {
                        vasenleikkauksia++;
                    }
                    if (ratkaisu.vertausoperaatio(k1) > 0) {
                        oikealeikkauksia++;
                    }
                }

            }

        }
        if ((vasenleikkauksia % 2 == 1) || (oikealeikkauksia % 2 == 1)) {
            return 1;
        }

        return 2;
    }

    public boolean kenenkaansisalla(Kordinaatti k, Jono monikulmiota) {
        Jonoiteroitava iter = monikulmiota.palautaEnsimmainen();
        Kordinaatti apukordinaatti = new Kordinaatti(k.palautaX() + 1, k.palautaY() + 1);

        while (iter != null) {
            Monikulmio xD = (Monikulmio) iter.palautaObjekti();
            int i = Suorajamonikulmioleikkaus(k, apukordinaatti, xD);
            if (i % 2 == 1) {
                return true;

            }

            iter = iter.palautaSeuraava();
        }

        return false;
    }
}
