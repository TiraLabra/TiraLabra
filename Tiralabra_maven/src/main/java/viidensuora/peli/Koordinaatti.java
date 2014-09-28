package viidensuora.peli;

/**
 * Pelimerkin tai siirron koordinaatti. Ei vielä käytössä...
 *
 * @author juha
 */
public class Koordinaatti {

    private final int i;
    private final int j;

    public Koordinaatti(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

}
