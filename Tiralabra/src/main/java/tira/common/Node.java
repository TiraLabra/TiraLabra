package tira.common;

import java.util.ArrayList;
import tira.list.LinkedList;

/**
 *
 * @author joonaslaakkonen
 * Luokka kuvaa kartan solmua. Luokka toteuttaa Comparable rajapinnan prioriteettijonon vertailua varten.
 */
public class Node implements Comparable<Node> {
    
    private Node previous;
    private String name;
    private ArrayList<Edge> routes;
    private LinkedList<Edge> routs;
    private int shortest;
    private int heuristic;
    private int x;
    private int y;
    
    public Node(String name) {
        this.previous = null;
        this.name = name;
        this.shortest = Integer.MAX_VALUE;
        this.routes = new ArrayList<Edge>();
        this.routs = new LinkedList<Edge>();
        this.heuristic = 0;
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
        this.routs.add(e);
    }
    
    public LinkedList<Edge> getEdges() {
//        return this.routes;
        return this.routs;
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
    
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (getClass() != o.getClass()) {
            return false;
        }

        Node compared = (Node) o;

        if (this.name != compared.toString()) {
            return false;
        }

        if (this.x != compared.getX() || this.y != compared.getY()) {
            return false;
        }

        return true;
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