package Nopeustestit;

import Tietorakenteet.OmaMinimiPriorityQueue;
import java.util.Comparator;
import java.util.PriorityQueue;

public class QueueVertailu {

    private final int SISA_LOOP_KOKO = 1000000;
    private final int ULKO_LOOP_KOKO = 1000;

    public void vertailePush() {

        System.out.println("Push");
        long omaAika = 0;
        long javaAika = 0;
        for (int i = 0; i < ULKO_LOOP_KOKO; ++i) {
            omaAika += testaaOmaJonoPush();
            javaAika += testaaJavaJonoPush();
        }

        System.out.println("Int: Javan jonon suoritukseen kului " + javaAika / 1000000 + "ms");
        System.out.println("Int: Oman jonon suoritukseen kului " + omaAika / 1000000 + "ms");
    }

    public void vertailePop() {
        System.out.println("Pop");
        long omaAika = 0;
        long javaAika = 0;
        for (int i = 0; i < ULKO_LOOP_KOKO; ++i) {
            omaAika += testaaOmaJonoPop();
            javaAika += testaaJavaJonoPop();
        }

        System.out.println("Int: Javan jonon suoritukseen kului " + javaAika / 1000000 + "ms");
        System.out.println("Int: Oman jonon suoritukseen kului " + omaAika / 1000000 + "ms");
    }

    private OmaMinimiPriorityQueue<Integer> luoJono() {
        return new OmaMinimiPriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
    }

    private long testaaOmaJonoPush() {

        OmaMinimiPriorityQueue<Integer> omaJono = luoJono();
        long aika = System.nanoTime();
        for (int i = 0; i < SISA_LOOP_KOKO; ++i) {
            omaJono.push(i);
        }

        return System.nanoTime() - aika;
    }

    private long testaaJavaJonoPush() {

        PriorityQueue<Integer> javaJono = new PriorityQueue<Integer>();
        long aika = System.nanoTime();

        for (int i = 0; i < SISA_LOOP_KOKO / 10; ++i) {
            javaJono.add(i);
        }

        return System.nanoTime() - aika;
    }

    private long testaaOmaJonoPop() {

        OmaMinimiPriorityQueue<Integer> omaJono = luoJono();

        for (int i = 0; i < SISA_LOOP_KOKO / 10; ++i) {
            omaJono.push(i);
        }

        long aika = System.nanoTime();
        for (int i = 0; i < SISA_LOOP_KOKO / 10; ++i) {
            omaJono.pop();
        }
        return System.nanoTime() - aika;
    }

    private long testaaJavaJonoPop() {

        PriorityQueue<Integer> javaJono = new PriorityQueue<Integer>();
        

        for (int i = 0; i < SISA_LOOP_KOKO / 10; ++i) {
            javaJono.add(i);
        }
        long aika = System.nanoTime();
        
        for (int i = 0; i < SISA_LOOP_KOKO / 10; ++i) {
            javaJono.poll();
        }

        return System.nanoTime() - aika;
    }
}
