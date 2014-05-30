/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package verkko;

import java.util.Arrays;
import util.Lista;

/**
 * Luokka kuvaa verkon solmua jolla on jotkut koordinaatit ja painotettuja
 * naapureita
 *
 * @author Arvoitusmies
 */
public class Solmu {

    /**
     * Naapurit
     */
    private final Lista<Solmu> naapuriSolmut;
    /**
     * Naapureja vastaavat painot
     */
    private final Lista<Double> naapuripainot;
    /**
     * Koordinaatit
     */
    private final Double[] koordinaatit;

    /**
     * Konstruktori...
     *
     * @param koordinaatit
     */
    public Solmu(Double[] koordinaatit) {
        this.koordinaatit = koordinaatit.clone();
        naapuriSolmut = new Lista<>();
        naapuripainot = new Lista<>();
    }

    @Override
    public String toString() {
        return Arrays.toString(koordinaatit);
    }

    /**
     * Lisää solmun painolla paino naapureihin. Huom: ei lisää tätä solmua
     * solmun s naapureihin!
     *
     * @param s lisättävä solmu
     * @param paino
     */
    public void lisaaNaapuri(Solmu s, Double paino) {
        naapuriSolmut.lisaa(s);
        naapuripainot.lisaa(paino);
    }

    /**
     * Kyselee onko annettu solmu naapuri
     *
     * @param s
     * @return
     */
    public Boolean onkoNaapuri(Solmu s) {

        return naapuriSolmut.contains(s);
    }

    /**
     * getteri
     *
     * @return
     */
    public Lista<Solmu> getNaapuriSolmut() {
        return naapuriSolmut;
    }

    /**
     * getteri
     *
     * @return
     */
    public Lista<Double> getNaapuripainot() {
        return naapuripainot;
    }

    /**
     * Kuinka "uloitteinen" solmu voi olla.
     *
     * @return
     */
    public Integer dimensio() {
        return koordinaatit.length;
    }

    /**
     * Koordinaatti ulottuvuudelle i (lasketaan 0:sta).
     *
     * @param i
     * @return
     */
    public Double koordinaatti(Integer i) {
        return koordinaatit[i];
    }

    /**
     * Palauttaa "normi" etäisyyden.
     *
     * @param ks
     * @return
     */
    public Double euklidinenEtaisyys(Solmu ks) {
        int pienempi = koordinaatit.length < ks.dimensio() ? koordinaatit.length : ks.dimensio();
        int erotus = koordinaatit.length - ks.dimensio();
        int kumpi = (int) Math.signum(erotus);
        erotus = Math.abs(erotus);
        Double summa = 0.0;
        for (int i = 0; i < pienempi; i++) {
            summa += Math.pow(koordinaatit[i] - ks.koordinaatti(i), 2);
        }
        for (int i = pienempi; i < pienempi + erotus; i++) {
            if (kumpi == 1) {
                summa += Math.pow(koordinaatit[i], 2);
            } else {
                summa += Math.pow(ks.koordinaatti(i), 2);
            }
        }
        return Math.sqrt(summa);
    }

    /**
     * Etäisyys kulkien vain ns. akseleita pitkin.
     *
     * @param ks toinen koord.solmu
     * @return
     */
    public Double taksimiehenEtaisyys(Solmu ks) {
        Double summa = 0.0;
        int pienempi = koordinaatit.length < ks.dimensio() ? koordinaatit.length : ks.dimensio();
        int erotus = koordinaatit.length - ks.dimensio();
        int kumpi = (int) Math.signum(erotus);
        erotus = Math.abs(erotus);
        for (int i = 0; i < pienempi; i++) {
            summa += Math.abs(koordinaatit[i] - ks.koordinaatti(i));
        }
        for (int i = pienempi; i < pienempi - erotus; i++) {
            if (kumpi == 1) {
                summa += Math.abs(koordinaatit[i]);
            } else {
                summa += Math.abs(ks.koordinaatti(i));
            }
        }
        return summa;
    }

}
