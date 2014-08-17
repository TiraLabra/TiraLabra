package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.AI.AI;
import com.mycompany.tiralabra_maven.AI.EkaAI;
import com.mycompany.tiralabra_maven.AI.MinimaxAI;
import com.mycompany.tiralabra_maven.AI.SeuraavanSiirronPisteetAI;
import com.mycompany.tiralabra_maven.gui.Paivitettava;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Luokka sisältää metodeja peliolion käsittelyyn
 *
 * @author noora
 */
public class Peli {

    private final Pelilauta pelilauta;
    private boolean peliKaynnissa;
    private boolean valkoisenVuoroSiirtaa;
    private Paivitettava piirtoalusta;
    private Siirto[] sallitutSiirrot;
    private int valittuRivi;
    private int valittuSarake;
    private final AI AI;
    private Pelaaja pelaaja;

    /**
     * Konstruktorissa luodaan uusi pelilauta ja tekoälyn ensimmäinen versio.
     */
    public Peli() {
        this.pelilauta = new Pelilauta();
        this.peliKaynnissa = false;
        AI = new MinimaxAI(this);
        //AI = new EkaAI(this);
        pelaaja = new Pelaaja(this);
    }

    public Pelilauta getPelilauta() {
        return pelilauta;
    }

    public void setPaivitettava(Paivitettava piirtoalusta) {
        this.piirtoalusta = piirtoalusta;
    }

    public boolean isPeliKaynnissa() {
        return peliKaynnissa;
    }

    public Siirto[] getSallitutSiirrot() {
        return sallitutSiirrot;
    }

    public int getValittuRivi() {
        return valittuRivi;
    }

    public int getValittuSarake() {
        return valittuSarake;
    }

    public Paivitettava getPaivitettava() {
        return piirtoalusta;
    }

    public boolean isValkoisenVuoroSiirtaa() {
        return valkoisenVuoroSiirtaa;
    }

    public Pelaaja getPelaaja() {
        return pelaaja;
    }

    /**
     * Metodi aloittaa uuden pelin. Jos peli on jo käynnissä, pelaajalle
     * näytetään virheilmoitus. Metodi käskee pelilautaa asettamaan nappulat
     * alkuasetelmaan ja antaa siirtovuoron valkoiselle. Sen lisäksi kysytään
     * pelilaudelta sallitut siirrot ja muokataan piirtoalustan näkymä käynnissä
     * olevan pelin näkymäksi.
     */
    public void uusiPeli() {
        if (peliKaynnissa == true) {
            piirtoalusta.naytaViesti("Peli on kesken! Luovuta ensin ja aloita uusi peli vasta sitten.");
            return;
        }
        pelilauta.asetaNappulatAlkuasetelmaan();
        this.valkoisenVuoroSiirtaa = true;
        this.sallitutSiirrot = pelilauta.getSallitutSiirrot(this.valkoisenVuoroSiirtaa);
        this.valittuRivi = -1;
        this.valittuSarake = -1;
        piirtoalusta.naytaViesti("Valkoisen vuoro siirtää!");
        this.peliKaynnissa = true;
    }

    /**
     * Metodia kutsutaan kun käyttäjä painaa luovuta peli -nappia. Jos peliä ei
     * ole käynnissä, käyttäjälle näytetään virheilmoitus. Muuten peli
     * lopetetaan siten että nappia painanut pelaaja hävisi pelin.
     */
    public void luovutaPeli() {
        if (peliKaynnissa == false) {
            piirtoalusta.naytaViesti("Aloita peli ennen luovuttamista!");
            return;
        }
        if (valkoisenVuoroSiirtaa) {
            peliLoppu("Valkoinen luovutti. Musta voitti!");
        } else {
            peliLoppu("Musta luovutti. Valkoinen voitti!");
        }
    }

    /**
     * Metodin avulla lopetetaan peli. Käyttäjälle näytetään viesti, joka voi
     * sisältää esimerkiksi tiedon voittajasta. Tämän lisäksi piirtoalustan
     * nappeja muokaan siten että käyttäjä voi aloittaa uuden pelin.
     *
     * @param viesti on käyttäjälle näytettava viesti
     */
    private void peliLoppu(String viesti) {
        piirtoalusta.naytaViesti(viesti);
        this.peliKaynnissa = false;
    }

    /**
     * Metodia kutsutaan kun käyttäjä painaa AI-nappia. Jos peliä ei ole
     * käynnissä, käyttäjälle näytetään virheilmoitus. Muuten AI:ltä kysytään
     * siirtoa, joka annetaan siirräNappulaa-metodille.
     */
    public void AISiirtaa() {
        if (peliKaynnissa == false) {
            piirtoalusta.naytaViesti("Aloita ensin uusi peli!");
            return;
        }
        siirraNappulaa(this.AI.seuraavaSiirto());
    }

    /**
     * Metodi käskee pelilautaa toteuttamaan annetun siirron. Tämän jälkeen
     * siirtoja saatetaan vielä joutua jatkamaan, jos ensimmäinen siirto oli
     * hyppy. Lopuksi siirtovuoro vaihtuu toiselle pelaajalle
     *
     * @param siirto on siirto, joka halutaan toteuttaa
     */
    public void siirraNappulaa(Siirto siirto) {
        pelilauta.teeSiirto(siirto);

        if (jatkaSiirtamistaTarvittaessaJosHyppy(siirto)) {
            return;
        }
        vaihdaPelaajaaJonkaVuoroSiirtaa();
    }

    /**
     * Metodi vaihtaa siirtovuoron toiselle pelaajalle ja tarvittaessa lopettaa
     * pelin, jos sallittuja siirtoja ei enää ole jäljellä
     */
    private void vaihdaPelaajaaJonkaVuoroSiirtaa() {
        if (valkoisenVuoroSiirtaa) {
            valkoisenVuoroSiirtaa = false;
            this.sallitutSiirrot = pelilauta.getSallitutSiirrot(valkoisenVuoroSiirtaa);
            if (this.sallitutSiirrot == null) {
                peliLoppu("Musta ei voi siirtää. Valkoinen voitti");
            } else {
                piirtoalusta.naytaViesti("Mustan vuoro siirtää");
                AISiirtaa();
            }
        } else {
            valkoisenVuoroSiirtaa = true;
            this.sallitutSiirrot = pelilauta.getSallitutSiirrot(valkoisenVuoroSiirtaa);
            if (this.sallitutSiirrot == null) {
                peliLoppu("Valkoinen ei voi siirtää. Musta voitti");
            } else {
                piirtoalusta.naytaViesti("Valkoisen vuoro siirtää");
            }
        }
        this.valittuRivi = -1;
        this.valittuSarake = -1;
//        valitseNappulaValmiiksiJosVainYhtaNappulaaVoiSiirtaa();
    }

    /**
     * Metodi jatkaa saman pelaajan siirtovuoroa, jos ensimmäinen siirto oli
     * hyppy ja mahdollisia hyppyjä on vielä olemassa
     *
     * @param siirto on ensimmäinen tehty siirto
     * @return palauttaa tiedon siitä, onko hyppyjä vielä olemassa
     */
    private boolean jatkaSiirtamistaTarvittaessaJosHyppy(Siirto siirto) {
        if (siirto.onkoSiirtoHyppy()) {
            this.sallitutSiirrot = pelilauta.getSallitutHypyt(valkoisenVuoroSiirtaa, siirto.getLoppuRivi(), siirto.getLoppuSarake());
            if (this.sallitutSiirrot != null) {
                piirtoalusta.naytaViesti("Jatka hyppimistä");
                this.valittuRivi = siirto.getLoppuRivi();
                this.valittuSarake = siirto.getLoppuSarake();
                return true;
            }
        }
        return false;
    }
}
