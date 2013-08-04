/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
    private static final Queue<Character> JONO = new ArrayDeque<Character>();
    
    public Tulkki() {
    }
    
    public Character[] tulkitseMerkkijono(final String MERKKIJONO) {
        final char[] SYOTE      = MERKKIJONO.toCharArray();
        Character[] paluuarvo   = null;
        
        char merkki;
        
        for (int i = 0; i < SYOTE.length; i++) {
            merkki = SYOTE[i];
            
            tulkitseMerkkijono(MERKKIJONO);

        }
        
        return paluuarvo;
    }
    
    private void tulkitseMerkki(char merkki) {
        switch (merkki) {
            case ' ':
                return;
            case '+':
            case '-':
                break;
            case '*':
            case '/':
                break;
            default:
                JONO.add(merkki);
        }
    }

}
