package Hike;

import Hike.Algorithms.Dijkstra;
import Hike.Graph.Node;
import Hike.Structures.MinHeap;
import Hike.gameWindow.GameWindow;
import java.util.Random;
import javax.swing.SwingUtilities;

class Main {

    public static void main(String[] args) {
//        Random random = new Random();
//        MinHeap heap = new MinHeap(20);
//        for (int i = 0; i < 5; i++) {
//            Node node = new Node(1, 1, 5);
//            node.setDistance(random.nextInt(20));
//            heap.insert(node);
//        }
//        heap.printHeap();
//        System.out.println("");
//        heap.decHeap(1, 2);
//        heap.printHeap();


        GameWindow game = new GameWindow();
        game.run();

    }
}
