package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.AI.Pelaaja;

/**
 * Luokka on lähes tarpeettomaksi jäänyt ihmispelaajan AIn vastine.
 *
 * @author noora
 */
public class IhmisPelaaja extends Pelaaja {

    private final PeliOhjain peliOhjain;

    public IhmisPelaaja(Peli peli, PeliOhjain peliOhjain) {
        super(peli);
        this.peliOhjain = peliOhjain;
    }
    
    /**
     * Metodi kysyy peliohjaimelta ihmisen tekemän siirron
     * @param sallitutSiirrot Lista pelaajalle sallituista siirroista
     * @return Palauttaa pelaajan seuraavaksi tekemän siirron
     */
    @Override
    public Siirto seuraavaSiirto(Siirto[] sallitutSiirrot) {
        return peliOhjain.getSeuraavaSiirto(sallitutSiirrot);
    }

}
