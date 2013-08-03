/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import Structures.Graph.Graph;
import Structures.Graph.Vertex;
import Structures.Hashtable.Hashtable;
import Structures.LinkedList.LinkedList;
import Utils.Color;
import Utils.Iterator;

/**
 * Detects cycles in a graph
 */
public class CycleDetector {
    private Graph graph;
    public CycleDetector(Graph graph){
        this.graph=graph;
    }
    /**
    * Checks if the given graph has a cycle in it
    */
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
