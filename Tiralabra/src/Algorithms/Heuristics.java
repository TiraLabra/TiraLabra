package Algorithms;

import Structures.Graph.Vertex;

/**
* Determines a heuristic for a path finding algorithm
*/

public interface Heuristics {
    /**
    * Heuristic for vertex a and b. F.e. in A* returns the manhattan distance between the two
    */
    public int getHeuristics(Vertex a, Vertex b);
}
