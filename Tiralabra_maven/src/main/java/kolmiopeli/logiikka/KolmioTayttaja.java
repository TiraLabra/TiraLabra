package kolmiopeli.logiikka;

import java.awt.Color;
import java.util.ArrayList;
import kolmiopeli.domain.Kolmio;
import kolmiopeli.domain.Koordinaatti;
import kolmiopeli.domain.Ruudukko;
import kolmiopeli.domain.Variarpoja;

/**
 * Luokka tayttaa tyhjentyneita ruutuja pelin aikana
 *
 * @author Eemi
 */
public class KolmioTayttaja {

    private Kolmio[][] peliruudukko;
    private Variarpoja variarpoja;
    private KolmionVarinSopivuudenTarkastaja sopivuudenTarkastaja;

    /**
     * Alustaa oliolle peliruudukon, variarpojan ja varitarkastus apurakenteen
     * (sopivuudenTarkastaja).
     *
     * @param peliruudukko Peliruudukko.
     */
    public KolmioTayttaja(Ruudukko peliruudukko) {
        this.peliruudukko = peliruudukko.getRuudukko();
        this.variarpoja = new Variarpoja();
        this.sopivuudenTarkastaja = peliruudukko.getKolmionSopivuudenTarkastaja();
    }

    /**
     * Tayttaa metodille parametrina annetut tyhjat ruudut uusilla kolmioilla
     * JOTKA EIVAT LUO PISTEKOMBOJA VIEREISTEN KANSSA.
     *
     * @param taytettavatRuudut Lista koordinaateista jotka pitaa tayttaa.
     */
    public void taytaTietytRuudut(ArrayList<Koordinaatti> taytettavatRuudut) {

        for (Koordinaatti koordinaatti : taytettavatRuudut) {
            int rivi = koordinaatti.getRivi();
            int sarake = koordinaatti.getSarake();
            int viimeinenRivi = this.peliruudukko.length - 1;
            int viimeinenSarake = this.peliruudukko[0].length - 1;
            Kolmio palautettava = null;

            this.peliruudukko[rivi][sarake] = taytaRuutuunKolmio(rivi, sarake, viimeinenRivi, viimeinenSarake, palautettava);
        }

    }

    public void taytaKaikkiRuudut() {
        int viimeinenRivi = this.peliruudukko.length - 1;
        int viimeinenSarake = this.peliruudukko[0].length - 1;
        for (int rivi = viimeinenRivi; rivi > -1; rivi--) {
            for (int sarake = viimeinenSarake; sarake > -1; sarake--) {
                Kolmio palautettava = null;
                this.peliruudukko[rivi][sarake] = taytaRuutuunKolmio(rivi, sarake, viimeinenRivi, viimeinenSarake, palautettava);
            }
        }
    }

    private Kolmio taytaRuutuunKolmio(int rivi, int sarake, int viimeinenRivi, int viimeinenSarake, Kolmio palautettava) {

        // Mika rajatapaus on kyseessa, viimeisena tapaus ilman rajoituksia
        if (rivi == 0) {
            palautettava = kolmioRiville0(rivi, sarake, viimeinenRivi, viimeinenSarake, palautettava);
        } else if (rivi == viimeinenRivi) {
            palautettava = kolmioRivilleViimeinen(rivi, sarake, viimeinenRivi, viimeinenSarake, palautettava);
        } else {
            palautettava = kolmioJonnekinMuualle(rivi, sarake, viimeinenRivi, viimeinenSarake, palautettava);
        }
        return palautettava;
    }

    private Kolmio kolmioRiville0(int rivi, int sarake, int viimeinenRivi, int viimeinenSarake, Kolmio palautettava) {
        Color vari = this.variarpoja.arvoVari();
        if (osoittaakoRuutuYlospain(rivi, sarake)) {
            palautettava = kolmioRiville0Ylospain(rivi, sarake, viimeinenRivi, viimeinenSarake, palautettava, vari);
        } else {
            palautettava = kolmioRiville0Alaspain(rivi, sarake, viimeinenRivi, viimeinenSarake, palautettava, vari);
        }
        return palautettava;
    }

    private Kolmio kolmioJonnekinMuualle(int rivi, int sarake, int viimeinenRivi, int viimeinenSarake, Kolmio palautettava) {
        Color vari = this.variarpoja.arvoVari();
        if (osoittaakoRuutuYlospain(rivi, sarake)) {
            palautettava = kolmioJonnekinMuualleYlospain(rivi, sarake, viimeinenRivi, viimeinenSarake, palautettava, vari);
        } else {
            palautettava = kolmioJonnekinMuualleAlaspain(rivi, sarake, viimeinenRivi, viimeinenSarake, palautettava, vari);
        }
        return palautettava;
    }

    private Kolmio kolmioRivilleViimeinen(int rivi, int sarake, int viimeinenRivi, int viimeinenSarake, Kolmio palautettava) {
        Color vari = this.variarpoja.arvoVari();
        if (osoittaakoRuutuYlospain(rivi, sarake)) {
            palautettava = kolmioRivilleViimeinenYlospain(rivi, sarake, viimeinenRivi, viimeinenSarake, palautettava, vari);
        } else {
            palautettava = kolmioRivilleViimeinenAlaspain(rivi, sarake, viimeinenRivi, viimeinenSarake, palautettava, vari);
        }
        return palautettava;
    }

    private Kolmio kolmioRiville0Ylospain(int rivi, int sarake, int viimeinenRivi, int viimeinenSarake, Kolmio palautettava, Color vari) {
        int sopiiko = 0;
        if (sarake < viimeinenSarake - 1) {
            sopiiko += this.sopivuudenTarkastaja.kolmioSopiikoOikea(rivi, sarake, vari);
        }
        if (sarake > 1) {
            sopiiko += this.sopivuudenTarkastaja.kolmioSopiikoVasen(rivi, sarake, vari);
        }
        if (sarake > 0) {
            sopiiko += this.sopivuudenTarkastaja.ylospainKolmioSopiikoAlavasen(rivi, sarake, vari);
            sopiiko += this.sopivuudenTarkastaja.ylospainKolmioSopiikoAlavasenViereisiin(rivi, sarake, vari);
        }
        if (sarake < viimeinenSarake) {
            sopiiko += this.sopivuudenTarkastaja.ylospainKolmioSopiikoAlaoikea(rivi, sarake, vari);
            sopiiko += this.sopivuudenTarkastaja.ylospainKolmioSopiikoAlaoikeanViereisiin(rivi, sarake, vari);
        }
        if (sarake != 0 && sarake != viimeinenSarake) {
            sopiiko += this.sopivuudenTarkastaja.kolmioSopiikoViereisiin(rivi, sarake, vari);
        }

        return sopivuusTarkistus(rivi, sarake, viimeinenRivi, viimeinenSarake, palautettava, vari, sopiiko, "0Y");
    }

    private Kolmio kolmioRiville0Alaspain(int rivi, int sarake, int viimeinenRivi, int viimeinenSarake, Kolmio palautettava, Color vari) {
        int sopiiko = 0;
        if (sarake < viimeinenSarake - 1) {
            sopiiko += this.sopivuudenTarkastaja.kolmioSopiikoOikea(rivi, sarake, vari);
        }
        if (sarake > 1) {
            sopiiko += this.sopivuudenTarkastaja.kolmioSopiikoVasen(rivi, sarake, vari);
        }
        if (sarake > 0) {
            sopiiko += this.sopivuudenTarkastaja.alaspainKolmioSopiikoAlavasen(rivi, sarake, vari);
        }
        if (sarake < viimeinenSarake) {
            sopiiko += this.sopivuudenTarkastaja.alaspainKolmioSopiikoAlaoikea(rivi, sarake, vari);
        }
        if (sarake != 0 && sarake != viimeinenSarake) {
            sopiiko += this.sopivuudenTarkastaja.kolmioSopiikoViereisiin(rivi, sarake, vari);
        }

        return sopivuusTarkistus(rivi, sarake, viimeinenRivi, viimeinenSarake, palautettava, vari, sopiiko, "0A");
    }

    private Kolmio kolmioJonnekinMuualleYlospain(int rivi, int sarake, int viimeinenRivi, int viimeinenSarake, Kolmio palautettava, Color vari) {
        int sopiiko = 0;

        if (sarake < viimeinenSarake - 1) {
            sopiiko += this.sopivuudenTarkastaja.kolmioSopiikoOikea(rivi, sarake, vari);
        }
        if (sarake > 1) {
            sopiiko += this.sopivuudenTarkastaja.kolmioSopiikoVasen(rivi, sarake, vari);
        }
        if (sarake > 0) {
            sopiiko += this.sopivuudenTarkastaja.ylospainKolmioSopiikoAlavasen(rivi, sarake, vari);
            sopiiko += this.sopivuudenTarkastaja.ylospainKolmioSopiikoAlavasenViereisiin(rivi, sarake, vari);
            sopiiko += this.sopivuudenTarkastaja.ylospanKolmioSopiikoYlavasen(rivi, sarake, vari);
        }
        if (sarake < viimeinenSarake) {
            sopiiko += this.sopivuudenTarkastaja.ylospainKolmioSopiikoAlaoikea(rivi, sarake, vari);
            sopiiko += this.sopivuudenTarkastaja.ylospainKolmioSopiikoAlaoikeanViereisiin(rivi, sarake, vari);
            sopiiko += this.sopivuudenTarkastaja.ylospainKolmioSopiikoYlaoikea(rivi, sarake, vari);
        }
        if (sarake != 0 && sarake != viimeinenSarake) {
            sopiiko += this.sopivuudenTarkastaja.kolmioSopiikoViereisiin(rivi, sarake, vari);
        }

        return sopivuusTarkistus(rivi, sarake, viimeinenRivi, viimeinenSarake, palautettava, vari, sopiiko, "KeskY");
    }

    private Kolmio kolmioJonnekinMuualleAlaspain(int rivi, int sarake, int viimeinenRivi, int viimeinenSarake, Kolmio palautettava, Color vari) {
        int sopiiko = 0;

        if (sarake < viimeinenSarake - 1) {
            sopiiko += this.sopivuudenTarkastaja.kolmioSopiikoOikea(rivi, sarake, vari);
        }
        if (sarake > 1) {
            sopiiko += this.sopivuudenTarkastaja.kolmioSopiikoVasen(rivi, sarake, vari);
        }
        if (sarake > 0) {
            sopiiko += this.sopivuudenTarkastaja.alaspainKolmioSopiikoYlavasenViereisiin(rivi, sarake, vari);
            sopiiko += this.sopivuudenTarkastaja.alaspainKolmioSopiikoYlavasen(rivi, sarake, vari);
            sopiiko += this.sopivuudenTarkastaja.alaspainKolmioSopiikoAlavasen(rivi, sarake, vari);
        }
        if (sarake < viimeinenSarake) {
            sopiiko += this.sopivuudenTarkastaja.alaspainKolmioSopiikoYlaoikeaViereisiin(rivi, sarake, vari);
            sopiiko += this.sopivuudenTarkastaja.alaspainKolmioSopiikoYlaoikea(rivi, sarake, vari);
            sopiiko += this.sopivuudenTarkastaja.alaspainKolmioSopiikoAlaoikea(rivi, sarake, vari);
        }
        if (sarake != 0 && sarake != viimeinenSarake) {
            sopiiko += this.sopivuudenTarkastaja.kolmioSopiikoViereisiin(rivi, sarake, vari);
        }

        return sopivuusTarkistus(rivi, sarake, viimeinenRivi, viimeinenSarake, palautettava, vari, sopiiko, "KeskA");
    }

    private Kolmio kolmioRivilleViimeinenYlospain(int rivi, int sarake, int viimeinenRivi, int viimeinenSarake, Kolmio palautettava, Color vari) {
        int sopiiko = 0;
        if (sarake < viimeinenSarake - 1) {
            sopiiko += this.sopivuudenTarkastaja.kolmioSopiikoOikea(rivi, sarake, vari);
        }
        if (sarake > 1) {
            sopiiko += this.sopivuudenTarkastaja.kolmioSopiikoVasen(rivi, sarake, vari);
        }
        if (sarake > 0) {
            sopiiko += this.sopivuudenTarkastaja.ylospanKolmioSopiikoYlavasen(rivi, sarake, vari);
        }
        if (sarake < viimeinenSarake) {
            sopiiko += this.sopivuudenTarkastaja.ylospainKolmioSopiikoYlaoikea(rivi, sarake, vari);
        }
        if (sarake != 0 && sarake != viimeinenSarake) {
            sopiiko += this.sopivuudenTarkastaja.kolmioSopiikoViereisiin(rivi, sarake, vari);
        }

        return sopivuusTarkistus(rivi, sarake, viimeinenRivi, viimeinenSarake, palautettava, vari, sopiiko, "ViimY");
    }

    private Kolmio kolmioRivilleViimeinenAlaspain(int rivi, int sarake, int viimeinenRivi, int viimeinenSarake, Kolmio palautettava, Color vari) {
        int sopiiko = 0;
        if (sarake < viimeinenSarake - 1) {
            sopiiko += this.sopivuudenTarkastaja.kolmioSopiikoOikea(rivi, sarake, vari);
        }
        if (sarake > 1) {
            sopiiko += this.sopivuudenTarkastaja.kolmioSopiikoVasen(rivi, sarake, vari);
        }
        if (sarake > 0) {
            sopiiko += this.sopivuudenTarkastaja.alaspainKolmioSopiikoYlavasenViereisiin(rivi, sarake, vari);
            sopiiko += this.sopivuudenTarkastaja.alaspainKolmioSopiikoYlavasen(rivi, sarake, vari);
        }
        if (sarake < viimeinenSarake) {
            sopiiko += this.sopivuudenTarkastaja.alaspainKolmioSopiikoYlaoikeaViereisiin(rivi, sarake, vari);
            sopiiko += this.sopivuudenTarkastaja.alaspainKolmioSopiikoYlaoikea(rivi, sarake, vari);
        }
        if (sarake != 0 && sarake != viimeinenSarake) {
            sopiiko += this.sopivuudenTarkastaja.kolmioSopiikoViereisiin(rivi, sarake, vari);
        }

        return sopivuusTarkistus(rivi, sarake, viimeinenRivi, viimeinenSarake, palautettava, vari, sopiiko, "ViimA");
    }

    private boolean osoittaakoRuutuYlospain(int rivi, int sarake) {
        if ((rivi + sarake) % 2 == 0) {
            return true;
        }
        return false;
    }

    private Kolmio sopivuusTarkistus(int rivi, int sarake, int viimeinenRivi, int viimeinenSarake, Kolmio palautettava, Color vari, int sopiiko, String metodikoodi) {
        if (sopiiko == 0) {
            palautettava = new Kolmio(vari, rivi, sarake);
        } else {
            if (metodikoodi.equals("0Y")) {
                palautettava = kolmioRiville0Ylospain(rivi, sarake, viimeinenRivi, viimeinenSarake, palautettava, this.variarpoja.arvoVariMuuKuin(vari));
            }
            if (metodikoodi.equals("0A")) {
                palautettava = kolmioRiville0Alaspain(rivi, sarake, viimeinenRivi, viimeinenSarake, palautettava, this.variarpoja.arvoVariMuuKuin(vari));
            }
            if (metodikoodi.equals("KeskY")) {
                palautettava = kolmioJonnekinMuualleYlospain(rivi, sarake, viimeinenRivi, viimeinenSarake, palautettava, this.variarpoja.arvoVariMuuKuin(vari));
            }
            if (metodikoodi.equals("KeskA")) {
                palautettava = kolmioJonnekinMuualleAlaspain(rivi, sarake, viimeinenRivi, viimeinenSarake, palautettava, this.variarpoja.arvoVariMuuKuin(vari));
            }
            if (metodikoodi.equals("ViimY")) {
                palautettava = kolmioRivilleViimeinenYlospain(rivi, sarake, viimeinenRivi, viimeinenSarake, palautettava, this.variarpoja.arvoVariMuuKuin(vari));
            }
            if (metodikoodi.equals("ViimA")) {
                palautettava = kolmioRivilleViimeinenAlaspain(rivi, sarake, viimeinenRivi, viimeinenSarake, palautettava, this.variarpoja.arvoVariMuuKuin(vari));
            }
        }
        return palautettava;
    }
}
