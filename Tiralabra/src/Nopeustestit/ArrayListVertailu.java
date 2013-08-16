package Nopeustestit;

import Tietorakenteet.OmaArrayList;
import Tietorakenteet.OmaList;
import java.util.ArrayList;
import java.util.List;

public class ArrayListVertailu {

    private final int SISA_LOOP_KOKO = 1000000;
    private final int ULKO_LOOP_KOKO = 1000;

    public void vertaile() {
        long omaAika = 0;
        long javaAika = 0;
        
        for (int i = 0; i < ULKO_LOOP_KOKO; ++i) {
            omaAika += testaaOmaArrayList();
            javaAika += testaaJavaArrayList();
        }
        
        System.out.println("Javan arraylistin suoritukseen kului " + javaAika/1000000 + "ms");
        System.out.println("Oman arraylistin suoritukseen kului " + omaAika/1000000 + "ms");
        
    }

    private long testaaOmaArrayList() {
        long alku = System.nanoTime();
        OmaList<Integer> lista = new OmaArrayList<Integer>();

        for (int i = 0; i < SISA_LOOP_KOKO; ++i) {
            lista.add(i);
        }

        return System.nanoTime() - alku;
    }

    private long testaaJavaArrayList() {
        long alku = System.nanoTime();
        List<Integer> lista = new ArrayList<Integer>();

        for (int i = 0; i < SISA_LOOP_KOKO; ++i) {
            lista.add(i);
        }

        return System.nanoTime() - alku;
    }
}
