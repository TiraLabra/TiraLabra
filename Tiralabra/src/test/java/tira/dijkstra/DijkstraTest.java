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
import tira.utils.Target;

/**
 *
 * @author joonaslaakkonen
 */
public class DijkstraTest {
    
    private HashMap<String, ArrayList<Target>> grid;
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
//        LinkedList<Edge> edgesOne = nodes.get(0).getEdges();
//        LinkedList<Edge> edgesLast = nodes.get(nodes.size()-1).getEdges();
        Helper help = d.getHelperObject();
        assertEquals(4, nodes.size());
//        assertEquals(3, edgesOne.size());
//        assertEquals(3, edgesLast.size());
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
    
    private HashMap<String, ArrayList<Target>> doMap() {
        HashMap<String, ArrayList<Target>> graph = new HashMap<String, ArrayList<Target>>();
        ArrayList<Target> a = new ArrayList<Target>();
        ArrayList<Target> b = new ArrayList<Target>();
        ArrayList<Target> c = new ArrayList<Target>();
        ArrayList<Target> d = new ArrayList<Target>();
        
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
        
        graph.put("a", a);
        graph.put("b", b);
        graph.put("c", c);
        graph.put("d", d);

        return graph;
    }  
}