package util;

/**
 * Joukkoalkion olio Kruskalin algoritmia varten.
 */
public class SetElement {

    /**
     * Joukon ID.
     */
    private final int id;
    /**
     * Reitti joukon juureen.
     */
    private SetElement root;
    /**
     * Kuinka monta alkiota on yhdistettynä tähän joukkoon, kun tämä alkio on
     * juuri. Saadakseen oikean luvun, täytyy ensin mennä koko joukon juureen.
     */
    private int elementsJoinedByRoot;

    /**
     * Konstruktori asettaa joukon ID:ksi annetun arvon, itsensä omaksi
     * juurekseen, ja alkioiden lukumäärän yhdeksi.
     *
     * @param value Joukon ID.
     */
    public SetElement(int value) {
        id = value;
        root = this;
        elementsJoinedByRoot = 1;
    }

    /**
     * Hakee koko joukon juuren rekursiivisesti ja korjaa matkalla olleiden
     * alkioiden juuren koko joukon juureksi.
     *
     * @return Koko joukon juuri.
     */
    SetElement getRoot() {
        if (this.root == this) {
            return this;
        }
        SetElement rt = this.root.getRoot();
        this.root = rt;
        return rt;
    }

    /**
     * Hakee rekursiivisesti koko joukon juuren ja katsoo sen ID:n.
     *
     * @return Palauttaa koko joukon ID:n.
     */
    public int getId() {
        return getRoot().id;
    }

    /**
     * Hakee rekursiivisesti koko joukon juuren, jonka joukon koko on
     * ajantasalla.
     *
     * @return Palauttaa koko joukon koon.
     */
    public int getNumOfElements() {
        return getRoot().elementsJoinedByRoot;
    }

    /**
     * Hakee rekursiivisesti tämän joukon juuren, sekä annetun toisen joukon
     * alkion juuren. Päivittää juuren koon ja asettaa toisen joukon juureksi
     * tämän joukon juuren.
     *
     * @param se2 Annettu, toisen joukon alkio.
     */
    public void joinTwoSets(SetElement se2) {
        SetElement rt = getRoot();
        SetElement rt2 = se2.getRoot();
        rt.elementsJoinedByRoot += rt2.elementsJoinedByRoot;
        rt2.root = rt;
    }
}
