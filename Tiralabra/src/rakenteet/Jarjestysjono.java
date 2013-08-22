package rakenteet;

/**
 *
 * @author maef Tietorakenteen tapainen, joka korvaa valmiissa versiossa
 * PriorityQueue-olion. Ei ehkä ole identtinen sen kanssa, mutta tarpeeksi
 * lähellä ohjelman tarkoitukseen. Korvaamisen yksinkertaistamiseksi, metodeilla
 * on samat nimet kuin PriorityQueue-olion metodeilla.
 */
public class Jarjestysjono<E> implements TiRa{

    private transient Object[] jono;
    private int koko;

    public Jarjestysjono() {
        this.jono = new Object[11];
        koko = 0;
    }

    @Override
    public void add(Object e) {
        if (koko == jono.length) {
            Object[] uusiJono = new Object[2 * koko];
            for (int i = 0; i < koko; i++) {
                uusiJono[i] = jono[i];
            }
            jono = uusiJono;
        }
        jono[koko] = e;
        koko++;
        jarjesta();
    }

    public E peek() {
        if (koko == 0) {
            return null;
        }
        return (E) jono[koko - 1];
    }

    public E poll() {
        if (koko == 0) {
            return null;
        }
        E palautettava = (E) jono[koko - 1];
        koko--;

        return palautettava;
    }

    public E get(int i) {
        return (E) jono[i];
    }

    @Override
    public void clear() {
        koko = 0;
    }

    @Override
    public int size() {
        return koko;
    }

    private void jarjesta() {
        E apu;
        for (int i = 0; i < koko; i++) {
            for (int j = i + 1; j < koko; j++) {
                if (((Comparable) jono[i]).compareTo((Comparable) jono[j]) < 0) {
                    apu = (E) jono[i];
                    jono[i] = jono[j];
                    jono[j] = apu;
                }
            }
        }
    }

    public boolean isEmpty() {
        if (koko == 0) {
            return true;
        }
        return false;
    }

    @Override
    /*
     * Järjestysalgoritmin testausta varten.
     */
    public String toString() {
        String taulu = "[";
        for (int i = 0; i < koko; i++) {
            if (i == koko - 1) {
                taulu += jono[i];
            } else {
                taulu += jono[i] + ", ";
            }
        }
        taulu += "]";

        return taulu;
    }

    
    @Override
    public boolean contains(Object e) {
        for (int i = 0; i < koko; i++) {
            if (jono[i].equals(e)) {
                return true;
            }
        }
        return false;
    }


}
