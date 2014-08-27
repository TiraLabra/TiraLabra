package tira.astar;

/**
 *
 * @author joonaslaakkonen
 * Luokka kuvaa polkua kahden verkon Cell -olion välillä. Polulla on etäisyys ja kohdesolmu.
 */
public class Path {
    private Cell target;
    private int weight;
    
    public Path(Cell destination, int distance){ 
        this.target = destination;
        this.weight = distance;
    }
    
    /**
     * 
     * Gettereitä ja settereitä. 
     */   
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