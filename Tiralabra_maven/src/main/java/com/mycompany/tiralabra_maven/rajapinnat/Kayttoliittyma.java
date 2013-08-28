
package com.mycompany.tiralabra_maven.rajapinnat;

/**
 * Tämän rajapinnan toteuttava luokka vastaa käyttäjän kanssa kommunikoinnista.
 *
 * @author John Lång <jllang@cs.helsinki.fi>
 */
public interface Kayttoliittyma {
    
    /**
     * Näyttää käyttäjälle parametrina annetun viestin.
     *
     * @param viesti Tulostettava viesti.
     */
    void tulosta(String viesti);
    
    /**
     * Palauttaa käyttäjän antaman syötteen. Käyttäjälle näytetään parametrina
     * annettu viesti ennen syötteen lukemista.
     *
     * @param viesti Tulostettava viesti.
     * @return Käyttäjältä saatu syöte.
     */
    String pyydaSyote(String viesti);
    
}
