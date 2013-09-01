package fi.jw.cs.tiralabra.cli;

import fi.jw.cs.tiralabra.BinaryTreeMap;
import fi.jw.cs.tiralabra.Huffman;
import fi.jw.cs.tiralabra.SimplePriorityQueue;
import fi.jw.cs.tiralabra.Steganographer;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

/**
 * Simple performance tests in a console application
 *
 * @author Jan Wikholm <jw@jw.fi>
 * @since 2013-08-13
 */
public class PerformanceTester {
    public static void main(String... args) throws IOException {
        int n = 200000000;
		int inc = 3333333;
//        String[][] randomStrings = generateStrings(n);
//        String[] included = randomStrings[0];
//        String[] excluded = randomStrings[1];
        //testQueues(n, included, excluded);
        //testMaps(n, included, excluded);
//        testQueueScaling();

//		testHuffmanPureN(n,inc);
//		testHuffmanSingleRandom(n,inc);
//		testHuffmanRandom(n,inc);

        testSteganoPerformance(n, inc);
    }

    private static void testSteganoPerformance(int max, int incrementer) throws IOException {
        System.out.println("Testing Stegano performance");
        int current = 1;

        int pixels = max / 3;
        int height = pixels / 10;
        int width = 10;
        int repeat = 10;
        System.out.println("Setting up blank image of " + width + "x" + height);
        BufferedImage img = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);

        String message = getTimesN("1", max);
        while (current < max) {
            String msg = message.substring(0, current);

            double durationAVG = 0;

            for (int r = 0; r < repeat; r++) {
                Steganographer stego = new Steganographer();
                stego.setPath("foo");
                stego.setImage(img);
                stego.setMessage(msg);

                long start = System.currentTimeMillis();

                stego.encode();
                long end = System.currentTimeMillis();
                durationAVG += (end - start);
            }

            double duration = durationAVG / repeat;
            System.out.println(current + "," + (duration));
            current += incrementer;
        }
    }

	private static void testHuffmanPureN(int max, int incrementer) {
		System.out.println("Test Huffman pure n size");
		int current = 10;



		while (current < max) {
			String msg = getTimesN("A", current);
			long start = System.currentTimeMillis();
			Huffman h = new Huffman(msg);
			h.encode();
			long end = System.currentTimeMillis();

			System.out.println(current + "," + (end-start));
			current += incrementer;
		}

	}
	private static void testHuffmanRandom(int max, int incrementer) {
		System.out.println("Test Huffman Random");
		int current = 10;

		List<Long> times = new ArrayList<>();


		while (current < max) {
			String msg = getRandomTimesN(current);
			long start = System.currentTimeMillis();
			Huffman h = new Huffman(msg);
			h.encode();
			long end = System.currentTimeMillis();
			System.out.println(current + "," + (end-start));
			current += incrementer;
		}

	}

	private static void testHuffmanSingleRandom(int max, int incrementer) {
		System.out.println("Test Huffman Single Random");
		int current = 10;

		String wholeRandom = getRandomTimesN(max);
		long[] times = new long[max];
		while (current < max) {
			String msg = wholeRandom.substring(0,current);
			long start = System.currentTimeMillis();
			Huffman h = new Huffman(msg);
			h.encode();
			long end = System.currentTimeMillis();
			System.out.println(current + "," + (end-start));
			current += incrementer;
		}

	}

	private static String getTimesN(String str, int n) {
		StringBuilder sb = new StringBuilder(n);
		int i = 0;
		while (i++ < n)
			sb.append(str);

		return sb.toString();
	}
	private static String getRandomTimesN(int n) {
		StringBuilder sb = new StringBuilder(n);
		int i = 0;
		Random r = new Random();
		while (i++ < n)
			sb.append((char)r.nextInt(255));

		return sb.toString();
	}

    private static void testQueueScaling() {
        int repetitions = 10;
        long[] durations = new long[repetitions];
        final int startN = 1000000;
        int num = startN;
        int incrementer = startN;
        int cycles = 5;

        for (int cycle = 0; cycle < cycles; cycle++) {
            System.out.println("Cycle " + cycle);
            num = startN;
            for (int i = 1; i <= repetitions; i++) {
                num += incrementer;
                System.out.println("Repeating at " + i + " num = " + num);
                long start = System.currentTimeMillis();
                SimplePriorityQueue<Integer> q = new SimplePriorityQueue<Integer>();
                for (int j = 0; j < num; j++) {
                    q.add(j);
                }
                while (!q.isEmpty()) {
                    q.poll();
                }
                long duration = System.currentTimeMillis() - start;
                durations[i - 1] += duration;
            }
        }

        num = startN;
        for (int i = 0; i < repetitions; i++) {
            num += incrementer;
            double avgdur = (double) durations[i] / (double) cycles;
            System.out.println(num + "\t => " + avgdur + " ms\t=" + (avgdur / num) + "/item");
        }


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
