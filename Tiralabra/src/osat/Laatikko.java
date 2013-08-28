package osat;

/**
 * Luokka, joka kuvaa yksittäistä laatikkoa, joka sisältää mittatiedot, sekä
 * tuotteen tunnisteena käytettävän EAN-koodin.
 *
 * @author albis
 */
public class Laatikko extends Nelikulmio{
    /**
     * Kokonaisluku, joka kertoo kyseisen laatikon leveyden.
     */
    private int leveys;
    
    /**
     * Kokonaisluku, joka kertoo kyseisen laatikon pituuden.
     */
    private int pituus;
    
    /**
     * Kokonaisluku, joka kertoo kyseisen laatikon korkeuden.
     */
    private int korkeus;
    
    /**
     * Tuotteet toisistaan erottava tunniste.
     */
    private long EAN;
    
    public Laatikko(int leveys, int pituus, int korkeus, long EAN) {
        super(leveys, pituus, korkeus);
        this.EAN = EAN;
    }
    
    public long getEAN() {
        return EAN;
    }
    
    public void setEAN(long EAN) {
        this.EAN = EAN;
    }
}