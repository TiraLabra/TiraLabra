/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * Erikoistunut prioriteettijono. Lisäys & poll pitäisi olla lähellä O(1):tä.
 * Tilavaatimus saattaa olla huomattava. Toteutus mukaelma tästä:
 * http://en.wikipedia.org/wiki/Priority_queue#Specialized_heaps
 *
 * Lisävaatimus: lisäykset ovat prioriteetiltaan suurempia kuin ensimmäinen
 * Perii PriorityQueue:n jotta testaaminen on helpompaa
 *
 * @author E
 */
public class PrioriteettiJonoListalla<E> extends PriorityQueue<E> {

    /**
     * Taulukon maksimikoko 2^31-1
     */
    public static final int ARRAY_MAXSIZE = 2147483639;
    /**
     * Aloituskerroin tarkkuudelle: saadaan taulukon koko
     */
    public static final int DEFAULT_SIZE = 45;
    /**
     * Comparator päättää oikean paikan jonossa: verrataan ensimmainen-olioon
     */
    private Comparator<E> comparator;
    /**
     * Ensimmäinen lisättävä tallennetaan tähän. Kaikkien muiden prioriteettien
     * pitää olla suurempia kuin tämän!
     */
    private E ensimmainen;
    /**
     * Toteutustapana taulukko, jossa prioriteetilla p on vastaava jono
     */
    private Jono<E>[] jono;
    /**
     * Parhaan prioriteetin osoitin
     */
    private int head;
    /**
     * Jäsenten lkm
     */
    private int size;
    /**
     * Taulukon koko
     */
    public int maxSize;
    /**
     * Kasvatuskerroin taulukolle
     */
    private int growFactor;

    ///////////////////
    // Konstruktorit //
    ///////////////////
    /**
     * Oletuskonstruktori
     */
    public PrioriteettiJonoListalla() {
        this(DEFAULT_SIZE);
    }

    /**
     * Konstruktorissa mukana vertailija
     *
     * @param comparator
     */
    public PrioriteettiJonoListalla(Comparator<E> comparator) {
        this();
        this.setComparator(comparator);
    }

    /**
     * Konstruktorissa mukana aloituskoko
     *
     * @param aloitusKoko
     */
    public PrioriteettiJonoListalla(int aloitusKoko) {
        head = 0;
        size = 0;
        growFactor = 2;
        maxSize = aloitusKoko; // vastaa N minuuttia yli ensimmäisen heuristisen arvion, jos lasketaan kustannuksia minuuteissa
        jono = new Jono[maxSize];
    }

    /**
     * Konstruktorissa mukana vertailija & aloituskoko
     *
     * @param comparator
     * @param aloitusKoko
     */
    public PrioriteettiJonoListalla(int aloitusKoko, Comparator<E> comparator) {
        this(aloitusKoko);
        this.setComparator(comparator);
    }

    //////////////////////
    // JULKISET METODIT //
    //////////////////////    
    /**
     * Asetetaan vertailija jonka perusteella oikea paikka etsitään
     *
     * @param comparator
     */
    public void setComparator(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    /**
     * Lisätään oikealle paikalle arvo. Ideana on, että prioriteetit kasvavat
     * ensimmäisestä lisäyksestä alkaen
     *
     * @param e Lisättävä arvo
     * @return True
     */
    @Override
    public boolean add(E e) {
        if (this.ensimmainen == null) {
            this.setEnsimmainen(e);
        }
        int prioriteetti;
        if (this.comparator == null) {
            prioriteetti = 0;
        } else {
            prioriteetti = comparator.compare(e, this.ensimmainen);
        }
        if (prioriteetti < 0) {
            // nyt ei toiminut!
            // ei tehdä tässä mitään, antaa kaatua
            System.out.println("HUPS");
            // WIP voidaan kopioida arvot uuteen taulukkoon & asettaa tämä ensimmäiseksi
        } else if (prioriteetti < head) {
            // näin ei kuuluisi käydä reittioppaan yhteydessä: reittien kokonaiskustannukset kasvavia
            // tästä ei kyllä ole niin haittaakaan
            System.out.println("OHHOH");
            head = prioriteetti;
        }
        if (prioriteetti >= ARRAY_MAXSIZE) {
            System.out.println("HUPS! Kurja prioriteetti");
            prioriteetti = ARRAY_MAXSIZE - 1; // sinne vaan!
        }
        if (prioriteetti >= maxSize - 1) { // O(maxSize) <- kopioidaan arvot uuteen
            kasvata(prioriteetti);
        }
        if (this.jono[prioriteetti] == null) {
            this.jono[prioriteetti] = new Jono<E>();
        }
        this.jono[prioriteetti].add(e); // oikeaan listaan O(1)
        size++;
        return true;
    }

    /**
     * Palauttaa muttei poista jonon ensimmäisen jäsenen.
     *
     * @return
     */
    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        if (head < 0 || head >= maxSize || this.isEmpty()) {
            return null;
        }
        if (this.jono[head] == null || this.jono[head].isEmpty()) {
            head = seuraava(); // max O(N), paljon tihemmässä pitäisi olla kuitenkin
        }
        E e = this.jono[head].peek(); // O(1)
        return e;
    }

    /**
     * Poistetaan ja palautetaan korkeimman prioriteetin arvo
     *
     * @return
     */
    @Override
    public E poll() {
        if (head < 0 || head >= maxSize || this.isEmpty()) {
            return null;
        }
        if (this.jono[head] == null || this.jono[head].isEmpty()) {
            head = seuraava(); // max O(N), paljon tihemmässä pitäisi olla kuitenkin
        }
        E e = this.jono[head].poll(); // O(1)
        size--;
        return e;
    }

    /**
     * Tarkistaa, onko jonossa vielä jäseniä
     *
     * @return true, jos tyhjä
     */
    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    /**
     * Jonon koko
     *
     * @return Jonon koko
     */
    @Override
    public int size() {
        return size;
    }

    ////////////////////////
    // YKSITYISET METODIT //
    ////////////////////////
    /**
     * Kasvattaa jonon kokoa jotta uusi arvo mahtuu siihen
     *
     * @param lisattavaPrioriteetti
     */
    private void kasvata(int lisattavaPrioriteetti) {
        int factor = this.growFactor;
        int oldSize = this.maxSize;
        while (maxSize * factor < lisattavaPrioriteetti) {
            factor *= 2;
        }
        if (factor * this.maxSize >= ARRAY_MAXSIZE) {
            // HUPS!
            System.out.println("HUHHUH, ei pyde");
            this.maxSize = ARRAY_MAXSIZE;
        } else {
            this.maxSize = factor * this.maxSize;
        }
        Jono<E>[] uusiValues = new Jono[this.maxSize];
        System.arraycopy(this.jono, 0, uusiValues, 0, oldSize);
        this.jono = uusiValues;
    }

    /**
     * Seuraava arvon (epätyhjä lista) sisältävän taulukon indeksi
     *
     * @return
     */
    private int seuraava() {
        int index = this.head;
        for (int i = index; i < maxSize; i++) {
            Jono<E> lista = this.jono[i];
            if (lista != null && !lista.isEmpty()) {
                return i;
            }
        }

        return -1; // ei ollut seuraavaa
    }

    /**
     * Asetetaan ensimmäinen (pienimmän prioriteetin arvo).
     *
     * @param e Pienimmän prioriteetin arvo
     */
    private void setEnsimmainen(E e) {
        this.ensimmainen = e;
    }
}
