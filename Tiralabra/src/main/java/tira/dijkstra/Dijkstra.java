package tira.dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
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

    public Dijkstra(String start, String end, Mapper grid) {
        this.source = start;
        this.destination = end;
        this.graph = grid.getGrid();
        this.nodes = new ArrayList<Node>();
    }
    
    /**
     * Metodi alustaa kartasta verkon solmut ja kaaret, joita käytetään Dijkstran algoritmissa.
     */
    public void initialize() {
        for (String apu : this.graph.keySet()) {
            Node next = new Node(apu);
            this.nodes.add(next);
        }
        
        for (Node helper : this.nodes) {
            for (Target finder : this.graph.get(helper.toString())) {
                Node added = findNodeByName(finder.getName());
                helper.addEdge(new Edge(added, finder.getDistance()));
            }
        }
        
        this.startNode = findNodeByName(this.source);
        this.goalNode = findNodeByName(this.destination);
    }
    
    /**
     * Reitin haku algoritmilla.
     * @param start
     * @param goal 
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
            List<Node> path = getShortestPath(this.goalNode);
            System.out.println("Alla reitti:\n" + path);
        }    
    }
    
    /**
     * Etsitään solmu nimen perusteella.
     * @param name
     * @return 
     */
    
    private Node findNodeByName(String name) {
        for (Node helper : this.nodes) {
            if (helper.toString().equals(name)) {
                return helper;
            }
        }
        return null;
    }
    
    /**
     * Luodaan lista reitistä solmujen välillä.
     * @param helper
     * @return 
     */

    private List<Node> getShortestPath(Node helper) {
        List<Node> path = new ArrayList<Node>();
        for (Node vertex = helper; vertex != null; vertex = vertex.getPrevious())
            path.add(vertex);
        Collections.reverse(path);
        return path;
    }

}