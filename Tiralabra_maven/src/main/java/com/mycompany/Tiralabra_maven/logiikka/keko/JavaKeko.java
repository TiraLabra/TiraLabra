package com.mycompany.Tiralabra_maven.logiikka.keko;

import java.util.PriorityQueue;

/**
 *
 * @author Hannu
 */
public class JavaKeko<E> implements MinimiKeko<E>{
    
//    Prio
    
    private PriorityQueue<E> q;
    
    public JavaKeko(){
        this.q=new PriorityQueue<E>();
    }

    @Override
    public void heapInsert(E kekoAlkio) {
        this.q.add(kekoAlkio);
    }

    @Override
    public E heapDelMin() {
        return this.q.poll();
    }

    @Override
    public void heapDelete(E kekoAlkio) {
        this.q.remove(kekoAlkio);
    }

    @Override
    public void heapDecreaseKey(E kekoAlkio) {
        this.q.remove(kekoAlkio);
        this.q.add(kekoAlkio);
    }

    @Override
    public boolean heapIsEmpty() {
        return this.q.isEmpty();
    }
    
}
