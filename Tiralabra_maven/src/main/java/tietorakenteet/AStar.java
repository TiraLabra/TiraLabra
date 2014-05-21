package tietorakenteet;

import java.util.PriorityQueue;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * A Star -reitinhaun luokka.
 */
public class AStar {
    
    
    // A-starin rakenteita, oikeat tyypit inttien tilalle määriteltävä...
    private int[] kaydyt;
    
    private TreeSet kaydyt2;
    //private int[] kaymatta;
    
    private PriorityQueue<Node> kaymatta;
    private SortedSet<Node> kaymatta2;
    
    private int[] kuljettuReitti;

    public AStar() {
        kaydyt2 = new TreeSet();
    }
    
    /**
     *
     * @param a
     * @param alku
     * @param loppu 
     */
    public void AStarHaku(Alue a, Node alku, Node loppu) {
        
        // Alustetaan 
        kaymatta.add(alku);
        
        
        
    }
    
    
    

}
