package kolmiopeli.domain;

import java.awt.Color;
import java.util.ArrayList;
import kolmiopeli.logiikka.KolmionVarinSopivuudenTarkastaja;
/**
 * Ruudukko joka sisaltaa Kolmioita, ts. pelilauta.
 * @author Eemi
 */
public class Ruudukko {
    // Tassa vaiheessa toteutan ruudukon kaksiulotteisena tauluna
    // jossa joka toinen kolmio osoittaa ylos ja ja joka toinen alas.
    // Katso dokumentointi/liitteet/RuudukonIdea.jpg

    /**
     * Kaksiulotteinen taulu johon kolmiot tallennetaan.
     */
    private Kolmio[][] ruudukko;
    /**
     * Variarpoja jolla arvotaan luotaville kolmioille varit.
     * @see kolmiopeli.domain.Variarpoja
     */
    private Variarpoja variarpoja;
    private KolmionVarinSopivuudenTarkastaja kolmionSopivuudenTarkastaja;

    public Ruudukko(int korkeus, int leveys) {
        this.ruudukko = new Kolmio[korkeus][leveys];
        this.variarpoja = new Variarpoja();
        this.kolmionSopivuudenTarkastaja = new KolmionVarinSopivuudenTarkastaja(this);

    }

    // Raaka versio taulun luomisesta, variarpojaa/luoKolmiotTauluun metodia pitaa muokata jotta 
    // alkutilanteessa olisi korkeintaan kaksi samanvarista kolmiota vierekkain.
    // (Talla hetkella metodi saattaa luoda vaikka koko taulun samanvariseksi)
    // Tama metodi on OBSOLETE
    public boolean luoKolmiotTauluun() {
        for (int rivi = 0; rivi < this.ruudukko.length; rivi++) {
            for (int sarake = 0; sarake < this.ruudukko[0].length; sarake++) {
                this.ruudukko[rivi][sarake] = new Kolmio(variarpoja.arvoVari(), rivi, sarake);
            }
        }
        return true;
    }

    public KolmionVarinSopivuudenTarkastaja getKolmionSopivuudenTarkastaja() {
        return this.kolmionSopivuudenTarkastaja;
    }

    
    
    
    public void setRuudukko(Kolmio[][] ruudukko) {
        this.ruudukko = ruudukko;
    }

    /**
     * Luo ruudukosta raa'an hahmotelman stringina.
     * @return Peliruudukon tekstimuodossa
     */
    @Override
    public String toString() {
        String ruudukkoTekstina = "";
        for (int rivi = 0; rivi < this.ruudukko.length; rivi++) {
            for (int sarake = 0; sarake < this.ruudukko[0].length; sarake++) {

                // Jos rivi + sarake on parillinen, kolmio osoittaa ylospain,
                // muuten alaspain. Kolmion suunnan voi myos laittaa Kolmioiden
                // attribuutiksi.

                // Kauttaviivat havainnollistavat tekstiversiossa
                // kolmioiden suuntaa.
                if ((rivi + sarake) % 2 == 0) {
                    ruudukkoTekstina += "/";
                } else {
                    ruudukkoTekstina += "\\";
                }

                if (this.ruudukko[rivi][sarake].getKolmionVari() == Color.YELLOW) {
                    ruudukkoTekstina += "K";
                } else if (this.ruudukko[rivi][sarake].getKolmionVari() == Color.RED) {
                    ruudukkoTekstina += "P";
                } else if (this.ruudukko[rivi][sarake].getKolmionVari() == Color.BLUE) {
                    ruudukkoTekstina += "S";
                } else {
                    ruudukkoTekstina += "V";
                }

                if ((rivi + sarake) % 2 == 0) {
                    ruudukkoTekstina += "\\ ";
                } else {
                    ruudukkoTekstina += "/ ";
                }

            }
            ruudukkoTekstina += "\n";
        }
        return ruudukkoTekstina;
    }

    public Kolmio[][] getRuudukko() {
        return ruudukko;
    }

    public void poistaKolmiotKohdista(ArrayList<Koordinaatti> tuhoutuvat) {
        for (Koordinaatti koordinaatti : tuhoutuvat) {
            this.ruudukko[koordinaatti.getRivi()][koordinaatti.getSarake()] = null;
        }

    }
}
