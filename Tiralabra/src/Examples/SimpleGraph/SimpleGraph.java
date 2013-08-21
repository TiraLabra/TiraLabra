package Examples.SimpleGraph;

import Algorithms.Path;
import Structures.Graph.Graph;
import Structures.Graph.Vertex;

/**
 * How to build a basic graph
 */
public class SimpleGraph {
    public SimpleGraph(){
        buildGraph();
    }
    private void buildGraph(){
        /*
         * Let's create the following graph:
         *    -3        1
         * H<------A----------B
         * |       |          |
         * | 5     | 9        | 7
         * |       |    -1    |     3
         * I------>D--------->C----------F
         *    -2   |                     |
         *         | 4                   | 2
         *         |        8            |
         *         E---------------------G
         */
        // Create the graph itself
        Graph myGraph = new Graph();
        // Create the vertices and name them
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");
        Vertex e = new Vertex("E");
        Vertex f = new Vertex("F");
        Vertex g = new Vertex("G");
        Vertex h = new Vertex("H");
        Vertex i = new Vertex("I");
        // Create the edges
        // Connect vertex A to H
        myGraph.connect(a, h, -3);
        // Connect vertex A to B and vertex B to A
        myGraph.connectBothWays(a, b, 1);
        myGraph.connectBothWays(a, d, 9);
        myGraph.connectBothWays(b, c, 7);
        myGraph.connectBothWays(h, i, 5);
        myGraph.connect(i, d, -2);
        myGraph.connectBothWays(d, e, 4);
        myGraph.connect(d, c, -1);
        myGraph.connectBothWays(c, f, 3);
        myGraph.connectBothWays(e, g, 8);
        myGraph.connectBothWays(f, g, 2);
        // Let's find the shortest path between vertex A and G. Our graph will use Bellman-Ford algorithm because it has negative edge weights
        Path fromAtoG = myGraph.shortestPath(a, g);
        // Let's print our path (length and the path itself)
        System.out.println(fromAtoG);
        // Let's change the edge weights between vertex A and H and vertex I and D. Let's also remove the edge between vertex D and C completely
        myGraph.setWeight(a, h, 1);
        myGraph.setWeight(i, d, 2);
        myGraph.disconnect(d, c);
        // What's the path now?
        fromAtoG=myGraph.shortestPath(a, g);
        System.out.println(fromAtoG);
        // Our graph has changed the search algorithm to Dijkstra because all edge weights are now positive
    }
}
