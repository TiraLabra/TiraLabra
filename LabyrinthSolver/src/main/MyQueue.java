package main;

/**
 * Jonotietorakenne.
 *
 * @author Juri Kuronen
 * @param <E> Alkion tietotyyppi.
 */
public class MyQueue<E> {

    /**
     * Jonon alkiot tallennetaan aputaulukkoon.
     */
    private Object[] items;
    /**
     * Jonon taulukon koko.
     */
    private int maxsize;
    /**
     * Jonon pään paikka.
     */
    private int tail;
    /**
     * Jonon ensimmäisen alkion paikka.
     */
    private int head;

    /**
     * Luo tyhjän jonon, jonka aputaulukon kooksi asetetaan oletuksena 16.
     */
    public MyQueue() {
        head = 0;
        tail = 0;
        maxsize = 16;
        items = new Object[maxsize];
    }

    /**
     * Luo tyhjän jonon, jonka aputaulukon koko on annettu.
     *
     * @param ms Listan aputaulukon koko.
     */
    public MyQueue(int ms) {
        head = 0;
        tail = 0;
        if (ms > 0) {
            maxsize = ms;
        } else {
            maxsize = 16;
        }
        items = new Object[maxsize];
    }

    /**
     * Palauttaa kohdan, missä jonon ensimmäinen alkio on.
     *
     * @return Palauttaa kohdan, missä jonon ensimmäinen alkio on.
     */
    public int getHead() {
        return head;
    }

    /**
     * Palauttaa kohdan, missä jonon päätyalkio on.
     *
     * @return Palauttaa kohdan, missä jonon päätyalkio on.
     */
    public int getTail() {
        return tail;
    }

    /**
     * Palauttaa alkion annetusta indeksistä.
     *
     * @param key Haettavan alkion indeksi.
     * @return Palauttaa alkion annetusta indeksistä.
     */
    public E get(int key) {
        return (E) items[key];
    }

    /**
     * Uusi alkio laitetaan jonon perälle. Tämän jälkeen päivitetään tarpeen
     * tullen jonon aputaulukon koko.
     *
     * @param item Alkio, joka lisätään listaan.
     */
    public void enqueue(Object item) {
        items[tail] = item;
        tail = increment(tail);
        updateQueueSize();
    }

    /**
     * Jos jonon koko saavutti aputaulukon koon, luodaan uusi kaksi kertaa
     * isompi aputaulukko ja kopioidaan vanhat alkiot siihen.
     */
    void updateQueueSize() {
        if (tail != head || (head == tail && items[head] == null)) {
            return;
        }
        Object[] newItems = new Object[maxsize * 2];
        int counter = 0;
        while (counter != maxsize) {
            newItems[counter++] = items[head];
            head = increment(head);
        }
        items = newItems;
        maxsize *= 2;
        head = 0;
        tail = counter;
    }

    /**
     * Jos jono ei ole tyhjä, palauttaa jonon päässä olevan alkion.
     *
     * @return Palauttaa listan päässä olevan alkion. Jos lista on tyhjä,
     * palauttaa null.
     */
    public E dequeue() {
        if (empty()) {
            return null;
        }
        E value = (E) items[head];
        head = increment(head);
        return value;
    }

    /**
     * Tarkastaa, onko lista tyhjä.
     *
     * @return Palauttaa true, jos lista on tyhjä.
     */
    public boolean empty() {
        return head == tail && items[head] == null;
    }

    /**
     * Siirtää head:in tai tail:in indeksiä eteenpäin yhdellä. Jos indeksi
     * saavutti jonon aputaulukon maksimikoon, siirtyy indeksi jonon aputaulukon
     * alkuun.
     *
     * @param i Annettu indeksi.
     * @return Palauttaa seuraavan kohdan indeksin.
     */
    private int increment(int i) {
        i++;
        if (i == maxsize) {
            i = 0;
        }
        return i;
    }

    /**
     * Palauttaa listan koon.
     *
     * @return Palauttaa listan koon.
     */
    public int size() {
        if (head <= tail) {
            return tail - head;
        }
        return maxsize + tail - head;
    }

}
