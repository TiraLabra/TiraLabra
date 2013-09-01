package kolmiopeli.logiikka.tiralabraAlgoritmit.omatTietorakenteet;


/**
 * Oma HashSet-tietorakenne.
 */
public class OmaHashSet<T> {

    private int size;
    private OmaLinkedList<T>[] joukko;
    private final int HASH_PRIME;
    private OmaLinkedList<T> lisatyt;

    /**
     * Konstruktori, joka luo Arrayn OmaLinkedListoja ylivuototauluiksi, alustaa
     * koon nollaksi ja luo viela listan johon kerataan lisatyt oliot muihin rakenteisiin
     * lisaamista varten
     */
    public OmaHashSet() {
        HASH_PRIME = 24593;
        joukko = new OmaLinkedList[HASH_PRIME];
        size = 0;
        lisatyt = new OmaLinkedList<T>();
    }
    
    /**
     * Tutkii, onko parametrina annettu alkio jo joukossa.
     * @param etsittava Alkio, jonka kuulumista joukkoon tutkitaan.
     * @return True, jos loytyy, muuten false;
     */
    public boolean contains(T etsittava) {
        int hashIndex = hashFunktio(etsittava);
        if (joukko[hashIndex] == null) {
            return false;
        }
        OmaLinkedList<T> ylivuotolista = joukko[hashIndex];
        Listasolmu listasolmu = null;
        for (int i = 0; i < ylivuotolista.size(); i++) {
            if (i == 0) {
                listasolmu = ylivuotolista.peekFirst();
            } else {
                listasolmu = listasolmu.getNext();
            }
            if (listasolmu.getKey().equals(etsittava)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Lisaa joukkoon parametrina annetun alkion.
     * @param lisattava Joukkoon lisattava alkio.
     * @return True, jos lisaaminen onnistui, muuten false.
     */
    public boolean add(T lisattava) {
        if (this.contains(lisattava)) {
            return false;
        }
        int hashIndex = hashFunktio(lisattava);
        if (joukko[hashIndex] == null) {
            joukko[hashIndex] = new OmaLinkedList<T>();
        }
        OmaLinkedList<T> ylivuotolista = joukko[hashIndex];
        ylivuotolista.addLast(lisattava);
        size++;
        lisatyt.addLast(lisattava);
        return true;
    }

    /**
     * Palauttaa listan joukon alkioista.
     * @return Lista alkioista.
     */
    public OmaLinkedList<T> getLisatyt() {
        return lisatyt;
    }
    
    /**
     * Palauttaa joukon koon.
     * @return Joukon koko.
     */
    public int size() {
        return size;
    }

    /**
     * Hajautusfunktio.
     * @param lisattava Olio, joka halutaan hajauttaa.
     * @return Hajautuskoodi.
     */
    private int hashFunktio(T lisattava) {
        return lisattava.hashCode() % this.HASH_PRIME;
    }
    
}
