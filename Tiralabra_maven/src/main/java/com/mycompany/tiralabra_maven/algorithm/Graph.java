
package com.mycompany.tiralabra_maven.algorithm;

import com.mycompany.tiralabra_maven.datastructures.List;

/**
 * Graph interface
 * @author Yessergire Mohamed
 */
public interface Graph {

    /**
     * 
     * @return adjacent nodes of s
     */
    List<Node> getAdjacent(Node s);

    /**
     *
     * @return the weight of the path a and b.
     */
    int weight(Node a, Node b);

}
