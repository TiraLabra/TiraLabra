package com.mycompany.tiralabra_maven.logiikka.dijkstra;

import com.mycompany.tiralabra_maven.logiikka.Paikka;
import com.mycompany.tiralabra_maven.logiikka.Piste;
import com.mycompany.tiralabra_maven.logiikka.keko.JavaKeko;
import com.mycompany.tiralabra_maven.logiikka.keko.MinimiKeko;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 *
 * @author Hannu
 */
public class DijkstraWithHeap {

    private Paikka[][] paikat;
    private Piste lahtoPiste;
    private Piste maaliPiste;
//    private int n;
//    private int m;

    public DijkstraWithHeap(int[][] kartta, Piste lahtoPiste, Piste maaliPiste) {
//        this.n = ;
//        this.m = ;

        this.lahtoPiste = lahtoPiste;
        this.maaliPiste = maaliPiste;
        this.paikat = new Paikka[kartta.length][kartta[0].length];

        // InitialiseSingleSource
        for (int i = 0; i < this.paikat.length; i++) {
            for (int j = 0; j < this.paikat[0].length; j++) {
                // luokan Paikka konstruktori asettaa Paikan julkisen muuttujan etaisyysAlkuun arvoksi noin aareton
                this.paikat[i][j] = new Paikka(i, j, kartta[i][j]);
//                this.paikat[i][j] = new Paikka(vuori[i][j]);
            }
        }
        this.paikat[lahtoPiste.x][lahtoPiste.y].etaisyysAlkuun = 0;

    }

    public int ratkaise() {
        // Dijkstra with min-heap


        int iVierus;
        int jVierus;

//        System.out.println("n m: " + n + " " + m);


        JavaKeko<Paikka> heap = new JavaKeko<Paikka>();
//        PriorityQueue<Paikka> kekoPieninEtaisyys = new PriorityQueue<Paikka>(n*m);

        Paikka paikkaU;

//        boolean muuttuikoV;


        for (int i = 0; i < this.paikat.length; i++) {
            for (int j = 0; j < this.paikat[0].length; j++) {
                heap.heapInsert(this.paikat[i][j]);

                iVierus = i;
                jVierus = j - 1;
                if (iVierus >= 0 && iVierus < this.paikat.length && jVierus >= 0 && jVierus < this.paikat[0].length) {
                    this.paikat[i][j].vierusPaikat.add(this.paikat[iVierus][jVierus]);
                    System.out.println("i, j, iVierus, jVierus: " + i + j + " " + iVierus + jVierus);
                }
                iVierus = i;
                jVierus = j + 1;
                if (iVierus >= 0 && iVierus < this.paikat.length && jVierus >= 0 && jVierus < this.paikat[0].length) {
                    this.paikat[i][j].vierusPaikat.add(this.paikat[iVierus][jVierus]);
                    System.out.println("i, j, iVierus, jVierus: " + i + j + " " + iVierus + jVierus);
                }
                iVierus = i - 1;
                jVierus = j;
                if (iVierus >= 0 && iVierus < this.paikat.length && jVierus >= 0 && jVierus < this.paikat[0].length) {
                    this.paikat[i][j].vierusPaikat.add(this.paikat[iVierus][jVierus]);
                    System.out.println("i, j, iVierus, jVierus: " + i + j + " " + iVierus + jVierus);
                }
                iVierus = i + 1;
                jVierus = j;
                if (iVierus >= 0 && iVierus < this.paikat.length && jVierus >= 0 && jVierus < this.paikat[0].length) {
                    this.paikat[i][j].vierusPaikat.add(this.paikat[iVierus][jVierus]);
                    System.out.println("i, j, iVierus, jVierus: " + i + j + " " + iVierus + jVierus);
                }
            }
        }




        System.out.println(this.paikat[0][0].vierusPaikat);

//        for (int i = 0; i < this.paikat.length; i++) {
//            for (int j = 0; j < this.paikat[0].length; j++) {
//                System.out.println(kekoPieninEtaisyys.poll().etaisyys);
//            }
//        }

        System.out.println("");

        while (!heap.heapIsEmpty()) {
            paikkaU = heap.heapDelMin();
            System.out.println("paikkaU " + paikkaU.i + paikkaU.j + " " + paikkaU.etaisyysAlkuun);
//            paikkaU.kay();
            for (Paikka paikkaV : paikkaU.vierusPaikat) {
                System.out.println("paikkaV " + paikkaV.i + paikkaV.j + " " + paikkaV.etaisyysAlkuun);
//                muuttuikoV = false;
                if (relax(paikkaU, paikkaV)) {
                    System.out.println("V muuttui");
                    heap.heapDelete(paikkaV);
                    heap.heapInsert(paikkaV);
                }
            }
        }

        for (int i = 0; i < this.paikat.length; i++) {
            for (int j = 0; j < this.paikat[0].length; j++) {
                System.out.println("i=" + i + " j=" + j + ": " + this.paikat[i][j].etaisyysAlkuun);
            }
        }

        System.out.println("");


        return this.paikat[maaliPiste.x][maaliPiste.y].etaisyysAlkuun;
    }

    public boolean relax(Paikka paikkaU, Paikka paikkaV) {
        if (paikkaV.etaisyysAlkuun > paikkaU.etaisyysAlkuun + Math.abs(paikkaV.aikaKustannus)) {
            paikkaV.etaisyysAlkuun = paikkaU.etaisyysAlkuun + Math.abs(paikkaV.aikaKustannus);
            paikkaV.polku = paikkaU;
            return true;
        }
        return false;
    }

    public void shortestPath() {

        Stack<Paikka> pino = new Stack<Paikka>();

        Paikka u = this.paikat[maaliPiste.x][maaliPiste.y].polku;

        while (!u.equals(this.paikat[this.lahtoPiste.x][this.lahtoPiste.y])) {
            pino.push(u);
            u = u.polku;
        }

        System.out.println("polku");

        while (!pino.empty()) {
            u=pino.pop();
            System.out.print(u.i);
            System.out.print(", ");
            System.out.print(u.j);
            System.out.println();
            
        }






    }
}
