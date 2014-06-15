package Hike.algorithms;

import Hike.graph.Edge;
import Hike.graph.Node;
import Hike.structures.LinkyList;
import Hike.structures.MinHeap;
import Hike.structures.PathStack;
import Hike.Values;

/**
 * This class calculates distances to nodes using A* algorithm.
 * nodeTable contains the table that has been set up in class Node. All nodes
 * are added to MinHeap and the one with smallest distance has it's neighbours
 * checked and MinHeap is updated.
 */
public class Pathfinder {

    private Node[][] nodeTable;
    private LinkyList neighbours;
    private PathStack que;
    private double calculations;   // Will be used to count something. Very general estimate used for testing.
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

        calculations = 0;
        this.heuristic = heuristic;
        this.que = new PathStack(nodeTable.length * nodeTable[0].length);
        this.nodeTable = nodeTable;
        this.targety = ty;
        this.targetx = tx;


        long timeStart = System.currentTimeMillis();
        initialize();
        findRoute();
        long timeEnd = System.currentTimeMillis();
        totalTime = (timeEnd - timeStart);


        System.out.println("Dijkstra took: " + (timeEnd - timeStart) + "ms.");
        System.out.println("Calculations: " + (long) calculations);
    }

    /**
     * Starts the algorithm. Always starts from position 0,0 in the
     * grid. All nodes are in the MinHeap in the beginning.
     */
    private void findRoute() {
        Node eval = heap.removeMin();

        while (heap.empty() == false) {
            calculations = calculations + 3;
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

        for (Edge edge : neighbours) {
            if (!edge.getChild().getChecked()) {
                relax(edge);


                calculations++;
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
                calculations++;
            }
        }


    }

    /**
     * Checks if distance to a goal is shorter by travelling through start.
     * Check if the goal has been checked already to prevent stack getting too
     * large in error situations. The A* heuristic is also checked here.
     *
     *
     * @param edge The edge being checked
     * 
     */
    private void relax(Edge edge) {
        edge.getChild().setDistanceToGoal(heuristic(edge));

        if (edge.getChild().getDistance() > edge.getParent().getDistance() + edge.getCost()) {
            calculations = calculations + 3;
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

    public Node[][] getRouteTable() {
        return nodeTable;

    }

    public double getC() {
        return calculations;
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
    
    /**
     * Heuristic for calculating routes with A*. Value d can be changed to control 
     * accuracy vs speed, larger is faster and smaller is more accurate.
     * @param edge
     * @return 
     */

    private double heuristic(Edge edge) {
        Node goal = edge.getChild();
        double d = Values.ACCURACY; //Easiest cost. This value can control accuracy vs speed. Higher is faster but more inaccurate.
        double d2 = Math.sqrt(2)*d; //Diagonal movement cost
        goal.getWeight();

        if (heuristic.contains("Diagonalsearch")) { //Diagonal search. 
            double x = Math.abs(goal.getX() - targetx);
            double y = Math.abs(goal.getY() - targety);
            return d * (x + y) + (d2 - 2*d) * Math.min(y, x);

        } else if (heuristic.contains("Manhattan")) { //Manhattan.
            double x = Math.abs(goal.getX() - targetx);
            double y = Math.abs(goal.getY() - targety);
            return d * (x + y);
        } else { // Normal Dijkstra.
            return 0;
        }
    }
}
