package com.mycompany.tiralabra_maven.logiikka.dijkstra;

import com.mycompany.tiralabra_maven.logiikka.Paikka;
import com.mycompany.tiralabra_maven.logiikka.Piste;
import com.mycompany.tiralabra_maven.logiikka.keko.JavaKeko;
import com.mycompany.tiralabra_maven.logiikka.keko.MinimiKeko;
import java.util.PriorityQueue;

/**
 *
 * @author Hannu
 */
public class DijkstraWithHeap {

    public static int ratkaise(int[][] kartta, Piste lahtoPiste, Piste maaliPiste) {
        // Dijkstra with min-heap

        int n = kartta.length;
        int m = kartta[0].length;

        int iVierus;
        int jVierus;

        System.out.println("n m: " + n + " " + m);

        Paikka[][] paikat = new Paikka[n][m];

        JavaKeko<Paikka> heap = new JavaKeko<Paikka>();
//        PriorityQueue<Paikka> kekoPieninEtaisyys = new PriorityQueue<Paikka>(n*m);

        Paikka paikkaU;

//        boolean muuttuikoV;

        // InitialiseSingleSource
        for (int i = 0; i < paikat.length; i++) {
            for (int j = 0; j < paikat[0].length; j++) {
                // luokan Paikka konstruktori asettaa paikan etaisyydeksi noin aareton
                paikat[i][j] = new Paikka(i, j, kartta[i][j]);
//                paikat[i][j] = new Paikka(vuori[i][j]);
            }
        }
        paikat[lahtoPiste.x][lahtoPiste.y].etaisyysAlkuun = 0;

        for (int i = 0; i < paikat.length; i++) {
            for (int j = 0; j < paikat[0].length; j++) {
                heap.heapInsert(paikat[i][j]);
//                paikat[i][j].asetaVieruspaikat(paikat);

                iVierus = i;
                jVierus = j - 1;
                if (iVierus >= 0 && iVierus < paikat.length && jVierus >= 0 && jVierus < paikat[0].length) {
                    paikat[i][j].vierusPaikat.add(paikat[iVierus][jVierus]);
                    System.out.println("i, j, iVierus, jVierus: " + i + j + " " + iVierus + jVierus);
                }
                iVierus = i;
                jVierus = j + 1;
                if (iVierus >= 0 && iVierus < paikat.length && jVierus >= 0 && jVierus < paikat[0].length) {
                    paikat[i][j].vierusPaikat.add(paikat[iVierus][jVierus]);
                    System.out.println("i, j, iVierus, jVierus: " + i + j + " " + iVierus + jVierus);
                }
                iVierus = i - 1;
                jVierus = j;
                if (iVierus >= 0 && iVierus < paikat.length && jVierus >= 0 && jVierus < paikat[0].length) {
                    paikat[i][j].vierusPaikat.add(paikat[iVierus][jVierus]);
                    System.out.println("i, j, iVierus, jVierus: " + i + j + " " + iVierus + jVierus);
                }
                iVierus = i + 1;
                jVierus = j;
                if (iVierus >= 0 && iVierus < paikat.length && jVierus >= 0 && jVierus < paikat[0].length) {
                    paikat[i][j].vierusPaikat.add(paikat[iVierus][jVierus]);
                    System.out.println("i, j, iVierus, jVierus: " + i + j + " " + iVierus + jVierus);
                }
            }
        }




        System.out.println(paikat[0][0].vierusPaikat);

//        for (int i = 0; i < paikat.length; i++) {
//            for (int j = 0; j < paikat[0].length; j++) {
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

        for (int i = 0; i < paikat.length; i++) {
            for (int j = 0; j < paikat[0].length; j++) {
                System.out.println("i=" + i + " j=" + j + ": " + paikat[i][j].etaisyysAlkuun);
            }
        }

        System.out.println("");


        return paikat[maaliPiste.x][maaliPiste.y].etaisyysAlkuun;
    }

    public static boolean relax(Paikka paikkaU, Paikka paikkaV) {
        if (paikkaV.etaisyysAlkuun > paikkaU.etaisyysAlkuun+ Math.abs(paikkaV.aikaKustannus)) {
            paikkaV.etaisyysAlkuun = paikkaU.etaisyysAlkuun+ Math.abs(paikkaV.aikaKustannus);
            return true;
        }
        return false;
    }
}
