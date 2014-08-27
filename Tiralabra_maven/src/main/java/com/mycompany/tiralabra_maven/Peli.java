package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.AI.Pelaaja;
import com.mycompany.tiralabra_maven.gui.Paivitettava;

/**
 * Luokka sisältää metodeja peliolion käsittelyyn
 *
 * @author noora
 */
public class Peli implements Runnable {

    private final Pelilauta pelilauta;
    private boolean peliKaynnissa;
    private Paivitettava paivitettava;
    private Siirto[] sallitutSiirrot;
    private String viesti;
    private Pelaaja musta;
    private Pelaaja valkoinen;
    private Pelaaja vuorossaOlevaPelaaja;
    private boolean valkoisenVuoroSiirtaa;
    private int siirtojenMaara;

    /**
     * Konstruktorissa luodaan uusi pelilauta, asetetaan sille nappulat ja selvitetään sallitut aloitussiirrot.
     */
    public Peli() {
        this.pelilauta = new Pelilauta();
        this.pelilauta.asetaNappulatAlkuasetelmaan();
        this.sallitutSiirrot = pelilauta.getSallitutSiirrot(true);
        this.peliKaynnissa = false;
        this.siirtojenMaara = 0;
    }

    public Pelilauta getPelilauta() {
        return pelilauta;
    }

    public void setPaivitettava(Paivitettava paivitettava) {
        this.paivitettava = paivitettava;
    }

    public boolean isPeliKaynnissa() {
        return peliKaynnissa;
    }

    public Siirto[] getSallitutSiirrot() {
        return sallitutSiirrot;
    }

    public boolean isValkoisenVuoroSiirtaa() {
        //return vuorossaOlevaPelaaja == valkoinen;
        return valkoisenVuoroSiirtaa;
    }

    public void setMusta(Pelaaja pelaaja) {
        this.musta = pelaaja;
    }

    public void setValkoinen(Pelaaja pelaaja) {
        this.valkoinen = pelaaja;
    }

    public Pelaaja getMusta() {
        return musta;
    }

    public Pelaaja getValkoinen() {
        return valkoinen;
    }

    
    /**
     * Metodi käynnistää uuden pelin, antaa siirtovuoron valkoiselle ja käskee tekemään siirtoja niin kauan kuin peli on käynnissä.
     * Jos siirto oli hyppy eli vastustajan nappulan syöminen, on vielä tarkastettava, onko hyppimistä mahdollista jatkaa samalla nappulalla.
     * Kun mahdollisia hyppimisiä ei enää ole, siirtovuoro vaihtuu toiselle pelaajalle. Jos toisella pelaajalla ei ole ollenkaan sallittuja siirtoja, peli päättyy.
     *
     */
    @Override
    public void run() {
        if (musta == null || valkoinen == null) {
            //System.out.println("PELAAJIA EI OLTU LAITETTU KUNOLLA !! >:(");
            return;
        }
        vuorossaOlevaPelaaja = valkoinen;
        valkoisenVuoroSiirtaa = true;
        
        peliKaynnissa = true;
        while (peliKaynnissa) {
            siirra();
        }

    }

    private void siirra() {
        Siirto siirto = vuorossaOlevaPelaaja.seuraavaSiirto(pelilauta.getSallitutSiirrot(valkoisenVuoroSiirtaa));
        siirraNappulaa(siirto);
        this.sallitutSiirrot = pelilauta.getSallitutHypyt(valkoisenVuoroSiirtaa, siirto.getLoppuRivi(), siirto.getLoppuSarake());
        while (siirto.onkoSiirtoHyppy() && sallitutSiirrot != null) {
            siirto = vuorossaOlevaPelaaja.seuraavaSiirto(sallitutSiirrot);
            siirraNappulaa(siirto);
            this.sallitutSiirrot = pelilauta.getSallitutHypyt(valkoisenVuoroSiirtaa, siirto.getLoppuRivi(), siirto.getLoppuSarake());
        }
        siirtojenMaara++;

        vaihdaVuoroa();
        
        if (this.siirtojenMaara > 100){
            peliLoppu("Pattitilanne, peli loppu!");
        }

        if (!onkoSallittujaSiirtoja()) {
            if (valkoisenVuoroSiirtaa) {
                peliLoppu("Valkoinen ei voi siirtää. Musta voitti");
            } else {
                peliLoppu("Musta ei voi siirtää. Valkoinen voitti");
            }
        }
    }

    private boolean onkoSallittujaSiirtoja() {
        return pelilauta.getSallitutSiirrot(valkoisenVuoroSiirtaa) != null;
    }

    private void vaihdaVuoroa() {
        if (this.vuorossaOlevaPelaaja == valkoinen) {
            this.vuorossaOlevaPelaaja = musta;
        } else {
            this.vuorossaOlevaPelaaja = valkoinen;
        }
        
        valkoisenVuoroSiirtaa = !valkoisenVuoroSiirtaa;
    }

    /**
     * Metodia kutsutaan kun käyttäjä painaa luovuta peli -nappia. Peli
     * lopetetaan siten että nappia painanut pelaaja häviää pelin.
     */
    public void luovutaPeli() {
        if (valkoisenVuoroSiirtaa) {
            peliLoppu("Valkoinen luovutti. Musta voitti!");
        } else {
            peliLoppu("Musta luovutti. Valkoinen voitti!");
        }
    }

    private void peliLoppu(String viesti) {
        this.setViesti(viesti);
        this.peliKaynnissa = false;
    }

    private void siirraNappulaa(Siirto siirto) {
        //System.out.println("alku: " + siirto.getAlkuRivi() + siirto.getAlkuSarake() + " loppu: " + siirto.getLoppuRivi() + siirto.getLoppuSarake());
        pelilauta.teeSiirto(siirto);
        if (paivitettava != null) {
            paivitettava.paivita();
        }
    }

    /**
     * Metodi asettaa annetun viestin muuttujaan, josta käyttöliittymäikkuna sitten voi sen käydä hakemassa.
     * Viestin asettamisen jälkeen päivitetään käyttöliittymä, jolloin myös uusi viesti haetaan.
     * @param viesti Viesti, joka haluttaa esittää käyttäjälle.
     */
    public void setViesti(String viesti) {
        this.viesti = viesti;
        if (paivitettava != null) {
            this.paivitettava.paivita();
        }
    }

    public String getViesti() {
        return this.viesti;
    }
}
