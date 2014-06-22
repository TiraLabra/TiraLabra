package logiikka;

/**
 * Kertolasku-luokka, jonka avulla saadaan kerrottua kaksi matriisia keskenään,
 * jos ensimmäisen matriisin sarakkeiden määrä vastaa toisen matriisin rivien
 * määrää.
 *
 * @author Eversor
 */
public class Kertolasku {

    /**
     * Metodi, joka kertoo kaksi matriisia keskenään ja palauttaa niiden
     * tulomatriisin. Tarkastaa aluksi toteuttavatko matriisit kertolaskun
     * ehdon, eli vastaako ensimmäisen matriisin sarakkeiden määrä toisen
     * matriisin rivien määrää. Toimintaperiaatteena on tulomatriisin alkiota
     * koskevan rivin ja sarakkeen pistetulo, missä rivi otetaan ensimmäisestä
     * matriisista ja sarake toisesta matriisista.
     *
     * @param eka Vasemmalta luettuna ensimmäinen matriisi muotoa m x n
     * @param toka Vasemmalta luettuna toinen matriisi muotoa p x q
     * @return Palauttaa tulomatriisin muotoa m x q, jos n = q
     */
    public double[][] kerro(double[][] eka, double[][] toka) {
        tarkasta(eka, toka);
        double[][] tulomatriisi = new double[eka.length][toka[0].length];

        for (int rivi = 0; rivi < eka.length; rivi++) {
            for (int sarake = 0; sarake < toka[0].length; sarake++) {
                for (int i = 0; i < eka[0].length; i++) {
                    tulomatriisi[rivi][sarake] += eka[rivi][i] * toka[i][sarake];
                }
            }
        }

        return tulomatriisi;
    }

    /**
     * Metodi, joka tarkastaa toteuttavatko parametreina annetut matriisit
     * kertolaskun ehdon, eli vastaako ensimmäisen matriisin sarakkeiden määrä
     * toisen matriisin rivien määrää. Heittää poikkeuksen, jos ehto ei täyty.
     *
     * @param eka Vasemmalta luettuna ensimmäinen matriisi muotoa m x n
     * @param toka Vasemmalta luettuna toinen matriisi muotoa p x q
     */
    public void tarkasta(double[][] eka, double[][] toka) {
        if (eka[0].length != toka.length) {
            throw new IllegalArgumentException("Matriisien kertolaskua ei voida"
                    + "suorittaa, koska ensimmäisen matriisin sarakkeiden määrä"
                    + "ei vastaa toisen matriisin rivien määrää");
        }
    }
}