package logiikka;

/**
 * Matriisilaskin-luokka, joka kapseloi sisäänsä kaikki laskuoperaatiot.
 *
 * @author Eversor
 */
public class Matriisilaskin {

    private Yhteenlasku yhteenlasku;
    private Skalaaritulo skalaaritulo;

    /**
     * Konstruktori, joka luo uudet ilmentymät laskuoperaatio-luokista.
     *
     */
    public Matriisilaskin() {
        yhteenlasku = new Yhteenlasku();
        skalaaritulo = new Skalaaritulo();
    }

    /**
     * Metodi, joka kutsuu Yhteenlasku-luokan summaa metodia parametreinaan
     * kaksi summattavaa matriisia.
     *
     * @param A Ensimmäinen yhteenlaskettava matriisi
     * @param B Toinen yhteenlaskettava matriisi
     * @return Palauttaa yhteenlasketun matriisin
     */
    public double[][] summaa(double[][] A, double[][] B) {
        return yhteenlasku.summaa(A, B);
    }

    /**
     * Metodi, joka kutsuu Skalaaritulo-luokan kerro metodia parametreinaan
     * matriisi sekä reaaliluku, jolla kyseistä matriisia kerrotaan.
     *
     * @param A Matriisi, jonka alkiot halutaan kertoa reaaliluvulla
     * @param c Reaaliluku, jolla kerrotaan matriisin alkiot
     * @return Palauttaa reaaliluvulla kerrotun matriisin
     */
    public double[][] kerro(double[][] A, double c) {
        return skalaaritulo.kerro(A, c);
    }
}
