/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar.tietorakenteet;

/**
 *
 * @author sasumaki
 */
public class Jono<E> {

    private Object[] taulu;
    private int head;
    private int tail;
    private int size;

    public Jono() {
        this.taulu = new Object[11];
        this.head = 0;
        this.tail = 0;
        this.size = 11;
    }

    public void enqueue(Object o) {
        if (full()) {
            kasvata();
        }

        taulu[tail] = o;
        tail++;
        if (tail == size) {
            tail = 0;
        }
    }

    private void kasvata() {
        Object[] uusiTaulu = new Object[size * 2];
        int uusiTail = 0;

        while (!isEmpty()) {
            uusiTaulu[uusiTail] = taulu[head];
            head = next(head);
            uusiTail++;
        }
        tail = uusiTail;
        taulu = uusiTaulu;
        size *= 2;

    }

    private int next(int i) {
        return (i + 1) % size;
    }

    public E dequeue() {

        E pois = (E) taulu[head];
        head = next(head);
        return (E) pois;
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public boolean full() {
        int tailnext = tail + 1;
        if (tailnext == size) {
            tailnext = 0;
        }
        return head == tailnext;
    }
}
