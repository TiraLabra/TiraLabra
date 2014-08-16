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
}
