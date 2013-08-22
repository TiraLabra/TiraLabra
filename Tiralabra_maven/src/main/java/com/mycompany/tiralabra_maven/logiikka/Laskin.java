
package com.mycompany.tiralabra_maven.logiikka;

import com.mycompany.tiralabra_maven.tietorakenteet.Jono;
import com.mycompany.tiralabra_maven.tietorakenteet.Pino;

/**
 * Luokan vastuulla on laskea sille annetun käänteistä puolalaista notaatiota
 * (RPN) käyttävän kaavan lukuarvo oikein.
 *
 * @author John Lång
 */
public final class Laskin {
    
    private final Pino<Integer> PINO;
    
    /**
     * Palauttaa luokan uuden instanssin.
     */
    public Laskin() {
        PINO = new Pino<>();
        
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
    public int laske(final Jono<String> SYOTE) throws IllegalArgumentException,
            ArithmeticException {
        String merkkijono;
        char merkki;
        
        while (!SYOTE.onTyhja()) {
            merkkijono = SYOTE.poista();
            merkki = merkkijono.charAt(0);
            switch (merkki) {
                case ' ':
                    // Tulkin pitäisi poistaa välilyönnit, mutta käyttäjän
                    // antamassa RPN-kaavassa niitä saattaa olla.
                    break;
                case '+': case '-': case '*': case '/': case '%':
                    sievenna(merkki);
                    break;
                default:
                    try {
                        PINO.lisaa(Integer.parseInt(merkkijono));
                    } catch (NumberFormatException poikkeus) {
                        throw new IllegalArgumentException("Virheellinen luku "
                                + "\"" + merkkijono + "\"!");
                    }
            }
        }
        
        // Tässä vaiheessa pinossa tulisi olla jäljellä vain kaavan lopullinen
        // lukuarvo.
        if (PINO.koko() > 1) {
            throw new IllegalArgumentException("Liikaa operandeja!");
        }
        
        return PINO.poista();
    }
    
    private void sievenna(final char OPERAATTORI)
            throws IllegalArgumentException, ArithmeticException {
        int n = 0;
        int m = 0;
        try {
            // Käyttäjä on saattanut syöttää liian vähän operandeja.
            m = PINO.poista();
            n = PINO.poista();
        } catch (Exception poikkeus) {
            throw new IllegalArgumentException("Puuttuvia operandeja!");
        }
        
        switch (OPERAATTORI) {
            case '+':
                tarkastaYlaraja(n + m, Integer.MAX_VALUE);
                PINO.lisaa(n + m);
                break;
            case '-':
                // TODO: Lisää tarkastus.
                PINO.lisaa(n - m);
                break;
            case '*':
                tarkastaYlaraja((long) n * m, Integer.MAX_VALUE);
                PINO.lisaa(n * m);
                break;
            case '/':
                tarkastaNimittaja(m);
                PINO.lisaa(n / m);
                break;
            case '%':
                tarkastaNimittaja(m);
                PINO.lisaa(n % m);
                break;
        }
    }
    
    private void tarkastaYlaraja(final long LUKU, final int YLARAJA)
            throws ArithmeticException {
        if (LUKU > YLARAJA) {
            throw new ArithmeticException("Aritmeettinen ylivuoto!");
        }
    }

    private void tarkastaNimittaja(final int NIMITTAJA) throws ArithmeticException {
        if (NIMITTAJA == 0) {
            throw new ArithmeticException("Jakolaskun nimittäjä "
                    + "oli nolla!");
        }
    }

}
