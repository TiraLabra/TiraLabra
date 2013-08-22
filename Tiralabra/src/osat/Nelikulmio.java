package osat;

/**
 * Luokka, joka kuvaa yksittäistä muotoa, jolla voi olla eri mittasuhteita.
 * Nämä esittävät siis käytännössä lavoja, aliluokka Laatikko taas luonnollisesti
 * kuvaa laatikkoa.
 * 
 * @author albis
 */
public class Nelikulmio {
    /**
     * Kyseisen muodon leveyden kertova kokonaisluku.
     */
    private int leveys;
    
    /**
     * Kyseisen muodon pituuden kertova kokonaisluku.
     */
    private int pituus;
    
    /**
     * Kyseisen muodon korkeuden kertova kokonaisluku.
     */
    private int korkeus;

    public Nelikulmio(int leveys, int pituus, int korkeus) {
        this.leveys = leveys;
        this.pituus = pituus;
        this.korkeus = korkeus;
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
}
