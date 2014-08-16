package tira.tiralabra;

import java.util.ArrayList;

/**
 *
 * @author joonaslaakkonen
 */
public class Node implements Comparable<Node> {
    
    private Node previous;
    private String name;
    private ArrayList<Edge> routes;
    private int shortest;
    
    public Node(String name) {
        this.previous = null;
        this.name = name;
        this.shortest = Integer.MAX_VALUE;
        this.routes = new ArrayList<Edge>();
    }
    
    public int getShortest() {
        return this.shortest;
    }
    
    public String toString() {
        return this.name;
    }
    
    public void addEdge(Edge e) {
        this.routes.add(e);
    }
    
    public void setShortest(int value) {
        this.shortest = value;
    }

    @Override
    public int compareTo(Node o) {
        return this.shortest - o.getShortest();
    }
    
}
