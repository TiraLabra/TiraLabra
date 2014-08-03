/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 2.8.2014 
 */
package TestSuite;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Marko <markoma@iki.fi>
 */
public class main {

    public static void main(String[] args) {

        int[] a = randomNum(100000);

        long sum = 0;

        for (int i = 0; i < 100000; i++) {
            sum += timer(randomNum(10000));
        }
        System.out.println("average: " + (sum / 100000) / 1000 + " microseconds");
    }

    public static long timer(int[] a) {
        long start = System.nanoTime();

        Arrays.sort(a);

        long result = (System.nanoTime() - start);

        //System.out.println(result + " nanoseconds");
        return result;
    }

    public static int[] randomNum(int lenght) {
        int[] taulukko = new int[lenght];
        Random r = new Random();
        for (int i = 0; i < taulukko.length; i++) {
            taulukko[i] = r.nextInt(lenght);
        }
        return taulukko;
    }
}
