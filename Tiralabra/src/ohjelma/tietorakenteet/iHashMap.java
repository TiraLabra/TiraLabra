/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohjelma.tietorakenteet;

import java.util.Arrays;

/**
 *
 * @author kkivikat
 */
public class iHashMap<K, V> {

    private int koko;
    private int DEFAULT_CAPACITY = 50;
    private HashMapAlkio<K, V>[] arvot = new HashMapAlkio[DEFAULT_CAPACITY];

    /**
    * Palauttaa mapin koon.
    **/
    public int koko() {
        return koko;
    }

    /**
    * Palauttaa avaimelle kuuluvan arvon.
    **/
    public V get(K avain) {
        for (int i = 0; i < koko; i++) {
            if (arvot[i] != null) {
                if (arvot[i].getAvain() == avain) {
                    return arvot[i].getArvo();
                }
            }
        }
        throw new RuntimeException("ei löydy");
        //return null;
    }

    /**
    * Lisää avaimen ja arvon mappiin.
    **/
    public void put(K avain, V arvo) {
        arvot[koko++] = new HashMapAlkio<K, V>(avain, arvo);
    }

    /**
    * Tarkistaa ettei koko ylity.
    **/
    public void tarkistaKapasiteetti() {
        if (koko == arvot.length) {
            int uusiKoko = arvot.length * 2;
            arvot = Arrays.copyOf(arvot, uusiKoko);
        }
    }

    /**
    * Poistaa mapista halutun tietueen.
    **/
    public void remove(K avain) {
        for (int i = 0; i < koko; i++) {
            if (arvot[i].getAvain().equals(avain)) {
                arvot[i] = null;
                koko--;
                tiivista(i);
            }
        }
    }

    /**
    * Tiivistetään mappia poistetun arvon kohdalta.
    **/
    public void tiivista(int indeksi) {
        for (int i = 0; i < koko; i++) {
            arvot[i] = arvot[i + 1];
        }
    }

    /**
    * Tulostaa kaikki mapissa olevat arvot.
    **/
    public void tulostaArvot() {
        for (int i = 0; i < koko; i++) {
            System.out.println(arvot[i].getArvo());
        }
    }
}