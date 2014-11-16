package A;

import java.util.Iterator;
import junit.framework.TestCase;

/**
 *
 * @author Lutikka
 */
public class A_StarTest extends TestCase {

    public A_StarTest(String testName) {
        super(testName);

    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void test5NodesStraightPath(){
        Node n1 = new Node();
        n1.setPosition(new int[]{0});
        Node n2 = new Node();
        n2.setPosition(new int[]{1});
        Node n3 = new Node();
        n3.setPosition(new int[]{2});
        Node n4 = new Node();
        n4.setPosition(new int[]{3});
        Node n5 = new Node();
        n5.setPosition(new int[]{4});
        n1.addEdge(new Edge(n2,1));
        n2.addEdge(new Edge(n3,1));
        n3.addEdge(new Edge(n4,1));
        n4.addEdge(new Edge(n5,1));
        n2.addEdge(new Edge(n1,1));
        n3.addEdge(new Edge(n2,1));
        n4.addEdge(new Edge(n3,1));
        n5.addEdge(new Edge(n4,1));
        IHeuristic heuristic = new IHeuristic() {

            public double Distance(Object o1, Object o2) {
                Node node1 = (Node) o1;
                Node node2 = (Node) o2;
                double total = 0;
                for (int i = 0; i < node1.getPosition().length; i++) {
                    total += Math.pow(node1.getPosition()[i] - node2.getPosition()[i], 2);
                }
                return Math.sqrt((double) total);
            }

        };
        Iterator iter = A_Star.findShortestPath(n1, n5, heuristic).iterator();
        int i=0;
        while(iter.hasNext()){
            Node n = (Node)iter.next();
            assertTrue("Path is not the shortest path",n.getPosition()[0]==i);
            i++;
        }
        
//        Node[] nodes = new Node[100];
//        for (int i = 0; i < 100; i++) {
//            nodes[i] = new Node();
//        }
//        for (int i = 0; i < 97; i++) {
//            nodes[i].addEdge(new Edge(nodes[i+1],1));
//            nodes[i].addEdge(new Edge(nodes[i+2],2));
//            nodes[i].addEdge(new Edge(nodes[i+3],3));
//            nodes[i].setPosition(new int[]{i});
//        }
//        for (int i = 97; i < 100; i++) {
//            nodes[i].setPosition(new int[]{i});
//        }
//        IHeuristic heuristic = new IHeuristic(){
//
//            public double Distance(Object o1, Object o2) {
//                Node node1 = (Node)o1;
//                Node node2 = (Node)o2;
//                double total=0;
//                for (int i = 0;i<node1.getPosition().length; i++) {
//                    total+= Math.pow(node1.getPosition()[i]-node2.getPosition()[i], 2);                        
//                }
//                return Math.sqrt((double)total);
//            }
//            
//        };
//        List a = A_Star.findShortestPath(nodes[0], nodes[95], heuristic);
    }
}
