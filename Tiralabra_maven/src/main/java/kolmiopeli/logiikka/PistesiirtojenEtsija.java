package kolmiopeli.logiikka;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kolmiopeli.domain.Kolmio;
import kolmiopeli.domain.KolmioPari;
import kolmiopeli.domain.Koordinaatti;
import kolmiopeli.domain.KoordinaattiPari;
import kolmiopeli.domain.Ruudukko;

/**
 * Luokka tutkii peliruudukkoa ja etsii sielta kahden kolmion pareja joita
 * vaihtamalla peliruudukkoon muodostuisi pistecomboja. Luokka tallentaa nama
 * "KoordinaattiParit" listaan josta Siirrot luokka voi tarkistaa tuottaako sen
 * aikoma siirto pisteita. Luokka myos pitaa kirjaa jaljella olevien siirtojen
 * maarasta, josta voi tarkistaa paattyyko peli (peli paattyy kun siirtoja ei
 * enaa ole peliruudukossa).
 *
 * @author eemihauk
 */
public class PistesiirtojenEtsija {

    private KolmioParienSijainnit kolmioparienSijainnit;
    private List[][] kolmioparit;
    private Kolmio[][] ruudukko;
    private List<KoordinaattiPari> sallitutKolmioVaihtoKohdat;
    private int siirtoja;

    /**
     * Luo pistesiirtojen etsijan ja alustaa sen apurakenteet.
     *
     * @param ruudukko Peliruudukko
     */
    public PistesiirtojenEtsija(Ruudukko ruudukko) {

        this.kolmioparienSijainnit = new KolmioParienSijainnit(ruudukko);
        this.kolmioparienSijainnit.tutkiKaikkiMahdollisuudet();
        this.kolmioparit = this.kolmioparienSijainnit.getRuudunMahdollisuudet();
        this.ruudukko = this.kolmioparienSijainnit.getTutkittavaRuudukko();
        this.sallitutKolmioVaihtoKohdat = new ArrayList<KoordinaattiPari>();
    }

    public int getSiirtoja() {
        return siirtoja;
    }

    public int getKolmiopareja() {
        return this.kolmioparienSijainnit.getKolmiopareja();
    }

    /**
     * Etsii kaikki siirrot mitka tuottavat pisteita ja kirjaa niiden kohdat
     * ruudukossa.
     *
     * @return Mahdollisten pistesiirtojen maara
     */
    public int etsiKaikkiPistesiirrot() {
        this.sallitutKolmioVaihtoKohdat.clear();
        this.kolmioparienSijainnit.tutkiKaikkiMahdollisuudet();
        this.kolmioparit = this.kolmioparienSijainnit.getRuudunMahdollisuudet();

        int viimeinenRivi = ruudukko.length - 1;
        int viimeinenSarake = ruudukko[0].length - 1;
        int mahdollisuudet = 0;
        for (int rivi = 0; rivi <= viimeinenRivi; rivi++) {
            for (int sarake = 0; sarake <= viimeinenSarake; sarake++) {
                Kolmio tutkittava = ruudukko[rivi][sarake];
                if (tutkittava.osoittaakoKolmioYlospain()) {
                    mahdollisuudet += ylospainKolmionSiirtomahdollisuudet(rivi, sarake);
                } else {
                    if (tutkittava.getSijaintiSarake() != viimeinenSarake) {
                        mahdollisuudet += tutkiPaikanvaihtoViereisenKolmionKanssa(rivi, sarake);
                    }
                }
            }
        }

        this.siirtoja = mahdollisuudet;
        return mahdollisuudet;

    }

    private int ylospainKolmionSiirtomahdollisuudet(int rivi, int sarake) {
        int viimeinenRivi = ruudukko.length - 1;
        int viimeinenSarake = ruudukko[0].length - 1;
        int mahdollisuudet = 0;

        if (sarake < viimeinenSarake) {
            mahdollisuudet += tutkiPaikanvaihtoViereisenKolmionKanssa(rivi, sarake);
        }
        if (rivi < viimeinenRivi) {
            mahdollisuudet += tutkiPaikanvaihtoAlapuolellaOlevanKolmionKanssa(rivi, sarake);
        }
        
        return mahdollisuudet;
    }

    private int tutkiPaikanvaihtoViereisenKolmionKanssa(int rivi, int sarake) {
        Kolmio tama = this.ruudukko[rivi][sarake];
        Kolmio viereinen = this.ruudukko[rivi][sarake + 1];
        if (tama.getKolmionVari() == viereinen.getKolmionVari()) {
            return 0;
        }
        if (tutkiKolmioidenVaihtaminen(rivi, sarake, viereinen)
                || tutkiKolmioidenVaihtaminen(rivi, sarake + 1, tama)) {
            this.sallitutKolmioVaihtoKohdat.add(new KoordinaattiPari(rivi, sarake, rivi, sarake + 1));
            return 1;
        } else {
            return 0;
        }


    }

    private int tutkiPaikanvaihtoAlapuolellaOlevanKolmionKanssa(int rivi, int sarake) {
        Kolmio tama = this.ruudukko[rivi][sarake];
        Kolmio alapuolella = this.ruudukko[rivi + 1][sarake];
        if (tama.getKolmionVari() == alapuolella.getKolmionVari()) {
            return 0;
        }
        if (tutkiKolmioidenVaihtaminen(rivi, sarake, alapuolella)
                || tutkiKolmioidenVaihtaminen(rivi + 1, sarake, tama)) {
            this.sallitutKolmioVaihtoKohdat.add(new KoordinaattiPari(rivi, sarake, rivi + 1, sarake));
            return 1;
        } else {
            return 0;
        }

    }

    private boolean tutkiKolmioidenVaihtaminen(int rivi, int sarake, Kolmio vaihdettava) {
        List<KolmioPari> ruutuunLiitetytKolmioparit = this.kolmioparit[rivi][sarake];
        boolean loytyikoPistesiirto = false;
        for (KolmioPari kolmioPari : ruutuunLiitetytKolmioparit) {
            // Onko tutkittavan ruudun viereinen kolmio samaa varia kuin tutkittavaan ruutuun liitetty KolmioPari
            if (vaihdettava.getKolmionVari() == kolmioPari.getVari()) {
                // Tarkistetaan ettei viereinen kuulu kolmioPariin
                if (!kolmioPari.kuuluukoKolmioPariin(vaihdettava)) {
                    loytyikoPistesiirto = true;
                }
            }
        }
        return loytyikoPistesiirto;
    }

    /**
     * Tutkii tuottaako kahden annetun kolmion vaihto pisteita.
     *
     * @param siirrettavanRivi
     * @param siirrettavanSarake
     * @param viereisenRivi
     * @param viereisenSarake
     * @return True, jos kahden annetun kolmion vaihto tuottaa pisteita
     */
    public boolean tuottaakoPisteitaJosVaihtaa(int siirrettavanRivi, int siirrettavanSarake, int viereisenRivi, int viereisenSarake) {
        Koordinaatti siirrettava = new Koordinaatti(siirrettavanRivi, siirrettavanSarake);
        Koordinaatti viereinen = new Koordinaatti(viereisenRivi, viereisenSarake);
        for (KoordinaattiPari koordinaattiPari : this.sallitutKolmioVaihtoKohdat) {
            if (koordinaattiPari.getKoordinaatit().contains(siirrettava)
                    && koordinaattiPari.getKoordinaatit().contains(viereinen)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Onnistuneen siirron jalkeen tama metodi kasaa siirretyn kolmion kanssa
     * tuhoutuvat kolmiot joukoksi ja palauttaa ne kutsujalle.
     *
     * @param siirretty Onnistuneen siirron kokenut kolmio.
     * @return Joukkona tuhoutuvien kolmioiden koordinaatit.
     */
    public Set<Koordinaatti> getTamanKolmionKanssaTuhoutuvat(Kolmio siirretty) {
        Set<Koordinaatti> koordinaatit = new HashSet();
        int rivi = siirretty.getSijaintiRivi();
        int sarake = siirretty.getSijaintiSarake();
        List<KolmioPari> ruutuunLiitetytKolmioparit = this.kolmioparit[rivi][sarake];
        // Kerataan tuhoutuvien kolmioiden koordinaatit
        int kolmioparienMaaraRuudussa = ruutuunLiitetytKolmioparit.size();
        List<KolmioPari> poistettavatKolmioparit = new ArrayList();
        for (int i = 0; i < kolmioparienMaaraRuudussa; i++) {
            KolmioPari kolmioPari = ruutuunLiitetytKolmioparit.get(i);
            if (siirretty.getKolmionVari() == kolmioPari.getVari()) {
                if (!kolmioPari.kuuluukoKolmioPariin(siirretty)) {
                    koordinaatit.addAll(kolmioPari.getKoordinaatit());
                    poistettavatKolmioparit.add(kolmioPari);
                    koordinaatit.add(siirretty.getKoordinaatti());
                }
            }
        }
        ruutuunLiitetytKolmioparit.removeAll(poistettavatKolmioparit);
        return koordinaatit;
    }
}
