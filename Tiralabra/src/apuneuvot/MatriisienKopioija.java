package apuneuvot;

/**
 * MatriisienKopioija-luokka, jonka avulla voidaan tuottaa kopioita matriiseista.
 * 
 * @author Eversor
 */
public class MatriisienKopioija {
    
    /**
     * Metodi, joka kopioi parametrina annetun neliömatriisin sisällön tyhjään
     * matriisiin ja lopuksi palauttaa vastakopioidun matriisin.
     * 
     * @param matriisi Matriisi, jonka sisältö kopioidaan tyhjään matriisiin, 
     *                 muotoa n x n
     * @return Palauttaa parametrina annetun matriisin kopion muotoa n x n
     */
    public double[][] kopioiNeliomatriisi(double[][] matriisi) {
        tarkasta(matriisi);
        double[][] X = new double[matriisi.length][matriisi.length];
        for (int rivi = 0; rivi < matriisi.length; rivi++) {
            for (int sarake = 0; sarake < matriisi[0].length; sarake++) {
                X[rivi][sarake] = matriisi[rivi][sarake];
            }
        }
        return X;
    }
    
    /**
     * Metodi, joka tarkastaa onko parametrina annettu matriisi neliömatriisi.
     * Heittää poikkeuksen, jos ehto ei täyty.
     * 
     * @param matriisi Matriisi, josta halutaan selvittää onko neliömatriisi,
     *                 muotoa m x n
     */
    private void tarkasta(double[][] matriisi) {
        if (matriisi.length != matriisi[0].length) {
            throw new IllegalArgumentException("Matriisi ei ole neliömatriisi,"
                    + "joten sitä ei voida kopioida tällä metodilla");
        }
    }
}