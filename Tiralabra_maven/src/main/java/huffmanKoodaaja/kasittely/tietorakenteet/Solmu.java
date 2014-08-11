package huffmanKoodaaja.kasittely.tietorakenteet;

public class Solmu {
    private int merkki;
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
