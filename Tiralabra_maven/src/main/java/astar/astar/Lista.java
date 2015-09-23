/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar.astar;

/**
 *
 * @author sasumaki
 */
public class Lista<E> {

    private Object[] taulu;
    private int size;

    public Lista() {
        this.size = 0;
        this.taulu = new Object[10];
    }

    public void add(E kama) {
        taulu[size] = kama;
        size++;
    }

    public E get(int i) {

        return (E) taulu[i];
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
}
