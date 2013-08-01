/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import Structures.Graph.Vertex;

/**
* Interface for a path finder algorithm
*/

public interface PathFinder {
    /*
     * @param Start vertex and end vertex of a path
     * @return The shortest path between vertex a and vertex b
     */
    public Path shortestPath(Vertex a, Vertex b);
}
