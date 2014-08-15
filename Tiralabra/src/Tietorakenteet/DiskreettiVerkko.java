/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Serafim
 */
public class DiskreettiVerkko implements Verkko {

    private double ruudunpituus;
    private HashMap<Kordinaatti, DiskreettiSolmu> kartta;

    public DiskreettiVerkko() {
        ruudunpituus = 1;
        this.kartta = new HashMap<Kordinaatti, DiskreettiSolmu>();
    }

    public void asetaRuudunpituus(double pituus) {
        this.ruudunpituus = pituus;
    }

    public double palautaPituus() {
        return ruudunpituus;
    }

    public void asetaKartta(HashMap<Kordinaatti, DiskreettiSolmu> kartta) {
        this.kartta = kartta;
    }

    @Override
    public ArrayList<Abstraktisolmu> Naapurit(Abstraktisolmu node) {
        ArrayList<Abstraktisolmu> listasolmuja = new ArrayList<Abstraktisolmu>();
        DiskreettiSolmu s = (DiskreettiSolmu) node;
        double x = s.PalautaX();
        double y = s.PalautaY();
        Kordinaatti[] k = new Kordinaatti[8];
        k[0] = new Kordinaatti(x + this.ruudunpituus, y);
        k[1] = new Kordinaatti(x, y + this.ruudunpituus);
        k[2] = new Kordinaatti(x + this.ruudunpituus, y + this.ruudunpituus);
        k[3] = new Kordinaatti(x - this.ruudunpituus, y);
        k[4] = new Kordinaatti(x, y - this.ruudunpituus);
        k[5] = new Kordinaatti(x + this.ruudunpituus, y - this.ruudunpituus);
        k[6] = new Kordinaatti(x - this.ruudunpituus, y - this.ruudunpituus);
        k[7] = new Kordinaatti(x - this.ruudunpituus, y + this.ruudunpituus);
        for (int i = 0; i <= 7; i++) {
            if (kartta.get(k[i]) != null) {
                listasolmuja.add(kartta.get(k[i]));
            }
        }

        return listasolmuja;
    }

    @Override
    public boolean Olemassa(Abstraktisolmu node) {
        DiskreettiSolmu s = (DiskreettiSolmu) node;
        if (this.kartta.get(s.palautaKordinaatit()) == null) {
            return false;
        }
        return true;
    }

    @Override
    public double Heurestiikka(Abstraktisolmu alku, Abstraktisolmu loppu) {
        return Etaisyys(alku, loppu);
    }

    @Override
    public double Etaisyys(Abstraktisolmu alku, Abstraktisolmu loppu) {
        DiskreettiSolmu a = (DiskreettiSolmu) alku;
        DiskreettiSolmu b = (DiskreettiSolmu) loppu;
        double x1 = a.PalautaX();
        double y1 = a.PalautaY();
        double x2 = b.PalautaX();
        double y2 = b.PalautaY();
        double d = Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2));

        return d;
    }

}
