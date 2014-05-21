package main;

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

    public E dequeue() {
        if (empty()) {
            return null;
        }
        E value = (E) items[head];
        head = increment(head);
        return value;
    }

    public boolean empty() {
        return head == tail;
    }

    int increment(int i) {
        i++;
        if (i == maxsize) {
            i = 0;
        }
        return i;
    }

    public int size() {
        if (head <= tail) {
            return tail - head;
        }
        return maxsize + tail - head;
    }

}
