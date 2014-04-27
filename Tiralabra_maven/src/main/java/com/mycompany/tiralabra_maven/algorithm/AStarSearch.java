package com.mycompany.tiralabra_maven.algorithm;

import com.mycompany.tiralabra_maven.datastructures.List;
import com.mycompany.tiralabra_maven.datastructures.PriorityQueue;
import com.mycompany.tiralabra_maven.datastructures.Set;

public class AStarSearch implements Search {

    private final Graph graph;
    private Heuristic heuristic;

    public AStarSearch(Graph graph, Heuristic heuristic) {
        this.graph = graph;
        this.heuristic = heuristic;
    }

    @Override
    public List<Node> findOptimalPath() {
        List<Node> states = new List<>();
        Node state = getOptimalPath();
        while (state != null) {
            states.insertLast(state);
            state = state.getParent();
        }
        return states;
    }

    private Node getOptimalPath() {

        PriorityQueue open = PriorityQueue.createMinPriorityQueue();
        Set<Node> closed = new Set<>();
        open.enqueue(graph.getStartNode());

        while (!open.isEmpty()) {
            Node current = (Node) open.dequeue();

            if (graph.isGoalNode(current)) {
                return current;
            }

            closed.add(current);

            for (Node successor : graph.getSuccessors(current)) {
                int cost = current.getCost() +  graph.weight(current, successor);
                /*if (open.contains(successor) && cost < successor.getCost()) {
                    open.remove(successor);
                }*/
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
