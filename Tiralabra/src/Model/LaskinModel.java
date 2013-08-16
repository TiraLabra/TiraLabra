package Model;

/**
 * Laskin toteuttaa liukulukutaulukkona annetuille matriiseille
 * laskutoimituksia.
 *
 * @author Ilkka Vähämaa
 */
public class LaskinModel {

    /**
     * Kaikki matriisit voidaan kertoa jollakin vakiolla eli skalaarilla.
     *
     * @param matriisi Liukulukutaulukkona annetaan matriisi, joka kerrotaan
     * skalaarilla
     * @param skalaari Vakioluku, millä kaikki matriisin alkion kerrotaan.
     * @return Palauttaa annetun matriisin skalaarilla kerrottuna
     */
    public double[][] kerroSkalaarilla(double[][] matriisi, double skalaari) {
        for (int i = 0; i < matriisi.length; i++) {
            for (int j = 0; j < matriisi[0].length; j++) {
                matriisi[i][j] *= skalaari;
            }
        }
        return matriisi;
    }

    /**
     * Laskee matriisien summamatriisin.
     *
     * @param matriisiYksi Ensimmäinen yhteenlaskettava matriisi.
     * @param matriisiKaksi Toinen yhteenlaskettava matriisi
     * @return Matriisit laskettuna yhteen.
     * @throws Exception Vain samanmuotoiset matriisit voidaan laskea yhteen ja
     * mikäli tyyppi ei täsmää, metodi palauttaa poikkeuksen.
     */
    public double[][] laskeYhteen(double[][] matriisiYksi, double[][] matriisiKaksi) throws Exception {
        if (matriisiYksi.length == matriisiKaksi.length && matriisiYksi[0].length == matriisiKaksi[0].length) {
            for (int i = 0; i < matriisiYksi.length; i++) {
                for (int j = 0; j < matriisiKaksi[0].length; j++) {
                    matriisiYksi[i][j] += matriisiKaksi[i][j];
                }
            }
            return matriisiYksi;
        } else {
            throw new Exception("Matriisien tyypit eivät täsmää");
        }
    }

    /**
     * Matriisin 1 sarakkeiden määrän täytyy olla sama kuin matriisin 2
     * vaakarivien määrä. Muussa tapauksessa laskutoimitus ei ole määritelty.
     *
     * @param matriisi1 Kertoja matriisi.
     * @param matriisi2 Kerrottava matriisi.
     * @return Matriisien tulo.
     * @throws Exception Metodi palauttaa poikkeuksen jos matriisien tuloa ei
     * ole määritelty.
     */
    public double[][] kerroMatriisit(double[][] matriisi1, double[][] matriisi2) throws Exception {
        if (matriisi1.length == matriisi2[0].length) {
            double[][] uusi = new double[matriisi1.length][matriisi2[0].length];

            for (int rivi = 0; rivi < uusi.length; rivi++) {
                for (int sarake = 0; sarake < uusi[0].length; sarake++) {
                    uusi[rivi][sarake] = laskeAlkio(rivi, sarake, matriisi1, matriisi2);
                }
            }

            return uusi;
        } else {
            throw new Exception("Matriisin tuloa ei voi määritellä");
        }
    }

    /**
     * Apumetodi matriisien tulolle. Alkio muodostuu tuloparien summasta, mitkä
     * tulevat matriisin 1 vaakariviä pitkin ja matriisin 2 saraketta pitkin.
     *
     * @param rivi
     * @param sarake
     * @param matriisi1
     * @param matriisi2
     * @return annetun kohdan alkio tulossa olevien matriisien perusteella.
     */
    private int laskeAlkio(int rivi, int sarake, double[][] matriisi1, double[][] matriisi2) {
        int alkio = 0;
        for (int k = 0; k < matriisi1.length; k++) {
            alkio += (matriisi1[rivi][k] * matriisi2[k][sarake]);
        }
        return alkio;
    }

    /**
     * Metodi jakaa matriisin kahden kolmiomatriisin tuloksi.
     *
     * @param matriisi LU-Dekomposoitava matriisi
     * @return LU-Dekomposoidun matriisin ylempi kolmiomatriisi
     */
    public double[][] luDekompositioDoolittleYlempiKolmiomatriisi(double[][] matriisi) throws Exception {
        if (matriisi.length == matriisi[0].length) {
            double[][] u = new double[matriisi.length][matriisi.length];
            double[][] l = new double[matriisi.length][matriisi.length];


            for (int i = 0; i < matriisi.length; i++) {
                for (int j = i; j < matriisi.length; j++) {
                    u[i][j] = matriisi[i][j];
                    for (int k = 0; k < i; k++) {
                        u[i][j] = u[i][j] - l[i][k] * u[k][j];
                    }
                }
                for (int j = i + 1; j < matriisi.length; j++) {
                    l[j][i] = matriisi[j][i];
                    for (int k = 0; k < i; k++) {
                        l[j][i] = l[j][i] - l[j][k] * u[k][i];
                    }
                    l[j][i] = l[j][i] / u[i][i];
                }
            }
            return u;
        } else {
            throw new Exception("Determinantti on laskettavissa vain neliömatriiseille.");
        }

    }

    /**
     * Metodi jakaa matriisin kahden kolmiomatriisin tuloksi.
     *
     * @param matriisi LU-Dekomposoitava matriisi
     * @return LU-Dekomposoitu matriisi yhteen matriisiin talletettuna.
     */
    public double[][] luDekompositioDoolittle(double[][] matriisi) throws Exception {
        if (matriisi.length == matriisi[0].length) {
            double[][] dekompositio = new double[matriisi.length][matriisi.length];
            for (int i = 0; i < dekompositio.length; i++) {
                for (int j = i; j < dekompositio.length; j++) {
                    for (int k = 0; k < i; k++) {
                        matriisi[i][j] = matriisi[i][j] - matriisi[i][k] * matriisi[k][j];
                    }
                }
                for (int j = i + 1; j < dekompositio.length; j++) {
                    for (int k = 0; k < i; k++) {
                        matriisi[j][i] = matriisi[j][i] - matriisi[j][k] * matriisi[k][i];
                    }
                    matriisi[j][i] = matriisi[j][i] / matriisi[i][i];
                }
            }
            return matriisi;
        } else {
            throw new Exception("Determinantti on laskettavissa vain neliömatriiseille.");
        }
    }

    /**
     * Laskee matriisin determinantin käyttämällä doolittlen algoritmia hajoittamaan matriisin 
     * LU-komponentteihin, minkä jälkeen determinantti saadaan ylemmän kolmiomatriisin diagonaalin tulosta
     * 
     * @param matriisi Matriisi jolle halutaan laskea determinantti.
     * @return matriisin determinantti.
     * @throws Exception Vain neliömatriiseille voidaan laskea determinantti
     */
    public double laskeDeterminantti(double[][] matriisi) throws Exception {
        double[][] laskettava = luDekompositioDoolittleYlempiKolmiomatriisi(matriisi);
        double determinantti = 1;
        for (int i = 0; i < laskettava.length; i++) {
            determinantti = determinantti * laskettava[i][i];
        }
        return determinantti;
    }

}
