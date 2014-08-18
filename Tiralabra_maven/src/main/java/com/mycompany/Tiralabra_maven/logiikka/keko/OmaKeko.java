package com.mycompany.Tiralabra_maven.logiikka.keko;

import java.lang.reflect.Array;

/**
 * KESKENERÄINEN LUOKKA
 * Oman tietorakenteen (minimikeko) toteutus.
 * Keon alkiot tyyppiä E.
 */
public class OmaKeko<E> {
    
        private E[] a;

    public OmaKeko(Class<E> c, int s) {
        // Use Array native method to create array of a type only known at run time
        @SuppressWarnings("unchecked")
        final E[] b = (E[]) Array.newInstance(c, s);
        this.a = b;
    }

    E get(int i) {
        return a[i];
    }
    
    
}
