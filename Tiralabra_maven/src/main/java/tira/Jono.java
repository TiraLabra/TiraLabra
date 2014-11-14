/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira;

/**
 * Jono
 *
 * @author E
 */
public class Jono<E> {

    /**
     * Oletusmaksimikoko
     */
    public static final int DEFAULTSIZE = 5;
    /**
     * Oletuskasvatuskerroin
     */
    public static final int DEFAULTGROWFACTOR = 2;

    private int maxSize;
    private int head;
    private int tail;
    private int size;
    private Object[] values;

    public Jono() {
        maxSize = 10;
        values = new Object[maxSize];
        head = 0;
        tail = 0;
    }
    /**
     * Lisää jonon viimeiseksi jäseneksi
     * 
     * @param e Lisättävä arvo
     */
    public void enqueue(E e) {
        if (size >= maxSize-1) {
            kasvata();
        }
        this.values[tail] = e;
        tail++;
        size++;
        if (tail >= maxSize) {
            tail = 0;
        }
        // System.out.println("ENQ "+this.toString());
    }
    /**
     * Sama kuin enqueue
     * 
     * @param e 
     */
    public void add( E e) {        
        this.enqueue(e);
    }
    /**
     * Poistaa ja palauttaa jonon 1. jäsenen
     * @return Ensimmäinen jonosta
     */
    public E dequeue() {
       
        E e = (E) this.values[head];
        head++;
        if (head >= maxSize) {
            head = 0;
        }
        size--;
        // System.out.println("DEQ "+this.toString());
        return e;
    }
    /**
     * Sama kuin dequeue
     * 
     * @return 
     */
    public E poll() {
        return this.dequeue();
    }
    /**
     * Tarkistaa, onko jonossa vielä jäseniä
     *
     * @return true, jos tyhjä
     */
    public boolean isEmpty() {
        return size <= 0;
    }

    /**
     * Jonon koko
     *
     * @return Jonon koko
     */
    public int size() {
        return size;
    }    

    //////////////////////
    //YKSITYISET METODIT//
    //////////////////////
    /**
     * Kasvattaa listan kokoa oletuskasvatuskertoimen verran
     */
    private void kasvata() {
        this.kasvata(DEFAULTGROWFACTOR);
    }

    /**
     * Kasvattaa listan kokoa kertoimen verran
     *
     * @param factor
     */
    private void kasvata(int factor) {
        this.maxSize = factor * this.maxSize;
        Object[] uusiValues = new Object[this.maxSize];
        System.arraycopy(this.values, 0, uusiValues, 0, this.size);
        this.values = uusiValues;
    }
    
    ////////////////////////////
    // Automaattiset metodit ///
    ////////////////////////////

    @Override
    public String toString() {
        return "Jono{" + "maxSize=" + maxSize + ", head=" + head + ", tail=" + tail + ", size=" + size + '}';
    }
    
    
}
