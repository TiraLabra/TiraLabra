package Algorithms;

import Structures.Graph.Graph;
import Structures.Graph.Vertex;
import Structures.LinkedList.LinkedList;
import Structures.Hashtable.Hashtable;
import Structures.Queue.Queue;
import Structures.Stack.Stack;
import Utils.Color;
import Utils.Iterator;

/**
* Contains graph related algorithms
*/

public class Algorithm {
    private Graph graph;
    public Algorithm(Graph graph){
        this.graph=graph;
    }
    public boolean hasCycle(){
        Hashtable<Vertex,Color> color=new Hashtable<Vertex,Color>();
        LinkedList<Vertex> vertices=graph.getVertices();
        Iterator<Vertex> i = new Iterator<Vertex>(vertices);
        while(i.hasNext()){
            Vertex v = i.getNext();
            color.put(v, Color.WHITE);
        }
        i.clear();
        while(i.hasNext()){
            Vertex v = i.getNext();
            if(color.get(v)==Color.WHITE){
                if(DFSCycle(v,color)==true){
                    return true;
                }
            }
        }
        return false;
    }
    public LinkedList<Vertex> floodFill(Vertex a){
        return BFSFloodFill(a);
    }
    private LinkedList<Vertex> BFSFloodFill(Vertex a){
        Hashtable<Vertex,Color> color=new Hashtable<Vertex,Color>();
        LinkedList<Vertex> reached = new LinkedList<Vertex>();
        LinkedList<Vertex> vertices = graph.getVertices();
        Iterator<Vertex> j = new Iterator<Vertex>(vertices);
        while(j.hasNext()){
            Vertex v = j.getNext();
            color.put(v,Color.WHITE);
        }
        Queue<Vertex> queue = new Queue<Vertex>();
        color.put(a,Color.GREY);
        queue.enqueue(a);
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
    private boolean DFSCycle(Vertex a, Hashtable<Vertex,Color> color){       
        color.put(a,Color.GREY);
        LinkedList<Vertex> adjacencyVertices=graph.getAdjacencyVertices(a);
        Iterator<Vertex> i = new Iterator<Vertex>(adjacencyVertices);
        while(i.hasNext()){
            Vertex u = i.getNext();
            if(color.get(u)==Color.GREY){
                return true;
            }
            if(color.get(u)==Color.WHITE){
                if(DFSCycle(u,color)==true){
                    return true;
                }
            }
        }
        color.put(a,Color.BLACK);
        return false;
    }
}
