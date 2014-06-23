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
import com.mycompany.tiralabra_maven.testing.Testaus;
import com.mycompany.tiralabra_maven.tools.FileHandler;
import com.mycompany.tiralabra_maven.tools.Io;
import java.io.File;
import java.io.IOException;

/**
 * Tämä luokka toimii ohjelman toiminnallisuuden perustana, ja suorittaa
 * pakkaamisen.
 *
 * @author Sami
 */
public class Pakkaaja {

    private Io io;
    private Kontti kontti;

    /**
     * Konstruktori luokalle
     *
     * @param io Käytetään I/O-operaatioihin
     */
    public Pakkaaja(Io io) {
        this.io = io;
    }

    /**
     * Tämä metodi suorittaa komentojen vastaanottamisen ja niiden ohjaamisen.
     */
    public void run() throws IOException {
        Testaus t = new Testaus(this.io, new Kontti(6058, 2438, 2591));

        whileloop:
        while (true) {
            int komento = this.io.lueLuku("Valitse komento: 1 pakkaa kontti, 2 aja vakiotestit, 3 aja satunnaistestit, 4 generoi testitiedostot, 0 lopeta");
            switch (komento) {
                case 0:
                    break whileloop;
                case 1:
                    String tiedostonNimi = kysySyotteet();
                    List<Laatikkotyyppi> laatikot;
                    try {
                        laatikot = FileHandler.lueLaatikot(new File(tiedostonNimi));
                    } catch (IOException ex) {
                        this.io.sout("Tiedostoa ei löytynyt!");
                        break;
                    }
                    PakkausSuunnitelma pakkausSuunnitelma = pakkaaKontti(kontti, laatikot);
                    System.out.println("Pakattiin " + pakkausSuunnitelma.getLaatikoita() + " laatikkoa");
//                    System.out.println("Pakattujen laatikoiden tilavuus: " + pakkausSuunnitelma.getTilavuus());
//                    System.out.println("Kontin tilavuus: " + kontti.getTilavuus());
                    this.io.sout("Täyttösuhde: " + pakkausSuunnitelma.getTilavuus() * 100 / kontti.getTilavuus() + "%");
                    this.io.sout("Tallennetaan tiedostoon output.txt");
                    FileHandler.kirjoitaString(new File("output.txt"), pakkausSuunnitelma.toString());
                    break;
                case 2:
                    t.ajaVakiotestit();
                    break;
                case 3:
                    int maara = this.io.lueLuku("Kuinka monta iteraatiota tehdään?");
                    t.ajaSatunnaistestit(maara);
//                    System.out.println("Ei käyttössä");
                    break;
                case 4:
                    t.generoiTestitiedostot();
                    break;
            }
        }

    }

    /**
     * Tämä metodi kysyy käyttäjältä tarpeellisia tietoja, kun halutaan pakata
     * kontti. Palauttaa laatikot sisältävän tiedoston nimen
     *
     * @return Laatikoiden tiedot sisältävän tiedoston nimi
     */
    private String kysySyotteet() {
        int laatikoita;
        int tyyppeja;

        whileloop:
        while (true) {
            int k = this.io.lueLuku("Valitse kontti: 1 (6058x2438x2591), 2 (100x100x100), 3 (2991x2438x2591), 4 (12192x2438x2591)");
            switch (k) {
                case 1:
                    kontti = new Kontti(6058, 2438, 2591);
                    break whileloop;
                case 2:
                    kontti = new Kontti(100, 100, 100);
                    break whileloop;
                case 3:
                    kontti = new Kontti(2991, 2438, 2591);
                    break whileloop;
                case 4:
                    kontti = new Kontti(12192, 2438, 2591);
                    break whileloop;
                default:
                    break;
            }
        }

        return this.io.lueRivi("Anna luettavan tiedoston nimi");

    }

    /**
     * Algoritmin ydin, joka pakkaa konttiin annetut laatikot.
     *
     * @param kontti Määritelty Kontti, johon laatikot pakataan
     * @param laatikot Pakattavat laatikot
     * @return Paras löydetty PakkausSuunnitelma
     */
    public PakkausSuunnitelma pakkaaKontti(Kontti kontti, List<Laatikkotyyppi> laatikot) {
        PakkausSuunnitelma pakkausSuunnitelma = new PakkausSuunnitelma();

        Tila tila = new Tila(kontti, laatikot);
        while (!tila.getTilapalkit().empty()) {
//            this.io.sout("vapaita bokseja: " + tila.vapaitaLaatikoita());
            Tilapalkki tilapalkki = tila.getTilapalkit().pop();
            Palkki palkki = haeParasPalkkiLaatikoista(tilapalkki, tila.getVapaatLaatikot());
            if (palkki != null) {
                tila.paivita(palkki, tilapalkki);
            }
            pakkausSuunnitelma = tila.getPakkausSuunnitelma();

        }

        return pakkausSuunnitelma;
    }

    /**
     * Tämä käy läpi laatikot, ja parhaan mahdollisen palkin.
     *
     * @param tilapalkki Tilapalkki, johon sopiva palkki luodaan
     * @param laatikot Lista laatikoista, joista palkki voidaan koota
     * @return Palautetaan paras löydetty palkki
     */
    public Palkki haeParasPalkkiLaatikoista(Tilapalkki tilapalkki, List<Laatikkotyyppi> laatikot) {
        Palkki paras = null;
//        System.out.println("");
        this.io.sout("Etsitään paras palkki tilapalkille: " + tilapalkki);

        for (int j = 0; j < laatikot.size(); j++) {
            Laatikkotyyppi tyyppi = laatikot.get(j);
            if (tyyppi.getLaatikoita() == 0) {
                continue;
            }
            if (tyyppi.getTilavuus() > tilapalkki.getTilavuus()) {
                continue;
            }

            Laatikko laatikko = new Laatikko(tyyppi, new Sijainti(), 0);
//            System.out.println("laatikko " + laatikko);
            Palkki p = valitseOrientaatio(tilapalkki, laatikko, tyyppi.getLaatikoita());
            if (p == null) {
                continue;
            }
            if (paras == null) {
                paras = p;
            } else if (p.getTilavuus() > paras.getTilavuus()) {
                paras = p;
            }

        }

        if (paras == null) {
            System.out.println("ei löytynyt palkkia");
            this.io.sout("-----------------------------------");

            return null;
        }

        this.io.sout("Paras palkki: " + paras);
//        this.io.sout("laatikoita akseleittain nx, ny, nz: " + paras.getNx() + " " + paras.getNy() + " " + paras.getNz());
        this.io.sout("palkin koko, x, y, z: " + paras.getX() + " " + paras.getY() + " " + paras.getZ());
        this.io.sout("palkkiin menee " + paras.getNx() * paras.getNy() * paras.getNz() + " laatikkoa");
        this.io.sout("-----------------------------------");
        return paras;
    }

    /**
     * Tämä selvittää mikä on laatikon paras orientaatio tietylle tilapalkille.
     *
     * @param tilapalkki Täytettävä tilapalkki
     * @param laatikko Laatikko, jonka tyyppisiä laatikoita halutaan laittaa
     * palkkiin
     * @param n Laatikoiden lukumäärä
     * @return Laatikosta tehty paras mahdollinen palkki, joka mahtuu
     * tilapalkkiin.
     */
    public Palkki valitseOrientaatio(Tilapalkki tilapalkki, Laatikko laatikko, int n) {
        Palkki paras = null;
        long suurinTilavuus = 0;
        long nx, ny, nz;
//        this.io.sout("laatikoita käytettävänä: " + n);

        for (int i = 0; i < 6; i++) {
            laatikko.setOrientaatio(i);

            if (laatikko.getX() > tilapalkki.getX() || laatikko.getY() > tilapalkki.getY() || laatikko.getZ() > tilapalkki.getZ()) {
                // laatikko ei mahdu näin päin
                continue;
            }
            long[] maarat = orientaationMaaratAkseleittain(tilapalkki, laatikko, n);
            nx = maarat[0];
            ny = maarat[1];
            nz = maarat[2];
//            System.out.println("nn: " + nx * ny * nz);

            long tilavuus = nx * laatikko.getX() * ny * laatikko.getY() * nz * laatikko.getZ();

            if (tilavuus > suurinTilavuus) {
                suurinTilavuus = tilavuus;
                paras = new Palkki(tilapalkki.getSijainti(), laatikko, nx, ny, nz);
            }
        }
        if (paras != null) {
            paras.lisaaLaatikot();
        }
        return paras;
    }

    /**
     * Selvittää kuinka monta laatikkoa kannattaa laittaa minkäkin akselin
     * mukaan palkkiin, jotta tilapalkki täyttyy mahdollisimman täydellisesti.
     *
     * @param tilapalkki Täytettävä tilapalkki
     * @param laatikko Laatikko, jonka tyyppisiä halutaan laittaa palkkiin
     * @param n Laatikoiden lukumäärä
     * @return Taulukko, joka sisältä kolme long-tyyppistä arvoa (nx, ny, nz),
     * jotka edustavat kullekin akselille mahtuvien laatikoiden lukumäärää.
     */
    public long[] orientaationMaaratAkseleittain(Tilapalkki tilapalkki, Laatikko laatikko, int n) {
        long nx, ny, nz;
//            this.io.sout("laatikoita käytettävänä: " + n);
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
        long[] taulu = {nx, ny, nz};
        return taulu;
    }

}
