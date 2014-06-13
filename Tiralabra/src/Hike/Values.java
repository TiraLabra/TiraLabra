/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hike;

/**
 * Contains essential values for the application. Difficulty of terrains
 * controlled with these values.
 *
 * @author petri
 */
public class Values {

    public static final int IMAGEHEIGHT = 500;
    public static final int IMAGEWIDTH = 800;
    public static final int UNSETGROUND = 1;
    public static final int GRASS = 1;
    public static final int DESERT = 10;
    public static final int WATER = 50;
    public static final int MOUNTAIN = 1000;
    public static String HEURISTIC;      // 0 = No heuristic, Djikstra. 1 = Diagonal Distance, Chebyshev.
    public static boolean SHOWSEARCHED = false;
    public static boolean DIAGONAL = true;

    public static void setHeuristic(String t) {
        HEURISTIC = t;
    }

    public static void setShowSearched() {
        if (SHOWSEARCHED == false) {
            SHOWSEARCHED = true;
        } else {
            SHOWSEARCHED = false;
        }

    }

    public static void setDiagonal() {
        if (DIAGONAL == false) {
            DIAGONAL = true;
        } else {
            DIAGONAL = false;
        }

    }
}
