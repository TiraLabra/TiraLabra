package osat;

/**
 * Luokka, joka kuvaa yksittäistä lavaa, jolla voi olla eri mittasuhteita.
 * 
 * @author albis
 */
public class Lava {
    /**
     * Kyseisen lavan leveyden kertova kokonaisluku.
     */
    private int leveys;
    
    /**
     * Kyseisen lavan pituuden kertova kokonaisluku.
     */
    private int pituus;
    
    /**
     * Kyseisen lavan korkeuden kertova kokonaisluku.
     */
    private int korkeus;
    
    /**
     * Taulukko, johon mallinnetaan laatikoiden asettamista.
     */
    private boolean[][] asetetutLaatikot;
    
    public Lava(int leveys, int pituus, int korkeus) {
        this.leveys = leveys;
        this.pituus = pituus;
        this.korkeus = korkeus;

        asetetutLaatikot = new boolean[leveys][pituus];
    }
    
    /**
     * Metodi, joka merkitsee annetun kokoisen laatikon täyttävän annetun paikan.
     * 
     * @param x Kertoo asetettavan laatikon vasemman yläkulman sijainnin x-akselilla.
     * @param y Kertoo asetettavan laatikon vasemman yläkulman sijainnin y-akselilla.
     * @param leveys Kokonaisluku, joka kertoo laatikon leveyden.
     * @param pituus Kokonaisluku, joka kertoo laatikon pituuden.
     */
    public void merkitseLaatikko(int x, int y, int leveys, int pituus) {
        for (int i = x; i < x+leveys; i++) {
            for (int j = y; j < y+pituus; j++) {
                    asetetutLaatikot[i][j] = true;
            }
        }
    }
    
    /**
     * Metodi, joka merkitsee annetun ruudun täytetyksi.
     * 
     * @param x Kertoo täytettävän ruudun sijainnin x-akselilla.
     * @param y Kertoo täytettävän ruudun sijainnin y-akselilla.
     */
    public void taytaRuutu(int x, int y) {
        asetetutLaatikot[x][y] = true;
    }
    
    /**
     * Metodi, joka kopioi kyseisen Lava-olion.
     * 
     * @return Palauttaa kopion kyseisestä oliosta.
     */
    public Lava kopioi() {
        Lava kopio = new Lava(leveys, pituus, korkeus);
        
        for (int i = 0; i < leveys; i++) {
            for (int j = 0; j < pituus; j++) {
                kopio.taytaRuutu(i, j);
            }
        }
        
        return kopio;
    }
    
    public int getLeveys() {
        return leveys;
    }
    
    public int getPituus() {
        return pituus;
    }
    
    public int getKorkeus() {
        return korkeus;
    }
    
    /**
     * Kertoo, onko kyseisellä ruudulla laatikko vai ei.
     * 
     * @param x Haetun ruudun sijainti x-akselilla.
     * @param y Haetun ruudun sijainti y-akselilla
     * @return Palauttaa true, jos on tyhjä, false jos ruutu on täysi.
     */
    public boolean onkoTyhja(int x, int y) {
        return asetetutLaatikot[x][y];
    }
}
