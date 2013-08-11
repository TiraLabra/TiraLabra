
package Algorithms;

import Structures.Graph.Graph;
import Structures.Graph.Vertex;
import Structures.Hashtable.Hashtable;

/**
 * Super class for path finding algorithm that uses relax method for edges
 */
public class Relax {
    private Graph graph;
    public Relax(Graph graph){
        this.graph=graph;
    }
    public void relax(Vertex a, Vertex b, Hashtable<Vertex,Integer> distance, Hashtable<Vertex,Vertex> path){
        if(distance.get(b)>distance.get(a)+graph.getWeight(a,b)){
            distance.put(b,distance.get(a)+graph.getWeight(a,b));
            path.put(b,a);
        }
    }
}
