package main;

/**
 * Kekoalkio kekotietorakennetta ja A*-hakua varten.
 */
public class HeapElement {

    /**
     * Kekoalkiot ovat jossain labyrintin koordinaatissa.
     */
    private final int coordinate;
    /**
     * Kuljettu matka tässä kohtaa.
     */
    private final int distance;
    /**
     * Kustannusarvio maaliin tästä kohtaa.
     */
    private final int heuristic;

    /**
     * Asettaa kekoalkion koordinaatiksi annetun koordinaatin ja arvoksi annetun
     * arvon.
     *
     * @param c Koordinaatti.
     * @param d Kuljettu matka.
     * @param h Kustannusarvio maaliin.
     */
    public HeapElement(int c, int d, int h) {
        coordinate = c;
        distance = d;
        heuristic = h;
    }

    /**
     * Palauttaa sen koordinaatin, jossa annettu kekoalkio on.
     *
     * @return Palauttaa kekoalkion koordinaatin.
     */
    public int getCoordinate() {
        return coordinate;
    }

    /**
     * Palauttaa kuljetun matkan.
     *
     * @return Palauttaa kuljetun matkan.
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Palauttaa kekoalkion arvon.
     *
     * @return Palauttaa kekoalkion arvon.
     */
    public int getValue() {
        return distance + heuristic;
    }
}
