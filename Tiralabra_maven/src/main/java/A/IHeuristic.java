package A;

import A.INode;

/**
 * class containing a method for estimating heuristic distance from a node to another node
 */
public interface IHeuristic {
    
    /**
     * Returns heuristic distance from node1 to anode2
     * @param n1 first node
     * @param n2 second node
     * @return 
     */
    public double Distance(Object o1, Object o2);
}
