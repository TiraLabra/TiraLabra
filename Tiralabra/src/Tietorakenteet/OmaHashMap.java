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
    // alkulukuja

    final double MAKSIMI_KUORMA_KERROIN = 0.7;
    private static final int[] ALKULUKUJA = {3, 7, 13, 23, 41, 83, 163, 317, 751, 1511, 3041, 6089, 122143, 24691, 48023,
        81973, 104729, 204427, 421303, 854159, 1684489, 3149561, 6157103};
    int koko;
    private int nykyinenKokoPaikka = 0;
    private Object[] arvot;

    /**
     * Konstruktori
     */
    public OmaHashMap() {
        koko = 0;
        arvot = new Object[ALKULUKUJA[nykyinenKokoPaikka]];
        alustaTaulukko(arvot);

    }

    /**
     * Alustaa taulukon
     *
     * @param taulukko Alustettava taulukko
     */
    private void alustaTaulukko(Object[] taulukko) {
        for (int i = 0; i < taulukko.length; ++i) {
            OmaList<Pari<K, V>> lista = new OmaArrayList<Pari<K, V>>();
            taulukko[i] = lista;
        }
    }

    /**
     * Kasvattaa taulukon kokoa
     */
    private void kasvata() {
        ++nykyinenKokoPaikka;
        Object[] uusiTaulukko;
        

        if (nykyinenKokoPaikka < ALKULUKUJA.length) {
            uusiTaulukko = new Object[ALKULUKUJA[nykyinenKokoPaikka]];
        } else {
            uusiTaulukko = new Object[arvot.length * 2];
        }

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
                for (int j = 0; j < lista.size(); ++j) {
                    asetaTaulukkoon(lista.get(j).ensimmainen, lista.get(j).toinen, uusiTaulukko);
                }
            }
        }
    }

    @Override
    public void clear() {
        nykyinenKokoPaikka = 0;
        koko = 0;
        arvot = new Object[ALKULUKUJA[nykyinenKokoPaikka]];
        alustaTaulukko(arvot);
    }

    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    private int taulukonIndeksi(Object key, Object[] obj) {
        return Math.abs(key.hashCode() % obj.length);
    }

    @Override
    public V get(Object key) {

        OmaList<Pari<K, V>> lista = (OmaList<Pari<K, V>>) arvot[taulukonIndeksi(key, arvot)];

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
    public V put(K key, V value) {
        V vanha = get(key);

        if (asetaTaulukkoon(key, value, arvot)) {
            ++koko;
        }

        if ((double) koko / arvot.length > MAKSIMI_KUORMA_KERROIN) {
            kasvata();
        }

        return vanha;
    }

    private boolean asetaTaulukkoon(K key, V value, Object[] taulukko) {
        boolean kokoMuuttunut = true;
        OmaList<Pari<K, V>> lista = (OmaList<Pari<K, V>>) taulukko[taulukonIndeksi(key, taulukko)];

        // jos on jo taulukossa, poistetaan
        for (int i = 0; i < lista.size(); ++i) {
            if (lista.get(i).ensimmainen.equals(key)) {
                lista.remove(i);
                kokoMuuttunut = false;
                break;
            }
        }

        Pari<K, V> pari = new Pari<K, V>();
        pari.ensimmainen = key;
        pari.toinen = value;
        lista.add(pari);
        return kokoMuuttunut;
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
}
