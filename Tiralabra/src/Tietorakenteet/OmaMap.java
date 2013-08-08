package Tietorakenteet;

/**
 * Oma karsittu interface Map-rakenteesta
 *
 */
public interface OmaMap<K, V> {

    /**
     * Metodi joka tyhjentää hakurakenteen
     */
    public void clear();
    /**
     * Kertoo onko hakurakenteessa kyseistä avainta
     * @param key Avain josta halutaan tietää onko se hakurakenteessa
     * @return Onko avain hakurakenteessa
     */
    public boolean containsKey(Object key);
    /*
     * Palauttaa avaimeen sidotun arvon tai null jos avainta ei ole
     * @param key Avain jota vastaava arvo halutaan
     * @return Haluttu arvo tai null jos avainta ei ole hakurakenteessa
     */
    
    /**
     * Palauttaa taulussa olevien avain-arvo-parien määrän
     * @return Avain-arvo-parien määrä
     */
    public int size();
    
    public V get(Object key);
    /**
     * Kertoo onko hakurakenne tyhjä
     * @return Onko hakurakenne tyhjä
     */
    public boolean isEmpty();
    /**
     * Asettaa kyseistä avainta vastaavan arvon hakurakenteeseen. Mahdollinen vanha arvo tuhoutuu
     * @param key Avain johon arvo sidotaan
     * @param value Avaimeen sidottava arvo
     * @return Vanha avaimeen sidottu arvo tai null jos avain on uusi
     */
    public V put(K key, V value);
    
    /**
     * Palauttaa listan kaikista taulun avaimista
     * @return Lista taulun avaimista
     */
    public OmaList<K> avaimet();
    
}
