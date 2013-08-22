package Tietorakenteet;

/**
 * Oma karsittu interface Map-rakenteesta. Huomaa että tietoa ei voi poistaa
 * taulusta koska algoritmin toteutuksessa tätä ominaisuutta ei tarvittu
 *
 */
public interface OmaMap<K, V> {

    /**
     * Metodi joka tyhjentää hakurakenteen
     */
    public void clear();

    /**
     * Palauttaa taulussa olevien avain-arvo-parien määrän
     *
     * @return Avain-arvo-parien määrä
     */
    public int size();

    /*
     * Palauttaa avaimeen sidotun arvon tai null jos avainta ei ole
     * @param key Avain jota vastaava arvo halutaan
     * @return Haluttu arvo tai null jos avainta ei ole hakurakenteessa
     */
    public V get(Object key);

    /**
     * Kertoo onko hakurakenne tyhjä
     *
     * @return Onko hakurakenne tyhjä
     */
    public boolean isEmpty();

    /**
     * Asettaa kyseistä avainta vastaavan arvon hakurakenteeseen. Mahdollinen
     * vanha arvo tuhoutuu
     *
     * @param key Avain johon arvo sidotaan
     * @param value Avaimeen sidottava arvo
     */
    public void put(K key, V value);

    /**
     * Palauttaa listan kaikista taulun avaimista
     *
     * @return Lista taulun avaimista
     */
    public OmaList<K> avaimet();
}
