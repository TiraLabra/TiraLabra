package tira;

/**
 * Hajautustaulun alkiot ovat pareja
 *
 * @param <K> Avain
 * @param <V> Arvo
 */
public class Pari<K, V> {

    private K k;
    private V v;

        // WIP: linkitetty lista -toteutus tähän?
    public Pari(K k, V v) {
        this.k = k;
        this.v = v;
    }

    public K getK() {
        return k;
    }

    public void setK(K k) {
        this.k = k;
    }

    public V getV() {
        return v;
    }

    public void setV(V v) {
        this.v = v;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.k != null ? this.k.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pari<?, ?> other = (Pari<?, ?>) obj;
        return !(this.k != other.k && (this.k == null || !this.k.equals(other.k)));
    }

    @Override
    public String toString() {
        return "Pari{" + "k=" + k + ", v=" + v + '}';
    }
    
}
