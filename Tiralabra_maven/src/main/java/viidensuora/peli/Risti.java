package viidensuora.peli;

/**
 * Pelilaudalle asetettavia ristejä kuvaava luokka.
 *
 * @author juha
 */
public class Risti extends Pelimerkki {

    public Risti(int i, int j) {
        super(i, j);
    }

    @Override
    public String toString() {
        return "X";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Risti;
    }

}
