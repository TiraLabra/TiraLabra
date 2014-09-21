package com.mycompany.tiralabra_maven.tyokalut;

import com.mycompany.tiralabra_maven.tietorakenteet.Hakupuu;

/**
 * Luokka johon talletetaan mitattuja aikoja.
 *
 * @author Markus
 */
public class Mittaustulos {

    private long pienin, suurin, summa;
    private int lkm;
    private final Hakupuu puu;
    private final String nimi;



    /**
     *  Luo uuden mittastulos olion ja asettaa olio-muuttujille asianmukaiset alkuarvot.
     * @param nimi  Mittaustulokselle annettava nimi.
     * @param puu  Hakupuu, jota tulokset koskevat
     */
    public Mittaustulos(String nimi, Hakupuu puu) {
        this.puu = puu;
        this.nimi = nimi;
        suurin = Long.MIN_VALUE;
        pienin = Long.MAX_VALUE;
        lkm = 0;
        summa = 0;
    }

    public Mittaustulos() {
        this(null,null);
    }
    
    

    /**
     *  Palauttaa mittaustulosten nimen, mikäli niille annetiin sellainen luomisen yhteydessä.
     * @return  Mittaustulosten nimi
     */
    public Hakupuu getPuu() {
        return puu;
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

    @Override
    public String toString() {
        //TODO lisää jokin tulostuasu
        return super.toString();
    }
    
    
}
