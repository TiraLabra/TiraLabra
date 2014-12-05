package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.DataStructures.MyList;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Implementation of A* algorithm. Algorithm's purpose is to find the most optimal path in a given map or maze.
 * The Map consists of coordinates called Nodes. @see Node
 * 
 */
public class Astar {
    public void run(String map) {
        /** Temporarily here for timing purposes */
        //long aikaAlussa = System.currentTimeMillis();
        /** starting point of the map */
        //Node start = new Node(1, 2);
        /** ending point of the map */
        //Node end = new Node(3, 2);
        /** current point of the map */
        //Node current = start;
        /** map width */
        //int maxX = 5;
        /** map height */
        //int maxY = 5;

        /** create the game map */
        //MyMap myMap = new MyMap(start, end, maxX, maxY);
        //myMap.createMap();

        /** Create map */
        MyMap myMap = new MyMap();
        myMap.createMap2(map);
        Node start = myMap.getStart();
        Node end = myMap.getEnd();
        Node current = start;
        int maxX = myMap.getMaxX();
        int maxY = myMap.getMaxY();

        Node[][] nodes = myMap.getMap();

        /** list of unchecked nodes */

        PriorityQueue<Node> open = new PriorityQueue<Node>(100, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {

                return (Integer.valueOf(o1.getCost()).compareTo(o2.getCost()));
            }
        });

        open.add(start);
        /*
        MyPriorityQueue open = new MyPriorityQueue(nodes.length);
        open.insert(start); */

        /** list of nodes that have been checked */
        MyList closed = new MyList();
        /**
         *
         * Find neighbors of the start node which are walkable,
         * find out all possible directions of the current node,
         * remove walls + unwalkable squares + create a list of the walkable items.
         * Then it calculates which of the available nodes is the best one cost-wise.
         */
        /** Max steps to get to the end of path: */
        int steps = 0;

        while (!(closed.contains(end)) && (steps < 1000)) {
            /** If there is no path to be found, return */
            if (steps == 999) {
                break;
            }
            /** Break the loop if current node is end node */
            if (current.getX() == end.getX() && current.getY() == end.getY()) {
                end.setParent(current.getParent());
                break;
            }

            /** current = remove lowest rank item from OPEN */
            current = open.poll();
            //current = open.deleteMinimum();
            //System.out.println("current x y " + current.getX() + " " +  current.getY());
            /** add current to the searched list */
            closed.add(current);


            for (int x = -1; x < 2; x++) {
                for (int y = -1; y < 2; y++) {
                    if ((x == 0) && (y == 0)) {
                        continue;
                    }

                    /** find the value of neighbour */
                    int xp = x + current.getX();
                    int yp = y + current.getY();

                    steps++;
                    if (isWalkableXY(xp, yp, maxX, maxY) && !nodes[yp][xp].isWall()) {

                        /** for neighbours of the current:
                         * set node which we are going through now as neighbor
                         * F = G+H
                         * cost = g(current) + movementcost(current, neighbor)
                         * terrain cost is 1 because no terrain difference
                         * cost = 1 + calculateHeuristic(end, neighbor); */
                        double nextStepCost = current.getCost() + calculateEuclidean(xp, yp, end);

                        Node neighbor = nodes[yp][xp];

                        /**  if neighbor in OPEN and cost less than g(neighbor): */
                        if (open.contains(neighbor) && nextStepCost < neighbor.getCost()) {
                            /** remove neighbor from OPEN, because new path is better */
                            open.remove(neighbor);
                            //open.removeNode(neighbor);

                        }

                        /** if neighbor in CLOSED and cost less than g(neighbor): */
                        if (closed.contains(neighbor) && nextStepCost < neighbor.getCost()) {
                            /** remove neighbor from CLOSED */
                            closed.remove(neighbor);
                        }

                        /** if neighbor not in OPEN and neighbor not in CLOSED: */
                        if (!(open.contains(neighbor)) && !(closed.contains(neighbor))) {

                            /**
                             * set g(neighbor) to cost
                             * cost = 1;
                             * add neighbor to OPEN
                             */
                            neighbor.setCost((int) (nextStepCost));
                            open.add(neighbor);
                            //open.insert(neighbor);

                            //System.out.print("Add to open " + neighbor.getX() + " "+ neighbor.getY());
                            //System.out.println("cost " + neighbor.getCost());
                            /** set priority queue rank to g(neighbor) + h(neighbor) */

                            /** set neighbor's parent to current */
                            neighbor.setParent(current);

                        }
                    }

                }
            }
        }

        /**
         * Reconstruct reverse path from goal to start
         * by following parent pointers */
        Node help;
        /** Skip the end node */
        help = current.getParent();
        current = help;
        int pathLength = 0;
        /** Go through all the nodes in the path and mark them with character P: */
        while (current.getParent() != null) {
            help.setCharacter('P');
            help = current.getParent();
            current = help;
            pathLength++;
        }

        /** Print the final map! */
        myMap.printMap();
        /** Tell us how long the path was: */
        System.out.println("Path length was " + pathLength + " steps.");
        /** Tells us how long it took to run the algorithm: */
        //long aikaLopussa = System.currentTimeMillis();
        //System.out.println("Operaatioon kului aikaa: " + (aikaLopussa - aikaAlussa) + "ms.");
    }


    /**
     * Checks, if the node is within the boundaries of map
     * @param node The Node being checked
     * @param maxX Maximum width of the map
     * @param maxY Maximum height of the map
     * @return True, if Node is within the boundaries of map
     */
    public static boolean isWalkable(Node node, int maxX, int maxY) {
        if (node.getX() >= 0 && node.getX() < maxX) {
            if (node.getY() >= 0 && node.getY() < maxY) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks, if the node is within the boundaries of map
     * @param x the x coordinate of node
     * @param y the y coordinate of node
     * @param maxX Maximum width of the map
     * @param maxY Maximum height of the map
     * @return True, if Node is within the boundaries of map
     */
    public static boolean isWalkableXY(int x, int y, int maxX, int maxY) {
        if (x >= 0 && x < maxX) {
            if (y >= 0 && y < maxY) {
                return true;
            }
        }
        return false;
    }

    /** Heuristic calculation using Euclidean Distance
     * @param xp x coordinate of node where cost is being calculated
     * @param yp y coordinate of the node
     * @param end End node for comparison
     * */
     public static double calculateEuclidean(int xp, int yp, Node end) {
         /** dx and dy are the distances between the nodes */
         double dx = end.getX() - xp;
         double dy = end.getY() - yp;
         /** Calculate Euclidean distance */
         return Math.sqrt((dx * dx) + (dy * dy));
    }


    /**
     * Heuristic calculation using Manhattan distance
     * @param xp x coordinate of node where cost is being calculated
     * @param yp y coordinate of the node
     * @param end The end Node at the map
     * @return returns cost calculated by distance
     */

    //public static double calculateHeuristicXY(int xp, int yp, Node end) {
        /** dx is an approximation of the distance between the two nodes' x coordinates */
        //double dx = Math.abs(end.getX() - xp);
        /** dy is an approximation of the distance between the two nodes' y coordinates */
        //double dy = Math.abs(end.getY() - yp);
        /** heuristic calculates the distance quickly by approximation instead of offering exact numbers
         *  hence it's very fast */
        //double heuristic;
        //heuristic = dx+dy;
        //return heuristic;
    //}
    /**
     * Heuristic calculation using Manhattan distance
     *
     * @param end The end Node at the map
     * @param current Current Node where the algorithm is
     * @return returns cost calculated by distance
     */
    public static double calculateManhattan(Node end, Node current) {
        /** dx is an approximation of the distance between the two nodes' x coordinates */
        double dx = Math.abs(end.getX() - current.getX());
        /** dy is an approximation of the distance between the two nodes' y coordinates */
        double dy = Math.abs(end.getY() - current.getY());
        /** heuristic calculates the distance quickly by approximation instead of offering exact numbers
         *  hence it's very fast */
        double heuristic;
        heuristic = dx+dy;
        return heuristic;
    }


}
