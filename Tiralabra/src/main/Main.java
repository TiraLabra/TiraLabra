package main;

import logiikka.Matriisilaskin;

/**
 * Main-luokka, joka on vasta omaa testailua varten.
 *
 * @author Eversor
 */
public class Main {

    public static void main(String[] args) {
        Matriisilaskin laskin = new Matriisilaskin();
        double[][] A = new double[3][2];
        double[][] B = new double[3][2];

        A[0][0] = 0;
        A[0][1] = 1;
        A[1][0] = 2;
        A[1][1] = 3;
        A[2][0] = 4;
        A[2][1] = 5;

        B[0][0] = 1;
        B[0][1] = 1;
        B[1][0] = 1;
        B[1][1] = 1;
        B[2][0] = 1;
        B[2][1] = 1;

        double[][] C = laskin.summaa(A, B);

        for (int rivit = 0; rivit < C.length; rivit++) {
            for (int sarakkeet = 0; sarakkeet < C[0].length; sarakkeet++) {
                System.out.print(C[rivit][sarakkeet] + " ");
            }
            System.out.println("");
        }
        
        System.out.println("");
        
        double[][] D = laskin.kerro(C, 0.1);
        
        for (int rivit = 0; rivit < D.length; rivit++) {
            for (int sarakkeet = 0; sarakkeet < D[0].length; sarakkeet++) {
                System.out.print(D[rivit][sarakkeet] + " ");
            }
            System.out.println("");
        }
    }
}
