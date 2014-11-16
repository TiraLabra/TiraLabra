package A;

import java.util.List;

/**
 *
 * @author Lutikka
 */
public class Main {
    
    public static void main(String[] args) {
        Node[] nodes = new Node[100];
        for (int i = 0; i < 100; i++) {
            nodes[i] = new Node();
        }
        for (int i = 0; i < 97; i++) {
            nodes[i].addEdge(new Edge(nodes[i+1],1));
            nodes[i].addEdge(new Edge(nodes[i+2],2));
            nodes[i].addEdge(new Edge(nodes[i+3],3));
            nodes[i].setPosition(new int[]{i});
        }
        for (int i = 97; i < 100; i++) {
            nodes[i].setPosition(new int[]{i});
        }
        IHeuristic heuristic = new IHeuristic(){

            public double Distance(Object o1, Object o2) {
                Node node1 = (Node)o1;
                Node node2 = (Node)o2;
                double total=0;
                for (int i = 0;i<node1.getPosition().length; i++) {
                    total+= Math.pow(node1.getPosition()[i]-node2.getPosition()[i], 2);                        
                }
                return Math.sqrt((double)total);
            }
            
        };
        List a = A_Star.findShortestPath(nodes[0], nodes[95], heuristic);
    }
}
