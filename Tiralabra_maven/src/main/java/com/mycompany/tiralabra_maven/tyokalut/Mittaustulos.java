package com.mycompany.tiralabra_maven.tyokalut;

import com.mycompany.tiralabra_maven.tietorakenteet.Hakupuu;

/**
 * Luokka johon talletetaan tietoja puista kun niiden suorituskykyä tutkitaan.
 * Olioon on tarkoitus tallettaa tieto saman opertaation useasta suorituksesta
 * jolloin mittuksesta yms aiheutuva virhe voidaan korjata.
 *
 * @author Markus
 */
public class Mittaustulos {

    private long pienin, suurin, summa;
    private int lkm;
    private final Hakupuu puu;
    private final String nimi;
    private int korkeus;
    private int koko;

    /**
     * Luo uuden mittastulos olion ja asettaa olio-muuttujille asianmukaiset
     * alkuarvot.
     *
     * @param puu Hakupuu, jota tulokset koskevat
     */
    public Mittaustulos(Hakupuu puu) {
        this.puu = puu;
        if (puu != null) {
            this.nimi = puu.getNimi();
        } else {
            this.nimi = "";
        }
        suurin = Long.MIN_VALUE;
        pienin = Long.MAX_VALUE;
        lkm = 0;
        summa = 0;
        //Tieto siitä kuinka suuri puu oli suurimmillaan.
        if (puu != null) {
            korkeus = puu.getKorkeus();
            koko = puu.getKoko();
        }

    }

    public Mittaustulos() {
        this(null);
    }

    /**
     * Pauttaa mittaustuloksiin liittyvän puun nimen tai tyhjää mikäli puuta ei
     * ole.
     *
     * @return Puun nimi tai "" jos puuta ei ole.
     */
    public String getNimi() {
        return nimi;
    }

    /**
     * Palauttaa pienimmän mitatun ajan.
     *
     * @return Pienin mitattu aika
     */
    public long getPienin() {
        return pienin;
    }

    /**
     * Palauttaa suurimman mitatun ajan.
     *
     * @return Suurin mitattu aika
     */
    public long getSuurin() {
        return suurin;
    }

    /**
     * Laskee ja palauttaa mitattujen aikojen keskiarvon.
     *
     * @return Laskettu keskiarvo tai 0 mikäli tietoja ei ole.
     */
    public long getKeskiarvo() {
        if (lkm > 0) {
            return summa / lkm;
        }
        return 0L;
    }

    /**
     * Palauttaa kaikkien lisättyjen aikojen summan.
     *
     * @return Kaikkien lisättyjen aikojen summa.
     */
    public long getKokonaisaika() {
        return summa;
    }

    /**
     * Palauttaa Mittastulokselle luomisen yhteydessä annetun puun koon
     * suurimmillaan.
     *
     * @return Lisätyn puun suurin koko mittauksen aikana.
     */
    public int getKoko() {
        return koko;
    }

    /**
     * Palauttaa Mittastulokselle luomisen yhteydessä annetun puun korkeus
     * suurimmillaan.
     *
     * @return Lisätyn puun suurin korkeus mittauksen aikana.
     */
    public int getKorkeus() {
        return korkeus;
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
            if (puu != null) {
                if (puu.getKorkeus() > korkeus) {
                    korkeus = puu.getKorkeus();
                }
                if (puu.getKoko() > koko) {
                    koko = puu.getKoko();
                }
            }
        }
    }

    /**
     * Päivittää puun korkeutta ja kokoa koskevat tiedot vastaamaan
     * kutsuhetkistä tilannetta.
     */
    public void paivitaPuunTiedot() {
        if (puu != null) {
            if (puu.getKorkeus() > korkeus) {
                korkeus = puu.getKorkeus();
            }
            if (puu.getKoko() > koko) {
                koko = puu.getKoko();
            }
        }
    }
}
