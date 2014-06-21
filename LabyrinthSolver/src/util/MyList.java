package util;

/**
 * Listatietorakenne.
 *
 * @author Juri Kuronen
 * @param <E> Alkion tietotyyppi.
 */
public class MyList<E> {

    /**
     * Listan alkiot tallennetaan aputaulukkoon.
     */
    private Object[] items;
    /**
     * Listan aputaulukon koko.
     */
    private int maxsize;
    /**
     * Listan koko.
     */
    private int size;

    /**
     * Luo tyhjän listan, jonka aputaulukon kooksi asetetaan oletuksena 16.
     */
    public MyList() {
        size = 0;
        maxsize = 16;
        items = new Object[16];
    }

    /**
     * Luo tyhjän listan, jonka aputaulukon koko on annettu.
     *
     * @param ms Listan aputaulukon koko.
     */
    public MyList(int ms) {
        size = 0;
        if (ms > 0) {
            maxsize = ms;
        } else {
            maxsize = 16;
        }
        items = new Object[ms];
    }

    /**
     * Lisää listaan uuden alkion. Tämän jälkeen päivitetään tarpeen tullen
     * listan aputaulukon koko.
     *
     * @param item Alkio, joka lisätään listaan.
     */
    public void add(Object item) {
        items[size] = item;
        size++;
        updateListSize();
    }

    /**
     * Jos listan koko saavutti aputaulukon koon, luodaan uusi kaksi kertaa
     * isompi aputaulukko ja kopioidaan vanhat alkiot siihen.
     */
    private void updateListSize() {
        if (size != maxsize) {
            return;
        }
        Object[] newItems = new Object[maxsize * 2];
        System.arraycopy(items, 0, newItems, 0, maxsize);
        items = newItems;
        maxsize *= 2;
    }

    /**
     * Poistaa listan viimeisimmän alkion.
     *
     * @return Palauttaa listan viimeisimmän alkion.
     */
    public E removeLast() {
        if (size == 0) {
            return null;
        }
        size--;
        return (E) items[size];
    }

    /**
     * Poistaa alkion annetusta indeksistä. (Listan päädyn alkio vaihdetaan
     * tähän kohtaan ja sen jälkeen listan kokoa vähennetään yhdellä.)
     *
     * @param key Poistettavan alkion indeksi.
     * @return Palauttaa poistettavan alkion.
     */
    public E removeByIndex(int key) {
        if (key >= size || key < 0) {
            return null;
        }
        swap(key, size - 1);
        return (E) removeLast();
    }

    /**
     * Etsii ja poistaa annetun alkion, jos se löytyy. (Listan päädyn alkio
     * vaihdetaan tähän kohtaan ja sen jälkeen listan kokoa vähennetään
     * yhdellä.)
     *
     * @param item Poistettava alkio.
     * @return Palauttaa poistettavan alkion.
     */
    public E removeByValue(Object item) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(item)) {
                return (E) removeByIndex(i);
            }
        }
        return null;
    }

    /**
     * Palauttaa alkion annetusta indeksistä.
     *
     * @param key Haettavan alkion indeksi.
     * @return Palauttaa alkion annetusta indeksistä.
     */
    public E get(int key) {
        if (key >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (E) items[key];
    }

    /**
     * Tarkastaa onko lista tyhjä, eli onko listan koko 0.
     *
     * @return Palauttaa true, jos lista on tyhjä.
     */
    public boolean empty() {
        return size == 0;
    }

    /**
     * Palauttaa listan koon.
     *
     * @return Palauttaa listan koon.
     */
    public int size() {
        return size;
    }

    /**
     * Yhdistää annetun listan alkiot tähän listaan.
     *
     * @param list Lista, joka yhdistetään tähän listaan.
     */
    public void join(MyList list) {
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.size; i++) {
            add(list.get(i));
        }
    }

    /**
     * Vaihtaa kahden indeksin alkioiden paikat.
     *
     * @param index1 Alkion 1 indeksi.
     * @param index2 Alkion 2 indeksi.
     */
    public void swap(int index1, int index2) {
        if (index1 < 0 || index2 < 0 || index1 >= size || index2 >= size) {
            return;
        }
        Object temp = items[index1];
        items[index1] = items[index2];
        items[index2] = temp;
    }

    /**
     * Kääntää listan alkioiden järjestyksen.
     */
    public void reverseList() {
        for (int i = 0; i < size / 2; i++) {
            swap(i, size - 1 - i);
        }
    }

}
