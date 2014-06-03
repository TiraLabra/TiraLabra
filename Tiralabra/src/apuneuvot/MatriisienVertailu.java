package apuneuvot;

/**
 * MatriisienVertailu-luokka, jonka avulla saadaan vertailtua ovatko kaksi
 * matriisia samoja 10^(-10):n tarkkuudella.
 * 
 * @author Eversor
 */
public class MatriisienVertailu {
    
    /**
     * Metodi, joka kertoo ovatko parametreina annetut matriisit samoja.
     * Tarkastaa ensin ovatko matriisit samankokoisia, jos eivät ole niin 
     * palauttaa false. Tämän jälkeen tutkii matriisit alkio kerrallaan ja 
     * palauttaa false, jos eroavuus löytyy. Vertailun tarkkuutena käytetään
     * 10^(-10). Lopuksi palauttaa true, jos matriisit ovat samat.
     * 
     * @param A Matriisi jota vertaillaan muotoa m x n
     * @param B Matriisi jota vertaillaan muotoa p x q
     * @return Palauttaa true, jos matriisit ovat sama
     */
    public boolean vertaile(double[][] A, double[][] B){
        if(A.length != B.length || A[0].length != B[0].length){
            return false;
        }
        for(int rivi = 0; rivi < A.length; rivi++){
            for(int sarake = 0; sarake < A[0].length; sarake++){
                if(Math.abs(A[rivi][sarake] - B[rivi][sarake]) > 0.0000000001) {
                    return false;
                }
            }
        }
        return true;
    }
}