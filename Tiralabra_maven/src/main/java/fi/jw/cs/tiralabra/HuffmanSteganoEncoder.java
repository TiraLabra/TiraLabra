package fi.jw.cs.tiralabra;

import java.io.IOException;

/**
 * @author Jan Wikholm <jw@jw.fi>
 * @since 2013-08-02
 */

public class HuffmanSteganoEncoder {
    public static void main(String... args) {
//        String message = "";
//        for (String s : args) {
//            message += s + " ";
//        }
//        message = message.trim();
//        System.out.println("Message \t" + message);
//        Huffman encoder = new Huffman(message);
//        encoder.encode();
//
//        System.out.print("Encoded \t");
//        System.out.println(encoder.getEncodedMessage());
//
//        Huffman decoder = new Huffman();
//        decoder.setMap(encoder.getMap());
//        decoder.setEncodedMessage(encoder.getEncodedMessage());
//        decoder.decode();
//        System.out.print("Decoded \t");
//        System.out.println(decoder.getMessage());

        try {
            Steganographer s = new Steganographer(args[0], "");
            System.out.println("Got file open " + args[0]);
            s.emptyLeastSignificantBits();
            System.out.println("Cleared out bits");
            s.saveFile(args[1]);
            System.out.println("File saved to " + args[1]);
        } catch (IOException ioe) {
            System.out.println("Oh poop.");
            ioe.printStackTrace();
        }
    }
}
