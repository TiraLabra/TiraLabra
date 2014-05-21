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
    Object[] items;
    /**
     * Jonon taulukon koko.
     */
    int maxsize;
    /**
     * Jonon pään paikka.
     */
    int tail;
    /**
     * Jonon ensimmäisen alkion paikka.
     */
    int head;

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
        maxsize = ms;
        items = new Object[maxsize];
    }

    /**
     * Uusi alkio laitetaan jonon perälle. Jos jonon koko saavutti aputaulukon
     * koon, luodaan uusi kaksi kertaa isompi aputaulukko ja kopioidaan vanhat
     * alkiot siihen.
     *
     * @param item Alkio, joka lisätään listaan.
     */
    public void enqueue(Object item) {
        items[tail] = item;
        tail = increment(tail);
        if (tail == head) {
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
    }

    /**
     * Pyrkii palauttamaan listan päässä olevan alkion.
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
        return head == tail;
    }

    /**
     * Lisää annettua indeksiä yhdellä. Jos indeksi saavutti jonon aputaulukon
     * maksimikoon, siirtyy indeksi jonon aputaulukon alkuun.
     *
     * @param key Annettu indeksi.
     * @return Palauttaa jonon seuraavan kohdan indeksin.
     */
    int increment(int key) {
        key++;
        if (key == maxsize) {
            key = 0;
        }
        return key;
    }

    /**
     * Palauttaa listan koon.
     * @return Palauttaa listan koon.
     */
    public int size() {
        if (head <= tail) {
            return tail - head;
        }
        return maxsize + tail - head;
    }

}
