package tira.dijkstra;

import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import tira.common.Edge;
import tira.common.Node;
import tira.list.LinkedList;
import tira.utils.Helper;
import tira.utils.Location;
import tira.utils.Target;

/**
 *
 * @author joonaslaakkonen
 */
public class DijkstraTest {
    
    private LinkedList<Location> grid;
    private String start;
    private String end;
    private Dijkstra d;
    
    public DijkstraTest() {    
    }
    
    @Before
    public void setUp() {
        this.grid = this.doMap();
        this.start = "c";
        this.end = "d";
        this.d = new Dijkstra(start, end, grid);
    }

    /**
     * Test of initialize method, of class Dijkstra.
     * Testi testaa, että Nodet ja Edget on luotu, sen lisäksi tarkistetaan, että maali ja lähtö on oikein.
     */
    @Test
    public void testInitialize() {
        d.initialize();
        LinkedList<Node> nodes = d.getNodes();
        Node a = (Node)nodes.get(0);
        Node b = (Node)nodes.get(nodes.size()-1);
        LinkedList<Edge> edgesOne = a.getEdges();
        LinkedList<Edge> edgesLast = b.getEdges();
        Helper help = d.getHelperObject();
        assertEquals(4, nodes.size());
        assertEquals(3, edgesOne.size());
        assertEquals(3, edgesLast.size());
        assertEquals(d.getGoal(), help.search(this.end));
        assertEquals(d.getStart(), help.search(this.start));
    }

    /**
     * Test of route method, of class Dijkstra.
     */
    @Test
    public void testRoute() {
        d.initialize();
        d.route();
        Helper help = d.getHelperObject();     
        Node goal = help.search(this.end);
        Node previousFromGoal = help.search(this.end).getPrevious();
        assertEquals(25, goal.getShortest());
        assertEquals(help.search("a"), previousFromGoal);
    }

    /**
     * Test of print method, of class Dijkstra.
     */
    @Test
    public void testPrint() {     
        d.initialize();
        d.route();
        d.print();
        Helper help = d.getHelperObject();
        String vastaus = d.pathToGoalString();
        String tulos = "Lyhyin reitti solmusta " + help.search(start).toString() + " solmuun " + help.search(end).toString() + " on " + help.search(end).getShortest() + "km.cad";
        assertEquals(vastaus, tulos);
        
        
    }
    
    private LinkedList<Location> doMap() {
        LinkedList<Location> graph = new LinkedList<Location>();
        Location a = new Location("a");
        Location b = new Location("b");
        Location c = new Location("c");
        Location d = new Location("d");
        
        a.add(new Target("b", 15, 0, 0));
        a.add(new Target("c", 10, 0, 0));
        a.add(new Target("d", 15, 0, 0));
        
        b.add(new Target("a", 15, 0, 0));
        b.add(new Target("c", 30, 0, 0));
        b.add(new Target("d", 5, 0, 0));
        
        c.add(new Target("a", 10, 0, 0));
        c.add(new Target("b", 30, 0, 0));
        c.add(new Target("d", 75, 0, 0));
        
        d.add(new Target("a", 15, 0, 0));
        d.add(new Target("b", 5, 0, 0));
        d.add(new Target("c", 75, 0, 0));
        
        graph.add(a);
        graph.add(b);
        graph.add(c);
        graph.add(d);

        return graph;
    }  
}