
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
            case '.': case '|': case '?': case '*': case '+':
                return true;
            default:
                return false;
        }
    }
    
    @Override
    protected boolean merkkiOnOperandi() {
//        switch (merkki) {
//            case '(': case ')':
//                return false;
//            default:
////                return !merkkiOnLyhenne() && !merkkiOnOperaattori();
//                return true;
//        }
        return !merkkiOnLyhenne() && !merkkiOnOperaattori();
    }
    
    @Override
    protected void kasitteleLyhenne() {
//        JONO.lisaa(".");
        if (syotteenMerkit[indeksi] == '\\') {
            JONO.lisaa("\\" + syotteenMerkit[indeksi + 1]);
            indeksi += 1;
//            JONO.lisaa(".");
            return;
        }
        
        int alkuindeksi = indeksi + 1;
        char kasiteltava = syotteenMerkit[alkuindeksi];
        if (kasiteltava == '^') {
            // Ei riitä aika tällaisen kanssa säätämiseen. :(
            throw new UnsupportedOperationException("Lyhenne [^...] ole ei "
                    + "tuettu.");
        }
        int toistot = tutkiToistot();
        if (syotteenMerkit[alkuindeksi + 1] == '-') {
            char alku, loppu;
            // Lyhenne on esim. muotoa [a-z]:
            while (toistot > 0) {                
                alku = syotteenMerkit[alkuindeksi];
                loppu = syotteenMerkit[alkuindeksi + 2];
                for (kasiteltava = alku; kasiteltava <= loppu; kasiteltava++) {
                    JONO.lisaa(kasiteltava + "");
                }
                JONO.lisaa("|");
                toistot--;
            }
        } else {
            // Lyhenne on muotoa [agh26] (="a|g|h|2|6"):
            while (toistot > 0) {                
                for (int i = alkuindeksi; i < syotteenMerkit.length; i++) {
                    kasiteltava = syotteenMerkit[i];
                    if (kasiteltava == ']') {
                        JONO.lisaa("|");
                        break;
                    }
                    JONO.lisaa(kasiteltava + "");
                }
                toistot--;
            }
        }
//        JONO.lisaa(".");
    }

    private int tutkiToistot() throws IllegalArgumentException {
        StringBuilder mjr = new StringBuilder();
        char kasiteltava;
        int toistot = 1;
        if (indeksi + 1 == ']') {
            // Sulkujen '[' ja ']' välissä ei ole mitään.
            kaadu("Virheellinen lyhennysmerkintä!");
        }
        ulompi: for (int i = indeksi + 1; i < syotteenMerkit.length; i++) {
            if (i == syotteenMerkit.length) {
                // Ei löydetty sulkumerkkiä ']':
                kaadu("Virheellinen lyhennysmerkintä!");
            }
            kasiteltava = syotteenMerkit[i];
            if (kasiteltava == ']') {
                if (i + 1 == syotteenMerkit.length) {
                    // Päästiin merkkijonon loppuun eikä toistojen määrää ole
                    // ilmoitettu.
                    indeksi = i;
                    break;
                }
                if (syotteenMerkit[i + 1] == '{') {
                    if (i + 2 == '}') {
                        // Sulkujen '{' ja '}' välissä ei ole mitään.
                        kaadu("Virheellinen lyhennysmerkintä!");
                    }
                    for (int j = i + 2; j < syotteenMerkit.length; j++) {
                        if (j == syotteenMerkit.length) {
                            // Ei löydetty sulkumerkkiä '}':
                            kaadu("Virheellinen lyhennysmerkintä!");
                        }
                        kasiteltava = syotteenMerkit[j];
                        if (kasiteltava == '}') {
                            try {
                                toistot = Integer.parseInt(mjr.toString());
                                // Tulkin yleinen indeksi päivitetään kohtaan,
                                // josta tulkkaus jatkuu kun lyhenne on avattu.
                                indeksi = j + 1;
                                break ulompi;
                            } catch(NumberFormatException e) {
                                // Sulkumerkkien välissä ei ollut kokonaislukua:
                                kaadu("Virheellinen lyhennysmerkintä!");
                            }
                        }
                        mjr.append(kasiteltava);
                    }
                } else {
                    // Toistojen määrää ei ole ilmoitettu.
                    indeksi = i;
                    break;
                }
            }
        }
        return toistot;
    }

    @Override
    protected void kasitteleOperandi() {
        StringBuilder mjr = new StringBuilder();
        while (merkkiOnOperandi()) {
            mjr.append(merkki);
            indeksi++;
            if (indeksi == syotteenMerkit.length) {
                JONO.lisaa(mjr.toString());
                return;
            }
            merkki = syotteenMerkit[indeksi];
        }
        JONO.lisaa(mjr.toString());
//        JONO.lisaa(".");
    }

}
