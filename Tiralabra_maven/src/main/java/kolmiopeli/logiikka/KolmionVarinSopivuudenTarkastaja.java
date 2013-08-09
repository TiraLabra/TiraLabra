package kolmiopeli.logiikka;

import java.awt.Color;
import kolmiopeli.domain.Kolmio;
import kolmiopeli.domain.Ruudukko;

/**
 * Luokka jota sovelletaan peliruudukkoa tayttavissa luokissa tutkimaan
 * tutkittavan kolmion laheisten kolmioiden vareja
 *
 * @author Eemi
 * @see dokumentointi\liitteet\RuudukonSkannauksenVisualisointia
 */
public class KolmionVarinSopivuudenTarkastaja {

    private Kolmio[][] peliruudukko;

    /**
     * Luo olion ja liittaa siihen tutkittavan peliruudukon
     *
     * @param peliruudukko Peliruudukko
     */
    public KolmionVarinSopivuudenTarkastaja(Ruudukko peliruudukko) {
        this.peliruudukko = peliruudukko.getRuudukko();
    }

    public void setPeliruudukko(Kolmio[][] peliruudukko) {
        this.peliruudukko = peliruudukko;
    }

    
    
    /**
     * Onko sarake+1 ja sarake+2 samaa varia kuin parametri
     *
     * @param rivi Tutkittavan ruudun rivi
     * @param sarake Tutkittavan ruudun sarake
     * @param vari Tutkittavan ruudun potentiaalinen vari
     * @return 1, jos parametrina annettu vari ei sovi tutkittavaan ruutuun,
     * muuten 0
     */
    public int kolmioSopiikoOikea(int rivi, int sarake, Color vari) {
        if (this.peliruudukko[rivi][sarake + 1].getKolmionVari() == vari
                && this.peliruudukko[rivi][sarake + 2].getKolmionVari() == vari) {
            return 1;
        }
        return 0;
    }

    /**
     * Sopiiko annettu vari alaoikean suunnan kolmioiden vareihin
     *
     * @param rivi Tutkittavan ruudun rivi
     * @param sarake Tutkittavan ruudun sarake
     * @param vari Tutkittavan ruudun potentiaalinen vari
     * @return 1, jos parametrina annettu vari ei sovi tutkittavaan ruutuun,
     * muuten 0
     */
    public int alaspainKolmioSopiikoAlaoikea(int rivi, int sarake, Color vari) {
        if (this.peliruudukko[rivi][sarake + 1].getKolmionVari() == vari
                && this.peliruudukko[rivi + 1][sarake + 1].getKolmionVari() == vari) {
            return 1;
        }
        return 0;
    }

    /**
     * Sopiiko annettu vari viereisen ja alla olevan kolmion varin kanssa
     * @param rivi Tutkittavan ruudun rivi
     * @param sarake Tutkittavan ruudun sarake
     * @param vari Tutkittavan ruudun potentiaalinen vari
     * @return 1, jos parametrina annettu vari ei sovi tutkittavaan ruutuun,
     * muuten 0
     */
    public int ylospainKolmioSopiikoAlaoikeanViereisiin(int rivi, int sarake, Color vari) {
        if (this.peliruudukko[rivi][sarake + 1].getKolmionVari() == vari
                && this.peliruudukko[rivi + 1][sarake].getKolmionVari() == vari) {
            return 1;
        }
        return 0;
    }

    /**
     * Sopiiko annettu vari alaoikealla olevien kolmioiden varien kanssa
     * @param rivi Tutkittavan ruudun rivi
     * @param sarake Tutkittavan ruudun sarake
     * @param vari Tutkittavan ruudun potentiaalinen vari
     * @return 1, jos parametrina annettu vari ei sovi tutkittavaan ruutuun,
     * muuten 0
     */
    public int ylospainKolmioSopiikoAlaoikea(int rivi, int sarake, Color vari) {
        if (this.peliruudukko[rivi + 1][sarake].getKolmionVari() == vari
                && this.peliruudukko[rivi + 1][sarake + 1].getKolmionVari() == vari) {
            return 1;
        }
        return 0;
    }

    /**
     * Sopiiko annettu vari alavasemmalla olevien kolmioiden varien kanssa
     * @param rivi Tutkittavan ruudun rivi
     * @param sarake Tutkittavan ruudun sarake
     * @param vari Tutkittavan ruudun potentiaalinen vari
     * @return 1, jos parametrina annettu vari ei sovi tutkittavaan ruutuun,
     * muuten 0
     */
    public int ylospainKolmioSopiikoAlavasen(int rivi, int sarake, Color vari) {
        if (this.peliruudukko[rivi + 1][sarake].getKolmionVari() == vari
                && this.peliruudukko[rivi + 1][sarake - 1].getKolmionVari() == vari) {
            return 1;
        }
        return 0;
    }

    /**
     * Sopiiko annettu vari vasemmalla olevien kolmioiden varien kanssa
     * @param rivi Tutkittavan ruudun rivi
     * @param sarake Tutkittavan ruudun sarake
     * @param vari Tutkittavan ruudun potentiaalinen vari
     * @return 1, jos parametrina annettu vari ei sovi tutkittavaan ruutuun,
     * muuten 0
     */
    public int kolmioSopiikoVasen(int rivi, int sarake, Color vari) {
        if (this.peliruudukko[rivi][sarake - 1] == null
                || this.peliruudukko[rivi][sarake - 2] == null) {
            return 0;
        }
        if (this.peliruudukko[rivi][sarake - 1].getKolmionVari() == vari
                && this.peliruudukko[rivi][sarake - 2].getKolmionVari() == vari) {
            return 1;
        }
        return 0;
    }

    /**
     * Sopiiko annettu vari vasemman viereisen ja alapuolella olevan kolmion varin kanssa
     * @param rivi Tutkittavan ruudun rivi
     * @param sarake Tutkittavan ruudun sarake
     * @param vari Tutkittavan ruudun potentiaalinen vari
     * @return 1, jos parametrina annettu vari ei sovi tutkittavaan ruutuun,
     * muuten 0
     */
    public int ylospainKolmioSopiikoAlavasenViereisiin(int rivi, int sarake, Color vari) {
        if (this.peliruudukko[rivi][sarake - 1] == null
                || this.peliruudukko[rivi + 1][sarake] == null) {
            return 0;
        }
        if (this.peliruudukko[rivi][sarake - 1].getKolmionVari() == vari
                && this.peliruudukko[rivi + 1][sarake].getKolmionVari() == vari) {
            return 1;
        }
        return 0;
    }

    /**
     * Sopiiko annettu vari oikeanpuolisen ja vasemmanpuolisen kolmion varin kanssa
     * @param rivi Tutkittavan ruudun rivi
     * @param sarake Tutkittavan ruudun sarake
     * @param vari Tutkittavan ruudun potentiaalinen vari
     * @return 1, jos parametrina annettu vari ei sovi tutkittavaan ruutuun,
     * muuten 0
     */
    public int kolmioSopiikoViereisiin(int rivi, int sarake, Color vari) {
        if (this.peliruudukko[rivi][sarake - 1] == null
                || this.peliruudukko[rivi][sarake + 1] == null) {
            return 0;
        }
        if (this.peliruudukko[rivi][sarake - 1].getKolmionVari() == vari
                && this.peliruudukko[rivi][sarake + 1].getKolmionVari() == vari) {
            return 1;
        }
        return 0;
    }

    /**
     * Sopiiko annettu vari ylavasemmalla olevien kolmioiden varien kanssa
     * @param rivi Tutkittavan ruudun rivi
     * @param sarake Tutkittavan ruudun sarake
     * @param vari Tutkittavan ruudun potentiaalinen vari
     * @return 1, jos parametrina annettu vari ei sovi tutkittavaan ruutuun,
     * muuten 0
     */
    public int ylospanKolmioSopiikoYlavasen(int rivi, int sarake, Color vari) {
        if (this.peliruudukko[rivi][sarake - 1] == null
                || this.peliruudukko[rivi - 1][sarake - 1] == null) {
            return 0;
        }
        if (this.peliruudukko[rivi][sarake - 1].getKolmionVari() == vari
                && this.peliruudukko[rivi - 1][sarake - 1].getKolmionVari() == vari) {
            return 1;
        }
        return 0;
    }

    /**
     * Sopiiko annettu vari sopiiko vari ylaoikealla olevien kolmioiden varien kanssa
     * @param rivi Tutkittavan ruudun rivi
     * @param sarake Tutkittavan ruudun sarake
     * @param vari Tutkittavan ruudun potentiaalinen vari
     * @return 1, jos parametrina annettu vari ei sovi tutkittavaan ruutuun,
     * muuten 0
     */
    public int ylospainKolmioSopiikoYlaoikea(int rivi, int sarake, Color vari) {
        if (this.peliruudukko[rivi][sarake + 1] == null
                || this.peliruudukko[rivi - 1][sarake + 1] == null) {
            return 0;
        }
        if (this.peliruudukko[rivi][sarake + 1].getKolmionVari() == vari
                && this.peliruudukko[rivi - 1][sarake + 1].getKolmionVari() == vari) {
            return 1;
        }
        return 0;
    }

    /**
     * Sopiiko annettu vari vasemman ja ylapuolisen kolmion varin kanssa
     * @param rivi Tutkittavan ruudun rivi
     * @param sarake Tutkittavan ruudun sarake
     * @param vari Tutkittavan ruudun potentiaalinen vari
     * @return 1, jos parametrina annettu vari ei sovi tutkittavaan ruutuun,
     * muuten 0
     */
    public int alaspainKolmioSopiikoYlavasenViereisiin(int rivi, int sarake, Color vari) {
        if (this.peliruudukko[rivi][sarake - 1] == null
                || this.peliruudukko[rivi - 1][sarake] == null) {
            return 0;
        }
        if (this.peliruudukko[rivi][sarake - 1].getKolmionVari() == vari
                && this.peliruudukko[rivi - 1][sarake].getKolmionVari() == vari) {
            return 1;
        }
        return 0;
    }

    /**
     * Sopiiko annettu vari oikeanpuolisen ja ylapuolisen kolmion varin kanssa
     * @param rivi Tutkittavan ruudun rivi
     * @param sarake Tutkittavan ruudun sarake
     * @param vari Tutkittavan ruudun potentiaalinen vari
     * @return 1, jos parametrina annettu vari ei sovi tutkittavaan ruutuun,
     * muuten 0
     */
    public int alaspainKolmioSopiikoYlaoikeaViereisiin(int rivi, int sarake, Color vari) {
        if (this.peliruudukko[rivi][sarake + 1] == null
                || this.peliruudukko[rivi - 1][sarake] == null) {
            return 0;
        }
        if (this.peliruudukko[rivi][sarake + 1].getKolmionVari() == vari
                && this.peliruudukko[rivi - 1][sarake].getKolmionVari() == vari) {
            return 1;
        }
        return 0;
    }

    /**
     * Sopiiko annettu vari alavasemmalla olevien kolmioiden varien kanssa
     * @param rivi Tutkittavan ruudun rivi
     * @param sarake Tutkittavan ruudun sarake
     * @param vari Tutkittavan ruudun potentiaalinen vari
     * @return 1, jos parametrina annettu vari ei sovi tutkittavaan ruutuun,
     * muuten 0
     */
    public int alaspainKolmioSopiikoAlavasen(int rivi, int sarake, Color vari) {
        if (this.peliruudukko[rivi][sarake - 1] == null
                || this.peliruudukko[rivi + 1][sarake - 1] == null) {
            return 0;
        }
        if (this.peliruudukko[rivi][sarake - 1].getKolmionVari() == vari
                && this.peliruudukko[rivi + 1][sarake - 1].getKolmionVari() == vari) {
            return 1;
        }
        return 0;
    }

    /**
     * Sopiiko annettu vari ylavasemmalla olevien kolmioiden varin kanssa
     * @param rivi Tutkittavan ruudun rivi
     * @param sarake Tutkittavan ruudun sarake
     * @param vari Tutkittavan ruudun potentiaalinen vari
     * @return 1, jos parametrina annettu vari ei sovi tutkittavaan ruutuun,
     * muuten 0
     */
    public int alaspainKolmioSopiikoYlavasen(int rivi, int sarake, Color vari) {
        if (this.peliruudukko[rivi - 1][sarake] == null
                || this.peliruudukko[rivi - 1][sarake - 1] == null) {
            return 0;
        }
        if (this.peliruudukko[rivi - 1][sarake].getKolmionVari() == vari
                && this.peliruudukko[rivi - 1][sarake - 1].getKolmionVari() == vari) {
            return 1;
        }
        return 0;
    }

    /**
     * Sopiiko annettu vari ylaoikealla olevien kolmioiden varien kanssa
     * @param rivi Tutkittavan ruudun rivi
     * @param sarake Tutkittavan ruudun sarake
     * @param vari Tutkittavan ruudun potentiaalinen vari
     * @return 1, jos parametrina annettu vari ei sovi tutkittavaan ruutuun,
     * muuten 0
     */
    public int alaspainKolmioSopiikoYlaoikea(int rivi, int sarake, Color vari) {
        if (this.peliruudukko[rivi - 1][sarake] == null
                || this.peliruudukko[rivi - 1][sarake + 1] == null) {
            return 0;
        }
        if (this.peliruudukko[rivi - 1][sarake].getKolmionVari() == vari
                && this.peliruudukko[rivi - 1][sarake + 1].getKolmionVari() == vari) {
            return 1;
        }
        return 0;
    }
}
