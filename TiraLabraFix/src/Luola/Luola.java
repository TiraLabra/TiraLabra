/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Luola;

import Tietorakenteet.Jatkuvamonikulmio;
import Tietorakenteet.Jono.Jono;
import Tietorakenteet.Jono.Jonoiteroitava;
import Tietorakenteet.Kordinaatti;
import java.util.Random;

/**
 *
 * Tämän luokan tarkoituksena on toimia generoitavana objektina jota voi käyttää
 * suorituskykytestauksessa
 */
public class Luola {

    private Jatkuvamonikulmio d;
    private Random rand;
    private Kordinaatti alku;
    private Kordinaatti loppu;

    public Luola() {
        rand = new Random();
    }

    public void konstruoi(int luolansuuruus, int pienin) {

        Jono ala = new Jono();
        Jono yla = new Jono();
        for (int i = 1; i <= luolansuuruus; i++) {
            double i1 = randInt(pienin, 9);
            double i2 = randInt(pienin, 9);
            ala.lisaa(i1);
            yla.lisaa(i2);
        }
        generoi(ala, yla);

    }

    public void generoi(Jono ala, Jono yla) {
        
        this.alku = new Kordinaatti(0.5, 5);
        Jono kordinaattijono = new Jono();
        Jonoiteroitava iter = ala.palautaEnsimmainen();
        Jonoiteroitava itery = yla.palautaEnsimmainen();
        kordinaattijono.lisaa(new Kordinaatti(0, 0));
        int i = 1;
        while (iter != null) {
            kordinaattijono.lisaa(new Kordinaatti(i, 0));
            if (i % 2 == 1) {
                double d = (double) iter.palautaObjekti();
                double z = i + 0.5;
                kordinaattijono.lisaa(new Kordinaatti(z, d));
                iter = iter.palautaSeuraava();

            }

            i++;
        }
        kordinaattijono.lisaa(new Kordinaatti(i, 0));
        i++;
        double dab = i + 1 / 2 - 2;
        this.loppu = new Kordinaatti(dab, 5);
        i++;
        kordinaattijono.lisaa(new Kordinaatti(i, 0));
        kordinaattijono.lisaa(new Kordinaatti(i, 10));
        int j = i;
        j--;
        while (itery != null) {

            kordinaattijono.lisaa(new Kordinaatti(j, 10));
            if (j % 2 == 1) {

                double d2 = j - 0.5;
                double z = (double) itery.palautaObjekti();
                z = 10 - z;
                kordinaattijono.lisaa(new Kordinaatti(d2, z));
                itery = itery.palautaSeuraava();

            }

            j--;
        }
        kordinaattijono.lisaa(new Kordinaatti(j, 10));
        kordinaattijono.lisaa(new Kordinaatti(0, 10));
        kordinaattijono.lisaa(new Kordinaatti(0, 12000));
        kordinaattijono.lisaa(new Kordinaatti(i+2, 12));
        kordinaattijono.lisaa(new Kordinaatti(i+2, -2));
        kordinaattijono.lisaa(new Kordinaatti(0, -20000));
        this.d = new Jatkuvamonikulmio(kordinaattijono);

    }

    public int randInt(int min, int max) {

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public Jatkuvamonikulmio palautakulmio() {
        return this.d;
    }

    public Kordinaatti palautaalku() {
        return this.alku;
    }

    public Kordinaatti palautaloppu() {
        return this.loppu;
    }

}
