
package com.mycompany.tiralabra_maven.automaatit;

import com.mycompany.tiralabra_maven.tietorakenteet.Hajautuskartta;

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
    private final Tila VILLI_KORTTI;    // Tänne voidaan siirtyä epsilonilla.

    public Tila(final boolean ON_HYVAKSYVA, final Tila VILLI_KORTTI) {
        this.ON_HYVAKSYVA   = ON_HYVAKSYVA;
        // Tilasiirtymien määrää on vähän paha arvata ennakolta joten olisi ehkä
        // hyvä jos hajautuskartta uudelleenhajauttaisi itsensä tarvittaessa...
        this.TILASIIRTYMAT  = new Hajautuskartta<Tila>(3);
        this.VILLI_KORTTI   = VILLI_KORTTI;
    }
    
    /**
     * Automaatti siirtyy seuraavaan tilaan jos nykyiselle tilalle on määritelty
     * syötettä vastaava tilasiirtymä. Jos tilasiirtymää ei ole määritelty, 
     * palautetaan <i>null</i> merkkinä automaatille siitä ettei syöte vastaa
     * säännöllistä lauseketta.
     * 
     * @param   merkki Syötteen merkki jolle etsitään tilasiirtymää.
     * @return  Automaatin seuraava tila tai <i>null</i>.
     */
    public Tila tilasiirtyma(Character merkki) {
        return TILASIIRTYMAT.hae(merkki);
    }
    
    /**
     * Automaatti voi siirtyä tilasta toiseen ilman siirtymistä seuraavaan
     * merkkiin syötteessä. Kutsun tällaista tilasiirtymää
     * epsilon-tilasiirtymäksi.
     * 
     * @return  Seuraava tila mikäli tilalle on määritelty epsilon-tilasiirtymä.
     *          Muutoin <i>null</i>.
     */
    public Tila epsilonTilasiirtyma() {
        return VILLI_KORTTI;
    }
    
}
