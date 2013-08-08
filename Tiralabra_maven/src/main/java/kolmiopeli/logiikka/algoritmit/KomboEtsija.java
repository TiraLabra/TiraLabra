
package kolmiopeli.logiikka.algoritmit;

import kolmiopeli.domain.Kolmio;
import kolmiopeli.domain.Koordinaatti;

/**
 * Luokka lahtee kaymaan pelilautaa lapi uusista arvotuista kolmioista 
 * ja etsii kolmen tai useamman samanvarisen vierekkaisen kolmion 
 * muodostamia polkuja kolmioiden muodostamasta verkosta.
 * @author Eemi
 */
public class KomboEtsija {
    private Kolmio[][] peliruudukko;
    
    public KomboEtsija(Kolmio[][] peliruudukko) {
        this.peliruudukko = peliruudukko;
    }
    
    /**
     * Kay lapi juuriarvottujen kolmioiden laheisia kolmioita niin pitkalle kun loytyy sopivan varisia.
     * @param Array kohdista joissa on uunituoreita uusia kolmioita arvottu.
     */
    public void etsiKombot(Koordinaatti[] juuriArvotut) {
        for (int i = 0; i < juuriArvotut.length; i++) {
            // BFS
        }
    }
    
}
