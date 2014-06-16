package logiikka;

import apuneuvot.MatriisienGeneroija;

/**
 * Potenssi-luokka, jonka avulla korotetaan nopeammin neliömatriiseja potenssiin.
 * 
 * @author Eversor
 */
public class Potenssi {
    
    private MatriisienGeneroija generoija;
    private Strassen strassen;
    
    /**
     * Konstruktori, joka tallettaa parametreina annetut MatriisienGeneroija- ja
     * Strassen-luokkien ilmentymät private-muuttujiin.
     * 
     * @param generoija
     * @param strassen 
     */
    public Potenssi(MatriisienGeneroija generoija, Strassen strassen){
        this.generoija = generoija;
        this.strassen = strassen;
    }
    
    /**
     * Metodi, joka korottaa parametrina annetun neliömatriisin annettuun
     * potenssiin. Tarkastaa aluksi onko matriisi neliömatriisi, jonka jälkeen
     * aloittaa rekursiivisesti laskemaan matriisin potenssia. Palauttaa lopuksi
     * rekursion tuloksena syntyneen matriisin potenssin.
     * 
     * @param A Matriisi, joka halutaan korottaa potenssiin, muotoa m x n
     * @param potenssi Kokonaisluku, jolla halutaan korottaa matriisi potenssiin
     * @return Palauttaa potenssiin korotetun neliömatriisin
     */
    public double[][] neliomatriisiPotenssiin(double[][] A, int potenssi) {
        tarkasta(A);
        return rekursioAlgo(A, potenssi);
    }
    
    /**
     * Metodi, joka rekursiivisesti laskee parametrina annetun matriisin 
     * potensseja haluttuun potenssiin asti. Jos potenssi on pienempi kuin nolla,
     * heittää poikkeuksen, koska matriisien potenssiin korottaminen on
     * määritelty vain positiivisilla kokonaisluvuilla. Nolla-potenssi palauttaa
     * neliömatriisin kokoisen yksikkömatriisin. Parillisuudesta riippuen
     * korottaa rekursiivisesti potenssiin kaksi Strassenin kertolaskulla,
     * parittomana palauttaa matriisilla kerrotun rekursioAlgon tuloksen.
     * Lopuksi palauttaa neliömatriisin korotettuna haluttuun potenssiin.
     * 
     * @param A Matriisi, joka halutaan korottaa potenssiin, muotoa n x n
     * @param potenssi Kokonaisluku, jolla halutaan korottaa matriisi potenssiin
     * @return Palauttaa potenssiin korotetun neliömatriisin
     */
    private double[][] rekursioAlgo(double[][] A, int potenssi) {
        if(potenssi < 0) {
            throw new IllegalArgumentException("Potenssin pitää olla nollaa "
                    + "suurempi tai yhtä suuri kokonaisluku.");
        } else if(potenssi == 0) {
            return generoija.luoYksikkomatriisi(A.length);
        } else if(potenssi == 1) {
            return A;
        } else if(potenssi % 2 == 0) {
            return rekursioAlgo(strassen.kerro(A, A), potenssi/2);
        } else {
            return strassen.kerro(A, rekursioAlgo(
                                     strassen.kerro(A, A), (potenssi - 1) / 2));
        }
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
            throw new IllegalArgumentException("Matriisi ei ole neliömatriisi, "
                    + "joten sen potenssia ei voida laskea");
        }
    }
}