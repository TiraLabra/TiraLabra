/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package omamatriisipaketti;

import yleismetodeja.Peruslasku;

/**
 *
 * @author risto
 */
public class LUPdecomposition {
    private double[][] l;
    private double[][] u;
    private int[] p;
    private int rivinvaihtojenmaara;
    
    public LUPdecomposition() {
        this.l=null;
        this.u=null;
        this.p=null;
  
    }
    
    public LUPdecomposition(double[][] l, double[][] u, int[] p) {
        this.l = l;
        this.u = u;
        this.p = p;
    }
    
    public LUPdecomposition(double[][] dekomponoitava) throws Exception {
        int n = dekomponoitava.length;
        double[][] u = new double[n][n];
        double[][] l = new double[n][n];
        this.rivinvaihtojenmaara=0;
        /**
         * Permutaatiomatriisi on talletettu listana siten, että jos permutaatiomatriisi[i] = j, niin
         * matriisissa on rivillä i sarakkeessa j arvo 1.
         */
        int[] permutaatiomatriisi = new int[n];

        /**
         * Vaihdettavan rivin numero
         */
        int k1=0;
        /**
         * pivot
         */
        double p;
        for (int i = 0; i < n; i++) {
            permutaatiomatriisi[i] = i;
        }
        for (int k = 0; k < n; k++) {
            p = 0;
            for (int i = k; i < n; i++) {
                if (Math.abs(dekomponoitava[i][k]) > p) {
                     p = Math.abs(dekomponoitava[i][k]);
                     k1 = i;
                }
            }    
            if (p==0) {
                throw new Exception("Matriisi on singulaarinen");
            }
            if (k!=k1) {
                rivinvaihtojenmaara++;
            }
            Peruslasku.vaihdalistanAlkiot(permutaatiomatriisi, k, k1);
            for (int i = 0; i < n; i++) {
                Peruslasku.vaihdaMatriisinAlkiot(dekomponoitava, k, i, k1, i);
            }
            for (int i = k+1; i < n; i++) {
                dekomponoitava[i][k] = dekomponoitava[i][k]/dekomponoitava[k][k];
                for (int j = k+1; j < n; j++) {
                    dekomponoitava[i][j] = dekomponoitava[i][j] - dekomponoitava[i][k]*dekomponoitava[k][j];
                }
            }
            
        }
        
        this.l = kirjoitaLower(dekomponoitava);
        this.u = kirjoitaUpper(dekomponoitava);
        this.p = permutaatiomatriisi;
        
    }
    
    public static double[][] kirjoitaLower(double[][] matriisi) {
        int n = matriisi.length;
        double[][] palautettava = new double[n][n];
        kirjoitaYkkosiaDiagonaalille(palautettava);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                palautettava[i][j] = matriisi[i][j];
            }
        }
        return palautettava;
    }
    
    public static double[][] kirjoitaUpper(double[][] matriisi) {
        int n = matriisi.length;
        double[][] palautettava = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                palautettava[i][j] = matriisi[i][j];
            }
        }
        return palautettava;
    }
    
    public static void kirjoitaYkkosiaDiagonaalille(double[][] matriisi) {
        int n = matriisi.length;
        for (int i = 0; i < n; i++) {
            matriisi[i][i] = 1;
        }
    }
    
    public double[][] getL() {
        return this.l;
    }
    
    public double[][] getU() {
        return this.u;
    }
    
    public void setP(int[] p) {
        this.p=p;
    }
    
    public double getRivinvaihtojenMaara() {
        return this.rivinvaihtojenmaara;
    }
    
    public double[][] getPermutationArray() {
        int n = this.p.length;
        double[][] permutaatioarray = new double[n][n];
        for (int i = 0; i < n; i++) {
            permutaatioarray[i][this.p[i]] = 1; 
        }
        return permutaatioarray;
    }
    
}
