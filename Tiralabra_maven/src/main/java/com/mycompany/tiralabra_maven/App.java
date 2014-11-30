package com.mycompany.tiralabra_maven;

/**
 * Main method for the Astar implementation
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("** Labyrinth **");
        Astar astar = new Astar();
        astar.run();

        /** For performance testing: */
        /*
        long aikaAlussa2 = System.currentTimeMillis();
        for (int j = 0; j < 5; j++) {
            long aikaAlussa = System.currentTimeMillis();
            for (int i = 0; i < 100000; i++) {
                astar.run();
            }
            long aikaLopussa = System.currentTimeMillis();
            System.out.println("Operaatioon kului aikaa: " + (aikaLopussa - aikaAlussa) + "ms.");
            System.out.println("Aikaa keskimäärin per operaatio: " + (aikaLopussa - aikaAlussa)/100000 + "ms.");
        }
        long aikaLopussa2 = System.currentTimeMillis();
        System.out.println("Operaatioon kului aikaa: " + (aikaLopussa2 - aikaAlussa2) + "ms.");
        System.out.println("Aikaa keskimäärin per operaatio: " + (aikaLopussa2 - aikaAlussa2)/5 + "ms.");
        */
    }
}
