package Hike.Algorithms;

import Hike.Graph.Node;
import Hike.Structures.LinkyList;
import Hike.Structures.MinHeap;
import Hike.Values;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * This class calculates distances to nodes using Dijkstra's algorithm.
 * nodeTable contains the table that has been set up in class Node. All nodes
 * are added to MinHeap and the one with smallest distance has it's neighbours
 * checked and MinHeap is updated.
 */
public class Pathfinder {

    private Node[][] nodeTable;
    private LinkyList neighbours;
    private Deque<Node> que;
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
        this.que = new ArrayDeque<Node>();
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
        for (Node node : neighbours) {
            relax(eval, node);


            c++;
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
     * Checks if distance to a goal is shorter by travelling through start
     *
     * @param start
     * @param goal
     */
    private void relax(Node start, Node goal) {
        goal.setDistanceToGoal(heuristic(goal));

        if (goal.getDistance() > start.getDistance() + goal.getWeight()) {
            c = c + 3;
            goal.setPrevious(start);
            heap.decHeap(goal.getHeapIndex(), start.getDistance() + goal.getWeight(), goal.getDistanceToGoal());


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


            return que.poll();
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

    private int heuristic(Node goal) {
        int d = Values.GRASS; //Easiest possible weight

        if (heuristic.contains("Chebyshev")) { //Chebyshev. Should always find shortest route.
            int x = Math.abs(goal.getX() - targetx);
            int y = Math.abs(goal.getY() - targety);
            return d * Math.max(x, y);

        } else if (heuristic.contains("Manhattan")) { //Manhattan, faster but might not find the shortest route.
            int x = Math.abs(goal.getX() - targetx);
            int y = Math.abs(goal.getY() - targety);
            return d * (x + y);
        } else { // Normal Dijkstra.
            return 0;
        }
    }
}
