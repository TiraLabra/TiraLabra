
package com.mycompany.tiralabra_maven.logiikka;

import com.mycompany.tiralabra_maven.tietorakenteet.Hajautuskartta;
import com.mycompany.tiralabra_maven.tietorakenteet.Jono;
import com.mycompany.tiralabra_maven.tietorakenteet.Pino;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * Luokan vastuulla on tulkita merkkijonoina annetut matemaattiset kaavat
 * käänteiseen puolalaiseen notaatioon (RPN) käyttäen Shunting yard -algoritmia.
 *
 * @author John Lång
 */
public final class Tulkki {

    private static final Hajautuskartta<Integer> PRIORITEETIT
            = new Hajautuskartta<Integer>(3);
    private static final Pino<Character> PINO = new Pino<Character>();
    private static final Queue<String> JONO = new Jono<String>();
    private static final Queue<Character> APUJONO = new Jono<Character>();
    private static char[] syotteenMerkit;
    private static char merkki;
    private static int indeksi;
    
    static {
        PRIORITEETIT.lisaa('/', 1);
        PRIORITEETIT.lisaa('*', 1);
        PRIORITEETIT.lisaa('%', 1);
        PRIORITEETIT.lisaa('+', 2);
        PRIORITEETIT.lisaa('-', 2);
    }

    public Tulkki() {
    }

    public Queue<String> tulkitseMerkkijono(final String MERKKIJONO)
            throws IllegalArgumentException {
        
        // Koska tulkin kentät ovat staattiset, on jonoon voinut jäädä edellisen
        // kutsukerran paluuarvo.        
        JONO.clear();
        
        syotteenMerkit = MERKKIJONO.toCharArray();

        for (indeksi = 0; indeksi < syotteenMerkit.length; indeksi++) {
            merkki = syotteenMerkit[indeksi];
            if (merkki == ' ') {
                continue;
            } else if (merkkiOnNumero()) {
                kasitteleLuku();
                indeksi--;
            } else if (merkkiOnOperaattori()) {
                kasitteleOperaattori();
            } else if (merkki == '(') {
                PINO.lisaa(merkki);
            } else if (merkki == ')') {
                while (true) {
                    if (PINO.onTyhja()) {
                        throw new IllegalArgumentException("Merkkijonon \""
                                + MERKKIJONO + "\" sulkumerkit eivät täsmää!");
                    } else if (PINO.kurkista() == '(') {
                        PINO.poista();
                        break;
                    }
                    JONO.add(PINO.poista() + "");
                }
            } else {
                throw new IllegalArgumentException("Merkkijono \"" + MERKKIJONO
                        + "\" sisältää tuntemattomia merkkejä!");
            }
        }
        
        while (!PINO.onTyhja()) {
            JONO.add(PINO.poista() + "");
        }

        return JONO;
    }

    private boolean merkkiOnNumero() {
        switch (merkki) {
            case '0': case '1': case '2': case '3': case '4':
            case '5': case '6': case '7': case '8': case '9':
                return true;
            default:
                return false;
        }
    }
    
    private boolean merkkiOnOperaattori() {
        switch(merkki) {
            case '+': case '-': case '*': case '/': case '%':
                return true;
            default:
                return false;
        }
    }

    private void kasitteleLuku() {
        if (merkkiOnNumero()) {
            APUJONO.add(merkki);
            indeksi++;
            if (indeksi == syotteenMerkit.length) {
//                JONO.add(APUJONO.poll() + "");
                kokoaLuku();
                return;
            }
            merkki = syotteenMerkit[indeksi];
            kasitteleLuku();
        } else {
            kokoaLuku();
        }
    }
    
    private void kokoaLuku() {
        StringBuilder mjr = new StringBuilder();
        while (!APUJONO.isEmpty()) {
            mjr.append(APUJONO.poll());
        }
        JONO.add(mjr.toString());
    }
    
    private void kasitteleOperaattori() {
        // Pitäisi ehkä tehdä joku hashmappi operaattorien prioriteeteille.
        if (PINO.onTyhja()) {
            PINO.lisaa(merkki);
        } else {
            char pinonYlin = PINO.kurkista();
            if (pinonYlin != '(') {
                // Pienemmän prioriteetin laskutoimitukset suoritetaan ensin.
                if (PRIORITEETIT.hae(merkki) >= PRIORITEETIT.hae(pinonYlin)) {
                    JONO.add(PINO.poista() + "");
                }
            }
            PINO.lisaa(merkki);
        }
    }    
}
