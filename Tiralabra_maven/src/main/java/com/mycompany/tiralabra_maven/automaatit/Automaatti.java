
package com.mycompany.tiralabra_maven.automaatit;

import com.mycompany.tiralabra_maven.totuusfunktiot.Disjunktio;
import com.mycompany.tiralabra_maven.tietorakenteet.Jono;

/**
 * Tämän luokan tarkoituksena on toteuttaa (UNIX grep-tyylisiä) säännöllisiä
 * lausekkeita käsittelevä äärellinen deterministinen automaatti. Automaatti käy
 * läpi tiloja leveyssuuntaisella läpikäynnillää.
 *
 * @author John Lång <jllang@cs.helsinki.fi>
 */
public class Automaatti {
    
    // VAROITUS: Sisältä keskeneräistä koodia
    
    // Pitänee refaktoroida tätä luokkaa aika rankalla kädellä siten, että
    // konstruktorissa asetetaan regex ja kasittele-metodilla katsotaan täsmääkö
    // se annetulla merkkijonolla...
    
    private Automaattisolmu[]       alkutilat;
    private Automaattisolmu         lopputila;
    private String[]                data;
    private Jono<Automaattisolmu>   solmut;
    
    public Automaatti() {
    }
    
    public boolean kasittele(final Jono<String> SYOTE) {
        alusta(SYOTE);
        return suorita();
    }
        
    private void alusta(final Jono<String> SYOTE) {
        int             i = 0, j = 0;
        String          merkkijono;
        char            merkki;
        Automaattisolmu solmu = null;
        alkutilat = new Automaattisolmu[SYOTE.pituus()];
        // Koko syöte ei ole dataa mutta eipähän lopu tila kesken:
        data    = new String[SYOTE.pituus()];
        // Olettaen että syöte on RPN-muodossa, ensimmäinen pätkä ei voi olla
        // muuta kuin dataa.
//        data[0] = SYOTE.poista();
        while (!SYOTE.onTyhja()) {
            // Tämä toiminnallisuus muistuttaa paljon luokan Laskin metodin
            // laske while-looppia.
            merkkijono  = SYOTE.poista();
            merkki      = merkkijono.charAt(0);
            switch (merkki) {
                case '*':
                case '+':
                case '?':
                    break;
                case '|':
                    solmu = new Automaattisolmu(i, new Disjunktio(), data[i]);
                    alkutilat[j] = solmu;
                    j++;
                    break;
                default:
                    data[i] = merkkijono;
                    // Merkki tulkitaan datana.
            }
            i++;
        }
        lopputila = solmu;
    }
    
    private boolean suorita() {
        // Leveyssuuntainen läpikäynti
        solmut = new Jono<Automaattisolmu>();
        for (Automaattisolmu solmu : alkutilat) {
            if (solmu == null) {
                break;
            }
            solmut.lisaa(solmu);
        }
        Automaattisolmu solmu;
        while (!solmut.onTyhja()) {
            solmu = solmut.poista();
            solmu = solmu.tilasiirtyma(data[solmu.INDEKSI]);
            if (solmu != null) {
                if (solmu == lopputila) {
                    return true;
                }
                solmut.lisaa(solmu);
            }
        }
        // Yhtäkään tutkittua polkua pitkin ei päästy maaliin; siispä merkkijono
        // ei täsmännyt regexin kanssa.
        return false;
    }

    
}
