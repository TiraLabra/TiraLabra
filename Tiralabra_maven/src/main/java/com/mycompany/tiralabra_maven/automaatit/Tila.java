
package com.mycompany.tiralabra_maven.automaatit;

import com.mycompany.tiralabra_maven.tietorakenteet.Hajautuskartta;
import com.mycompany.tiralabra_maven.tietorakenteet.Jono;

/**
 * 
 * 
 * http://en.wikipedia.org/wiki/Nondeterministic_finite_automaton_with_%CE%B5-moves
 *
 * @author John Lång <jllang@cs.helsinki.fi>
 */
final class Tila {
    
    final boolean ON_HYVAKSYVA;
    private final Hajautuskartta<Tila> TILASIIRTYMAT;
    
    Tila(final boolean ON_HYVAKSYVA) {
        this.ON_HYVAKSYVA   = ON_HYVAKSYVA;
        this.TILASIIRTYMAT  = new Hajautuskartta<>();
    }
    
    void lisaaTilasiirtyma(final char EHTO, final Tila TILA) {
        // Jos automaatti olisi deterministinen, tarkastettaisiin ettei
        // tyhjälle merkille '\0' lisättäisi tilasiirtymää.
        TILASIIRTYMAT.lisaa(EHTO, TILA);
    }
    
    /**
     * 
     * @param   MERKKI Syötteen merkki jolle etsitään tilasiirtymää.
     * @return  <b>Jono</b> joka sisältää automaatin seuraavat mahdolliset 
     *          tilasiirtymät syötteenä annetulle merkille tai <i>null</i> jos
     *          sellaisia ei ole.
     */
    Jono<Tila> tilasiirtymat(final Character MERKKI) {
        // Tila[] voisi ehkä olla nopeampi tietorakenne...
        return TILASIIRTYMAT.haeKaikki(MERKKI);
    }
}
