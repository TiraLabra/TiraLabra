package com.mycompany.Tiralabra_maven.logiikka.paikkaPino;

import com.mycompany.Tiralabra_maven.logiikka.Paikka;

/**
 * Rajapinta pinolle.
 * Pinon alkiot tyyppiä Paikka.
 * Tätä rajapintaa käyttämällä koodia ei tarvitse muuttaa kun siirrytään
 * Javan Stack:in käyttämisestä pinon oman toteutuksen käyttämiseen.
 */
public interface PinoAlkionaPaikka {
    
    /**
     * Pinoon lisätään pinoalkio.
     *
     * @param pinoAlkio lisättävä pinoalkio
     */
    public void stackPush(Paikka pinoAlkio);
    
    /**
     * Pinosta poistetaan päällimmäisenä oleva pinoalkio.
     *
     * @return pinosta poistettu päällimmäisenä ollut pinoalkio
     */
    public Paikka stackPop();
    
    /**
     * Tarkastaa onko pino tyhjä.
     *
     * @return palautetaan true, jos pino on tyhjä
     */
    public boolean stackIsEmpty();
    
}
