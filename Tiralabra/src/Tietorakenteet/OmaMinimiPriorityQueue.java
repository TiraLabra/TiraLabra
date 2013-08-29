package Tietorakenteet;

import java.util.Comparator;

/**
 * Luokka joka toteuttaa minimiprioriteettijonon. Koska en tarvitse muuta, en
 * ala rakentamaan yleisempää jonoa joka toteuttaisi myös
 * maksimiprioriteettijonon - tosin comparatorin toiminnan ympäri kääntämällä
 * toimii myös maksimiprioriteettijonona mutta tällöin rikotaan compare-metodin
 * kontraktia. Lisäksi add-operaatio olettaa aina että keko toteuttaa kekoehdon
 *
 * Metodien javadocit rajapinnan yhteydessä
 *
 * @param <T> Varastoitavan objektin tyyppi
 */
public class OmaMinimiPriorityQueue<T> implements OmaQueue<T> {

    OmaArrayList<T> keko;
    Comparator<T> vertaaja;

    /**
     * Konstruktori. Ottaa parametrina Comparator<T> - rajapinnan toteuttavan
     * objektin joka vastaa objektien vertailusta
     *
     * @param vertaaja Objekti joka toteuttaa Comparator-rajapinnan ja joka
     * vastaa objektien vertailusta
     */
    public OmaMinimiPriorityQueue(Comparator<T> vertaaja) {
        keko = new OmaArrayList<T>();
        this.vertaaja = vertaaja;

    }

    @Override
    public int size() {
        return keko.size();
    }

    @Override
    public boolean isEmpty() {
        return keko.isEmpty();
    }

    @Override
    public boolean push(T e) {

        keko.add(e);
        int paikka = keko.size() - 1;

        while (paikka != 0) {
            int vanhemmanIndeksi = haeVanhemmanIndeksi(paikka);

            if (vertaaja.compare(keko.get(vanhemmanIndeksi), keko.get(paikka)) > 0) {
                vaihda(vanhemmanIndeksi, paikka);
                paikka = vanhemmanIndeksi;
            } else {
                break;
            }
        }



        return true;
    }

    @Override
    public T pop() {
        T paluu = keko.get(0);
        keko.set(0, keko.get(size() - 1));
        keko.remove(size() - 1);
        if (size() > 1) {
            heapify(0);
        }

        return paluu;
    }

    private int haeVasemmanLapsenIndeksi(int i) {
        return ((i + 1) << 1) - 1;
    }

    private T haeVasenLapsi(int i) {
        int indeksi = haeVasemmanLapsenIndeksi(i);
        if (indeksi >= size()) {
            return null;
        }

        return keko.get(indeksi);
    }

    private T haeOikeaLapsi(int i) {
        int indeksi = haeOikeanLapsenIndeksi(i);
        if (indeksi >= size()) {
            return null;
        }

        return keko.get(indeksi);
    }

    private int haeOikeanLapsenIndeksi(int i) {
        return (i + 1) << 1;
    }

    private int haeVanhemmanIndeksi(int i) {
        return (i - 1) >>> 1;
        
    }

    // suoraan luentomonisteesta napattu - varmistaa että keko toteuttaa kekoehdon
    private void heapify(int paikka) {
        T vasen = haeVasenLapsi(paikka);
        T oikea = haeOikeaLapsi(paikka);

        int pieninPaikka;
        T pienin;
        if (oikea != null) {
            assert (vasen != null);
            if (vertaaja.compare(vasen, oikea) < 0) {
                pieninPaikka = haeVasemmanLapsenIndeksi(paikka);
                pienin = vasen;
            } else {
                pieninPaikka = haeOikeanLapsenIndeksi(paikka);
                pienin = oikea;
            }

            if (vertaaja.compare(keko.get(paikka), pienin) > 0) {
                vaihda(paikka, pieninPaikka);
                heapify(pieninPaikka);
            }

        } else if (vasen != null && vertaaja.compare(keko.get(paikka), vasen) > 0) {
            assert (oikea == null);
            vaihda(paikka, haeVasemmanLapsenIndeksi(paikka));
        }

    }

    private void vaihda(int ensimmainen, int toinen) {
        T temp = keko.get(ensimmainen);
        keko.set(ensimmainen, keko.get(toinen));
        keko.set(toinen, temp);
    }
}
