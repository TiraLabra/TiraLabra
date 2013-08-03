package Tietorakenteet;

/**
 * Karsittu versio list-rajapinnasta työn tarpeisiin <p> Poistettu metodit joita
 * en tarvitse
*
 */
public interface OmaList<T> {

    /**
     * Metodi joka lukee palauttaa listan koon. Arvo aina ei-negatiivinen
     *
     * @return listan koko
     */
    public int size();

    /**
     * Metodi joka palauttaa booleanin joka kertoo onko lista tyhjä (true on
     * tyhjä, false ei ole tyhjä)
     *
     * @return onko lista tyhjä
     */
    public boolean isEmpty();

    /**
     * Metodi joka kertoo onko kyseinen objekti listassa. Palauttaa booleanin
     *
     * @param o Objekti josta halutaan tietää onko se listassa
     * @return Onko annettu objekti listassa
     */
    public boolean contains(Object o);

    /**
     * Metodi joka lisää annetun objektin listaan.
     *
     * @param e Objekti joka halutaan lisätä listaan
     * @return Palauttaa aina true
     */
    public boolean add(T e);

    /**
     * Poistaa annetun objetkin listalta jos se löytyy. Palauttaa booleanin joka kertoo muokattiinko listaa
     * @param o Objekti joka halutaan poistaa
     * @return Palauttaa true jos objekti löytyi listalta, false jos ei löytynyt
     */
    public boolean remove(Object o);

    /**
     * Metodi joka tyhjentää listan
     *
     */
    public void clear();

    /**
     * Metodi joka palauttaa objektin annetusta listan kohdasta <br> Heittää
     * IndexOutOfBoundsException-poikkeuksen jos indeksi on negatiivinen tai
     * yritetään lukea indeksiä joka on suurempi tai yhtäsuuri kuin listan koko
     *
     * @param index Indeksi jolta kohtaa halutaan palauttaa objekti
     * @return Objekti joka on listalla indeksin kohdalla
     * @throws IndexOutOfBoundsException jos indeksi on epäkelpo
     */
    public T get(int index);

    /**
     * Metodi joka korvaa objektin annetusta listan kohdasta <br> Heittää
     * IndexOutOfBoundsException-poikkeuksen jos indeksi on negatiivinen tai
     * yritetään lukea indeksiä joka on suurempi tai yhtäsuuri kuin listan koko
     * <br> Palauttaa objektin joka oli ennen korvausta kyseisen indeksin
     * kohdalla
     *
     * @param index Indeksi jolta kohtaa halutaan korvata objekti
     * @param element Objekti joka halutaan sijoittaa indeksin kohdalle
     * @return Objekti joka oli listalla indeksin kohdalla ennen korvaamista
     * @throws IndexOutOfBoundsException jos indeksi on epäkelpo
     */
    public T set(int index, Object element);

    /**
     * Metodi joka poistaa objektin annetusta listan kohdasta <br> Heittää
     * IndexOutOfBoundsException-poikkeuksen jos indeksi on negatiivinen tai
     * yritetään lukea indeksiä joka on suurempi tai yhtäsuuri kuin listan koko
     *
     * @param index Indeksi jolta kohtaa halutaan poistaa objekti
     * @return Objekti joka oli listalla indeksin kohdalla
     * @throws IndexOutOfBoundsException jos indeksi on epäkelpo
     */
    public T remove(int index);

    /**
     * Metodi joka palauttaa kopion sisäisestä taulukosta
     *
     * @return Taulukko joka sisältää listan objektit.
     */
    public Object[] toArray();
}
