package A;

/**
 * A_Star data associated with a node.
 * The class keeps track of cost to current node from start, heuristicCost to
 * finish and where the shortest path came from.
 * @author Lutikka
 */
public class NodeData {
    /**
     * Cost to this node from start so far
     */
    private int costSoFar;
    /**
     * Heuristic cost to finish
     */
    private double heuristicCost;
    /**
     * Node from which the shortest path came from
     */
    private INode cameFrom;

    public int getCostSoFar() {
        return costSoFar;
    }

    public void setCostSoFar(int costSoFar) {
        this.costSoFar = costSoFar;
    }

    public double getHeuristicCost() {
        return heuristicCost;
    }

    public void setHeuristicCost(double heuristicCost) {
        this.heuristicCost = heuristicCost;
    }
   
    public INode cameFrom(){
        return cameFrom;
    }

    public void setCameFrom(INode cameFrom) {
        this.cameFrom = cameFrom;
    }
    
}
