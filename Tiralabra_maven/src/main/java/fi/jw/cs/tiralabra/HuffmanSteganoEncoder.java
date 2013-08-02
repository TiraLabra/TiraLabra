package fi.jw.cs.tiralabra;

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
        System.out.println("HuffmanSteganoEncoder called with message: " + message);
        Huffman huff = new Huffman(message);
        huff.encode();
        System.out.println("Result of encoding: " + huff.getEncodedMessage());
        System.out.println("Map used:");
        for (String key : huff.getMap().keySet()) {
            System.out.println("\t" + key + " => " + huff.getMap().get(key));
        }
    }
}
