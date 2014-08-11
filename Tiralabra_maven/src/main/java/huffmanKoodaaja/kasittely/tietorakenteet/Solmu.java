package huffmanKoodaaja.kasittely.tietorakenteet;

/**
 * Puun tai jonon solmu.
 */
public class Solmu {

    /**
     * Tiedostossa esiintyvä tavu taikka null-arvossa solmu ilman merkkiä.
     */
    private int merkki;
    /**
     * Merkin taikka solmun lasten yhteenlaskettu frekvenssi.
     */
    private int frekvenssi;

    public Solmu(int merkki, int frekvenssi) {
        this.merkki = merkki;
        this.frekvenssi = frekvenssi;
    }

    public int getMerkki() {
        return merkki;
    }

    public int getFrekvenssi() {
        return frekvenssi;
    }
}
