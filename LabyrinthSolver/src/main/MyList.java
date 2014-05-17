package main;

/**
 * Listatietorakenne.
 *
 * @author Juri Kuronen
 */
public class MyList {

    /**
     * Listan alkiot tallennetaan aputaulukkoon.
     */
    private int[] items;
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
        items = new int[16];
    }

    /**
     * Luo tyhjän listan, jonka aputaulukon koko on annettu.
     *
     * @param ms Listan aputaulukon koko.
     */
    public MyList(int ms) {
        size = 0;
        maxsize = ms;
        items = new int[ms];
    }

    /**
     * Lisää listaan uuden alkion. Jos listan koko saavutti aputaulukon koon,
     * luodaan uusi kaksi kertaa isompi aputaulukko ja kopioidaan vanhat alkiot
     * siihen.
     *
     * @param value Alkion arvo, joka lisätään listaan.
     */
    public void add(int value) {
        items[size] = value;
        size++;
        if (size == maxsize) {
            int[] newItems = new int[maxsize * 2];
            System.arraycopy(items, 0, newItems, 0, maxsize);
            items = newItems;
            maxsize *= 2;
        }
    }

    /**
     * Poistaa listan viimeisimmän alkion.
     *
     */
    public void remove() {
        if (size == 0) {
            return;
        }
        size--;
    }

    /**
     * Vaihtaa annettuun indeksiin listan päädyn alkion ja vähentää listan kokoa
     * yhdellä. Tämä operaatio poisti listasta alkion annetusta indeksistä.
     *
     * @param key Poistettavan alkion indeksi.
     */
    public void remove(int key) {
        if (key >= size) {
            return;
        }
        items[key] = items[size - 1];
        size--;
    }

    /**
     * Palauttaa alkion arvon annetusta indeksistä.
     *
     * @param key Haettavan alkion indeksi.
     * @return Palauttaa alkion arvon annetusta indeksistä.
     */
    public int get(int key) {
        if (key >= size) {
            throw new IndexOutOfBoundsException();
        }
        return items[key];
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
        for (int i = 0; i < list.size; i++) {
            add(list.get(i));
        }
    }
}
