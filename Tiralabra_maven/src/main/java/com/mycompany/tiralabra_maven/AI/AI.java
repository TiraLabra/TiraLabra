package com.mycompany.tiralabra_maven.AI;

import com.mycompany.tiralabra_maven.peli.Pelaaja;
import com.mycompany.tiralabra_maven.peli.Peli;
import com.mycompany.tiralabra_maven.peli.PeliOhjain;
import com.mycompany.tiralabra_maven.peli.Siirto;

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
    

    /**
     * Konstruktorissa asetetaan AI siirtämään automaattisesti, jos peliohjainta ei ole asetettu
     * @param peli Käynnissä oleva peli
     * @param peliohjain Peliä ohjaava peliohjain
     * @param siirraAutomaagisesti Tieto siitä, siirtääkö AI automaattisesti vai painaako käyttäjä aina nappia kun haluaa siirron tapahtuvan
     * @param viive mahdollinen viive AIn siirroissa, jonka avulla käyttäjä ehtii havaitsemaan yksittäiset siirrot
     */
    public AI(Peli peli, PeliOhjain peliohjain, boolean siirraAutomaagisesti, int viive) {
        this.peliohjain = peliohjain;
        this.peli = peli;
        this.viive = viive;
        this.siirraAutomaagisesti = siirraAutomaagisesti;
        
        if (peliohjain == null) {
            this.siirraAutomaagisesti = true;
        }
    }

    /**
     * Konstruktori AIn luomiseksi ilman peliohjainta. Täälöin AIn on aina siirrettävä automaattisesti,
     * sillä peliohjain käsittelee käyttöliittymän nappien painallukset
     * @param peli Käynnissä oleva peli
     */
    public AI(Peli peli) {
        this(peli, null, true, 0);
    }

    public boolean isSiirraAutomaagisesti() {
        return siirraAutomaagisesti;
    }

    /**
     * Metodi aiheuttaa viiveen tekoälyn siirroille, jolloin käyttäjälläkin on mahdollisuus nähdä yksittäiset siirrot
     */
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
    @Override
    public abstract Siirto seuraavaSiirto(Siirto[] sallitutSiirrot);
}
