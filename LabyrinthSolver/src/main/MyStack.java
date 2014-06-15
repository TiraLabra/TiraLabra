package main;

import java.util.EmptyStackException;

/**
 * Pinotietorakenne.
 *
 * @author Juri Kuronen
 * @param <E> Alkion tietotyyppi.
 */
public class MyStack<E> {

    /**
     * Pinoalkio.
     */
    private class StackItem {

        /**
         * Pinoalkion tieto.
         */
        Object data;
        /**
         * Tämän pinoalkion alla oleva pinoalkio.
         */
        StackItem next;

        /**
         * Luo uuden pinoalkion, jolle asetetaan annettu tieto.
         *
         * @param dt Pinoalkion tieto.
         */
        StackItem(Object dt) {
            data = dt;
        }
    }

    /**
     * Pinon päällimmäinen alkio.
     */
    private StackItem top;
    /**
     * Pinon koko.
     */
    private int size;

    /**
     * Luo uuden tyhjän pinon.
     */
    public MyStack() {
        size = 0;
    }

    /**
     * Luo uuden pinoalkion, jonka tieto annettu, ja asettaa sen pinon
     * päällimmäiseksi.
     *
     * @param data Pinoalkion tieto.
     */
    public void push(Object data) {
        StackItem si = new StackItem(data);
        if (top == null) {
            top = si;
        } else {
            si.next = top;
            top = si;
        }
        size++;
    }

    /**
     * Palauttaa pinon päällimmäisen alkion tiedon.
     *
     * @return Palauttaa pinon päällimmäisen alkion tiedon.
     */
    public E pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        E value = (E) top.data;
        top = top.next;
        size--;
        return value;
    }

    /**
     * Tarkastaa onko pino tyhjä, eli onko pinon koko 0.
     *
     * @return Palauttaa true, jos pino on tyhjä.
     */
    public boolean empty() {
        return size == 0;
    }

    /**
     * Palauttaa pinon koon.
     *
     * @return Palauttaa pinon koon.
     */
    public int size() {
        return size;
    }
}
