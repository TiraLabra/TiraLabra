package Structures.Graph;

import Algorithms.BFS;
import Algorithms.BellmanFord;
import Algorithms.CycleDetector;
import Algorithms.Dijkstra;
import Algorithms.FloodFill;
import Algorithms.Path;
import Algorithms.PathFinder;
import Structures.LinkedList.LinkedList;
import Structures.Hashtable.Hashtable;
import Utils.Iterator;

/**
* Graph structure, which is defined by a set of vertices and the edges between them
*/

public class Graph {
    private Hashtable<Edge,Integer> weight;
    private int currentKey;
    private LinkedList<Vertex> vertices;
    int withWeight;
    int withNegativeWeight;
    public Graph(){
        withWeight=0;
        withNegativeWeight=0;
        this.weight=new Hashtable<Edge,Integer>();
        this.currentKey=0;
        this.vertices=new LinkedList<Vertex>();
    }
    /**
    * Connects two vertices in the graph with an edge having a weight of one
    * @param Two vertices to be connected
    */
    public void connect(Vertex a, Vertex b){
        if(!isConnected(a,b)){
            if(a.getKey()==-1){
                register(a);
            }
            if(b!=null && b.getKey()==-1){
                register(b);
            }
            if(b!=null){
                a.addAdjencyVertex(b);
                this.weight.put(new Edge(a,b), 1);
            }  
        }
    }
    /**
    * Connects two vertices in the graph with an edge having a certain weight
    * @param Two vertices to be connected and the weight of the edge
    */
    public void connect(Vertex a, Vertex b, int weight){
        if(!isConnected(a,b)){
            if(a.getKey()==-1){
                register(a);
            }
            if(b.getKey()==-1){
                register(b);
            }
            if(weight<0){
                this.withNegativeWeight++;
            }
            if(weight!=1){
                this.withWeight++;
            }
            a.addAdjencyVertex(b);
            this.weight.put(new Edge(a,b), weight);
        }      
    }
    /**
    * @param Two vertices and the weight to be connected in a way that a is connected to b and b is connected to a
    */
    public void connectBothWays(Vertex a, Vertex b, int weight){
        connect(a,b,weight);
        connect(b,a,weight);
    }
    /**
    * @param Two vertices to be connected in a way that a is connected to b and b is connected to a
    */
    public void connectBothWays(Vertex a, Vertex b){
        connect(a,b);
        connect(b,a);
    }
    /**
    * @param Two vertices to be disconnected. If these two vertices don't determine an edge, this method does nothing
    */
    public void disconnect(Vertex a, Vertex b){
        if(isConnected(a,b)){
            int edgeWeight = this.weight.get(new Edge(a,b));
            if(edgeWeight!=1){
                this.withWeight--;
            }
            if(edgeWeight<0){
                this.withNegativeWeight--;
            }
            this.weight.remove(new Edge(a,b));
            LinkedList<Vertex> adjacencyVertices = a.getAdjencyVertices();
            Iterator<Vertex> i = new Iterator<Vertex>(adjacencyVertices);
            while(i.hasNext()){
                Vertex v = i.getNext();
                if(v.equals(b)){
                    i.remove();
                }
            }
        }
    }
    /**
    * @param Two vertices, which determine an edge
    * @return The weight of the edge between given vertices or Integer.MIN_VALUE if there's no edge between them
    */
    public int getWeight(Vertex a, Vertex b){
        Edge key = new Edge(a,b);
        if(!isConnected(a,b)){
            return Integer.MIN_VALUE;
        }else{
            return this.weight.get(key);
        }
    }
    /**
    * @param Set the weight of the edge between vertex a and b
    */
    public void setWeight(Vertex a, Vertex b, int weight){
        if(!isConnected(a,b)){
            connect(a,b,weight);
        }else{
            int edgeWeight = this.weight.get(new Edge(a,b));
            if(edgeWeight!=1){
                this.withWeight--;
            }
            if(edgeWeight<0){
                this.withNegativeWeight--;
            }
            this.weight.put(new Edge(a,b), weight);
            if(weight!=1){
                this.withWeight++;
            }
            if(weight<0){
                this.withNegativeWeight++;
            }
        }
    }
    /**
    * @param A vertex which adjacency vertices needs to be known
    * @return List of vertex's adjacency vertices
    */
    public LinkedList<Vertex> getAdjacencyVertices(Vertex a){
        return a.getAdjencyVertices();
    }
    /**
    * All edges an their weights as a map
    * @return A map where edge between two vertices is the key to its weight
    */
    public Hashtable<Edge,Integer> getWeights(){
        return this.weight;
    }
    /**
    * Checks if two given vertices are connected in the graph
    * @param Two vertices of which connection wants to be known
    */    
    public boolean isConnected(Vertex a, Vertex b){
        if(a==null || b==null){
            return true;
        }
        Edge key = new Edge(a,b);
        if(!this.weight.containsKey(key)){
            return false;
        }
        return true;
    }
    private void register(Vertex a){
        this.vertices.add(a);
        a.setKey(this.currentKey);
        this.currentKey++;
    }
    /**
    * @return List of all vertices in the graph
    */
    public LinkedList<Vertex> getVertices(){
        return this.vertices;
    }
    /**
    * Finds the shortes path from vertex a to vertex b.
    * @param A start vertex and an end vertex
    * @return The path from vertex a to vertex b
    */
    public Path shortestPath(Vertex a, Vertex b){
        Path path;
        PathFinder pf;
        if(this.withWeight==0){
            pf=new BFS(this);
            path=pf.shortestPath(a, b);
        }else{
            if(this.withNegativeWeight!=0){
                pf=new BellmanFord(this);
                path=pf.shortestPath(a, b);
                
            }else{
                pf=new Dijkstra(this);
                path=pf.shortestPath(a, b);
            }
        }
        return path;
    }
    /**
    * Finds the shortes path from vertex a to vertex b using certain path finding algorithm.
    * @param A start vertex, an end vertex and a path finding algorithm
    * @return The path from vertex a to vertex b
    */
    public Path shortestPath(Vertex a, Vertex b, PathFinder pathfinder){
        return pathfinder.shortestPath(a, b);
    }
    /**
    * Finds the shortest distance between vertex a and vertex b
    * @return The shortest distance
    */
    public int distance(Vertex a, Vertex b){
        Path path;
        PathFinder pf;
        if(this.withWeight==0){
            pf=new BFS(this);
            path=pf.shortestPath(a, b);
        }else{
            if(this.withNegativeWeight!=0){
                pf=new BellmanFord(this);
                path=pf.shortestPath(a, b);
            }else{
                pf=new Dijkstra(this);
                path=pf.shortestPath(a, b);
            }
        }
        return path.getLength();
    }
    /**
    * Checks if the graph contains a cycle
    */
    public boolean hasCycle(){
        CycleDetector cd=new CycleDetector(this);
        return cd.hasCycle();
    }
    /**
    * Fills all vertices reached from vertex a using flood fill
    * @return List of vertices reached from vertex a
    */
    public LinkedList<Vertex> floodFill(Vertex a){
        FloodFill ff = new FloodFill(this);
        return ff.fill(a);
    }
}
