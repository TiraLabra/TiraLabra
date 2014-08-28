package tira.common;

import tira.common.Edge;
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
    private int heuristic;
    private int x;
    private int y;
    
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
    
    public void setHeuristic(int value) {
        this.heuristic = value;
    }
    
    public int getHeuristic() {
        return this.heuristic;
    }
    
    public int getShortest() {
        return this.shortest;
    }
    
    public void setShortest(int value) {
        this.shortest = value;
    }
    
    public String toString() {
        return this.name;
    }
    
    public void addEdge(Edge e) {
        this.routes.add(e);
    }
    
    public ArrayList<Edge> getEdges() {
        return this.routes;
    }
    
    public Node getPrevious() {
        return this.previous;
    }
   
    public void setPrevious(Node handle) {
        this.previous = handle;
    }
    
    public void setCoords(int xx, int yy) {
        this.x = xx;
        this.y = yy;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    /**
     * Etäisyyden vertailu prioriteettijonon alkion valintaan.
     * @param o
     * @return 
     */
    @Override
    public int compareTo(Node o) {
        return (this.shortest + this.heuristic) - (o.getShortest() + o.getHeuristic());
    }  
}