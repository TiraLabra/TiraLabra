package viidensuora.ai;

import viidensuora.logiikka.Koordinaatti;
import viidensuora.logiikka.Ristinolla;

/**
 * Tietokonepelaajan tekoälyä kuvaava luokka.
 *
 * @author juha
 */
public class Tekoaly {

    /**
     * Pelitilanne.
     */
    private final Ristinolla rn;

    /**
     * Metodi, jolla paras siirto etsitään.
     */
    private final Etsintametodi metodi;

    /**
     * Käyttöliittymää/Hiirikuuntelijaa varten?
     */
    private boolean miettii;

    /**
     * metodi parametrina?
     *
     * @param rn
     */
    public Tekoaly(Ristinolla rn) {
        this.rn = rn;
        this.metodi = new AlphaBetaKarsinta(rn, new MunEvaluoija());
        //this.metodi = new MinMax(rn, new MunEvaluoija());
        this.miettii = false;
    }

    /**
     * Etsii parhaan siirron käyttäen omaa Etsintämetodiaan.
     *
     * @param syvyys syvyys, jolta etsitään
     * @param ristinVuoro kumman pelaajan siirto
     * @return parhaan löydetyn siirron koordinaati
     */
    public Koordinaatti etsiParasSiirto(int syvyys, boolean ristinVuoro) {
        miettii = true;
        Koordinaatti k = ristinVuoro ? metodi.etsiRistinSiirto(syvyys)
                : metodi.etsiNollanSiirto(syvyys);
        miettii = false;
        return k;
    }

    /**
     * Hiirikuuntelijaa varten??
     * @return 
     */
    public boolean miettii() {
        return miettii;
    }

}
