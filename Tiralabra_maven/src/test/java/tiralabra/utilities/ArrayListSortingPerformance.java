/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.utilities;

import java.util.Random;

/**
 *
 * @author atte
 */
public class ArrayListSortingPerformance {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Random random = new Random();

        ArrayList<ArrayList<Integer>> toSort = new ArrayList<>();
        int size = 1000000;
        for (int i = 0; i < 10; i++) {
            toSort.add(randomArrayList(random, size));
        }

        long max = Long.MIN_VALUE;
        long min = Long.MAX_VALUE;
        long sum = 0;
        int operations = 10;
        
        System.out.println("Sorting ArrayLists with a size of: " + size);
        System.out.print("Time taken: ");
        for (ArrayList<Integer> list : toSort) {
            long start = System.currentTimeMillis();
            ArrayListSorter.sort(list);
            long timeTaken = System.currentTimeMillis() - start;
            System.out.print(timeTaken + "ms ");

            sum += timeTaken;

            if (timeTaken > max) {
                max = timeTaken;
            }

            if (timeTaken < min) {
                min = timeTaken;
            }

        }
        System.out.println("");
        System.out.println("Maximum time taken: " + max + "ms");
        System.out.println("Minimum time taken: " + min + "ms");
        System.out.println("Average time taken: " + ((double) sum / operations)  + "ms");

    }

    public static ArrayList<Integer> randomArrayList(Random random, int size) {
        ArrayList<Integer> randomList = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            randomList.add(random.nextInt());
        }
        return randomList;
    }
}
