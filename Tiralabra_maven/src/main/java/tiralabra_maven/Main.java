package tiralabra_maven;

import java.util.Random;

/**
 * Luokka joka suorittaa ohjelman
 * @author esaaksvu
 */
public class Main {

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        PuuRajapinta p = new BinaariHakupuu();
        Random r = new Random();
        
        p.lisaaSolmu(new Solmu(9));
        p.lisaaSolmu(new Solmu(13));
        p.lisaaSolmu(new Solmu(2));
        p.lisaaSolmu(new Solmu(4));
        p.lisaaSolmu(new Solmu(3));
        p.lisaaSolmu(new Solmu(7));
        p.lisaaSolmu(new Solmu(6));
        p.lisaaSolmu(new Solmu(15));
        p.lisaaSolmu(new Solmu(18));
        p.lisaaSolmu(new Solmu(17));
        p.lisaaSolmu(new Solmu(20));
      
        
        
        long estimatedTime = System.nanoTime() - startTime;
        System.out.println(p.getJuuri().getArvo());
        System.out.println(p.getJuuri());
        System.out.println("-------------------\n" + estimatedTime + "ns");

    }
}
