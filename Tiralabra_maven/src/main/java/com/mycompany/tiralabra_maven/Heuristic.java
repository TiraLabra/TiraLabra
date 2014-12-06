package com.mycompany.tiralabra_maven;

/** Class which contains all the heuristics used in calculating the
 * Astar algorithm.
 *
 *
 */
public class Heuristic {
    /** A checker for which heuristic to use;
     *  1 = Euclidean distance
     *  2 = Manhattan distance
     *  3 = Diagonal distance
     *  0 = Djiksta's algorithm (no heuristic)
     */
    private int heuristic;


    public Heuristic(int heuristic) {
        this.heuristic = heuristic;
    }

    /** Returns the heuristic cost of the distance between two points on map
     *
     * @param xp x coordinate of node where cost is being calculated
     * @param yp y coordinate of the node
     * @param end End node for comparison
     * @return cost according to the wanted heuristic
     */
    public double cost(int xp, int yp, Node end) {
        double result = 0;
        if (heuristic == 1) {
            result = calculateEuclidean(xp, yp, end);
        } else if (heuristic == 2) {
            result = calculateManhattan(xp, yp, end);
        } else if (heuristic == 3) {
            result = calculateDiagonal(xp, yp, end);
        } else if (heuristic == 0) {
            result = 0;
        }
        return result;
    }


    /** Heuristic calculation using Euclidean Distance
     * Picks next step based by which one is closest to the end node.
     * Does not generally work well in maps with many obstacles.
     *
     * @param xp x coordinate of node where cost is being calculated
     * @param yp y coordinate of the node
     * @param end End node for comparison
     * */
    public static double calculateEuclidean(int xp, int yp, Node end) {
        /** dx and dy are the distances between the nodes */
        double dx = xp - end.getX();
        double dy = yp - end.getY();
        /** Calculate Euclidean distance */
        return Math.sqrt((dx * dx) + (dy * dy));
    }


    /**
     * Heuristic calculation using Manhattan distance
     * Primaly meant for non-diagonal movement maps and is cheaper than euclidean distance.
     * Instead of calculating every step it approximates which step is the best one, which makes it cheaper.
     *
     * @param xp x coordinate of node where cost is being calculated
     * @param yp y coordinate of the node
     * @param end The end Node at the map
     * @return returns cost calculated by distance
     */

    public static double calculateManhattan(int xp, int yp, Node end) {
    /** dx is an approximation of the distance between the two nodes' x coordinates */
    double dx = Math.abs(end.getX() - xp);
    /** dy is an approximation of the distance between the two nodes' y coordinates */
    double dy = Math.abs(end.getY() - yp);
    /** heuristic calculates the distance quickly by approximation instead of offering exact numbers
     *  hence it's very fast */
    double heuristic;
    heuristic = dx+dy;
    return heuristic;
    }

    /**
     * Heuristic calculation using Chebyshev distance (aka. Diagonal distance)
     *
     * @param xp x coordinate of node where cost is being calculated
     * @param yp y coordinate of the node
     * @param end The end Node at the map
     * @return returns cost calculated by distance
     */
    public static double calculateDiagonal(int xp, int yp, Node end) {
        /** dx is an approximation of the distance between the two nodes' x coordinates */
        double dx = Math.abs(xp - end.getX());
        /** dy is an approximation of the distance between the two nodes' y coordinates */
        double dy = Math.abs(yp - end.getY());
        /** heuristic calculates the distance quickly by approximation instead of offering exact numbers
         *  hence it's very fast */
        double heuristic;
        heuristic = dx+dy;
        return heuristic;

    }





    //probably unneeded items, to be scrapped later:

    /**
     * Heuristic calculation using Manhattan distance
     *
     * @param end The end Node at the map
     * @param current Current Node where the algorithm is
     * @return returns cost calculated by distance
     */

/*
    public static double calculateManhattan(Node end, Node current) {
        *//** dx is an approximation of the distance between the two nodes' x coordinates *//*
        double dx = Math.abs(end.getX() - current.getX());
        *//** dy is an approximation of the distance between the two nodes' y coordinates *//*
        double dy = Math.abs(end.getY() - current.getY());
        *//** heuristic calculates the distance quickly by approximation instead of offering exact numbers
     *  hence it's very fast *//*
        double heuristic;
        heuristic = dx+dy;
        return heuristic;
    }*/




}
