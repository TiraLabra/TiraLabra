package tira.dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import tira.common.Helper;
import tira.common.Edge;
import tira.common.Node;
import tira.main.Mapper;
import tira.main.Target;

/**
 *
 * @author joonaslaakkonen
 * Luokka etsii reitin kartalla käyttäen Dijkstran algoritmia.
 */
public class Dijkstra {
    
    private String source;
    private String destination;
    private HashMap<String, ArrayList<Target>> graph;
    private ArrayList<Node> nodes;
    private Node startNode;
    private Node goalNode;
    private Helper path;

    public Dijkstra(String start, String end, HashMap grid) {
        this.source = start;
        this.destination = end;
        this.graph = grid;
        this.nodes = new ArrayList<Node>();
        this.path = new Helper(this.nodes);
    }
    
    /**
     * Metodi alustaa kartasta verkon solmut ja kaaret, joita käytetään Dijkstran algoritmissa.
     * Sen lisäksi asetetaan muistiin lähtö -ja maalisolmut.
     */
    public void initialize() {
        for (String apu : this.graph.keySet()) {
            Node next = new Node(apu);
            this.nodes.add(next);
        }
        
        for (Node helper : this.nodes) {
            for (Target finder : this.graph.get(helper.toString())) {
                Node added = this.path.search(finder.getName());
                helper.addEdge(new Edge(added, finder.getDistance()));
            }
        }
        
        this.startNode = this.path.search(this.source);
        this.goalNode = this.path.search(this.destination);
    }
    
    /**
     * Reitin haku algoritmilla.
     */  
    public void route() {
        
        /**
         * Asetetaan alkusolmun etäisyydeksi nolla ja luodaan prioriteettijono, jonne lisätään
         * alkusolmu.
         */     
        this.startNode.setShortest(0);
        PriorityQueue<Node> queue = new PriorityQueue<Node>();
        queue.add(this.startNode);
        
        /**
         * Käydään läpi prioriteettijono. 
         */      
        while (!queue.isEmpty()) {
            Node handle = queue.poll();
            
            for (Edge apu : handle.getEdges()) {
                Node neighbor = apu.getTarget();
                int weight = apu.getWeight();
                int distance = handle.getShortest() + weight;
                
                /**
                 * Relaksointi.
                 */
                if (distance < neighbor.getShortest()) {
                    queue.remove(neighbor);
                    neighbor.setShortest(distance);
                    neighbor.setPrevious(handle);
                    queue.add(neighbor);
                }
            }
        }
    }

    /**
     * Tulostetaan lyhyin reitti alusta määränpäähän.
     */   
    public void print() {
        if (this.goalNode.getShortest() == Integer.MAX_VALUE) {
            System.out.println("Reittiä ei ole kohteiden välillä");
        } else {
            System.out.println("Lyhyin reitti solmusta " + this.startNode.toString() + " solmuun " + this.goalNode.toString() + " on " + this.goalNode.getShortest() + "km.");
            List<Node> path = this.path.getRoute(this.goalNode);
            System.out.println("Alla reitti:\n" + path);
        }    
    }
}