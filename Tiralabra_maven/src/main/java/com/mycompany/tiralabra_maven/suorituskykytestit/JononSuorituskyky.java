
package com.mycompany.tiralabra_maven.suorituskykytestit;

import com.mycompany.tiralabra_maven.tietorakenteet.Jono;
import java.util.ArrayDeque;

/**
 * Tämä luokka vertaa luokkien <b>Hajautuskartta</b> ja <b>HashMap</b>
 * suorituskykyä keskenään alkioiden lisäyksen ja luvun kestossa sekä muistin
 * kulutuksessa.
 *
 * @author John Lång <jllang@cs.helsinki.fi>
 */
public final class JononSuorituskyky {
    
    private static final int TESTI_JONO_KIRJOITUS = 0;
    private static final int TESTI_JONO_LUKU      = 1;
    private static final int TESTI_ARRAY_DEQUE_KIRJOITUS        = 2;
    private static final int TESTI_ARRAY_DEQUE_LUKU             = 3;
    private static Jono<Integer>     jono;
    private static ArrayDeque<Integer> ad;  // Tämä ei ole linkitetty
                                            // tietorakenne mutta saa kelvata.
    
    
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
        System.out.println("Aloitetaan Jonon suorituskykytestaus "
                + ALKIOITA + " avain-arvoparilla.");
        System.out.println("----------------------------------------" +
                "----------------------------------------");
        long[] ajat;
        
        ajat = ajanotto(ALKIOITA, TESTI_JONO_KIRJOITUS);
        System.out.println("Jonon kirjoitusajat:");
        System.out.println("Keskimääräinen aika:            "
                + Suorituskykytyokalut.keskiarvo(ajat) + " ms.");
        System.out.println("Lyhyin aika:                    "
                + Suorituskykytyokalut.pienin(ajat) + " ms.");
        System.out.println("Pisin aika:                     "
                + Suorituskykytyokalut.suurin(ajat) + " ms.");
        System.out.println();
        
        ajat = ajanotto(ALKIOITA, TESTI_JONO_LUKU);
        System.out.println("Jonon lukuajat:");
        System.out.println("Keskimääräinen aika:            "
                + Suorituskykytyokalut.keskiarvo(ajat) + " ms.");
        System.out.println("Lyhyin aika:                    "
                + Suorituskykytyokalut.pienin(ajat) + " ms.");
        System.out.println("Pisin aika:                     "
                + Suorituskykytyokalut.suurin(ajat) + " ms.");
        System.out.println();
        
        long muistia = Runtime.getRuntime().freeMemory();
        jono = null;
        Runtime.getRuntime().gc();
        System.out.println("Jonon muistinkulutus:  "
                + (Runtime.getRuntime().freeMemory() - muistia) + " tavua.");
        
        System.out.println('\n');
        
        ajat = ajanotto(ALKIOITA, TESTI_ARRAY_DEQUE_KIRJOITUS);
        System.out.println("ArrayDeque kirjoitusajat:");
        System.out.println("Keskimääräinen aika:            "
                + Suorituskykytyokalut.keskiarvo(ajat) + " ms.");
        System.out.println("Lyhyin aika:                    "
                + Suorituskykytyokalut.pienin(ajat) + " ms.");
        System.out.println("Pisin aika:                     "
                + Suorituskykytyokalut.suurin(ajat) + " ms.");
        System.out.println();
        
        ajat = ajanotto(ALKIOITA, TESTI_ARRAY_DEQUE_LUKU);
        System.out.println("ArrayDeque lukuajat:");
        System.out.println("Keskimääräinen aika:            "
                + Suorituskykytyokalut.keskiarvo(ajat) + " ms.");
        System.out.println("Lyhyin aika:                    "
                + Suorituskykytyokalut.pienin(ajat) + " ms.");
        System.out.println("Pisin aika:                     "
                + Suorituskykytyokalut.suurin(ajat) + " ms.");
        System.out.println();
        
        muistia = Runtime.getRuntime().freeMemory();
        Runtime.getRuntime().gc();
        System.out.println("ArrayDequen muistinkulutus:  "
                + (Runtime.getRuntime().freeMemory() - muistia) + " tavua.");
        System.out.println("========================================" +
                "========================================");
    }

    private static long[] ajanotto(final int ALKIOITA, final int TESTIN_NUMERO) {
        long aloitushetki;
        long[] ajat = new long[10];
        if (TESTIN_NUMERO == TESTI_JONO_KIRJOITUS) {
            for (int i = 0; i < 10; i++) {
                jono = null;
                Runtime.getRuntime().gc();
                jono = new Jono<>();
                aloitushetki = System.currentTimeMillis();
                taytaJono(ALKIOITA);
                ajat[i] = System.currentTimeMillis() - aloitushetki;
            }
        } else if (TESTIN_NUMERO == TESTI_JONO_LUKU) {
            jono = null;
            Runtime.getRuntime().gc();
            jono = new Jono<>();
            for (int i = 0; i < 10; i++) {
                taytaJono(ALKIOITA);
                aloitushetki = System.currentTimeMillis();
                for (int j = 0; j < ALKIOITA; j++) {
                    jono.poista();
                }
                ajat[i] = System.currentTimeMillis() - aloitushetki;
            }
        } else if (TESTIN_NUMERO == TESTI_ARRAY_DEQUE_KIRJOITUS) {
            for (int i = 0; i < 10; i++) {
                ad = null;
                Runtime.getRuntime().gc();
                ad = new ArrayDeque<>();
                aloitushetki = System.currentTimeMillis();
                taytaAD(ALKIOITA);
                ajat[i] = System.currentTimeMillis() - aloitushetki;
            }
        } else if (TESTIN_NUMERO == TESTI_ARRAY_DEQUE_LUKU) {
            ad = null;
            Runtime.getRuntime().gc();
            ad = new ArrayDeque<>();
            for (int i = 0; i < 10; i++) {
                taytaAD(ALKIOITA);
                aloitushetki = System.currentTimeMillis();
                for (int j = 0; j < ALKIOITA; j++) {
                    ad.poll();
                }
                ajat[i] = System.currentTimeMillis() - aloitushetki;
            }
        }
        return ajat;
    }
    
    private static void taytaJono(final int AVAIMIA) {
        for (int i = 0; i < AVAIMIA; i++) {
            jono.lisaa(AVAIMIA);
        }
    }
    
    private static void taytaAD(final int AVAIMIA) {
        for (int i = 0; i < AVAIMIA; i++) {
            ad.add(i);
        }
    }
    
}
