package logiikka;

import java.util.Arrays;

/**
 * Matriisilaskin-luokka, joka kapseloi sisäänsä kaikki laskuoperaatiot.
 *
 * @author Eversor
 */
public class Matriisilaskin {

    private Yhteenlasku yhteenlasku;
    private Vahennyslasku vahennyslasku;
    private Skalaaritulo skalaaritulo;
    private Kertolasku kertolasku;
    private Transpoosi transpoosi;
    private Strassen strassen;

    /**
     * Konstruktori, joka luo uudet ilmentymät laskuoperaatio-luokista. 
     * Ilmentymät asetetaan niiden private muuttujiin.
     *
     */
    public Matriisilaskin() {
        yhteenlasku = new Yhteenlasku();
        vahennyslasku = new Vahennyslasku();
        skalaaritulo = new Skalaaritulo();
        kertolasku = new Kertolasku();
        transpoosi = new Transpoosi();
        strassen = new Strassen(yhteenlasku, vahennyslasku);
    }

    /**
     * Metodi, joka kutsuu Yhteenlasku-luokan summaa metodia parametreinaan
     * kaksi summattavaa matriisia. Huom. matriisien oltava samankokoisia!
     *
     * @param A Ensimmäinen yhteenlaskettava matriisi muotoa m x n
     * @param B Toinen yhteenlaskettava matriisi muotoa m x n
     * @return Palauttaa yhteenlasketun matriisin muotoa m x n
     */
    public double[][] summaa(double[][] A, double[][] B) {
        return yhteenlasku.summaa(A, B);
    }

    /**
     * Metodi, joka kutsuu Vahennyslasku-luokan vahenna metodia parametreinaan
     * kaksi toistaan vähennettävää matriisia. Huom. matriisien oltava
     * samankokoisia!
     *
     * @param A Ensimmäinen matriisi, josta vähennetään toinen matriisi, muotoa
     *          m x n
     * @param B Toinen matriisi, joka vähennetään ensimmäisestä matriisista,
     *          muotoa m x n
     * @return Palauttaa erotusmatriisin muotoa m x n
     */
    public double[][] vahenna(double[][] A, double[][] B) {
        return vahennyslasku.vahenna(A, B);
    }

    /**
     * Metodi, joka kutsuu Skalaaritulo-luokan kerro metodia parametreinaan
     * matriisi sekä reaaliluku, jolla kyseistä matriisia kerrotaan.
     *
     * @param A Matriisi, jonka alkiot halutaan kertoa reaaliluvulla
     * @param c Reaaliluku, jolla kerrotaan matriisin alkiot
     * @return Palauttaa reaaliluvulla kerrotun matriisin
     */
    public double[][] kerro(double[][] A, double c) {
        return skalaaritulo.kerro(A, c);
    }

    /**
     * Metodi, joka kutsuu Kertolasku-luokan kerro metodia parametreinaan kaksi
     * toisiinsa kerrottavaa matriisia. Toteuttaa yhtälön tulomatriisi = eka *
     * toka. Huom. matriisien kertolasku ei ole vaihdannainen, joten
     * laskujärjestyksellä on väliä! Huom2. ensimmäisen matriisin sarakkeiden
     * määrä pitää vastata toisen matriisin rivien määrää!
     *
     * @param eka Vasemmalta luettuna ensimmäinen matriisi muotoa m x n
     * @param toka Vasemmalta luettuna toinen matriisi muotoa n x q
     * @return Palauttaa tulomatriisin muotoa m x q
     */
    public double[][] kerro(double[][] eka, double[][] toka) {
        return kertolasku.kerro(eka, toka);
    }

    /**
     * Metodi, joka kutsuu Transpoosi-luokan transpoosaa metodia parametrinaan
     * matriisi, jonka transpoosi halutaan.
     *
     * @param A Matriisi, jonka transpoosi halutaan
     * @return Palauttaa matriisin transpoosin
     */
    public double[][] transpoosaa(double[][] A) {
        return transpoosi.transpoosaa(A);
    }

    /**
     * Metodi, joka kutsuu Strassen-luokan kerro metodia parametrinaan kaksi
     * toisiinsa kerrottavaa matriisia. Totetuttaa yhtälön tulomatriisi = eka *
     * toka. Huom. matriisien kertolasku ei ole vaihdannainen, joten
     * laskujärjestyksellä on väliä! Huom2. matriisien oltava samankokoisia
     * neliömatriiseja!
     *
     * @param eka Vasemmalta luettuna ensimmäinen matriisi muotoa n x n
     * @param toka Vasemmalta luettuna toinen matriisi muotoa n x n
     * @return Palauttaa tulomatriisin muotoa n x n
     */
    public double[][] kerroStrassenilla(double[][] eka, double[][] toka) {
        return strassen.kerro(eka, toka);
    }

    /**
     * Metodi, joka selvittää onko annettu matriisi neliömatriisi. Matriisi on
     * neliömatriisi, jos sen rivien ja sarakkeiden lukumäärät ovat samat.
     *
     * @param A Matriisi, josta halutaan tieto onko neliömatriisi
     * @return Palauttaa true, jos matriisi on neliömatriisi
     */
    public boolean onkoNeliomatriisi(double[][] A) {
        return A.length == A[0].length;
    }

    /**
     * Metodi, joka selvittää onko annettu matriisi symmetrinen. Matriisi on
     * symmetrinen, jos sen transpoosi on matriisi itse.
     *
     * @param A Matriisi, jonka symmetrisyys halutaan selvittää
     * @return Palauttaa true, jos matriisi on symmetrinen
     */
    public boolean onkoSymmetrinen(double[][] A) {
        return Arrays.deepEquals(A, transpoosaa(A));
    }
}