package fi.jw.cs.tiralabra;

import java.util.PriorityQueue;

/**
 * @author Jan Wikholm <jw@jw.fi>
 * @since 2013-08-02
 */

public class HuffmanSteganoEncoder {
    public static void main(String... args) {
        String message = "";
        for (String s : args) {
            message += s + " ";
        }
        message = message.trim();
        System.out.println("HuffmanSteganoEncoder called with message: \n" + message);
        Huffman huff = new Huffman(message);
        huff.encode();
        System.out.println("Result of encoding: \n" + huff.getEncodedMessage());
        System.out.println("Map used:");
        for (String key : huff.getMap().keySet()) {
            System.out.println("\t" + key + " => " + huff.getMap().get(key));
        }

        PriorityQueue<Node> nodes = new PriorityQueue<Node>(huff.getSortedNodes());
        System.out.println("Weights:");
        while (!nodes.isEmpty()) {
            Node n = nodes.poll();
            System.out.print(n.getLabel() + "," + n.getWeight() + "\0");
        }
    }
}
