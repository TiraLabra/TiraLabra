package domain;

/**
 * Pino-luokka, joka vastaa ohjelman tietorakenteen toteutuksesta
 *
 * @author csgit
 */
public class Pino {

    /**
     * Kokonaisluku, joka kertoo pinon huipun viitteen
     */
    private int top;
    /**
     * Object-luokan taulukko-olio, johon tallennettaan pinon oliot
     */
    private Object[] table;

    /**
     * Konstruktori, joka luo uuden tyhjän pinon, jonka koko annetaan
     * parametrina
     *
     * @param pinonKoko Kokonaisluku
     */
    public Pino(int pinonKoko) {
        top = -1;
        table = new Object[pinonKoko];
    }

    /**
     * Lisää parametrina olevan olion pinon päällimmäiseksi
     *
     * @param lisattava Object-luokan olio
     */
    public void push(Object lisattava) {
        top++;
        table[top] = lisattava;
    }

    /**
     * Poistaa pinon päällimmäisenä olevan olion, ja palauttaa sen metodin
     * arvona
     *
     * @return Object-luokan olio
     */
    public Object pop() {
        Object poistettava = table[top];
        top--;
        return poistettava;
    }

    /**
     * Testaa, onko pino tyhjä
     *
     * @return Totuusarvo (tosi tai epätosi)
     */
    public boolean empty() {
        return top == -1;
    }

    /**
     * Kertoo pinon päällimmäisenä olevan olion metodin paluuarvona poistamatta
     * sitä pinosta
     *
     * @return Object-luokan olio
     */
    public Object peek() {
        return table[top];
    }

    /**
     * Kertoo, kuinka monta oliota pinoon enintään mahtuu
     *
     * @return Kokonaisluku
     */
    public int getSize() {
        return table.length;
    }

    /**
     * Kertoo pinossa olevien olioiden lukumäärän metodin paluuarvona
     *
     * @return Kokonaisluku
     */
    public int getOlioidenLkm() {
        return top + 1;
    }

    /**
     * Kertoo pinon päällimäisenä olevan olion sijainnin taulukossa
     *
     * @return Kokonaisluku
     */
    public int getTop() {
        return top;
    }
}