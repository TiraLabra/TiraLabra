package viidensuora.logiikka;

/**
 * Pelilaudalle asetettavia pelimerkkejä kuvaava enum.
 * 
 * @author juha
 */
public enum Pelimerkki {

    NOLLA("O"), RISTI("X");

    private final String merkki;

    private Pelimerkki(String merkki) {
        this.merkki = merkki;
    }

    /**
     * 
     * @return Pelimerkin merkkijonoesitys
     */
    @Override
    public String toString() {
        return merkki;
    }
}
