package fi.jw.cs.tiralabra;

import java.io.IOException;

/**
 * @author Jan Wikholm <jw@jw.fi>
 * @since 2013-08-02
 */

public class HuffmanSteganoEncoder {
    public static void main(String... args) {
        try {

            String message = "";
            String src = args[0];
            String dest = args[1];

            for (int i = 2; i < args.length; i++) {
                message += args[i] + " ";
            }

            message = message.trim();
            System.out.println("Message \t" + message);
            Huffman encoder = new Huffman(message);
            encoder.encode();
            String encodedMessage = encoder.getEncodedMessage();

            System.out.print("Encoded \t");
            System.out.println(encodedMessage);


            System.out.println("Open source file " + src);
            System.out.println("Encode: \t" + encodedMessage);
            Steganographer s = new Steganographer(src, encodedMessage);
            s.encode();
            s.saveFile(dest);
            s = null;
            System.out.println("File saved to " + dest);
            System.out.println("Opening new Steganographer for " + dest);
            Steganographer dec = new Steganographer(args[1]);
            dec.decodeBits();
            String stegDecoded = dec.getMessage();
            System.out.println("Decoded: \t" + stegDecoded);
            System.out.println("Handing it over to Huffman");

            Huffman decoder = new Huffman();
            decoder.setMap(encoder.getMap());
            decoder.setEncodedMessage(stegDecoded);
            decoder.decode();
            System.out.print("Decoded \t");
            System.out.println(decoder.getMessage());
        } catch (IOException ioe) {
            System.out.println("Oh poop.");
            ioe.printStackTrace();
        }


    }
}
