package tira.astar;

import java.util.ArrayList;
import java.util.HashMap;
import tira.main.Mapper;
import tira.main.Target;

/**
 *
 * @author joonaslaakkonen
 * Astar olion luokka, joka vastaa reitin etsinnästä Astar algoritmilla. Käytössä heuristiikka joka vertailee
 * solmujen etäisyyttä niiden sijainnin perusteella.
 */
public class Astar {
    private ArrayList<Cell> cells;
    private HashMap<String, ArrayList<Target>> graph;
    private String destination;
    private String source;
    private Cell startCell;
    private Cell goalCell;

    public Astar(String start, String end, Mapper grid) {
        this.source = start;
        this.destination = end;
        this.graph = grid.getGrid();
        this.cells = new ArrayList<Cell>();
    }
    
    /**
     * Alustus-metodi, joka luo Astar algoritmin tarvitsemat solmut ja polut HashMapiin tallenetun kartan perusteella.
     */
    
    public void initialize() {
        for (String apu : this.graph.keySet()) {
            Cell next = new Cell(apu);
            this.cells.add(next);
        }
        
        for (Cell helper : this.cells) {
            for (Target finder : this.graph.get(helper.toString())) {
                Cell added = findCellByName(finder.getName());
                added.setCoords(finder.getX(), finder.getY());
                helper.addPath(new Path(added, finder.getDistance()));
            }
        }
        
        this.startCell = findCellByName(this.source);
        this.goalCell = findCellByName(this.destination);
    }
    
    /**
     * 
     * @param name
     * @return palauttaa haetun solmun jos sellainen on olemassa.
     */
    private Cell findCellByName(String name) {
        for (Cell helper : this.cells) {
            if (helper.toString().equals(name)) {
                return helper;
            }
        }
        return null;
    }
    
    /**
     * Kesken
     * @return 
     */
    private int updateHeuristic() {
        return 0;
    }
    
    /**
     * Alustuksen testailua.
     */
    public void test() {
        System.out.println(this.cells);
    }

}