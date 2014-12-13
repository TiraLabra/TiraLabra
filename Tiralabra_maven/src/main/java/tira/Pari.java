package tira;

/**
 * Hajautustaulun alkiot ovat pareja. Törmäykset hoidetaan tällä luokalla:
 * Pari-oliot ovat samalla yhteen suuntaan linkitettyjä listoja. Lisäykset
 * lisätään listan loppuun.
 *
 * @param <K> Avain
 * @param <V> Arvo
 */
public class Pari<K, V> {

    /**
     * Avain
     */
    private K k;
    /**
     * Arvo
     */
    private V v;
    /**
     * Seuraava pari. Tähän laitetaan ketjuun törmäysten sattuessa
     */
    private Pari<K, V> next;

    /**
     * Uusi pari
     * 
     * @param k Avain
     * @param v Arvo
     */
    public Pari(K k, V v) {
        this.k = k;
        this.v = v;
    }

    /**
     * Poistetaan vastaava pari (avaimet samat)
     *
     * @param p poistetava arvo
     * @return listan loppuosa
     */
    public Pari<K, V> remove(K p) {
        if (this.getK().equals(p)) {
            return this.getNext();
        } else if (this.hasNext()) {
            this.setNext(this.getNext().remove(p));
        }
        return this;
    }

    /**
     * Korvaa avaimella löytyvän arvon. Jos samaa arvoa ei ollut, lisätään
     * listan perälle
     *
     * @param p Lisättävä pari
     * @return Korvattava arvo. Null jos samalla arvolla ei ollut
     */
    public V replace(Pari<K, V> p) {
        if (this.equals(p)) {
            V v = this.getV();
            this.setV(p.getV());
            return v;
        } else if (!this.hasNext()) {
            this.setNext(p);
            return null;
        }
        return this.getNext().replace(p);
    }

    /**
     * Lisää loppuun uuden parin
     *
     * @param p Lisättävä pari
     * @return
     */
    public void add(Pari<K, V> p) {
        Pari curr = this;
        while (curr.hasNext()) {
            curr = curr.getNext();
        }
        curr.setNext(p);
    }

    /**
     * Lisää alkuun uuden parin ja palauttaa viitteen uuteen alkusolmuun
     *
     * @param p Lisättävä arvo
     * @return Uusi alkusolmu
     */
    public Pari<K, V> addFirst(Pari<K, V> p) {
        p.setNext(this);
        return p;
    }

    /**
     * Palauttaa avainta vastaavan arvon
     *
     * @param p
     * @return Arvo tai null, jos avainta ei löydy
     */
    public V get(K p) {
        if (this.getK().equals(p)) {
            return this.getV();
        } else if (!this.hasNext()) {
            return null;
        }
        return this.getNext().get(p);
    }

    /**
     * Jos avainta vastaava arvo on tallennettu
     *
     * @param p Avain
     * @return
     */
    public boolean contains(K p) {
        if (this.getK().equals(p)) {
            return true;
        } else if (!this.hasNext()) {
            return false;
        }
        return this.getNext().contains(p);
    }

    /**
     * Palauttaa listan koon
     *
     * @return Koko
     */
    public int size() {
        if (!hasNext()) {
            return 1;
        } else {
            return 1 + this.next.size();
        }
    }

    /**
     * Onko parilla seuraajaa
     *
     * @return
     */
    public boolean hasNext() {
        return this.next != null;
    }
    ///////////////////////////
    // AUTOMAATTISET METODIT //
    ///////////////////////////

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

    public Pari<K, V> getNext() {
        return next;
    }

    public void setNext(Pari<K, V> next) {
        this.next = next;
    }

    /**
     * Hashcode lasketaan vain avaimesta
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.k != null ? this.k.hashCode() : 0);
        return hash;
    }

    /**
     * Yhtäsuuruutta vertaillaan avaimilla
     *
     * @param obj
     * @return
     */
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
        String s = "Pari{" + "k=" + k + ", v=" + v + '}';
        if (hasNext()) {
            s += "->" + getNext().toString();
        }
        return s;
    }

}
