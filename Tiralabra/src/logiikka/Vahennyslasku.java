package logiikka;

/**
 * Vahennyslasku-luokka, jonka avulla saadaan kahden m x n -kokoisen matriisin
 * alkiot vähennettyä toisistaan.
 *
 * @author Eversor
 */
public class Vahennyslasku {

    /**
     * Metodi, joka vähentää kahden annetun matriisin alkiot toisistaan ja
     * palauttaa erotusmatriisin. Tarkastaa aluksi ovatko matriisit
     * samankokoiset ja heittää poikkeuksen, jos ne ovat erikokoisia.
     * Toimintaperiaatteena on matriisien vastinalkioiden erotus.
     *
     * @param A Ensimmäinen matriisi josta vähennetään toinen matriisi, muotoa 
     *          m x n
     * @param B Toinen matriisi, joka vähennetään ensimmäisestä matriisista,
     *          muotoa p x q
     * @return Palauttaa erotusmatriisin muotoa m x n, jos m = p ja n = q
     */
    public double[][] vahenna(double[][] A, double[][] B) {
        tarkasta(A, B);
        double[][] erotusmatriisi = new double[A.length][A[0].length];

        for (int rivi = 0; rivi < erotusmatriisi.length; rivi++) {
            for (int sarake = 0; sarake < erotusmatriisi[0].length; sarake++) {
                erotusmatriisi[rivi][sarake] += A[rivi][sarake] - B[rivi][sarake];
            }
        }
        return erotusmatriisi;
    }

    /**
     * Metodi, joka tarkastaa toteuttavatko parametreina annetut matriisit
     * vähennyslaskun ehdon, eli ovatko matriisit samankokoiset. Heittää
     * poikkeuksen, jos ehto ei täyty.
     *
     * @param A Matriisi muotoa m x n
     * @param B Matriisi muotoa p x q
     */
    private void tarkasta(double[][] A, double[][] B) {
        if (A.length != B.length || A[0].length != B[0].length) {
            throw new IllegalArgumentException("Matriisit eivät ole "
                   + "samankokoisia, joten niitä ei voida vähentää toisistaan");
        }
    }
}