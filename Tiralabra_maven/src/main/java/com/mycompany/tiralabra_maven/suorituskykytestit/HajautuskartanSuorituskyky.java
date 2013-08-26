
package com.mycompany.tiralabra_maven.suorituskykytestit;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mycompany.tiralabra_maven.tietorakenteet.Hajautuskartta;

/**
 * Tämä luokka vertaa luokkien <b>Hajautuskartta</b> ja <b>HashMap</b>
 * suorituskykyä keskenään alkioiden lisäyksen ja luvun kestossa sekä muistin
 * kulutuksessa.
 *
 * @author John Lång <jllang@cs.helsinki.fi>
 */
public final class HajautuskartanSuorituskyky {
    
    private static final char[] MERKIT = "0123456789abcdefghijklmnopqrstuvwxyzåäöABCDEFGHIJKLMNOPQRSTUVWXYZÅÄÖ+-*/%^|()?".toCharArray();
    private static final int TESTI_HAJAUTUSKARTTA_KIRJOITUS = 0;
    private static final int TESTI_HAJAUTUSKARTTA_LUKU      = 1;
    private static final int TESTI_HASHMAP_KIRJOITUS        = 2;
    private static final int TESTI_HASHMAP_LUKU             = 3;
    private static Hajautuskartta<Integer>     hk;
//    private static HashMap<Character, Integer> hm;
    // Päädyin vaihtamaan Hajatuskartan kanssa vertailtavan luokan Googlen Guava
    // -kirjaston HashMultimap:iksi, koska se lienee toiminnaltaan lähempänä
    // omaa implementaatiotani.
    private static Multimap<Character, Integer> hmm;
    private static int hajautustaulunPituus;
    
    /**
     * Aloittaa suorituskyvyn testaamisen.
     */
    public static void aloita() {
        hajautustaulunPituus = 1543;
        testaa(1000);
        hajautustaulunPituus = 12289;
        testaa(10000);
        hajautustaulunPituus = 196613;
        testaa(100000);
        hajautustaulunPituus = 1572871;
        testaa(1000000);
        hajautustaulunPituus = 12582917;
        testaa(10000000);
//        hajautustaulunPituus = 100663319;
//        testaa(100000000);
    }
    
    private static void testaa(final int ALKIOITA) {
        System.out.println("Aloitetaan Hajautuskartan suorituskykytestaus "
                + ALKIOITA + " avain-arvoparilla.");
        System.out.println("----------------------------------------" +
                "----------------------------------------");
        long[] ajat;
        
        ajat = ajanotto(ALKIOITA, TESTI_HAJAUTUSKARTTA_KIRJOITUS);
        System.out.println("Hajautuskartan kirjoitusajat:");
        System.out.println("Keskimääräinen aika:            "
                + Suorituskykytyokalut.keskiarvo(ajat) + " ms.");
        System.out.println("Lyhyin aika:                    "
                + Suorituskykytyokalut.pienin(ajat) + " ms.");
        System.out.println("Pisin aika:                     "
                + Suorituskykytyokalut.suurin(ajat) + " ms.");
        System.out.println();
        
        ajat = ajanotto(ALKIOITA, TESTI_HAJAUTUSKARTTA_LUKU);
        System.out.println("Hajautuskartan lukuajat:");
        System.out.println("Keskimääräinen aika:            "
                + Suorituskykytyokalut.keskiarvo(ajat) + " ms.");
        System.out.println("Lyhyin aika:                    "
                + Suorituskykytyokalut.pienin(ajat) + " ms.");
        System.out.println("Pisin aika:                     "
                + Suorituskykytyokalut.suurin(ajat) + " ms.");
        System.out.println();
        
        long muistia = Runtime.getRuntime().freeMemory();
        hk = null;
        Runtime.getRuntime().gc();
        System.out.println("Hajautuskartan muistinkulutus:  "
                + (Runtime.getRuntime().freeMemory() - muistia) + " tavua.");
        
        System.out.println('\n');
        
        ajat = ajanotto(ALKIOITA, TESTI_HASHMAP_KIRJOITUS);
        System.out.println("HashMultimapin kirjoitusajat:");
        System.out.println("Keskimääräinen aika:            "
                + Suorituskykytyokalut.keskiarvo(ajat) + " ms.");
        System.out.println("Lyhyin aika:                    "
                + Suorituskykytyokalut.pienin(ajat) + " ms.");
        System.out.println("Pisin aika:                     "
                + Suorituskykytyokalut.suurin(ajat) + " ms.");
        System.out.println();
        
        ajat = ajanotto(ALKIOITA, TESTI_HASHMAP_LUKU);
        System.out.println("HashMultimapin lukuajat:");
        System.out.println("Keskimääräinen aika:            "
                + Suorituskykytyokalut.keskiarvo(ajat) + " ms.");
        System.out.println("Lyhyin aika:                    "
                + Suorituskykytyokalut.pienin(ajat) + " ms.");
        System.out.println("Pisin aika:                     "
                + Suorituskykytyokalut.suurin(ajat) + " ms.");
        System.out.println();
        
        muistia = Runtime.getRuntime().freeMemory();
//        hm = null;
        hmm = null;
        Runtime.getRuntime().gc();
        System.out.println("HashMultimapin muistinkulutus:  "
                + (Runtime.getRuntime().freeMemory() - muistia) + " tavua.");
        System.out.println("========================================" +
                "========================================");
    }

    private static long[] ajanotto(final int ALKIOITA, final int TESTIN_NUMERO) {
        long aloitushetki;
        long[] ajat = new long[10];
        if (TESTIN_NUMERO == TESTI_HAJAUTUSKARTTA_KIRJOITUS) {
            for (int i = 0; i < 10; i++) {
                hk = null;
                Runtime.getRuntime().gc();
                hk = new Hajautuskartta<>(hajautustaulunPituus);
                aloitushetki = System.currentTimeMillis();
                for (int j = 0; j < ALKIOITA; j++) {
                    hk.lisaa(MERKIT[j % 78], j);
                }
                ajat[i] = System.currentTimeMillis() - aloitushetki;
            }
        } else if (TESTIN_NUMERO == TESTI_HAJAUTUSKARTTA_LUKU) {
            for (int i = 0; i < 10; i++) {
                aloitushetki = System.currentTimeMillis();
                for (int j = 0; j < ALKIOITA; j++) {
                    hk.haeEnsimmainen(MERKIT[j % 78]);
                }
                ajat[i] = System.currentTimeMillis() - aloitushetki;
            }
        } else if (TESTIN_NUMERO == TESTI_HASHMAP_KIRJOITUS) {
            for (int i = 0; i < 10; i++) {
//                hm = null;
                hmm = null;
                Runtime.getRuntime().gc();
//                hm = new HashMap<>();
                hmm = HashMultimap.create();
                aloitushetki = System.currentTimeMillis();
                for (int j = 0; j < ALKIOITA; j++) {
//                    hm.put(MERKIT[j % 78], j);
                    hmm.put(MERKIT[j % 78], j);
                }
                ajat[i] = System.currentTimeMillis() - aloitushetki;
            }
        } else if (TESTIN_NUMERO == TESTI_HASHMAP_LUKU) {
            for (int i = 0; i < 10; i++) {
                aloitushetki = System.currentTimeMillis();
                for (int j = 0; j < ALKIOITA; j++) {
//                    hm.get(MERKIT[j % 78]);
                    hmm.get(MERKIT[j % 78]);
                }
                ajat[i] = System.currentTimeMillis() - aloitushetki;
            }
        }
        return ajat;
    }
    
}
