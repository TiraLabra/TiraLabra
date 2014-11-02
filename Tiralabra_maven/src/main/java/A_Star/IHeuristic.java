package A_Star;

/**
 * Heuristic class containing a method for estimating distance from node to another node
 * @author Lutikka
 */
public interface IHeuristic {
    
    /**
     * Returns Distance from INode n1 to n2
     * @param n1 first node
     * @param n2 second node
     * @return 
     */
    public int Distance(INode n1, INode n2);
}
