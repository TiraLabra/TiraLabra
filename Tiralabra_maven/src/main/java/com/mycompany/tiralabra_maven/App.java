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
        System.out.println("kontti: " + kontti);
        ArrayList<Laatikkotyyppi> laatikot = generoiLaatikoita(132, 3);
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

        // tehdään uusia pakkaussuunnitelmia kunnes aikaraja umpeutuu
        int i = 0;
        while (i < 1) { // tähän toistaiseksi vain yksi pakkaussuunnitelma
            Tila tila = new Tila(kontti, laatikot); // aloitetaan tyhjästä kontista
            while (tila.vapaitaLaatikoita() > 0) {
                System.out.println("vapaita bokseja: " + tila.vapaitaLaatikoita());
                Tilapalkki tilapalkki = tila.getTilapalkit().pop();
//                Palkki palkki = haeParasPalkkiPalkeista(tilapalkki, palkit;
                Palkki palkki = haeParasPalkkiLaatikoista(tilapalkki, tila.getVapaatLaatikot());
                if (palkki != null) {
                    tila.paivita(palkki, tilapalkki, kontti);
                }
            }
            //            if (parempi(tila.getPakkausSuunnitelma(), paras)) {
            paras = tila.getPakkausSuunnitelma();

//            }
            i++;
        }

        return paras;
    }

    /**
     * Hakee parametrinä saatuun tilapalkkiin parhaiten sopivan palkin
     *
     * @param tilapalkki Tilapalkki, joka halutaan täyttää
     * @param palkit Lista palkeista, joista paras palkki etsitään
     * määrittelemiseen
     * @return Paras löydetty palkki
     */
    public static Palkki haeParasPalkkiPalkeista(Tilapalkki tilapalkki, ArrayList<Palkki> palkit) {
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
     * Tämä tekee parhaan mahdollisen palkin annetulle tilapalkille annetun
     * laatikkolistan mukaan.
     *
     * @param tilapalkki Tilapalkki, johon sopiva palkki luodaan
     * @param laatikot Lista laatikoista, joista palkki voidaan koota
     * @return Palautetaan paras löydetty palkki
     */
    public static Palkki haeParasPalkkiLaatikoista(Tilapalkki tilapalkki, ArrayList<Laatikkotyyppi> laatikot) {
        int nx = 1, ny = 1, nz = 1;
        int n;
        int suurinTilavuus = 0;
        Palkki paras = null;
        System.out.println("Etsitään paras palkki tilapalkille: " + tilapalkki);

        // orientaatiot pitäisi vielä käydä läpi
        for (Laatikkotyyppi tyyppi : laatikot) {
            n = tyyppi.getLaatikot().size();
            System.out.println("etsitään laatikkotyypille: " + tyyppi);
            if (tyyppi.getX() > tilapalkki.getX() || tyyppi.getY() > tilapalkki.getY() || tyyppi.getZ() > tilapalkki.getZ()) {
                System.out.println("Laatikko ei mahdu");
                continue;
            }
            System.out.println("laatikoita käytettävänä: " + n);

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

            System.out.println("kokeiltava: nx, ny, nz: " + nx + " " + ny + " " + nz);
            int tilavuus = nx * tyyppi.getX() * ny * tyyppi.getY() * nz * tyyppi.getZ();

            if (tilavuus > suurinTilavuus) {
                suurinTilavuus = tilavuus;
                paras = new Palkki(tyyppi, nx, ny, nz);
            }
        }

        if (paras == null) {
            return null;
        }

        System.out.println("Paras palkki: " + paras);
        System.out.println("laatikoita akseleittain nx, ny, nz: " + paras.getNx() + " " + paras.getNy() + " " + paras.getNz());
        System.out.println("palkin koko, x, y, z: " + paras.getNx() * paras.getTyyppi().getX() + " " + paras.getNy() * paras.getTyyppi().getY() + " " + paras.getNz() * paras.getTyyppi().getZ());
        System.out.println("palkkiin menee " + paras.getNx() * paras.getNy() * paras.getNz() + " laatikkoa");

        return paras;
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
        System.out.println("Generoidaan laatikoita:");

        for (int i = 0; i < tyyppienMaara; i++) {
            laatikot.add(new Laatikkotyyppi(random.nextInt(99) + 1, random.nextInt(99) + 1, random.nextInt(99) + 1));
        }

        int i = 0;
        outerloop:
        while (true) {
            for (Laatikkotyyppi tyyppi : laatikot) {
                if (i == laatikoidenMaara){
                    break outerloop;
                }
                Laatikko laatikko = new Laatikko(tyyppi, null, 0);
                tyyppi.getLaatikot().add(laatikko);
                i++;
                
            }
        }

        for (Laatikkotyyppi tyyppi : laatikot) {
            System.out.println("laatikkotyyppi: " + tyyppi);
            System.out.println("laatikoita: " + tyyppi.getLaatikot().size());
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
