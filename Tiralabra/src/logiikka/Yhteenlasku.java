package logiikka;

/**
 * Yhteenlasku-luokka, jonka avulla saadaan kahden m x n -kokoisen matriisin
 * alkiot laskettua keskenään yhteen.
 *
 * @author Eversor
 */
public class Yhteenlasku {

    /**
     * Metodi, joka summaa kahden annetun matriisin alkiot keskenään yhteen ja
     * palauttaa yhteenlasketun matriisin. Tarkastaa aluksi ovatko matriisit
     * samankokoiset ja heittää poikkeuksen, jos ne ovat erikokoisia.
     * Toimintaperiaatteena on matriisien vastinalkioiden summa.
     *
     * @param A Ensimmäinen yhteenlaskettava matriisi muotoa m x n
     * @param B Toinen yhteenlaskettava matriisi muotoa p x q
     * @return Palauttaa summamatriisin muotoa m x n, jos m = p ja n = q
     */
    public double[][] summaa(double[][] A, double[][] B) {
        tarkasta(A, B);
        double[][] summamatriisi = new double[A.length][A[0].length];

        for (int rivi = 0; rivi < summamatriisi.length; rivi++) {
            for (int sarake = 0; sarake < summamatriisi[0].length; sarake++) {
                summamatriisi[rivi][sarake] += A[rivi][sarake] + B[rivi][sarake];
            }
        }
        return summamatriisi;
    }

    /**
     * Metodi, joka tarkastaa toteuttavatko parametreina annetut matriisit
     * yhteenlaskun ehdon, eli ovatko matriisit samankokoiset. Heittää
     * poikkeuksen, jos ehto ei täyty.
     *
     * @param A Matriisi muotoa m x n
     * @param B Matriisi muotoa p x q
     */
    private void tarkasta(double[][] A, double[][] B) {
        if (A.length != B.length || A[0].length != B[0].length) {
            throw new IllegalArgumentException("Matriisit eivät ole "
                    + "samankokoisia, joten niitä ei voida laskea yhteen");
        }
    }
}