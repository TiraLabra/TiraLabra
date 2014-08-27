package com.mycompany.tiralabra_maven.AI;

import com.mycompany.tiralabra_maven.Peli;
import com.mycompany.tiralabra_maven.PeliOhjain;
import com.mycompany.tiralabra_maven.Siirto;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstrakti luokka, joka toimii tekoälynä eli jolta voi kysyä seuraavaa siirtoa
 *
 * @author noora
 */
public abstract class AI implements Pelaaja {

    protected Peli peli;
    private int viive;
    private boolean siirraAutomaagisesti;
    private PeliOhjain peliohjain;
    

    public AI(Peli peli, PeliOhjain peliohjain, boolean siirraAutomaagisesti, int viive) {
        this.peliohjain = peliohjain;
        this.peli = peli;
        this.viive = viive;
        this.siirraAutomaagisesti = siirraAutomaagisesti;
        
        if (peliohjain == null) {
            this.siirraAutomaagisesti = true;
        }
    }

    public AI(Peli peli) {
        this(peli, null, true, 0);
    }

    protected void odota() {
        if (siirraAutomaagisesti) {

            if (viive == 0) {
                return;
            }
            try {
                Thread.sleep(viive);
            } catch (InterruptedException ex) {
            }
        } else {
            peliohjain.odotaAiNapinPainamista();

        }
    }

    /**
     * Metodi palauttaa seuraavan siirron
     *
     * @param sallitutSiirrot
     * @return paluttaa seuraavan siirron
     */
    public abstract Siirto seuraavaSiirto(Siirto[] sallitutSiirrot);
}
