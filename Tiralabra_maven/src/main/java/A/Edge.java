package A;

/**
 * A class that represents a edge from a node to another node
 *
 */
public class Edge implements IEdge {

    /**
     * The node this edge is pointing at
     */
    INode node;
    /**
     * Cost to traverse this edge
     */
    int cost;

    public Edge(INode node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    /**
     * returns the cost to traverse this edge
     *
     * @return
     */
    public int getCost() {
        return cost;
    }

    /**
     * returns the destination node
     *
     * @return
     */
    public INode getDestination() {
        return node;
    }

}
