/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tira;

/**
 *
 * @author eniirane
 */
public class Keko<V> {
    
    int pituus = 0;
    int koko = 0;
    
    Lista lista;
    
    public Keko(int pituus) {
        this.pituus = pituus;
        Lista lista = new Lista();
    }
    
    public void insert(V value) {
    }
    
    public void min() {
        //todo public V 
    }
    
    public void del_min() {
    
    }

    public void heapify(int i) {
        int l = getLeft(i);
        int r = getRight(i);
        int pienempi = Math.min( getIndex(l), getIndex(r) );
        if (r <= this.koko) {
            if (getIndex(i) > pienempi) {
                vaihda(getIndex(i), getIndex(pienempi));
                heapify(pienempi);
            }
        } else if (l == this.koko && getIndex(i) > getIndex(l)) {
                vaihda(getIndex(i), getIndex(l));
        }
    }

    public void vaihda(int i, int j) {
    
    }
    
    public int getIndex(int i) {
        return -1;
    }
    
    public int getParent(int i) {
        return (int)Math.floor(i/2);
    }
    
    public int getLeft(int i) {
        return i*2;
    }
    
    public int getRight(int i) {
        return (i*2) + 1;
    }
}
