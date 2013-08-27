
package com.mycompany.tiralabra_maven.automaatit;

import com.mycompany.tiralabra_maven.tietorakenteet.Jono;
import com.mycompany.tiralabra_maven.tietorakenteet.Tilasiirtymajono;
import com.mycompany.tiralabra_maven.tietorakenteet.Tilasiirtymakartta;

/**
 * 
 * 
 * http://en.wikipedia.org/wiki/Nondeterministic_finite_automaton_with_%CE%B5-moves
 *
 * @author John Lång <jllang@cs.helsinki.fi>
 */
public final class Tila {
    
    private static int tiloja = 0;
    
    final boolean ON_HYVAKSYVA;
    private boolean tulostettu = false;
//    private final Tilasiirtymakartta TILASIIRTYMAT;
    private final Tilasiirtymajono TILASIIRTYMAT;
    private final int ID;
    
    public Tila(final boolean ON_HYVAKSYVA) {
        this.ON_HYVAKSYVA   = ON_HYVAKSYVA;
        this.TILASIIRTYMAT  = new Tilasiirtymajono();
        this.ID = tiloja;
        Tila.tiloja++;
    }
    
    void lisaaTilasiirtyma(final char EHTO, final Tila TILA) {
        // Jos automaatti olisi deterministinen, tarkastettaisiin ettei
        // tyhjälle merkille '\0' (jonka tilalla käytän tulostuksessa hienomman
        // näköistä merkkiä '\u03b5') lisättäisi tilasiirtymää.
        TILASIIRTYMAT.lisaa(EHTO, TILA);
    }
    
    /**
     * 
     * @param   MERKKI Syötteen merkki jolle etsitään tilasiirtymää.
     * @return  <b>Jono</b> joka sisältää automaatin seuraavat mahdolliset 
     *          tilasiirtymät syötteenä annetulle merkille tai <i>null</i> jos
     *          sellaisia ei ole.
     */
    public Jono<Tila> tilasiirtymat(final Character MERKKI) {
        // Tila[] voisi ehkä olla nopeampi tietorakenne...
//        return TILASIIRTYMAT.haeKaikki(MERKKI);
        return TILASIIRTYMAT.haeKaikki(MERKKI);
    }
    
    @Override
    public String toString() {
        StringBuilder mjr = new StringBuilder();
        
        mjr.append('[');
        mjr.append(ID);
        mjr.append(']');
        mjr.append('<');
        mjr.append('.');
        mjr.append('.');
        mjr.append('.');
        mjr.append('>');
        
        return mjr.toString();
    }
    
    public String sisennettyMerkkijono(final String SISENNYS) {
//        mjr.append(SISENNYS);
        if (!tulostettu) {
            tulostettu = true;
            StringBuilder mjr = new StringBuilder();
            
            mjr.append('[');
            mjr.append(ID);
            mjr.append(']');
            mjr.append('<');
            if (ON_HYVAKSYVA) {
                mjr.append('1');
            } else {
                mjr.append('0');
            }
            mjr.append(',');
            mjr.append(TILASIIRTYMAT.sisennettyMerkkijono(SISENNYS + "  "));
            mjr.append('>');
        
        return mjr.toString();
        }
        return toString();
    }
    
    public static void nollaaTilalaskuri() {
        Tila.tiloja = 0;
    }
}
