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
        double[][] A = new double[][]{
            {0, 1},
            {2, 3},
            {4, 5}
        };
        double[][] B = new double[][]{
            {1, 1},
            {1, 1},
            {1, 1}
        };
        double[][] H = new double[][]{
            {2, -1, 2},
            {3, -1, 3},
            {0, 4, 0}
        };
        double[][] X = new double[][]{
            {1, 2, 3},
            {4, 5, 6}
        };
        double[][] sym = new double[][]{
            {1, 3},
            {3, 4}  
        };
        double[][] antisym = new double[][]{
            {0, 4, -5},
            {-4, 0, -6},
            {5, 6, 0}
        };
        double[][] testi = new double[][]{
            {1, 1, 1},
            {2, 2, 2},
            {3, 3, 3}
        };
        double[][] kaanteis = new double[][]{
            {2, -1, 0},
            {-1, 2, -1},
            {0, -1, 2}
        };
        double[][] kaanteis2 = new double[][]{
            {1, 3, -1},
            {1, 2, 0},
            {2, 4, 1}
        };
        double[][] kaanteis3 = new double[][]{
            {2, 3, 1, 4, 1}, 
            {2, 2, -1, 1, -2},
            {4, 4, 5, 1, 2},
            {1, -1, 1, -1, 1},
            {0, -1, -2, -3, -4}
        };
        double[][] orto = new double[][]{
            {(double)2/3, (double)-2/3, (double)1/3},
            {(double)1/3, (double)2/3, (double)2/3},
            {(double)2/3, (double)1/3, (double)-2/3}
        };

        double[][] C = laskin.summaa(A, B);

        System.out.println("Summa C = A + B");
        tulosta(C);

        double[][] D = laskin.kerro(C, 0.1);

        System.out.println("Skalaaritulo D = C * 0.1");
        tulosta(D);      
        
        double[][] E = laskin.kerro(X, D);

        System.out.println("Kertolasku E = X * D");
        tulosta(E);
        
        double[][] F = laskin.kerroStrassenilla(testi, H);

        System.out.println("Kertolasku Strassenilla F = testi * H");
        tulosta(F);
        
        double[][] G = laskin.kerro(testi, H);

        System.out.println("Kertolasku G = testi * H");
        tulosta(G);
        
        System.out.println("Transpoosi A^T");
        double[][] tA = laskin.transpoosaa(A);
        tulosta(tA);
        
        System.out.println("Transpoosi (A^T)^T");
        double[][] tAt = laskin.transpoosaa(tA);
        tulosta(tAt);

        System.out.println(laskin.onkoSymmetrinen(sym));
        System.out.println(laskin.onkoNeliomatriisi(A));
        System.out.println(laskin.onkoAntisymmetrinen(antisym));
        System.out.println(laskin.laskeDeterminantti(sym));
        System.out.println(laskin.onkoKaantyva(sym));
        System.out.println(laskin.onkoOrtogonaalinen(orto));
        System.out.println("");
        
        double[][] symKaanteis = laskin.invertoi(sym);
        tulosta(symKaanteis);
    }
    
    public static void tulosta(double[][] matriisi){
        for (int rivi = 0; rivi < matriisi.length; rivi++) {
            for (int sarake = 0; sarake < matriisi[0].length; sarake++) {
                System.out.print(matriisi[rivi][sarake] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
}