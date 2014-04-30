package com.mycompany.tiralabra_maven.algorithm;

import com.mycompany.tiralabra_maven.datastructures.List;

/**
 * The interface of the search algorithm
 * @author Yessergire Mohamed
 */
public interface Search {

    /**
     * Tries to find a path for start node to goal node.
     * @param start start node
     * @param goal goal node
     * @return a list of nodes representing a path from start to goal
     * it exists.
     */
    List<Node> findPath(Node start, Node goal);
    
}
