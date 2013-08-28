
package com.mycompany.tiralabra_maven.logiikka;

import com.mycompany.tiralabra_maven.tietorakenteet.Hajautuskartta;
import com.mycompany.tiralabra_maven.tietorakenteet.Jono;
import com.mycompany.tiralabra_maven.tietorakenteet.Pino;
import java.util.EmptyStackException;

/**
 * Luokan implementaation vastuulla on jonkin infiksinotaatiolla annetun
 * formaalin kielen kaavan tulkitseminen käänteiseen puolalaiseen notaatioon
 * (RPN). Tulkkaamisessa hyödynnetään Edsger Dijkstran keksimää Shunting yard 
 * -algoritmia.
 *
 * @author John Lång <jllang@cs.helsinki.fi>
 */
public abstract class Tulkki {
    
    protected final Hajautuskartta<Integer> PRIORITEETIT;
    protected final Pino<Character>         PINO;
    protected final Jono<String>            JONO;
    protected final Jono<Character>         APUJONO;
    protected String                        merkkijono;
    protected char[]                        syotteenMerkit;
    protected char                          merkki;
    protected int                           indeksi;
    
    /**
     * Palauttaa luokan uuden instanssin.
     */
    public Tulkki() {
        this.PRIORITEETIT   = new Hajautuskartta<>(4);
        this.PINO           = new Pino<>();
        this.JONO           = new Jono<>();
        this.APUJONO        = new Jono<>();
    }
    
    /**
     * Tulkitsee (mikäli mahdollista) syötteenä annetun kaavan RPN kaavaksi.
     * Tulkin tulee varmistaa että kaavan sulkumerkit täsmäävät (ja että kaikki
     * kaavan literaalit mahtuvat int-muuttujiin mikäli dataa käsitellään
     * kokonaislukumuodossa; kts. <b>Aritmetiikkatulkki</b>). Tarkemmat kaavan
     * ja operaattorien (kielestä riippuvaan) käsittelyyn liittyvät tarkastukset
     * kuuluvat RPN-kaavaa syötteenään käsittelevän luokan vastuulle.
     *
     * @param   MERKKIJONO Matemaattinen kaava.
     * @return  Matemaattinen kaava käänteisellä puolalaisella notaatiolla.
     * @throws  IllegalArgumentException Jos kaava ei sisällä yhtä paljon
     * vasemman- ja oikeanpuoleisia sulkumerkkejä tai jos se sisältää numeerisia
     * arvoja jotka ovat liian suuria int-muuttujaan.
     * @see Aritmetiikkatulkki
     * @see Regextulkki
     */
    public Jono<String> tulkitseMerkkijono(final String MERKKIJONO)
            throws IllegalArgumentException {
        merkkijono = MERKKIJONO;
        
        // Ihan vain varmuuden vuoksi:
        PINO.tyhjenna();
        JONO.tyhjenna();
        
        iteroiMerkit();        
        tyhjennaPino();

        return JONO;
    }
    
    protected void iteroiMerkit()
            throws EmptyStackException, IllegalArgumentException {
        syotteenMerkit = merkkijono.toCharArray();
        for (indeksi = 0; indeksi < syotteenMerkit.length; indeksi++) {
            merkki = syotteenMerkit[indeksi];
            if (merkki == ' ') {
                continue;
            } else if (merkkiOnLyhenne()) {
                kasitteleLyhenne();
            } else if (merkkiOnOperaattori()) {
                kasitteleOperaattori();
            } else if (merkkiOnDataa()) {
                kasitteleData();
                indeksi--;
            } else if (merkki == '(') {
                PINO.lisaa(merkki);
            } else if (merkki == ')') {
                while (true) {
                    if (PINO.onTyhja()) {
                        // Vasen sulkumerkki puuttuu. (Code coverage bugittaa
                        // tämän rivin osalta jostain syystä, Aritmetiikkatulkin
                        // testimetodi testEpakelpoKaava3 kattaa tämän rivin.)
                        kaadu("Kaavan \"" + merkkijono + "\" sulkumerkit eivät "
                                + "täsmää!");
                    } else if (PINO.kurkista() == '(') {
                        PINO.poista();
                        break;
                    }
                    JONO.lisaa(PINO.poista() + "");
                }
            } else {
                // Code coverage bugittaa myös tässä,
                // AritmetiikkatulkkiTest.testEpakelpoKaava4 testaa tämän rivin.
                kaadu("Kaava \"" + merkkijono + "\" sisältää tuntemattomia"
                        + "merkkejä!");
            }
        }
    }

    protected abstract boolean merkkiOnLyhenne();
    
    protected abstract boolean merkkiOnOperaattori();
    
    protected abstract boolean merkkiOnDataa();
    
    protected abstract void kasitteleLyhenne();
    
    protected void kasitteleOperaattori() {
        if (PINO.onTyhja()) {
            PINO.lisaa(merkki);
        } else {
            char pinonYlin = PINO.kurkista();
            if (pinonYlin != '(') {
                // Pienemmän prioriteetin operaattorit lisätään ulostuloon
                // ensimmäisinä.
                if (PRIORITEETIT.haeEnsimmainen(merkki)
                        >= PRIORITEETIT.haeEnsimmainen(pinonYlin)) {
                    JONO.lisaa(PINO.poista() + "");
                }
            }
            PINO.lisaa(merkki);
        }
    }
    
    protected abstract void kasitteleData();

    protected void tyhjennaPino()
            throws IllegalArgumentException {
        while (!PINO.onTyhja()) {
            merkki = PINO.poista();
            // Pinossa ei saa olla enää sulkuja
            if (merkki == '(') {
                kaadu("Kaavan \"" + merkkijono + "\" sulkumerkit eivät täsmää!");
            }
            JONO.lisaa(merkki + "");
        }
    }
    
    protected void kaadu(final String VIESTI) throws IllegalArgumentException {
        throw new IllegalArgumentException(VIESTI);
    }
    
}
