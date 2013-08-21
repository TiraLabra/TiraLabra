
package com.mycompany.tiralabra_maven.automaatit;

import com.mycompany.tiralabra_maven.tietorakenteet.Jono;

/**
 * Tämä luokka mallintaa epädeterminististä äärellistä automaattia. Automaatti
 * osaa tarkastaa kuuluuko syötteenä annettu merkkijono konstruktorissa
 * määriteltyyn säännölliseen kieleen.
 *
 * @author John Lång <jllang@cs.helsinki.fi>
 */
public class Automaatti {
    
    private final Tila ALKUTILA;
    private final Jono<String> KIELI;
    
    /**
     * Palauttaa luokan uuden instanssin. 
     * 
     * @param KIELI 
     */
    public Automaatti(final Jono<String> KIELI) {
        this.KIELI = KIELI;
        if (KIELI == null) {
            throw new IllegalArgumentException("Syötteenä saatu säännöllinen "
                    + "lauseke oli tyhjä!");
        } else {
            ALKUTILA = new Tila(false);
            
        }
    }
    
    public boolean kieliSisaltaa(final String MERKKIJONO) {
        if (MERKKIJONO == null || MERKKIJONO.trim().length() == 0) {
            return false;
        }
        
        boolean paluuarvo = false;
        
        
        
        return paluuarvo;
    }
    
}
