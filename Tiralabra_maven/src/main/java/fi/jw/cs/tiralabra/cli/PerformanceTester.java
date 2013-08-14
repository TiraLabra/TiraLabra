package fi.jw.cs.tiralabra.cli;

import fi.jw.cs.tiralabra.BinaryTreeMap;
import fi.jw.cs.tiralabra.SimplePriorityQueue;

import java.util.HashMap;
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
        testQueues(n, included, excluded);
        testMaps(n, included, excluded);

    }

    public static void testMaps(int n, String[] included, String[] excluded) {
        HashMap<String, String> hm = new HashMap<>();
        BinaryTreeMap btm = new BinaryTreeMap();

        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            hm.put(included[i], excluded[i]);
        }
        long added = System.currentTimeMillis();

        int includedFound = 0;
        int includedNotFound = 0;
        int excludedFound = 0;
        int excludedNotFound = 0;
        for (int i = 0; i < n; i++) {
            if (hm.containsKey(included[i])) {
                includedFound++;
            } else {
                includedNotFound++;
            }

            if (hm.containsKey(excluded[i])) {
                excludedFound++;
            } else {
                excludedNotFound++;
            }
        }

        long searchDone = System.currentTimeMillis();

        for (int i = 0; i < n; i++) {
            btm.put(included[i], excluded[i]);
        }
        long badded = System.currentTimeMillis();

        int bincludedFound = 0;
        int bincludedNotFound = 0;
        int bexcludedFound = 0;
        int bexcludedNotFound = 0;
        for (int i = 0; i < n; i++) {
            if (btm.containsKey(included[i])) {
                bincludedFound++;
            } else {
                bincludedNotFound++;
            }

            if (btm.containsKey(excluded[i])) {
                bexcludedFound++;
            } else {
                bexcludedNotFound++;
            }
        }

        long bsearchDone = System.currentTimeMillis();
        System.out.println("HM  add  " + (added - start));
        System.out.println("BTM add  " + (badded - searchDone));

        System.out.println("");

        System.out.println("HM  poll " + (searchDone - added));
        System.out.println("BTM poll " + (bsearchDone - badded));

        System.out.println("");

        System.out.println("HM  found " + includedFound + " | " + excludedFound);
        System.out.println("BTM found " + bincludedFound + " | " + bexcludedFound);

        System.out.println("HM  !found " + includedNotFound + " | " + excludedNotFound);
        System.out.println("BTM !found " + bincludedNotFound + " | " + bexcludedNotFound);
        System.out.println("");
    }

    public static void testQueues(int n, String[] included, String[] excluded) {

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
