package Hike.Algorithms;

import Hike.Graph.Node;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * This class calculates distances to nodes using Dijkstra's algorithm.
 * nodeTable contains the table that has been set up in class Node Checked and
 * Unchecked contain nodes that are being examined in the algorithm Neighbours
 * contains the neighbours of a node Que is a stack used to print the shortest
 * path
 */
public class Dijkstra {

    private Node[][] nodeTable;
    private Set<Node> checked;
    private Set<Node> unchecked;
    private LinkedList<Node> neighbours;
    private Deque<Node> que;
    private double c;   // Will be used to count something
    private Node small;

    /**
     * Constructor, sets variables and runs the search.
     *
     * @param nodeTable Table containing all Nodes that are used
     */
    public Dijkstra(Node[][] nodeTable) {

        c = 0;
        this.que = new ArrayDeque<Node>();
        this.nodeTable = nodeTable;
        this.checked = new HashSet<Node>();
        this.unchecked = new HashSet<Node>();
        small = new Node(1, 1, 21000000);
        long aikaAlussa = System.currentTimeMillis();
        initialize();
        findDijkstra();
        long aikaLopussa = System.currentTimeMillis();
        System.out.println("Operaatioon kului aikaa: " + (aikaLopussa - aikaAlussa) + "ms.");

//        nodeTable[0][0].printTable();
        System.out.println("Calculations: " + (long) c + " plus varmaan miljardi lisää jossain, tää ei ole valmis vielä");
        System.out.println("Square root: " + Math.sqrt(c));
    }

    /**
     * Starts the Dijkstra algorithm. Always starts from position 0,0 in the
     * grid. Node is placed into the unchecked list, the unchecked list is
     * checked for the node containing the smallest distance, that node is then
     * placed to the checked list and the distances to it's neighbours is
     * calculated.
     *
     */
    private void findDijkstra() {
        Node eval = nodeTable[0][0];
        unchecked.add(eval);

        while (unchecked.isEmpty() == false) {

            eval = getLowestDistance();
            unchecked.remove(eval);
            checked.add(eval);
            checkNeighbours(eval);


        }
    }

    /**
     * Relaxes all neighbours that have not yet been checked.
     *
     * @param eval The node whose neighbours are relaxed
     */
    private void checkNeighbours(Node eval) {
        neighbours = eval.getNeighbours();
        for (Node node : neighbours) {
            if (checked.contains(node) == false) {
                relax(eval, node);

            }
        }


    }

    /**
     * Finds the smallest distance node from the unchecked list
     *
     * @return node with smallest distance value
     */
    private Node getLowestDistance() {
        small.setDistance(21000000);
        for (Node node : unchecked) {
            if (node.getDistance() < small.getDistance()) {
                small = node;
            }

        }
        return small;

    }

    /**
     * Sets the distance of the starting point to 0.
     */
    private void initialize() {
        nodeTable[0][0].setDistance(0);


    }

    /**
     * Checks if distance to a goal is shorter by travelling through start
     *
     * @param start
     * @param goal
     */
    private void relax(Node start, Node goal) {
        if (goal.getDistance() > start.getDistance() + goal.getWeight()) {
            goal.setDistance(start.getDistance() + goal.getWeight());
            goal.setPrevious(start);
            unchecked.add(goal);


        }

    }

    /**
     * Places the nodes previous node in a stack to find shortest route from y,x
     * to 0,0.
     *
     * @param y
     * @param x
     */
    public void buildPath(int y, int x) {
        Node u = nodeTable[y][x].getPrevious();
        while (u != null) {
            que.push(u);
            u = u.getPrevious();

        }

    }

    /**
     * Pops a value from the stack
     *
     * @return
     */
    public Node nextPath() {
        if (que.size() > 0) {


            return que.poll();
        }
        return null;




    }
}
