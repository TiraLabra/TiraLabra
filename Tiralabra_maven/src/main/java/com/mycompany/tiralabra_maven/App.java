package com.mycompany.tiralabra_maven;

/**
 * Main method for the A-star implementation
 * How to use:
 * Modify String map to choose a map of your liking (map1, map2, map2)
 * Modify int i to a wanted heuristic
 * OR just user printAll()-method to print all the heuristics an the specified map
 */
public class App 
{
    public static void main( String[] args )
    {


        System.out.println(logo());

        /** @param i A checker for which heuristic to use;
         *  1 = Euclidean distance
         *  2 = Manhattan distance
         *  3 = Diagonal distance
         *  0 = Dijkstra's algorithm (no heuristic)
         */
        //int i = 0;
        String map = map1();
        //printOne(map, i);

        printAll(map);


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


    /** Runs A-star once with the wanted map and heuristic.
     *
     * @param map Map for the Astar-operation.
     * @param heuristic Heuristic used
     */
    public static void printOne(String map, int heuristic) {
        Astar astar = new Astar();
        if (heuristic == 1) {
            System.out.println("Euclidean distance: ");
            astar.run(map, heuristic);
            System.out.println("");
        } else if (heuristic == 2) {
            System.out.println("Manhattan distance: ");
            astar.run(map, heuristic);
            System.out.println("");
        } else if (heuristic == 3) {
            System.out.println("Diagonal distance: ");
            astar.run(map, heuristic);
            System.out.println("");
        } else if (heuristic == 0) {
            System.out.println("Dijkstra: ");
            astar.run(map, heuristic);
            System.out.println("");
        }
    }


    /** Prints all the maps created by all heuristics.
     *
     *
     */
    public static void printAll(String map) {
        int i = 1;
        printOne(map, i);

        i = 2;
        printOne(map, i);
        i = 3;
        printOne(map, i);
        i = 0;
        printOne(map, i);
    }



    public static String map1() {
        return "##############################\n" +
                "#o#_______________________##x#\n" +
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

    public static String map2() {
        return "##############################\n" +
                "#o___________________________#\n" +
                "#____________________________#\n" +
                "#____________________________#\n" +
                "#____________________________#\n" +
                "#____________________________#\n" +
                "#____________________________#\n" +
                "#____________________________#\n" +
                "#____________________________#\n" +
                "#__________________________x_#\n" +
                "##############################";
    }

    public static String map3() {
        return "##############################\n" +
                "#____________________________#\n" +
                "#_________##############_____#\n" +
                "#______________________#_____#\n" +
                "#___________o__________#__x__#\n" +
                "#______________________#_____#\n" +
                "#______________________#_____#\n" +
                "#_________##############_____#\n" +
                "#____________________________#\n" +
                "#____________________________#\n" +
                "##############################";
    }

    public static String map4() {
        return
                "x___o__\n" +
                "_______";
    }

    public static String map5() {
        return  "_______\n" +
                "___#___\n" +
                "x__#o__\n" +
                "___#___";
    }


    public static final String ANSI_RESET = "\u001B[0m";

    public static String logo() {
        return "\u001B[35m" +
                "    .-.          .-.               _        .-. .-.   \n" +
                "    : :          : :              :_;      .' `.: :   \n" +
                "    : :    .--.  : `-. .-..-..--. .-.,-.,-.`. .': `-. \n" +
                "    : :__ ' .; ; ' .; :: :; :: ..': :: ,. : : : : .. :\n" +
                "    :___.'`.__,_;`.__.'`._. ;:_;  :_;:_;:_; :_; :_;:_;\n" +
                "                        .-. :                         \n" +
                "                        `._.'                         \n" +
                ANSI_RESET;
    }



}
