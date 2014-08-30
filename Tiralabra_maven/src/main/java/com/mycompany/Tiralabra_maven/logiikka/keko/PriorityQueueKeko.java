package com.mycompany.Tiralabra_maven.logiikka.keko;

import java.util.PriorityQueue;

/**
 * Minimikeko toteutettuna Javan valmiilla tietorakenteella PriorityQueue. Keon
 * alkiot tyyppiä E.
 */
public class PriorityQueueKeko<E> implements MinKeko<E> {

    private PriorityQueue<E> q;

    public PriorityQueueKeko() {
        this.q = new PriorityQueue<E>();
    }

    /**
     * Kekoon lisätään kekoalkio.
     *
     * @param kekoAlkio lisättävä kekoalkio
     */
    @Override
    public void heapInsert(E kekoAlkio) {
        this.q.add(kekoAlkio);
    }

    /**
     * Keosta poistetaan huipulla oleva pienimmän avaimen sisältävä kekoalkio.
     *
     * @return keosta poistettu pienimmän avaimen sisältänyt kekoalkio
     */
    @Override
    public E heapDelMin() {
        return this.q.poll();
    }

    /**
     * Asettaa kekoalkion oikealle paikalle keossa, jos kekoalkion avain on
     * muuttunut.
     *
     * @param kekoAlkio oikealle paikalle asetettava kekoalkio
     */
    @Override
    public void heapDecreaseKey(E kekoAlkio) {
        this.q.remove(kekoAlkio);
        this.q.add(kekoAlkio);
    }

    /**
     * Tarkastaa onko keko tyhjä.
     *
     * @return palautetaan true, jos keko on tyhjä
     */
    @Override
    public boolean heapIsEmpty() {
        return this.q.isEmpty();
    }
}
