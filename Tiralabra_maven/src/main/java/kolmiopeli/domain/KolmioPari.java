
package kolmiopeli.domain;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kolmiopeli.domain.Kolmio;

/**
 * Kahden samanvarisen lahekkaisen kolmion pari, josta puuttuu kolmas kolmio joka tuhoisi kolmiot ja antaisi pisteita.
 * @author Eemi
 */
public class KolmioPari {
    private Kolmio kolmio1;
    private Kolmio kolmio2;

    /**
     * Luo kahden kolmion KolmioParin.
     * @param kolmio1
     * @param kolmio2 
     */
    public KolmioPari(Kolmio kolmio1, Kolmio kolmio2) {
        this.kolmio1 = kolmio1;
        this.kolmio2 = kolmio2;
    }

    public Kolmio getKolmio1() {
        return kolmio1;
    }

    public Kolmio getKolmio2() {
        return kolmio2;
    }
    
    public Color getVari() {
        return kolmio1.getKolmionVari();
    }
    
    /**
     * Tutkii onko paratrina annettu kolmio osa tata KolmioParia
     * @param kolmio Tutkittava kolmio
     * @return True, jos kolmio on jompikumpi kyseisen KolmioParin kolmioista
     */
    public boolean kuuluukoKolmioPariin(Kolmio kolmio) {
        if (kolmio == this.kolmio1 || kolmio == this.kolmio2) {
            return true;
        }
        return false;
    }

    public List<Koordinaatti> getKoordinaatit() {
        ArrayList<Koordinaatti> kolmioidenKoordinaatit = new ArrayList<Koordinaatti>();
        kolmioidenKoordinaatit.add(new Koordinaatti(this.kolmio1.getSijaintiRivi(), this.kolmio1.getSijaintiSarake()));
        kolmioidenKoordinaatit.add(new Koordinaatti(this.kolmio2.getSijaintiRivi(), this.kolmio2.getSijaintiSarake()));
        return kolmioidenKoordinaatit;
    }
    
    
}
