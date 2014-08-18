package tira.astar;

import java.util.ArrayList;
import java.util.HashMap;
import tira.main.Mapper;
import tira.main.Target;

/**
 *
 * @author joonaslaakkonen
 */
public class Astar {
    private ArrayList<Cell> cells;
    private HashMap<String, ArrayList<Target>> graph;
    private String destination;
    private String source;
    private Cell startNode;
    private Cell goalNode;

    public Astar(String start, String end, Mapper grid) {
        this.source = start;
        this.destination = end;
        this.graph = grid.getGrid();
        this.cells = new ArrayList<Cell>();
    }
    
    public void initialize() {
        int startX;
        int startY;
        int goalX;
        int goalY;
        
        for (String apu : this.graph.keySet()) {
            ArrayList<Target> targets = this.graph.get(apu);
            for (Target help : targets) {
                if (help.getName().equals(this.source)) {
                    startX = help.getX();
                    startY = help.getY();
                }
                
                if (help.getName().equals(this.destination)) {
                    goalX = help.getX();
                    goalY = help.getY();
                }
            }
        }
        
        for (String name : this.graph.keySet()) {
                Cell next = new Cell(name);
                this.cells.add(next);
        }
    }

}