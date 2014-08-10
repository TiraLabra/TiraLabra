package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.AI.AI;
import com.mycompany.tiralabra_maven.AI.EkaAI;
import com.mycompany.tiralabra_maven.gui.Piirtoalusta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Luokka sisältää metodeja peliolion käsittelyyn
 * 
 * @author noora
 */
public class Peli implements ActionListener {

    private final Pelilauta pelilauta;
    private boolean peliKaynnissa;
    private boolean valkoisenVuoroSiirtaa;
    private Piirtoalusta piirtoalusta;
    private Siirto[] sallitutSiirrot;
    private int valittuRivi;
    private int valittuSarake;
    private final AI AI;

    /**
     * Konstruktorissa luodaan uusi pelilauta ja tekoälyn ensimmäinen versio.
     */
    public Peli() {
        this.pelilauta = new Pelilauta();
        this.peliKaynnissa = false;
        AI = new EkaAI(this);
    }

    public Pelilauta getPelilauta() {
        return pelilauta;
    }

    public void setPiirtoalusta(Piirtoalusta piirtoalusta) {
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

    public Piirtoalusta getPiirtoalusta() {
        return piirtoalusta;
    }

    public boolean isValkoisenVuoroSiirtaa() {
        return valkoisenVuoroSiirtaa;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.piirtoalusta.paivita();
    }

    /**
     * Metodi aloittaa uuden pelin. Jos peli on jo käynnissä, pelaajalle näytetään virheilmoitus.
     * Metodi käskee pelilautaa asettamaan nappulat alkuasetelmaan ja antaa siirtovuoron valkoiselle.
     * Sen lisäksi kysytään pelilaudelta sallitut siirrot ja muokataan piirtoalustan näkymä käynnissä olevan pelin näkymäksi.
     */
    public void uusiPeli() {
        if (peliKaynnissa == true) {
            piirtoalusta.naytaViesti("Peli on kesken! Luovuta ensin ja aloita uusi peli vasta sitten.");
            return;
        }
        pelilauta.asetaNappulatAlkuasetelmaan();
        this.valkoisenVuoroSiirtaa = true;
        this.sallitutSiirrot = pelilauta.getSallitutSiirrot(this.valkoisenVuoroSiirtaa);
        piirtoalusta.setSallitutSiirrot(this.sallitutSiirrot);
        this.valittuRivi = -1;
        this.valittuSarake = -1;
        piirtoalusta.setValittuRivi(valittuRivi);
        piirtoalusta.setValittuSarake(valittuSarake);
        piirtoalusta.naytaViesti("Valkoisen vuoro siirtää!");
        this.peliKaynnissa = true;
        piirtoalusta.muokkaaNapitKunPeliKaynnissa();
        piirtoalusta.paivita();
    }
    
    /**
     * Metodia kutsutaan kun käyttäjä painaa luovuta peli -nappia.
     * Jos peliä ei ole käynnissä, käyttäjälle näytetään virheilmoitus.
     * Muuten peli lopetetaan siten että nappia painanut pelaaja hävisi pelin.
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
     * Metodin avulla lopetetaan peli.
     * Käyttäjälle näytetään viesti, joka voi sisältää esimerkiksi tiedon voittajasta.
     * Tämän lisäksi piirtoalustan nappeja muokaan siten että käyttäjä voi aloittaa uuden pelin.
     * @param viesti on käyttäjälle näytettava viesti
     */
    private void peliLoppu(String viesti) {
        piirtoalusta.naytaViesti(viesti);
        piirtoalusta.muokkaaNapitKunPeliLoppu();
        this.peliKaynnissa = false;
        piirtoalusta.paivita();
    }
    
    /**
     * Metodia kutsutaan kun käyttäjä painaa AI-nappia.
     * Jos peliä ei ole käynnissä, käyttäjälle näytetään virheilmoitus.
     * Muuten AI:ltä kysytään siirtoa, joka annetaan siirräNappulaa-metodille.
     */
    public void AISiirtaa() {
        if (peliKaynnissa == false){
            piirtoalusta.naytaViesti("Aloita ensin uusi peli!");
            return;
        }
        siirraNappulaa(this.AI.seuraavaSiirto());
        
    }

    /**
     * Metodia kutsutaan, kun käyttäjä valitsee jonkin ruudun pelilaudalta.
     * Metodissa tutkitaan löytyykö kyseiseistä ruutua sallittujen siirtojen listalta.
     * Käyttäjän on ensin valittava ruutu, jossa on nappula, jota hän voi siirtää.
     * Tämä ruutu asetetaan valituksi, jonka jälkeen käyttäjän on valittava ruutu, johon valitun ruudun nappulan voi siirtää.
     * Käyttäjälle näytetään viestejä, jotta hän valitsisi ruudut.
     * @param rivi on valitun ruudun rivin numero
     * @param sarake on valitun ruudun sarakkeen numero
     */
    public void valitseRuudutJoissaSiirtoTapahtuu(int rivi, int sarake) {
        for (Siirto siirto : sallitutSiirrot) {
            if (siirto.getAlkuRivi() == rivi && siirto.getAlkuSarake() == sarake) {
                this.valittuRivi = rivi;
                this.valittuSarake = sarake;
                piirtoalusta.setValittuRivi(rivi);
                piirtoalusta.setValittuSarake(sarake);
                piirtoalusta.naytaViesti("Tee siirto");
                piirtoalusta.paivita();
                return;
            }
        }
        if (this.valittuRivi < 0) {
            piirtoalusta.naytaViesti("Valitse nappula, jota haluat liikuttaa");
            return;
        }
        for (Siirto siirto2 : sallitutSiirrot) {
            if (siirto2.getAlkuRivi() == valittuRivi && siirto2.getAlkuSarake() == valittuSarake && siirto2.getLoppuRivi() == rivi && siirto2.getLoppuSarake() == sarake) {
                siirraNappulaa(siirto2);
                return;
            }
        }
        piirtoalusta.naytaViesti("Valitse ruutu, johon haluat liikkua");

    }

    /**
     * Metodi käskee pelilautaa toteuttamaan annetun siirron. 
     * Tämän jälkeen siirtoja saatetaan vielä joutua jatkamaan, jos ensimmäinen siirto oli hyppy.
     * Lopuksi siirtovuoro vaihtuu toiselle pelaajalle
     * @param siirto on siirto, joka halutaan toteuttaa
     */
    private void siirraNappulaa(Siirto siirto) {
        pelilauta.teeSiirto(siirto);

        if (jatkaSiirtamistaTarvittaessaJosHyppy(siirto)) {
            return;
        }
        vaihdaPelaajaaJonkaVuoroSiirtaa();
    }

    /**
     * Metodi vaihtaa siirtovuoron toiselle pelaajalle ja tarvittaessa lopettaa pelin, jos sallittuja siirtoja ei enää ole jäljellä
     */
    private void vaihdaPelaajaaJonkaVuoroSiirtaa() {
        if (valkoisenVuoroSiirtaa) {
            valkoisenVuoroSiirtaa = false;
            this.sallitutSiirrot = pelilauta.getSallitutSiirrot(valkoisenVuoroSiirtaa);
            if (this.sallitutSiirrot == null) {
                peliLoppu("Musta ei voi siirtää. Valkoinen voitti");
            } else {
                piirtoalusta.naytaViesti("Mustan vuoro siirtää");
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
//        valitseNappulaValmiiksiJosVainYhtaNappulaaVoiSiirtaa();
        piirtoalusta.paivita();
    }

//    private void valitseNappulaValmiiksiJosVainYhtaNappulaaVoiSiirtaa() {
//        if (this.sallitutSiirrot != null){
//            boolean vainYhtaNappulaaVoiSiirtaa = true;
//            for (int i = 0; i < this.sallitutSiirrot.length; i++){
//                if (this.sallitutSiirrot[i].getAlkuRivi() != this.sallitutSiirrot[0].getAlkuRivi() || 
//                        this.sallitutSiirrot[i].getAlkuSarake() != this.sallitutSiirrot[0].getAlkuSarake()){
//                    vainYhtaNappulaaVoiSiirtaa = false;
//                    break;
//                }
//            }
//            if (vainYhtaNappulaaVoiSiirtaa){
//                this.valittuRivi = this.sallitutSiirrot[0].getAlkuRivi();
//                this.valittuSarake = this.sallitutSiirrot[0].getLoppuRivi();
//            }
//        }
//    }
    
    /**
     * Metodi jatkaa saman pelaajan siirtovuoroa, jos ensimmäinen siirto oli hyppy ja mahdollisia hyppyjä on vielä olemassa
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
                piirtoalusta.paivita();
                return true;
            }
        }
        return false;
    }

    

    

}
