package tira.common;

import tira.list.LinkedList;

/**
 *
 * @author joonaslaakkonen
 * Luokka kuvaa kartan solmua. Luokka toteuttaa Comparable rajapinnan olioiden vertailua varten.
 */
public class Node implements Comparable<Node> {
    
    private Node previous;
    private String name;
    private LinkedList<Edge> routes;
    private int shortest;
    private int heuristic;
    private int x;
    private int y;
    private boolean inHeap;
    private boolean closed;
    
    public Node(String name) {
        this.previous = null;
        this.name = name;
        this.shortest = Integer.MAX_VALUE;
        this.routes = new LinkedList<Edge>();
        this.heuristic = 0;
        this.inHeap = false;
        this.closed = false;
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
    
    public LinkedList<Edge> getEdges() {
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
     * Talletetaan tieto, että solmu on keossa.
     */
    public void addedToHeap() {
        this.inHeap = true;
    }
    
    /**
     * Solmu pois keosta.
     */
    public void removedFromHeap() {
        this.inHeap = false;
    }
    
    public boolean inHeap() {
        return this.inHeap;
    }
    
    /**
     * 
     * @return tieto onko solmu käsitelty.
     */
    public boolean isClosed() {
        return this.closed;
    }
    
    /**
     * Merkitään solmu käsitellyksi.
     */
    public void close() {
        this.closed = true;
    }
    
    /**
     * Metodi tutkii, ovatko oliot samat.
     * @param o vertailtava olio.
     * @return kertoo tiedon ovatko oliot samoja vai eivät.
     */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (getClass() != o.getClass()) {
            return false;
        }

        Node compared = (Node) o;

        if (!this.name.equals(compared.toString())) {
            return false;
        }

        if (this.x != compared.getX() || this.y != compared.getY()) {
            return false;
        }

        return true;
    }
    
    /**
     * Etäisyyden vertailu, jota tarvitaan minimikeossa.
     * @param o Solmu johon verrataan.
     * @return palauttaa tiedon laskutoimituksesta.
     */
    @Override
    public int compareTo(Node o) {
        return (this.shortest + this.heuristic) - (o.getShortest() + o.getHeuristic());
    }  
}