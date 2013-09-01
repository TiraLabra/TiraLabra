
package com.mycompany.tiralabra_maven.automaatit;

import com.mycompany.tiralabra_maven.tietorakenteet.Jono;
import com.mycompany.tiralabra_maven.tietorakenteet.Pino;

/**
 * Tämä luokka mallintaa epädeterminististä äärellistä automaattia. Automaatti
 * osaa tarkastaa kuuluuko syötteenä annettu merkkijono konstruktorissa
 * (lopullisesti) määriteltyyn säännölliseen kieleen. <b>Automaatti</b> on
 * luokan <b>RegexKasittelija</b> komponentti.
 *
 * @author John Lång <jllang@cs.helsinki.fi>
 * @see RegexKasittelija
 */
public final class Automaatti {
    
//    private final String            KIELI;        // toString-metodia varten
    private final Tila              ALKUTILA;       // Rakennus ja läpikäynti
    private final Pino<Tila>        ALIALKUTILAT;   // Automaatin rakentamiseen
    private final Pino<Character>   EHDOT;          // Automaatin rakentamiseen
    private final Pino<String>      OPEDANDIT;      // Automaatin rakentamiseen
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
//            this.KIELI          = LAUSEKE.tuloste();
            this.ALKUTILA       = new Tila(false);
            this.ALIALKUTILAT   = new Pino<>();
            this.EHDOT          = new Pino<>();
            this.OPEDANDIT      = new Pino<>();
            rakennaKieli(LAUSEKE);
        }
    }
    
    /**
     * Tutkii sisältyykö parametrina annettu merkkijono automaatin säännölliseen
     * kieleen. Merkkijono sisältyy kieleen jos ja vain jos automaatin suoritus
     * päättyy hyväksyvässä tilassa sen käytyä läpi kaikki merkkijonon merkkejä
     * vastaavat tilasiirtymät.
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
                    OPEDANDIT.lisaa(merkkijono.substring(merkkijono.length() - 1));
                    continue;
                }
                OPEDANDIT.lisaa(merkkijono);
            } else {
                merkki = merkkijono.charAt(0);
                switch (merkki) {
                    case '.':
                        if (!OPEDANDIT.onTyhja()) {
                            // Pitäisi korvata tämä LAUSEKE.onTyhja metodilla
                            // kaikkiOperanditOnKasitelty...
                            konkatenoi(LAUSEKE.onTyhja());
                        }
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
                        OPEDANDIT.lisaa(merkkijono);
                }
            }
        }
    }
    
    private boolean kaikkiOperanditOnKasitelty() {
        return false;
    }
        
    private void taytaPino(final Tila TILA) {
        // Varmistaan että pinossa DATA on dataa:
        if (!OPEDANDIT.onTyhja()) {
            while (!OPEDANDIT.onTyhja()) {
                ketjuta(TILA);
            }
        } else {
            // Jos ei, niin pinossa ALIALKUTILAT voi olla alialkutiloja eli
            // tiloja joilla on aikaisemmin määriteltyjä tilasiirtymiä (esim.
            // konkatenoidut merkkijonot):
            for (int i = 0; i < ALIALKUTILAT.korkeus(); i++) {
                EHDOT.lisaa('\u03b5');
            }
        }
    }
    
    private void konkatenoi(final boolean PAATTYY_HYVAKSYVAAN_TILAAN) {
        final Tila TILA = new Tila(PAATTYY_HYVAKSYVAAN_TILAAN);
        
        if (OPEDANDIT.korkeus() > 1) {
            StringBuilder mjr = new StringBuilder();
            
            while (!OPEDANDIT.onTyhja()) {
                mjr.append(OPEDANDIT.poista());
            }
            
            // Pitää kääntää merkkijono toisin päin koska alimerkkijonot
            // otettiin pinosta:
            OPEDANDIT.lisaa(mjr.reverse().toString());
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
        final String MERKKIJONO = OPEDANDIT.poista();
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
        taytaPino(TILA);        
        while (!ALIALKUTILAT.onTyhja()) {
            viimeisin.lisaaTilasiirtyma(EHDOT.poista(), ALIALKUTILAT.poista());
        }
        ALIALKUTILAT.lisaa(viimeisin);
        viimeisin = TILA;
    }
    
    private void nollaTaiYksi(final boolean PAATTYY_HYVAKSYVAAN_TILAAN) {
        final Tila TILA = new Tila(PAATTYY_HYVAKSYVAAN_TILAAN);
        taytaPino(TILA);
        viimeisin.lisaaTilasiirtyma('\u03b5', TILA);
        viimeisin.lisaaTilasiirtyma(EHDOT.poista(), ALIALKUTILAT.poista());
        ALIALKUTILAT.lisaa(viimeisin);
        viimeisin = TILA;
    }
    
    private void nollaTaiUseampi(final boolean PAATTYY_HYVAKSYVAAN_TILAAN) {
        final Tila TILA = new Tila(PAATTYY_HYVAKSYVAAN_TILAAN);
        taytaPino(TILA);
        viimeisin.lisaaTilasiirtyma('\u03b5', TILA);
        TILA.lisaaTilasiirtyma(EHDOT.poista(), ALIALKUTILAT.poista());
        ALIALKUTILAT.lisaa(viimeisin);
        viimeisin = TILA;
    }
    
    private void yksiTaiUseampi(final boolean PAATTYY_HYVAKSYVAAN_TILAAN) {
        final Tila TILA = new Tila(PAATTYY_HYVAKSYVAAN_TILAAN);        
        taytaPino(TILA);
        viimeisin.lisaaTilasiirtyma(EHDOT.kurkista(), ALIALKUTILAT.kurkista());
        TILA.lisaaTilasiirtyma(EHDOT.poista(), ALIALKUTILAT.poista());
        ALIALKUTILAT.lisaa(viimeisin);
        viimeisin = TILA;
    }
    
    @Override
    public String toString() {
        return "Epädeterministinen äärellinen automaatti:\n\u21A6"
                + ALKUTILA.sisennettyMerkkijono("");
    }

}
