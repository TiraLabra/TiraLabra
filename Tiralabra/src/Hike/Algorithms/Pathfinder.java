package Hike.Algorithms;

import Hike.Graph.Edge;
import Hike.Graph.Node;
import Hike.Structures.LinkyList;
import Hike.Structures.MinHeap;
import Hike.Structures.PathStack;
import Hike.Values;

/**
 * This class calculates distances to nodes using Dijkstra's algorithm.
 * nodeTable contains the table that has been set up in class Node. All nodes
 * are added to MinHeap and the one with smallest distance has it's neighbours
 * checked and MinHeap is updated.
 */
public class Pathfinder {

    private Node[][] nodeTable;
    private LinkyList neighbours;
    private PathStack que;
    private double c;   // Will be used to count something
    private MinHeap heap;
    private long totalTime;
    private int targety;
    private int targetx;
    private String heuristic;

    /**
     * Constructor, sets variables and runs the search.
     *
     * @param nodeTable Table containing all Nodes that are used
     */
    public Pathfinder(Node[][] nodeTable, int ty, int tx, String heuristic) {

        c = 0;
        this.heuristic = heuristic;
        this.que = new PathStack(nodeTable.length * nodeTable[0].length);
        this.nodeTable = nodeTable;
        this.targety = ty;
        this.targetx = tx;


        long timeStart = System.currentTimeMillis();
        initialize();
        findDijkstra();
        long timeEnd = System.currentTimeMillis();
        totalTime = (timeEnd - timeStart);


        System.out.println("Dijkstra took: " + (timeEnd - timeStart) + "ms.");
        System.out.println("Calculations: " + (long) c);
    }

    /**
     * Starts the Dijkstra algorithm. Always starts from position 0,0 in the
     * grid. All nodes are in the MinHeap in the beginning.
     */
    private void findDijkstra() {
        Node eval = heap.removeMin();

        while (heap.empty() == false) {
            c = c + 3;
            checkNeighbours(eval);

            eval = heap.removeMin();

            // if target found, break loop.
            if (eval == nodeTable[targety][targetx]) {
                break;
            }
        }
    }

    /**
     * Relaxes all neighbours.
     *
     * @param eval The node whose neighbours are relaxed
     */
    private void checkNeighbours(Node eval) {
        neighbours = eval.getNeighbours();
        Node checkNext = new Node(0, 0, 0);
        checkNext.setDistance(Integer.MAX_VALUE);
        for (Edge edge : neighbours) {
            if (!edge.getChild().getChecked()) {
                relax(edge);


                c++;
            }
        }
    }

    /**
     * Sets the distance of the starting point to 0. Puts all nodes to MinHeap.
     */
    private void initialize() {
        nodeTable[0][0].setDistance(0);
        heap = new MinHeap(nodeTable.length * nodeTable[0].length + 1);
        for (int h = 0; h < nodeTable.length; h++) {
            for (int w = 0; w < nodeTable[0].length; w++) {
                heap.insert(nodeTable[h][w]);
                c++;
            }
        }


    }

    /**
     * Checks if distance to a goal is shorter by travelling through start.
     * Check if the goal has been checked already to prevent stack getting too
     * large in error situations
     *
     *
     * @param start
     * @param goal
     */
    private void relax(Edge edge) {
        edge.getChild().setDistanceToGoal(heuristic(edge.getChild()));

        if (edge.getChild().getDistance() > edge.getParent().getDistance() + edge.getCost()) {
            c = c + 3;
            edge.getChild().setPrevious(edge.getParent());
            edge.getChild().setChecked();
            heap.decHeap(edge.getChild().getHeapIndex(), edge.getParent().getDistance() + edge.getCost(), edge.getChild().getDistanceToGoal());


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
        Node u = nodeTable[y][x];
        while (u != null) {
            que.push(u);
            u = u.getPrevious();

        }
        System.out.println("Steps in path: " + que.size());

    }

    /**
     * Pops a value from the stack
     *
     * @return
     */
    public Node nextPath() {
        if (que.size() > 0) {


            return que.pop();
        }
        return null;




    }

    public Node[][] getDijkstraTable() {
        return nodeTable;

    }

    public double getC() {
        return c;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public int getTargety() {
        return targety;
    }

    public int getTargetx() {
        return targetx;
    }

    public MinHeap getHeap() {
        return this.heap;
    }

    private double heuristic(Node goal) {
        double d = 1.0; //Easiest possible weight
        double d2 = 1.4;

        if (heuristic.contains("Diagonalsearch")) { //Diagonal search. 
            double x = Math.abs(goal.getX() - targetx);
            double y = Math.abs(goal.getY() - targety);
            return d * (x + y) + (d2 - 2 * d) * Math.min(x, y);

        } else if (heuristic.contains("Manhattan")) { //Manhattan.
            double x = Math.abs(goal.getX() - targetx);
            double y = Math.abs(goal.getY() - targety);
            return d * (x + y);
        } else { // Normal Dijkstra.
            return 0;
        }
    }
}
