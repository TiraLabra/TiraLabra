package pacman.tietorakenteet;

import pacman.alusta.Peliruutu;

/**
 * Lista on luokka, joka kuvaa ArrayListin toimintaa.
 *
 * @author Hanna
 */
public class Lista {

    /**
     * Taulukko, johon on tallennettuna olennainen data.
     */
    private Object[] ruudut;
    /**
     * Taulukon koko
     */
    private int koko;

    /**
     * Konstruktori, jossa määritellään, että lista on aluksi tyhjä taulukko ja
     * sen koko on 0.
     */
    public Lista() {
        ruudut = new Peliruutu[0];
        koko = 0;
    }

    /**
     * Lisää metodi lisää listaan uuden alkion.
     *
     * @param alkio
     */
    public void lisaa(Object alkio) {
        koko++;
        Object[] taulukko = new Object[koko];
        System.arraycopy(ruudut, 0, taulukko, 0, ruudut.length);
        taulukko[taulukko.length - 1] = alkio;

        ruudut = taulukko;

    }

    /**
     * Metodi palauttaa listan koon.
     *
     * @return
     */
    public int koko() {
        return ruudut.length;
    }

    /**
     * Metodi tarkistaa sisältääkö lista parametrina annetun alkion.
     *
     * @param alkio
     * @return
     */
    public boolean sisaltaa(Object alkio) {
        for (int i = 0; i < ruudut.length; i++) {
            if (ruudut[i].equals(alkio)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodi tulostaa kaikki listan jäsenet.
     */
    public void tulostaLista() {
        for (int i = 0; i < ruudut.length; i++) {
            System.out.println(ruudut[i]);
        }
    }

    /**
     * Metodi hakee listasta alkion, jonka indeksi on annettu parametrinä.
     *
     * @param indeksi
     * @return
     */
    public Object getAlkio(int indeksi) {
        return ruudut[indeksi];
    }

    /**
     * Metodi kertoo onko lista tyhjä.
     *
     * @return
     */
    public boolean onkoTyhja() {
        return ruudut.length == 0;
    }
}
