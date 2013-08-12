package Tietorakenteet;

/**
 * Oma Hashmap-implementaatio. Talukko[hashCode] sisältää ylivuotolistan johonka
 * tallennetaan kaikki objektit jotka saavat saman hash coden Ylivuotolista
 * toteutettu arraylistillä - jos taulu toimii oikein, ylivuotolistat ovat
 * pieniä eikä muistia hukata paljoa ja arraylistillä on parempi käyttäytyminen
 * välimuistissa koska data on peräkkäin muistissa vs linkitetyn listan alkiot
 * jotka voivat olla mielivaltaisissa paikoissa
 *
 * @param <K> Avain
 * @param <V> Arvo
 */
public class OmaHashMap<K, V> implements OmaMap<K, V> {

    private final double MAKSIMI_KUORMA_KERROIN = 0.7;
    private int koko;
    private Object[] arvot;

    /**
     * Konstruktori. Asettaa kapasiteetin 32 alkille
     */
    public OmaHashMap() {
        this(32);
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
        arvot = new Object[taulukonKoko];
        alustaTaulukko(arvot);
    }


    /**
     * Alustaa taulukon
     *
     * @param taulukko Alustettava taulukko
     */
    private void alustaTaulukko(Object[] taulukko) {
        for (int i = 0; i < taulukko.length; ++i) {
            taulukko[i] = new OmaArrayList<Pari<K, V>>();
        }
    }

    /**
     * Kasvattaa taulukon kokoa
     */
    private void kasvata() {

        Object[] uusiTaulukko = new Object[arvot.length * 2];
        alustaTaulukko(uusiTaulukko);
        rehash(uusiTaulukko);
        arvot = uusiTaulukko;
    }

    /**
     * Rehashaa kaikki avain-arvo-parit uuteen taulukkoon
     *
     * @param uusiTaulukko taulukko johonka rehashataan avain-arvo-parit
     */
    private void rehash(Object[] uusiTaulukko) {
        for (int i = 0; i < arvot.length; ++i) {
            
            assert (arvot[i] != null);
            OmaList<Pari<K, V>> lista = (OmaList<Pari<K, V>>) arvot[i];
            if (!lista.isEmpty()) {
                reHashaaLista(lista, uusiTaulukko);
            }
        }
    }

    @Override
    public void clear() {

        koko = 0;
        arvot = new Object[32];
        alustaTaulukko(arvot);
    }

    private int taulukonIndeksi(Object key, Object[] obj) {
        return key.hashCode() & (obj.length - 1);       
     }

    @Override
    public V get(Object key) {
        OmaList<Pari<K, V>> lista = (OmaList<Pari<K, V>>) arvot[taulukonIndeksi(key, arvot)];
        
        assert (lista != null);
        
        for (int i = 0; i < lista.size(); ++i) {
            if (lista.get(i).ensimmainen.equals(key)) {
                return lista.get(i).toinen;
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

        if (koko > arvot.length * MAKSIMI_KUORMA_KERROIN) {
            kasvata();
        }
    }

    private boolean asetaTaulukkoon(K key, V value, Object[] taulukko) {

        OmaList<Pari<K, V>> lista = (OmaList<Pari<K, V>>) taulukko[taulukonIndeksi(key, taulukko)];
        
        if (korvaaJosJoTaulukossa(lista, key, value)) {
            return false;
        }
        
        lisaaTaulukkoon(key, value, lista);
        return true;
    }

    @Override
    public OmaList<K> avaimet() {
        OmaList<K> avainLista = new OmaArrayList<K>();

        for (int i = 0; i < arvot.length; ++i) {
            OmaList<Pari<K, V>> lista = (OmaList<Pari<K, V>>) arvot[i];

            for (int j = 0; j < lista.size(); ++j) {
                avainLista.add(lista.get(j).ensimmainen);
            }
        }
        return avainLista;
    }

    private boolean korvaaJosJoTaulukossa(OmaList<Pari<K, V>> lista, K key, V value) {
        // jos on jo taulukossa, korvataan
        for (int i = 0; i < lista.size(); ++i) {
            if (lista.get(i).ensimmainen.equals(key)) {
                lista.get(i).toinen = value;
                return true;
            }
        }
        return false;
    }

    private void lisaaTaulukkoon(K key, V value, OmaList<Pari<K, V>> lista) {
        Pari<K, V> pari = new Pari<K, V>();
        pari.ensimmainen = key;
        pari.toinen = value;
        lista.add(pari);
    }

    private void reHashaaLista(OmaList<Pari<K, V>> lista, Object[] uusiTaulukko) {
        for (int j = 0; j < lista.size(); ++j) {
            asetaTaulukkoon(lista.get(j).ensimmainen, lista.get(j).toinen, uusiTaulukko);
        }
    }
}
