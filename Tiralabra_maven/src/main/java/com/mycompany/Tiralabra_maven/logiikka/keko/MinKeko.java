package com.mycompany.Tiralabra_maven.logiikka.keko;

import com.mycompany.Tiralabra_maven.logiikka.Paikka;

/**
 * Rajapinta minimikeolle.
 * Keon alkiot tyyppiä E.
 * Tätä rajapintaa käyttämällä koodia ei tarvitse muuttaa kun siirrytään
 * Javan PriorityQueue:n käyttämisestä minimikeon oman toteutuksen käyttämiseen.
 */
public interface MinKeko<E> {
    
        /**
     * Kekoon lisätään kekoalkio.
     *
     * @param kekoAlkio lisättävä kekoalkio
     */
    public void heapInsert(E kekoAlkio);

    /**
     * Keosta poistetaan huipulla oleva pienimmän avaimen sisältävä kekoalkio.
     *
     * @return keosta poistettu pienimmän avaimen sisältänyt kekoalkio
     */
    public E heapDelMin();

    /**
     * Keosta poistetaan kekoalkio.
     *
     * @param kekoAlkio poistettava kekoalkio
     */
    public void heapDelete(E kekoAlkio);

    /**
     * Asettaa kekoalkion oikealle paikalle keossa, jos kekoalkion avain on muuttunut.
     *
     * @param kekoAlkio oikealle paikalle asetettava kekoalkio
     */
    public void heapDecreaseKey(E kekoAlkio);

    /**
     * Tarkastaa onko keko tyhjä.
     *
     * @return palautetaan true, jos keko on tyhjä
     */
    public boolean heapIsEmpty();

    
}
