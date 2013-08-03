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
            String message = "111000111";
            System.out.println("Open source file " + args[0]);
            System.out.println("Encode: \t" + message);
            Steganographer s = new Steganographer(args[0], message);
            s.encode();
            s.saveFile(args[1]);
            s = null;
            System.out.println("File saved to " + args[1]);
            Steganographer dec = new Steganographer(args[1]);
            System.out.println("Decoding bits from " + args[1]);
            dec.decodeBits();
            System.out.println("Decode: \t" + dec.getMessage());
        } catch (IOException ioe) {
            System.out.println("Oh poop.");
            ioe.printStackTrace();
        }
    }
}
