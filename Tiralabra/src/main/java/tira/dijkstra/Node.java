package tira.dijkstra;

import tira.dijkstra.Edge;
import java.util.ArrayList;

/**
 *
 * @author joonaslaakkonen
 * Luokka kuvaa kartan solmua. Luokka toteuttaa Comparable rajapinnan prioriteettijonon vertailua varten.
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
    
    /**
     * 
     * Luokan getterit ja setterit
     */
    
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
    
    public void setPrevious(Node handle) {
        this.previous = handle;
    }

    public Node getPrevious() {
        return this.previous;
    }
    
    public ArrayList<Edge> getEdges() {
        return this.routes;
    }
    
    /**
     * Etäisyyden vertailu.
     * @param o
     * @return 
     */

    @Override
    public int compareTo(Node o) {
        return this.shortest - o.getShortest();
    }
    
}