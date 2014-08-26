package tira.astar;

import java.util.ArrayList;

/**
 *
 * @author joonaslaakkonen
 * Luokka kuvaa Astar-algoritmilla tutkittavan verkon solmuja.
 */
public class Cell implements Comparable<Cell> {
    
    private Cell parent;
    private int shortestPath;
    private int heuristic;
    private String node;
    private ArrayList<Path> routes;
    private int x;
    private int y;
    
    public Cell(String name) {
        this.parent = null;
        this.shortestPath = Integer.MAX_VALUE;
        this.node = name;
        this.routes = new ArrayList<Path>();
    }
    
    /**
     * 
     * Gettereitä ja settereitä.
     */
    
    public void setHeuristic(int value) {
        this.heuristic = value;
    }
    
    public int getHeuristic() {
        return this.heuristic;
    }
    
    public String toString() {
        return this.node;
    }
    
    @Override
    public int compareTo(Cell o) {
        return this.heuristic - o.getHeuristic();
    }

    public void addPath(Path path) {
        this.routes.add(path);
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

    public void setShortest(int i) {
        this.shortestPath = i;
    }
    
    public ArrayList<Path> getRoutes() {
        return this.routes;
    }

    public int getShortest() {
        return this.shortestPath;
    }

    public void setPrevious(Cell cel) {
        this.parent = cel;
    }

    public Cell getPrevious() {
        return this.parent;
    }
    
}
