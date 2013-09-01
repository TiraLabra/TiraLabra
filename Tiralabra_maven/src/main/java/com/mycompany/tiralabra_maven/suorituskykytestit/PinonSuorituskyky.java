
package com.mycompany.tiralabra_maven.suorituskykytestit;

import com.mycompany.tiralabra_maven.tietorakenteet.Pino;
import java.util.Stack;

/**
 * Tämä luokka vertaa luokkien <b>Hajautuskartta</b> ja <b>HashMap</b>
 * suorituskykyä keskenään alkioiden lisäyksen ja luvun kestossa sekä muistin
 * kulutuksessa.
 *
 * @author John Lång <jllang@cs.helsinki.fi>
 */
public final class PinonSuorituskyky {
    
    private static final int TESTI_JONO_KIRJOITUS   = 0;
    private static final int TESTI_JONO_LUKU        = 1;
    private static final int TESTI_STACK_KIRJOITUS  = 2;
    private static final int TESTI_STACK_LUKU       = 3;
    private static Pino<Integer>     pino;
    private static Stack<Integer>   stack;
    
    
    /**
     * Aloittaa suorituskyvyn testaamisen.
     */
    public static void aloita() {
//        testaa(1000);
//        testaa(10000);
        testaa(100000);
        testaa(1000000);
        testaa(10000000);
//        testaa(100000000);
    }
    
    private static void testaa(final int ALKIOITA) {
        System.out.println("Aloitetaan Pinon suorituskykytestaus "
                + ALKIOITA + " avain-arvoparilla.");
        System.out.println("----------------------------------------" +
                "----------------------------------------");
        long[] ajat;
        
        ajat = ajanotto(ALKIOITA, TESTI_JONO_KIRJOITUS);
        System.out.println("Pinon kirjoitusajat:");
        System.out.println("Keskimääräinen aika:            "
                + Suorituskykytyokalut.keskiarvo(ajat) + " ms.");
        System.out.println("Lyhyin aika:                    "
                + Suorituskykytyokalut.pienin(ajat) + " ms.");
        System.out.println("Pisin aika:                     "
                + Suorituskykytyokalut.suurin(ajat) + " ms.");
        System.out.println();
        
        ajat = ajanotto(ALKIOITA, TESTI_JONO_LUKU);
        System.out.println("Pinon lukuajat:");
        System.out.println("Keskimääräinen aika:            "
                + Suorituskykytyokalut.keskiarvo(ajat) + " ms.");
        System.out.println("Lyhyin aika:                    "
                + Suorituskykytyokalut.pienin(ajat) + " ms.");
        System.out.println("Pisin aika:                     "
                + Suorituskykytyokalut.suurin(ajat) + " ms.");
        System.out.println();
        
        long muistia = Runtime.getRuntime().freeMemory();
        pino = null;
        Runtime.getRuntime().gc();
        System.out.println("Pinon muistinkulutus:     "
                + (Runtime.getRuntime().freeMemory() - muistia) + " tavua.");
        
        System.out.println('\n');
        
        ajat = ajanotto(ALKIOITA, TESTI_STACK_KIRJOITUS);
        System.out.println("Stack:n kirjoitusajat:");
        System.out.println("Keskimääräinen aika:            "
                + Suorituskykytyokalut.keskiarvo(ajat) + " ms.");
        System.out.println("Lyhyin aika:                    "
                + Suorituskykytyokalut.pienin(ajat) + " ms.");
        System.out.println("Pisin aika:                     "
                + Suorituskykytyokalut.suurin(ajat) + " ms.");
        System.out.println();
        
        ajat = ajanotto(ALKIOITA, TESTI_STACK_LUKU);
        System.out.println("Stack:n lukuajat:");
        System.out.println("Keskimääräinen aika:            "
                + Suorituskykytyokalut.keskiarvo(ajat) + " ms.");
        System.out.println("Lyhyin aika:                    "
                + Suorituskykytyokalut.pienin(ajat) + " ms.");
        System.out.println("Pisin aika:                     "
                + Suorituskykytyokalut.suurin(ajat) + " ms.");
        System.out.println();
        
        muistia = Runtime.getRuntime().freeMemory();
//        hm = null;
//        hmm = null;
        Runtime.getRuntime().gc();
        System.out.println("Stack:n muistinkulutus:   "
                + (Runtime.getRuntime().freeMemory() - muistia) + " tavua.");
        System.out.println("========================================" +
                "========================================");
    }

    private static long[] ajanotto(final int ALKIOITA, final int TESTIN_NUMERO) {
        long aloitushetki;
        long[] ajat = new long[10];
        if (TESTIN_NUMERO == TESTI_JONO_KIRJOITUS) {
            for (int i = 0; i < 10; i++) {
                pino = null;
                Runtime.getRuntime().gc();
                pino = new Pino<>();
                aloitushetki = System.currentTimeMillis();
                taytaPino(ALKIOITA);
                ajat[i] = System.currentTimeMillis() - aloitushetki;
            }
        } else if (TESTIN_NUMERO == TESTI_JONO_LUKU) {
            pino = null;
            Runtime.getRuntime().gc();
            pino = new Pino<>();
            for (int i = 0; i < 10; i++) {
                taytaPino(ALKIOITA);
                aloitushetki = System.currentTimeMillis();
                for (int j = 0; j < ALKIOITA; j++) {
                    pino.poista();
                }
                ajat[i] = System.currentTimeMillis() - aloitushetki;
            }
        } else if (TESTIN_NUMERO == TESTI_STACK_KIRJOITUS) {
            for (int i = 0; i < 10; i++) {
                stack = null;
                Runtime.getRuntime().gc();
                stack = new Stack<>();
                aloitushetki = System.currentTimeMillis();
                taytaStack(ALKIOITA);
                ajat[i] = System.currentTimeMillis() - aloitushetki;
            }
        } else if (TESTIN_NUMERO == TESTI_STACK_LUKU) {
            stack = null;
            Runtime.getRuntime().gc();
            stack = new Stack<>();
            for (int i = 0; i < 10; i++) {
                taytaStack(ALKIOITA);
                aloitushetki = System.currentTimeMillis();
                for (int j = 0; j < ALKIOITA; j++) {
                      stack.pop();
                }
                ajat[i] = System.currentTimeMillis() - aloitushetki;
            }
        }
        return ajat;
    }

    private static void taytaPino(final int ALKIOITA) {
        for (int j = 0; j < ALKIOITA; j++) {
            pino.lisaa(j);
        }
    }

    private static void taytaStack(final int ALKIOITA) {
        for (int j = 0; j < ALKIOITA; j++) {
              stack.push(j);
        }
    }
    
}
