package com.mycompany.tiralabra_maven;

/**
 * Luokka joka suorittaa ohjelman
 * @author esaaksvu
 */
public class Main {

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        PuuRajapinta p = new BinaariHakupuu();

        p.lisaaSolmu(new Solmu(2));
        p.lisaaSolmu(new Solmu(1));
        p.lisaaSolmu(new Solmu(3));

        System.out.println(p.tulostaPuu());
        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("-------------------\n" + estimatedTime + "ns");

    }
}
