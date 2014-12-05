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
        astar.run(Map2());

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


    public static String Map1() {
        return "##############################\n" +
                "#o#_______________________##_#\n" +
                "#_#_######################_#_#\n" +
                "#_#_#______________________#_#\n" +
                "#_#_#_##################_#_#_#\n" +
                "#_#_#_#__________________#_#_#\n" +
                "#_#_#_##################_#_#_#\n" +
                "#_#_#__________________#_#_#_#\n" +
                "#_#_#_##################_#_#_#\n" +
                "#________________________#x__#\n" +
                "##############################";
    }

    public static String Map2() {
        return "##############################\n" +
                "#o____x______________________#\n" +
                "#_#_######################_#_#\n" +
                "#_#_#______________________#_#\n" +
                "#_#_#_##################_#_#_#\n" +
                "#_#_#_#__________________#_#_#\n" +
                "#_#_#_##################_#_#_#\n" +
                "#_#_#__________________#_#_#_#\n" +
                "#_#_#_##################_#_#_#\n" +
                "#________________________#___#\n" +
                "##############################";
    }

    public String Map3() {
        return "##############################\n" +
                "#o#_______________________##_#\n" +
                "#_#_######################_#_#\n" +
                "#_#_#______________________#_#\n" +
                "#_#_#_##################_#_#_#\n" +
                "#_#_#_#__________________#_#_#\n" +
                "#_#_#_##################_#_#_#\n" +
                "#_#_#__________________#_#_#_#\n" +
                "#_#_#_##################_#_#_#\n" +
                "#________________________#x__#\n" +
                "##############################";
    }


}
