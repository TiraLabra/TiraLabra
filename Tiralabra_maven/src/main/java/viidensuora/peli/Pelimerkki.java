package viidensuora.peli;

/**
 * Abstrakti yliluokka Ristille ja Nollalle.
 *
 * @author juha
 */
public abstract class Pelimerkki {

    /**
     * Rivi, jolla pelimerkki sijaitsee.
     */
    private final int i;

    /**
     * Sarake, jolla perimerkki sijaitsee.
     */
    private final int j;

    public Pelimerkki(int rivi, int sarake) {
        this.i = rivi;
        this.j = sarake;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

}
