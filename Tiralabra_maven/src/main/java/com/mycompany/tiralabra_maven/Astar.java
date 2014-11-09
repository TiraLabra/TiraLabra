package com.mycompany.tiralabra_maven;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Implementation of A* algorithm. Algorithm's purpose is to find the most optimal path in a given map or maze.
 * The Map consists of coordinates called Nodes. @see Node
 * The implementation is currently a rough draft and missing some intended features, like reading and interpreting
 * the map, creating nodes, setting map size, finding neighbors of current node, chopping the main method into
 * multiple methods and refactoring it to become more readable.
 */
public class Astar {
    public static void main(String[] args) {
        //TODO: node creation based on map
        //analyze map character by character, creating nodes and add !iswalkable if character is not
        //also set start & end according to map

        Node start = new Node(0,0);
        Node end = new Node(4,1);
        Node current = start;
        Node neighbor = new Node(0,0);

        int maxX = 5;
        int maxY = 5;
        //list of unchecked nodes
        //List open = new ArrayList();
        PriorityQueue<Node> open = new PriorityQueue();

        open.add(start);

        //cost of the movement so far
        double cost;
        cost = calculateHeuristic(end, current);


        //list of nodes that have been checked
        List closed = new ArrayList();

        while (!(open.contains(end))) {
            //current = remove lowest rank item from OPEN
            current = open.poll();
            closed.add(current);


            //find neighbors of the start node which are walkable
            //find out all possible directions of the current node
            //remove walls + unwalkable squares + create a list of them


            //for neighbours of the current:
            //set node which we are going through now as neighbor
            //F = G+H
            //cost = g(current) + movementcost(current, neighbor)
            //terrain cost is 1 because no terrain difference
            cost = 1 + calculateHeuristic(end, neighbor);

            //    if neighbor in OPEN and cost less than g(neighbor):
            if (open.contains(neighbor) && cost<1) {
                //remove neighbor from OPEN, because new path is better
                open.remove(neighbor);
            }

            //if neighbor in CLOSED and cost less than g(neighbor): **
            if (closed.contains(neighbor) && cost<1 ) {
                //remove neighbor from CLOSED
                closed.remove(neighbor);
            }

            //if neighbor not in OPEN and neighbor not in CLOSED:
            if (!(open.contains(neighbor)) && !(closed.contains(neighbor))) {
                //set g(neighbor) to cost
                //cost = 1;
                //add neighbor to OPEN
                open.add(neighbor);
                //set priority queue rank to g(neighbor) + h(neighbor)

                //set neighbor's parent to current
                neighbor.setParent(current);

            }

        }

        end.setParent(current);


        //reconstruct reverse path from goal to start
        //by following parent pointers

        //ArrayList path = new ArrayList();
        /*
        Node help = new Node();

        while (current.getParent() != null) {
            path.add(current);
            help = current.getParent();
            current = help;
        }

        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
        }
        */
    }


    /**
     * Checks, if the node is within the boundaries of map
     * @param node The Node being checked
     * @param maxX Maximum width of the map
     * @param maxY Maximum height of the map
     * @return True, if Node is within the boundaries of map
     */
    public static boolean isWalkable(Node node, int maxX, int maxY) {
        if (node.getX() >= 0 && node.getX() <= maxX) {
            if (node.getY() >= 0 && node.getY() <= maxY) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates the heuristic used for this A* algorithm, current heuristic for testing purposes is Manhattan distance.
     * @param end The end Node at the map
     * @param current Current Node where the algorithm is
     * @return returns cost calculated by distance
     */

    public static double calculateHeuristic(Node end, Node current) {
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
