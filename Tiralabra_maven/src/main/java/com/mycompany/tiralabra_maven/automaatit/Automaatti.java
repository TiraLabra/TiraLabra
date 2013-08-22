
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
    private Tila                    viimeisinTila, haarautumisTila;
    private final Jono<Tila>        TILAT;
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
            this.EHDOT          = null;
            this.DATA           = null;
        } else {
            this.ALKUTILA       = new Tila(false);
            this.TILAT          = new Jono<>();
            this.EHDOT          = new Jono<>();
            this.DATA           = new Pino<>();
            this.viimeisinTila  = ALKUTILA;
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
        Tila tila = ALKUTILA;
        Jono<Tila> tilasiirtymat;
        
        // Ninjasti sijoitettua testikoodia...
        Tila t = new Tila(false);
        Tila u = new Tila(true);
        Tila v = new Tila(true);
        t.lisaaTilasiirtyma('a', u);
        u.lisaaTilasiirtyma('b', v);
        
        // Miksei tämä toimi?
        for (int i = 0; i < merkit.length; i++) {
            tilasiirtymat = tila.tilasiirtymat(merkit[i]);
            if (tilasiirtymat != null) {
                // Lisätään seuraavaksi tutkittavat tilat jonoon (BFS):
                while (!tilasiirtymat.onTyhja()) {
                    TILAT.lisaa(tilasiirtymat.poista());
                }
            }
            if (!TILAT.onTyhja()) {
                tila = TILAT.poista();
            }
            if (tila.ON_HYVAKSYVA && i == merkit.length - 1) {
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
                // Myöhemmin voisi tietysti implementoida ilmaisuja kuten \s tai
                // \d (mutta nämä eivät ole varsinaisia regexejä.)
                DATA.lisaa(merkkijono);
            } else {
                merkki = merkkijono.charAt(0);
                switch (merkki) {
                    case '.':
                        // Jos konkatenointi on viimeinen toimenpide, on sen päätyttävä
                        // hyväksyvään tilaan.
                        konkatenoi(LAUSEKE.kurkista() == null);
                        break;
                    case '|':
                        konkatenoi(LAUSEKE.kurkista() == null);
                        haarautuminen();
                        break;
                    case '?':
                        nollaTaiYksi();
                        break;
                    case '*':
                        nollaTaiUseampi();
                        break;
                    case '+':
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
        viimeisinTila = new Tila(true);
    }
    
    private void konkatenoi(final boolean PAATTYY_HYVAKSYVAAN_TILAAN) {
        // Merkkijonosta rakennetaan jono tiloja joihin siirrytään oikeilla
        // merkeillä. Jonon ensimmäinen solmu lisätään jonoon VAIHTOEHDOT jotta
        // haarautumisten käsittely onnistuisi oikein.
        String merkkijono = DATA.poista();
        EHDOT.lisaa(merkkijono.charAt(0));
        Tila tila = new Tila(false), edellinenTila;
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
    }
    
    private void haarautuminen() {
        // Toteutan haarautumisen unaarisena operaationa joka lisää yhden uuden
        // tilasiirtymän käsittelyssä olevalle tilalle.
        viimeisinTila.lisaaTilasiirtyma(EHDOT.poista(), TILAT.poista());
    }
    
    private void nollaTaiYksi() {
    }
    
    private void nollaTaiUseampi() {
        
    }
    
    private void yksiTaiUseampi() {
        
    }
    
}
