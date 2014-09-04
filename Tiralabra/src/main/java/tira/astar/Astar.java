package tira.astar;

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
 * Astar olion luokka, joka vastaa reitin etsinnästä Astar algoritmilla. Käytössä heuristiikka joka vertailee
 * solmujen etäisyyttä niiden sijainnin perusteella (euklidinen etäisyys).
 */
public class Astar {
    
    private LinkedList<Node> cells;
    private LinkedList<Location> locations;
    private String destination;
    private String source;
    private Node startCell;
    private Node goalCell;
    private Helper path;

    public Astar(String start, String end, LinkedList grid) {
        this.source = start;
        this.destination = end;
        this.locations = grid;
        this.cells = new LinkedList<Node>();
        this.path = new Helper(this.cells);
    }
    
    /**
     * Alustus-metodi, joka luo Astar algoritmin tarvitsemat solmut ja polut tekstitiedostosta
     * tallenetun kartan perusteella.
     */  
    public void initialize() {
        for (Location apu : this.locations) {
            Node next = new Node(apu.toString());
            this.cells.add(next);
        }
        
        for (Node helper : this.cells) {
            Location next = (Location)this.locations.searchWithString(helper.toString()).getOlio();
            LinkedList<Target> targets = next.getTargets();
            for (Target finder : targets) {
                Node added = this.path.search(finder.getName());
                added.setCoords(finder.getX(), finder.getY());
                helper.addEdge(new Edge(added, finder.getDistance()));
            }
        }
        
        this.startCell = this.path.search(this.source);
        this.goalCell = this.path.search(this.destination);
        
        this.setHeuristics();
    }
    
    /**
     * Reitin haku algoritmilla.
     */  
    public void route() {  
        
        /**
         * Asetetaan alkusolmun etäisyydeksi nolla ja luodaan prioriteettijono, jonne lisätään
         * alkusolmu. Sen lisäksi luodaan lista, jonne käsitellyt solmut siirretään.
         */     
        this.startCell.setShortest(0);
        Heap<Node> heap = new Heap(this.cells.size());
        heap.insert(this.startCell);
        LinkedList<Node> closed = new LinkedList<Node>();
        
        /**
         * Käydään läpi keko. 
         */     
        while (!closed.contains(this.goalCell) && !heap.empty()) {
            Node handle = heap.poll();
            closed.add(handle);
            
            for (Edge apu : handle.getEdges()) {
                Node neighbor = apu.getTarget();
                int cost = handle.getShortest() + apu.getWeight();
                
                /**
                 * Etäisyyden päivitys mikäli ollaan löydetty parempi reitti.
                 */
                if (!closed.contains(neighbor)) {
                    if (neighbor.getShortest() > cost) {
                        neighbor.setShortest(cost);
                        neighbor.setPrevious(handle);
                        heap.insert(neighbor);
                    }
                }
            }
        }
    }
    
    /**
     * Tulostetaan lyhyin reitti alusta määränpäähän.
     */ 
    public void print() {
        if (this.goalCell.getShortest() == Integer.MAX_VALUE) {
            System.out.println("Reittiä ei ole kohteiden välillä");
        } else {
            System.out.println("Lyhyin reitti solmusta " + this.startCell.toString() + " solmuun " + this.goalCell.toString() + " on " + this.goalCell.getShortest() + "km.");         
            System.out.println(this.path.getRoute(this.goalCell));
        }    
    }    
    
    /**
     * Asetetaan arvio jokaiselle solmulle, kuinka pitkä matka siitä on maaliin.
     */
    private void setHeuristics() {
        for (Node setter : this.cells) {
            int xdiff = Math.abs(setter.getX() - this.goalCell.getX());
            int ydiff = Math.abs(setter.getY() - this.goalCell.getY());
            double heuristic = Math.sqrt((xdiff*xdiff + ydiff*ydiff));
            int parsed = (int)heuristic;
            setter.setHeuristic(parsed);
        }
    }
    
    /**
     * Seuraavaksi alla on metodeja, joita käytän vain testeissä päästäkseni käsiksi luokan muuttujiin.
     * Eivät siis vaikuta millään tavalla algoritmin tai ohjelman suoritukseen.
     */   
    public LinkedList<Node> getNodes() {
        return this.cells;
    }
    
    public Node getStart() {
        return this.startCell;
    }
    
    public Node getGoal() {
        return this.goalCell;
    }
    
    public Helper getHelperObject() {
        return this.path;
    }
    
    public String pathToGoalString() {
        String route = this.path.getRoute(this.goalCell);
        String path = "Lyhyin reitti solmusta " + this.startCell.toString() + " solmuun " + this.goalCell.toString() + " on " + this.goalCell.getShortest() + "km.";
        return path + route;
    }
}