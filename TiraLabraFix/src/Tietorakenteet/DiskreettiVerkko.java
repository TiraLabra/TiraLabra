/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet;

import java.util.ArrayList;
import java.util.HashMap;

/*
 *
 * DiskreettiVerkko on erikoistapaus AbstraktistaVerkosta. Tässä luokassa mallinetaan tilannetta missä R^2 on approksimoitu pikseleillä
 */
public class DiskreettiVerkko implements Verkko {

    private double ruudunpituus;
    private HashMap<Kordinaatti, DiskreettiSolmu> kartta;

    /**
     *
     * Luo uuden diskreetinverkon
     *
     * @param ruudunpituus ruudun pituus
     */
    public DiskreettiVerkko(double ruudunpituus) {
        this.ruudunpituus = ruudunpituus;
        this.kartta = new HashMap<Kordinaatti, DiskreettiSolmu>();
    }

    /**
     *
     * Palauttaa pikseliavaruuden
     *
     * @return HashMap<Kordinaatti, Diskreettisolmu> kartta
     */
    public HashMap<Kordinaatti, DiskreettiSolmu> palautaKartta() {
        return this.kartta;
    }

    /**
     *
     *
     * Asettaa ruudunpituuden
     *
     */
    public void asetaRuudunpituus(double pituus) {
        this.ruudunpituus = pituus;
    }

    /**
     *
     *
     * Palauttaa ruudu pituuden
     *
     */
    public double palautaPituus() {
        return ruudunpituus;
    }

    /**
     *
     *
     * Aettaa kertan (Lähinnä testausta varten)
     *
     */
    public void asetaKartta(HashMap<Kordinaatti, DiskreettiSolmu> kartta) {
        this.kartta = kartta;
    }

    /**
     *
     *
     * Palauttaa kaikki Abstraktisolmu pikselin naapurit ArrayList muodossa
     *
     * @return ArrayList<Abstraktisolmu> Lista abstraktisolmuja
     *
     */
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
                if (kartta.get(k[i]).palautaKulku() == true) {
                    listasolmuja.add(kartta.get(k[i]));
                }
            }
        }

        return listasolmuja;
    }

    /**
     *
     *
     * Tarkistaa onko kyseinen Abstraktisolmu tässä verkossa
     */
    @Override
    public boolean Olemassa(Abstraktisolmu node) {
        DiskreettiSolmu s = (DiskreettiSolmu) node;
        if (this.kartta.get(s.palautaKordinaatit()) == null) {
            return false;
        }
        return true;
    }

    /**
     *
     *
     * Palauttaa Atähtialgoritmin vaatiman heurestiikan
     *
     * @return double heurestiikka
     */
    @Override
    public double Heurestiikka(Abstraktisolmu alku, Abstraktisolmu loppu) {
        return Etaisyys(alku, loppu);
    }

    /**
     *
     * Laskee eukliidisen pituuden kahden solmun välillä
     *
     * @return double arvo
     */
    @Override
    public double Etaisyys(Abstraktisolmu alku, Abstraktisolmu loppu) {
        DiskreettiSolmu a = (DiskreettiSolmu) alku;
        DiskreettiSolmu b = (DiskreettiSolmu) loppu;
        double x1 = a.PalautaX();
        double y1 = a.PalautaY();
        double x2 = b.PalautaX();
        double y2 = b.PalautaY();
        double sisa = Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2);
        double d = Math.sqrt(sisa);

        return d;
    }

    public void Lisaa(DiskreettiSolmu[] solmut) {
        for (DiskreettiSolmu solmut1 : solmut) {
            this.kartta.put(solmut1.palautaKordinaatit(), solmut1);
        }

    }

    @Override
    public void tyhjenna() {
        for (Kordinaatti k : this.kartta.keySet()) {
            SolmuMuisti a = this.kartta.get(k).palautaSolmuMuisti();
            a.tyhjenna();

        }

    }

}
