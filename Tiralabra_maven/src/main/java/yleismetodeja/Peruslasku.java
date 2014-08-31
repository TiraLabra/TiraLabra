
package yleismetodeja;
import omamatriisipaketti.LUPdecomposition;
/**
 * Peruslaskutoimituksia. Tähän luokkaan on koottu staattisia metodeja, jotka 
 * operoivat kaksiulotteisilla taulukoilla ja suorittavat perusmatriisioperaatioita.
 * @author risto
 */
public class Peruslasku {
    
    
    /**
     * Plus. Metodi laskee alkioittain yhteen kaksi taulukkoa. 
     * @param a Double[][]-tyyppinen taulukko.
     * @param b Double[][]-tyyppinen taulukko.
     * @return Metodi palauttaa Double[][]-tyyppisen taulukon. 
     * @throws Jos taulukot eivät ole saman kokoisia, metodi heittää poikkeuksen.
     */
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
    /**
     * Miinus. Metodi laskee kahden kaksiulotteisen taulukon alkioiden erotuksen.
     * @param a Double[][]-tyyppinen taulukko.
     * @param b Double[][]-tyyppinen taulukko.
     * @return Metodi palauttaa kaksiulotteisen taulukon.
     * @throws Mikäli taulukot eivät ole samaa ulottuvuutta, metodi heittää poikkeuksen.
     */
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
     * Kertolasku naivisti toteutettuna. Metodi laskee lineaarikuvausten yhdistämisen
     * suoraviivaisesti. Kerrottava kerrotaan kertojalla, joten laskusuuntua on oikealta vasemmalle
     * parametrien suhteen.
     * @param kerrottava double[][]-tyyppinen taulukko.
     * @param kertoja double[][]-tyyppinen taulukko.
     * @return Double[][]-tyyppinen taulukko.
     * @throws Exception Matriisien ulottuvuuksien on oltava oikein, tai metodi heittää poikkeuksen.
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
     * sivun pituus ei satu olemaan kahden potenssi. Laskusuunta on parametrien suhteen oikealta
     * vasemmalle, joten lasketaan a*b.
     * @param a double[][] tyyppinen neliömatriisi.
     * @param b double[][] tyyppinen nelioömatriisi.
     * @param m matriisin sivun pituus.
     * @param minN raja-arvo, jota pienemmät laskut tehdään naivilla menetelmällä. 
     * @return double[][] tyyppinen taulukko.
     */
    public static double[][] yleinenStrassen(double[][] a, double[][] b, int m, int minN) {
        int oikeaKoko = etsiLahinSuurempiKahdenPotenssi(m);
        if (m == oikeaKoko) {
            return strassen(a,b,m,minN);
        }    
        
        a = Taulukko.lisaaNollaRivejaHaluttuunKokoonSaakka(a, oikeaKoko, m);
        b = Taulukko.lisaaNollaRivejaHaluttuunKokoonSaakka(b, oikeaKoko, m);
        double[][] tulos = strassen(a,b,oikeaKoko,minN);
        return Taulukko.poistaUloimmatNrivia(tulos,oikeaKoko-m);
        
    }
    
    
    
    /**
     * Strassen algoritmi neliömatriisien kertomiseksi. Tässä algoritmi on implementoitu
     * kopioimalla matriiseja indeksien laskemisen sijaan, mikä on ymmärtääkseni katastrofi
     * tehokkuuden kannalta. Asymptoottiseen aikavaatimukseen asia ei kuitenkaan vaikuta.
     * @param a kerrottava neliömatriisi double[][] tyyppisenä taulukkona
     * @param b kertova neliömatriisi double[][] tyyppisenä taulukkona
     * @param m matriisien sivujen pituudet. 
     * @param minN raja-arvo, jota pienemmät matriisit lasketaan tavallisella menetelmällä. 
     * Strassen algoritmiin liittyy hieman overheadia, mistä syystä suoraviivainen menetelmä on
     * pienillä matriisella nopeampi.
     * @return 
     */
    public static double[][] strassen(double[][] a, double[][] b,int m,int minN) {
        if (m < minN) {
            return naivemultiply(a,b);
        }
        // ositetaan matriisit neljään osaan
        double[][] a11 = Taulukko.ensimmainenNeljannes(a);
        double[][] a12 = Taulukko.toinenNeljannes(a);
        double[][] a21 = Taulukko.kolmasNeljannes(a);
        double[][] a22 = Taulukko.neljasNeljannes(a);
        double[][] b11 = Taulukko.ensimmainenNeljannes(b);
        double[][] b12 = Taulukko.toinenNeljannes(b);
        double[][] b21 = Taulukko.kolmasNeljannes(b);
        double[][] b22 = Taulukko.neljasNeljannes(b);
        
        //välivaiheessa muodostetaan näistä apumatriiseja, jotka helpottavat notaatiota
        double[][] s1 = minus(b12,b22);
        double[][] s2 = plus(a11,a12);
        double[][] s3 = plus(a21,a22);
        double[][] s4 = minus(b21, b11);
        double[][] s5 = plus(a11, a22);
        double[][] s6 = plus(b11,b22);
        double[][] s7 = minus(a12,a22);
        double[][] s8 = plus(b21,b22);
        double[][] s9 = minus(a11,a21);
        double[][] s10 = plus(b11,b12);
        
        // lasketaan rekursiivisesti lisää apumatriiseja
        double[][] p1 = strassen(a11,s1,m/2,minN);
        double[][] p2 = strassen(s2, b22, m/2,minN);
        double[][] p3 = strassen(s3, b11, m/2, minN);
        double[][] p4 = strassen(a22,s4, m/2,minN);
        double[][] p5 = strassen(s5,s6,m/2,minN);
        double[][] p6 = strassen(s7,s8,m/2,minN);
        double[][] p7 = strassen(s9,s10,m/2,minN);
        
        // nyt voidaan ilmaista tulosmatriisin osat edellä muodostettujen matriisien avulla
        double[][] c11 = minus(plus(plus(p5,p4),p6),p2);
        double[][] c12 = plus(p1,p2);
        double[][] c21 = plus(p3,p4);
        double[][] c22 = minus(plus(p5,p1),plus(p3,p7));
        // lopuksi nämä yhdistetään yhdeksi tulosmatriisiksi.
        return Taulukko.yhdista(c11,c12,c21,c22, m);
    }
    
    
    /**
     * Etsi lähin suurempi kahden potenssi. Metodi etsii pienemmän muotoa 2^n olevan
     * luvun, joka on suurempi kuin parametrina annettu luku.
     * @param m kokonaisluku
     * @return kokonaisluku, joka on kahden potenssi ja suurempi kuin m
     */
    
    public static int etsiLahinSuurempiKahdenPotenssi(int m) {
        double lahinkahdenpotenssi = 0;
        double tulos = 0;
        while (tulos < m) {
            lahinkahdenpotenssi++;
            tulos = Math.pow(2, lahinkahdenpotenssi);
        }        
        return (int)tulos;
    }
    
    public static double det(double[][] matriisi) throws Exception {
       
        LUPdecomposition lu = new LUPdecomposition(matriisi);
        return lu.det();
    }
    
    /**
     * Gauss-Jordan eliminointi. Metodi suorittaa gauss-jordan eliminoinnin eli
     * laskee reduced row echelon muodon matriisille.
     * @param pmatriisi double[][] tyyppinen matriisi.
     * @return double[][] tyyppinen matriisi, joka on rre-muodossa.
     */
    
    public static double[][] gaussjordan(double[][] pmatriisi){
        return Peruslasku.jordan(Peruslasku.gauss(pmatriisi));
    }
    
    public static double[][] gauss(double[][] pmatriisi){
        int m = pmatriisi.length;
        int n = pmatriisi[0].length;
        double[][] matriisi = Taulukko.kopioiArray(pmatriisi);
        /**
         * Vaihdettavan rivin numero
         */
        int k1=0;
        /**
         * pivot
         */
        double p;
        double kerroin = 0;
        int tutkittavaSarake=0;
        //gauss
        for (int kasiteltavaRivi = 0; kasiteltavaRivi < m; kasiteltavaRivi++) {
            
            p = 0;
            while (p==0 && tutkittavaSarake < n) {
                for (int i = kasiteltavaRivi; i < m; i++) {
                    if (Math.abs(matriisi[i][tutkittavaSarake]) > p) {
                        p = matriisi[i][tutkittavaSarake];
                        k1 = i;
                    }
                }
            Taulukko.vaihdaRivit(matriisi,kasiteltavaRivi,k1);
            tutkittavaSarake++;
            }
            
            for (int i = kasiteltavaRivi+1; i < m; i++) {
                    kerroin = matriisi[i][tutkittavaSarake-1]/p;
                    for (int j = 0; j < n; j++) {
                        double tulos = matriisi[i][j] - kerroin*matriisi[kasiteltavaRivi][j];
                        if (Math.abs(tulos)<0.00001) {
                            matriisi[i][j] = 0;
                        }
                        else matriisi[i][j] = tulos;
                    }
                }
                
            
            }        
        
        
        return matriisi;
    }
    
    public static double[][] jordan(double[][] pmatriisi) {
        double[][] matriisi = pmatriisi;
        int m = matriisi.length;
        int n = matriisi[0].length;
        int pivotinSarake;
        double kerroin;
        for (int kasiteltavaRivi = m-1; kasiteltavaRivi > 0; kasiteltavaRivi--) {
            pivotinSarake=etsiRivinPivotinSarake(matriisi,kasiteltavaRivi);
            if (pivotinSarake!=-1) {
                for (int i = kasiteltavaRivi-1; i >= 0; i--){    
                    kerroin = matriisi[i][pivotinSarake]/matriisi[kasiteltavaRivi][pivotinSarake];
                    for (int j = pivotinSarake; j < n; j++) {
                        double tulos =matriisi[i][j] - kerroin*matriisi[kasiteltavaRivi][j];
                        if (Math.abs(tulos)<0.00001) {
                            matriisi[i][j] = 0;
                        }
                        else matriisi[i][j] = tulos;
                    }
                }
            }
        }
        
        
        
        return jaaKukinRiviPivotillaan(matriisi);
    }
    
    public static int etsiRivinPivotinSarake(double[][] matriisi, int i) {
        int n = matriisi[i].length;
        for (int j = 0; j < n; j++) {
            if (matriisi[i][j]!=0) {
                return j;
            }
        }
        return -1;
        
    }
    
    public static double[][] jaaKukinRiviPivotillaan(double[][] matriisi) {
        int m = matriisi.length;
        int n = matriisi[0].length;
        int pivotinSarake;
        double pivot;
        
        for (int i = 0; i < m; i++) {
            pivotinSarake = etsiRivinPivotinSarake(matriisi,i);
            if (pivotinSarake!=-1) {
                pivot = matriisi[i][pivotinSarake];
                for (int j = 0; j < n; j++) {
                    matriisi[i][j] = matriisi[i][j]/pivot;
                }
            }
        }
        
        return matriisi;
    }
    
    
    
    /**
     * Inverse. Metodi laskee kääntömatriisin käyttäen Gauss-Jordan eliminointia apuna. 
     * Algoritmissa matriisiin listään oikealla identiteettimatriisi ja suoritetaan Gauss-Jordan eliminointi.
     * Tämän jälkeen oikealla olevasta matriisista saadaan kääntömatriisi. It's like magic. 
     * @param matriisi double[][] tyyppinen neliömatriisi
     * @return double[][] tyyppinen matriisi.
     */
    public static double[][] inv(double[][] matriisi) throws Exception {
        int n = matriisi.length;
        double[][] identity = new double[n][n];
        Taulukko.kirjoitaYkkosiaDiagonaalille(identity);
        double[][] lisatty = Taulukko.augment(matriisi, identity);
        lisatty = gaussjordan(lisatty);
        for (int i = 0; i < n; i++) {
            if (lisatty[i][i]-1 > 0.00001) {
                throw new IllegalArgumentException("Matriisi ei ole kääntyvä");
            }
        }
        return Taulukko.poistaNSarakettaVasemmalta(lisatty, n);
    }
    
    public static double[][] smoni(double[][] matriisi, double skalaari) {
        double[][] palautettava = new double[matriisi.length][matriisi[0].length];
        for (int i = 0; i < matriisi.length; i++) {
            for (int j = 0; j < matriisi[0].length; j++) {
                palautettava[i][j] = matriisi[i][j]*skalaari;
            }
        }
        return palautettava;
    }
    

    
    /**
     * Laske diagonaalialkioiden tulo. Metodi laskee matriisin diagonaalilla olevien alkioiden tulon. 
     */
    public static double laskeDiagonaaliAlkioidenTulo(double[][] matriisi) {
        double tulo = 1;
        for (int i = 0; i < matriisi.length; i++) {
            tulo = tulo*matriisi[i][i];
        }
        return tulo;
    }

}


