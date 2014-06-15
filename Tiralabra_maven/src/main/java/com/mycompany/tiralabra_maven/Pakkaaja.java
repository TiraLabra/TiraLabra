/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.domain.Kontti;
import com.mycompany.tiralabra_maven.domain.Laatikko;
import com.mycompany.tiralabra_maven.domain.Laatikkotyyppi;
import com.mycompany.tiralabra_maven.domain.PakkausSuunnitelma;
import com.mycompany.tiralabra_maven.domain.Palkki;
import com.mycompany.tiralabra_maven.domain.Sijainti;
import com.mycompany.tiralabra_maven.domain.Tila;
import com.mycompany.tiralabra_maven.domain.Tilapalkki;
import com.mycompany.tiralabra_maven.structures.List;
import com.mycompany.tiralabra_maven.tools.Io;
import com.mycompany.tiralabra_maven.ui.Graphics;
import java.util.Random;

/**
 * Tämä luokka toimii ohjelman toiminnallisuuden perustana, ja suorittaa
 * pakkaamisen.
 *
 * @author Sami
 */
public class Pakkaaja {

    private Io io;

    /**
     * Konstruktori luokalle
     * @param io Käytetään I/O-operaatioihin
     */
    public Pakkaaja(Io io) {
        this.io = io;
    }

    /**
     * Tämä metodi aloittaa varsinaisen ohjelman ajamisen
     */
    public void run() {
        Kontti kontti = new Kontti(1000, 1000, 200);
        this.io.sout("kontti: " + kontti);
        List<Laatikkotyyppi> laatikot = generoiLaatikoita(132, 3);
        this.io.sout("laatikkotyyppejä: " + laatikot.size());

        PakkausSuunnitelma pakkausSuunnitelma = pakkaaKontti(kontti, laatikot, 10000);
//        Graphics g = new Graphics();
//        g.run(1000, 1000);

    }

    /**
     * Algoritmin ydin, joka pakkaa konttiin annetut laatikot.
     *
     * @param kontti Määritelty Kontti, johon laatikot pakataan
     * @param laatikot Pakattavat laatikot
     * @param aikaraja Aikaraja, jota käytetään rajoittamaan haun syvyyttä.
     * @return Paras löydetty PakkausSuunnitelma
     */
    public PakkausSuunnitelma pakkaaKontti(Kontti kontti, List<Laatikkotyyppi> laatikot, long aikaraja) {
        long t = System.currentTimeMillis();

        PakkausSuunnitelma pakkausSuunnitelma = new PakkausSuunnitelma();

        Tila tila = new Tila(kontti, laatikot); // aloitetaan tyhjästä kontista
        while (tila.vapaitaLaatikoita() > 0 || !tila.getTilapalkit().empty()) {
            this.io.sout("vapaita bokseja: " + tila.vapaitaLaatikoita());
            Tilapalkki tilapalkki = tila.getTilapalkit().pop();
//                Palkki palkki = haeParasPalkkiPalkeista(tilapalkki, palkit;
            Palkki palkki = haeParasPalkkiLaatikoista(tilapalkki, tila.getVapaatLaatikot());
            if (palkki != null) {
                tila.paivita(palkki, tilapalkki, kontti);
            }
            pakkausSuunnitelma = tila.getPakkausSuunnitelma();

//            }
        }

        return pakkausSuunnitelma;
    }

    /**
     * Tämä tekee parhaan mahdollisen palkin annetulle tilapalkille annetun
     * laatikkolistan mukaan.
     *
     * @param tilapalkki Tilapalkki, johon sopiva palkki luodaan
     * @param laatikot Lista laatikoista, joista palkki voidaan koota
     * @return Palautetaan paras löydetty palkki
     */
    public Palkki haeParasPalkkiLaatikoista(Tilapalkki tilapalkki, List<Laatikkotyyppi> laatikot) {
        int nx = 1, ny = 1, nz = 1;
        int n;
        int suurinTilavuus = 0;
        Palkki paras = null;
        this.io.sout("Etsitään paras palkki tilapalkille: " + tilapalkki);

        for (int j = 0; j < laatikot.size(); j++) {
            Laatikkotyyppi tyyppi = laatikot.get(j);
            if (tyyppi.getLaatikot().empty()) {
                continue;
            }
            Laatikko laatikko = tyyppi.getLaatikot().get(0);
            for (int i = 0; i < 6; i++) {
                laatikko.setOrientaatio(i);

                n = tyyppi.getLaatikot().size();
                this.io.sout("etsitään laatikkotyypille: " + tyyppi);
                this.io.sout("orientaatio: " + i);
                this.io.sout("laatikko: " + laatikko);
                if (laatikko.getX() > tilapalkki.getX() || laatikko.getY() > tilapalkki.getY() || laatikko.getZ() > tilapalkki.getZ()) {
                    this.io.sout("Laatikko ei mahdu");
                    continue;
                }
                this.io.sout("laatikoita käytettävänä: " + n);

                nz = tilapalkki.getZ() / laatikko.getZ();
                if (nz > n) {
                    nz = n;
                }
                nx = tilapalkki.getX() / laatikko.getX();
                if (nz * nx > n) {
                    nx = n / nz;
                }
                ny = tilapalkki.getY() / laatikko.getY();
                if (nz * nx * ny > n) {
                    ny = n / (nz * nx);
                }

                this.io.sout("kokeiltava: nx, ny, nz: " + nx + " " + ny + " " + nz);
                int tilavuus = nx * laatikko.getX() * ny * laatikko.getY() * nz * laatikko.getZ();

                if (tilavuus > suurinTilavuus) {
                    suurinTilavuus = tilavuus;
                    paras = new Palkki(tyyppi, nx, ny, nz);
                }
            }
        }

        if (paras == null) {
            return null;
        }

        this.io.sout("Paras palkki: " + paras);
        this.io.sout("laatikoita akseleittain nx, ny, nz: " + paras.getNx() + " " + paras.getNy() + " " + paras.getNz());
        this.io.sout("palkin koko, x, y, z: " + paras.getNx() * paras.getTyyppi().getX() + " " + paras.getNy() * paras.getTyyppi().getY() + " " + paras.getNz() * paras.getTyyppi().getZ());
        this.io.sout("palkkiin menee " + paras.getNx() * paras.getNy() * paras.getNz() + " laatikkoa");

        return paras;
    }

    /**
     * Selvittää mahtuuko palkki tilapalkkiin vai ei. True tarkoittaa mahtumista
     *
     * @param tilapalkki Tilapalkki, johon palkki yritetään mahduttaa
     * @param palkki Palkki, joka yritetään mahduttaa tilapalkkiin
     * @return Totuusarvo mahtumisesta, true tarkoittaa mahtumista
     */
    public boolean mahtuu(Tilapalkki tilapalkki, Palkki palkki) {
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
    public List<Laatikkotyyppi> generoiLaatikoita(int laatikoidenMaara, int tyyppienMaara) {
//        Tarkasta syötteet
        Random random = new Random();
        List<Laatikkotyyppi> laatikot = new List<Laatikkotyyppi>();
        this.io.sout("Generoidaan laatikoita:");

        for (int i = 0; i < tyyppienMaara; i++) {
            laatikot.add(new Laatikkotyyppi(random.nextInt(99) + 1, random.nextInt(99) + 1, random.nextInt(99) + 1));
        }

        int i = 0;
        outerloop:
        while (true) {
            for (int j = 0; j < laatikot.size(); j++) {
                Laatikkotyyppi tyyppi = laatikot.get(j);
                if (i == laatikoidenMaara) {
                    break outerloop;
                }
                Laatikko laatikko = new Laatikko(tyyppi, new Sijainti(), 0);
                tyyppi.getLaatikot().add(laatikko);
                i++;

            }
        }

        for (int j = 0; j < laatikot.size(); j++) {
            Laatikkotyyppi tyyppi = laatikot.get(j);
            this.io.sout("laatikkotyyppi: " + tyyppi);
            this.io.sout("laatikoita: " + tyyppi.getLaatikot().size());
        }

        return laatikot;
    }

}
