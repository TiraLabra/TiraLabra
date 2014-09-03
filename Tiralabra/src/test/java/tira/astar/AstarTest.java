package tira.astar;

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
public class AstarTest {
    
    private HashMap<String, ArrayList<Target>> grid;
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
        Node c =(Node)nodes.get(0);
        Node b = (Node)nodes.get(nodes.size()-1);
        LinkedList<Edge> edgesOne = c.getEdges();
        LinkedList<Edge> edgesLast = b.getEdges();
        Helper help = a.getHelperObject();
        assertEquals(4, nodes.size());
        assertEquals(3, edgesOne.size());
        assertEquals(3, edgesLast.size());
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

    private HashMap<String, ArrayList<Target>> doMap() {
        HashMap<String, ArrayList<Target>> graph = new HashMap<String, ArrayList<Target>>();
        ArrayList<Target> a = new ArrayList<Target>();
        ArrayList<Target> b = new ArrayList<Target>();
        ArrayList<Target> c = new ArrayList<Target>();
        ArrayList<Target> d = new ArrayList<Target>();
        
        a.add(new Target("b", 35, 30, 30));
        a.add(new Target("c", 5, 10, 10));
        a.add(new Target("d", 10, 20, 0));
        
        b.add(new Target("a", 35, 5, 15));
        b.add(new Target("d", 25, 20, 0));
        
        c.add(new Target("a", 5, 5, 15));
        c.add(new Target("d", 5, 20, 0));
        
        d.add(new Target("a", 10, 5, 15));
        d.add(new Target("b", 25, 30, 30));
        d.add(new Target("c", 5, 10, 10));
        
        graph.put("a", a);
        graph.put("b", b);
        graph.put("c", c);
        graph.put("d", d);

        return graph;
    }   
}