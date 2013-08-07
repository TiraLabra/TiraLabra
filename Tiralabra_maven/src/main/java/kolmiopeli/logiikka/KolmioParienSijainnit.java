package kolmiopeli.logiikka;

import kolmiopeli.domain.KolmioPari;
import java.util.ArrayList;
import java.util.List;
import kolmiopeli.domain.Kolmio;
import kolmiopeli.domain.Ruudukko;

    /**
     * Luokkan olio skannaa peliruudukon ja tallentaa siita tietoja kohdista joissa on mahdollista saada pisteita yhdella siirrolla
     * 
     */
public class KolmioParienSijainnit {

    private List[][] ruudunMahdollisuudet;
    private Kolmio[][] tutkittavaRuudukko;
    private int kolmiopareja;
    
    /**
     * Luo olion ja alustaa ruudunMahdollisuudet-taulukon.
     * @param tutkittavaRuudukko 
     */
    public KolmioParienSijainnit(Ruudukko tutkittavaRuudukko) {
        this.tutkittavaRuudukko = tutkittavaRuudukko.getRuudukko();
        this.ruudunMahdollisuudet = new List[this.tutkittavaRuudukko.length][this.tutkittavaRuudukko[0].length];
        for (int i = 0; i < this.ruudunMahdollisuudet.length; i++) {
            for (int j = 0; j < this.ruudunMahdollisuudet[0].length; j++) {
                this.ruudunMahdollisuudet[i][j] = new ArrayList<KolmioPari>();
            }
        }
        this.kolmiopareja = 0;
    }

    public int getKolmiopareja() {
        return kolmiopareja;
    }

    public List[][] getRuudunMahdollisuudet() {
        return ruudunMahdollisuudet;
    }

    public Kolmio[][] getTutkittavaRuudukko() {
        return tutkittavaRuudukko;
    }
    
    
    /**
     * 
     * Skannaa peliruudukon ja etsii sielta kolmioparit.
     * @see dokumentointi\liitteet\RuudukonSkannauksenVisualisointia
     */
     
    public void tutkiKaikkiMahdollisuudet() {
        tyhjennaRuudunMahdollisuudetListat();
        int parienMaara = 0;
        int viimeinenRivi = this.tutkittavaRuudukko.length - 1;
        int viimeinenSarake = this.tutkittavaRuudukko[0].length - 1;
        for (int rivi = 0; rivi < this.tutkittavaRuudukko.length; rivi++) {
            for (int sarake = 0; sarake < this.tutkittavaRuudukko[0].length; sarake++) {
                Kolmio tutkittavaKolmio = this.tutkittavaRuudukko[rivi][sarake];

                // Kolmio oikeassa alanurkassa
                if (rivi == viimeinenRivi && sarake == viimeinenSarake) {
                    break;
                }
                
                // Kolmio melkein oikeassa alanurkassa
                else if (rivi == viimeinenRivi && sarake == viimeinenSarake - 1) {
                    parienMaara += kolmioMelkeinOikeassaAlanurkassa(rivi, sarake, tutkittavaKolmio);
                } 
                
                // Kolmio alarivilla
                else if (rivi == viimeinenRivi) {
                    parienMaara += kolmioAlarivilla(rivi, sarake, tutkittavaKolmio);
                } 
                
                // Kolmio oikeassa reunassa
                else if (sarake == viimeinenSarake ) {
                    parienMaara += kolmioOikeassaReunassa(rivi, sarake, tutkittavaKolmio);
                } 
                
                // Kolmio melkein oikeassa reunassa
                else if (sarake == viimeinenSarake - 1) {
                    parienMaara += kolmioMelkeinOikeassaReunassa(rivi, sarake, tutkittavaKolmio);
                } 
                
                // Kolmio on vasemmassa reunassa
                else if (sarake == 0) {
                    parienMaara += kolmioVasemmassaReunassa(rivi, sarake, tutkittavaKolmio);
                } 
                
                // Kolmio jossain missa se tarkistaa kaikki mahdollisuudet
                else {
                    parienMaara += kolmioJossainKeskella(rivi, sarake, tutkittavaKolmio);
                }

            }
        }
        this.kolmiopareja = parienMaara;
    }

    private int kolmioMelkeinOikeassaAlanurkassa(int rivi, int sarake, Kolmio tutkittavaKolmio) {
        int parienMaara = 0;
        if (tutkittavaKolmio.osoittaakoKolmioYlospain()) {
            parienMaara += ylospainOsoittavanMahdollisuudetMelkeinOikeaAlanurkka(rivi, sarake, tutkittavaKolmio);
        } else {
            parienMaara += alaspainOsoittavanMahdollisuudetMelkeinOikeaAlanurkka(rivi, sarake, tutkittavaKolmio);
        }
        return parienMaara;
    }

    private int kolmioAlarivilla(int rivi, int sarake, Kolmio tutkittavaKolmio) {
        int parienMaara = 0;
        if (tutkittavaKolmio.osoittaakoKolmioYlospain()) {
            parienMaara += ylospainOsoittavanMahdollisuudetAlarivilla(rivi, sarake, tutkittavaKolmio);
        } else {
            parienMaara += alaspainOsoittavanMahdollisuudetAlarivilla(rivi, sarake, tutkittavaKolmio);
        }
        return parienMaara;
    }

    private int kolmioOikeassaReunassa(int rivi, int sarake, Kolmio tutkittavaKolmio) {
        int parienMaara = 0;
        if (tutkittavaKolmio.osoittaakoKolmioYlospain()) {
            parienMaara += ylospainOsoittavanMahdollisuudetOikeaReuna(rivi, sarake, tutkittavaKolmio);
        } else {
            parienMaara += alaspainOsoittavanMahdollisuudetOikeaReuna(rivi, sarake, tutkittavaKolmio);
        }
        return parienMaara;
    }

    private int kolmioMelkeinOikeassaReunassa(int rivi, int sarake, Kolmio tutkittavaKolmio) {
        int parienMaara = 0;
        if (tutkittavaKolmio.osoittaakoKolmioYlospain()) {
            parienMaara += ylospainOsoittavanMahdollisuudetMelkeinOikeaReuna(rivi, sarake, tutkittavaKolmio);
        } else {
            parienMaara += alaspainOsoittavanMahdollisuudetMelkeinOikeaReuna(rivi, sarake, tutkittavaKolmio);
        }
        return parienMaara;
    }

    private int kolmioVasemmassaReunassa(int rivi, int sarake, Kolmio tutkittavaKolmio) {
        int parienMaara = 0;
        if (tutkittavaKolmio.osoittaakoKolmioYlospain()) {
            parienMaara += ylospainOsoittavanMahdollisuudetVasenReuna(rivi, sarake, tutkittavaKolmio);
        } else {
            parienMaara += alaspainOsoittavanMahdollisuudetVasenReuna(rivi, sarake, tutkittavaKolmio);
        }
        return parienMaara;
    }

    private int kolmioJossainKeskella(int rivi, int sarake, Kolmio tutkittavaKolmio) {
        int parienMaara = 0;
        if (tutkittavaKolmio.osoittaakoKolmioYlospain()) {
            parienMaara += ylospainOsoittavanMahdollisuudet(rivi, sarake, tutkittavaKolmio);
        } else {
            parienMaara += alaspainOsoittavanMahdollisuudet(rivi, sarake, tutkittavaKolmio);
        }
        return parienMaara;
    }

    private int ylospainOsoittavanMahdollisuudet(int rivi, int sarake, Kolmio tutkittavaKolmio) {
        int parienMaara = 0;
        parienMaara += tutkiYlospainOsHetiViereinen(rivi, sarake, tutkittavaKolmio);
        parienMaara += tutkiSitaseuraavaViereinen(rivi, sarake, tutkittavaKolmio);
        parienMaara += tutkiYlospainOsAlavasemmalla(rivi, sarake, tutkittavaKolmio);
        parienMaara += tutkiYlospainOsAlakeskella(rivi, sarake, tutkittavaKolmio);
        parienMaara += tutkiYlospainOsAlaoikealla(rivi, sarake, tutkittavaKolmio);
        return parienMaara;
    }

    private int ylospainOsoittavanMahdollisuudetMelkeinOikeaAlanurkka(int rivi, int sarake, Kolmio tutkittavaKolmio) {
        int parienMaara = 0;
        parienMaara += tutkiYlospainOsHetiViereinen(rivi, sarake, tutkittavaKolmio);
        return parienMaara;
    }

    private int ylospainOsoittavanMahdollisuudetAlarivilla(int rivi, int sarake, Kolmio tutkittavaKolmio) {
        int parienMaara = 0;
        parienMaara += tutkiYlospainOsHetiViereinen(rivi, sarake, tutkittavaKolmio);
        parienMaara += tutkiSitaseuraavaViereinen(rivi, sarake, tutkittavaKolmio);
        return parienMaara;
    }

    private int ylospainOsoittavanMahdollisuudetMelkeinOikeaReuna(int rivi, int sarake, Kolmio tutkittavaKolmio) {
        int parienMaara = 0;
        parienMaara += tutkiYlospainOsHetiViereinen(rivi, sarake, tutkittavaKolmio);
        parienMaara += tutkiYlospainOsAlavasemmalla(rivi, sarake, tutkittavaKolmio);
        parienMaara += tutkiYlospainOsAlakeskella(rivi, sarake, tutkittavaKolmio);
        parienMaara += tutkiYlospainOsAlaoikealla(rivi, sarake, tutkittavaKolmio);
        return parienMaara;
    }

    private int ylospainOsoittavanMahdollisuudetOikeaReuna(int rivi, int sarake, Kolmio tutkittavaKolmio) {
        int parienMaara = 0;
        parienMaara += tutkiYlospainOsAlavasemmalla(rivi, sarake, tutkittavaKolmio);
        parienMaara += tutkiYlospainOsAlakeskella(rivi, sarake, tutkittavaKolmio);
        return parienMaara;
    }

    private int ylospainOsoittavanMahdollisuudetVasenReuna(int rivi, int sarake, Kolmio tutkittavaKolmio) {
        int parienMaara = 0;
        parienMaara += tutkiYlospainOsHetiViereinen(rivi, sarake, tutkittavaKolmio);
        parienMaara += tutkiSitaseuraavaViereinen(rivi, sarake, tutkittavaKolmio);
        parienMaara += tutkiYlospainOsAlakeskella(rivi, sarake, tutkittavaKolmio);
        parienMaara += tutkiYlospainOsAlaoikealla(rivi, sarake, tutkittavaKolmio);
        return parienMaara;
    }

    private int alaspainOsoittavanMahdollisuudet(int rivi, int sarake, Kolmio tutkittavaKolmio) {
        int parienMaara = 0;
        parienMaara += tutkiAlaspainOsHetiViereinen(rivi, sarake, tutkittavaKolmio);
        parienMaara += tutkiSitaseuraavaViereinen(rivi, sarake, tutkittavaKolmio);
        parienMaara += tutkiAlaspainOsAlavasemmalla(rivi, sarake, tutkittavaKolmio);
        parienMaara += tutkiAlaspainOsAlaoikealla(rivi, sarake, tutkittavaKolmio);
        return parienMaara;
    }

    private int alaspainOsoittavanMahdollisuudetMelkeinOikeaAlanurkka(int rivi, int sarake, Kolmio tutkittavaKolmio) {
        int parienMaara = 0;
        parienMaara += tutkiAlaspainOsHetiViereinen(rivi, sarake, tutkittavaKolmio);
        return parienMaara;
    }

    private int alaspainOsoittavanMahdollisuudetAlarivilla(int rivi, int sarake, Kolmio tutkittavaKolmio) {
        int parienMaara = 0;
        parienMaara += tutkiAlaspainOsHetiViereinen(rivi, sarake, tutkittavaKolmio);
        parienMaara += tutkiSitaseuraavaViereinen(rivi, sarake, tutkittavaKolmio);
        return parienMaara;
    }

    private int alaspainOsoittavanMahdollisuudetMelkeinOikeaReuna(int rivi, int sarake, Kolmio tutkittavaKolmio) {
        int parienMaara = 0;
        parienMaara += tutkiAlaspainOsHetiViereinen(rivi, sarake, tutkittavaKolmio);
        parienMaara += tutkiAlaspainOsAlavasemmalla(rivi, sarake, tutkittavaKolmio);
        parienMaara += tutkiAlaspainOsAlaoikealla(rivi, sarake, tutkittavaKolmio);
        return parienMaara;
    }

    private int alaspainOsoittavanMahdollisuudetOikeaReuna(int rivi, int sarake, Kolmio tutkittavaKolmio) {
        int parienMaara = 0;
        parienMaara += tutkiAlaspainOsAlavasemmalla(rivi, sarake, tutkittavaKolmio);
        return parienMaara;
    }

    private int alaspainOsoittavanMahdollisuudetVasenReuna(int rivi, int sarake, Kolmio tutkittavaKolmio) {
        int parienMaara = 0;
        parienMaara += tutkiAlaspainOsHetiViereinen(rivi, sarake, tutkittavaKolmio);
        parienMaara += tutkiSitaseuraavaViereinen(rivi, sarake, tutkittavaKolmio);
        parienMaara += tutkiAlaspainOsAlaoikealla(rivi, sarake, tutkittavaKolmio);
        return parienMaara;
    }

    private int tutkiYlospainOsHetiViereinen(int rivi, int sarake, Kolmio tutkittavaKolmio) {
        Kolmio viereinen = this.tutkittavaRuudukko[rivi][sarake + 1];
        if (viereinen.getKolmionVari() == tutkittavaKolmio.getKolmionVari()) {
            KolmioPari uusiLoydetty = new KolmioPari(tutkittavaKolmio, viereinen);
            if (sarake != 0) {
                this.ruudunMahdollisuudet[rivi][sarake - 1].add(uusiLoydetty);
            }
            if (rivi != 0) {
                this.ruudunMahdollisuudet[rivi - 1][sarake + 1].add(uusiLoydetty);
            }
            if (rivi != this.tutkittavaRuudukko.length - 1) {
                this.ruudunMahdollisuudet[rivi + 1][sarake].add(uusiLoydetty);
            }
            if (sarake != this.tutkittavaRuudukko[0].length - 2) {
                this.ruudunMahdollisuudet[rivi][sarake + 2].add(uusiLoydetty);
            }
            return 1;
        }
        return 0;
    }

    private int tutkiSitaseuraavaViereinen(int rivi, int sarake, Kolmio tutkittavaKolmio) {
        Kolmio viereinen = this.tutkittavaRuudukko[rivi][sarake + 2];
        if (viereinen.getKolmionVari() == tutkittavaKolmio.getKolmionVari()) {
            KolmioPari uusiLoydetty = new KolmioPari(tutkittavaKolmio, viereinen);
            this.ruudunMahdollisuudet[rivi][sarake + 1].add(uusiLoydetty);
            return 1;
        }
        return 0;
    }

    private int tutkiYlospainOsAlavasemmalla(int rivi, int sarake, Kolmio tutkittavaKolmio) {
        Kolmio viereinen = this.tutkittavaRuudukko[rivi + 1][sarake - 1];
        if (viereinen.getKolmionVari() == tutkittavaKolmio.getKolmionVari()) {
            KolmioPari uusiLoydetty = new KolmioPari(tutkittavaKolmio, viereinen);
            this.ruudunMahdollisuudet[rivi + 1][sarake].add(uusiLoydetty);
            return 1;
        }
        return 0;
    }

    private int tutkiYlospainOsAlakeskella(int rivi, int sarake, Kolmio tutkittavaKolmio) {
        Kolmio viereinen = this.tutkittavaRuudukko[rivi + 1][sarake];
        if (viereinen.getKolmionVari() == tutkittavaKolmio.getKolmionVari()) {
            KolmioPari uusiLoydetty = new KolmioPari(tutkittavaKolmio, viereinen);
            if (sarake != 0) {
                this.ruudunMahdollisuudet[rivi][sarake - 1].add(uusiLoydetty);
                this.ruudunMahdollisuudet[rivi + 1][sarake - 1].add(uusiLoydetty);
            }
            if (sarake != this.tutkittavaRuudukko[0].length - 1) {
                this.ruudunMahdollisuudet[rivi][sarake + 1].add(uusiLoydetty);
                this.ruudunMahdollisuudet[rivi + 1][sarake + 1].add(uusiLoydetty);
            }
            return 1;
        }
        return 0;
    }

    private int tutkiYlospainOsAlaoikealla(int rivi, int sarake, Kolmio tutkittavaKolmio) {
        Kolmio viereinen = this.tutkittavaRuudukko[rivi + 1][sarake + 1];
        if (viereinen.getKolmionVari() == tutkittavaKolmio.getKolmionVari()) {
            KolmioPari uusiLoydetty = new KolmioPari(tutkittavaKolmio, viereinen);
            this.ruudunMahdollisuudet[rivi + 1][sarake].add(uusiLoydetty);
            return 1;
        }
        return 0;
    }

    private int tutkiAlaspainOsHetiViereinen(int rivi, int sarake, Kolmio tutkittavaKolmio) {
        Kolmio viereinen = this.tutkittavaRuudukko[rivi][sarake + 1];
        if (viereinen.getKolmionVari() == tutkittavaKolmio.getKolmionVari()) {
            KolmioPari uusiLoydetty = new KolmioPari(tutkittavaKolmio, viereinen);
            if (sarake != 0) {
                this.ruudunMahdollisuudet[rivi][sarake - 1].add(uusiLoydetty);
            }
            if (rivi != 0) {
                this.ruudunMahdollisuudet[rivi - 1][sarake].add(uusiLoydetty);
            }
            if (rivi != this.tutkittavaRuudukko.length - 1) {
                this.ruudunMahdollisuudet[rivi + 1][sarake + 1].add(uusiLoydetty);
            }
            if (sarake != this.tutkittavaRuudukko[0].length - 2) {
                this.ruudunMahdollisuudet[rivi][sarake + 2].add(uusiLoydetty);
            }
            return 1;
        }
        return 0;
    }

    private int tutkiAlaspainOsAlavasemmalla(int rivi, int sarake, Kolmio tutkittavaKolmio) {
        Kolmio viereinen = this.tutkittavaRuudukko[rivi + 1][sarake - 1];
        if (viereinen.getKolmionVari() == tutkittavaKolmio.getKolmionVari()) {
            KolmioPari uusiLoydetty = new KolmioPari(tutkittavaKolmio, viereinen);
            this.ruudunMahdollisuudet[rivi][sarake - 1].add(uusiLoydetty);
            return 1;
        }
        return 0;
    }

    private int tutkiAlaspainOsAlaoikealla(int rivi, int sarake, Kolmio tutkittavaKolmio) {
        Kolmio viereinen = this.tutkittavaRuudukko[rivi + 1][sarake + 1];
        if (viereinen.getKolmionVari() == tutkittavaKolmio.getKolmionVari()) {
            KolmioPari uusiLoydetty = new KolmioPari(tutkittavaKolmio, viereinen);
            this.ruudunMahdollisuudet[rivi][sarake + 1].add(uusiLoydetty);
            return 1;
        }
        return 0;
    }

    private void tyhjennaRuudunMahdollisuudetListat() {
        for (int i = 0; i < this.ruudunMahdollisuudet.length; i++) {
            for (int j = 0; j < this.ruudunMahdollisuudet[0].length; j++) {
                this.ruudunMahdollisuudet[i][j].clear();
            }
        }
    }
}
