package com.mycompany.tiralabra_maven.algorithm;

import com.mycompany.tiralabra_maven.datastructures.List;
import com.mycompany.tiralabra_maven.datastructures.PriorityQueue;
import com.mycompany.tiralabra_maven.datastructures.Set;

/**
 *
 * @author Yessergire Mohamed
 */
public class AStarSearch implements Search {

    private final Graph graph;
    private Heuristic heuristic;

    public AStarSearch(Graph graph, Heuristic heuristic) {
        this.graph = graph;
        this.heuristic = heuristic;
    }

    /**
     * Tries to find the optimal path for start node to goal node.
     * @param start start node
     * @param goal goal node
     * @return a list of nodes representing find the optimal from start
     * to goal it exists.
     */
    @Override
    public List<Node> findPath(Node start, Node goal) {
        List<Node> states = new List<>();
        Node state = getPath(start, goal);
        while (state != null) {
            states.insertLast(state);
            state = state.getParent();
        }
        return states;
    }

    private Node getPath(Node start, Node goal) {

        PriorityQueue open = PriorityQueue.createMinPriorityQueue();
        Set<Node> closed = new Set<>();
        open.enqueue(start);

        while (!open.isEmpty()) {
            Node current = (Node) open.dequeue();

            if (goal.equals(current)) {
                return current;
            }

            closed.add(current);

            for (Node successor : graph.getAdjacent(current)) {
                int cost = current.getCost() +  graph.weight(current, successor);
                if (open.contains(successor) && cost < successor.getCost()) {
                    open.remove(successor); // new path is better
                }
                // This should never happen if you have an monotone admissible heuristic
                if (closed.contains(successor) && cost < successor.getCost()) {
                    closed.remove(successor);
                }
                if (!open.contains(successor) && !closed.contains(successor)) {
                    successor.setCost(cost);
                    successor.setRank(heuristic.value(successor));
                    open.enqueue(successor);
                }
            }
        }
        return null;
    }

    public Heuristic getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(Heuristic heuristic) {
        this.heuristic = heuristic;
    }

}
