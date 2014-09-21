package viidensuora.peli;

/**
 * Pelilaudalle asetettavia nollia kuvaava luokka.
 * 
 * @author juha
 */
public class Nolla extends Pelimerkki {

    public Nolla(int i, int j) {
        super(i, j);
    }

    @Override
    public String toString() {
        return "O";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Nolla;
    }

}
