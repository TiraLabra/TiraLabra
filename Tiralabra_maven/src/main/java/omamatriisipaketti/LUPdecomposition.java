
package omamatriisipaketti;

import yleismetodeja.Peruslasku;
import static yleismetodeja.Peruslasku.laskeDiagonaaliAlkioidenTulo;
import yleismetodeja.Taulukko;

/**
 * Matriisin PA = LU dekompositio neliömatriiseille. 
 * @author risto
 */
public class LUPdecomposition {
    /**
     * Alempi kolmiomatriisi double[][]-tyyppisenä listana.
     */
    private double[][] l;
    /**
     * YLempi kolmiomatriisi double[][]-tyyppisenä listana.
     */
    private double[][] u;
    /**
     * Permutaatiomatriisi esitettynä listana. Mikäli listan rivillä i on arvo j, 
     * permutaatiomatriisissa on kohdassa (i,j) ykkönen, muut arvot ovat nollia.
     */
    private int[] p;
    /**
     * Rivien vaihtojen lukumäärä. Determinantin laskemista varten on hyödyllistä pitää kirjaa dekomponointi varten
     * tarvituista rivien swapeista. 
     */
    private int rivinvaihtojenmaara;
    
    public LUPdecomposition() {
        this.l=null;
        this.u=null;
        this.p=null;
  
    }
    
    /**
     * Mikäli jostain syystä tunnetuista osista haluaa koota LUP-dekomposition, sen voi
     * tehdä konstruktorin avulla.
     * @param l lower triangular matrix.
     * @param u upper triangular matrix
     * @param p permutation matrix listana.
     */
    public LUPdecomposition(double[][] l, double[][] u, int[] p) {
        this.l = l;
        this.u = u;
        this.p = p;
    }
    
    /**
     * Metodi dekomponoi annetun matriisin. Metodi laskee L ja U matriisit 
     * samaan matriisiin, joten lopussa L ja U täytyy erikseen poimia pois matriisista.
     * @param matriisi double[][]-tyyppinen neliömatriisi.
     * @throws Exception Metodi heittää poikkeuksen, mikäli matriisia ei voida
     * dekomponoida.
     */
    public LUPdecomposition(double[][] matriisi) throws Exception {
        int n = matriisi.length;
        double[][] dekomponoitava = Taulukko.kopioiArray(matriisi);
        double[][] u = new double[n][n];
        double[][] l = new double[n][n];
        this.rivinvaihtojenmaara=0;
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
            Taulukko.vaihdalistanAlkiot(permutaatiomatriisi, k, k1);
            Taulukko.vaihdaRivit(dekomponoitava,k,k1);
            /*
            for (int i = 0; i < n; i++) {
                Taulukko.vaihdaMatriisinAlkiot(dekomponoitava, k, i, k1, i);
            }
            */
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
    
    /**
     * Kirjoita lower triangular matrix. Apumetodi, joka kirjoittaa annetusta matriisista 
     * diagonaalin alapuolella olevat arvot uuteen matriisiin.
     * @param matriisi double[][]-tyyppinen neliömatriisi
     * @return double[][] matriisi, jossa on diagonaalin yläpuolella vain nollia.
     */
    public static double[][] kirjoitaLower(double[][] matriisi) {
        int n = matriisi.length;
        double[][] palautettava = new double[n][n];
        Taulukko.kirjoitaYkkosiaDiagonaalille(palautettava);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                palautettava[i][j] = matriisi[i][j];
            }
        }
        return palautettava;
    }
    
    /**
     * Kirjoita uppper triangular matrix. Apumetodi, joka kirjoittaa annetusta matriiisista 
     * diagonaalin yläpuolella olevat arvot uuteen matriisiin. 
     * @param matriisi double[][]-tyyppinen neliömatriisi
     * @return double[][] matriisi, jossa on diagonaalin alapuolella vain nollia.
     */
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
    
    /**
     * Metodi muodostaa permutaatiomatriisilistasta taulukon ja palauttaa sen.
     * @return double[][]-tyyppinen nxn-neliömatriisi, jossa on n ykköstä. 
     */
    public double[][] getPermutationArray() {
        int n = this.p.length;
        double[][] permutaatioarray = new double[n][n];
        for (int i = 0; i < n; i++) {
            permutaatioarray[i][this.p[i]] = 1; 
        }
        return permutaatioarray;
    }
    
        /**
     * Determinantti. Metodi laskee LU-dekomposition determinantin.
     * @param lu on lu dekompositio jostakin matriisista.
     * @return double matriisin determinantti.
     */
    public double det() {
        double tulo = laskeDiagonaaliAlkioidenTulo(this.u);        
        return tulo*Math.pow(-1, this.rivinvaihtojenmaara);
    }
    
}
