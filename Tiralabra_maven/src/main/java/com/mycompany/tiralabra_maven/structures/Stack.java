package com.mycompany.tiralabra_maven.structures;

/**
 * Tämä luokka edustaa yksinkertaista pinoa, jolla on toiminnot push, pop, size ja empty. Tähän voi tallentaa kaikentyyppisiä olioita.
 * @param <E> Luokka, jonka olioita aiotaan tallentaa pinoon 
 */

public class Stack<E> {

    private int size = 0;
    private StackNode head;

    public Stack() {
    }

    public Stack(StackNode head) {
        this.head = head;
        this.size++;
    }

    /**
     * Tämä lisää pinon kärkeen uuden solmun, annetun parametrin perusteella. Jos jono ei ole tyhjä, asetetaan edellinen kärjessä ollut solmu kärkisolmun seuraavaksi.
     * @param e Muuttuja, joka tallenetaan pinon päälle
     */
    public void push(E e) {
        StackNode uusi = new StackNode(e);
        if (this.size > 0) {
            uusi.setNext(this.head);
        }
        this.head = uusi;
        size++;
    }

/**
 * Tämä ottaa pinon kärjestä solmun, ja asettaa sen seuraavan solmun uudeksi kärkisolmuksi. Tietojen poistaminen jää Javan roskienkerääjän tehtäväksi.
 * @return Pinon ylimmän solmun sisältämä arvo
 */
    public E pop() {
        if (size == 0) {
            return null;
        }
        size--;
        StackNode popattava = this.head;
        this.head = popattava.getNext();

        return (E) popattava.getValue();
    }

    /**
     * Tämä palauttaa pinon koon
     * @return Pinon koko
     */
    public int size() {
        return this.size;
    }

    /**
     * Tämä palauttaa true, jos pino on tyhjä, ja muutoin false
     * @return Totuusarvo pinon tyhjyydestä
     */
    public boolean empty() {
        return this.size == 0;
    }

}
