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
    }
    
    /**
     * Metodilla testaan onko alustus nodeiksi ja vertexeiksi onnistunut oikein
     */
    public void initHelper() {
        for (Node apu : this.nodes) {
            System.out.println("Solu " + apu.toString());
            System.out.println("Naapurit " + apu.printEdges());          
        }
    }
    
    /**
     * Reitin haku algoritmilla.
     * @param start
     * @param goal 
     */
    
    public void route(String start, String goal) {
        Node startNode = null;
        Node goalNode = null;
        
        /**
         * Haetaan käyttäjän syötteen perusteella aloitus -ja maalisolmut. 
         */
        
        for (Node looker : this.nodes) {
            if (start.equals(looker.toString())) {
                startNode = looker;
            }
            if (goal.equals(looker.toString())) {
                goalNode = looker;
            }
        }
        
        /**
         * Asetetaan alkusolmun etäisyydeksi nolla ja luodaan prioriteettijono, jonne lisätään
         * alkusolmu.
         */
        
        startNode.setShortest(0);
        PriorityQueue<Node> queue = new PriorityQueue<Node>();
        queue.add(startNode);
        
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
    
    public void print(String start, String goal) {
        Node target = findNodeByName(goal);
        Node source = findNodeByName(start);
        if (target.getShortest() == Integer.MAX_VALUE) {
            System.out.println("Reittiä ei ole kohteiden välillä");
        } else {
            System.out.println("Lyhyin reitti solmusta " + source.toString() + " solmuun " + target.toString() + " on " + target.getShortest() + "km.");
            List<Node> path = getShortestPath(target);
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