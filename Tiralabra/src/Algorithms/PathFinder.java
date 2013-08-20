package Algorithms;

import Structures.Graph.Graph;
import Structures.Graph.Vertex;
import Structures.Hashtable.Hashtable;
import Structures.LinkedList.LinkedList;
import Structures.Stack.Stack;

/**
* A super class for a path finder algorithm
*/
public abstract class PathFinder {
    private Graph graph;
    public PathFinder(Graph graph){
        this.graph=graph;
    }
    /**
     * Relaxes the distance between two vertices if necessary
     */
    public void relax(Vertex a, Vertex b, Hashtable<Vertex,Integer> distance, Hashtable<Vertex,Vertex> path){
        if(distance.get(b)>distance.get(a)+graph.getWeight(a,b)){
            distance.put(b,distance.get(a)+graph.getWeight(a,b));
            path.put(b,a);
        }
    }
    public Path getPath(Vertex a, Vertex b, Hashtable<Vertex,Vertex> path, Hashtable<Vertex,Integer> distance){
        Vertex u = path.get(b);
        Stack<Vertex> stack=new Stack<Vertex>();
        int d = distance.get(b);
        LinkedList<Vertex> p=new LinkedList<Vertex>();
        while(u!=a){
            stack.push(u);
            u=path.get(u);
        }
        while(!stack.isEmpty()){
            Vertex v=stack.pop(); 
            p.add(v);
        }
        return new Path(p,d);
    }
    /**
     * @param The start vertex and  the end vertex of a path
     * @return The shortest path between vertex a and vertex b
     */
    public abstract Path shortestPath(Vertex a, Vertex b); 
}
