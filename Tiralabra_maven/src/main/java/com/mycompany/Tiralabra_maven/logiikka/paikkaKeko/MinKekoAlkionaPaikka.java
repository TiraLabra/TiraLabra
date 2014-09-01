package com.mycompany.Tiralabra_maven.logiikka.paikkaKeko;

import com.mycompany.Tiralabra_maven.logiikka.Paikka;

/**
 * Rajapinta minimikeolle. Keon alkiot tyyppiä Paikka. Tätä rajapintaa
 * käyttämällä koodia ei tarvitse muuttaa kun siirrytään Javan PriorityQueue:n
 * käyttämisestä minimikeon oman toteutuksen käyttämiseen.
 */
public class MinKekoAlkionaPaikka {

    /**
     * Kekoon lisätään kekoalkio.
     *
     * @param kekoAlkio lisättävä kekoalkio
     */
    public void heapInsert(Paikka kekoAlkio) {
    }

    /**
     * Keosta poistetaan huipulla oleva pienimmän avaimen sisältävä kekoalkio.
     *
     * @return keosta poistettu pienimmän avaimen sisältänyt kekoalkio
     */
    public Paikka heapDelMin(){
        return new Paikka();
    };

    /**
     * Asettaa kekoalkion oikealle paikalle keossa. Algoritmeissa tätä metodia
     * kutsutaan VAIN kun Paikka.etaisyysAlkuun on muuttunut eli myös kekoalkion
     * avain on muuttunut.
     *
     * @param kekoAlkio oikealle paikalle asetettava kekoalkio
     */
    public void heapDecreaseKey(Paikka kekoAlkio){
    }

    /**
     * Tarkastaa onko keko tyhjä.
     *
     * @return palautetaan true, jos keko on tyhjä
     */
    public boolean heapIsEmpty(){
        return true;
    }
}
