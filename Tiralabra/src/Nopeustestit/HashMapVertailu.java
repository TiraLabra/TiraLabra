package Nopeustestit;

import Tietorakenteet.OmaHashMap;
import Tietorakenteet.OmaMap;
import java.util.HashMap;
import java.util.Map;

public class HashMapVertailu {

    private final int SISA_LOOP_KOKO = 100000;
    private final int ULKO_LOOP_KOKO = 1000;

    public void vertailePut() {
        System.out.println("Put-vertailu");
        long omaStringAika = 0;
        long omaIntAika = 0;
        long javaStringAika = 0;
        long javaIntAika = 0;
        for (int i = 0; i < ULKO_LOOP_KOKO; ++i) {
            omaStringAika += testaaOmaHashMapStringPut();
            javaStringAika += testaaJavaHashMapStringPut();
            omaIntAika += testaaOmaHashMapIntPut();
            javaIntAika += testaaJavaHashMapIntPut();
        }

        System.out.println("String: Javan hashmapin suoritukseen kului " + javaStringAika / 1000000 + "ms");
        System.out.println("String: Oman hashmapin suoritukseen kului " + omaStringAika / 1000000 + "ms");

        System.out.println("Int: Javan hashmapin suoritukseen kului " + javaIntAika / 1000000 + "ms");
        System.out.println("Int: Oman hashmapin suoritukseen kului " + omaIntAika / 1000000 + "ms");

    }

    public void vertaileGet() {
        System.out.println("Get-vertailu");
        
        
        OmaHashMap<Integer, Integer> omaIntMap = new OmaHashMap<Integer, Integer>();
        HashMap<Integer, Integer> javaIntMap = new HashMap<Integer, Integer>();
        
        OmaHashMap<String, Integer> omaStringMap = new OmaHashMap<String, Integer>();
        HashMap<String, Integer> javaStringMap = new HashMap<String, Integer>();
        
        alustaGetMapit(omaIntMap, javaIntMap, omaStringMap, javaStringMap);
        getAjanLasku(omaStringMap, javaStringMap, omaIntMap, javaIntMap);
    }

    private long testaaOmaHashMapStringPut() {
        
        OmaHashMap<String, Integer> lista = new OmaHashMap<String, Integer>();
        long alku = System.nanoTime();
        for (int i = 0; i < SISA_LOOP_KOKO; ++i) {
            lista.put("loop" + i, i);
        }

        return System.nanoTime() - alku;
    }

    private long testaaJavaHashMapStringPut() {
        
        HashMap<String, Integer> lista = new HashMap<String, Integer>();
        long alku = System.nanoTime();
        for (int i = 0; i < SISA_LOOP_KOKO; ++i) {
            lista.put("loop" + i, i);
        }

        return System.nanoTime() - alku;
    }

    private long testaaOmaHashMapIntPut() {
        
        OmaHashMap<Integer, Integer> lista = new OmaHashMap<Integer, Integer>();
        long alku = System.nanoTime();
        for (int i = 0; i < SISA_LOOP_KOKO; ++i) {
            lista.put(i, i);
        }

        return System.nanoTime() - alku;
    }

    private long testaaJavaHashMapIntPut() {
        
        HashMap<Integer, Integer> lista = new HashMap<Integer, Integer>();
        long alku = System.nanoTime();
        for (int i = 0; i < SISA_LOOP_KOKO; ++i) {
            lista.put(i, i);
        }

        return System.nanoTime() - alku;
    }

    private void alustaGetMapit(OmaHashMap<Integer, Integer> omaIntMap, HashMap<Integer, Integer> javaIntMap, OmaHashMap<String, Integer> omaStringMap, HashMap<String, Integer> javaStringMap) {
        for (int i = 0; i < SISA_LOOP_KOKO; ++i) {
            omaIntMap.put(i, i);
            javaIntMap.put(i, i);
            
            omaStringMap.put("loop" + i, i);
            javaStringMap.put("loop" + i, i);
        }
    }

    private void getAjanLasku(OmaHashMap<String, Integer> omaStringMap, HashMap<String, Integer> javaStringMap, OmaHashMap<Integer, Integer> omaIntMap, HashMap<Integer, Integer> javaIntMap) {
        
        long omaStringAika = 0;
        long omaIntAika = 0;
        long javaStringAika = 0;
        long javaIntAika = 0;
        
        for (int i = 0; i < ULKO_LOOP_KOKO; ++i) {
            omaStringAika += testaaOmaHashMapStringGet(omaStringMap);
            javaStringAika += testaaJavaHashMapStringGet(javaStringMap);
            omaIntAika += testaaOmaHashMapIntGet(omaIntMap);
            javaIntAika += testaaJavaHashMapIntGet(javaIntMap);
        }

        System.out.println("String: Javan hashmapin suoritukseen kului " + javaStringAika / 1000000 + "ms");
        System.out.println("String: Oman hashmapin suoritukseen kului " + omaStringAika / 1000000 + "ms");

        System.out.println("Int: Javan hashmapin suoritukseen kului " + javaIntAika / 1000000 + "ms");
        System.out.println("Int: Oman hashmapin suoritukseen kului " + omaIntAika / 1000000 + "ms");
    }

    private long testaaOmaHashMapStringGet(OmaHashMap<String, Integer> omaStringMap) {
        long alku = System.nanoTime();
        
        for (int i = 0; i < SISA_LOOP_KOKO; ++i) {
            omaStringMap.get("loop" + i);
        }
        
        return System.nanoTime() - alku;
    }

    private long testaaJavaHashMapStringGet(HashMap<String, Integer> javaStringMap) {
        long alku = System.nanoTime();
        
        for (int i = 0; i < SISA_LOOP_KOKO; ++i) {
            javaStringMap.get("loop" + i);
        }
        
        return System.nanoTime() - alku;
    }

    private long testaaOmaHashMapIntGet(OmaHashMap<Integer, Integer> omaIntMap) {
        long alku = System.nanoTime();
        
        for (int i = 0; i < SISA_LOOP_KOKO; ++i) {
            omaIntMap.get(i);
        }
        
        return System.nanoTime() - alku;
    }

    private long testaaJavaHashMapIntGet(HashMap<Integer, Integer> javaIntMap) {
        long alku = System.nanoTime();
        
        for (int i = 0; i < SISA_LOOP_KOKO; ++i) {
            javaIntMap.get(i);
        }
        
        return System.nanoTime() - alku;
    }
    
    
}
