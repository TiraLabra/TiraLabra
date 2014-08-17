package tira.tiralabra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * @author joonaslaakkonen
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
    
    public void route(String start) {
        Node startNode = null;
        for (Node looker : this.nodes) {
            if (start.equals(looker.toString())) {
                startNode = looker;
            }
        }
        startNode.setShortest(0);
        PriorityQueue<Node> queue = new PriorityQueue<Node>();
        queue.add(startNode);
        
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

    private Node findNodeByName(String name) {
        for (Node helper : this.nodes) {
            if (helper.toString().equals(name)) {
                return helper;
            }
        }
        return null;
    }

    void print() {
        for (Node helper : this.nodes) {
            System.out.println("Minimimatka: " + helper.getShortest());
            List<Node> path = getShortestPath(helper);
            System.out.println(path);
        }
    }

    private List<Node> getShortestPath(Node helper) {
        List<Node> path = new ArrayList<Node>();
        for (Node vertex = helper; vertex != null; vertex = vertex.getPrevious())
            path.add(vertex);
        Collections.reverse(path);
        return path;
    }
}