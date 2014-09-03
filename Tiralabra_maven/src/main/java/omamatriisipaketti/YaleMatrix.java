
package omamatriisipaketti;

import yleismetodeja.Peruslasku;
import yleismetodeja.Taulukko;

/**
 * Yale-formaatissa oleva matriisi. Tämä formaatti on tarkoitettu tallentamaan matriiseja, joissa 
 * on hyvin vähän muita kuin nollia. 
 * @author risto
 */
public class YaleMatrix implements Matrix {
    /**
     * Non-zero values. Taulukkoon on koottu matriisin arvot, jotka eivät ole nollia.
     */
    private double[] a;
    /**
     * Rivin ensimmäisen non-zero-alkion indeksi. Taulukossa on jokaisen rivin vasemmalta katsoen ensimmäisen
     * ei-nolla-alkion arvo. Taulukon viimeinen alkio kertoo kaikkien matriisin alkioiden määrän.
     */
    private int[] ia;
    /**
     * Jokaisen non-zero alkion sarakeindeksi. Taulukkoon on koottu jokaisen ei-nolla-alkion sarake.
     */
    private int[] ja;
    private int m;
    private int n;
    private double[][] testiARRAY;
    
    public YaleMatrix(double[][] arvot) {
        this.testiARRAY = arvot;
        int nzalkioidenmaara = laskeNZAlkiot(arvot);
        this.a = keraaNZAlkiot(arvot, nzalkioidenmaara);
        this.ia = muodostaIAlista(arvot);
        this.ja = muodostaJAlista(arvot, nzalkioidenmaara);
        this.m = arvot.length;
        this.n = arvot[0].length;
    }
    
    public int getN() {
        return this.n;
    }
    
    public int getM() {
        return this.m;
    }
    
    public double[] getA() {
        return this.a;
    }
    
    /**
     * Laske non-zero alkiot. Metodi laskee moniko kaksiulotteisin taulukoista on arvoltaan
     * muuta kuin nolla.
     * @param arvot kaksiulotteinen double-taulukko.
     * @return Muiden kuin nolla alkioiden määrä int-arvona.
     */
    public static int laskeNZAlkiot(double[][] arvot) {
        int nZAlkioita = 0;
        for (int i = 0; i < arvot[0].length;i++) {
            for (int j = 0; j <arvot[1].length; j++) {
                if (arvot[i][j]!=0) {
                    nZAlkioita++;
                } 
            }
        }
        return nZAlkioita;
    }
    
    /**
     * Kerää non-zero alkiot. Metodi kokoaa kaksiulotteisen taulukon kaikki alkiot yhteen yksiulotteiseen taulukkoon.
     * 
     * @param arvot double arvoja sisältävät kaksiulotteinen taulukko.
     * @param nZAlkioita kokonaisluku, joka kertoo muiden kuin nolla-alkioiden määrän.
     * @return Metodi palauttaa muut kuin nolla koottuna yhdeksi listaksi, siten että arvot ovat järjestyksessä
     * vasemmalta oikealle ja ylhäältä alas.
     */
    public static double[] keraaNZAlkiot(double[][] arvot, int nZAlkioita) {
        double[] palautettava = new double[nZAlkioita];
        int k = 0;
        
        for (int i = 0; i < arvot.length; i++) {
            for (int j = 0; j < arvot[0].length; j++) {
                if (arvot[i][j]!= 0) {
                    palautettava[k]=arvot[i][j];
                    k++;
                }
            }
        }
        return palautettava;
    }
    
    
    
    /**
     * Hae ensimmäisten non-zero-alkioiden indeksit. Metodi tutkii kunkin matriisin rivin ja
     * selvittää sen ensimmäisen ei-nolla-alkion sarakeindeksin. 
     * @param arvot double arvoja sisältävät kaksiulotteinen taulukko.
     * @param nzalkiot ei-nolla-alkioiden lukumäärä.
     * @return Yksiulotteinen lista, jossa on kunkin rivin osalta tieto ensimmäisen nz-alkion paikasta
     * sekä viimeisenä alkiona kaikkien matriisin alkioiden määrä.
     */
    public static int[] muodostaIAlista(double[][] arvot) {
        int m = arvot.length;
        int n = arvot[0].length;
        int aiempienNZAlkioidenmaara;
        int[] palautettava = new int[m+1];
        
        for (int i = 0; i < m; i++) {
            aiempienNZAlkioidenmaara=0;
        
            for (int h = 0; h < i; h++) {
                aiempienNZAlkioidenmaara=aiempienNZAlkioidenmaara+laskeNZAlkiotRiviltaI(arvot,h);
            }
            
            for (int j = 0; j < n; j++) {
                if (arvot[i][j]!=0) {
                    palautettava[i] =aiempienNZAlkioidenmaara;
                    break;
                }
                palautettava[i]=-1;
            }
        }
        
        
        palautettava[m]=arvot[0].length*arvot[1].length;
        return palautettava;
    }
    
    
    public static int laskeNZAlkiotRiviltaI(double[][] matriisi, int i) {
        int n = matriisi[0].length;
        int lukumaara =0;
        for (int j = 0; j < n; j++) {
            if (matriisi[i][j]!=0) {
                lukumaara++;
            }
        }
        return lukumaara;
    }
    
    public int[] getIA() {
        return this.ia;
    }
    
    public int[] getJA() {
        return this.ja;
    }
    
    /**
     * Hae kaikkien non-zero-alkioiden indeksit. Metodi tuottaa listan kaikkien 
     * ei-nolla alkioiden sijainneista sarakkeiden suhteen.
     * @param arvot
     * @param nzalkiot
     * @return 
     */
    public static int[] muodostaJAlista(double[][]arvot, int nzalkiot) {
        int[] palautettava = new int[nzalkiot];
        int palautettavanIndeksi = 0;
        for (int i = 0; i < arvot[0].length;i++) {
            for (int k=0; k < arvot[1].length;k++) {
                if (arvot[i][k]!=0) {
                    palautettava[palautettavanIndeksi]=k;
                    palautettavanIndeksi++;
                }
            }
        }
        return palautettava;
        
        
    }
    
    /**
     * Skalaarimonikerta. Metodi kertoo jokaisen matriisin ei-nolla-alkion 
     * parametrina annettulla skalaarilla.
     * @param skalaari double tyyppinen reaaliluku
     * @return metodi palauttaa matriisin Yale-formaatissa.
     */
    public YaleMatrix smoni(double skalaari) {
        for (int i = 0; i < this.a.length;i++) {
            a[i] = a[i]*skalaari;
        }
        
        return this;
    }

    public double[][] kerro(double[][] kertoja) {
        int m = this.m;
        int n = kertoja[0].length;
        int rivinEnsimmaisenAlkionIndeksiAssa;
        int rivinViimeisenAlkionIndeksiAssa;
        double summa;
        int anArvonSarake;
        double[][] palautettava = new double[m][n];
        
        for (int i = 0; i < m; i++) {
            rivinEnsimmaisenAlkionIndeksiAssa = this.ia[i];
            if (rivinEnsimmaisenAlkionIndeksiAssa == -1) {
                for (int j = 0; j < n;j++) {
                    palautettava[i][j] = 0;
                }
            }
            
            else {
                
                int seuraavanRivinEkanAlkionIndeksiAssa=a.length;
                for (int h = i+1; h < m; h++) {
                    if (this.ia[h] != -1) {
                        seuraavanRivinEkanAlkionIndeksiAssa=this.ia[h];
                        break;
                    }
                }
                rivinViimeisenAlkionIndeksiAssa = seuraavanRivinEkanAlkionIndeksiAssa-1;
                for (int j = 0; j < n; j++) {
                    summa=0;
                    for (int rivinIalkionIndeksiAssa = rivinEnsimmaisenAlkionIndeksiAssa; rivinIalkionIndeksiAssa <= rivinViimeisenAlkionIndeksiAssa; rivinIalkionIndeksiAssa++) {
                        anArvonSarake = ja[rivinIalkionIndeksiAssa];
                        summa = summa + a[rivinIalkionIndeksiAssa]*kertoja[anArvonSarake][j];
                    }                    
                    palautettava[i][j] = summa;
                }
            }
                
            
        }
        return palautettava;
    }
    
    
    public static int[] laskeRivinIindeksitAssa(int[] ia, int i) {

        int[] palautettava = new int[2];
        palautettava[0] = ia[i];
        int k = i+1;
        
        while (ia[k]==-1 && k < ia.length-1) {
            k++;
        }
        palautettava[1] = ia[k]-1;
        if (palautettava[1] == ia.length-1) {
            palautettava[1] = -1;
        }
        return palautettava;
    }
    
        
    
    public Matrix plus(Matrix lisattava) {
        return new BasicMatrix(Peruslasku.plus(this.getArray(), lisattava.getArray()));
    }

    public double det() throws Exception {
        return Peruslasku.det(this.getArray());
    }

    public Matrix kerro(Matrix matrix) {
        return new BasicMatrix(this.kerro(matrix.getArray()));
    }

    public Matrix minus(Matrix matrix) {
        return new BasicMatrix(Peruslasku.minus(this.getArray(), matrix.getArray()));
    }

    public Matrix inv() throws Exception {
        return new BasicMatrix(Peruslasku.inv(this.getArray()));
    }

    public Matrix rref() throws Exception {
        return new BasicMatrix(Peruslasku.gaussjordan(this.getArray()));
    }
    
    public LUPdecomposition lup() throws Exception {
        return new LUPdecomposition(this.getArray());
    }
    
    public void print() {
        System.out.print(Taulukko.toString(this.getArray()));
    }
    
    public double[][] getArray() {
        int alkioidenmaara = this.ia[this.ia.length-1];
        int rivienmaara = this.ia.length-1;
        int sarakkeidenmaara = (int)((double)alkioidenmaara/(double)rivienmaara);
        double[][] palautettava = new double[rivienmaara][sarakkeidenmaara];
        int[] alkujaloppu;
        for (int i = 0; i < rivienmaara; i++) {
            alkujaloppu = laskeRivinIindeksitAssa(this.ia, i);
            if (alkujaloppu[0]==-1) {
                for (int j = 0; j < sarakkeidenmaara;j++) {
                    palautettava[i][j] = 0;
                }
            }
            else {
                if (alkujaloppu[1] < 0) {
                    alkujaloppu[1] = this.a.length-1;
                }
                if (alkujaloppu[1] > this.a.length-1) {
                    alkujaloppu[1] = this.a.length-1;
                }
                System.out.println("rivin " + i+" alku "+alkujaloppu[0]);
                System.out.println("rivin " + i +" loppu " + alkujaloppu[1]);
                for (int k = alkujaloppu[0]; k <= alkujaloppu[1]; k++) {
                    palautettava[i][this.ja[k]] = this.a[k];
                }
            }
        }
        return palautettava;
    }
    
    


    
}
