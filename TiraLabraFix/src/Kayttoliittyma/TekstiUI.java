/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;

import Tietorakenteet.Jono.Jono;
import Tietorakenteet.Jono.Jonoiteroitava;
import Tietorakenteet.Kordinaatti;
import Tietorakenteet.Monikulmio;
import java.util.Scanner;
import logiikka.Logiikka;

/**
 *
 * @author Serafim
 */
public class TekstiUI {

    private Logiikka logiikka;
    private Scanner scanneri;

    public TekstiUI() {
        this.logiikka = new Logiikka();
        this.scanneri = new Scanner(System.in);

    }

    public void run() {
        System.out.println("Voit tehdä seuraavai asioita:");
        System.out.println("[1] lisaamonikulmio");
        System.out.println("[2] palautamonikulmio");
        System.out.println("[3] asetaalku");
        System.out.println("[4] asetaloppu");
        System.out.println("[5] laske");
        System.out.println("[6] poistakaikki");
        System.out.println("[7] safemode");
        System.out.println("[8] suorituskyky");
        System.out.println("[n] poistu");
        while (true) {
            System.out.print("Komento: ");
            String k = scanneri.next();
            if (k.equals("lisaamonikulmio")) {
                lisaamonikulmio();
            }
            if (k.equals("palautamonikulmio")) {
                listaaMonikulmiot();
            }
            if (k.equals("poistu")) {
                return;
            }
            if (k.equals("laske")) {
                laskeLyhinAnalyyttisesti();
            }
            if (k.equals("asetaalku")) {
                asetaAlku();
            }
            if (k.equals("asetaloppu")) {
                asetaLoppu();
            }
            if (k.equals("poistakaikki")) {
                poistakaikki();
            }
            if (k.equals("safemode")) {
                safemode();
            }
            if (k.equals("poista")) {
                poista();
            }
            if (k.equals("suorituskyky"))
            {
            luolatest();
            }
        }

    }

    public void luolatest() {
        System.out.println("Muodossa maksimi,vaativuus");
        String k = this.scanneri.next();
        String[] b = k.split(",");
        if (b.length != 2) {
            System.out.println("Syntaksi virhe");
        } else {
            int i = 0;
            int j = 0;
            try {
                i = Integer.parseInt(b[0]);
                j = Integer.parseInt(b[1]);

            } catch (Exception e) {
                System.out.println("Virhe Syntaksissa");
                return;

            }
            int bzb = this.logiikka.luolasuorituskyky(i, j);
            if (bzb != 0) {
                System.out.println("Jokin meni pieleen");
            } else {
                Jono ABC = this.logiikka.palautaReitti();
                if (ABC == null) {
                    System.out.println("Reittiä ei löytyny");

                } else {
                    Jonoiteroitava iter = ABC.palautaEnsimmainen();
                    while (iter != null) {
                        Kordinaatti kz = (Kordinaatti) iter.palautaObjekti();
                        System.out.println(kz.tulosta());
                        iter = iter.palautaSeuraava();
                    }
                    double c = logiikka.palautaPituus();
                    System.out.println("Reitinkokonaispituus: " + c);
                    long a = logiikka.palautaLaskemisaika();
                    long abc = logiikka.palautaAlgoritmiaika();
                    System.out.println("Yhteensä käytettiin: " + a + " aikaa");
                    System.out.println("Josta A-star: " + abc + " aikaa");

                }
            }

        }

    }

    public void poista() {
        System.out.println("Laita monikulmion numero jonka haluaisit poistaa");
        System.out.print("numero: ");
        String k = this.scanneri.next();
        int i = 0;
        try {
            i = Integer.parseInt(k);

        } catch (Exception e) {
            System.out.println("Virhe Syntaksissa");
            return;

        }
        int j = this.logiikka.poistaNs(i);
        if (j == 0) {
            System.out.println("Poistettu!");
        }
        if (j == 1) {
            System.out.println("Ei löytyny");
        }
    }

    public void safemode() {
        System.out.println("y ON, n Off");
        System.out.print("Mode: ");
        String k = scanneri.next();
        if (k.charAt(0) == 'y') {
            this.logiikka.asetaSafemode(true);
        }
        if (k.charAt(0) == 'n') {
            this.logiikka.asetaSafemode(false);
        }
    }

    public void poistakaikki() {
        this.logiikka.clear();
    }

    public void lisaamonikulmio() {
        System.out.print("Lisaa monikulmion: ");
        String k = scanneri.next();
        String[] kulmiot = k.split(";");
        if (kulmiot.length == 0) {
            System.out.println("Virhe Syntaksissa");
            return;
        }
        Jono kulmia = new Jono();
        for (int i = 0; i < kulmiot.length; i++) {

            String[] kordinaatit = kulmiot[i].split(",");
            if (kordinaatit.length != 2) {
                System.out.println("Virhe Syntaksissa");
                return;
            }
            double yksi = 0;
            double kaksi = 0;
            try {
                yksi = Double.parseDouble(kordinaatit[0]);
                kaksi = Double.parseDouble(kordinaatit[1]);

            } catch (Exception e) {
                System.out.println("Virhe Syntaksissa");
                return;

            }

            Kordinaatti uusi = new Kordinaatti(yksi, kaksi);
            kulmia.lisaa(uusi);

        }
        if (kulmia.palautaKoko() < 3) {
            System.out.println("Monikulmiossa alle 3 kulmaa");
            return;
        }
        int i = this.logiikka.lisaaMonikulmio(kulmia);
        if (i == 0) {
            System.out.println("Monkulmio lisätty");
        } else if (i == 1) {
            System.out.println("Ei lisätty, leikkasi itseään");
        } else if (i == 2) {
            System.out.println("Ei lisätty, ei sopinu muiden joukkoon");
        } else if (i == 3) {
            System.out.println("Ei lisätty, koska oli jonkin monikulmion sisällä");
        }

    }

    public void poistamonilumio() {

    }

    public void asetaAlku() {
        System.out.print("alkupiste: ");
        String k = scanneri.next();
        String[] a = k.split(",");
        double eka = 0;
        double toka = 0;
        try {
            eka = Double.parseDouble(a[0]);
            toka = Double.parseDouble(a[1]);

        } catch (Exception e) {
            System.out.println("Virhe Syntaksissa");
            return;

        }
        Kordinaatti abc = new Kordinaatti(eka, toka);
        int j = this.logiikka.asetaAlku(abc);
        if (j == 0) {
            System.out.println("Lisätty!");
        }
        if (j == 1) {
            System.out.println("Ei Lisätty : Sama kuin loppupiste");
        }
        if (j == 2) {
            System.out.println("Ei lisätty : On jonkin monikulmion sisällä");
        }

    }

    public void asetaLoppu() {
        System.out.print("loppupiste: ");
        String k = scanneri.next();
        String[] a = k.split(",");
        double eka = 0;
        double toka = 0;
        try {
            eka = Double.parseDouble(a[0]);
            toka = Double.parseDouble(a[1]);

        } catch (Exception e) {
            System.out.println("Virhe Syntaksissa");
            return;

        }
        Kordinaatti abc = new Kordinaatti(eka, toka);
        int j = this.logiikka.asetaLoppu(abc);
        if (j == 0) {
            System.out.println("Lisätty!");
        }
        if (j == 1) {
            System.out.println("Ei Lisätty : Sama kuin loppupiste");
        }
        if (j == 2) {
            System.out.println("Ei lisätty : On jonkin monikulmion sisällä");
        }

    }

    public void laskeLyhinAnalyyttisesti() {
        int i = this.logiikka.laskeReitti();
        if (i == 1) {
            System.out.println("Alkupistettä ei ole astetettu!");
            return;
        }
        if (i == 2) {
            System.out.println("Loppupistettä ei ole asetettu!");
            return;
        }
        if (i == 3) {
            System.out.println("Reittiä ei löytyny");
            return;
        }
        if (i == 4) {
            System.out.println("Alkupiste jonkun sisällä");
            return;
        }
        if (i == 5) {
            System.out.println("Loppupsite jonkun sisällä");
            return;
        }
        Jono j = this.logiikka.palautaReitti();
        if (j == null) {
            System.out.println("Reittiä ei löytyny");

        } else {
            Jonoiteroitava iter = j.palautaEnsimmainen();
            while (iter != null) {
                Kordinaatti k = (Kordinaatti) iter.palautaObjekti();
                System.out.println(k.tulosta());
                iter = iter.palautaSeuraava();
            }
            double c = logiikka.palautaPituus();
            System.out.println("Reitinkokonaispituus: " + c);
            long a = logiikka.palautaLaskemisaika();
            long b = logiikka.palautaAlgoritmiaika();
            System.out.println("Yhteensä käytettiin: " + a + " aikaa");
            System.out.println("Josta A-star: " + b + " aikaa");

        }

    }

    public void piirratiedostoon() {

    }

    public void listaaMonikulmiot() {
        Jono j = this.logiikka.palautaMonikulmio();
        Jonoiteroitava iter = j.palautaEnsimmainen();
        int i = 1;
        while (iter != null) {
            Monikulmio xD = (Monikulmio) iter.palautaObjekti();
            System.out.println(i + ": " + xD.tulosta());
            iter = iter.palautaSeuraava();
            i++;
        }
    }

}
