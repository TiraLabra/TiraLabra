/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.gui.Ruutu;
import java.util.PriorityQueue;
//import java.util.Scanner;


/**
 *
 * @author mikko
 */
public class Simulaatio extends Thread {

    private final Koordinaatit alku;
    private final Koordinaatit maali;
    private final Piirtologiikka piirtologiikka;
    private final int leveys;
    private final int korkeus;
    private boolean valmis;
    //private Scanner sc;

    private int[][] ruudukko = {
        {1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    public Simulaatio(Piirtologiikka piirtologiikka) {
        this.alku = new Koordinaatit(0, 0);
        this.maali = new Koordinaatit(9, 5);
        this.leveys = 10;
        this.korkeus = 10;
        this.piirtologiikka = piirtologiikka;
        this.valmis = false;
        //this.sc = new Scanner(System.in);
    }

    @Override
    public void run() {
        alustaPiirtologiikka();

        //Tehdään priorityQueue joka palauttaa aina sen solmun, jolle (etäisyys alkuun + arvioitu etäisyys loppuun) on pienin
        Vertailija vertailija = new Vertailija(maali);
        PriorityQueue<Solmu> tutkimattomat = new PriorityQueue<Solmu>(100, vertailija);

        Solmu solmu = new Solmu(alku, 0, null);

        System.out.println("alkutilanne: " + solmu);

        while (!solmu.getKoordinaatit().equals(maali)) {
            //käydään läpi solmun naapurisolmut ja lisätään ne tutkittavien joukkoon.
            for (Suunta suunta : Suunta.values()) {
                Koordinaatit koord = solmu.getKoordinaatit().suuntaan(suunta);
                if (koordinaatitUlkopuolella(koord)) {
                    continue;
                }
                if (ruudukko[koord.getY()][koord.getX()] == 0) {
                    continue;
                }

                //Lisätään tutkittaviin uusi solmu, jonka kuljetuksi matkaksi annetaan tämän solmun kuljettu matka + maaston vaikeustaso
                tutkimattomat.add(new Solmu(koord, solmu.getKuljettuMatka() + ruudukko[koord.getY()][koord.getX()], solmu));
                piirtologiikka.setRuutu(koord.getX(), koord.getY(), Ruutu.TUTKIMATON);

            }
            //piirtologiikka.setRuutu(solmu.getKoordinaatit().getX(), solmu.getKoordinaatit().getY(), Ruutu.TUTKITTU);
            solmu = tutkimattomat.poll();
            //piirtologiikka.setRuutu(solmu.getKoordinaatit().getX(), solmu.getKoordinaatit().getY(), Ruutu.KASITTELYSSA);
//
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException ex) {
//            }
            //System.out.println(solmu);
            //System.out.println(solmu);
        }

        System.out.println("REITTI (lopusta alkuun:");
        while (solmu != null) {
            piirtologiikka.setRuutu(solmu.getKoordinaatit().getX(), solmu.getKoordinaatit().getY(), Ruutu.REITTI);
            System.out.println(solmu);
            solmu = solmu.getEdellinen();
        }
        this.valmis = true;
    }

    private void alustaPiirtologiikka() {
        for (int x = 0; x < this.leveys; x++) {
            for (int y = 0; y < this.korkeus; y++) {
                if (this.alku.getX() == x && this.alku.getY() == y) {
                    this.piirtologiikka.setRuutu(x, y, Ruutu.ALKU);
                } else if (this.maali.getX() == x && this.maali.getY() == y) {
                    this.piirtologiikka.setRuutu(x, y, Ruutu.MAALI);
                } else if (this.ruudukko[y][x] == 0) {
                    this.piirtologiikka.setRuutu(x, y, Ruutu.SEINA);
                }
            }
        }
    }

    private boolean koordinaatitUlkopuolella(Koordinaatit koordinaatit) {
        return koordinaatit.getX() < 0 || koordinaatit.getY() < 0 || koordinaatit.getX() >= ruudukko[0].length || koordinaatit.getY() >= ruudukko.length;

    }

    public int getLeveys() {
        return this.leveys;
    }

    public int getKorkeus() {
        return this.korkeus;
    }

    public boolean onkoValmis() {
        return this.valmis;
    }
}
