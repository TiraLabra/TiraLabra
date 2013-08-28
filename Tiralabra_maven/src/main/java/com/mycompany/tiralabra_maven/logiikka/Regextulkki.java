
package com.mycompany.tiralabra_maven.logiikka;

/**
 * Luokan vastuulla on tulkita merkkijonoina annetut säännölliset lausekkeet 
 * käänteiseen puolalaiseen notaatioon (RPN).
 *
 * @author John Lång <jllang@cs.helsinki.fi>
 */
public final class Regextulkki extends Tulkki {
    
    /**
     * Palauttaa luokan uuden instanssin.
     */
    public Regextulkki() {
        PRIORITEETIT.lisaa('?', 1);
        PRIORITEETIT.lisaa('*', 1);
        PRIORITEETIT.lisaa('+', 1);
        PRIORITEETIT.lisaa('|', 2);
        PRIORITEETIT.lisaa('.', 2);
    }
    
    @Override
    protected boolean merkkiOnLyhenne() {
        switch (merkki) {
            case '[': case '\\':
                return true;
            default:
                return false;
        }
    }
    
    @Override
    protected boolean merkkiOnOperaattori() {
        switch (merkki) {
            /*case '.':*/ case '|': case '?': case '*': case '+':
                return true;
            default:
                return false;
        }
    }
    
    @Override
    protected boolean merkkiOnDataa() {
        return !merkkiOnOperaattori() && !merkkiOnLyhenne();
    }
    
    @Override
    protected void kasitteleLyhenne() {
        JONO.lisaa(".");
        if (syotteenMerkit[indeksi] == '\\') {
            JONO.lisaa("\\" + syotteenMerkit[indeksi + 1]);
            indeksi += 1;
            return;
        }
        
        char kasiteltava, alku, loppu;
        if (syotteenMerkit[indeksi + 1] == '^') {
            // Ei riitä aika tällaisen kanssa säätämiseen. :(
            throw new UnsupportedOperationException("Lyhenne [^...] ole ei "
                    + "tuettu.");
        }
        if (syotteenMerkit[indeksi + 2] == '-') {
            // Lyhenne on esim. muotoa [a-z]:
            alku    = syotteenMerkit[1];
            loppu   = syotteenMerkit[3];
            for (char merkki = alku; merkki <= loppu; merkki++) {
                JONO.lisaa(merkki + "");
            }
            JONO.lisaa("|");
            indeksi += 4;
        } else {
            // Lyhenne on muotoa [agh26] (="a|g|h|2|6"):
            int i = indeksi + 1;
            for (; i < syotteenMerkit.length; i++) {
                kasiteltava = syotteenMerkit[i];
                if (kasiteltava == ']') {
                    JONO.lisaa("|");
                    indeksi = i;
                    return;
                }
                JONO.lisaa(kasiteltava + "");
            }
            throw new IllegalArgumentException("Virheellinen lyhennysmerkintä!");
        }
    }

    @Override
    protected void kasitteleData() {
        JONO.lisaa(".");
        StringBuilder mjr = new StringBuilder();
        while (merkkiOnDataa()) {
            mjr.append(merkki);
            indeksi++;
            if (indeksi == syotteenMerkit.length) {
                JONO.lisaa(mjr.toString());
                JONO.lisaa(".");;
                return;
            }
            merkki = syotteenMerkit[indeksi];
        }
        JONO.lisaa(mjr.toString());
    }

}
