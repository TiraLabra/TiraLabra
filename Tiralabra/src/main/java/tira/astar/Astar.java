package tira.astar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
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

    public Astar(String start, String end, HashMap grid) {
        this.source = start;
        this.destination = end;
        this.graph = grid;
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
        
        this.setHeuristics();
    }
    
    /**
     * Reitin haku algoritmilla.
     */  
    public void route() {
        
        this.startCell.setShortest(0);
        PriorityQueue<Cell> queue = new PriorityQueue<Cell>();
        ArrayList<Cell> closed = new ArrayList<Cell>();
        
        for (Path apu : this.startCell.getRoutes()) {
            queue.add(apu.getTarget());
            apu.getTarget().setPrevious(this.goalCell);
            apu.getTarget().setShortest(apu.getWeight());
        }
        closed.add(this.startCell);
        
        while (!queue.isEmpty()) {
            Cell handle = queue.poll();
            
            for (Path next : handle.getRoutes()) {
                if (!closed.contains(next.getTarget())) {
                    next.getTarget().setPrevious(handle);
                    next.getTarget().setShortest(handle.getShortest() + next.getWeight());
                }
            }
        }
        
        
//        
//        /**
//         * Asetetaan alkusolmun etäisyydeksi nolla ja luodaan prioriteettijono, jonne lisätään
//         * alkusolmu. Sen lisäksi luodaan lista, jonne käsitellyt solmut siirretään.
//         */
//        
//        this.startCell.setShortest(0);
//        PriorityQueue<Cell> queue = new PriorityQueue<Cell>();
//        queue.add(this.startCell);
//        ArrayList<Cell> closed = new ArrayList<Cell>();
//        
//        /**
//         * Käydään läpi prioriteettijono. 
//         */
//        
//        while (!queue.isEmpty()) {
//            Cell handle = queue.poll();
//            closed.add(handle);
//            
//            for (Path apu : handle.getRoutes()) {
//                Cell neighbor = apu.getTarget();
//                int cost = handle.getShortest() + apu.getWeight();
//                int guess = cost + neighbor.getHeuristic();
//                
//                if (closed.contains(neighbor) && guess >= neighbor.getShortest()) {
//                    ;
//                } else if (!queue.contains(neighbor) || guess < neighbor.getShortest()) {
//                    neighbor.setPrevious(handle);
//                    neighbor.setShortest(cost);
//                    neighbor.setHeuristic(guess);
//                    
//                    if (queue.contains(neighbor)) {
//                        queue.remove(neighbor);
//                    }
//                    
//                    queue.add(neighbor);
//                }
//            }
//        }
    }
    
    /**
     * Tulostetaan lyhyin reitti alusta määränpäähän.
     */ 
    public void print() {
        if (this.goalCell.getShortest() == Integer.MAX_VALUE) {
            System.out.println("Reittiä ei ole kohteiden välillä");
        } else {
            System.out.println("Lyhyin reitti solmusta " + this.startCell.toString() + " solmuun " + this.goalCell.toString() + " on " + this.goalCell.getShortest() + "km.");
            List<Cell> path = getShortestPath(this.goalCell);
            System.out.println("Alla reitti:\n" + path);
        }    
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
     * Asetetaan arvio jokaiselle solmulle, kuinka pitkä matka siitä on maaliin.
     */
    private void setHeuristics() {
        for (Cell setter : this.cells) {
            int xdiff = Math.abs(setter.getX() - this.goalCell.getX());
            int ydiff = Math.abs(setter.getY() - this.goalCell.getY());
            double heuristic = Math.sqrt((xdiff*xdiff + ydiff*ydiff));
            int parsed = (int)heuristic;
            setter.setHeuristic(parsed);
        }
    }
    
    /**
     * Luodaan lista reitistä solmujen välillä.
     * @param helper
     * @return 
     */
    private List<Cell> getShortestPath(Cell helper) {
        List<Cell> path = new ArrayList<Cell>();
        for (Cell vertex = helper; vertex != null; vertex = vertex.getPrevious())
            path.add(vertex);
        Collections.reverse(path);
        return path;
    }
}