
package com.mycompany.tiralabra_maven.logiikka;

import com.mycompany.tiralabra_maven.automaatit.Automaatti;
import com.mycompany.tiralabra_maven.automaatit.Tila;
import com.mycompany.tiralabra_maven.tietorakenteet.Jono;

/**
 * Tämä luokka muodostaa annetusta RPN-muotoisesta säännöllisestä lausekkeesta
 * äärellisen automaatin joka pystyy vertaamaan merkkijonoja tähän lausekkeeseen.
 *
 * @author John Lång <jllang@cs.helsinki.fi>
 */
public final class RegexKasittelija {
    
    private static Automaatti AUTOMAATTI;
    
    public void asetaSaannollinenLauseke(final Jono<String> SYOTE) {
        Tila.nollaaTilalaskuri();
        AUTOMAATTI = new Automaatti(SYOTE);
        System.out.println(AUTOMAATTI);
    }

    public boolean tasmaa(final String MERKKIJONO) {
        return AUTOMAATTI.kieliSisaltaa(MERKKIJONO);
    }
    
}
