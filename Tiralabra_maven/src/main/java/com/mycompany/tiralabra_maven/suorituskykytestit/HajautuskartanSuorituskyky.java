/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.suorituskykytestit;

import com.mycompany.tiralabra_maven.tietorakenteet.Hajautuskartta;
import java.util.HashMap;

/**
 * Tämä luokka vertaa luokkien <b>Hajautuskartta</b> ja <b>HashMap</b>
 * suorituskykyä keskenään alkioiden lisäyksen ja luvun kestossa sekä muistin
 * kulutuksessa.
 *
 * @author John Lång <jllang@cs.helsinki.fi>
 */
public final class HajautuskartanSuorituskyky {
    
    private static final String MERKIT = "0123456789abcdefghijklmnopqrstuvwxyzåäöABCDEFGHIJKLMNOPQRSTUVWXYZÅÄÖ+-*/%^|()?";
    private static final char[] MERKIT2 = MERKIT.toCharArray(); // Tämä ei taida olla lukemisen kannalta Stringiä nopeampi...
    private static final int TESTI_HAJAUTUSKARTTA_KIRJOITUS = 0;
    private static final int TESTI_HAJAUTUSKARTTA_LUKU      = 1;
    private static final int TESTI_HASHMAP_KIRJOITUS        = 2;
    private static final int TESTI_HASHMAP_LUKU             = 3;
    private static Hajautuskartta<Integer>     hk;
    private static HashMap<Character, Integer> hm;
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
                + keskiarvo(ajat) + " ms.");
        System.out.println("Lyhyin aika:                    " + pienin(ajat)
                + " ms.");
        System.out.println("Pisin aika:                     " + suurin(ajat)
                + " ms.");
        System.out.println();
        
        ajat = ajanotto(ALKIOITA, TESTI_HAJAUTUSKARTTA_LUKU);
        System.out.println("Hajautuskartan lukuajat:");
        System.out.println("Keskimääräinen aika:            "
                + keskiarvo(ajat) + " ms.");
        System.out.println("Lyhyin aika:                    " + pienin(ajat)
                + " ms.");
        System.out.println("Pisin aika:                     " + suurin(ajat)
                + " ms.");
        System.out.println();
        
        long muistia = Runtime.getRuntime().freeMemory();
        hk = null;
        Runtime.getRuntime().gc();
        System.out.println("Hajautuskartan muistinkulutus:  "
                + (Runtime.getRuntime().freeMemory() - muistia) + " tavua.");
        
        System.out.println('\n');
        
        ajat = ajanotto(ALKIOITA, TESTI_HASHMAP_KIRJOITUS);
        System.out.println("HashMapin kirjoitusajat:");
        System.out.println("Keskimääräinen aika:            "
                + keskiarvo(ajat) + " ms.");
        System.out.println("Lyhyin aika:                    " + pienin(ajat)
                + " ms.");
        System.out.println("Pisin aika:                     " + suurin(ajat)
                + " ms.");
        System.out.println();
        
        ajat = ajanotto(ALKIOITA, TESTI_HASHMAP_LUKU);
        System.out.println("HashMapin lukuajat:");
        System.out.println("Keskimääräinen aika:            "
                + keskiarvo(ajat) + " ms.");
        System.out.println("Lyhyin aika:                    " + pienin(ajat)
                + " ms.");
        System.out.println("Pisin aika:                     " + suurin(ajat)
                + " ms.");
        System.out.println();
        
        muistia = Runtime.getRuntime().freeMemory();
        hm = null;
        Runtime.getRuntime().gc();
        System.out.println("HashMapin muistinkulutus:       "
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
                    hk.lisaa(MERKIT2[j % 78], j);
                }
                ajat[i] = System.currentTimeMillis() - aloitushetki;
            }
        } else if (TESTIN_NUMERO == TESTI_HAJAUTUSKARTTA_LUKU) {
            for (int i = 0; i < 10; i++) {
                aloitushetki = System.currentTimeMillis();
                for (int j = 0; j < ALKIOITA; j++) {
                    hk.haeEnsimmainen(MERKIT2[j % 78]);
                }
                ajat[i] = System.currentTimeMillis() - aloitushetki;
            }
        } else if (TESTIN_NUMERO == TESTI_HASHMAP_KIRJOITUS) {
            for (int i = 0; i < 10; i++) {
                hm = null;
                Runtime.getRuntime().gc();
                hm = new HashMap<>();
                aloitushetki = System.currentTimeMillis();
                for (int j = 0; j < ALKIOITA; j++) {
                    hm.put(MERKIT2[j % 78], j);
                }
                ajat[i] = System.currentTimeMillis() - aloitushetki;
            }
        } else if (TESTIN_NUMERO == TESTI_HASHMAP_LUKU) {
            for (int i = 0; i < 10; i++) {
                aloitushetki = System.currentTimeMillis();
                for (int j = 0; j < ALKIOITA; j++) {
                    hm.get(MERKIT2[j % 78]);
                }
                ajat[i] = System.currentTimeMillis() - aloitushetki;
            }
        }
        return ajat;
    }
    
    private static float keskiarvo(final long[] LUVUT) {
        long summa = 0;
        for (long l : LUVUT) {
            summa += l;
        }
        return summa / 10.0f;
    }
    
    private static long pienin(final long[] LUVUT) {
        long pienin = Long.MAX_VALUE;
        
        for (long l : LUVUT) {
            if (l < pienin) {
                pienin = l;
            }
        }
        
        return pienin;
    }
    
    private static long suurin(final long[] LUVUT) {
        long suurin = 0;
        
        for (long l : LUVUT) {
            if (l > suurin) {
                suurin = l;
            }
        }
        
        return suurin;
    }
    
}
