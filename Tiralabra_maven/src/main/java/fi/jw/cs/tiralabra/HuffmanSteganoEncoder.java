package fi.jw.cs.tiralabra;

import java.io.IOException;

/**
 * @author Jan Wikholm <jw@jw.fi>
 * @since 2013-08-02
 */

public class HuffmanSteganoEncoder {
    public static void main(String... args) {

        try {
            char cmd = args[0].charAt(0);
            Steganographer s;
            switch (cmd) {
                case 'c':
                    checkMaxLength(args[1]);
                    break;
                case 'e':
                    encode(args);
                    break;
                case 'd':
                    decode(args[1], "");
                    break;
            }

        } catch (IOException ioe) {
            System.out.println("Oh poop.");
            ioe.printStackTrace();
        }


    }

    static void checkMaxLength(String path) throws IOException {
        Steganographer s = new Steganographer(path);
        System.out.println("Maximum encoding length for file " + path + " = " + s.getMaximumMessageLength());
    }

    static void encode(String... args) throws IOException {

        String message = "";
        String src = args[1];
        String dest = args[2];

        for (int i = 3; i < args.length; i++) {
            message += args[i] + " ";
        }

        message = message.trim();
        Huffman encoder = new Huffman(message);
        encoder.encode();
        String encodedMessage = encoder.getEncodedMessage();
        Steganographer s = new Steganographer(src, encodedMessage);
        //encoder.getSerializedMap();
        s.encode();
        s.saveFile(dest);
        System.out.println(dest + " saved");
        s = null;
    }

    static void decode(String path, String map) throws IOException {
        System.out.println("Opening " + path);
        Steganographer dec = new Steganographer(path);
        dec.decodeBits();
        String stegDecoded = dec.getMessage();

        Huffman decoder = new Huffman();
        //decoder.parseMap(map);
        //decoder.setMap(encoder.getMap());
        decoder.setEncodedMessage(stegDecoded);
        decoder.decode();
        System.out.println(decoder.getMessage());
    }
}
