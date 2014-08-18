package com.mycompany.Tiralabra_maven.logiikka.keko;

import com.mycompany.Tiralabra_maven.logiikka.Paikka;
import java.util.PriorityQueue;

/**
 *
 * @author Hannu
 */
public class JK implements MK{
    
    private PriorityQueue<Paikka> q;
    
    public JK(){
        this.q=new PriorityQueue<Paikka>();
    }

    /**
     * Kekoon lisätään kekoalkio.
     *
     * @param kekoAlkio lisättävä kekoalkio
     */
    @Override
    public void heapInsert(Paikka kekoAlkio) {
        this.q.add(kekoAlkio);
    }

    /**
     * Keosta poistetaan huipulla oleva pienimmän avaimen sisältävä kekoalkio.
     *
     * @return keosta poistettu pienimmän avaimen sisältänyt kekoalkio
     */
    @Override
    public Paikka heapDelMin() {
        return this.q.poll();
    }

    /**
     * Keosta poistetaan kekoalkio.
     *
     * @param kekoAlkio poistettava kekoalkio
     */
    @Override
    public void heapDelete(Paikka kekoAlkio) {
        this.q.remove(kekoAlkio);
    }

    /**
     * Asettaa kekoalkion oikealle paikalle keossa, jos kekoalkion avain on muuttunut.
     *
     * @param kekoAlkio oikealle paikalle asetettava kekoalkio
     */
    @Override
    public void heapDecreaseKey(Paikka kekoAlkio) {
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
