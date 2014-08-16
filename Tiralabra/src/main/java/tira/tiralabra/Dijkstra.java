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
    private ArrayList<Node> nodes;

    public Dijkstra(String start, String end, Mapper grid) {
        this.source = start;
        this.destination = end;
        this.graph = grid.getGrid();
        this.nodes = new ArrayList<Node>();
    }

    public void initialize() {
        for (String apu : this.graph.keySet()) {
            Node next = new Node(apu);
            this.nodes.add(next);
        }
    }
    
    public void route() {
    }

    private void createEdges(Node next) {
        String node = next.toString();
        ArrayList<Target> connections = this.graph.get(node);
        
    }
    
}