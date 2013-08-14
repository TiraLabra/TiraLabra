package fi.jw.cs.tiralabra.cli;

import fi.jw.cs.tiralabra.SimplePriorityQueue;

import java.util.PriorityQueue;

/**
 * @author Jan Wikholm <jw@jw.fi>
 * @since 2013-08-13
 */
public class PerformanceTester {
    public static void main(String... args) {
        int n = 1000000;
        String[][] randomStrings = generateStrings(n);
        String[] included = randomStrings[0];
        String[] excluded = randomStrings[1];

        String[] pqResult = new String[n];
        String[] spqResult = new String[n];

        PriorityQueue<String> pq = new PriorityQueue<>(100);
        SimplePriorityQueue<String> spq = new SimplePriorityQueue<>();

        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            pq.add(included[i]);
        }
        long added = System.currentTimeMillis();

        for (int i = 0; i < n; i++) {
            pqResult[i] = pq.poll();
        }

        long polled = System.currentTimeMillis();

        for (int i = 0; i < n; i++) {
            spq.add(included[i]);
        }
        long sadded = System.currentTimeMillis();

        for (int i = 0; i < n; i++) {
            spqResult[i] = spq.poll();
        }

        long spolled = System.currentTimeMillis();

        int same = 0;

        for (int i = 0; i < n; i++) {
            if (pqResult[i] == spqResult[i])
                same++;
        }

        System.out.println("PQ  add  " + (added - start));
        System.out.println("SPQ add  " + (sadded - polled));

        System.out.println("");

        System.out.println("PQ  poll " + (polled - added));
        System.out.println("SPQ poll " + (spolled - sadded));

        System.out.println("");

        System.out.println("Same result: " + same + " / " + n);


    }

    static String[][] generateStrings(int count) {
        String[] inc = new String[count];
        String[] exc = new String[count];

        for (int i = 0; i < count; i++) {
            String ok = i + "ok";
            String not = i + "not";
            inc[i] = ok;
            exc[i] = not;
        }

        String[][] strs = new String[2][count];
        strs[0] = inc;
        strs[1] = exc;
        return strs;
    }
}
