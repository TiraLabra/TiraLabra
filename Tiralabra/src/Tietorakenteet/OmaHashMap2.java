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
public class OmaHashMap2<K, V> implements OmaMap<K, V> {

    final double MAKSIMI_KUORMA_KERROIN = 0.7f;
    private int koko;
    // motivaatio: muistiluku satunnaisesta paikasta on kallista koska todennäköisesti ei ole välimuistissa
    // pitämällä avaimet & arvot eri arrayssa luvut ovat peräkkäisiä ja osuu todennäköisesti suorittimen välimuistiin
    // ylivuotoketju on lista jonka sisällä on array avain-arvo-pareista, array on eri muistipaikassa, parien avain-arvot ovat eri muistipaikoissa -> 2 lisämahdollisuutta 
    // osua ohi välimuistista
    // hashmapissa testien perusteella keskimääriin 1.3 objektia per indeksi -> yleensä vain yksi -> ei tarvitse koskea ylivuotoketjuun
    private Object[] avaimet;
    private Object[] arvot;
    private Object[] ylivuotoketjut;

    /**
     * Konstruktori
     */
    public OmaHashMap2() {
        koko = 0;

        avaimet = new Object[32];
        arvot = new Object[32];
        ylivuotoketjut = new Object[32];


    }

    /**
     * Debug-metodi. Tulostaa taulukon tilan
     */
    public void tulostaTaulukonTila() {
        System.out.println("Load factor: " + laskeKuormaKerroin());
        System.out.println("Taulukon koko: " + avaimet.length);

        double alkioita = 0;
        double indeksienMaara = 0;

        for (int i = 0; i < ylivuotoketjut.length; ++i) {

            if (ylivuotoketjut[i] == null) {
                continue;
            }
            ++indeksienMaara;

            OmaList<Pari<K, V>> lista = (OmaList<Pari<K, V>>) ylivuotoketjut[i];

            alkioita += lista.size();

        }

        System.out.println("Alkioita keskimäärin per ylivuotolista: " + alkioita / indeksienMaara);
        System.out.println("Koko: " + koko + " alkioita: " + alkioita);
        System.out.println();
    }

    /**
     * Kasvattaa taulukon kokoa
     */
    private void kasvata() {

        Object[] uusiAvaimet = new Object[avaimet.length * 2];
        Object[] uusiArvot = new Object[arvot.length * 2];
        Object[] uusiYlivuotoKetjut = new Object[ylivuotoketjut.length * 2];

        assert (uusiAvaimet.length == uusiArvot.length);
        assert (uusiArvot.length == uusiYlivuotoKetjut.length);

       /* System.out.println("Taulukon tila ennen rehashia: ");
        tulostaTaulukonTila();*/

        rehash(uusiAvaimet, uusiArvot, uusiYlivuotoKetjut);

        avaimet = uusiAvaimet;
        arvot = uusiArvot;
        ylivuotoketjut = uusiYlivuotoKetjut;

      /*  System.out.println("Taulukon tila rehashin jälkeen ");
        tulostaTaulukonTila();*/

    }

    /**
     * Rehashaa kaikki avain-arvo-parit uuteen taulukkoon
     *
     * @param uusiAvaimet taulukko johonka rehashataan avain-arvo-parit
     */
    private void rehash(Object[] uusiAvaimet, Object[] uusiArvot, Object[] uusiYlivuotoKetju) {
        for (int i = 0; i < avaimet.length; ++i) {

            if (avaimet[i] == null) {
                assert (ylivuotoketjut[i] == null);
                continue;
            }

            siirraObjekti((K) avaimet[i], (V) arvot[i], uusiAvaimet, uusiArvot, uusiYlivuotoKetju);

            if (ylivuotoketjut[i] != null) {
                OmaList<Pari<K, V>> lista = (OmaList<Pari<K, V>>) ylivuotoketjut[i];
                for (int j = 0; j < lista.size(); ++j) {
                    siirraObjekti(lista.get(j).ensimmainen, lista.get(j).toinen, uusiAvaimet, uusiArvot, uusiYlivuotoKetju);
                }
            }

        }
    }

    @Override
    public void clear() {

        koko = 0;

        avaimet = new Object[32];
        ylivuotoketjut = new Object[32];
    }


    private int taulukonIndeksi(Object key, Object[] obj) {
        return Math.abs(key.hashCode() % obj.length);
    }

    @Override
    public V get(Object key) {

        int indeksi = taulukonIndeksi(key, avaimet);
        if (avaimet[indeksi] == null) {
            return null;
        }

        if (avaimet[indeksi].equals(key)) {
            return (V) arvot[indeksi];
        }

        if (ylivuotoketjut[indeksi] == null) {
            return null;
        }


        OmaList<Pari<K, V>> lista = (OmaList<Pari<K, V>>) ylivuotoketjut[indeksi];

        for (int i = 0; i < lista.size(); ++i) {
            if (lista.get(i).ensimmainen.equals(key)) {
                return (V) lista.get(i).toinen;
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
     
        if (asetaArvo(key, value)) {
            ++koko;
        }



        if (laskeKuormaKerroin() >= MAKSIMI_KUORMA_KERROIN) {
            kasvata();
        }

    }

    private boolean asetaArvo(K key, V value) {
        int indeksi = taulukonIndeksi(key, avaimet);
        if (avaimet[indeksi] == null) {
            avaimet[indeksi] = key;
            arvot[indeksi] = value;
            return true;
        }

        if (avaimet[indeksi].equals(key)) {
            arvot[indeksi] = value;
            return false;
        }
        OmaList<Pari<K, V>> lista = (OmaList<Pari<K, V>>) ylivuotoketjut[indeksi];

        if (lista != null) {
            for (int i = 0; i < lista.size(); ++i) {
                if (lista.get(i).ensimmainen.equals(key)) {
                    lista.get(i).toinen = value;
                    return false;
                }
            }

            Pari<K, V> pari = new Pari<K, V>();
            pari.ensimmainen = key;
            pari.toinen = value;
            lista.add(pari);
            return true;
            
        } else {
            lista = new OmaArrayList<Pari<K, V>>();
            Pari<K, V> pari = new Pari<K, V>();
            pari.ensimmainen = key;
            pari.toinen = value;
            lista.add(pari);
            ylivuotoketjut[indeksi] = lista;
            return true;
            
        }
    }

    @Override
    public OmaList<K> avaimet() {
        OmaList<K> avainLista = new OmaArrayList<K>();

        for (int i = 0; i < avaimet.length; ++i) {
            if (avaimet[i] != null) {
                avainLista.add((K) avaimet[i]);
            }
        }

        for (int i = 0; i < ylivuotoketjut.length; ++i) {
            if (ylivuotoketjut[i] != null) {
                OmaList<Pari<K, V>> lista = (OmaList<Pari<K, V>>) ylivuotoketjut[i];
                for (int j = 0; j < lista.size(); ++j) {
                    avainLista.add(lista.get(j).ensimmainen);
                }
            }
        }

        return avainLista;

    }

    private double laskeKuormaKerroin() {
        return (double) koko / avaimet.length;
    }

    private void siirraObjekti(K avain, V arvo, Object[] uusiAvaimet, Object[] uusiArvot, Object[] uusiYlivuotoKetju) {
        int uusiIndeksi = taulukonIndeksi(avain, uusiAvaimet);

        if (uusiAvaimet[uusiIndeksi] == null) {
            uusiAvaimet[uusiIndeksi] = avain;
            uusiArvot[uusiIndeksi] = arvo;
        } else {
            Pari<K, V> pari = new Pari<K, V>();
            pari.ensimmainen = avain;
            pari.toinen = arvo;

            if (uusiYlivuotoKetju[uusiIndeksi] == null) {
                uusiYlivuotoKetju[uusiIndeksi] = new OmaArrayList<Pari<K, V>>();
            }

            OmaList<Pari<K, V>> lista = (OmaList<Pari<K, V>>) uusiYlivuotoKetju[uusiIndeksi];
            lista.add(pari);
        }
    }
}
