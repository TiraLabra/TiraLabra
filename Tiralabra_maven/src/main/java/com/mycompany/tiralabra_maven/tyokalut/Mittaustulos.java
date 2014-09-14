package com.mycompany.tiralabra_maven.tyokalut;

/**
 * Luokka johon talletetaan mitattuja aikoja.
 *
 * @author Markus
 */
public class Mittaustulos {

    private long pienin, suurin, summa;
    private int lkm;
    private String nimi;

    /**
     *  Luo uuden mittaustulosolion ilman nimeä.
     */
    public Mittaustulos() {
        this("");
    }

    /**
     *  Luo uuden mittastulos olion nimellä.
     * @param nimi  Tuloksille annettava nimi
     */
    public Mittaustulos(String nimi) {
        this.nimi = nimi;
        suurin = Long.MIN_VALUE;
        pienin = Long.MAX_VALUE;
        lkm = 0;
        summa = 0;
    }

    /**
     *  Palauttaa mittaustulosten nimen, mikäli niille annetiin sellainen luomisen yhteydessä.
     * @return  Mittaustulosten nimi
     */
    public String getNimi() {
        return nimi;
    }

    /**
     *  Palauttaa pienimmän mitatun ajan.
     * @return Pienin mitattu aika
     */
    public long getPienin() {
        return pienin;
    }

    /**
     *  Palauttaa suurimman mitatun ajan.
     * @return  Suurin mitattu aika
     */
    public long getSuurin() {
        return suurin;
    }

    /**
     * Laskee ja palauttaa aikojen keskiarvon.
     *
     * @return Laskettu keskiarvo tai 0 mikäli tietoja ei ole.
     */
    public long getKeskiarvo() {
        if (lkm > 0L) {
            return summa / lkm;
        }
        return 0L;
    }

    /**
     * Lisää tuloksiin uuden ajan ja päivittää olio-muuttujien arvot.
     *
     * @param aika Lisättävä aika
     */
    public void lisaaAika(long aika) {
        if (aika > 0L) {
            summa += aika;
            lkm++;
            if (aika > suurin) {
                suurin = aika;
            }
            if (aika < pienin) {
                pienin = aika;
            }
        }
    }
}
