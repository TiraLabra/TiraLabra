
package com.mycompany.tiralabra_maven.algorithm;

import com.mycompany.tiralabra_maven.datastructures.List;

public interface Graph {

    Node getStartNode();

    boolean isGoalNode(Node g);

    List<Node> getSuccessors(Node s);

    int weight(Node from, Node to);

    Heuristic getHeuristic();
}
