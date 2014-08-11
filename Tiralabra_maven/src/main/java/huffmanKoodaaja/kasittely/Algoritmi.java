package huffmanKoodaaja.kasittely;

import huffmanKoodaaja.kasittely.tietorakenteet.Frekvenssitaulu;
import huffmanKoodaaja.kasittely.tietorakenteet.Puu;

/**
 * Luokka vastaa algoritmin ydinlogiikasta. Vastaa sekä pakkaus- että
 * purkulogiikasta.
 */
public class Algoritmi {

    private Tiedosto tiedosto;
    /**
     * True = tiedosto pakataan, False = tiedosto puretaan
     */
    private boolean pakkaus;

    /**
     * Asettaa algoritmissa käytettävän tiedoston ja hakee tiedon pakataanko
     * vaiko puretaanko.
     *
     * @param tiedosto Pakattava/purettava tiedosto
     */
    public void setTiedosto(Tiedosto tiedosto) {
        this.tiedosto = tiedosto;
        this.pakkaus = tiedosto.isPakkaus();
    }

    /**
     * Ohjaa tiedoston käsittelyn oikeaan algoritmiin.
     */
    public void kasittele() {
        if (pakkaus) {
            pakkaa();
        } else {
            pura();
        }
    }

    /**
     * Pakkaa käyttäen Huffman-koodausta.
     */
    private void pakkaa() {
        Frekvenssitaulu taulukko = new Frekvenssitaulu();
        tiedosto.lueTaulukoksi(taulukko);
        Puu puu = new Puu();
        puu.luo(taulukko.luoJono());
        tiedosto.kirjoitaTaulukko(taulukko);
        tiedosto.kirjoitaPakattu(puu);
    }

    /**
     * Purkaa Huffman-koodausta.
     */
    private void pura() {
        Frekvenssitaulu taulukko = new Frekvenssitaulu();
        tiedosto.lueTaulukko(taulukko);
        Puu puu = new Puu();
        puu.luo(taulukko.luoJono());
        tiedosto.kirjoitaPurettu(puu);
    }

}
