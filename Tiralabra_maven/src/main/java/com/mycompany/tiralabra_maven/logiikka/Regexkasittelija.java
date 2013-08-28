
package com.mycompany.tiralabra_maven.logiikka;

import com.mycompany.tiralabra_maven.automaatit.Automaatti;
import com.mycompany.tiralabra_maven.automaatit.Tila;
import com.mycompany.tiralabra_maven.tietorakenteet.Jono;

/**
 * Tämä luokka muodostaa annetusta RPN-muotoisesta säännöllisestä lausekkeesta
 * äärellisen automaatin joka pystyy päättelemään kuuluuko jokin sille syötetty
 * merkkijono automaatin säännölliseen kieleen ts. täsmääkö säännöllinen lauseke
 * syötteen kanssa.
 *
 * @author John Lång <jllang@cs.helsinki.fi>
 * @see Automaatti
 */
public final class Regexkasittelija {
    
    private static Automaatti AUTOMAATTI;
    
    /**
     * Rakentaa uuden äärellisen automaatin syötteenä annetulle RPN-muotoiselle
     * säännölliselle kielelle.
     * 
     * @param SYOTE Luokan <b>Jono</b> olio, joka sisältää merkkijonoina
     * säännöllisen kielen operaattorit ja operandit.
     */
    public void asetaSaannollinenLauseke(final Jono<String> SYOTE) {
        Tila.nollaaTilalaskuri();
        AUTOMAATTI = new Automaatti(SYOTE);
        System.out.println(AUTOMAATTI);
    }

    /**
     * Tutkii kuuluuko syötteenä annettu merkkijono <b>Regexkasittelija</b>:n
     * äärellisen automaatin säännölliseen kieleen (eli täsmääkö syöte
     * säännöllisen lausekkeen kanssa).
     * 
     * @param MERKKIJONO
     * @return <i>true</i> jos ja vain jos merkkijono sisältyy
     * <b>Regexkasittelija</b>:n äärellisen automaatin kieleen; muutoin
     * <i>false</i>.
     */
    public boolean tasmaa(final String MERKKIJONO) {
        return AUTOMAATTI.kieliSisaltaa(MERKKIJONO);
    }
    
}
