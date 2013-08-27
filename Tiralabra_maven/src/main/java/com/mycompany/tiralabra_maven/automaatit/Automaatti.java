
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
    
    private final String            KIELI;          // toString-metodia varten
    private final Tila              ALKUTILA;
    private final Pino<Tila>        ALIALKUTILAT;   // Automaatin rakentamiseen
    private final Pino<Character>   EHDOT;          // Automaatin rakentamiseen
    private final Pino<String>      DATA;           // Automaatin rakentamiseen
    private Tila                    viimeisin;      // Automaatin rakentamiseen
    
    /**
     * Palauttaa luokan uuden instanssin. 
     * 
     * @param LAUSEKE Säännöllinen lauseke RPN-muodossa.
     * @see Tulkki#tulkitseMerkkijono
     */
    public Automaatti(final Jono<String> LAUSEKE) {
        if (LAUSEKE == null) {
            // Jacoco jälleen bugittaa... (testEpakelpoLauseke)
            throw new IllegalArgumentException("Syötteenä saatu säännöllinen "
                    + "lauseke oli tyhjä!");
        } else {
            this.KIELI          = LAUSEKE.tuloste();
            this.ALKUTILA       = new Tila(false);
            this.ALIALKUTILAT   = new Pino<>();
            this.EHDOT          = new Pino<>();
            this.DATA           = new Pino<>();
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
        
        final char[] MERKIT = MERKKIJONO.toCharArray();
        final Jono<Tila> TILAT = new Jono<>();
        TILAT.lisaa(ALKUTILA);
        Tila tila;    
        Jono<Tila> tilasiirtymat = null;
        int i = 0;
        
        while (!TILAT.onTyhja()) {
            tila = TILAT.poista();
            if (i == MERKKIJONO.length()) {
                // Koko syöte on käsitelty:
                if (tila.ON_HYVAKSYVA) {
                    // Päädyttiin hyväksyvään tilaan:
                    return true;
                } else if (epsilonTilasiirtymia(tila)) {
                    // Löydettiin epsilon-tilasiirtymiä:
                    TILAT.yhdista(tila.tilasiirtymat('\u03b5'));
                    continue;
                }
                return false;
            }
            tilasiirtymat = tila.tilasiirtymat(MERKIT[i]);
            if (tilasiirtymat == null) {
                // Ei löydetty merkin kanssa täsmääviä tilasiirtymiä:
                if (epsilonTilasiirtymia(tila)) {
                    TILAT.yhdista(tila.tilasiirtymat('\u03b5'));
                    continue;
                }
                return false;
            }
            TILAT.yhdista(tilasiirtymat);
            i++;
        }
        // Tilat loppuivat kesken eikä päästy hyväksyvään tilaan:
        return false;
    }
    
    private boolean epsilonTilasiirtymia(final Tila TILA) {
        return TILA.tilasiirtymat('\u03b5') != null;
    }
    
    private void rakennaKieli(final Jono<String> LAUSEKE) {
        String merkkijono;
        char merkki;
        viimeisin = ALKUTILA;
        while (!LAUSEKE.onTyhja()) {
            merkkijono = LAUSEKE.poista();
            if (merkkijono.length() > 1) {
                // Kyse on datasta sillä operaattoreiden pituus on yksi merkki.
                // Myöhemmin voisi tietysti implementoida ilmaisuja kuten \s, \d
                // tai [a-z] (mutta nämä eivät ole varsinaisia regexejä vaan
                // lyhenteitä.)
                if (merkkijono.charAt(0) == '\\') {
                    DATA.lisaa(merkkijono.substring(merkkijono.length() - 1));
                    continue;
                }
                DATA.lisaa(merkkijono);
            } else {
                merkki = merkkijono.charAt(0);
                switch (merkki) {
                    case '.':
                        konkatenoi(LAUSEKE.onTyhja());
                        break;
                    case '|':
                        haarauta(LAUSEKE.onTyhja());
                        break;
                    case '?':
                        nollaTaiYksi(LAUSEKE.onTyhja());
                        break;
                    case '*':
                        nollaTaiUseampi(LAUSEKE.onTyhja());
                        break;
                    case '+':
                        yksiTaiUseampi(LAUSEKE.onTyhja());
                        break;
                    default:
                        DATA.lisaa(merkkijono);
                }
            }
        }
        // Viimeisen tilan tulee olla hyväksyvä.
//        viimeisin = new Tila(true);
    }
    
    private void konkatenoi(final boolean PAATTYY_HYVAKSYVAAN_TILAAN) {
        final Tila TILA = new Tila(PAATTYY_HYVAKSYVAAN_TILAAN);
        
        if (DATA.korkeus() > 1) {
            StringBuilder mjr = new StringBuilder();
            
            while (!DATA.onTyhja()) {
                mjr.append(DATA.poista());
            }
            
            // Pitää kääntää merkkijono toisin päin koska alimerkkijonot
            // otettiin pinosta:
            DATA.lisaa(mjr.reverse().toString());
        }
        
        ketjuta(TILA);
        viimeisin.lisaaTilasiirtyma(EHDOT.poista(), ALIALKUTILAT.poista());
        viimeisin = TILA;
    }
    
    /**
     * Muodostaa datapinon päällimmäisestä merkkijonosta tilojen ketjun, jonka
     * ensimmäinen tila lisätään jonoon TILAT sekä ensimmäinen merkki jonoon
     * EHDOT. Jonon viimeinen tila annetaan syötteenä.
     * 
     * @param YHTYMAKOHTA 
     */
    private void ketjuta(final Tila YHTYMAKOHTA) {
        final String MERKKIJONO = DATA.poista();
        EHDOT.lisaa(MERKKIJONO.charAt(0));
        int j = MERKKIJONO.length();
        
        if (j == 1) {
            ALIALKUTILAT.lisaa(YHTYMAKOHTA);
            return;
        }
        
        Tila tila = new Tila(false);
        ALIALKUTILAT.lisaa(tila);
        Tila edellinenTila;
        j--;
        for (int i = 1; i < j; i++) {
            edellinenTila = tila;
            tila = new Tila(false);
            edellinenTila.lisaaTilasiirtyma(MERKKIJONO.charAt(i), tila);
        }
        tila.lisaaTilasiirtyma(MERKKIJONO.charAt(j), YHTYMAKOHTA);
    }
    
    private void haarauta(final boolean PAATTYY_HYVAKSYVAAN_TILAAN) {
        final Tila TILA = new Tila(PAATTYY_HYVAKSYVAAN_TILAAN);
        while (!DATA.onTyhja()) {
            ketjuta(TILA);
        }        
        while (!ALIALKUTILAT.onTyhja()) {
            viimeisin.lisaaTilasiirtyma(EHDOT.poista(), ALIALKUTILAT.poista());
        }
        viimeisin = TILA;
    }
    
    private void nollaTaiYksi(final boolean PAATTYY_HYVAKSYVAAN_TILAAN) {
        final Tila TILA = new Tila(PAATTYY_HYVAKSYVAAN_TILAAN);
        while (!DATA.onTyhja()) {
            ketjuta(TILA);
        }
        viimeisin.lisaaTilasiirtyma('\u03b5', TILA);
        viimeisin.lisaaTilasiirtyma(EHDOT.poista(), ALIALKUTILAT.poista());
        viimeisin = TILA;
        
    }
    
    private void nollaTaiUseampi(final boolean PAATTYY_HYVAKSYVAAN_TILAAN) {
        final Tila TILA = new Tila(PAATTYY_HYVAKSYVAAN_TILAAN);
        while (!DATA.onTyhja()) {
            ketjuta(TILA);
        }
        viimeisin.lisaaTilasiirtyma('\u03b5', TILA);
        TILA.lisaaTilasiirtyma(EHDOT.poista(), ALIALKUTILAT.poista());
        viimeisin = TILA;
    }
    
    private void yksiTaiUseampi(final boolean PAATTYY_HYVAKSYVAAN_TILAAN) {
        final Tila TILA = new Tila(PAATTYY_HYVAKSYVAAN_TILAAN);        
        while (!DATA.onTyhja()) {
            ketjuta(TILA);
        }
        viimeisin.lisaaTilasiirtyma(EHDOT.kurkista(), ALIALKUTILAT.kurkista());
        TILA.lisaaTilasiirtyma(EHDOT.poista(), ALIALKUTILAT.poista());
        viimeisin = TILA;
    }
    
    @Override
    public String toString() {
        return "Kielen \"" + KIELI.substring(0, KIELI.length() - 1)
                + "\" epädeterministinen äärellinen automaatti:\n\u21A6"
                + ALKUTILA.sisennettyMerkkijono("");
    }

}
