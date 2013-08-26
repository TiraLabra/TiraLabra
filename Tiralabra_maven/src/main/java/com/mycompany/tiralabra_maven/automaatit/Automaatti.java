
package com.mycompany.tiralabra_maven.automaatit;

import com.mycompany.tiralabra_maven.tietorakenteet.Jono;
import com.mycompany.tiralabra_maven.tietorakenteet.Pino;

/**
 * Tämä luokka mallintaa epädeterminististä äärellistä automaattia. Automaatti
 * osaa tarkastaa kuuluuko syötteenä annettu merkkijono konstruktorissa
 * (lopullisesti) määriteltyyn säännölliseen kieleen.
 *
 * @author John Lång <jllang@cs.helsinki.fi>
 */
public final class Automaatti {
    
    private final Tila              ALKUTILA;
    private Tila                    kasiteltava;
    private final Jono<Tila>        TILAT, TILAT2;
    private final Jono<Character>   EHDOT;
    private final Pino<String>      DATA;
    
    /**
     * Palauttaa luokan uuden instanssin. 
     * 
     * @param LAUSEKE Säännöllinen lauseke RPN-muodossa.
     * @see Tulkki#tulkitseMerkkijono
     */
    public Automaatti(final Jono<String> LAUSEKE) {
//        this.LAUSEKE  = LAUSEKE;
        if (LAUSEKE == null) {
            throw new IllegalArgumentException("Syötteenä saatu säännöllinen "
                    + "lauseke oli tyhjä!");
        } else if (LAUSEKE.pituus() == 1 || LAUSEKE.kurkista().equals("*")) {
            // Tämä kieli sisältää kaikki merkkijonot.
            this.ALKUTILA       = new Tila(true);
            this.TILAT          = null;
            this.TILAT2         = null;
            this.EHDOT          = null;
            this.DATA           = null;
        } else {
            this.ALKUTILA       = new Tila(false);
            this.TILAT          = new Jono<>();
            this.TILAT2         = new Jono<>();
            this.EHDOT          = new Jono<>();
            this.DATA           = new Pino<>();
            this.kasiteltava  = ALKUTILA;
            rakennaKieli(LAUSEKE);
        }
    }
    
    /**
     * Tutkii sisältyykö parametrina annettu merkkijono automaatin säännölliseen
     * kieleen. Merkkijono sisältyy kieleen jos ja vain jos automaatin suoritus
     * päättyy hyväksyvässä tilassa sen käytyä läpi kaikki merkkijonon merkit.
     * 
     * @param MERKKIJONO    Jokin merkkijono.
     * @return              <i>true</i> jos ja vain jos parametrina annettu
     *                      merkkijono sisältyy automaatin kieleen; muutoin
     *                      <i>false</i>.
     */
    public boolean kieliSisaltaa(final String MERKKIJONO) {
        if (MERKKIJONO == null || MERKKIJONO.trim().length() == 0) {
            return false;
        }
        
        char[] merkit = MERKKIJONO.toCharArray();
        TILAT.tyhjenna();
        TILAT.lisaa(ALKUTILA);
        Tila tila;        
        Jono<Tila> tilasiirtymat;
//        boolean onnistui = false;
        
        for (int i = 0; i < merkit.length; i++) {
            tila = TILAT.poista();
//            tilasiirtymat = tila.tilasiirtymat(merkit[i]);
            tilasiirtymat = tila.tilasiirtymat(merkit[i]);
            if (tilasiirtymat == null && !tila.ON_HYVAKSYVA) {
                return false;
            }
            TILAT.yhdista(tilasiirtymat);
//            TILAT.yhdista(tila.tilasiirtymat('\u03b5'));
            // "Epsiloneille" pitäisi kehittää joku fiksu ratkaisu, esim. tilaan
            // tieto että siirryttiinkö siihen "ilmaiseksi" jolloin i:tä voidaan
            // pienentää yhdellä.
            if (i == merkit.length - 1 && tila.ON_HYVAKSYVA) {
                return true;
            }
        }
        
        return false;
    }
    
    private void rakennaKieli(final Jono<String> LAUSEKE) {
        String merkkijono;
        char merkki;
        while (!LAUSEKE.onTyhja()) {
            merkkijono = LAUSEKE.poista();
            if (merkkijono.length() > 1) {
                // Kyse on datasta sillä operaattoreiden pituus on yksi merkki.
                // Myöhemmin voisi tietysti implementoida ilmaisuja kuten \s, \d
                // tai [a-z] (mutta nämä eivät ole varsinaisia regexejä vaan
                // lyhenteitä.)
                DATA.lisaa(merkkijono);
            } else {
                merkki = merkkijono.charAt(0);
                switch (merkki) {
                    case '.':
                        konkatenoi(LAUSEKE.kurkista() == null);
                        // Yhdistetään kaikki tilojen ketjut uuteen tilaan.
                        Tila tila = new Tila(LAUSEKE.kurkista() == null), tila2;
                        while (!TILAT2.onTyhja()) {
                            tila2 = TILAT2.poista();
                            tila2.lisaaTilasiirtyma('\u03b5', tila);
                        }
                        kasiteltava = tila;
                        break;
                    case '|':
                        konkatenoi(LAUSEKE.kurkista() == null);
                        haarautuminen();
                        break;
                    case '?':
                        konkatenoi(LAUSEKE.kurkista() == null);
                        nollaTaiYksi();
                        break;
                    case '*':
                        konkatenoi(LAUSEKE.kurkista() == null);
                        nollaTaiUseampi();
                        break;
                    case '+':
                        konkatenoi(LAUSEKE.kurkista() == null);
                        yksiTaiUseampi();
                        break;
                    case '\\':
                        DATA.lisaa(merkkijono.substring(1));
                        break;
                    default:
                        DATA.lisaa(merkkijono);
                }
            }
        }
        // Viimeisen tilan tulee olla hyväksyvä.
        kasiteltava = new Tila(true);
    }
    
    private void konkatenoi(final boolean PAATTYY_HYVAKSYVAAN_TILAAN) {
        // Merkkijonosta rakennetaan jono tiloja joihin siirrytään oikeilla
        // merkeillä. Jonon ensimmäinen solmu lisätään jonoon VAIHTOEHDOT jotta
        // haarautumisten käsittely onnistuisi oikein.
        String merkkijono = DATA.poista();        
        EHDOT.lisaa(merkkijono.charAt(0));
        Tila tila;
        
        if (merkkijono.length() == 1) {
            tila = new Tila(PAATTYY_HYVAKSYVAAN_TILAAN);
            TILAT.lisaa(tila);
            TILAT2.lisaa(tila);
            return;
        }
        
        tila = new Tila(false);
        Tila edellinenTila;
        TILAT.lisaa(tila);
        char merkki;
        char[] syotteenMerkit = merkkijono.toCharArray();
        for (int i = 0, j = syotteenMerkit.length; i < j; i++) {
            merkki = syotteenMerkit[i];
            edellinenTila = tila;
            tila = new Tila(false);
            // Vähän ikävää että joutuu vertailemaan mutta tämä on helpointa:
            if (i == j - 1 && PAATTYY_HYVAKSYVAAN_TILAAN) {
                // Ylikirjoitetaan viimeinen tila hyväksyväksi.
                tila = new Tila(true);
            }
            edellinenTila.lisaaTilasiirtyma(merkki, tila);
        }
        TILAT2.lisaa(tila);
        
    }
    
    private void haarautuminen() {
        // Toteutan haarautumisen unaarisena operaationa joka lisää yhden uuden
        // tilasiirtymän käsittelyssä olevalle tilalle.
        kasiteltava.lisaaTilasiirtyma(EHDOT.poista(), TILAT.poista());
    }
    
    private void nollaTaiYksi() {
        Tila seuraavaTila = TILAT.poista();
        kasiteltava.lisaaTilasiirtyma('\u03b5', seuraavaTila);
        kasiteltava.lisaaTilasiirtyma(EHDOT.poista(), seuraavaTila);
        kasiteltava = seuraavaTila;
        
    }
    
    private void nollaTaiUseampi() {
        
    }
    
    private void yksiTaiUseampi() {
        
    }
    
    @Override
    public String toString() {
        return ALKUTILA.sisennettyMerkkijono("");
    }
    
}
