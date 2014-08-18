package tira.astar;

/**
 *
 * @author joonaslaakkonen
 */
public class Path {
    public Cell target;
    public int weight;
    
    public Path(Cell destination, int distance){ 
        this.target = destination;
        this.weight = distance;
    }
    
    public String toString() {
        return this.target.toString() + this.weight;
    }
    
    public Cell getTarget() {
        return this.target;
    }

    public int getWeight() {
        return this.weight;
    }
}