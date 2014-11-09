package com.mycompany.tiralabra_maven;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Implementation of A* algorithm. Algorithm's purpose is to find the most optimal path in a given map or maze.
 * The Map consists of coordinates called Nodes. @see Node
 *
 *
 */
public class Astar {
    public static void main(String[] args) {

        //current map for testing purposes:
        //  o______ start: 0,0
        //  _______ end: 3,3
        //  __x____ maxX: 5
        //  _______ maxY: 5
        //  _______

        //TODO: set walls
        //TODO: node creation based on map
        //analyze map character by character, creating nodes and add !iswalkable if character is not _
        //also set start & end according to map

        Node start = new Node(0,0);
        Node end = new Node(4,1);
        Node current = start;
        Node neighbor;

        int maxX = 5;
        int maxY = 5;
        //list of unchecked nodes
        //List open = new ArrayList();
        PriorityQueue<Node> open = new PriorityQueue();

        open.add(start);

        //cost of the movement so far
        double cost = 0;
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
                cost = 1;
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

        ArrayList path = new ArrayList();
        Node help = new Node;

        while (current.getParent() != null) {
            path.add(current);
            help = current.getParent();
            current = help;
        }

        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
        }
        //add neighbor squares to open list
        //save each node's parent
        //find the neighbor which has the lowest F value (cost = g(current) + movementcost(current, neighbor))
        //remove parent node from open list and add it to closed
    }


    /**
     * Checks, if the node is within the boundaries of map
     * @param node The Node being checked
     * @param maxX Maximum width of the map
     * @param maxY Maximum height of the map
     * @return True, if Node is within the boundaries of map
     */
    public boolean isWalkable(Node node, int maxX, int maxY) {
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
