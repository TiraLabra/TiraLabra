package A;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of A* algorithm, which is used to find the shortest path from
 * one node in a graph to another node in the same graph.
 *
 */
public class A_Star {

    /**
     * finds and returns the shortest path from start to finish
     * <p>
     * 
     * </p>
     * @param start
     * @param finish
     * @param heuristic
     * @return
     */
    public static List<INode> findShortestPath(INode start, INode finish, IHeuristic heuristic) {
        // checks
        if (start == null) {
            throw new NullPointerException("start node param can't be null");
        }
        if (finish == null) {
            throw new NullPointerException("finish node param can't be null");
        }
        if (heuristic == null) {
            throw new NullPointerException("heuristic param can't be null. If you don't want use heuristic set it to 0");
        }

        // initialize A*
        IA_Heap<INode> openSet = constructHeap();

        start.createNodeData().setCameFrom(null);
        start.getNodeData().setHeuristicCost(Integer.MAX_VALUE);
        start.getNodeData().setCostSoFar(0);
        openSet.insert(start);

        // loop until finish
        while (!openSet.isEmpty()) {
            // set current node as the node with least cost in the openSet
            INode current = openSet.pollMin();
            // done?
            if (current == finish) {
                return reconstructPath(start,finish);
            }
            
            Iterator<IEdge> edges = current.getNeighbors().iterator();
            // iterate over all edges
            while (edges.hasNext()) {
                IEdge edge = edges.next();
                INode neighbor = edge.getDestination();
                NodeData nodeData = neighbor.getNodeData();;

                //calculate new cost for this neighbor
                // equal to 'cost to current node + the cost to move to this neighbor'
                int newCost = current.getNodeData().getCostSoFar() + edge.getCost();
                //fix null nodeData
                if (nodeData == null) {
                    nodeData = neighbor.createNodeData();
                    nodeData.setCostSoFar(Integer.MAX_VALUE);
                }
                if (newCost < nodeData.getCostSoFar()) {
                    nodeData.setCostSoFar(newCost);
                    //estimated length of full path i.e. from start to finish
                    nodeData.setHeuristicCost(nodeData.getCostSoFar() + heuristic.Distance(neighbor, finish));
                    openSet.insert(neighbor);
                    nodeData.setCameFrom(current);
                }
            }
        }
        return null;
    }

    /**
     * Reconstructs the path from start to finish using node data provided by
     * findShortestPath()
     *
     * @param finish
     * @param start
     * @return List of nodes from start to finish
     */
    private static List<INode> reconstructPath(INode start, INode finish) {
        if (finish == null) {
            return null;
        }
        List<INode> list = new LinkedList();

        while (true) {
            list.add(finish);
            if (finish == start) {
                break;
            }
            finish = finish.getNodeData().cameFrom();
        }
        //FROM finish -> start TO start -> finish
        Collections.reverse(list);
        return list;
    }

    /**
     * Constructs new Heap and returns it
     *
     * @return IA_Heap
     */
    private static IA_Heap constructHeap() {
        return new Heap(new Comparator() {

            public int compare(Object o1, Object o2) {
                INode n1 = (INode) o1;
                INode n2 = (INode) o2;
                return (int) Math.round(n1.getNodeData().getHeuristicCost() - n2.getNodeData().getHeuristicCost());
            }

        });
    }
}
