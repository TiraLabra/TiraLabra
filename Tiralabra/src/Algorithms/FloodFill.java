package Algorithms;

import Structures.Graph.Graph;
import Structures.Graph.Vertex;
import Structures.Hashtable.Hashtable;
import Structures.LinkedList.LinkedList;
import Structures.Queue.Queue;
import Utils.Color;
import Utils.Iterator;

/**
 * Flood fill algorithm
 */
public class FloodFill {
    private Graph graph;
    public FloodFill(Graph graph){
        this.graph=graph;
    }
    /**
    * Starts flood fill from the given vertex
    * @param The starting vertex of flood fill
    * @return A list of vertices reached from the given start vertex
    */
    public LinkedList<Vertex> fill(Vertex start){
        return BFSFloodFill(start);
    }
    private LinkedList<Vertex> BFSFloodFill(Vertex start){
        Hashtable<Vertex,Color> color=new Hashtable<Vertex,Color>();
        LinkedList<Vertex> reached = new LinkedList<Vertex>();
        LinkedList<Vertex> vertices = graph.getVertices();
        Iterator<Vertex> j = new Iterator<Vertex>(vertices);
        while(j.hasNext()){
            Vertex v = j.getNext();
            color.put(v,Color.WHITE);
        }
        Queue<Vertex> queue = new Queue<Vertex>();
        color.put(start,Color.GREY);
        queue.enqueue(start);
        while(!queue.isEmpty()){
            Vertex u=queue.dequeue();
            LinkedList<Vertex> adjacencyVertices=graph.getAdjacencyVertices(u);
            Iterator<Vertex> r = new Iterator<Vertex>(adjacencyVertices);
            while(r.hasNext()){
                Vertex h = r.getNext();
                if(color.get(h)==Color.WHITE){
                    color.put(h, Color.GREY);
                    queue.enqueue(h);
                }
            }
            color.put(u, Color.BLACK);
            reached.add(u);
        }
        return reached;
    }
}
