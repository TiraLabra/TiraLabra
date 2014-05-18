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
     *
     * @param A Ensimmäinen yhteenlaskettava matriisi
     * @param B Toinen yhteenlaskettava matriisi
     * @return Palauttaa yhteenlasketun matriisin
     */
    public double[][] summaa(double[][] A, double[][] B) {
        if (A.length != B.length || A[0].length != B[0].length) {
            throw new IllegalArgumentException("Matriisit eivät ole "
                    + "samankokoisia, joten niitä ei voida laskea yhteen");
        }
        
        double[][] C = new double[A.length][A[0].length];

        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C[0].length; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }
}
