package Tietorakenteet;

/**
 * Oma Hashmap-implementaatio. arvot[hashCode] sisältää ylivuotolistan johonka
 * tallennetaan kaikki objektit jotka saavat saman hash coden.
 *
 * Metodien javadocit rajapinnan yhteydessä.
 *
 * @param <K> Avain
 * @param <V> Arvo
 *
 *
 */
public class OmaHashMap<K, V> implements OmaMap<K, V> {

    private final static double MAKSIMI_KUORMA_KERROIN = 0.75;
    private final static int OLETUS_KOKO = 32;
    private int koko;
    private double kasvatusPiste;
    private HashMapAlkio[] arvot;

    /**
     * Konstruktori. Asettaa kapasiteetin oletuskooksi
     */
    public OmaHashMap() {
        this(OLETUS_KOKO);
    }

    /**
     * Asettaa kapasiteetin taulukonKoko-parametrin määrittämäksi. Parametrin on
     * oltava kahden potenssi
     *
     * @param taulukonKoko Kuinka monta alkiota sisäinen taulukko varaa aluksi
     * @throws IllegalArgumentException jos taulukonKoko ei ole kahden potenssi
     */
    public OmaHashMap(int taulukonKoko) throws IllegalArgumentException {

        double potenssi = Math.log(taulukonKoko) / Math.log(2);
        potenssi = potenssi - (int) potenssi;
        if (potenssi > 0.000000001) {
            throw new IllegalArgumentException("Annetun koon on oltava kahden potenssi");
        }


        koko = 0;
        arvot = new HashMapAlkio[taulukonKoko];

        laskeKasvatusPiste();
    }

    private void laskeKasvatusPiste() {
        kasvatusPiste = (double) arvot.length * MAKSIMI_KUORMA_KERROIN;
    }

    @Override
    public void clear() {

        koko = 0;
        arvot = new HashMapAlkio[OLETUS_KOKO];

    }

    private int taulukonIndeksi(Object key, Object[] obj) {
        return key.hashCode() & (obj.length - 1);
    }

    @Override
    public V get(Object key) {
        HashMapAlkio<K, V> alkio = arvot[taulukonIndeksi(key, arvot)];

        for (; alkio != null; alkio = alkio.seuraava) {
            if (alkio.avain.equals(key)) {
                return alkio.arvo;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return koko;
    }

    @Override
    public boolean isEmpty() {
        return koko == 0;
    }

    @Override
    public void put(K key, V value) {
        if (asetaTaulukkoon(key, value, arvot)) {
            ++koko;
        }

        if (koko > kasvatusPiste) {
            kasvata();
        }
    }
    /**
     * Asettaa avain-arvo-parin tauluun
     * @param key Avain
     * @param value arvo
     * @param taulukko taulukko johonka asetetaan
     * @return Lisättiinkö tauluun kokonaan uusi alkio 
     */
    private boolean asetaTaulukkoon(K key, V value, HashMapAlkio[] taulukko) {

        int indeksi = taulukonIndeksi(key, taulukko);
        HashMapAlkio<K, V> alkio = taulukko[indeksi];

        if (korvaaJoJosOlemassa(alkio, key, value)) {
            return false;
        }

        lisaaListaan(key, value, alkio, taulukko, indeksi);
        return true;
    }
    
    @Override
    public OmaList<K> avaimet() {
        OmaList<K> avainLista = new OmaArrayList<K>();

        for (int i = 0; i < arvot.length; ++i) {
            for (HashMapAlkio<K, V> alkio = arvot[i]; alkio != null; alkio = alkio.seuraava) {
                avainLista.add(alkio.avain);
            }
        }
        return avainLista;
    }
    /**
     * Jos avain on jo listassa, korvataan sen arvo
     * 
     * @param alkio lista jota tarkastellaan
     * @param key avain
     * @param value arvo
     * @return Korvattiinko vanha arvo listasta
     */
    private boolean korvaaJoJosOlemassa(HashMapAlkio<K, V> alkio, K key, V value) {

        for (HashMapAlkio<K, V> paikka = alkio; paikka != null; paikka = paikka.seuraava) {
            if (paikka.avain.equals(key)) {
                paikka.arvo = value;
                return true;
            }
        }
        return false;
    }
    /**
     * Lisää listaan uuden avain-arvo-parin
     * @param key avain
     * @param value arvo
     * @param alkio vanha alkio
     * @param taulukko taulukko 
     * @param indeksi taulukon indeksi
     */
    private void lisaaListaan(K key, V value, HashMapAlkio<K, V> alkio, HashMapAlkio[] taulukko, int indeksi) {
        HashMapAlkio<K, V> uusi = new HashMapAlkio<K, V>();
        uusi.avain = key;
        uusi.arvo = value;
        uusi.seuraava = alkio;
        taulukko[indeksi] = uusi;

    }

    /**
     * Kasvattaa taulukon kokoa
     */
    private void kasvata() {

        HashMapAlkio[] uusiTaulukko = new HashMapAlkio[arvot.length * 2];
        rehash(uusiTaulukko);
        arvot = uusiTaulukko;
        laskeKasvatusPiste();
    }

    /**
     * Rehashaa kaikki avain-arvo-parit uuteen taulukkoon
     *
     * @param uusiTaulukko taulukko johonka rehashataan avain-arvo-parit
     */
    private void rehash(HashMapAlkio[] uusiTaulukko) {
        for (int i = 0; i < arvot.length; ++i) {
             reHashaaLista(arvot[i], uusiTaulukko);
        }
    }
    /**
     * Rehashaa linkitetyn listan alkiot uuteen taulukkoo
     * @param alkio Vanha listan alkio
     * @param uusiTaulukko uusi taulukko johonka siirretään
     */
    private void reHashaaLista(HashMapAlkio<K, V> alkio, HashMapAlkio[] uusiTaulukko) {
        for (; alkio != null; alkio = alkio.seuraava) {
            asetaTaulukkoon(alkio.avain, alkio.arvo, uusiTaulukko);
        }
    }
}
