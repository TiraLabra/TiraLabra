
package yleismetodeja;

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
        
        a = muodostaKahdenPotenssiMatriisi(a, oikeaKoko, m);
        b = muodostaKahdenPotenssiMatriisi(b, oikeaKoko, m);
        double[][] tulos = strassen(a,b,m,minN);
        return poistaUloimmatNrivia(tulos,oikeaKoko-m);
        
    }
    
    
    /**
     * Poista uloimmat n riviä. Metodi poistaa parametrina annetusta neliömatriisista
     * uloimmat n riviä. 
     * @param matriisi double[][] tyyppinen taulukko.
     * @param n kokonaisluku, joka kertoo montako riviä poistetaan.
     * @return double[][] tyyppinen neliömatriisi, jonka sivun pituus on n pienempi kuin
     * parametrina saadun taulukon.
     */
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
     * @param a kerrottava neliömatriisi double[][] tyyppisenä taulukkona
     * @param b kertova neliömatriisi double[][] tyyppisenä taulukkona
     * @param m matriisien sivujen pituudet. 
     * @param minN raja-arvo, jota pienemmät matriisit lasketaan tavallisella menetelmällä. 
     * Strassen algoritmiin liittyy hieman overheadia, mistä syystä suoraviivainen menetelmä on
     * pienillä matriisella nopeampi.
     * @return 
     */
       
    public static double[][] strassen(double[][] a, double[][] b, int m, int minN) {
        if (m < minN) {
            return naivemultiply(a,b);
        }
        double[][] m1 = strassen(plus(ensimmainenNeljannes(a), neljasNeljannes(a)),plus(ensimmainenNeljannes(b), neljasNeljannes(b)),m/2, minN);
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
    
    public static double[][] strassen2(double[][] a, double[][] b, int m, int minN) {
        if (m < minN) {
            return naivemultiply(a,b);
        }
        double[][] m1 = naivemultiply(plus(ensimmainenNeljannes(a), neljasNeljannes(a)),plus(ensimmainenNeljannes(b), neljasNeljannes(b)));
        double[][] m2 = naivemultiply(plus(kolmasNeljannes(a),neljasNeljannes(b)),ensimmainenNeljannes(b)); 
        double[][] m3 = naivemultiply(ensimmainenNeljannes(a), minus(toinenNeljannes(b),neljasNeljannes(b)));
        double[][] m4 = naivemultiply(neljasNeljannes(a),minus(kolmasNeljannes(b),ensimmainenNeljannes(b)));
        double[][] m5 = naivemultiply(plus(ensimmainenNeljannes(a),toinenNeljannes(a)),neljasNeljannes(b));
        double[][] m6 = naivemultiply(minus(kolmasNeljannes(a),ensimmainenNeljannes(a)), plus(ensimmainenNeljannes(b),toinenNeljannes(b)));
        double[][] m7 = naivemultiply(minus(toinenNeljannes(a),neljasNeljannes(a)),plus(kolmasNeljannes(b),neljasNeljannes(b)));
        double[][] c11 = minus(plus(plus(m1,m4),m7),m5);
        double[][] c12 = plus(m3,m5);
        double[][] c21 = plus(m2,m4);
        double[][] c22 = minus(plus(plus(m1,m3),m6),m2);        
        return yhdista(c11,c12,c21,c22, m);        
        
        
    }
    
    public static double[][] strassen3(double[][] a, double[][] b, int m, int minN) {
        if (m < minN) {
            return naivemultiply(a,b);
        }
        double[][] operandi1 = plus(ensimmainenNeljannes(a), neljasNeljannes(a));
        double[][] operandi2 = plus(ensimmainenNeljannes(b), neljasNeljannes(b));
        double[][] m1 = strassen(operandi1,operandi2,m/2, minN);
        
        operandi1 = plus(kolmasNeljannes(a),neljasNeljannes(b));
        operandi2 = ensimmainenNeljannes(b);
        double[][] m2 = strassen(operandi1,operandi2, m/2, minN);
        
        operandi1 = ensimmainenNeljannes(a);
        operandi2 = minus(toinenNeljannes(b),neljasNeljannes(b));
        double[][] m3 = strassen(operandi1, operandi2, m/2, minN);
        
        operandi1 = neljasNeljannes(a);
        operandi2 = minus(kolmasNeljannes(b),ensimmainenNeljannes(b));
        double[][] m4 = strassen(operandi1,operandi2, m/2, minN);
        
        operandi1 = plus(ensimmainenNeljannes(a),toinenNeljannes(a));
        operandi2 = neljasNeljannes(b);
        double[][] m5 = strassen(operandi1,operandi2,m/2,minN);
        
        operandi1 = minus(kolmasNeljannes(a),ensimmainenNeljannes(a));
        operandi2 = plus(ensimmainenNeljannes(b),toinenNeljannes(b));
        double[][] m6 = strassen(operandi1, operandi2,m/2,minN);
        
        operandi1 = minus(toinenNeljannes(a),neljasNeljannes(a));
        operandi2 = plus(kolmasNeljannes(b),neljasNeljannes(b));
        double[][] m7 = strassen(operandi1,operandi2,m/2,minN);
        
        double[][] c11 = minus(plus(plus(m1,m4),m7),m5);
        double[][] c12 = plus(m3,m5);
        double[][] c21 = plus(m2,m4);
        double[][] c22 = minus(plus(plus(m1,m3),m6),m2);        
        return yhdista(c11,c12,c21,c22, m);
    }
    
    public static double[][] uusiStrassen(double[][] a, double[][] b,int m,int minN) {
        if (m < minN) {
            return naivemultiply(a,b);
        }
        
        double[][] a11 = ensimmainenNeljannes(a);
        double[][] a12 = toinenNeljannes(a);
        double[][] a21 = kolmasNeljannes(a);
        double[][] a22 = neljasNeljannes(a);
        double[][] b11 = ensimmainenNeljannes(b);
        double[][] b12 = toinenNeljannes(b);
        double[][] b21 = kolmasNeljannes(b);
        double[][] b22 = neljasNeljannes(b);
        
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
        
        double[][] p1 = uusiStrassen(a11,s1,m/2,minN);
        double[][] p2 = uusiStrassen(s2, b22, m/2,minN);
        double[][] p3 = uusiStrassen(s3, b11, m/2, minN);
        double[][] p4 = uusiStrassen(a22,s4, m/2,minN);
        double[][] p5 = uusiStrassen(s5,s6,m/2,minN);
        double[][] p6 = uusiStrassen(s7,s8,m/2,minN);
        double[][] p7 = uusiStrassen(s9,s10,m/2,minN);
        
        double[][] c11 = minus(plus(plus(p5,p4),p6),p2);
        double[][] c12 = plus(p1,p2);
        double[][] c21 = plus(p3,p4);
        double[][] c22 = minus(plus(p5,p1),plus(p3,p7));
        
        return yhdista(c11,c12,c21,c22, m);
    }
    
    /**
     * Muodosta kahden potenssi -matriisi. Strassen algoritmissa vaaditaan, että matriisin sivun
     * pituus on jokin kahden potenssi. Tämän takia matriisiin lisätään nollarivejä, jotka lopuksi
     * poistetaan
     * @param matriisi double[][] tyyppinen taulukko, johon lisätään rivejä. 
     * @param oikeaKoko haluttu matriisin sivun pituus
     * @param m nykyinen matriisin sivun pituus
     * @return double[][] tyyppinen taulukko, jonka sivun pituus on parametrin mukainen.
     */
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
    
    /**
     * Yhdistä. Metodi yhdistää parametreina annetut neljä matriisia yhdeksi suuremmaksi
     * matriisiksi. Kaikkien parametrina annettavien matriisien tulee olla saman kokosia neliö-
     * matriiseja.
     * @param c11 neliömatriisi double[][] taulukkona
     * @param c12 neliömatriisi double[][] taulukkona
     * @param c21 neliömatriisi double[][] taulukkona
     * @param c22 neliömatriisi double[][] taulukkona
     * @param m matriisien sivujen pituus
     * @return 
     */
    public static double[][] yhdista(double[][] c11, double[][] c12, double[][] c21, double[][] c22,int m) {
        double[][] tulos = new double[m][m];
        kirjoitaTaulukkoonOsataulukko(tulos,c11,0,0);
        kirjoitaTaulukkoonOsataulukko(tulos,c12,0,m/2);
        kirjoitaTaulukkoonOsataulukko(tulos,c21,m/2,0);
        kirjoitaTaulukkoonOsataulukko(tulos,c22,m/2,m/2);
        
        
        return tulos;
    }
    
    /**
     * Kirjoita taulukkoon osataulukko. Metodi kirjoittaa parametrina annettuun taulukkoon
     * parametrina annetun pienemmän taulukon aloittaen annetusta pisteestä.
     * @param tulos double[][] tyyppinen matriisi, johon kirjoitetaan toisen taulukon arvot.
     * @param kirjoitettava double[][] tyyppinen matriisin, jonka arvot kirjoitetaan tulokseen.
     * @param vasemmanYlakulmanRivi pienemmän matriisin sijainti suuremassa ilmoitettuna pienemmän
     * matriisin vasemmassa yläkulmassa olevan alkion rivin sijainnilla tulosmatriisissa.
     * @param vasemmanYlakulmanSarake pienemmän matriisin sijainti suuremassa ilmoitettuna pienemmän
     * matriisin vasemmassa yläkulmassa olevan alkion sarakkeen sijainnilla tulosmatriisissa.
     */
    public static void kirjoitaTaulukkoonOsataulukko(double[][] tulos, double[][] kirjoitettava, int vasemmanYlakulmanRivi, int vasemmanYlakulmanSarake) {
        int kirjoitettavanIndeksiI=0;
        int kirjoitettavanIndeksiJ;
        
        for (int i = vasemmanYlakulmanRivi; i < vasemmanYlakulmanRivi + kirjoitettava.length; i++) {
            kirjoitettavanIndeksiJ = 0;
            for (int j = vasemmanYlakulmanSarake; j < vasemmanYlakulmanSarake + kirjoitettava[0].length; j++) {
                tulos[i][j] = kirjoitettava[kirjoitettavanIndeksiI][kirjoitettavanIndeksiJ];
                kirjoitettavanIndeksiJ++;
            }
            kirjoitettavanIndeksiI++;
        }
    }
    
    /**
     * Ensimmäinen neljännes. Metodi kirjoittaa neliömatriisin vasemmassa yläkulmassa olevat
     * arvot pienempään matriisiin.
     * @param ositettava on matriisi, josta arvoja kopioidaan
     * @return taulukko, joka on kooltaan neljännes alkuperäisestä.
     */
    public static double[][] ensimmainenNeljannes(double[][] ositettava) {
        int m = ositettava[0].length;
        double[][] tulos = new double[m/2][m/2];
        for (int i = 0; i < m/2; i++) {
            for (int j = 0; j < m/2; j++) {
                tulos[i][j] = ositettava[i][j];
            }
        }
        return tulos;
        
    }
    
    /**
     * Toinen neljännes. Metodi kirjoittaa neliömatriisin oikeassa yläkulmassa olevat arvot
     * pienempään matriisiin.
     * @param ositettava matriisi, josta arvoja kopioidaan.
     * @return taulukko, joka on kooltaan neljännes parametrina annetusta matriisista.
     */
    public static double[][] toinenNeljannes(double[][] ositettava) {
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
    /**
     * Kolmas neljännes. Metodi osittaa parametrina annetun taulukon poimimalla 
     * vasemman alakulman alkiot uuteen taulukkoon.
     * @param ositettava neliömatriisi double[][]-muodossa
     * @return double[][] tyyppinen neliömatriisi.
     */
    public static double[][] kolmasNeljannes(double[][] ositettava) {
        int m = ositettava[0].length;
        double[][] tulos = new double[m/2][m/2];
        int palautettavanIndeksiI=0;
        for (int i = m/2; i < m; i++) {
            for (int j = 0; j < m/2; j++) {
                tulos[palautettavanIndeksiI][j] = ositettava[i][j];                
            }
            palautettavanIndeksiI++;
        }
        return tulos;
    }
    
    
    public static double[][] gaussjordan(double[][] matriisi){
        return null;
    }
    
    /**
     * Neljäs neljännes. Metodi poimii parametrina annetusta neliömatriisista
     * oikean alakulman alkiot uuteen matriisiin.
     * @param ositettava neliömatriisi double[][] tyyppisenä
     * @return double[][] taulukko.
     */
    public static double[][] neljasNeljannes(double[][] ositettava) {
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


