package tira.tiralabra;

/**
 *
 * @author joonaslaakkonen
 */
class Edge {
    public Node target;
    public int weight;
    
    public Edge(Node destination, int distance){ 
        this.target = destination;
        this.weight = distance;
    }
    
    public String toString() {
        return this.target.toString() + this.weight;
    }
    
    public Node getTarget() {
        return this.target;
    }

    public int getWeight() {
        return this.weight;
    }
}