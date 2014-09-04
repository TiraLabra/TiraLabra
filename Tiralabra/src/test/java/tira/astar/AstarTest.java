package tira.astar;

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
public class AstarTest {
    
    private LinkedList<Location> grid;
    private String start;
    private String end;
    private Astar a;
    
    public AstarTest() {
    }
    
    @Before
    public void setUp() {
        this.grid = this.doMap();
        this.start = "a";
        this.end = "b";
        this.a = new Astar(start, end, grid);
    }


    /**
     * Test of initialize method, of class Astar.
     */
    @Test
    public void testInitialize() {
        a.initialize();
        LinkedList<Node> nodes = a.getNodes();
        Node c =(Node)nodes.searchWithString("c").getOlio();
        Node b = (Node)nodes.get(nodes.size()-1);
        LinkedList<Edge> edgesOne = c.getEdges();
        LinkedList<Edge> edgesLast = b.getEdges();
        Helper help = a.getHelperObject();
        assertEquals(4, nodes.size());
        assertEquals(1, edgesOne.size());
        assertEquals(2, edgesLast.size());
        assertEquals(a.getGoal(), help.search(this.end));
        assertEquals(a.getStart(), help.search(this.start));
        
        Node a = help.search("a");
        int xA = a.getX();
        int yA = a.getY();
        int heuristicA = a.getHeuristic();
        assertEquals(29, heuristicA);
        assertEquals(5, xA);
        assertEquals(15, yA);
        
        Node d = help.search("d");
        int xD = d.getX();
        int yD = d.getY();
        int heuristicD = d.getHeuristic();
        assertEquals(31, heuristicD);
        assertEquals(20, xD);
        assertEquals(0, yD);
    }

    /**
     * Test of route method, of class Astar.
     */
    @Test
    public void testRoute() {
        a.initialize();
        a.route();
        Helper help = a.getHelperObject();     
        Node goal = help.search(this.end);
        Node previousFromGoal = help.search(this.end).getPrevious();
        assertEquals(35, goal.getShortest());
        assertEquals(help.search("a"), previousFromGoal);
    }

    /**
     * Test of print method, of class Astar.
     */
    @Test
    public void testPrint() {
        a.initialize();
        a.route();
        a.print();
        Helper help = a.getHelperObject();
        String vastaus = a.pathToGoalString();
        String tulos = "Lyhyin reitti solmusta " + help.search(start).toString() + " solmuun " + help.search(end).toString() + " on " + help.search(end).getShortest() + "km.ab";
        assertEquals(vastaus, tulos);
    }

    private LinkedList<Location> doMap() {
        LinkedList<Location> graph = new LinkedList<Location>();
        Location a = new Location("a");
        Location b = new Location("b");
        Location c = new Location("c");
        Location d = new Location("d");
        
        a.add(new Target("b", 35, 30, 30));
        a.add(new Target("c", 5, 18, 6));
        a.add(new Target("d", 10, 20, 0));
        
        b.add(new Target("a", 35, 5, 15));
        b.add(new Target("d", 25, 20, 0));
        
        c.add(new Target("a", 5, 5, 15));
        
        d.add(new Target("a", 10, 5, 15));
        d.add(new Target("b", 25, 30, 30));
        
        graph.add(a);
        graph.add(b);
        graph.add(c);
        graph.add(d);

        return graph;
    }   
}