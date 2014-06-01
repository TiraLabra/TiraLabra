package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.domain.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

public class App {

//    private static int intti;
    /**
     * Main-metodi
     *
     * @param args
     */
    public static void main(String[] args) {
        //        intti = 1;
        Kontti kontti = new Kontti(1000, 1000, 200);
        ArrayList<Laatikkotyyppi> laatikot = generoiLaatikoita(100, 1);
        System.out.println("laatikkotyyppejä: " + laatikot.size());
        PakkausSuunnitelma pakkausSuunnitelma = pakkaaKontti(kontti, laatikot, 10000);
        //        if (boksit == null){
        //            System.out.println("lsdkfjsdlfkjsd");
        //        }
        int i = 0;
//        for (Palkki palkki : pakkausSuunnitelma.getPalkit()) {
//            for (Laatikko laatikko : palkki.getLaatikot()) {
//                Laatikkotyyppi tyyppi = laatikko.getTyyppi();
//                Sijainti sijainti = laatikko.getSijainti();
//                System.out.println("Laatikko " + i + ", sijainti x: " + sijainti.getPosX() + ", y: " + sijainti.getPosY() + ", z: " + sijainti.getPosZ());
//                i++;
//            }
//
//        }
    }

    /**
     * Algoritmin ydin, joka pakkaa konttiin annetut laatikot.
     *
     * @param kontti Määritelty Kontti, johon laatikot pakataan
     * @param laatikot Pakattavat laatikot
     * @param aikaraja Aikaraja, jota käytetään rajoittamaan haun syvyyttä.
     * @return Paras löydetty PakkausSuunnitelma
     */
    public static PakkausSuunnitelma pakkaaKontti(Kontti kontti, ArrayList<Laatikkotyyppi> laatikot, long aikaraja) {
        long t = System.currentTimeMillis();

        PakkausSuunnitelma paras = new PakkausSuunnitelma();

        int haunVaativuus = 1; // määrittelee haun tarkkuuden iteraatiossa
        // tehdään uusia pakkaussuunnitelmia kunnes aikaraja umpeutuu
        int i = 0;
        while (i < 1) { // tähän toistaiseksi vain yksi pakkaussuunnitelma
            Tila tila = new Tila(kontti, laatikot); // aloitetaan tyhjästä kontista
            while (tila.getVapaatTilapalkit().size() > 0) {
                Tilapalkki tilapalkki = tila.getVapaatTilapalkit().pop();
//                Palkki palkki = haeParasPalkkiPalkeista(tilapalkki, palkit, haunVaativuus);
                Palkki palkki = haeParasPalkkiLaatikoista(tilapalkki, laatikot, haunVaativuus);
                if (palkki != null) {
//                    System.out.println("palkki on olemassa");
                    tila.paivita(palkki, tilapalkki, kontti);
                }
            }
            //            if (parempi(tila.getPakkausSuunnitelma(), paras)) {
            paras = tila.getPakkausSuunnitelma();

//            }
            haunVaativuus *= 2;
            i++;
        }

        return paras;
    }

    /**
     * Hakee parametrinä saatuun tilapalkkiin parhaiten sopivan palkin
     *
     * @param tilapalkki Tilapalkki, joka halutaan täyttää
     * @param palkit Lista palkeista, joista paras palkki etsitään
     * @param haunVaativuus Parametri, jota käytetään haun syvyyden
     * määrittelemiseen
     * @return Paras löydetty palkki
     */
    public static Palkki haeParasPalkkiPalkeista(Tilapalkki tilapalkki, ArrayList<Palkki> palkit, int haunVaativuus) {
        Palkki paras = null;
        for (Palkki palkki : palkit) {
            if (mahtuu(tilapalkki, palkki)) {
                if (paras == null || paras.getTilavuus() < palkki.getTilavuus()) {
                    paras = palkki;
                }
            }
        }

        return paras;
    }
/**
 * Tämä tekee parhaan mahdollisen palkin annetulle tilapalkille annetun laatikkolistan mukaan.
 * 
 * @param tilapalkki Tilapalkki, johon sopiva palkki luodaan
 * @param laatikot Lista laatikoista, joista palkki voidaan koota
 * @param haunVaativuus Parametrin haun vaativuuden määrittelemiseen
 * @return Palautetaan paras löydetty palkki
 */
    public static Palkki haeParasPalkkiLaatikoista(Tilapalkki tilapalkki, ArrayList<Laatikkotyyppi> laatikot, int haunVaativuus) {
        int nx = 1, ny = 1, nz = 1;
        int suurinTilavuus = 0;
        Laatikkotyyppi paras = null;

        for (Laatikkotyyppi tyyppi : laatikot) {
            // kolmelle orientaatiolle tämä:
            System.out.println("tilapalkki: " + tilapalkki.getX() + " " + tilapalkki.getY() + " " + tilapalkki.getZ() + " ");
            int n = tyyppi.getLaatikot().size();
            nz = tilapalkki.getZ() / tyyppi.getZ();
            if (nz > n) {
                nz = n;
            }
            nx = tilapalkki.getX() / tyyppi.getX();
            if (nz * nx > n) {
                nx = n / nz;
            }
            ny = tilapalkki.getY() / tyyppi.getY();
            if (nz * nx * ny > n) {
                ny = n / (nz * nx);
            }

            if (nx * tyyppi.getX() * ny * tyyppi.getY() * nz * tyyppi.getZ() > suurinTilavuus) {
                suurinTilavuus = nx * tyyppi.getX() * ny * tyyppi.getY() * nz * tyyppi.getZ();
                paras = tyyppi;
            }
        }
        System.out.println("nx, ny, nz: " + nx + " " + ny + " " + nz);

        if (paras == null) {
            return null;
        }

        Palkki palkki = new Palkki(nx*paras.getX(), ny*paras.getY(), nz*paras.getZ());
        palkki.lisaaLaatikot(paras, nx, ny, nz);
        return palkki;
    }

    /**
     * Selvittää mahtuuko palkki tilapalkkiin vai ei. True tarkoittaa mahtumista
     *
     * @param tilapalkki Tilapalkki, johon palkki yritetään mahduttaa
     * @param palkki Palkki, joka yritetään mahduttaa tilapalkkiin
     * @return Totuusarvo mahtumisesta, true tarkoittaa mahtumista
     */
    public static boolean mahtuu(Tilapalkki tilapalkki, Palkki palkki) {
        return tilapalkki.getX() >= palkki.getX() && tilapalkki.getY() >= palkki.getY() && tilapalkki.getZ() >= palkki.getZ();
    }

    /**
     * Tämä metodi generoi randomisti laatikoita pakattavaksi, annettujen
     * parametrien puitteissa.
     *
     * @param laatikoidenMaara Haluttu laatikoiden määrä
     * @param tyyppienMaara Haluttu laatikoiden määrä
     * @return Lista generoiduista laatikoista
     */
    public static ArrayList<Laatikkotyyppi> generoiLaatikoita(int laatikoidenMaara, int tyyppienMaara) {
//        Tarkasta syötteet
        Random random = new Random();
        ArrayList<Laatikkotyyppi> laatikot = new ArrayList<Laatikkotyyppi>();
        Laatikkotyyppi laatikkotyyppi = null;
        for (int i = 0; i < tyyppienMaara; i++) {
            laatikkotyyppi = new Laatikkotyyppi(random.nextInt(99) + 1, random.nextInt(99) + 1, random.nextInt(99) + 1);
            for (int j = 0; j < laatikoidenMaara / tyyppienMaara; j++) {
                Laatikko laatikko = new Laatikko(laatikkotyyppi, null, 0);
                laatikkotyyppi.getLaatikot().add(laatikko);
            }
            laatikot.add(laatikkotyyppi);
            System.out.println("laatikoita: " + laatikkotyyppi.getLaatikot().size());
            System.out.println("laatikkotyyppi, x,y,z: " + laatikkotyyppi.getX() + " " + laatikkotyyppi.getY() + " " + laatikkotyyppi.getZ());
        }

        return laatikot;
    }

    /**
     * Selvittää kumpi parametrien pakkaussuunnitelmista on optimaalisempi.
     * Palautetaan true, mikäli ensimmäinen parametri on parempi.
     *
     * @param pakkausSuunnitelma Ensimmäinen pakkaussuunnitelma
     * @param paras Toinen pakkaussuunnitelma
     * @return True, mikäli ensimmäinen on parempi
     */
    public static boolean parempi(PakkausSuunnitelma pakkausSuunnitelma, PakkausSuunnitelma paras) {
        return true;
        //        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
