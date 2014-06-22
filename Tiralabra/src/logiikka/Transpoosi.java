package logiikka;

/**
 * Transpoosi-luokka, jonka avulla saadaan luotua matriisin transpoosi.
 *
 * @author Eversor
 */
public class Transpoosi {

    /**
     * Metodi, joka vaihtaa matriisin rivit sarakkeiksi ja päinvastoin.
     *
     * @param A Matriisi, jonka transpoosi halutaan
     * @return Palauttaa matriisin transpoosin
     */
    public double[][] transpoosaa(double[][] A) {
        double[][] transpoosi = new double[A[0].length][A.length];
        for (int rivi = 0; rivi < transpoosi.length; rivi++) {
            for (int sarake = 0; sarake < transpoosi[0].length; sarake++) {
                transpoosi[rivi][sarake] = A[sarake][rivi];
            }
        }
        return transpoosi;
    }
}