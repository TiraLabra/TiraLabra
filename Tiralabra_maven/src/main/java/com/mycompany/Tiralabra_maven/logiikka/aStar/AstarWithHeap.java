package com.mycompany.Tiralabra_maven.logiikka.aStar;

import com.mycompany.Tiralabra_maven.logiikka.Paikka;
import com.mycompany.Tiralabra_maven.logiikka.Piste;
import com.mycompany.Tiralabra_maven.logiikka.keko.PriorityQueueKeko;
import java.util.Stack;

/**
 * Astar-algoritmin apufunktiot ja varsinaisen ratkaisun sisältävä luokka.
 */
public class AstarWithHeap {

    private Piste lahtoPiste;
    private Piste maaliPiste;
    private int[][] kartta;
    private int[][] reittiKartta;
    private Paikka[][] paikat;
    private boolean maaliPoistettuKeosta; // aStar

    /**
     * Luokan AstarWithHeap konstruktori.
     * 
     * @param kartta aikakustannutkartta kokonaislukutaulukkona
     * @param lahtoPiste haettavan nopeimman reitin lähtöpiste
     * @param maaliPiste haettavan nopeimman reitin maalipiste
     */
    public AstarWithHeap(int[][] kartta, Piste lahtoPiste, Piste maaliPiste) {

        this.lahtoPiste = lahtoPiste;
        this.maaliPiste = maaliPiste;
        this.kartta = kartta;
        this.reittiKartta = kartta;
        this.paikat = new Paikka[kartta.length][kartta[0].length];
        this.maaliPoistettuKeosta = false; // aStar

    }

    /**
     * Metodi suorittaa Astar-algoritmin ratkaisun.
     * 
     * @return Lähtö- ja maalipisteiden välisen lyhimmän polun pituus.
     */
    public int ratkaise() {

        this.initialiseAstar();

        PriorityQueueKeko heap = rakennaKekoJaAsetaVieruspaikat();

        System.out.println(this.paikat[0][0].vierusPaikat);

        System.out.println("");

        Paikka paikkaU;

        while (this.maaliPoistettuKeosta == false) {
            paikkaU = heap.heapDelMin();
            if (paikkaU.i == maaliPiste.i && paikkaU.j == maaliPiste.j) { // aStar
                this.maaliPoistettuKeosta = true; // aStar
            } // aStar
            System.out.println("paikkaU " + paikkaU.i + paikkaU.j + " " + paikkaU.etaisyysAlkuun);
            for (Paikka paikkaV : paikkaU.vierusPaikat) {
                System.out.println("paikkaV " + paikkaV.i + paikkaV.j + " " + paikkaV.etaisyysAlkuun);
                if (relax(paikkaU, paikkaV)) {
                    System.out.println("V muuttui");
                    heap.heapDecreaseKey(paikkaV);
                }
            }
        }

        for (int i = 0; i < this.paikat.length; i++) {
            for (int j = 0; j < this.paikat[0].length; j++) {
                System.out.println("i=" + i + " j=" + j + ": " + this.paikat[i][j].etaisyysAlkuun);
            }
        }

        System.out.println("");


        return this.paikat[this.maaliPiste.i][this.maaliPiste.j].etaisyysAlkuun;
    }

    private void initialiseAstar() {
        for (int i = 0; i < this.paikat.length; i++) {
            for (int j = 0; j < this.paikat[0].length; j++) {
                // luokan Paikka konstruktori asettaa Paikan julkisen muuttujan etaisyysAlkuun arvoksi noin aareton
                this.paikat[i][j] = new Paikka(i, j, this.kartta[i][j]);
                this.paikat[i][j].etaisyysLoppuun = Math.abs(this.lahtoPiste.i - this.paikat[i][j].i) + Math.abs(this.lahtoPiste.j - this.paikat[i][j].j); // ASTAR
            }
        }

        this.paikat[this.lahtoPiste.i][this.lahtoPiste.j].etaisyysAlkuun = 0;
    }

    private PriorityQueueKeko rakennaKekoJaAsetaVieruspaikat() {
        PriorityQueueKeko heap = new PriorityQueueKeko();

        for (int i = 0; i < this.paikat.length; i++) {
            for (int j = 0; j < this.paikat[0].length; j++) {

                heap.heapInsert(this.paikat[i][j]);

                asetaVieruspaikka(this.paikat[i][j], i, j - 1);
                asetaVieruspaikka(this.paikat[i][j], i, j + 1);
                asetaVieruspaikka(this.paikat[i][j], i - 1, j);
                asetaVieruspaikka(this.paikat[i][j], i + 1, j);

            }
        }

        return heap;

    }

    private void asetaVieruspaikka(Paikka paikka, int iVierus, int jVierus) {
        if (iVierus >= 0 && iVierus < this.paikat.length && jVierus >= 0 && jVierus < this.paikat[0].length) {
            paikka.vierusPaikat.add(this.paikat[iVierus][jVierus]);
            System.out.println("i, j, iVierus, jVierus: " + paikka.i + paikka.j + " " + iVierus + jVierus);
        }

    }

    private boolean relax(Paikka paikkaU, Paikka paikkaV) {
        if (paikkaV.etaisyysAlkuun > paikkaU.etaisyysAlkuun + Math.abs(paikkaV.aikaKustannus)) {
            paikkaV.etaisyysAlkuun = paikkaU.etaisyysAlkuun + Math.abs(paikkaV.aikaKustannus);
            paikkaV.polku = paikkaU;
            return true;
        }
        return false;
    }

    /**
     * Metodi tulostaa lähtö- ja maalipisteiden välisen lyhyimmäin polun
     * Paikkojen i- ja j-koordinaatit (lukuunottamatta itse lähtö- ja
     * maalipisteitä). Lisäksi metodi asettaa taulukon reittiKartta arvoksi 0,
     * kohtiin joiden kautta lyhin polku kulkee.
     */
    public void shortestPath() {

        Stack<Paikka> pino = new Stack<Paikka>();

        Paikka paikkaU = this.paikat[this.maaliPiste.i][this.maaliPiste.j].polku;

        while (!(paikkaU.i == lahtoPiste.i && paikkaU.j == lahtoPiste.j)) {
            pino.push(paikkaU);
            paikkaU = paikkaU.polku;
        }

        System.out.println("polku");

        while (!pino.empty()) {
            paikkaU = pino.pop();
            System.out.println(paikkaU.i + ", " + paikkaU.j);
            this.reittiKartta[paikkaU.i][paikkaU.j] = 0;
        }


    }

    /**
     * Kehityksen aikaista testitulostusta.
     * Metodi tulostaa taulukon reittiKartta arvot.
     */
    public void testiTulostaReittikartta() {
        System.out.println("reittikartta");
        for (int i = 0; i < this.reittiKartta.length; i++) {
            for (int j = 0; j < this.reittiKartta[0].length; j++) {
                if (j == this.reittiKartta[0].length - 1) {
                    System.out.print(this.reittiKartta[i][j]);
                } else {
                    System.out.print(this.reittiKartta[i][j] + " ");
                }
            }
            System.out.println();
        }

    }
}
