
package yleismetodeja;

/**
 * Peruslaskutoimituksia. Tähän luokkaan on koottu staattisia metodeja, jotka 
 * operoivat kaksiulotteisilla taulukoilla.
 * @author risto
 */
public class Peruslasku {
    
    
    
    public static double[][] plus(double[][] a, double[][] b) {
        int m = a.length;
        int n = a[0].length;
        double[][] tulos = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                tulos[i][j] = a[i][j] + b[i][j];
            }
        }
        return tulos;
    }

    public static double[][] minus(double[][] a, double[][] b) {
        int m = a.length;
        int n = a[0].length;
        double[][] tulos = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                tulos[i][j] = a[i][j] - b[i][j];
            }
        }
        return tulos;
    }
    
    
    /**
     * Kertolasku naivisti toteutettuna. 
     * @param matrix
     * @return
     * @throws Exception 
     */    
    
    public static double[][] naivemultiply(double[][] kerrottava, double[][] kertoja) {
        int mkerrottava = kerrottava.length;
        int nkertoja = kertoja[0].length;
        
        
        double[][] tulos = new double[mkerrottava][nkertoja];
        
        for (int i = 0; i < mkerrottava;i++) {
            for (int j = 0; j < nkertoja; j++) {
                // kukin tuloksen alkio on kerrottavan rivin i ja kertojan sarakkeen j pistetulo

                double summa = 0;
                for (int k = 0; k < mkerrottava; k++) {
                    summa = summa + kerrottava[i][k]*kertoja[k][j];
                }
                tulos[i][j]=summa;
            }
             
        }
        return tulos;
    }
    
    /**
     * Strassen algoritmi yleistettynä. Metodi toimii myös neliömatriiseille, joiden
     * sivun pituus ei satu olemaan kahden potenssi.
     * @param a
     * @param b
     * @param m
     * @param minN
     * @return 
     */
    public static double[][] yleinenStrassen(double[][] a, double[][] b, int m, int minN) {
        int oikeaKoko = etsiLahinSuurempiKahdenPotenssi(m);
        if (m == oikeaKoko) {
            return strassen(a,b,m,minN);
        }    
        
        a = muodostaKahdenPotenssiMatriisi(a, oikeaKoko, m);
        b = muodostaKahdenPotenssiMatriisi(b, oikeaKoko, m);
        double[][] tulos = strassen(a,b,m,minN);
        return poistaUloimmatNrivia(tulos,oikeaKoko-m);
        
    }
    
    public static double[][] poistaUloimmatNrivia(double[][] matriisi, int n) {
        int m = matriisi.length;
        double[][] palautettava = new double[m-n][m-n];
        int palautettavanIndeksiI = 0;
        int palautettavanIndeksiJ;
        for (int i = 0; i < m-n; i++) {
            palautettavanIndeksiJ = 0;
            for (int j = 0; j < m-n; j++) {
                palautettava[palautettavanIndeksiI][palautettavanIndeksiJ] = matriisi[i][j];
                palautettavanIndeksiJ++;
            }
            palautettavanIndeksiI++;
        }
        return palautettava;
    }
    
    /**
     * Strassen algoritmi neliömatriisien kertomiseksi. Tässä algoritmi on implementoitu
     * kopioimalla matriiseja indeksien laskemisen sijaan, mikä on ymmärtääkseni katastrofi
     * tehokkuuden kannalta. Asymptoottiseen aikavaatimukseen asia ei kuitenkaan vaikuta.
     * @param a
     * @param b
     * @param m
     * @param minN raja-arvo, jota pienemmät matriisit lasketaan tavallisella menetelmällä. 
     * Strassen algoritmiin liittyy hieman overheadia, mistä syystä suoraviivainen menetelmä on
     * pienillä matriisella nopeampi.
     * @return 
     */
       
    public static double[][] strassen(double[][] a, double[][] b, int m, int minN) {
        if (m < minN) {
            return naivemultiply(a,b);
        }
        double[][] m1 = strassen(plus(ensimmainenNeljannes(a), neljasNeljannes(a)),
                plus(ensimmainenNeljannes(b), neljasNeljannes(b)),m/2, minN);
        double[][] m2 = strassen(plus(kolmasNeljannes(a),neljasNeljannes(b)),ensimmainenNeljannes(b), m/2, minN); 
        double[][] m3 = strassen(ensimmainenNeljannes(a), minus(toinenNeljannes(b),neljasNeljannes(b)), m/2, minN);
        double[][] m4 = strassen(neljasNeljannes(a),minus(kolmasNeljannes(b),ensimmainenNeljannes(b)), m/2, minN);
        double[][] m5 = strassen(plus(ensimmainenNeljannes(a),toinenNeljannes(a)),neljasNeljannes(b),m/2,minN);
        double[][] m6 = strassen(minus(kolmasNeljannes(a),ensimmainenNeljannes(a)), plus(ensimmainenNeljannes(b),toinenNeljannes(b)),m/2,minN);
        double[][] m7 = strassen(minus(toinenNeljannes(a),neljasNeljannes(a)),plus(kolmasNeljannes(b),neljasNeljannes(b)),m/2,minN);
        double[][] c11 = minus(plus(plus(m1,m4),m7),m5);
        double[][] c12 = plus(m3,m5);
        double[][] c21 = plus(m2,m4);
        double[][] c22 = minus(plus(plus(m1,m3),m6),m2);        
        return yhdista(c11,c12,c21,c22, m);
    }
    
    private static double[][] muodostaKahdenPotenssiMatriisi(double[][] matriisi, int oikeaKoko, int m){

        double[][] palautettava = new double[oikeaKoko][oikeaKoko];
        kirjoitaTaulukkoonOsataulukko(palautettava,matriisi,0,0);
        for (int i = oikeaKoko-m; i < oikeaKoko; i++) {
            for (int j = 0; j < oikeaKoko; j++) {
                palautettava[i][j] = 0;
            }
        }
        for (int i = 0; i < oikeaKoko; i++) {
            for (int j = oikeaKoko-m; j<oikeaKoko; j++) {
                palautettava[i][j] = 0;
            }
        }
        
        return palautettava;
    }
    
    public static int etsiLahinSuurempiKahdenPotenssi(int m) {
        double lahinkahdenpotenssi = 0;
        double tulos = 0;
        while (tulos < m) {
            lahinkahdenpotenssi++;
            tulos = Math.pow(2, lahinkahdenpotenssi);
        }        
        return (int)tulos;
    }
    
    private static double[][] yhdista(double[][] c11, double[][] c12, double[][] c21, double[][] c22,int m) {
        double[][] tulos = new double[m][m];
        kirjoitaTaulukkoonOsataulukko(tulos,c11,0,0);
        kirjoitaTaulukkoonOsataulukko(tulos,c12,0,m/2);
        kirjoitaTaulukkoonOsataulukko(tulos,c21,m/2,0);
        kirjoitaTaulukkoonOsataulukko(tulos,c22,m/2,m/2);
        
        
        return tulos;
    }
    
    private static void kirjoitaTaulukkoonOsataulukko(double[][] tulos, double[][] kirjoitettava, int vasemmanYlakulmanRivi, int vasemmanYlakulmanSarake) {
        int kirjoitettavanIndeksiI;
        int kirjoitettavanIndeksiJ;
        
        for (int i = vasemmanYlakulmanRivi; i < vasemmanYlakulmanRivi + kirjoitettava.length; i++) {
            kirjoitettavanIndeksiI = 0;
            kirjoitettavanIndeksiJ = 0;
            for (int j = vasemmanYlakulmanSarake; j < vasemmanYlakulmanSarake + kirjoitettava[0].length; j++) {
                tulos[i][j] = kirjoitettava[kirjoitettavanIndeksiI][kirjoitettavanIndeksiJ];
                kirjoitettavanIndeksiJ++;
            }
        }
    }
    
    private static double[][] ensimmainenNeljannes(double[][] ositettava) {
        int m = ositettava[0].length;
        double[][] tulos = new double[m/2][m/2];
        for (int i = 0; i < m/2; i++) {
            for (int j = 0; j < m/2; j++) {
                tulos[i][j] = ositettava[i][j];
            }
        }
        return tulos;
        
    }
    
    private static double[][] toinenNeljannes(double[][] ositettava) {
        int m = ositettava[0].length;
        int palautettavanIndeksiJ;
        double[][] tulos = new double[m/2][m/2];
        for (int i = 0; i < m/2; i++) {
            palautettavanIndeksiJ = 0;
            for (int j = m/2; j < m; j++) {
                tulos[i][palautettavanIndeksiJ] = ositettava[i][j];
                palautettavanIndeksiJ++;
            }
        }
        
        return tulos;
    }
    
    private static double[][] kolmasNeljannes(double[][] ositettava) {
        int m = ositettava[0].length;
        double[][] tulos = new double[m/2][m/2];
        int palautettavanIndeksiI;
        for (int i = m/2; i < m; i++) {
            palautettavanIndeksiI=0;
            for (int j = 0; j < m/2; j++) {
                tulos[palautettavanIndeksiI][j] = ositettava[i][j];
                palautettavanIndeksiI++;
            }
        }
        return tulos;
    }
    
    private static double[][] neljasNeljannes(double[][] ositettava) {
        int m = ositettava.length;
        double[][] tulos = new double[m/2][m/2];
        int palautettavanIndeksiI = 0;
        int palautettavanIndeksiJ = 0;
        for (int i = m/2; i < m; i++) {
            palautettavanIndeksiJ=0;
            for (int j = m/2; j < m; j++) {
                tulos[palautettavanIndeksiI][palautettavanIndeksiJ] = ositettava[i][j];
                
                palautettavanIndeksiJ++;
            }
            palautettavanIndeksiI++;
        }
        return tulos;
    }
}
