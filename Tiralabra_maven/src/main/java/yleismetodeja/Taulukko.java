
package yleismetodeja;

/**
 * Luokka sisältää yleishyödyllisiä metodeja taulukkojen käsittelyyn.
 * @author risto
 */
public class Taulukko {

    /**
     * To string. Metodi muodostaa taulukosta merkkijonon. Tässä ei ole kannettu sen enempää huolta
     * siitä kuinka luettava lopputulos on.
     * @param matriisi double[][]-tyyppinen matriisi.
     * @return String, jossa on matriisin alkiot on lueteltu taulukkona.
     */
    public static String toString(double[][] matriisi){
        int m = matriisi.length;
        int n = matriisi[0].length;
        String tulos = "";
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                tulos = tulos + matriisi[i][j] + "\t";
            }
            tulos = tulos + "\n";
        }
        return tulos;
        
    }

    /**
     *
     * @param lista the value of lista
     * @param rivi1 the value of rivi1
     * @param rivi2 the value of rivi2
     */
    public static void vaihdalistanAlkiot(int[] lista, int rivi1, int rivi2) {
        if (rivi1 == rivi2) {
            return;
        }
        int temp = lista[rivi1];
        lista[rivi1] = lista[rivi2];
        lista[rivi2] = temp;
    }

    /**
     * Ensimmäinen neljännes. Metodi kirjoittaa neliömatriisin vasemmassa yläkulmassa olevat
     * arvot pienempään matriisiin.
     *
     * @param ositettava on matriisi, josta arvoja kopioidaan
     * @return taulukko, joka on kooltaan neljännes alkuperäisestä.
     */
    public static double[][] ensimmainenNeljannes(double[][] ositettava) {
        int m = ositettava[0].length;
        double[][] tulos = new double[m / 2][m / 2];
        for (int i = 0; i < m / 2; i++) {
            for (int j = 0; j < m / 2; j++) {
                tulos[i][j] = ositettava[i][j];
            }
        }
        return tulos;
    }

    /**
     * Yhdistä. Metodi yhdistää parametreina annetut neljä matriisia yhdeksi suuremmaksi
     * matriisiksi. Kaikkien parametrina annettavien matriisien tulee olla saman kokosia neliö-
     * matriiseja.
     *
     * @param c11 neliömatriisi double[][] taulukkona
     * @param c12 neliömatriisi double[][] taulukkona
     * @param c21 neliömatriisi double[][] taulukkona
     * @param c22 neliömatriisi double[][] taulukkona
     * @param m matriisien sivujen pituus
     * @return
     */
    public static double[][] yhdista(double[][] c11, double[][] c12, double[][] c21, double[][] c22, int m) {
        double[][] tulos = new double[m][m];
        kirjoitaTaulukkoonOsataulukko(tulos, c11, 0, 0);
        kirjoitaTaulukkoonOsataulukko(tulos, c12, 0, m / 2);
        kirjoitaTaulukkoonOsataulukko(tulos, c21, m / 2, 0);
        kirjoitaTaulukkoonOsataulukko(tulos, c22, m / 2, m / 2);
        return tulos;
    }

    /**
     * Kolmas neljännes. Metodi osittaa parametrina annetun taulukon poimimalla
     * vasemman alakulman alkiot uuteen taulukkoon.
     *
     * @param ositettava neliömatriisi double[][]-muodossa
     * @return double[][] tyyppinen neliömatriisi.
     */
    public static double[][] kolmasNeljannes(double[][] ositettava) {
        int m = ositettava[0].length;
        double[][] tulos = new double[m / 2][m / 2];
        int palautettavanIndeksiI = 0;
        for (int i = m / 2; i < m; i++) {
            for (int j = 0; j < m / 2; j++) {
                tulos[palautettavanIndeksiI][j] = ositettava[i][j];
            }
            palautettavanIndeksiI++;
        }
        return tulos;
    }

    /**
     * Lisaa nollarivejä haluttuun kokoon saakka. Strassen algoritmissa vaaditaan, että matriisin sivun
     * pituus on jokin kahden potenssi. Tämän takia matriisiin lisätään nollarivejä, jotka lopuksi
     * poistetaan
     *
     * @param matriisi double[][] tyyppinen taulukko, johon lisätään rivejä.
     * @param oikeaKoko haluttu matriisin sivun pituus
     * @param m nykyinen matriisin sivun pituus
     * @return double[][] tyyppinen taulukko, jonka sivun pituus on parametrin mukainen.
     */
    public static double[][] lisaaNollaRivejaHaluttuunKokoonSaakka(double[][] matriisi, int oikeaKoko, int m) {
        double[][] palautettava = new double[oikeaKoko][oikeaKoko];
        kirjoitaTaulukkoonOsataulukko(palautettava, matriisi, 0, 0);
        for (int i = m; i < oikeaKoko; i++) {
            for (int j = 0; j < oikeaKoko; j++) {
                palautettava[i][j] = 0;
            }
        }
        for (int i = 0; i < oikeaKoko; i++) {
            for (int j = m; j < oikeaKoko; j++) {
                palautettava[i][j] = 0;
            }
        }
        return palautettava;
    }

    /**
     * Vaihda rivit.
     *
     */
    public static void vaihdaRivit(double[][] matriisi, int rivi1, int rivi2) {
        if (rivi1 == rivi2) {
            return;
        }
        int n = matriisi.length;
        double[] temp = new double[n];
        for (int i = 0; i < n; i++) {
            temp[i] = matriisi[rivi1][i];
        }
        for (int i = 0; i < n; i++) {
            matriisi[rivi1][i] = matriisi[rivi2][i];
        }
        for (int i = 0; i < n; i++) {
            matriisi[rivi2][i] = temp[i];
        }
    }

    /**
     * Neljäs neljännes. Metodi poimii parametrina annetusta neliömatriisista
     * oikean alakulman alkiot uuteen matriisiin.
     *
     * @param ositettava neliömatriisi double[][] tyyppisenä
     * @return double[][] taulukko.
     */
    public static double[][] neljasNeljannes(double[][] ositettava) {
        int m = ositettava.length;
        double[][] tulos = new double[m / 2][m / 2];
        int palautettavanIndeksiI = 0;
        int palautettavanIndeksiJ = 0;
        for (int i = m / 2; i < m; i++) {
            palautettavanIndeksiJ = 0;
            for (int j = m / 2; j < m; j++) {
                tulos[palautettavanIndeksiI][palautettavanIndeksiJ] = ositettava[i][j];
                palautettavanIndeksiJ++;
            }
            palautettavanIndeksiI++;
        }
        return tulos;
    }

    /**
     * Kirjoita taulukkoon osataulukko. Metodi kirjoittaa parametrina annettuun taulukkoon
     * parametrina annetun pienemmän taulukon aloittaen annetusta pisteestä.
     *
     * @param tulos double[][] tyyppinen matriisi, johon kirjoitetaan toisen taulukon arvot.
     * @param kirjoitettava double[][] tyyppinen matriisin, jonka arvot kirjoitetaan tulokseen.
     * @param vasemmanYlakulmanRivi pienemmän matriisin sijainti suuremassa ilmoitettuna pienemmän
     * matriisin vasemmassa yläkulmassa olevan alkion rivin sijainnilla tulosmatriisissa.
     * @param vasemmanYlakulmanSarake pienemmän matriisin sijainti suuremassa ilmoitettuna pienemmän
     * matriisin vasemmassa yläkulmassa olevan alkion sarakkeen sijainnilla tulosmatriisissa.
     */
    public static void kirjoitaTaulukkoonOsataulukko(double[][] tulos, double[][] kirjoitettava, int vasemmanYlakulmanRivi, int vasemmanYlakulmanSarake) {
        int kirjoitettavanIndeksiI = 0;
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
     * Poista uloimmat n riviä. Metodi poistaa parametrina annetusta neliömatriisista
     * uloimmat n riviä.
     *
     * @param matriisi double[][] tyyppinen taulukko.
     * @param n kokonaisluku, joka kertoo montako riviä poistetaan.
     * @return double[][] tyyppinen neliömatriisi, jonka sivun pituus on n pienempi kuin
     * parametrina saadun taulukon.
     */
    public static double[][] poistaUloimmatNrivia(double[][] matriisi, int n) {
        int m = matriisi.length;
        double[][] palautettava = new double[m - n][m - n];
        int palautettavanIndeksiI = 0;
        int palautettavanIndeksiJ;
        for (int i = 0; i < m - n; i++) {
            palautettavanIndeksiJ = 0;
            for (int j = 0; j < m - n; j++) {
                palautettava[palautettavanIndeksiI][palautettavanIndeksiJ] = matriisi[i][j];
                palautettavanIndeksiJ++;
            }
            palautettavanIndeksiI++;
        }
        return palautettava;
    }

    /**
     * Toinen neljännes. Metodi kirjoittaa neliömatriisin oikeassa yläkulmassa olevat arvot
     * pienempään matriisiin.
     *
     * @param ositettava matriisi, josta arvoja kopioidaan.
     * @return taulukko, joka on kooltaan neljännes parametrina annetusta matriisista.
     */
    public static double[][] toinenNeljannes(double[][] ositettava) {
        int m = ositettava[0].length;
        int palautettavanIndeksiJ;
        double[][] tulos = new double[m / 2][m / 2];
        for (int i = 0; i < m / 2; i++) {
            palautettavanIndeksiJ = 0;
            for (int j = m / 2; j < m; j++) {
                tulos[i][palautettavanIndeksiJ] = ositettava[i][j];
                palautettavanIndeksiJ++;
            }
        }
        return tulos;
    }

    /**
     *
     * @param matriisi the value of matriisi
     * @param alkion1Rivi the value of alkion1Rivi
     * @param alkion1Sarake the value of alkion1Sarake
     * @param alkion2Rivi the value of alkion2Rivi
     * @param alkion2Sarake the value of alkion2Sarake
     */
    public static void vaihdaMatriisinAlkiot(double[][] matriisi, int alkion1Rivi, int alkion1Sarake, int alkion2Rivi, int alkion2Sarake) {
        double temp = matriisi[alkion1Rivi][alkion1Sarake];
        matriisi[alkion1Rivi][alkion1Sarake] = matriisi[alkion2Rivi][alkion2Sarake];
        matriisi[alkion2Rivi][alkion2Sarake] = temp;
    }
    
}
