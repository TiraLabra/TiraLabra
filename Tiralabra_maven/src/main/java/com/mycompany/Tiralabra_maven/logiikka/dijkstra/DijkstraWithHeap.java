package com.mycompany.Tiralabra_maven.logiikka.dijkstra;

import com.mycompany.Tiralabra_maven.logiikka.Paikka;
import com.mycompany.Tiralabra_maven.logiikka.Piste;
import com.mycompany.Tiralabra_maven.logiikka.paikkaKeko.MinKekoAlkionaPaikka;
import com.mycompany.Tiralabra_maven.logiikka.paikkaKeko.OmaKekoAlkionaPaikka;
import com.mycompany.Tiralabra_maven.logiikka.paikkaKeko.PriorityQueueKekoAlkionaPaikka;
import com.mycompany.Tiralabra_maven.logiikka.paikkaPino.OmaPinoAlkionaPaikka;
//import java.util.Stack;

/**
 * Dijkstran algoritmin apufunktiot ja varsinaisen ratkaisun sisältävä luokka.
 */
public class DijkstraWithHeap {

    private Piste lahtoPiste;
    private Piste maaliPiste;
    private int[][] kartta;
    private int[][] reittiKartta;
    private Paikka[][] paikat;
    private boolean maaliPoistettuKeosta;
    private boolean omaKeko;

    /**
     * Luokan DijkstraWithHeap konstruktori.
     *
     * @param kartta aikakustannutkartta kokonaislukutaulukkona
     * @param lahtoPiste haettavan nopeimman reitin lähtöpiste
     * @param maaliPiste haettavan nopeimman reitin maalipiste
     * @param omaKeko jos omaKeko=true, käytetään omaa kekototeutusta
     */
    public DijkstraWithHeap(int[][] kartta, Piste lahtoPiste, Piste maaliPiste, boolean omaKeko) {

        this.lahtoPiste = lahtoPiste;
        this.maaliPiste = maaliPiste;
        this.kartta = kartta;
        this.reittiKartta = kartta;
        this.paikat = new Paikka[kartta.length][kartta[0].length];
        this.maaliPoistettuKeosta = false;
        this.omaKeko = omaKeko;

    }

    /**
     * Metodi suorittaa Dijkstran algoritmin ratkaisun.
     *
     * @return Lähtö- ja maalipisteiden välisen nopeimman polun kulkemiseen
     * käytetty aika.
     */
    public int ratkaise() {

//////        try {
//////            Thread.sleep(5000, 1);                 //1000 milliseconds is one second.
//////        } catch (InterruptedException ex) {
//////            Thread.currentThread().interrupt();
//////        }

        this.initialiseSingleSource();

        MinKekoAlkionaPaikka heap = rakennaKekoJaAsetaVieruspaikat();

//////        System.out.println(this.paikat[0][0].vierusPaikat);
//////
//////        System.out.println("");

        Paikka paikkaU;
        Paikka paikkaV;

//        while (!heap.heapIsEmpty()) {// EI käytetä tätä ehtoa vaan
//        lopetetaan etsintä kun nopein polku maalipisteeseen löydetty
        while (!this.maaliPoistettuKeosta) {
            paikkaU = heap.heapDelMin();
            paikkaU.etaisyysAlkuunLaskettu = true;
            if (paikkaU.i == maaliPiste.i && paikkaU.j == maaliPiste.j) {
                this.maaliPoistettuKeosta = true;
            }
//////            System.out.println("paikkaU " + paikkaU.i + paikkaU.j + " " + paikkaU.etaisyysAlkuun);
            while (!paikkaU.vierusPaikat.stackIsEmpty()) {
                paikkaV = paikkaU.vierusPaikat.stackPop();
                if (relax(paikkaU, paikkaV)) {
//////                    System.out.println("V muuttui");
                    heap.heapDecreaseKey(paikkaV);
//////                    System.out.println("paikkaV " + paikkaV.i + paikkaV.j + " " + paikkaV.etaisyysAlkuun);
                }
            }
//////        System.out.println("keon koko: "+heap.heapSize);
        }

//////        for (int i = 0; i < this.paikat.length; i++) {
//////            for (int j = 0; j < this.paikat[0].length; j++) {
//////                System.out.println("i=" + i + " j=" + j + ": " + this.paikat[i][j].etaisyysAlkuun);
//////            }
//////        }

//////        System.out.println("");


        return this.paikat[this.maaliPiste.i][this.maaliPiste.j].etaisyysAlkuun;
    }

    private void initialiseSingleSource() {
//    public void initialiseSingleSource() {
        for (int i = 0; i < this.paikat.length; i++) {
            for (int j = 0; j < this.paikat[0].length; j++) {
                // luokan Paikka konstruktori asettaa Paikan julkisen muuttujan etaisyysAlkuun arvoksi noin aareton
                this.paikat[i][j] = new Paikka(i, j, this.kartta[i][j]);
            }
        }

        this.paikat[this.lahtoPiste.i][this.lahtoPiste.j].etaisyysAlkuun = 0;
    }

    private MinKekoAlkionaPaikka rakennaKekoJaAsetaVieruspaikat() {

        MinKekoAlkionaPaikka heap = new MinKekoAlkionaPaikka();

        if (this.omaKeko) {
            heap = new OmaKekoAlkionaPaikka();
        } else {
            heap = new PriorityQueueKekoAlkionaPaikka();
        }

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
            paikka.vierusPaikat.stackPush(this.paikat[iVierus][jVierus]);
//////            System.out.println("i, j, iVierus, jVierus: " + paikka.i + paikka.j + " " + iVierus + jVierus);
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
     * Metodi laittaa nopeimmalla polulla olevat Paikat pinoon.
     *
     * @return pino, jossa nopeimman polun paikat
     */
    public OmaPinoAlkionaPaikka shortestPath() {

        OmaPinoAlkionaPaikka pino = new OmaPinoAlkionaPaikka();

        Paikka paikkaU = this.paikat[this.maaliPiste.i][this.maaliPiste.j].polku;

        while (!(paikkaU.i == lahtoPiste.i && paikkaU.j == lahtoPiste.j)) {
            pino.stackPush(paikkaU);
            paikkaU = paikkaU.polku;
        }

        return pino;
    }

    /**
     * Metodi laittaa pinoon kaikki Paikat, joissa kaydaan kun ratkaisualgoritmi
     * suoritettiin.
     *
     * @return pino, jossa kaikki paikat, joissa etaisyysAlkuun laskettu
     */
    public OmaPinoAlkionaPaikka kaydytPaikat() {

        OmaPinoAlkionaPaikka pino = new OmaPinoAlkionaPaikka();

        for (int i = 0; i < this.paikat.length; i++) {
            for (int j = 0; j < this.paikat[0].length; j++) {
                if (this.paikat[i][j].etaisyysAlkuunLaskettu) {
                    pino.stackPush(this.paikat[i][j]);
                }
            }
        }

        return pino;
    }

    /**
     * Kehityksen aikaista testitulostusta. Metodi tulostaa taulukon
     * reittiKartta arvot.
     */
    public void testiTulostaReittikartta(OmaPinoAlkionaPaikka pino) {

        Paikka paikkaU;

        System.out.println("polku");

        while (!pino.stackIsEmpty()) {
            paikkaU = pino.stackPop();
            System.out.println(paikkaU.i + ", " + paikkaU.j);
            this.reittiKartta[paikkaU.i][paikkaU.j] = 0;
        }


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
