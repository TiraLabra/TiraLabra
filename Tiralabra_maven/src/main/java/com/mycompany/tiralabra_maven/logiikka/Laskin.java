
package com.mycompany.tiralabra_maven.logiikka;

import java.util.Queue;
import java.util.Stack;

/**
 * Luokan vastuulla on laskea sille annetun RPN-kaavan lukuarvo oikein.
 *
 * @author John Lång
 */
public final class Laskin {
    
    private final Stack<Integer> PINO;
    
    public Laskin() {
        PINO = new Stack<Integer>();
        
    }
    
    /**
     * Laskee annetun RPN-kaavan lukuarvon.
     * 
     * @param SYOTE Kaavan operaattorit ja operandit merkkijonomuodossa.
     * @return Kaavan lukuarvo.
     * @throws IllegalArgumentException Jos käyttäjän syöttämässä RPN-kaavassa
     * on syntaksivirhe.
     * @throws ArithmeticException Jos tapahtuu nollalla jakaminen tai
     * aritmeettinen ylivuoto.
     */
    public int laske(final Queue<String> SYOTE) throws IllegalArgumentException,
            ArithmeticException {
        String merkkijono;
        char merkki;
        
        while (!SYOTE.isEmpty()) {
            merkkijono = SYOTE.poll();
            merkki = merkkijono.charAt(0);
            switch (merkki) {
                case ' ':
                    // Tulkin pitäisi poistaa välilyönnit, mutta käyttäjän
                    // antamassa RPN-kaavassa niitä saattaa olla.
                    break;
                case '+':
                case '-':
                case '*':
                case '/':
                    sievenna(merkki);
                    break;
                default:
                    try {
                        PINO.push(Integer.parseInt(merkkijono));
                    } catch (NumberFormatException poikkeus) {
                        throw new IllegalArgumentException("Virheellinen luku "
                                + "\"" + merkkijono + "\"!");
                    }
            }
        }
        
        if (PINO.size() > 1) {
            throw new IllegalArgumentException("Liikaa operandeja!");
        }
        
        return PINO.pop();
    }
    
    private void sievenna(final char OPERAATTORI)
            throws IllegalArgumentException, ArithmeticException {
        int n = 0;
        int m = 0;
        try {
            // Käyttäjä on saattanut syöttää liian vähän operandeja.
            m = PINO.pop();
            n = PINO.pop();
        } catch (Exception poikkeus) {
            throw new IllegalArgumentException("Puuttuvia operandeja!");
        }
        
        switch (OPERAATTORI) {
            case '+':
                tarkastaYlaraja(n + m, Integer.MAX_VALUE);
                PINO.push(n + m);
                break;
            case '-':
                PINO.push(n - m);
                break;
            case '*':
                tarkastaYlaraja((long) n * m, Integer.MAX_VALUE);
                PINO.push(n * m);
                break;
            case '/':
                if (m == 0) {
                    throw new ArithmeticException("Jakolaskun nimittäjä "
                            + "oli nolla!");
                }
                PINO.push(n / m);
                break;
        }
    }
    
    private void tarkastaYlaraja(final long LUKU, final int YLARAJA)
            throws ArithmeticException {
        if (LUKU > YLARAJA) {
            throw new ArithmeticException("Aritmeettinen ylivuoto!");
        }
    }

}
