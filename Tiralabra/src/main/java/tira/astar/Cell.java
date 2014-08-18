package tira.astar;

/**
 *
 * @author joonaslaakkonen
 */
public class Cell implements Comparable<Cell> {
    
    private Cell parent;
    private int shortestPath;
    private int heuristic;
    private String node;
    private int estimation;
    
    public Cell(String name) {
        this.parent = null;
        this.shortestPath = Integer.MAX_VALUE;
        this.node = name;
    }
    
    public void setHeuristinc(int value) {
        this.heuristic = value;
    }
    
    public int getHeuristic() {
        return this.heuristic;
    }
    
    @Override
    public int compareTo(Cell o) {
        return this.heuristic - o.getHeuristic();
    }
    
}
