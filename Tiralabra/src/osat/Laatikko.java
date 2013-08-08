package osat;

/**
 * Luokka, joka kuvaa yksittäistä laatikkoa, joka sisältää mittatiedot, sekä
 * tuotteen tunnisteena käytettävän EAN-koodin.
 *
 * @author albis
 */
public class Laatikko {
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
    private String EAN;
    
    public Laatikko(int leveys, int pituus, int korkeus, String EAN) {
        this.leveys = leveys;
        this.pituus = pituus;
        this.korkeus = korkeus;
        this.EAN = EAN;
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
    
    public String getEAN() {
        return EAN;
    }
}