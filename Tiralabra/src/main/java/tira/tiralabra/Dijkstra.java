package tira.tiralabra;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author joonaslaakkonen
 */
public class Dijkstra {
    
    private String source;
    private String destination;
    private HashMap<String, ArrayList<Target>> graph;
    private Node[] nodes;

    public Dijkstra(String start, String end, Mapper grid) {
        this.source = start;
        this.destination = end;
        this.graph = grid.getGrid();
        this.nodes = new Node[this.graph.size()];
    }

    public void initialize() {
        int i = 0;
        for (String apu : this.graph.keySet()) {
            Node next = new Node(apu);
            createEdges(next);
            this.nodes[i] = next;
            i++;
        }
    }
    
    public void route() {
    }

    private void createEdges(Node next) {
        ArrayList<Target> connections = this.graph.get(next.toString());
        Edge[] edges = new Edge[connections.size()];
        int i = 0;
        for (Target apu : connections) {
            edges[i] = new Edge();
        }
    }
    
}