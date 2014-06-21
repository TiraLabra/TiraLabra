package util;

import util.HeapElement;

/**
 * Kekotietorakenne.
 *
 * @author Juri Kuronen
 */
public class MyPriorityHeap {

    /**
     * Keon kekoalkiot tallennetaan aputaulukkoon.
     */
    private HeapElement[] items;
    /**
     * Keon aputaulukon koko.
     */
    private int maxsize;
    /**
     * Keon koko.
     */
    private int size;

    /**
     * Luo tyhjän keon, jonka aputaulukon kooksi asetetaan oletuksena 16.
     */
    public MyPriorityHeap() {
        size = 0;
        maxsize = 16;
        items = new HeapElement[16];
    }

    /**
     * Luo tyhjän keon, jonka aputaulukon koko on annettu.
     *
     * @param ms Keon aputaulukon koko.
     */
    public MyPriorityHeap(int ms) {
        size = 0;
        if (ms > 0) {
            maxsize = ms;
        } else {
            maxsize = 16;
        }
        items = new HeapElement[ms];
    }

    /**
     * Lisää kekoon uuden alkion. Alkio lisätään keon päähän, jonka jälkeen
     * uutta alkiota vaihdellaan alkuun päin niin kauan, kunnes uudelle alkiolle
     * löydetään paikka, mikä ei riko keko-ominaisuutta. Tämän jälkeen
     * päivitetään tarpeen tullen keon aputaulukon koko.
     *
     * @param coordinate Koordinaatti, joka asetetaan kekoon lisättävälle
     * alkiolle.
     * @param d Kuljettu matka.
     * @param h Kustannusarvio maaliin.
     * @see util.MyPriorityHeap#heapify(int)
     */
    public void insert(int coordinate, int d, int h) {
        int value = d + h;
        int index = size, parent = parent(index);
        while (index > 0 && items[parent].getValue() > value) {
            items[index] = items[parent];
            index = parent;
            parent = parent(index);
        }
        items[index] = new HeapElement(coordinate, d, h);
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
     * Poistaa pienimmän arvon omaavan kekoalkion (keon päästä) ja palauttaa
     * sen. Heapify()-apuoperaatiota käytetään kekoehdon ylläpitämiseksi.
     *
     * @return Palauttaa pienimmän arvon omaavan kekoalkion.
     * @see util.MyPriorityHeap#heapify(int)
     */
    public HeapElement removeMin() {
        if (empty()) {
            return null;
        }
        HeapElement min = items[0];
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
        HeapElement[] newItems = new HeapElement[maxsize * 2];
        System.arraycopy(items, 0, newItems, 0, maxsize);
        items = newItems;
        maxsize *= 2;
    }

    /**
     * Palauttaa alkion arvion annetusta indeksistä.
     *
     * @param key Haettavan alkion indeksi.
     * @return Palauttaa alkion annetusta indeksistä.
     */
    public int get(int key) {
        return items[key].getValue();
    }

    /**
     * Vaihtaa kahden indeksin alkioiden paikat.
     *
     * @param index1 Alkion 1 indeksi.
     * @param index2 Alkion 2 indeksi.
     */
    private void swap(int index1, int index2) {
        HeapElement temp = items[index1];
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
            if (items[left].getValue() <= items[right].getValue()) {
                smallest = left;
            }
            if (items[index].getValue() > items[smallest].getValue()) {
                swap(index, smallest);
                heapify(smallest);
            }
        } else if (left == size - 1 && items[index].getValue() > items[left].getValue()) {
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
