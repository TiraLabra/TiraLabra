package tiralabra_maven;

import java.util.Arrays;
import java.util.Random;

/**
 * Luokka joka suorittaa ohjelman
 *
 * @author esaaksvu
 */
public class Main {

    public static void main(String[] args) {
        PuuRajapinta avl = new AVLHakuPuu();
        PuuRajapinta bin = new BinaariHakupuu();
        PuuRajapinta rb = new PunMusPuu();

        int count = 100000;
        Random r = new Random();
        int[] testNumbers = new int[count];
        for (int i = 0; i < count; i++) {
            testNumbers[i] = i;
        }

        for (int j = 1; j < 4; j++) {
            if (j==1) System.out.println("Punamustapuu___________");
            if (j == 2) {
                rb = new AVLHakuPuu();
                System.out.println("AVLPUU_________________");
            }
            if (j == 3) {
                rb = new BinaariHakupuu();
                System.out.println("Binäärihakupuu_________");
            }

            System.out.println("Adding " + count + " numbers to tree...");
            for (int i : testNumbers) {
                rb.lisaaSolmu(new Solmu(i));
            }
            System.out.println("Starting removing...");
            long time_avg = 0;
            for (int i = 0; i < 1000; i++) {
                long rb_start = System.nanoTime();
                rb.poistaSolmu(testNumbers[r.nextInt(count)]);
                time_avg = time_avg + System.nanoTime() - rb_start;

            }

            System.out.println(time_avg / 1000 + "ns per each");
            System.out.println("Add 1000 more....");
            time_avg = 0;
            for (int i = 0; i < 1000; i++) {
                long rb_start = System.nanoTime();
                rb.lisaaSolmu(new Solmu(testNumbers[r.nextInt(count)]));
                time_avg = time_avg + System.nanoTime() - rb_start;
            }
            System.out.println(time_avg/1000 + "ns per each");
        }
    }
}
