package tira.dijkstra;

import tira.common.Edge;
import tira.common.Node;
import tira.heap.Heap;
import tira.list.LinkedList;
import tira.utils.Helper;
import tira.utils.Location;
import tira.utils.Target;

/**
 *
 * @author joonaslaakkonen
 * Luokka etsii reitin kartalla käyttäen Dijkstran algoritmia.
 */
public class Dijkstra {
    
    private String source;
    private String destination;
    private LinkedList<Location> locations;
    private LinkedList<Node> nodes;
    private Node startNode;
    private Node goalNode;
    private Helper path;

    public Dijkstra(String start, String end, LinkedList map) {
        this.source = start;
        this.destination = end;
        this.nodes = new LinkedList<Node>();
        this.path = new Helper(this.nodes);
        this.locations = map;
    }
    
    /**
     * Metodi alustaa kartasta verkon solmut ja kaaret, joita käytetään Dijkstran algoritmissa.
     * Sen lisäksi asetetaan muistiin lähtö -ja maalisolmut.
     */
    public void initialize() {
        for (Location loc : this.locations) {
            Node next = new Node(loc.toString());
            this.nodes.add(next);
        }
        
        for (Node helper : this.nodes) {
            Location next = (Location)this.locations.searchWithString(helper.toString()).getOlio();
            LinkedList<Target> targets = next.getTargets();
            for (Target finder : targets) {
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
        Heap<Node> heap = new Heap(this.nodes.size());
        heap.insert(this.startNode);
        
        /**
         * Käydään läpi keko. 
         */      
        while (!heap.empty()) {
            Node handle = heap.poll();
            
            for (Edge apu : handle.getEdges()) {
                Node neighbor = apu.getTarget();
                int weight = apu.getWeight();
                int distance = handle.getShortest() + weight;
                
                /**
                 * Relaksointi.
                 */
                if (distance < neighbor.getShortest()) {
                    neighbor.setShortest(distance);
                    neighbor.setPrevious(handle);
                    heap.insert(neighbor);                   
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
            System.out.println(this.path.getRoute(this.goalNode));
        }    
    }
    
    /**
     * Seuraavaksi alla on metodeja, joita käytän vain testeissä päästäkseni käsiksi luokan muuttujiin.
     * Eivät siis vaikuta millään tavalla algoritmin tai ohjelman suoritukseen.
     */   
    public LinkedList<Node> getNodes() {
        return this.nodes;
    }
    
    public Node getStart() {
        return this.startNode;
    }
    
    public Node getGoal() {
        return this.goalNode;
    }
    
    public Helper getHelperObject() {
        return this.path;
    }
    
    public String pathToGoalString() {
        String route = this.path.getRoute(this.goalNode);
        String path = "Lyhyin reitti solmusta " + this.startNode.toString() + " solmuun " + this.goalNode.toString() + " on " + this.goalNode.getShortest() + "km.";
        return path + route;
    }
}