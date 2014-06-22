/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.utilities;

import java.util.Random;
import tiralabra.utilities.ArrayList;

/**
 * Class for testing ArrayLists performance.
 *
 * @author atte
 */
public class ArrayListPerformance {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        Random random = new Random();

        int operations = 10000;
        addPerformance(list, 10000);
        removePerformance(list, 2000);
    }

    /**
     * Performance tests for add operation.
     *
     * @param list
     */
    private static void addPerformance(ArrayList<Integer> list, int operations) {
        long max = Long.MIN_VALUE;
        long min = Long.MAX_VALUE;
        long sum = 0;

        for (int i = 0; i < operations; i++) {
            long start = System.currentTimeMillis();
            list.add(i);
            long timeTaken = System.currentTimeMillis() - start;
            sum += timeTaken;

            if (timeTaken > max) {
                max = timeTaken;
            }

            if (timeTaken < min) {
                min = timeTaken;
            }
        }

        System.out.println("Maximum time taken: " + max);
        System.out.println("Minimum time taken: " + min);
        System.out.println("Average time taken: " + (double) sum / operations);
    }

    /**
     * Performance tests for the remove operation.
     * @param list
     * @param operations 
     */
    private static void removePerformance(ArrayList<Integer> list, int operations) {
        long max = Long.MIN_VALUE;
        long min = Long.MAX_VALUE;
        long sum = 0;

        for (int i = list.size(); i > list.size() - operations; i--) {
            long start = System.currentTimeMillis();
            list.remove(list.size() - i);
            long timeTaken = System.currentTimeMillis() - start;
            sum += timeTaken;

            if (timeTaken > max) {
                max = timeTaken;
            }

            if (timeTaken < min) {
                min = timeTaken;
            }
        }

        System.out.println("Maximum time taken: " + max);
        System.out.println("Minimum time taken: " + min);
        System.out.println("Average time taken: " + (double) sum / operations);
    }

}
