package Hike.Algorithms;

import Hike.Graph.Node;
import Hike.Structures.LinkyList;
import Hike.Structures.MinHeap;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * This class calculates distances to nodes using Dijkstra's algorithm.
 * nodeTable contains the table that has been set up in class Node. All nodes are added to
 * MinHeap and the one with smallest distance has it's neighbours checked and MinHeap is updated.
 */
public class Dijkstra {

    private Node[][] nodeTable;
    private LinkyList neighbours;
    private Deque<Node> que;
    private double c;   // Will be used to count something
    private MinHeap heap;
    private long totalTime;

    /**
     * Constructor, sets variables and runs the search.
     *
     * @param nodeTable Table containing all Nodes that are used
     */
    public Dijkstra(Node[][] nodeTable) {

        c = 0;
        this.que = new ArrayDeque<Node>();
        this.nodeTable = nodeTable;

        long timeStart = System.currentTimeMillis();
        initialize();
        findDijkstra();
        long timeEnd = System.currentTimeMillis();
        totalTime = (timeEnd - timeStart);
        
        
        System.out.println("Dijkstra took: " + (timeEnd - timeStart) + "ms.");
        System.out.println("Calculations: " + (long) c );
    }

    /**
     * Starts the Dijkstra algorithm. Always starts from position 0,0 in the
     * grid. All nodes are in the MinHeap in the beginning.
     */
    private void findDijkstra() {
        Node eval = heap.removeMin();
        while (heap.empty() == false) {
            c = c+2;
            checkNeighbours(eval);
            eval = heap.removeMin();
        }
    }

    /**
     * Relaxes all neighbours.
     *
     * @param eval The node whose neighbours are relaxed
     */
    private void checkNeighbours(Node eval) {
        neighbours = eval.getNeighbours();
        for (Node node : neighbours) {
            c++;
            relax(eval, node);
        }
    }

    /**
     * Sets the distance of the starting point to 0.
     * Puts all nodes to MinHeap.
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

        if (goal.getDistance() > start.getDistance() + goal.getWeight()) {
            c = c+3;
            goal.setDistance(start.getDistance() + goal.getWeight());
            goal.setPrevious(start);
            heap.decHeap(goal.getHeapIndex(), goal.getDistance());


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
}
