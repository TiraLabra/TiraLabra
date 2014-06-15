package main;

/**
 * Listatietorakenne.
 *
 * @author Juri Kuronen
 */
public class MyPriorityHeap {

    /**
     * Keon alkiot tallennetaan aputaulukkoon.
     */
    private int[] items;
    /**
     * Keon aputaulukon koko.
     */
    private int maxsize;
    /**
     * Keon koko.
     */
    private int size;

    public MyPriorityHeap() {
        size = 0;
        maxsize = 16;
        items = new int[16];
    }

    public MyPriorityHeap(int ms) {
        size = 0;
        maxsize = ms;
        items = new int[ms];
    }

    /**
     * Lisää kekoon uuden alkion. Alkio lisätään keon päähän, jonka jälkeen
     * uutta alkiota vaihdellaan ylöspäin niin kauan, kunnes uudelle alkiolle
     * löydetään paikka, mikä ei riko keko-ominaisuutta. Tämän jälkeen
     * päivitetään tarpeen tullen keon aputaulukon koko.
     *
     * @param item Alkio, joka lisätään kekoon.
     * @see heapify()
     */
    public void insert(int item) {
        int index = size, parent = parent(index);
        while (index > 0 && items[parent] > item) {
            items[index] = items[parent];
            index = parent;
            parent = parent(index);
        }
        items[index] = item;
        size++;
        updateHeapSize();
    }

    /**
     * Palauttaa annetun indeksin vanhemman indeksin.
     *
     * @param index Annettu indeksi.
     * @return Palauttaa vanhemman indeksin.
     */
    private int parent(int index) {
        return (index + 1) / 2 - 1;
    }

    /**
     * Poistaa pienimmän arvon (keon päästä) ja palauttaa sen.
     * Heapify()-apuoperaatiota käytetään kekoehdon ylläpitämiseksi.
     *
     * @return Palauttaa pienimmän arvon.
     * @throws java.lang.Exception Heittää poikkeuksen, jos keko on tyhjä.
     * @see heapify()
     */
    public int removeMin() throws Exception {
        if(empty()) {
            throw new Exception("Heap is empty!");
        }
        int min = items[0];
        items[0] = items[size - 1];
        size--;
        heapify(0);
        return min;
    }

    /**
     * Jos keon koko saavutti aputaulukon koon, luodaan uusi kaksi kertaa isompi
     * aputaulukko ja kopioidaan vanhat alkiot siihen.
     */
    private void updateHeapSize() {
        if (size != maxsize) {
            return;
        }
        int[] newItems = new int[maxsize * 2];
        System.arraycopy(items, 0, newItems, 0, maxsize);
        items = newItems;
        maxsize *= 2;
    }

    /**
     * Palauttaa alkion annetusta indeksistä.
     *
     * @param key Haettavan alkion indeksi.
     * @return Palauttaa alkion annetusta indeksistä.
     */
    public int get(int key) {
        return items[key];
    }

    /**
     * Vaihtaa kahden indeksin alkioiden paikat.
     *
     * @param index1 Alkion 1 indeksi.
     * @param index2 Alkion 2 indeksi.
     */
    private void swap(int index1, int index2) {
        int temp = items[index1];
        items[index1] = items[index2];
        items[index2] = temp;
    }

    /**
     * Operaatio korjaa kekoehdon, jos se on rikki kohdassa index.
     *
     * @param index Aputaulukon kohta.
     */
    private void heapify(int index) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        if (right < size) {
            int smallest = right;
            if (items[left] <= items[right]) {
                smallest = left;
            }
            if (items[index] >= items[smallest]) {
                swap(index, smallest);
                heapify(smallest);
            }
        } else if (left == size - 1 && items[index] >= items[left]) {
            swap(index, left);
        }
    }

    /**
     * Tarkastaa onko keko tyhjä, eli onko keon koko 0.
     *
     * @return Palauttaa true, jos keko on tyhjä.
     */
    public boolean empty() {
        return size == 0;
    }

    /**
     * Palauttaa keon koon.
     *
     * @return Palauttaa keon koon.
     */
    public int size() {
        return size;
    }
}
