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
     * kerrotun matriisin.
     * 
     * @param A Matriisi, jonka alkiot halutaan kertoa reaaliluvulla
     * @param c Reaaliluku, jolla kerrotaan matriisin alkiot
     * @return Palauttaa reaaliluvulla kerrotun matriisin
     */
    public double[][] kerro(double[][] A, double c) {
        double[][] tulomatriisi = new double[A.length][A[0].length];
        
        for (int rivi = 0; rivi < A.length; rivi++) {
            for (int sarake = 0; sarake < A[0].length; sarake++) {
                tulomatriisi[rivi][sarake] += c * A[rivi][sarake];
            }
        }
        return tulomatriisi;
    }
}