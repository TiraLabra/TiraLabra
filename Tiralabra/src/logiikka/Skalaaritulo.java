package logiikka;

/**
 * Skalaaritulo-luokka, jonka avulla saadaan kerrottua matriisin alkiot
 * annetulla reaaliluvulla.
 *
 * @author Eversor
 */
public class Skalaaritulo {

    /**
     * Metodi, joka kertoo annetun matriisin alkiot reaaliluvulla ja palauttaa
     * kerrotun matriisin. Kertolaskun yhteydessä lisätään alkioon 0, jotta
     * arvoa -0.0 ei esiintyisi.
     * 
     * @param A Matriisi, jonka alkiot halutaan kertoa reaaliluvulla
     * @param c Reaaliluku, jolla kerrotaan matriisin alkiot
     * @return Palauttaa reaaliluvulla kerrotun matriisin
     */
    public double[][] kerro(double[][] A, double c) {
        double[][] C = new double[A.length][A[0].length];
        
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                C[i][j] = c * A[i][j] + 0;
            }
        }
        return C;
    }
}
