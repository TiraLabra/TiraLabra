
package com.mycompany.tiralabra_maven.logiikka;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author John Lång
 */
public final class Tulkki {

    private static final Stack<Character> PINO = new Stack<Character>();
    private static final Queue<String> JONO = new ArrayDeque<String>();
    private static final Queue<Character> APUJONO = new ArrayDeque<Character>();
    private static char[] syotteenMerkit;
    private static char merkki;
    private static int indeksi;

    public Tulkki() {
    }

    public Queue<String> tulkitseMerkkijono(final String MERKKIJONO)
            throws IllegalArgumentException {
        
        // Koska tulkin kentät ovat staattiset, on jonoon voinut jäädä edellisen
        // kutsukerran paluuarvo.        
        while (!JONO.isEmpty()) {
            JONO.poll();
        }
        
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
                PINO.push(merkki);
            } else if (merkki == ')') {
                while (true) {
                    if (PINO.empty()) {
                        throw new IllegalArgumentException("Merkkijonon \""
                                + MERKKIJONO + "\" sulkumerkit eivät täsmää!");
                    } else if (PINO.peek() == '(') {
                        PINO.pop();
                        break;
                    }
                    JONO.add(PINO.pop() + "");
                }
            } else {
                throw new IllegalArgumentException("Merkkijono \"" + MERKKIJONO
                        + "\" sisältää tuntemattomia merkkejä!");
            }
        }
        
        while (!PINO.empty()) {
            JONO.add(PINO.pop() + "");
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
            case '+': case '-': case '*': case '/':
                return true;
            default:
                return false;
        }
    }
    
//    private boolean merkkiOnSulku() {
//        if (merkki == '(' || merkki == ')') {
//            return true;
//        }
//        return false;
//    }

    private void kasitteleLuku() {
        if (merkkiOnNumero()) {
            APUJONO.add(merkki);
            indeksi++;
            if (indeksi == syotteenMerkit.length) {
                JONO.add(APUJONO.poll() + "");
                return;
            }
            merkki = syotteenMerkit[indeksi];
            kasitteleLuku();
        } else {
            String k = "";
            while (!APUJONO.isEmpty()) {
                k += APUJONO.poll();
            }
            JONO.add(k);
        }
    }
    
    private void kasitteleOperaattori() {
        if (PINO.empty()) {
            PINO.push(merkki);
        } else {
            char pinonYlin = PINO.peek();
            if (pinonYlin != '(' && pinonYlin != ')') {
                if ((merkki == '+' || merkki == '-')
                        || (merkki == '*' && pinonYlin == '/')) {
                    JONO.add(PINO.pop() + "");
                    return;
                }
            }
            PINO.push(merkki);
        }
    }    
}
