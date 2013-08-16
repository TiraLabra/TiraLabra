

package Nopeustestit;

import Tietorakenteet.OmaHashMap;
import Tietorakenteet.OmaMap;
import java.util.HashMap;
import java.util.Map;


public class HashMapVertailu {
    private final int SISA_LOOP_KOKO = 100000;
    private final int ULKO_LOOP_KOKO = 1000;

    public void vertaile() {
        long omaStringAika = 0;
        long omaIntAika = 0;
        long javaStringAika = 0;
        long javaIntAika = 0;
        for (int i = 0; i < ULKO_LOOP_KOKO; ++i) {
            omaStringAika += testaaOmaHashMapString();
            javaStringAika += testaaJavaHashMapString();
            omaIntAika += testaaOmaHashMapInt();
            javaIntAika += testaaJavaHashMapInt();
        }
        
        System.out.println("String: Javan hashmapin    suoritukseen kului " + javaStringAika/1000000 + "ms");
        System.out.println("String: Oman hashmapin suoritukseen kului " + omaStringAika/1000000 + "ms");
        
        System.out.println("Int: Javan hashmapin suoritukseen kului " + javaIntAika/1000000 + "ms");
        System.out.println("Int: Oman hashmapin suoritukseen kului " + omaIntAika/1000000 + "ms");
        
    }

    private long testaaOmaHashMapString() {
        long alku = System.nanoTime();
        OmaMap<String, Integer> lista = new OmaHashMap<String, Integer>();

        for (int i = 0; i < SISA_LOOP_KOKO; ++i) {
            lista.put("loop" + i, i);
        }

        return System.nanoTime() - alku;
    }

    private long testaaJavaHashMapString() {
        long alku = System.nanoTime();
        Map<String, Integer> lista = new HashMap<String, Integer>();
        
        for (int i = 0; i < SISA_LOOP_KOKO; ++i) {
            lista.put("loop" + i, i);
        }

        return System.nanoTime() - alku;
    }
    
     private long testaaOmaHashMapInt() {
        long alku = System.nanoTime();
        OmaMap<Integer, Integer> lista = new OmaHashMap<Integer, Integer>();

        for (int i = 0; i < SISA_LOOP_KOKO; ++i) {
            lista.put(i, i);
        }

        return System.nanoTime() - alku;
    }

    private long testaaJavaHashMapInt() {
        long alku = System.nanoTime();
        Map<Integer, Integer> lista = new HashMap<Integer, Integer>();

        for (int i = 0; i < SISA_LOOP_KOKO; ++i) {
            lista.put(i, i);
        }

        return System.nanoTime() - alku;
    }
}
