package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.DataStructures.MyList;
import com.mycompany.tiralabra_maven.DataStructures.MyPriorityQueue;

/**
 * Implementation of A* algorithm. Algorithm's purpose is to find the most optimal path in a given map or maze.
 * The Map MyMap consists of coordinates called Nodes. @see Node
 * A-star uses Heuristic-class to calculate which of the neighbor Nodes is the most optimal for the path.
 *
 */
public class Astar {
    /** Path length for testing purposes */
    private int pathLength;
    /** Runtime for testing purposes */
    private long runTime;

    public void run(String map, int heuristicId) {
        /** Temporarily here for timing purposes */
        long aikaAlussa = System.currentTimeMillis();

        /** Create map */
        MyMap myMap = new MyMap();
        myMap.createMap(map);
        Node start = myMap.getStart();
        Node end = myMap.getEnd();
        Node current = start;
        int maxX = myMap.getMaxX();
        int maxY = myMap.getMaxY();

        /** Insert the wanted heuristic: */
        Heuristic heuristic = new Heuristic(heuristicId);

        /** get nodes */
        Node[][] nodes = myMap.getMap();

        /** list of unchecked nodes */
        MyPriorityQueue open = new MyPriorityQueue(20000);
        open.insert(start);

        /** list of nodes that have been checked */
        MyList closed = new MyList();

        /**
         * Find neighbors of the start node which are walkable,
         * find out all possible directions of the current node,
         * remove walls + unwalkable spaces + create a list of the walkable items.
         * Then it calculates which of the available nodes is the best one cost-wise.
         */
        while (!(closed.contains(end))) {
            /** Break the loop if current node is end node */
            if (current.getX() == end.getX() && current.getY() == end.getY()) {
                end.setParent(current.getParent());
                break;
            }

            /** current = remove lowest rank item from OPEN */
            current = open.deleteMinimum();
            /** add current to the searched list */
            closed.add(current);

            /** Start by checking all the neighbors of the current: */
            for (int x = -1; x < 2; x++) {
                for (int y = -1; y < 2; y++) {
                    if ((x == 0) && (y == 0)) {
                        continue;
                    }

                    /** find the value of neighbour */
                    int xp = x + current.getX();
                    int yp = y + current.getY();

                    /** check if the neighbour node is a valid point in a map and that it isn't a wall: */
                    if (isWalkableXY(xp, yp, maxX, maxY) && !nodes[yp][xp].isWall()) {
                        /** for neighbours of the current:
                         * set node which we are going through now as neighbor
                         * cost = cost of current node + movement cost between current and neighbor
                         * Since there are no differences in movement costs(terrains etc), the value is currently 1.
                         * */
                        Node neighbor = nodes[yp][xp];
                        double nextStepCost = current.getCost() + 1;

                        /** if neighbor is in open and costs less than neighbor node */
                        if (open.contains(neighbor) && nextStepCost < neighbor.getCost()) {
                            /** remove neighbor from open because new path is better: */
                            open.removeNode(neighbor);

                        }

                        /** if neighbor is in closed and next step costs less than neighbor: */
                        if (closed.contains(neighbor) && nextStepCost < neighbor.getCost()) {
                            /** remove neighbor from closed */
                            closed.remove(neighbor);
                        }

                        /** if neighbor if not in open or closed: */
                        if (!(open.contains(neighbor)) && !(closed.contains(neighbor))) {

                            neighbor.setCost((int) (nextStepCost));
                            /** set priority queue rank to cost of neighbor plus heuristic cost: */
                            neighbor.setHeuristic(neighbor.getCost() + (int) heuristic.cost(xp, yp, end));
                            /** add neighbor to open: */
                            open.insert(neighbor);
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
        this.pathLength = pathLength;
        /** Print the final map! */
        myMap.printMap();
        /** Tell us how long the path was: */
        System.out.println("Path length was " + pathLength + " steps.");
        /** Tells us how long it took to run the algorithm: */
        long aikaLopussa = System.currentTimeMillis();
        this.runTime = aikaLopussa - aikaAlussa;
        System.out.println("Runtime was " + getRunTime() + "ms.");
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

    /** Return path length */
    public int getPathLength() {
        return pathLength;
    }
    /** Return runtime */
    public long getRunTime() {
        return runTime;
    }

}
