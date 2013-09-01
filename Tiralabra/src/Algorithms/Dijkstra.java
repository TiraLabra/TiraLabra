package Algorithms;

import Structures.Graph.Graph;
import Structures.Graph.Vertex;
import Structures.Heap.MinHeap;
import Structures.LinkedList.LinkedList;
import Structures.Hashtable.Hashtable;
import Utils.Iterator;

/**
* Dijkstra's algorithm for finding the shortest path between vertex a and b. Only works with a graph that has only positive weights
*/

public class Dijkstra extends PathFinder{
    private Graph graph;
    public Dijkstra(Graph graph){
        super(graph);
        this.graph=graph;
    }
    @Override
    public Path shortestPath(Vertex a, Vertex b) {
        Hashtable<Vertex,Integer> distance=new Hashtable<Vertex,Integer>(graph.getVertices().size()*2);
        Hashtable<Vertex,Vertex> path=new Hashtable<Vertex,Vertex>(graph.getVertices().size()*2);
        Iterator<Vertex> r = new Iterator<Vertex>(graph.getVertices());
        MinHeap<Vertex> h = new MinHeap<Vertex>(graph.getVertices().size());
        while(r.hasNext()){
            Vertex v = r.getNext();
            distance.put(v, Integer.MAX_VALUE-10000);
            path.put(v, null);
        }
        distance.put(a,0);
        r.clear();
        while(r.hasNext()){
            Vertex v = r.getNext();
            h.insert(distance.get(v), v);
        }
        Vertex u = h.pop();
        while(!h.isEmpty()){
            LinkedList<Vertex> adjacencyVertices = graph.getAdjacencyVertices(u);
            Iterator<Vertex> i = new Iterator<Vertex>(adjacencyVertices);
            while(i.hasNext()){
                Vertex v = i.getNext();
                if(h.inHeap(v)){
                    super.relax(u,v,distance,path);
                    h.decrease(v,distance.get(v));
                }
            }
            u=h.pop();
        }
        return super.getPath(a,b,path,distance);
    }
}
