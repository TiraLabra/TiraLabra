package main;

import java.util.EmptyStackException;

/**
 * Pinotietorakenne.
 *
 * @author Juri Kuronen
 */
public class MyStack {

    /**
     * Pinoalkio.
     */
    private class StackItem {

        /**
         * Pinoalkion arvo.
         */
        int value;
        /**
         * Tämän pinoalkion alla oleva pinoalkio.
         */
        StackItem next;

        /**
         * Luo uuden pinoalkion, jolle asetetaan annettu arvo.
         *
         * @param v Pinoalkion arvo.
         */
        StackItem(int v) {
            value = v;
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
     * Luo uuden pinoalkion, jonka arvo annettu, ja asettaa sen pinon
     * päällimmäiseksi.
     *
     * @param value Pinoalkion arvo.
     */
    public void push(int value) {
        StackItem si = new StackItem(value);
        if (top == null) {
            top = si;
        } else {
            si.next = top;
            top = si;
        }
        size++;
    }

    /**
     * Palauttaa pinon päällimmäisen alkion.
     *
     * @return Palauttaa pinon päällimmäisen alkion.
     */
    public int pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        int value = top.value;
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
