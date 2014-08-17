package com.mycompany.tiralabra_maven;

/**
 * Luokka on ihmispelaajan AIn vastine. 
 * Luokka muuttaa hiirenkuuntelijalta saadun tiedon valitusta ruudusta siirroksi pelilaudalla.
 * 
 * @author noora
 */
public class Pelaaja {
    private Peli peli;
    private Siirto[] sallitutSiirrot;
    private int valittuRivi;
    private int valittuSarake;
    
    public Pelaaja(Peli peli){
        this.peli = peli;
        this.valittuRivi = -1;
        this.valittuSarake = -1;
    }

    public int getValittuRivi() {
        return valittuRivi;
    }

    public int getValittuSarake() {
        return valittuSarake;
    }
    
    
    
    /**
     * Metodi käskee peliä toteuttamaan pelaajan valitseman siirron.
     * Pelaaja valitsee ensin liikuteltavan nappulan, jonka on löydyttävä sallittujen siirtojen listalta.
     * Tämän jälkeen pelääja valitsee ruudun, johon haluaa nappulan liikuttaa. Siirron on oltava sallittu, jotta se toteutettaisiin.
     * @param rivi Pelaajan valitsema rivi
     * @param sarake Pelaajan valitsema sarake
     */
    public void valitseRuudutJoissaSiirtoTapahtuu(int rivi, int sarake) {
        sallitutSiirrot = peli.getSallitutSiirrot();
        if (valitseRuutuJostaLiikutaan(rivi, sarake)) {
            return;
        }
        if (this.valittuRivi < 0) {
            peli.getPaivitettava().naytaViesti("Valitse nappula, jota haluat liikuttaa");
            return;
        }
        if (valitseRuutuJohonLiikutaan(rivi, sarake)) {
            return;
        }
        peli.getPaivitettava().naytaViesti("Valitse ruutu, johon haluat liikkua");
    }
    
    /**
     * Metodi tutkii, löytyykö pelaajan valitsema ruutu sallittujen siirrettävien nappulaoiden joukosta.
     * @param rivi Pelaajan valitseman nappulan rivi
     * @param sarake Pelaajan valitseman nappulan sarake
     * @return Tieto siitä, oliko valittu ruutu sallittujen siirrettävien nappuloiden joukossa
     */
    private boolean valitseRuutuJostaLiikutaan(int rivi, int sarake) {
        for (Siirto siirto : sallitutSiirrot) {
            if (siirto.getAlkuRivi() == rivi && siirto.getAlkuSarake() == sarake) {
                this.valittuRivi = rivi;
                this.valittuSarake = sarake;
                peli.getPaivitettava().naytaViesti("Tee siirto");
                return true;
            }
        }
        return false;
    }

    /**
     * Metodi tutkii, voiko pelaajan valitseman nappulan siirtää pelaajan valitsemaan ruutuun ja jos voi, käskee peliä toteuttamaan siirron
     * @param rivi Pelaajan valitsema rivi, johon nappulaa ollaan siirtämässä
     * @param sarake Pelaajan valitsema sarake, johon nappulaa ollaan siirtämässä
     * @return Tieto siitä, oliko haluttu siirto sallittujen siirtojen listassa
     */
    private boolean valitseRuutuJohonLiikutaan(int rivi, int sarake) {
        for (Siirto siirto : sallitutSiirrot) {
            if (siirto.getAlkuRivi() == valittuRivi && siirto.getAlkuSarake() == valittuSarake && siirto.getLoppuRivi() == rivi && siirto.getLoppuSarake() == sarake) {
                peli.siirraNappulaa(siirto);
                return true;
            }
        }
        return false;
    }
    
}
