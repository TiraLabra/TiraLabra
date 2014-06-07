package apuneuvot;

import java.util.Random;

/**
 * MatriisienGeneroija-luokka, jonka avulla voidaan luoda halutun tyyppisiä
 * matriiseja. Sopii hyvin suorituskykytestauksien eri tarpeisiin.
 * 
 * @author Eversor
 */
public class MatriisienGeneroija {
    
    private Random random;

    /**
     * Konstrukstori, joka luo uuden Random-luokan ilmentymän ja tallettaa sen
     * private-muuttujaan.
     */
    public MatriisienGeneroija(){
        random = new Random();
    }
    
    /**
     * Metodi, joka luo uuden yksikkömatriisin parametrina annetun koon
     * mukaisesti. Yksikkömatriisi on siis neliömatriisi, jonka päälävistäjän
     * alkiot ovat ykkösiä ja muut alkiot ovat nollia.
     *
     * @param koko Yksikkömatriisin koko
     * @return Palauttaa luodun yksikkömatriisin, muotoa koko x koko
     */
    public double[][] luoYksikkomatriisi(int koko) {
        double[][] I = new double[koko][koko];
        for (int lavistaja = 0; lavistaja < koko; lavistaja++) {
            I[lavistaja][lavistaja] = 1;
        }
        return I;
    }
    
    /**
     * Metodi, joka luo uuden ykkösillä täytetyn matriisin parametreina annetun
     * koon mukaisesti. Ykkösillä täytetyn matriisin jokainen alkio on arvoltaan
     * yksi.
     * 
     * @param m Kokonaisluku, joka määrää matriisin rivien määrän
     * @param n Kokonaisluku, joka määrää matriisin sarakkeiden määrän
     * @return Palauttaa ykkösillä täytetyn matriisin muotao m x n
     */
    public double[][] luoYkkosillaTaytettyMatriisi(int m, int n) {
        double[][] matriisi = new double[m][n];
        for(int rivi = 0; rivi < m; rivi++) {
            for(int sarake = 0; sarake < n; sarake++) {
                matriisi[rivi][sarake] = 1;
            }
        }
        return matriisi;
    }
}