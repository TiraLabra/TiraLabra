package tira.dijkstra;

import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import tira.main.Target;

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
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        d.initialize();      
    }

    /**
     * Test of route method, of class Dijkstra.
     */
    @Test
    public void testRoute() {
        d.initialize();
        System.out.println("route");
        d.route();
    }

    /**
     * Test of print method, of class Dijkstra.
     */
    @Test
    public void testPrint() {
        d.initialize();
        d.route();
        System.out.println("print");
        d.print();
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