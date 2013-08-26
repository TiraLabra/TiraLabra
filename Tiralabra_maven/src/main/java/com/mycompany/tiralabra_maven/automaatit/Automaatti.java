
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
    
    private final String            KIELI;  // toString-metodia varten
    private final Tila              ALKUTILA;
    private Tila                    viimeisin;
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
            this.KIELI          = "*";
            this.ALKUTILA       = new Tila(true);
            this.TILAT          = null;
            this.EHDOT          = null;
            this.DATA           = null;
        } else {
            this.KIELI          = LAUSEKE.tuloste();
            this.ALKUTILA       = new Tila(false);
            this.TILAT          = new Jono<>();
            this.EHDOT          = new Jono<>();
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
        
        char[] merkit = MERKKIJONO.toCharArray();
        TILAT.tyhjenna();
        TILAT.lisaa(ALKUTILA);
        Tila tila = new Tila(false); // Jottei kääntäjä herjaisi.        
        Jono<Tila> tilasiirtymat;
//        boolean onnistui = false;
        
        for (int i = 0; i < merkit.length; i++) {
            tila = TILAT.poista();
//            tilasiirtymat = tila.tilasiirtymat(merkit[i]);
            tilasiirtymat = tila.tilasiirtymat(merkit[i]);
//            if (tilasiirtymat == null && !tila.ON_HYVAKSYVA) {
//                return false;
//            }
            TILAT.yhdista(tilasiirtymat);
//            TILAT.yhdista(tila.tilasiirtymat('\u03b5'));
            // "Epsiloneille" pitäisi kehittää joku fiksu ratkaisu, esim. tilaan
            // tieto että siirryttiinkö siihen "ilmaiseksi" jolloin i:tä voidaan
            // pienentää yhdellä.
        }
        
        return tila.ON_HYVAKSYVA;
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
                DATA.lisaa(merkkijono);
            } else {
                merkki = merkkijono.charAt(0);
                switch (merkki) {
                    case '.':
                        Tila tila = new Tila(LAUSEKE.onTyhja());
                        konkatenoi(tila);
                        while (!TILAT.onTyhja()) {
                            viimeisin.lisaaTilasiirtyma(EHDOT.poista(),
                                    TILAT.poista());
                        }
                        viimeisin = tila;
                        break;
                    case '|':
                        haarauta(LAUSEKE.onTyhja());
                        break;
                    case '?':
                        nollaTaiYksi(LAUSEKE.onTyhja());
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
        viimeisin = new Tila(true);
    }
    
    /**
     * Muodostaa datapinon päällimmäisestä merkkijonosta tilojen ketjun, jonka
     * ensimmäinen tila lisätään jonoon TILAT sekä ensimmäinen merkki jonoon
     * EHDOT. Jonon viimeinen tila annetaan syötteenä.
     * 
     * @param YHTYMAKOHTA 
     */
    private void konkatenoi(final Tila YHTYMAKOHTA) {
        String merkkijono = DATA.poista();
        Tila tila = new Tila(false);
        EHDOT.lisaa(merkkijono.charAt(0));
        int j = merkkijono.length();
        
        if (j > 1) {
            TILAT.lisaa(tila);
            Tila edellinenTila;
            j--;
            for (int i = 1; i < j; i++) {
                edellinenTila = tila;
                tila = new Tila(false);
                edellinenTila.lisaaTilasiirtyma(merkkijono.charAt(i), tila);
            }
            tila.lisaaTilasiirtyma(merkkijono.charAt(j), YHTYMAKOHTA);
        } else {
            TILAT.lisaa(YHTYMAKOHTA);
        }
    }
    
    private void haarauta(final boolean PAATTYY_HYVAKSYVAAN_TILAAN) {
        Tila tila = new Tila(PAATTYY_HYVAKSYVAAN_TILAAN);
        while (!DATA.onTyhja()) {
            konkatenoi(tila);
        }        
        while (!TILAT.onTyhja()) {
            viimeisin.lisaaTilasiirtyma(EHDOT.poista(), TILAT.poista());
        }
        viimeisin = tila;
    }
    
    private void nollaTaiYksi(final boolean PAATTYY_HYVAKSYVAAN_TILAAN) {
        Tila tila = new Tila(PAATTYY_HYVAKSYVAAN_TILAAN);
        konkatenoi(tila);
        viimeisin.lisaaTilasiirtyma('\u03b5', tila);
        viimeisin.lisaaTilasiirtyma(EHDOT.poista(), TILAT.poista());
        viimeisin = tila;
        
    }
    
    private void nollaTaiUseampi() {
        
    }
    
    private void yksiTaiUseampi() {
        
    }
    
    @Override
    public String toString() {
        return "Kielen \"" + KIELI.substring(0, KIELI.length() - 1)
                + "\" epädeterministinen äärellinen automaatti:\n "
                + ALKUTILA.sisennettyMerkkijono("");
    }
    
}
