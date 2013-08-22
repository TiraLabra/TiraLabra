package tiralabra_maven;

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
        
        Random r = new Random();
        int [] testNumbers = new int[5000000];
        for (int i = 0; i < 5000000; i++) {
            testNumbers[i]=r.nextInt(500000);
        }
        
        System.out.println("Adding 5000000 randoms to Binary...");  
        long bin_start = System.currentTimeMillis();
        for (int i:testNumbers) bin.lisaaSolmu(new Solmu(i));
        long bin_t = System.currentTimeMillis()-bin_start;
        System.out.println("took: "+bin_t+"ms");
        
        System.out.println("Adding 5000000 randoms to AVL...");       
        long avl_start = System.currentTimeMillis();
        for (int i:testNumbers) avl.lisaaSolmu(new Solmu(i));
        long avl_t = System.currentTimeMillis()-avl_start;
        System.out.println("took: "+avl_t+"ms");
 
        System.out.println("Adding 5000000 randoms to RB...");  
        long rb_start = System.currentTimeMillis();
        for (int i:testNumbers) rb.lisaaSolmu(new Solmu(i));
        long rb_t = System.currentTimeMillis()-rb_start;
        System.out.println("took: "+rb_t+"ms");
        
        
        
        
        
    
    
    }
}
